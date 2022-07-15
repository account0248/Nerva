package searchEmployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;

import jp.co.vaile.nerva.searchemployee.SearchEmpSessionAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

public class SearchEmpSessionActionTest {
	@Test
	public void SearchEmpSessionActionTest() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("employeeId", "V000000001");

		SearchEmpSessionAction searchEmpSessionAction = new SearchEmpSessionAction();

		// 実行クラスがservletのときはおまじないとして記述
		searchEmpSessionAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpSessionAction.doPost(httpRequest, httpResponse);
		
		assertThat(httpRequest.getAttribute("employeeId"),is("V000000001"));
	}
}
