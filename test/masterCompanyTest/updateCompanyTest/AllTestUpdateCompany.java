package masterCompanyTest.updateCompanyTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class AllTestUpdateCompany {
	
	@Test
	@DisplayName("updateCompanyパッケージテストの一括実行")
    // エラーメッセージパターン
    public void AllTestCompany() throws ClassNotFoundException, ServletException, IOException {
		UpdateCompanyMstTest updateCompanyMstTest = new UpdateCompanyMstTest();
		updateCompanyMstTest.testInputOneUpdateCompany();
		updateCompanyMstTest.testInputAllUpdateCompany();
		updateCompanyMstTest.errorCheckUpdateCompanyTest1();
		updateCompanyMstTest.errorCheckUpdateCompanyTest2();
		updateCompanyMstTest.errorCheckUpdateCompanyTest3();
		updateCompanyMstTest.errorCheckUpdateCompanyTest4();
		updateCompanyMstTest.errorCheckUpdateCompanyTest5();
	}

}
