package jp.co.vaile.nerva.teamDetail;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeamDetailBackPageAction extends HttpServlet {

	/**
	 * 戻るボタンを押下した際の処理。
	 * セッションの情報をもとに、前のページの画面遷移処理を呼び出す。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// セッションから前のページの情報を取得する
		HttpSession session = request.getSession();
		String teamId = (String) session.getAttribute("teamId");
		String projectId = (String) session.getAttribute("projectId");

		String result = "/jsp/ShowSearchTeamAction";

		// リクエストスコープにチームIDがある場合、リクエストスコープにチーム詳細ページ情報の値を格納する
		if (teamId != null) {
			request.setAttribute("teamDetailPage", "teamDetailPage");
			result = "/jsp/ShowSearchTeamAction";
		}

		if (projectId != null) {
			result = "/jsp/ShowProjectDetailAction";
		}

		// チーム検索遷移処理を呼び出す
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		// forwardメソッドでJSPに遷移します。
		disp.forward(request, response);
	}
}
