package searchteamtest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.searchTeam.ShowSearchTeamAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowSearchTeamTest {
	ShowSearchTeamAction showSearchTeamAction = new ShowSearchTeamAction();

	@Test
	public void testShowSearchTeam()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		menuPattern();
		detailTeamPattern();
	}

	// 正常動作(メニューから遷移)
	@Test
	public void menuPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String teamDetailPage = null;
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamDetailPage", teamDetailPage);
		
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showSearchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showSearchTeamAction.doGet(httpRequest, httpResponse);
	}

	// 正常動作(チーム詳細から遷移)
	@Test
	public void detailTeamPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String teamDetailPage = "teamDetailPage";
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamDetailPage", teamDetailPage);

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showSearchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showSearchTeamAction.doGet(httpRequest, httpResponse);
	}

}
