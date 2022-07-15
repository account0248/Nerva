package teansferApplicationtest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;

import jp.co.vaile.nerva.transferApplication.ShowTransferApplicationAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowTransferApplicationActionTest {
	ShowTransferApplicationAction showTransferApplicationAction = new ShowTransferApplicationAction();
	MockHttpSession httpSession = new MockHttpSession();
	@Test
	public void transferApplicationActionApprove() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		String LoginUser = "V610000000";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", LoginUser);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showTransferApplicationAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showTransferApplicationAction.doGet(httpRequest, httpResponse);
}
	
	@Test
	public void transferApplicationActionApprove2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		String LoginUser = "V610000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", LoginUser);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showTransferApplicationAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showTransferApplicationAction.doGet(httpRequest, httpResponse);
}
}
