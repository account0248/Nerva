package detailemployeetest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DetailEmployeeTest {

	@Test
	@DisplayName("従業員詳細全テスト")
	public void testDetailEmployee() throws ServletException, IOException {
		EmpDetailBackPageActionTest empDetailBackPageActionTest = new EmpDetailBackPageActionTest();
		EmpDetailInfoActionTest EmpDetailInfoActionTest = new EmpDetailInfoActionTest();

		empDetailBackPageActionTest.testEmpDetailBackPageAction();
		empDetailBackPageActionTest.testEmpDetailBackPageActionNull();
		
		EmpDetailInfoActionTest.testEmpDetailInfoActionParentEmpEnrolled();
		EmpDetailInfoActionTest.testEmpDetailInfoActionParentEmpUnEnrolled();
		EmpDetailInfoActionTest.testEmpDetailInfoActionSubsidiaryEmpEnrolled();
		EmpDetailInfoActionTest.testEmpDetailInfoActionSubsidiaryEmpUnEnrolled();
		EmpDetailInfoActionTest.testEmpDetailInfoActionUnset();
	}
}
