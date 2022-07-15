package jp.co.vaile.nerva.searchTeam;

import java.util.List;

public class SearchTeamListDTO {
	private List<SearchTeamDTO> searchTeamDTOList;

	public List<SearchTeamDTO> getSearchTeamList() {
		return searchTeamDTOList;
	}

	public void setSearchTeamList(List<SearchTeamDTO> searchTeamList) {
		this.searchTeamDTOList = searchTeamList;
	}
}
