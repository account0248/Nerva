package jp.co.vaile.nerva.searchTeam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchTeamSessionAction extends HttpServlet {

	/**
	 *チーム検索画面で詳細ボタンを押下後、チーム詳細情報ページに遷移する
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = "/jsp/ShowTeamDetailInfoAction";
		HttpSession session = request.getSession();
		//1.リクエストスコープのチームIDを取得する
		String teamId = request.getParameter("teamDetailName");
		//2.セッションスコープにチームIDを格納する。
		request.setAttribute("teamId", teamId);
		session.setAttribute("teamId", teamId);
		//3.チーム詳細情報表示アクションにフォワードする
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		// forwardメソッドでJSPに遷移します。
		disp.forward(request, response);
	}

}
