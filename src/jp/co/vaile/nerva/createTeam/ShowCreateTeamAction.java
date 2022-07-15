package jp.co.vaile.nerva.createTeam;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCompany;


// @WebServlet("/ShowCreateTeamAction")
public class ShowCreateTeamAction extends HttpServlet {

	/**
	 * セッションからチーム作成画面に表示する情報をもって遷移する。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションからログインユーザーのユーザーID、所属部長、所属会社グループ権限、所属会社IDを取得する。
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		CreateTeamLogic createTeamLogic = new CreateTeamLogic();
		String userId = (String) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		String companyPrivilege = (String)session.getAttribute("companyPrivilege");
		String companyId = (String)session.getAttribute("companyId");
		String companyCode = null;
		String companyName = null;
		SearchCompany searchCompany=new SearchCompany();

		try {
			//ユーザーIDを引数に、共通クラスから、ユーザーの所属している会社の会社識別コードを取得する。
			companyCode = searchCompany.searchCompanyCode(userId);
			
			//ユーザーID、所属会社ID、所属会社グループ権限を引数に、所属会社検索処理を呼び出す。
			companyName = createTeamLogic.getCompanyName(userId,companyId,companyPrivilege);		
		
			// 会社識別コードと所属会社名をセッションに格納し、所属部長をリクエストスコープに格納する。
			session.setAttribute("companyCode", companyCode);
			request.setAttribute("userName", userName);
			session.setAttribute("companyName", companyName);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// チーム作成画面に遷移する
		request.getRequestDispatcher("/jsp/createTeam.jsp").forward(request, response);
	}
}
