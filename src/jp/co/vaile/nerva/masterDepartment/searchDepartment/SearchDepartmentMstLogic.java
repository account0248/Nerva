package jp.co.vaile.nerva.masterDepartment.searchDepartment;

import java.sql.SQLException;
import java.util.List;

//所属組織検索処理を呼び出す。
public class SearchDepartmentMstLogic {
	/**
	 * 所属組織検索情報DTOを引数に所属組織検索処理の呼び出しを行う。所属組織検索結果DTOを戻り値にする。
	 * 
	 * @param searchDepartmentDTO
	 * @return searchDepartmentDTOList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchDepartmentDTO> searchDepartmentMstLogic(SearchDepartmentDTO searchDepartmentDTO)
			throws ClassNotFoundException, SQLException {
		SearchDepartmentDAO searchDepartmentDAO = new SearchDepartmentDAO();

		return searchDepartmentDAO.searchDepartmentList(searchDepartmentDTO);
	}
}
