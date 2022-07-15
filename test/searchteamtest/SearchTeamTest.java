package searchteamtest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.searchTeam.SearchTeamAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchTeamTest {
	SearchTeamAction searchTeamAction = new SearchTeamAction();

	@Test
	public void testSearchTeam()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		allInputPattern();
		empInputPattern();
		inputErrorPattern();
		inputTeamIdLengthErrorPattern();
		companyIdSQLPattern();
		noSearchResultsPattern();
	}

	@Test
	// 全項目入力正常動作
	public void allInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "V000000001");
		httpRequest.setParameter("json[teamName]", "チーム名UT");
		httpRequest.setParameter("json[teamLeader]", "リーダー名UT");
		httpRequest.setParameter("json[projectId]", "V0000001");
		httpRequest.setParameter("json[projectName]", "案件名UT");
		httpRequest.setParameter("json[orderSource]", "発注元名UT");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchTeamAction.doPost(httpRequest, httpResponse);
	}

	@Test
	// 全項目未入力正常動作
	public void empInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[teamLeader]", "");
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[orderSource]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchTeamAction.doPost(httpRequest, httpResponse);
	}

	@Test
	//入力チェックエラー発生
	public void inputErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "1");
		httpRequest.setParameter("json[teamName]", "あああああああああああああああああああああ");//21文字
		httpRequest.setParameter("json[teamLeader]", "あああああああああああああああああああああ");//21文字
		httpRequest.setParameter("json[projectId]", "1");//8文字以外
		httpRequest.setParameter("json[projectName]",
				"あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");//257文字
		httpRequest.setParameter("json[orderSource]", "あああああああああああああああああああああ");//21文字
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchTeamAction.doPost(httpRequest, httpResponse);
	}

	@Test
	//入力チェックエラー発生(チームID・担当案件IDの指定形式エラーチェック)
	public void inputTeamIdLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "ああああああああああ");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[teamLeader]", "");
		httpRequest.setParameter("json[projectId]", "ああああああああ");
		httpRequest.setParameter("json[projectName]","");
		httpRequest.setParameter("json[orderSource]", "");
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchTeamAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	//所属会社IDが株式会社V以外の場合
	public void companyIdSQLPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "V000000002");
		httpRequest.setParameter("json[teamName]", "チーム名UT");
		httpRequest.setParameter("json[teamLeader]", "リーダー名UT");
		httpRequest.setParameter("json[projectId]", "V0000001");
		httpRequest.setParameter("json[projectName]", "案件名UT");
		httpRequest.setParameter("json[orderSource]", "発注元名UT");

		String adminUser = "adminUser";
		String companyId = "CP00000003";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchTeamAction.doPost(httpRequest, httpResponse);
	}

	@Test
	//検索結果が0件の場合
	public void noSearchResultsPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[teamId]", "V000000100");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[teamLeader]", "");
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[orderSource]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchTeamAction.doPost(httpRequest, httpResponse);
	}

}
