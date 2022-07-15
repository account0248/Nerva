package jp.co.vaile.nerva.teamDetail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;

/**
 * Servlet implementation class ShowTeamDetailInfoAction
 */

public class ShowTeamDetailInfoAction extends HttpServlet {

	/**
	 *チーム詳細情報表示画面に遷移する。
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = "/jsp/detailTeam.jsp";

		// リクエストスコープからチームIDを取得する。
		HttpSession session = request.getSession();
		String teamId = (String) request.getAttribute("teamId");
		//セッションからログインユーザーの所属会社IDと所属会社グループ権限を取得する
		String companyId = (String) session.getAttribute("companyId");
		String companyPrivilege = (String)session.getAttribute("companyPrivilege");
		
		// 会社識別コードと会社名が入ったセッションを破棄する
		session.removeAttribute("companyCode");
		session.removeAttribute("companyName");
					
		// チームIDを基に案件詳細情報を取得する
		TeamDetailLogic teamDetailLogic = new TeamDetailLogic();
		TeamDetailPageDTO teamDetailPageDTO;
		try {
			teamDetailPageDTO = teamDetailLogic.acquireTeamDetailInfo(teamId);		

		// チームIDを基に参加従業員リストを取得する
		List<TeamEntryEmpDTO> teamEntryEmpList = new ArrayList<TeamEntryEmpDTO>();
		teamEntryEmpList = teamDetailLogic.acquireTeamEntryEmp(teamDetailPageDTO.getTeamId());
		
		// チームIDの参照権限チェックを行う
		CheckViewingAuthority checkTeamId = new CheckViewingAuthority();
		boolean viewingAuthority = checkTeamId.checkViewingAuthority(companyId,companyPrivilege, teamDetailPageDTO.getCompanyId());

		// チーム詳細情報と参加従業員リストをリクエストスコープに格納する
		if (viewingAuthority == true) {
			request.setAttribute("teamDetailPageDTO", teamDetailPageDTO);
			request.setAttribute("teamEntryEmpList", teamEntryEmpList);
		//参照権限がない場合、変数resultにエラー画面のURLを代入する
		} else {
			result = "/jsp/error.jsp";
		}
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			result = "/jsp/error.jsp";
		}

		//変数resultに格納されているURLの画面に遷移する
		request.getRequestDispatcher(result).forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}