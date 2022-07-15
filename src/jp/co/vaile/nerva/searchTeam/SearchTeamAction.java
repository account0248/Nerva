package jp.co.vaile.nerva.searchTeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchTeamAction extends HttpServlet {
	SearchTeamPageDTO searchTeamPageDTO;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		storeInputDTO(request);

		//3.入力情報を引数にエラーチェック処理を呼び出す。
		List<String> errorList;
		SearchTeamCheckLogic searchTeamCheckLogic = new SearchTeamCheckLogic();
		errorList = searchTeamCheckLogic.checkSearchTeam(searchTeamPageDTO);

		//3-1.エラーがあった場合、エラーメッセージを設定し、JSONで情報を返す。
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		if (errorList.size() != 0) {
			String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));

			out.print(errorListString);
			out.close();
			return;
			//3-2.エラーが無い場合、4に進む。
		}
		//4.入力情報を引数にチーム検索処理を呼び出す。
		HttpSession session = request.getSession();
		String loginBelongCompanyId = (String) session.getAttribute("companyId");
		List<SearchTeamDTO> searchTeamDTOList = new ArrayList<SearchTeamDTO>();
		SearchTeamLogic seachTeamLogic = new SearchTeamLogic();
		try {
			searchTeamDTOList = seachTeamLogic.searchTeam(searchTeamPageDTO, loginBelongCompanyId);
		} catch (ClassNotFoundException | SQLException e) {
			String errorListString = mapper.writeValueAsString("");
			out.print(errorListString);
			out.close();
			e.printStackTrace();
		}

		/*4-1.チーム検索結果DTOリストの中身がない場合、エラーメッセージを設定し、入力内容を保持したままJSONで情報を返し、処理を終了する。*/
		if (searchTeamDTOList.size() == 0) {
			errorList.add(seachTeamLogic.returnSearchTeamErrorMsg());
			String errorListString = mapper.writeValueAsString(storeErrorMap(errorList));			
			out.print(errorListString);
			out.close();
			return;
			//4-2.チーム検索結果DTOリストの中身がある場合、5に進む。
		}
		//5.チーム検索結果DTOリストをリクエストスコープに格納し、JSONで情報を返す。
		SearchTeamListDTO searchTeamListDTO = new SearchTeamListDTO();
		searchTeamListDTO.setSearchTeamList(searchTeamDTOList);
		String searchTeamDTOString = mapper.writeValueAsString(searchTeamListDTO);
		//2.チーム検索画面で記載された入力情報をセッションスコープに格納する。
		session.setAttribute("searchTeamPageDTO", searchTeamPageDTO);
		out.print(searchTeamDTOString);
		//クローズ
		out.close();
	}

	private Map<String, List<String>> storeErrorMap(List<String> errorList) {
		Map<String, List<String>> errorMap = new HashMap<String, List<String>>();
		errorMap.put("errorListDTO", errorList);
		return errorMap;
	}

	private void storeInputDTO(HttpServletRequest request) {
		//1.チーム検索画面で記載された入力情報を受け取る。
		String teamId = request.getParameter("json[teamId]");
		String teamName = request.getParameter("json[teamName]");
		String teamLeader = request.getParameter("json[teamLeader]");
		String projectId = request.getParameter("json[projectId]");
		String projectName = request.getParameter("json[projectName]");
		String orderSource = request.getParameter("json[orderSource]");

		searchTeamPageDTO = new SearchTeamPageDTO(teamId, teamName, teamLeader, projectId,
				projectName, orderSource);

	}

}
