package masterCompanyTest.searchCompanyTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestSearchCompany {

	@Test
	@DisplayName("searchCompanyパッケージテストの一括実行")
	public void allTestCompany() throws ClassNotFoundException, ServletException, IOException {
		ShowCompanyMstActionTest  showCompanyMstTest = new ShowCompanyMstActionTest ();
		SearchCompanyMstTest searchCompanyMstTest = new SearchCompanyMstTest();
		//所属会社マスタメンテナンス画面に遷移する
		showCompanyMstTest.masterCompanyPattern();
		showCompanyMstTest.masterChildCompanyPattern();
		// 検索機能全テストパターン
		searchCompanyMstTest.idfiltereSearchCompany();
		searchCompanyMstTest.namefiltereSearchCompany();
		searchCompanyMstTest.partialMatcheSearchCompany();
		searchCompanyMstTest.groupfiltereParentSearchCompany();
		searchCompanyMstTest.groupfiltereChildSearchCompany();
		searchCompanyMstTest.codefiltereSearchCompany();
		searchCompanyMstTest.codefiltereSearchCompany2();
		searchCompanyMstTest.codefiltereSearchCompany3();
		searchCompanyMstTest.filtereSearchCompany();
		searchCompanyMstTest.allSearchCompany();
		searchCompanyMstTest.allSearchChildCompany();
		searchCompanyMstTest.searchCompanyResultCheck();
		searchCompanyMstTest.companyIdLengthCheck();
		searchCompanyMstTest.companyIdLengthCheck2();
		searchCompanyMstTest.companyIdLengthCheck3();
		searchCompanyMstTest.companyIdMatchCheck();
		searchCompanyMstTest.companyNameLengthCheck();
		searchCompanyMstTest.companyNameLengthCheck1();
	}

}
