package menutest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.menu.LogoutAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class LogoutTest {

	LogoutAction logoutAction = new LogoutAction();

	@Test
	public void testLoginAction() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", "aaa");
		httpSession.setAttribute("companyId", "aaa");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		logoutAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		logoutAction.doGet(httpRequest, httpResponse);

	}
}
