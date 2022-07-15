package jp.co.vaile.nerva.masterIndustry.searchIndustry;

import java.sql.SQLException;
import java.util.List;

//業種検索処理を呼び出す。
public class SearchIndustryMstLogic {
	/*業種検索情報DTOを引数に業種検索処理の呼び出しを行う。
	  業種検索結果DTOを戻り値にする。*/
	public  List<SearchIndustryDTO>searchIndustryMstLogic (SearchIndustryDTO searchIndustryDTO) throws ClassNotFoundException, SQLException{
		SearchIndustryDAO searchIndustryDAO =new SearchIndustryDAO();

		return searchIndustryDAO.searchIndustryList(searchIndustryDTO);
	}
}



