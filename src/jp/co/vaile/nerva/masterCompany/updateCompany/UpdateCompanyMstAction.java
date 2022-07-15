package jp.co.vaile.nerva.masterCompany.updateCompany;

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

@WebServlet( "/UpdateCompanyMstAction" )
public class UpdateCompanyMstAction extends HttpServlet {
	/**
	更新するボタンが押下された時、所属会社情報の更新を行う。
	*@param HttpServletRequest request, HttpServletResponse response
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();

		// 1 所属会社マスタメンテナンス画面内更新フォームに入力された値を受け取る。
		String[] companyId = mapper.readValue(request.getParameter("json[companyId]"), String[].class);
		String[] companyName = mapper.readValue(request.getParameter("json[companyName]"), String[].class);

		// 2 ログイン者ID（セッション）取得
		String userId = (String) session.getAttribute("userId");

		// 入力情報をDTOに格納する
		UpdateCompanyDTO updateCompanyDTO = new UpdateCompanyDTO(userId, companyId, companyName);

		try {
			// エラーチェックを行うクラスのインスタンス生成
			UpdateCompanyCheckAction updateCompanyCheckAction = new UpdateCompanyCheckAction();
			// エラーメッセージをを格納するインスタンスを生成
			List<String> errorList = new ArrayList<String>();
			// 3 エラーリストのチェックをする
			errorList = updateCompanyCheckAction.checkUpdateCompany(updateCompanyDTO);

			// 3-1 エラーがあった場合、エラーメッセージをJSONの値で返す
			if (errorList.size() != 0) {
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.print(errorListString);
				out.close();
			} else {
				// 4 エラーが無い場合入力情報を引数に更新処理を呼び出す
				UpdateCompanyMstLogic updateCompanyMstLogic = new UpdateCompanyMstLogic();
				updateCompanyMstLogic.updateCompanyMstLogic(updateCompanyDTO);
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
