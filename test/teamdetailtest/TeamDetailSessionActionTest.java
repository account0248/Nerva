package teamdetailtest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.teamDetail.TeamDetailSessionAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class TeamDetailSessionActionTest {
	TeamDetailSessionAction TeamDetailSessionAction = new TeamDetailSessionAction();

	@Test
	@DisplayName("正常系テスト")
	void testNormalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//パラメータに従業員IDを設定
		String empId = "V000000001";
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setAttribute("empId", empId);

		// 疑似セッションスコープに情報をセット
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		TeamDetailSessionAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		TeamDetailSessionAction.doPost(httpRequest, httpResponse);
		
		assertThat(httpSession.getAttribute("teamDetailPage"), is("teamDetailPage"));
		assertThat(httpRequest.getAttribute("empId"), is("V000000001"));
	}
}
