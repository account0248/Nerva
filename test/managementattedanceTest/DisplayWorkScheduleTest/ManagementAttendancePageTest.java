package managementattedanceTest.DisplayWorkScheduleTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.managementattedance.ManagementAttendancePageAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

class ManagementAttendancePageTest {
	ManagementAttendancePageAction managementAttendancePageAction = new ManagementAttendancePageAction();
	
	@Test
	@DisplayName("正常動作(従業員検索画面から遷移)")
	public void managementAttendancePageTransition() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("employeeId", "V000000001");
		httpRequest.setParameter("employeeName", "テスト１");
		
		// 実行クラスがservletのときはおまじないとして記述
		managementAttendancePageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		managementAttendancePageAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"), is("V000000001"));
		assertThat(httpRequest.getAttribute("employeeName"), is("テスト１"));

	}

}
