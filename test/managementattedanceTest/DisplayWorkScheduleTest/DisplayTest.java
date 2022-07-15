package managementattedanceTest.DisplayWorkScheduleTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.managementattedance.displayWorkSchedule.DisplayAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

class DisplayTest {
	DisplayAction displayAction = new DisplayAction();
	@Test
	@DisplayName("表示全テスト")
	public void allDisplayTest() throws ServletException, IOException{
		allSelectPattern();
		allSelectNoDataPattern();
		noEmployeeIdPattern();
	}

	@Test
	@DisplayName("正常動作[登録情報有り](正常に選択されている場合)")
	private void allSelectPattern() throws ServletException, IOException{
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "05");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		
		// 実行クラスがservletのときはおまじないとして記述
		displayAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("正常動作[登録情報なし](正常に選択されている場合)")
	private void allSelectNoDataPattern() throws ServletException, IOException{
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "04");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		
		// 実行クラスがservletのときはおまじないとして記述
		displayAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("異常動作(従業員IDを取得できていない場合)")
	private void noEmployeeIdPattern() throws ServletException, IOException{
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "05");
		httpRequest.setParameter("json[employeeId]", "");
		
		// 実行クラスがservletのときはおまじないとして記述
		displayAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		displayAction.doPost(httpRequest, httpResponse);
	}
}
