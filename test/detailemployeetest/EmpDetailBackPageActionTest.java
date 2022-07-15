package detailemployeetest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.detailEmployee.EmpDetailBackPageAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class EmpDetailBackPageActionTest {
	EmpDetailBackPageAction empDetailBackPageAction = new EmpDetailBackPageAction();

	@Test
	@DisplayName("前画面情報がある場合")
	public void testEmpDetailBackPageAction() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//前画面情報をセッションにセット（前画面情報がある場合）
		String teamId = "T0000V0001";
		String teamDetailPage = "teamDetailPage";
		httpSession.setAttribute("teamId", teamId);
		httpSession.setAttribute("teamDetailPage", teamDetailPage);
		
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		empDetailBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		empDetailBackPageAction.doGet(httpRequest, httpResponse);


	}

	@Test
	@DisplayName("前画面情報がない場合")
	public void testEmpDetailBackPageActionNull() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		//前画面情報をセッションにセット（前画面情報がnullの場合）
		String teamDetailPage = null;
		httpSession.setAttribute("teamDetailPage", teamDetailPage);
		
		// 実行クラスがservletのときはおまじないとして記述
		empDetailBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		empDetailBackPageAction.doGet(httpRequest, httpResponse);

	}
}
