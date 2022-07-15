package masterIndustryTest.searchIndustryTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestIndustry {
	@Test
	@DisplayName("searchIndustryパッケージテストの一括実行")
    public void allTestIndustry() throws ClassNotFoundException, ServletException, IOException {
		ShowIndustryMstTest showIndustryMstTest = new ShowIndustryMstTest();
		SearchIndustryMstTest searchIndustryMstTest = new SearchIndustryMstTest();
		
		//業種マスタメンテナンス画面に遷移する
		showIndustryMstTest.masterIndustryPattern();
		
		// 検索機能全テストパターン
		searchIndustryMstTest.idfiltereSearchIndustry();
		searchIndustryMstTest.namefiltereSearchIndustry();
		searchIndustryMstTest.filtereSearchIndustry();
		searchIndustryMstTest.allSearchIndustry();
		searchIndustryMstTest.searchIndustryResultCheck();
		searchIndustryMstTest.industryIdLengthCheck();
		searchIndustryMstTest.industryIdLengthCheck2();
		searchIndustryMstTest.industryIdLengthCheck3();
		searchIndustryMstTest.industryIdMatchCheck();
		searchIndustryMstTest.industryNameLengthCheck2();
		searchIndustryMstTest.industryNameLengthCheck3();
		searchIndustryMstTest.industryNamePartCheck();
	}
}