package updateemployeetest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.updateEmployee.DeleteEmpAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class DeleteEmpActionTest {
	DeleteEmpAction deleteEmpAction = new DeleteEmpAction();

	@Test
	@DisplayName("正常動作")
	public void NomalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//パラメータにログインユーザID・従業員IDを設定
		String employeeId = "V000000036";
		String loginUserId = "adminUser";

		//従業員IDを疑似リクエストスコープにセット
		httpRequest.setParameter("employeeId", employeeId);
		
		//ログインユーザIDを疑似セッションスコープにセット
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		deleteEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteEmpAction.doPost(httpRequest, httpResponse);
	}


	@Test
	@DisplayName("エラーパターン")
	public void ErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//パラメータにログインユーザID・従業員IDを設定
		String employeeId = "V000000100";
		String loginUserId = "adminUser";

		//従業員IDを疑似リクエストスコープにセット
		httpRequest.setParameter("employeeId", employeeId);
		
		//ログインユーザIDを疑似セッションスコープにセット
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		deleteEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteEmpAction.doPost(httpRequest, httpResponse);
	}
}
