package jp.co.vaile.nerva.searchProject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchProjectToStringDAO {

	public static ArrayList<SearchProjectToStringDTO> searchProjectToString(
			ArrayList<SearchProjectDTO> searchProjectDTOArr) {

		ArrayList<SearchProjectToStringDTO> searchProjectToStringDTOArr = new ArrayList<SearchProjectToStringDTO>();

		for (int i = 0; i < searchProjectDTOArr.size(); i++) {

			SearchProjectToStringDTO searchProjectToStringDTO = new SearchProjectToStringDTO();

			SearchProjectDTO searchProjectDTO = searchProjectDTOArr.get(i);
			searchProjectToStringDTO = setSearchProjectToString(searchProjectDTO);

			searchProjectToStringDTOArr.add(searchProjectToStringDTO);
		}

		return searchProjectToStringDTOArr;

	}

	public static SearchProjectToStringDTO setSearchProjectToString(SearchProjectDTO searchProjectDTO) {

		SearchProjectToStringDTO searchProjectToStringDTO = new SearchProjectToStringDTO();

		searchProjectToStringDTO.setProjectId(searchProjectDTO.getProjectId());

		searchProjectToStringDTO.setProjectName(searchProjectDTO.getProjectName());

		searchProjectToStringDTO.setResponsibleName(searchProjectDTO.getResponsibleName());

		searchProjectToStringDTO.setContratorName(searchProjectDTO.getContratorName());

		searchProjectToStringDTO.setClientName(searchProjectDTO.getClientName());

		searchProjectToStringDTO.setInductryName(searchProjectDTO.getInductryName());

		searchProjectToStringDTO.setTotalTeamsProject(searchProjectDTO.getTotalTeamsProject());

		searchProjectToStringDTO.setTotalEmpProject(searchProjectDTO.getTotalEmpProject());

		Date projectStartDate = searchProjectDTO.getProjectStartDate();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		//Date型をString型に変換
		String projectStartDateToString = dateFormat.format(projectStartDate);

		searchProjectToStringDTO.setProjectStartDate(projectStartDateToString);

		Date projectCompleteDate = searchProjectDTO.getProjectCompleteDate();

		String projectCompleteDateToString ="";

		if(projectCompleteDate==null) {

			//String
			searchProjectToStringDTO.setProjectCompleteDate(projectCompleteDateToString);

		}else {

		//Date型をString型に変換
		 projectCompleteDateToString = dateFormat.format(projectCompleteDate);

		searchProjectToStringDTO.setProjectCompleteDate(projectCompleteDateToString);
		}
		

		return searchProjectToStringDTO;

	}
}
