package managementattedanceTest.DisplayWorkScheduleTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.managementattedance.displayWorkSchedule.DisplayHolidayPeriodAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

class DisplayHolidayPeriodTest {
	DisplayHolidayPeriodAction displayHolidayPeriodAction = new DisplayHolidayPeriodAction();
	
	@Test
	@DisplayName("正常動作")
	public void normalPattern() throws ServletException, IOException{
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 実行クラスがservletのときはおまじないとして記述
		displayHolidayPeriodAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayHolidayPeriodAction.doPost(httpRequest, httpResponse);
	}

}
