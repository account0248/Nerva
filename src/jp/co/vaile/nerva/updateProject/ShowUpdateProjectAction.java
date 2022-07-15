package jp.co.vaile.nerva.updateProject;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;
import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;

public class ShowUpdateProjectAction extends HttpServlet {

	/**
	 * 案件詳細情報表示画面の更新ボタン押下後、案件情報更新画面へ遷移する
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String result = "/jsp/updateProject.jsp";

		// セッションスコープから案件IDを受け取る
		String projectId = (String) session.getAttribute("projectId");

		ShowUpdatePjtLogic showUpdatePjtLogic = new ShowUpdatePjtLogic();
		ProjectDetailPageDTO updatePjtPageDto = new ProjectDetailPageDTO();
		
		try {
			
			// 案件IDを基に案件情報更新画面情報取得処理を呼び出す
			updatePjtPageDto = showUpdatePjtLogic.acquireUpdatePjtInfo(projectId);

			/* 案件情報が見つからなかった場合は案件検索画面にエラーメッセージを持って遷移 */
			if (updatePjtPageDto.getProjectId() == null) {
				result = "/jsp/ShowProjectAction"; // TODO 案件検索画面表示処理へ
				String searchProjectLengthCheckConstants[] = { CommonConstants.PROJECT }; // TODO エラーメッセージID記述
				String errorMessage = new ErrorMsg().returnErrorMsg(CommonConstants.NON_EMP_ERROR,
						searchProjectLengthCheckConstants);
				request.setAttribute("errorMsg", errorMessage);
			}

			// 案件更新画面DTOの案件情報IDを基に案件更新画面参加チームDTOリストを取得する
			ArrayList<ProjectEntryTeamDTO> updatePjtEntryTeamList = new ArrayList<ProjectEntryTeamDTO>();

			updatePjtEntryTeamList = showUpdatePjtLogic.acquireUpdatePjtTeam(updatePjtPageDto.getProjectInfoId());

			// 案件情報更新画面DTOと案件更新画面参加チームDTOリストをセッションに格納する
			session.setAttribute("updatePjtPageDto", updatePjtPageDto);
			session.setAttribute("updatePjtEntryTeamList", updatePjtEntryTeamList);

		} catch (Exception e) {
			// 例外が発生した場合、エラー画面に遷移する
			result = "/jsp/error.jsp";
			e.printStackTrace();
		}
		
		// 案件情報更新画面に遷移する
		RequestDispatcher disp = getServletContext().getRequestDispatcher(result);
		disp.forward(request, response);

	}
}
