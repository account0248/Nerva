package masterDepartmentTest.updateDepartmentTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestUpdateDepartment {
	@Test
	@DisplayName("updateDepartmentTestパッケージの一括実行")
    // エラーメッセージパターン
    public void AllTestDepartment() throws ClassNotFoundException, ServletException, IOException {
		UpdateDepartmentMstTest updateDepartmentMstTest = new UpdateDepartmentMstTest();
		updateDepartmentMstTest.testInputOneUpdateDepartment();
		updateDepartmentMstTest.testInputAllUpdateDepartment();
		updateDepartmentMstTest.errorCheckUpdateDepartmentTest1();
		updateDepartmentMstTest.errorCheckUpdateDepartmentTest2();
		updateDepartmentMstTest.errorCheckUpdateDepartmentTest3();
		updateDepartmentMstTest.errorCheckUpdateDepartmentTest4();
		updateDepartmentMstTest.errorCheckUpdateDepartmentTest5();
	}
}
