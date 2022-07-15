package jp.co.vaile.nerva.masterContract.searchContract;

import java.sql.SQLException;
import java.util.List;

//契約形態検索処理を呼び出す。
public class SearchContractMstLogic {
	/*契約形態検索情報DTOを引数に契約形態検索処理の呼び出しを行う。
	  契約形態検索結果DTOを戻り値にする。*/
	public  List<SearchContractDTO>searchContractMstLogic (SearchContractDTO searchContractDTO) throws ClassNotFoundException, SQLException{
		SearchContractDAO searchContractDAO =new SearchContractDAO();
	
		return searchContractDAO.searchContractList(searchContractDTO);
	}
}



