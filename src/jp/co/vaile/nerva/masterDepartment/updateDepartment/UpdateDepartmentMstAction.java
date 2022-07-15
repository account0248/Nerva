package jp.co.vaile.nerva.masterDepartment.updateDepartment;

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

@WebServlet("/UpdateDepartmentMstAction")
public class UpdateDepartmentMstAction extends HttpServlet {

	/**
	 * 更新するボタンが押下された時、所属組織情報の更新を行う。
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 **/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();

		// json型の値を画面から受け取る
		String[] departmentId = mapper.readValue(request.getParameter("json[departmentId]"), String[].class);
		String[] departmentName = mapper.readValue(request.getParameter("json[departmentName]"), String[].class);

		// ログイン者ID（セッション）取得
		String userId = (String) session.getAttribute("userId");
		// 入力情報をDTOに格納する
		UpdateDepartmentDTO updateDepartmentDTO = new UpdateDepartmentDTO(userId, departmentId, departmentName);

		try {
			// エラーチェックを行うクラスのインスタンス生成
			UpdateDepartmentCheckAction updateDepartmentCheckAction = new UpdateDepartmentCheckAction();
			// エラーメッセージをを格納するインスタンスを生成
			List<String> errorList = new ArrayList<String>();
			// エラーリストのチェックをする
			errorList = updateDepartmentCheckAction.checkUpdateDepartment(updateDepartmentDTO);

			if (errorList.size() != 0) {
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
				// 3-2.エラーが無い場合、4に進む。
			} else {
				// 4.入力情報を引数に更新処理を呼び出す。
				UpdateDepartmentMstLogic updateDepartmentMstLogic = new UpdateDepartmentMstLogic();
				updateDepartmentMstLogic.updateDepartmentMstLogic(updateDepartmentDTO);
				// クローズ
				out.print("{}");
				out.close();
			}
		} catch (ClassNotFoundException | SQLException e) {
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
