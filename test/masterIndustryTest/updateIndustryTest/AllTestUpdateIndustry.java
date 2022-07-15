package masterIndustryTest.updateIndustryTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestUpdateIndustry {
	@Test
	@DisplayName("updateIndustryパッケージテストの一括実行")
    // エラーメッセージパターン
    public void AllTestIndustry() throws ClassNotFoundException, ServletException, IOException {
		UpdateIndustryMstTest updateIndustryMstTest = new UpdateIndustryMstTest();
		updateIndustryMstTest.testInputOneUpdateIndustry();
		updateIndustryMstTest.testInputAllUpdateIndustry();
		updateIndustryMstTest.errorCheckUpdateIndustryTest1();
		updateIndustryMstTest.errorCheckUpdateIndustryTest3();
		updateIndustryMstTest.errorCheckUpdateIndustryTest4();
		updateIndustryMstTest.errorCheckUpdateIndustryTest5();
		updateIndustryMstTest.errorCheckUpdateIndustryTest6();

	}
}
