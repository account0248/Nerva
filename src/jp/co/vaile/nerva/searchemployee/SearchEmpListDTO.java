package jp.co.vaile.nerva.searchemployee;

import java.util.List;

public class SearchEmpListDTO {
		//検索結果DTOListをDTOに格納する。
		List<SearchEmpDTO> searchEmpDTOList;

		public List<SearchEmpDTO> getSearchEmpDTOList() {
			return searchEmpDTOList;
		}
		public void setSearchEmpDTOList(List<SearchEmpDTO> searchEmpList) {
			this.searchEmpDTOList = searchEmpList;


	}
}

