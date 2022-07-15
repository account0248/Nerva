package jp.co.vaile.nerva.detailEmployee;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EmpDetailBackPageAction")
public class EmpDetailBackPageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 戻るボタンを押下した際の処理。
	 * セッションの情報をもとに、前のページの画面遷移処理を呼び出す。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//セッションから前のページの情報を取得する。
		HttpSession session = request.getSession();
		String teamDetailPage = (String) session.getAttribute("teamDetailPage");
		String teamId = (String) session.getAttribute("teamId");
		
		//セッションに前画面の情報があった場合はチーム詳細情報遷移処理を呼び出す。
		if (teamDetailPage != null) {
			request.setAttribute("teamId", teamId);
			request.getRequestDispatcher("ShowTeamDetailInfoAction").forward(request, response);

			//セッションに前画面の情報がなかった場合はリクエストスコープにマップの値を格納して従業員検索画面遷移処理を呼び出す。
		} else {
			Map<String, String> searchEmployee = new HashMap<String, String>();
			searchEmployee.put("detailEmployee", "detailEmployee");
			request.setAttribute("searchEmployee", searchEmployee);
			request.getRequestDispatcher("ShowSearchEmpAction").forward(request, response);
		}

	}

}
