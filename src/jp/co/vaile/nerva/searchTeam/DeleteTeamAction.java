package jp.co.vaile.nerva.searchTeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeleteTeamAction extends HttpServlet {

	/**
	 * 削除ボタンを押下した際の処理。
	 * 該当のチーム情報の削除処理を呼び出す。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		//1.チーム検索画面で記載された入力情報を受け取る。
		HttpSession session = request.getSession();
		session.getAttribute("searchTeamDTOList");
		String userId = (String) session.getAttribute("userId");
		//2.該当のチームIDを受け取る。
		String teamId = request.getParameter("json[teamId]");

		/*	3.	該当のチームIDとログインユーザーIDを引数に削除処理する際のエラーチェック処理を呼び出す。
			3-2.エラーが無い場合、4に進む。
		*/
		PrintWriter out = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		try {
			DeleteTeamCheckLogic deleteTeamCheckLogic = new DeleteTeamCheckLogic();
			List<String> errorList = deleteTeamCheckLogic.checkDeleteTeam(teamId, userId);
			
			//3-1.エラーがあった場合、エラーメッセージを設定し、JSONで情報を返す。
			if (errorList.size() != 0) {
				//JSONで情報を返す		
				String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));
				out.write(errorListString);
				return;
			}
			//4.該当のチームIDを引数にチーム削除処理を呼び出す。
			DeleteTeamLogic deleteTeamLogic = new DeleteTeamLogic();
			deleteTeamLogic.deleteTeam(teamId, userId);
			String successResult = "{}";
			out.write(successResult);
			//クローズ
			out.close();
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
