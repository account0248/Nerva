package jp.co.vaile.nerva.masterCompany.searchCompany;
import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.sql.SQLException;
import java.util.List;

public class SearchCompanyMstLogic {
	/**
	 * 親会社の所属会社検索処理を呼び出す。
	 * @param searchCompanyDTO
	 * @return searchCompanyDTOList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public  List<SearchCompanyDTO>searchCompanyMstLogic (SearchCompanyDTO searchCompanyDTO,String companyPrivilege,String userId) throws ClassNotFoundException, SQLException{
		//1 真偽値を格納する変数を作成する。
		Boolean searchCompanyPrivilege = null;
		String parentCompanyValue=String.valueOf(PARENT_COMPANY_VALUE);
		String subCompanyValue=String.valueOf(SUBCOMPANY_VALUE);
		SearchCompanyDAO searchCompanyDAO =new SearchCompanyDAO();
		//2 companyPrivilegeで条件分岐を行う。
		if (searchCompanyDTO.getCompanyGroup().equals(parentCompanyValue)) {
			searchCompanyPrivilege = true;
		}
		else if(searchCompanyDTO.getCompanyGroup().equals(subCompanyValue)) {
			searchCompanyPrivilege = false;
		}
		return searchCompanyDAO.searchCompanyList(searchCompanyDTO,searchCompanyPrivilege,companyPrivilege,userId);
	}

}



