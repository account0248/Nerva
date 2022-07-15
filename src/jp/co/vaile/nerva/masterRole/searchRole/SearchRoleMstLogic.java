package jp.co.vaile.nerva.masterRole.searchRole;

import java.sql.SQLException;
import java.util.List;

//担当検索処理を呼び出す。
public class SearchRoleMstLogic {
	/*担当検索情報DTOを引数に担当検索処理の呼び出しを行う。
	  担当検索結果DTOを戻り値にする。*/
	public  List<SearchRoleDTO>searchRoleMstLogic (SearchRoleDTO searchRoleDTO) throws ClassNotFoundException, SQLException{
		SearchRoleDAO searchRoleDAO =new SearchRoleDAO();
	
		return searchRoleDAO.searchRoleList(searchRoleDTO);
	}
}



