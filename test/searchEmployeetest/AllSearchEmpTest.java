package searchEmployeetest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

public class AllSearchEmpTest {
	@Test
    // エラーメッセージパターン
    public void AllTest() throws ClassNotFoundException, ServletException, IOException {
		
		ShowSearchEmpActionTest showSearchEmpActiontst = new ShowSearchEmpActionTest();
		showSearchEmpActiontst.testShowSearchEmp();
		showSearchEmpActiontst.testShowSearchEmpAction();
		showSearchEmpActiontst.testShowChildSearchEmpAction();

		SearchEmpActionTest searchEmpActionTest = new SearchEmpActionTest();
		searchEmpActionTest.testInputSearch();
		searchEmpActionTest.testInputSearchEmpAction();
		searchEmpActionTest.testSearchParamErrorCheck1();
		searchEmpActionTest.testSearchParamErrorCheck2();
		searchEmpActionTest.testSearchParamErrorCheck3();
		searchEmpActionTest.testInputSearchEmpAction2();
		searchEmpActionTest.testSearchParamErrorCheck4();
		searchEmpActionTest.testSelectCompany1();
		searchEmpActionTest.testSelectCompany2();
		searchEmpActionTest.testSelectCompany3();
		searchEmpActionTest.testSelectCompany4();
		searchEmpActionTest.testSearchEmployeeName();
		searchEmpActionTest.testSearchEmployeeName2();
		searchEmpActionTest.testSearchProject();
		searchEmpActionTest.testSearchProject2();
		searchEmpActionTest.testSearchTeam();
		searchEmpActionTest.testSearchTeam2();
		searchEmpActionTest.testSearchManager();
		searchEmpActionTest.testSearchManager2();
		searchEmpActionTest.testSearchSkill();
		searchEmpActionTest.testSearchSkill2();
		searchEmpActionTest.testSearchNoResult();
		searchEmpActionTest.testSelectCompanyError();

		SearchEmpSessionActionTest searchEmpSessionActionTest = new SearchEmpSessionActionTest();
		searchEmpSessionActionTest.SearchEmpSessionActionTest();
		
		RegistPageActionTest registPageAction = new RegistPageActionTest();
		registPageAction.testRegistEmpPageAction();
		registPageAction.testRegistChildEmpPageAction();

	}
}
