package teamdetailtest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.teamDetail.TeamDetailBackPageAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class TeamDetailBackPageActionTest {

	TeamDetailBackPageAction teamDetailBackPageAction = new TeamDetailBackPageAction();

	@Test
	@DisplayName("セッションがチームIDのみの場合")
	void testTeamIdPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String teamId = "T0000V0001";

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("teamId", teamId );
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		teamDetailBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		teamDetailBackPageAction.doGet(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("セッションが案件IDのみの場合")
	void testProjectIdPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String projectId = "P0000001" ;

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("projectId", projectId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		teamDetailBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		teamDetailBackPageAction.doGet(httpRequest, httpResponse);
	}

}