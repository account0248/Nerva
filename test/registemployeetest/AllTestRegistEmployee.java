package registemployeetest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;

public class AllTestRegistEmployee {
	/**
	 * 従業員登録全テスト
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Test
	public void registEmployeeAllTest()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		RegistEmpCheckTest registEmpCheckTest = new RegistEmpCheckTest();
		RegistEmpTest registEmpTest = new RegistEmpTest();
		ReturnSkillInfoTest returnSkillInfoTest = new ReturnSkillInfoTest();

		registEmpCheckTest.allTest();
		registEmpTest.testRegistEmp();
		returnSkillInfoTest.testReturnSkillInfo();
	}
}
