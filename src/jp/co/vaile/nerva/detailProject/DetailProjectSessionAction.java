package jp.co.vaile.nerva.detailProject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DetailProjectSessionAction extends HttpServlet {
	/**
	 *案件詳細情報表示画面で詳細ボタンを押下後、チーム詳細情報ページに遷移する
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String result = "/jsp/ShowTeamDetailInfoAction";

		session.removeAttribute("errorMsgList");

		//リクエストスコープのチームIDと案件IDを取得する
		String teamId = request.getParameter("teamId");

		//セッションからログインユーザーの所属会社IDと所属会社グループ権限を取得する
		String userCompanyId = (String) session.getAttribute("companyId");
		String companyPrivilege = (String)session.getAttribute("companyPrivilege");


		//チーム参照権限の検査を行う
		ProjectDetailLogic projectDetailLogic = new ProjectDetailLogic();
		String errorMsgId;
		try {
			errorMsgId = projectDetailLogic.checkTeamReferenceAuthority(userCompanyId,companyPrivilege,teamId);

			//戻り値が空文字以外の場合
			if (!errorMsgId.equals("")) {
				//エラーメッセージをリクエストに格納する
				ArrayList<String> errorMsgList = new ArrayList<>();
				errorMsgList.add(errorMsgId);
				request.setAttribute("errorMsgList", errorMsgList);
				//案件詳細画面に遷移する
				result = "/jsp/ShowProjectDetailAction";

				//戻り値が空文字の場合
			} else {
				//セッションにチームIDを格納する
				request.setAttribute("teamId", teamId);
				session.setAttribute("teamId", teamId);

			}
		} catch (ClassNotFoundException | SQLException e) {
			result = "/jsp/error.jsp";
			e.printStackTrace();
		}
		//チーム詳細表示アクションにフォワードする
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		disp.forward(request, response);

	}
}
