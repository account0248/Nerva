package jp.co.vaile.nerva.searchemployee;

import java.sql.SQLException;
import java.util.List;

public class SearchEmpLogic {
	//従業員検索入力情報DTOを引数に従業員検索処理の呼び出しを行う。
	//従業員検索結果DTOを戻り値にする。

	public List<SearchEmpDTO> serchEmpLogic(SearchEmpPageDTO serchEmpPageDTO) throws ClassNotFoundException, SQLException{
		SearchEmpDAO serchEmpDAO =new SearchEmpDAO();
	return serchEmpDAO.serchEmpDTOList(serchEmpPageDTO);
	}
}
