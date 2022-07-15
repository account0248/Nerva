package jp.co.vaile.nerva.masterSkillType.searchSkillType;

import java.util.List;

public class SearchSkillTypeListDTO {
	//検索結果DTOListをDTOに格納する。
	private List<SearchSkillTypeDTO> searchSkillTypeDTOList;

	public List<SearchSkillTypeDTO> getSearchSkillTypeDTOList() {
		return searchSkillTypeDTOList;
	}

	public void setSearchSkillTypeDTOList(List<SearchSkillTypeDTO> searchSkillTypeDTOList) {
		this.searchSkillTypeDTOList = searchSkillTypeDTOList;
	}

}
