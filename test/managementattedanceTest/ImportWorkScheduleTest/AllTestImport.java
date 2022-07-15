package managementattedanceTest.ImportWorkScheduleTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllTestImport {

	
	@Test
	@DisplayName("全てのテスト実行")
	public void allTestImport() throws ServletException, IOException {
		ImportCheckTest importCheckTest = new ImportCheckTest();
		ImportExcelTest importExcelTest = new ImportExcelTest();
		ImportTextTest importTextTest = new ImportTextTest();
		importCheckTest.excelFileNamePattern();
		importCheckTest.textFileNamePattern();
		importCheckTest.noFileNamePattern();
		importCheckTest.incompatibleFileNamePattern();
		
		importExcelTest.excelImportNormalPattern();
		importExcelTest.fileNameErrorPattern();
		importExcelTest.canNotOpenFilePattern();
		importExcelTest.noStartTimeFormatPattern();
		importExcelTest.noEndTimeFormatPattern();
		importExcelTest.noRestTimeFormatPattern();
		importExcelTest.noHolidayWorkingTimeFormatPattern();
		importExcelTest.holidayDayInjusticePattern();
		importExcelTest.holidayTimeInjusticePattern();
		importExcelTest.noLateTimeFormatPattern();
		importExcelTest.noPasswordExcelImportPattern();
		
		importTextTest.textFileImportNormalPattern();
		importTextTest.textFileHolidayImportNormalPattern();
		importTextTest.requiredHolidayDaysErrorPattern();
		importTextTest.noneHolidayDaysErrorPattern();
		importTextTest.provisionHolidayDaysErrorPattern();
		importTextTest.requiredHolidayTimeErrorPattern();
		importTextTest.noneHolidayTimeErrorPattern();
		importTextTest.provisionHolidayTimeErrorPattern();
		importTextTest.requiredLateTimeErrorPattern();
		importTextTest.noneLateTimeErrorPattern();
		importTextTest.anyLateTimeErrorPattern();
		importTextTest.fileNameErrorPattern();
	}
	

}
