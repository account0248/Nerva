package jp.co.vaile.nerva.updateProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;

public class CheckUpdateProjectAction extends HttpServlet {

	/**
	 * 案件情報更新画面の更新ボタン押下後、更新処理を呼び出し案件詳細情報画面に遷移する
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		List<String> errorMsgList = new ArrayList<>();

		try {

			// json型の値を画面から受け取る
			String projectStartDate = request.getParameter("json[projectStartDate]");
			String projectCompleteDate = request.getParameter("json[projectCompleteDate]");
			String[] entryTeamAssignStartDate = request.getParameterValues("json[assignStartDate][]");
			String[] entryTeamAssignCompleteDate = request.getParameterValues("json[assignCompleteDate][]");
			String[] addTeamId = request.getParameterValues("json[addTeamId][]");
			String[] addTeamAssignStartDate = request.getParameterValues("json[addTeamAssignStartDate][]");
			String[] addTeamAssignCompleteDate = request.getParameterValues("json[addTeamAssignCompleteDate][]");

			// セッションスコープから案件情報更新画面DTOを取得する
			ProjectDetailPageDTO updatePjtPageDto = (ProjectDetailPageDTO) session.getAttribute("updatePjtPageDto");

			// 案件情報IDを取得
			int projectInfoId = updatePjtPageDto.getProjectInfoId();

			// セッションスコープからログインユーザの所属会社IDを取得
			String loginBelongCompanyId = (String) session.getAttribute("companyId");

			// 入力情報のエラーをチェックする
			UpdatePjtInputCheckLogic updatePjtInputCheckLogic = new UpdatePjtInputCheckLogic();

			errorMsgList = updatePjtInputCheckLogic.checkUpdateProject(projectStartDate, projectCompleteDate,
					entryTeamAssignStartDate, entryTeamAssignCompleteDate, addTeamId, addTeamAssignStartDate,
					addTeamAssignCompleteDate, projectInfoId, loginBelongCompanyId);

			// エラーメッセージがない場合、入力情報のデータをセッションに格納する。
			if (errorMsgList.size() == 0 || errorMsgList.get(0).equals("")) {

				String resistUserId = (String) session.getAttribute("userId");

				// 更新する案件情報
				ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
				projectInfoDTO.setProjectInfoId(projectInfoId);
				projectInfoDTO.setRegistUser(resistUserId);
				projectInfoDTO.setProjectStartDate(projectStartDate);
				projectInfoDTO.setProjectCompleteDate(projectCompleteDate);
				session.setAttribute("projectInfoDTO", projectInfoDTO);

				// 既に参加しているチーム情報
				session.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

				// 新たに追加されるチーム情報
				ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
				if (addTeamId != null) {
					for (int i = 0; i < addTeamId.length; i++) {
						if (addTeamId[i] == "" || addTeamId[i] == null) {
						} else {
							UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
							updatePjtAddTeamDTO.setTeamId(addTeamId[i]);
							updatePjtAddTeamDTO.setRegistUsertId(resistUserId);
							updatePjtAddTeamDTO.setAssignStartDate(addTeamAssignStartDate[i]);
							updatePjtAddTeamDTO.setAssignCompleteDate(addTeamAssignCompleteDate[i]);
							updatePjtAddTeamDTO.setProjectInfoId(projectInfoId);
							updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
						}
					}
					session.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);
				}
			}

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();

			String updateProjectErrorJsonList = mapper.writeValueAsString(setUpdateProjectErrorMsgJson(errorMsgList));
			out.write(updateProjectErrorJsonList);
			out.close();

		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// 例外が発生した場合、エラー画面に遷移する
			e.printStackTrace();

			ObjectMapper mapper = new ObjectMapper();
			String ajaxExceptionJson = "";
			PrintWriter out = response.getWriter();

			ajaxExceptionJson = mapper.writeValueAsString("");
			out.print(ajaxExceptionJson);
			out.close();

		}

	}

	public UpdateProjectErrorMsgJsonDTO setUpdateProjectErrorMsgJson(List<String> errorMsgList) {

		UpdateProjectErrorMsgJsonDTO updateProjectErrorMsgJsonDTO = new UpdateProjectErrorMsgJsonDTO();

		updateProjectErrorMsgJsonDTO.setUpdateProjectErrorMsgJsonDTO(errorMsgList);

		return updateProjectErrorMsgJsonDTO;
	}

}
