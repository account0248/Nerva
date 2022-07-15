package commonprocesstest;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.EmpIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.MailFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PhoneNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PostalCodeFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ProjectIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TeamIDFormatCheck;

public class UnitTestFormatCheck {
	FormatCheck empIDFormatCheck = new EmpIDFormatCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
	FormatCheck mailFormatCheck = new MailFormatCheck();
	FormatCheck phoneNumFormatCheck = new PhoneNumFormatCheck();
	FormatCheck postalCodeFormatCheck = new PostalCodeFormatCheck();
	FormatCheck projectIDFormatCheck = new ProjectIDFormatCheck();
	FormatCheck teamIDFormatCheck = new TeamIDFormatCheck();

	@Test
	public void unitTestFormatCheck() {
		System.out.println("EmpIDFormatCheck.isCorrectFormat");
		System.out.println(empIDFormatCheck.isCorrectFormat("L000000001"));
		System.out.println(empIDFormatCheck.isCorrectFormat("0000000000"));
		System.out.println(empIDFormatCheck.isCorrectFormat("aaaaaaaaaa"));
		System.out.println(empIDFormatCheck.isCorrectFormat("L00000000000000000001"));
		System.out.println("");
		System.out.println(empIDFormatCheck.isCorrectFormat("V000000001"));
		System.out.println(empIDFormatCheck.isCorrectFormat("K000000001"));
		System.out.println(empIDFormatCheck.isCorrectFormat("O000000001"));
		System.out.println("");


		System.out.println("HarfWidthAlphanumFormatCheck.isCorrectFormat");
		System.out.println("");
		System.out.println(harfWidthAlphanumFormatCheck.isCorrectFormat("aaaAAA000"));
		System.out.println("");


		System.out.println(".isCorrectFormat");
		System.out.println("");
		System.out.println(harfWidthNumFormatCheck.isCorrectFormat("111"));
		System.out.println("");


		System.out.println(".isCorrectFormat");
		System.out.println("");
		System.out.println(mailFormatCheck.isCorrectFormat("as_c@ecd"));
		System.out.println("");


		System.out.println(".isCorrectFormat");
		System.out.println("");
		System.out.println(phoneNumFormatCheck.isCorrectFormat("000-0000-0000"));
		System.out.println("");


		System.out.println(".isCorrectFormat");
		System.out.println("");
		System.out.println(postalCodeFormatCheck.isCorrectFormat("000-0000"));
		System.out.println("");


		System.out.println(".isCorrectFormat");
		System.out.println("");
		System.out.println(projectIDFormatCheck.isCorrectFormat("P0000001"));
		System.out.println("");


		System.out.println(".isCorrectFormat");
		System.out.println("");
		System.out.println(teamIDFormatCheck.isCorrectFormat("T0000V0000"));
		System.out.println("");
	}
}
