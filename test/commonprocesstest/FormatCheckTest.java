package commonprocesstest;

import org.junit.Test;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.CompanyIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ContractIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DateFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DayFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DepartmentIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.EmpIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ExcelFileNameFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumAtSignFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumUpperLetterFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.IndustryIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.MailFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PhoneNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PostIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PostalCodeFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ProjectIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.RoleIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.SkillTypeIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TeamIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TextFileNameFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TimeFormatCheck;

public class FormatCheckTest {

	@Test
	public void formatCheckTest(){
		FormatCheck empIDFormatCheck = new EmpIDFormatCheck();
		FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
		FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
		FormatCheck mailFormatCheck = new MailFormatCheck();
		FormatCheck phoneNumFormatCheck = new PhoneNumFormatCheck();
		FormatCheck postalCodeFormatCheck = new PostalCodeFormatCheck();
		FormatCheck projectIDFormatCheck = new ProjectIDFormatCheck();
		FormatCheck teamIDFormatCheck = new TeamIDFormatCheck();

		//ver2.0改修
		FormatCheck timeFormatCheck = new TimeFormatCheck();
		FormatCheck dayFormatCheck = new DayFormatCheck();
		FormatCheck harfWidthNumUpperLetterFormatCheck = new HarfWidthNumUpperLetterFormatCheck();
		FormatCheck companyIDFormatCheck = new CompanyIDFormatCheck();
		FormatCheck contractIDFormatCheck = new ContractIDFormatCheck();
		FormatCheck dateFormatCheck = new DateFormatCheck();
		FormatCheck departmentIDFormatCheck = new DepartmentIDFormatCheck();
		FormatCheck industryIDFormatCheck = new IndustryIDFormatCheck();
		FormatCheck postIDFormatCheck = new PostIDFormatCheck();
		FormatCheck roleIDFormatCheck = new RoleIDFormatCheck();
		FormatCheck skillTypeIDFormatCheck = new SkillTypeIDFormatCheck();
		FormatCheck harfWidthAlphanumAtSignFormatCheck = new HarfWidthAlphanumAtSignFormatCheck();
		FormatCheck excelFileNameFormatCheck = new ExcelFileNameFormatCheck();
		FormatCheck textFileNameFormatCheck = new TextFileNameFormatCheck();



		empIDFormatCheck.isCorrectFormat("V000000001");
		empIDFormatCheck.isCorrectFormat("V0000000000000000000000000000000001");
		harfWidthAlphanumFormatCheck.isCorrectFormat("aaaAAA000");
		harfWidthNumFormatCheck.isCorrectFormat("111");
		mailFormatCheck.isCorrectFormat("as_c@ecd");
		phoneNumFormatCheck.isCorrectFormat("000-0000-0000");
		postalCodeFormatCheck.isCorrectFormat("000-0000");
		projectIDFormatCheck.isCorrectFormat("P0000001");
		teamIDFormatCheck.isCorrectFormat("T0000V0000");
		//ver2.0改修
		timeFormatCheck.isCorrectFormat("V00000001");
		dayFormatCheck.isCorrectFormat("V000000001");
		harfWidthNumUpperLetterFormatCheck.isCorrectFormat("V000000001");
		companyIDFormatCheck.isCorrectFormat("V000000001");
		contractIDFormatCheck.isCorrectFormat("V000000001");
		dateFormatCheck.isCorrectFormat("V000000001");
		departmentIDFormatCheck.isCorrectFormat("V000000001");
		industryIDFormatCheck.isCorrectFormat("V000000001");
		postIDFormatCheck.isCorrectFormat("V000000001");
		roleIDFormatCheck.isCorrectFormat("V000000001");
		skillTypeIDFormatCheck.isCorrectFormat("V000000001");
		harfWidthAlphanumAtSignFormatCheck.isCorrectFormat("V000000001");
		excelFileNameFormatCheck.isCorrectFormat("V000000001");
		textFileNameFormatCheck.isCorrectFormat("V000000001");

	}
}
