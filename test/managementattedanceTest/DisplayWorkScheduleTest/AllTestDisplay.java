package managementattedanceTest.DisplayWorkScheduleTest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AllTestDisplay {

	@Test
	@DisplayName("全てのテスト実行")
	public void allTestDisplay() throws ClassNotFoundException, ServletException, IOException, SQLException {
		DisplayCheckTest displayCheckTest = new DisplayCheckTest();
		DisplayHolidayPeriodTest displayHolidayPeriodTest = new DisplayHolidayPeriodTest();
		DisplayTest displayTest = new DisplayTest();
		ManagementAttendancePageTest managementAttendancePageTest = new ManagementAttendancePageTest();
		ManagementAttendanceBackPageTest managementAttendanceBackPageTest = new ManagementAttendanceBackPageTest();
		
		displayCheckTest.allDisplayCheckTest();
		displayHolidayPeriodTest.normalPattern();
		displayTest.allDisplayTest();
		managementAttendancePageTest.managementAttendancePageTransition();
		managementAttendanceBackPageTest.managementAttendanceBackPageTransition();
	}

}
