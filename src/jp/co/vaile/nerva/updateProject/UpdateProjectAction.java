package jp.co.vaile.nerva.updateProject;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;

public class UpdateProjectAction extends HttpServlet {

	/**
	 * 案件情報更新画面の更新ボタン押下後、更新処理を呼び出し案件詳細情報画面に遷移する
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UpdateProjectCheckLogic updateProjectCheckLogic = new UpdateProjectCheckLogic();
		String move = "";
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		// セッションスコープから更新する案件情報を取得する
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO = (ProjectInfoDTO) session.getAttribute("projectInfoDTO");
		session.removeAttribute("projectInfoDTO");

		// セッションスコープから既に参加しているチーム情報とその配属完了日を取得する
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		entryTeamList = (ArrayList<ProjectEntryTeamDTO>) session.getAttribute("updatePjtEntryTeamList");
		String[] entryTeamAssignCompleteDate = (String[]) session.getAttribute("entryTeamAssignCompleteDate");
		session.removeAttribute("entryTeamAssignCompleteDate");

		// セッションスコープから新たに追加されるチーム情報を取得する
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		updateAddTeamDTOArr = (ArrayList<UpdatePjtAddTeamDTO>) session.getAttribute("addTeamDTOArr");
		session.removeAttribute("addTeamDTOArr");

		// セッションスコープからログインユーザの所属会社IDとユーザIDを取得
		String loginBelongCompanyId = (String) session.getAttribute("companyId");
		String registUser = (String) session.getAttribute("userId");

		try {

			// 更新されたか確認するフラグ
			boolean updateCheck = updateProjectCheckLogic.updateProjectCheck(projectInfoDTO,
					entryTeamAssignCompleteDate, updateAddTeamDTOArr, loginBelongCompanyId, entryTeamList, registUser);

			if (updateCheck == true) {
				// 更新された場合の画面遷移(詳細画面）
				move = "ShowProjectDetailAction";
				session.removeAttribute("projectSearchPageDTO");

			} else {
				// 更新されなかった場合

				// 案件情報から案件情報詳細IDを取得する
				int projectInfoId = projectInfoDTO.getProjectInfoId();
				// 案件更新の排他性チェック
				boolean checkUpdateProjectFlg = updateProjectCheckLogic.checkUpdateProject(projectInfoDTO,
						projectInfoId, entryTeamList);

				ArrayList<String> errorMsgList = new ArrayList<String>();
				UpdatePjtInputCheckLogic updatePjtInputCheckLogic = new UpdatePjtInputCheckLogic();
				
				if (checkUpdateProjectFlg == true) {
					// 更新するものがない場合
					errorMsgList = updatePjtInputCheckLogic.updateProjectNonError();

					move = "ShowProjectDetailAction";
					request.setAttribute("errorMsgList", errorMsgList);

				} else if (checkUpdateProjectFlg == false) {
					// 更新失敗の場合の画面遷移(検索画面)

					errorMsgList = updatePjtInputCheckLogic.updateProjectError();
					String errorMsg = errorMsgList.get(0);

					move = "ShowProjectAction";
					request.setAttribute("errorMsg", errorMsg);

				}
				
			}

		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// 例外が発生した場合、エラー画面に遷移する
			e.printStackTrace();
			move = "error.jsp";
		}

		request.getRequestDispatcher(move).forward(request, response);

	}

}
