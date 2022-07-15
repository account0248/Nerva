package registprojecttest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.registproject.RegistProjectBackPageAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

class RegistProjectBackPageActionTest {
	RegistProjectBackPageAction RegistProjectBackPageAction = new RegistProjectBackPageAction();

	@Test
	@DisplayName("正常動作")
	void showNomalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 実行クラスがservletのときはおまじないとして記述
		RegistProjectBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		RegistProjectBackPageAction.doPost(httpRequest, httpResponse);
	}

}