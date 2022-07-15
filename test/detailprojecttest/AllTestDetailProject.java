package detailprojecttest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class AllTestDetailProject {
	ShowProjectDetailActionTest showProjectDetailAcutionTest = new ShowProjectDetailActionTest();
	DetailProjectSessionActionTest detailProjectSessionActionTest = new DetailProjectSessionActionTest();
	DetailPjtBackPageActionTest detailPjtBackPageActionTest = new DetailPjtBackPageActionTest();


	@Test
	@DisplayName("detailProjectパッケージテストの一括実行")
	void testAllDetailProject() throws ServletException, IOException {
		showProjectDetailAcutionTest.testNormalPattern();
		showProjectDetailAcutionTest.testNotProjectPattern();
		showProjectDetailAcutionTest.testAbnormalPattern2();
		showProjectDetailAcutionTest.testProjectCompleteDateisNull();
		detailProjectSessionActionTest.testNormalPattern();
		detailProjectSessionActionTest.testNotAuthority();
		detailPjtBackPageActionTest.testNormalPattern();
		
	}

}
