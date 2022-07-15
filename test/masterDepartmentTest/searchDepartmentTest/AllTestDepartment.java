package masterDepartmentTest.searchDepartmentTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestDepartment {
	@Test
	@DisplayName("searchDepartmentTesパッケージの一括実行")
    public void allTestDepartment() throws ClassNotFoundException, ServletException, IOException {
		ShowDepartmentMstTest showDepartmentMstTest = new ShowDepartmentMstTest();
		SearchDepartmentMstTest searchDepartmentMstTest = new SearchDepartmentMstTest();
		
		//所属組織マスタメンテナンス画面に遷移する
		showDepartmentMstTest.masterDepartmentPattern();
		
		// 検索機能全テストパターン
		searchDepartmentMstTest.idfiltereSearchDepartment();
		searchDepartmentMstTest.namefiltereSearchDepartment1();
		searchDepartmentMstTest.filtereSearchDepartment();
		searchDepartmentMstTest.allSearchDepartment();
		searchDepartmentMstTest.searchDepartmentResultCheck();
		searchDepartmentMstTest.departmentIdLengthCheck();
		searchDepartmentMstTest.departmentIdLengthCheck2();
		searchDepartmentMstTest.departmentIdLengthCheck3();
		searchDepartmentMstTest.departmentIdMatchCheck();
		searchDepartmentMstTest.departmentNameLengthCheck();
		searchDepartmentMstTest.departmentNameLengthCheck2();
		searchDepartmentMstTest.namefiltereSearchDepartment2();
		searchDepartmentMstTest.noResultCheck();
	}
}