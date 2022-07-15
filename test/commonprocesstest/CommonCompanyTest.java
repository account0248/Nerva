package commonprocesstest;

import java.sql.SQLException;

import org.junit.Test;

import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCompany;

public class CommonCompanyTest {
	SearchCompany searchCompany=new SearchCompany();
	@Test	 
	public void commonCompanyTest() throws ClassNotFoundException, SQLException {
		String adminUserId="admin";
		String generalUserId="general";
		String companyPrivilege="1";
		String companyChildPrivilege="0";
		String companyId="CP00000001";
		searchCompany.belongCompanyCode(companyId);
		searchCompany.searchCompanyAllCode(companyPrivilege,  adminUserId);
		searchCompany.searchCompanyAllCode(companyChildPrivilege,  generalUserId);
		searchCompany.searchCompanyCode(adminUserId);
	}
}
