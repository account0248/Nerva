package jp.co.vaile.nerva.teamDetail;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TeamDetailSessionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * チーム詳細情報表示画面で詳細ボタンを押下後、従業員詳細情報ページに遷移する
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String empId =request.getParameter("empId");

		//	セッションにチームIDを格納する
		HttpSession session = request.getSession();
		session.setAttribute("teamDetailPage", "teamDetailPage");
		//	リクエストスコープに従業員IDを格納する
		request.setAttribute("employeeId", empId);
		//	従業員詳細表示アクションにフォワードする
		request.getRequestDispatcher("/EmpDetailInfoAction").forward(request, response);
	}
}
