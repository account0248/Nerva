package managementattedanceTest.UpdateWorkScheduleTest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllTestUpdateWorkSchedule {

	@Test
	@DisplayName("全てのテスト実行")
	public void allTestDisplay() throws ClassNotFoundException, ServletException, IOException, SQLException {
		UpdateWorkScheduleTest updateWorkScheduleTest = new UpdateWorkScheduleTest();
		
		updateWorkScheduleTest.insertPattern();
		updateWorkScheduleTest.updatePattern();
		updateWorkScheduleTest.shortMonthInsertPattern();
		updateWorkScheduleTest.shortMonthUpdatePattern();
		updateWorkScheduleTest.longMonthInsertPattern();
		updateWorkScheduleTest.longMonthUpdatePattern();
		updateWorkScheduleTest.firstAndLastInsertPattern();
		updateWorkScheduleTest.firstAndLastUpdatePattern();
		updateWorkScheduleTest.holidayAllInsertPattern();
		updateWorkScheduleTest.holidayAllUpdatePattern();
		updateWorkScheduleTest.noValueInsertPattern();
		updateWorkScheduleTest.noValueUpdatePattern();
		updateWorkScheduleTest.noInputStartTimePattern();
		updateWorkScheduleTest.noInputEndTimePattern();
		updateWorkScheduleTest.noInputRestTimePattern();
		updateWorkScheduleTest.noInputHolidayTimePattern();
		updateWorkScheduleTest.noInputLateTimePattern();
		updateWorkScheduleTest.remarksNormalPattern();
		updateWorkScheduleTest.remarksErrorPattern();
	}

}
