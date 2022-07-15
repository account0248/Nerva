package updateemployeetest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.updateEmployee.ReturnTeamInfoAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ReturnTeamInfoActionTest {
	ReturnTeamInfoAction returnTeamInfoAction = new ReturnTeamInfoAction();
	
	@Test
	@DisplayName("正常動作")
	void NomalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//パラメータにログインユーザIDを設定
		String loginUserId = "adminUser";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		returnTeamInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		returnTeamInfoAction.doPost(httpRequest, httpResponse);
	}
}
