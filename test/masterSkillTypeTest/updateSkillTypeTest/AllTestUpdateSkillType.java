package masterSkillTypeTest.updateSkillTypeTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestUpdateSkillType {
	@Test
	@DisplayName("updateSkillTypeパッケージテストの一括実行")
    // エラーメッセージパターン
    public void AllTestSkillType() throws ClassNotFoundException, ServletException, IOException {
		UpdateSkillTypeMstTest updateSkillTypeMstTest = new UpdateSkillTypeMstTest();
		updateSkillTypeMstTest.testNoChangeUpdateSkillType();
		updateSkillTypeMstTest.testInputOneUpdateSkillType();
		updateSkillTypeMstTest.testInputAllUpdateSkillType();
		updateSkillTypeMstTest.errorCheckUpdateSkillTypeTest1();
		updateSkillTypeMstTest.errorCheckUpdateSkillTypeTest2();
		updateSkillTypeMstTest.errorCheckUpdateSkillTypeTest3();
		updateSkillTypeMstTest.errorCheckUpdateSkillTypeTest4();
		updateSkillTypeMstTest.testInputYearsUpdateSkillType();
		updateSkillTypeMstTest.testInputDateOfAcquisitionUpdateSkillType();
		updateSkillTypeMstTest.errorCheckUpdateSkillTypeTest5();
		updateSkillTypeMstTest.testSearchPartUpdateSkillType();
		updateSkillTypeMstTest.errorCheckUpdateSkillTypeTest6();
	}
}
