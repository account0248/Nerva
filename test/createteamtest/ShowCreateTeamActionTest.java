package createteamtest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.createTeam.CreateTeamAction;
import jp.co.vaile.nerva.createTeam.ShowCreateTeamAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowCreateTeamActionTest {
	ShowCreateTeamAction showCreateTeamAction = new ShowCreateTeamAction();


	@Test
	@DisplayName("正常画面表示処理(親会社)")
	public void testNormalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", "admin");
		httpSession.setAttribute("userName", "管理者");
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showCreateTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showCreateTeamAction.doGet(httpRequest, httpResponse);
		
		assertThat(httpSession.getAttribute("companyName"), is("株式会社V"));
		assertThat(httpSession.getAttribute("companyCode"), is("V"));
		assertThat(httpRequest.getAttribute("userName"), is("管理者"));
	}
	
	@Test
	@DisplayName("正常画面表示処理(子会社)")
	public void testNormalPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", "adminK");
		httpSession.setAttribute("userName", "管理者K");
		httpSession.setAttribute("companyPrivilege", "0");
		httpSession.setAttribute("companyId", "CP00000002");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showCreateTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showCreateTeamAction.doGet(httpRequest, httpResponse);
		
		assertThat(httpSession.getAttribute("companyName"), is("K株式会社"));
		assertThat(httpSession.getAttribute("companyCode"), is("K"));
		assertThat(httpRequest.getAttribute("userName"), is("管理者K"));
	}
	
	@Test
	@DisplayName("チーム作成エラー発生時のセッションの確認")
	public void testErrorCheckSession() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", "admin");
		httpSession.setAttribute("userName", "管理者");
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showCreateTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。(チーム作成画面表示処理)
		showCreateTeamAction.doGet(httpRequest, httpResponse);
		
		CreateTeamAction createTeamAction = new CreateTeamAction();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "");
		httpRequest.setParameter("teamName", "");
		
		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。(チーム作成処理)
		createTeamAction.doPost(httpRequest, httpResponse);
		String[] error = {"チームIDはかならず入力してください。","チーム名はかならず入力してください。"};
		List<String> errorList = new ArrayList<String>(Arrays.asList(error));
		for(int i = 0; i < ((List<String>) httpRequest.getAttribute("errorList")).size(); i++) {
			assertThat(((List<String>) httpRequest.getAttribute("errorList")).get(i), is(errorList.get(i)));
		}

		assertThat(httpSession.getAttribute("companyName"), is("株式会社V"));
		assertThat(httpSession.getAttribute("companyCode"), is("V"));
		assertThat(httpRequest.getAttribute("userName"), is("管理者"));
	}
}
