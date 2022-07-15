package searchteamtest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.searchTeam.DeleteTeamAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class DeleteTeamTest {
	DeleteTeamAction deleteTeamAction = new DeleteTeamAction();

	@Test
	public void testDeleteTeam()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		successPattern();
		teamManagerErrorPattern();
		belongMemberErrorPattern();
		assignProjectErrorPattern();
		transferApplicationErrorPattern();

	}

	// 正常動作
	@Test
	public void successPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "T0000V0002");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteTeamAction.doPost(httpRequest, httpResponse);

	}

	@Test
	//エラー発生(チーム所属部長チェックでエラーが発生する)
	public void teamManagerErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "T0000V0003");

		String testUser = "Vuser3001";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", testUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteTeamAction.doPost(httpRequest, httpResponse);
	}

	@Test
	//エラー発生(所属メンバーチェックでエラーが発生する)
	public void belongMemberErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "T0000V0011");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteTeamAction.doPost(httpRequest, httpResponse);
	}
	@Test
	//エラー発生(案件配属チェックでエラーが発生する)
	public void assignProjectErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "T0000V0004");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteTeamAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	//エラー発生(移管申請チェックでエラーが発生する。)
	public void transferApplicationErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "T0000V0005");

		String testUser = "Vuser3001";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", testUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteTeamAction.doPost(httpRequest, httpResponse);
	}

}
