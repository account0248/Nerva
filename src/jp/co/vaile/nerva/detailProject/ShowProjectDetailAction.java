package jp.co.vaile.nerva.detailProject;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class ShowProjectDetailAction extends HttpServlet {

	/**
	 * リクエストを受け取り、案件詳細情報表示画面に表示する情報をもって遷移する
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProjectDetailLogic projectDetailLogic = new ProjectDetailLogic();
		String result = "/jsp/detailProject.jsp";

		// 前画面エラーメッセージ取得 
		ArrayList<String> errorMsgList = new ArrayList<String>();

		if ((ArrayList<String>) request.getAttribute("errorMsgList") != null) {
			errorMsgList = (ArrayList<String>) request.getAttribute("errorMsgList");
		}

		String projectId = (String) request.getParameter("projectId");
		// セッションスコープの案件IDを受け取る
		if (projectId == null) {
			projectId = (String) session.getAttribute("projectId");
		}
		// セッションからログインユーザーの所属会社IDを受け取る
		String userCompanyId = (String) session.getAttribute("companyId");

		// セッションからログインユーザーの所属会社グループ権限を取得する
		String companyPrivilege = (String) session.getAttribute("companyPrivilege");

		ProjectDetailPageDTO projectDetailPageDto = new ProjectDetailPageDTO();

		try {

			// 案件IDを基に案件詳細情報を取得する
			projectDetailPageDto = projectDetailLogic.acquireProjectDetailInfo(projectId);

			// 案件の参照権限があるかを確認する 
			boolean viewingAuthority = new CheckViewingAuthority().checkViewingAuthority(userCompanyId,
					companyPrivilege, projectDetailPageDto.getContractorId());
			if (viewingAuthority == false) {
				result = "/jsp/error.jsp";
			}

			// 案件情報が見つからなかった場合はエラーページへ
			if (projectDetailPageDto.getProjectId() == null) {
				result = "/jsp/ShowProjectAction";
				ErrorMsg returnErrorMsg = new ErrorMsg();
				String[] projectErrorMsg = { PROJECT };
				String errorMsg = returnErrorMsg.returnErrorMsg(CommonConstants.NON_EMP_ERROR, projectErrorMsg);
				request.setAttribute("errorMsg", errorMsg);
			}

			// 案件情報IDを基に参加チームのリストを取得する
			ArrayList<ProjectEntryTeamDTO> projectEntryTeamList = new ArrayList<ProjectEntryTeamDTO>();

			projectEntryTeamList = projectDetailLogic.acquireProjectEntryTeam(projectDetailPageDto.getProjectInfoId());

			// 案件詳細情報と参加チームリストをリクエストスコープに格納する
			request.setAttribute("projectDetailPageDto", projectDetailPageDto);
			request.setAttribute("projectEntryTeamList", projectEntryTeamList);
			request.setAttribute("errorMsgList", errorMsgList);

			// 案件IDをセッションに格納する
			session.setAttribute("projectId", projectId);

		} catch (ClassNotFoundException | SQLException e) {
			// エラー発生自はログを出力してエラー画面へ遷移
			e.printStackTrace();
			result = "/jsp/error.jsp";
		}

		// 案件詳細情報表示画面に遷移する
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		disp.forward(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
