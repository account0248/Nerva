package searchteamtest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.searchTeam.SearchTeamSessionAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchTeamSessionTest {
	SearchTeamSessionAction searchTeamSessionAction=new SearchTeamSessionAction();

	@Test
	public void testSearchTeamSession()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

		successPattern();

	}
	// 正常動作
		@Test
		public void successPattern() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpSession httpSession = new MockHttpSession();
			MockHttpResponse httpResponse = new MockHttpResponse();

			// 疑似リクエストスコープに情報をセット
			httpRequest.setParameter("teamDetailName", "V000000001");

			String adminUser = "adminUser";
			String companyId = "CP00000001";
			// 疑似セッションスコープに情報をセット
			httpSession.setAttribute("userId", adminUser);
			httpSession.setAttribute("companyId", companyId);
			httpRequest.setSession(httpSession);

			// 実行クラスがservletのときはおまじないとして記述
			searchTeamSessionAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			searchTeamSessionAction.doPost(httpRequest, httpResponse);
		}
}
