package managementattedanceTest.DisplayWorkScheduleTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.managementattedance.displayWorkSchedule.DisplayCheckAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

class DisplayCheckTest {
	DisplayCheckAction displayCheckAction = new DisplayCheckAction();
	
	@Test
	@DisplayName("全テスト")
	public void allDisplayCheckTest() throws ServletException, IOException {
		allSelectPattern();
		noSelectYearMonthPattern();
		noSelectYearPattern();
		noSelectMonthPattern();
	}
	
	@Test
	@DisplayName("正常動作(正常に選択されている)")
	private void allSelectPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "05");
		
		// 実行クラスがservletのときはおまじないとして記述
		displayCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラー動作(年度・月が未選択)")
	private void noSelectYearMonthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "");
		httpRequest.setParameter("json[month]", "");
		
		// 実行クラスがservletのときはおまじないとして記述
		displayCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラー動作(月が未選択)")
	private void noSelectMonthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "");
		
		// 実行クラスがservletのときはおまじないとして記述
		displayCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラー動作(年度が未選択)")
	private void noSelectYearPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "");
		httpRequest.setParameter("json[month]", "05");
		
		// 実行クラスがservletのときはおまじないとして記述
		displayCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayCheckAction.doPost(httpRequest, httpResponse);
	}
}
