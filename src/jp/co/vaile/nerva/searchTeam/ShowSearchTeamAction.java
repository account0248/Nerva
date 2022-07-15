package jp.co.vaile.nerva.searchTeam;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;

public class ShowSearchTeamAction extends HttpServlet {

	/**
	 * チーム検索画面を表示する際の処理
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/*	前画面によって処理を分岐する。
			1-1.前画面がチーム詳細情報の場合、チーム検索画面に遷移し、処理を終了する。
			1-2.前画面がチーム詳細情報でない場合、2へ進む。	*/
		String teamDetailPage = (String) request.getAttribute("teamDetailPage");
		HttpSession session = request.getSession();
		session.removeAttribute("teamId");
		session.removeAttribute("teamDetailPage");

		if (teamDetailPage == null) {
			//2.セッションスコープにチームIDが格納されていた場合、チームIDを破棄する。

			//3.セッションスコープにチーム検索画面入力情報DTOが格納されていた場合、チーム検索画面入力情報DTOを破棄する。
			session.removeAttribute("searchTeamPageDTO");
		}
		//4.チーム検索画面に遷移する際に必要な情報を取得する。

		ShowSearchTeamLogic showSearchTeamLogic = new ShowSearchTeamLogic();
		String result = "/jsp/searchTeam.jsp";
		try {
			List<OrderSourceDTO> orderSourceList = showSearchTeamLogic.returnOrderSourceTable();
			request.setAttribute("orderSourceList", orderSourceList);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = "/jsp/error.jsp";
		}
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		// forwardメソッドでJSPに遷移します。
		//5.	チーム検索画面に遷移する。
		disp.forward(request, response);
	}

}
