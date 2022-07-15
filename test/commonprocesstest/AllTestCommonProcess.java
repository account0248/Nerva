package commonprocesstest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class AllTestCommonProcess {

	@Test
	public void commonProcessAllTest()
			throws ClassNotFoundException, SQLException, ServletException, IOException, ParseException {
		DBConnection.closeConnection();
		DBConnection.getConnection();
		DBConnection.closeConnection();
		DBConnection.closeConnection();
		DBConnection.getConnection();

		ErrorMsgTest errorMsgTest = new ErrorMsgTest();
		ExistCheckTest existCheckTest = new ExistCheckTest();
		FormatCheckTest formatCheckTest = new FormatCheckTest();
		LengthCheckTest lengthCheckTest = new LengthCheckTest();
		OtherTest otherTest = new OtherTest();
		errorMsgTest.returnErrorMsgTest();
		existCheckTest.existCheckTest();
		formatCheckTest.formatCheckTest();
		lengthCheckTest.lengthCheckTest();
		otherTest.otherTest();
		// ver2.0追加クラス
		CommonSkillTypeTest commonSkillTypeTest = new CommonSkillTypeTest();
		CommonCompanyTest commonCompanyTest = new CommonCompanyTest();
		UnitTestFormatCheck unitTestFormatCheck = new UnitTestFormatCheck();
		UnitTestLengthCheck unitTestLengthCheck = new UnitTestLengthCheck();
		// ver2.0追加クラス
		otherTest.checkDuplicateDaoTest();
		otherTest.checkMasterResultTest();
		otherTest.hashPassword();
		commonSkillTypeTest.commonSkillTypeTest();
		commonCompanyTest.commonCompanyTest();
		unitTestFormatCheck.unitTestFormatCheck();
		unitTestLengthCheck.unitTestLengthCheck();
	}
}
