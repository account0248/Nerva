package jp.co.vaile.nerva.masterRole.updateRole;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet( "/UpdateRoleMstAction" )
public class UpdateRoleMstAction extends HttpServlet {
	/**
	 * 1 担当マスタメンテナンス画面内更新フォームに入力された値を受け取る。 
	 * 2 セッション情報からユーザーIDを取得する。 
	 * 3 入力情報を引数にエラーチェック処理を呼び出す。 
	 * 	3-1 エラーがあった場合,エラーメッセージをJSONの値を返す。 
	 * 	3-2 エラーがなかった場合、4に進む。 
	 * 4 入力情報を引数に更新処理を呼び出す。
	 **/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
		// json型の値を画面から受け取る
		String[] roleId = mapper.readValue(request.getParameter("json[roleId]"), String[].class);
		String[] roleName = mapper.readValue(request.getParameter("json[roleName]"), String[].class);
		// ログイン者ID（セッション）取得
		String userId = (String) session.getAttribute("userId");
		// 入力情報をDTOに格納する
		UpdateRoleDTO updateRoleDTO = new UpdateRoleDTO(userId, roleId, roleName);
		
		try {
			// エラーチェックを行うクラスのインスタンス生成
			UpdateRoleCheckAction updateRoleCheckAction = new UpdateRoleCheckAction();
			// エラーメッセージをを格納するインスタンスを生成
			List<String> errorList = new ArrayList<String>();
			// エラーリストのチェックをする
			errorList = updateRoleCheckAction.checkUpdateRole(updateRoleDTO);



			if (errorList.size() != 0) {
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
				// 3-2.エラーが無い場合、4に進む。
			} else {
				// 4.入力情報を引数に更新処理を呼び出す。
				UpdateRoleMstLogic updateRoleMstLogic = new UpdateRoleMstLogic();
				updateRoleMstLogic.updateRoleMstLogic(updateRoleDTO);
				// クローズ
				out.print("{}");
				out.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			String errorListString = mapper.writeValueAsString("");
			out.print(errorListString);
			out.close();
			e.printStackTrace();
		}
	}

	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}
}
