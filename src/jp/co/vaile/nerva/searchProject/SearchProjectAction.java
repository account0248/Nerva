	package jp.co.vaile.nerva.searchProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/searchProject" })
public class SearchProjectAction extends HttpServlet {

	String move;

	/**
	 * 検索ボタンを押下した時の処理。案件検索処理を呼び出す。
	 * @param request 
	 * @param response 
	 * @throws ServletException
	 * @throws IOException
	 **/
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		response.setContentType("application/json;charset=UTF-8");

		//json型の値を画面から受け取る
		String projectId = request.getParameter("json[projectId]");

		String projectName = request.getParameter("json[projectName]");

		String resposibleName = request.getParameter("json[resposibleName]");

		String contratorId = request.getParameter("json[contratorName]");

		String clientName = request.getParameter("json[clientName]");
		if(clientName=="") {
			clientName=null;
		}

		String inductryId = request.getParameter("json[inductryName]");

		String totalTeamsProjectToString = request.getParameter("json[totalTeamsProject]");

		String totalEmpProjectToString = request.getParameter("json[totalEmpProject]");

		//ログイン者の所属会社ID、所属会社グループ権限（セッション）取得
		HttpSession session = request.getSession();
		String loginUserCompanyId = (String) session.getAttribute("companyId");
		String companyPrivilege = (String)session.getAttribute("companyPrivilege");

		//入力情報をDTOに格納する
		SearchProjectPageDTO searchProjectPageDTO = new SearchProjectPageDTO();

		searchProjectPageDTO = setSearchProjectPage(projectId, projectName, resposibleName, contratorId,
				clientName, inductryId, totalTeamsProjectToString, totalEmpProjectToString);

		//入力情報のDTOをセッションに格納する
		session.setAttribute("projectSearchPageDTO", searchProjectPageDTO);

		//エラーリストのチェックをする
		List<String> errorList = new ArrayList<String>();
		SearchProjectCheckLogic searchProjectCheckLogic = new SearchProjectCheckLogic();
		
		try {
			errorList = searchProjectCheckLogic.checkSearchProject(searchProjectPageDTO);

			ArrayList<SearchProjectToStringDTO> searchProjectToStringDTOArr = new ArrayList<SearchProjectToStringDTO>();

			//入力内容チェックのエラーメッセージの中身が無いとき、検索を実行する
			if (errorList.size() == 0) {

				ArrayList<SearchProjectDTO> searchProjectDTOArr = new ArrayList<SearchProjectDTO>();

				SearchProjectLogic searchProjectLogic = new SearchProjectLogic();

				searchProjectDTOArr = searchProjectLogic.searchProjectLogic(searchProjectPageDTO);


				//検索結果がないとき、エラーメッセージを追加
				if (searchProjectDTOArr.size() == 0) {
					
					errorList = searchProjectCheckLogic.checkSearchProjectAll();
				}

				//検索処理を呼び出す
				searchProjectToStringDTOArr = searchProjectLogic.limitViewingProjectByPrivilegeToStringLogic(searchProjectDTOArr,
						loginUserCompanyId,companyPrivilege);
			}

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String searchProjectJsonDTOArr="";
			String searchProjectErrorJsonList="";

			if(errorList.size() != 0) {

				searchProjectErrorJsonList = mapper.writeValueAsString(setSearchProjectErrorMsgJson(errorList));
				out.write(searchProjectErrorJsonList);
				out.close();

			}else {

				searchProjectJsonDTOArr = mapper.writeValueAsString(setSearchProjectJson(searchProjectToStringDTOArr));
				out.write(searchProjectJsonDTOArr);
				out.close();
			}
			
		} catch (ClassNotFoundException  | SQLException e) {

			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			String searchProjectErrorJsonList="";

			searchProjectErrorJsonList = mapper.writeValueAsString("");
			out.write(searchProjectErrorJsonList);
			out.close();
			e.printStackTrace();

		}

	}

	/**
	 * 移管申請情報をDTOのインスタンスに格納する
	 * @param projectId
	 * @param projectName
	 * @param resposibleName
	 * @param contratorId
	 * @param clientName
	 * @param inductryId
	 * @param totalTeamsProject
	 * @param totalEmpProject
	 * @return
	 */
	public SearchProjectPageDTO setSearchProjectPage(String projectId, String projectName, String resposibleName,
			String contratorId, String clientName, String inductryId, String totalTeamsProject, String totalEmpProject) {

		SearchProjectPageDTO searchProjectPageDTO = new SearchProjectPageDTO();

		searchProjectPageDTO.setProjectId(projectId);

		searchProjectPageDTO.setProjectName(projectName);

		searchProjectPageDTO.setResponsibleName(resposibleName);

		searchProjectPageDTO.setContratorId(contratorId);

		searchProjectPageDTO.setClientName(clientName);

		searchProjectPageDTO.setInductryId(inductryId);

		searchProjectPageDTO.setTotalTeamsProject(totalTeamsProject);

		searchProjectPageDTO.setTotalEmpProject(totalEmpProject);

		return searchProjectPageDTO;
	}

	/**
	 * Json文字列に変換する
	 * @param searchProjectToStringDTOArr
	 * @return
	 */
	public SearchProjectJsonDTO setSearchProjectJson(ArrayList<SearchProjectToStringDTO> searchProjectToStringDTOArr) {

		SearchProjectJsonDTO searchProjectJsonDTO = new SearchProjectJsonDTO();

		searchProjectJsonDTO.setSearchProjectJsonDTO(searchProjectToStringDTOArr);

		return searchProjectJsonDTO;
	}
/**
 * エラーメッセージをJson文字列に変換する
 * @param errorList
 * @return
 */
	public SearchProjectErrorMsgJsonDTO setSearchProjectErrorMsgJson(List<String> errorList) {

		SearchProjectErrorMsgJsonDTO searchProjectErrorMsgJsonDTO = new SearchProjectErrorMsgJsonDTO();

		searchProjectErrorMsgJsonDTO.setSearchProjectErrorMsgJsonDTO(errorList);

		return searchProjectErrorMsgJsonDTO;
	}
}