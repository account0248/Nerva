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
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class CreateTeamActionTest {
	CreateTeamAction createTeamAction = new CreateTeamAction();

	@Test
	@DisplayName("全項目入力正常動作")
	public void AllInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "T0000V0002");
		httpRequest.setParameter("teamName", "チーム名テスト");

		String adminUser = "admin";
		String companyTestId = "CP00000001";
		String companyTestCode="V";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyTestId);
		httpSession.setAttribute("companyCode", companyTestCode);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		createTeamAction.doPost(httpRequest, httpResponse);

		assertThat(httpRequest.getAttribute("teamId"), is("T0000V0002"));
	}

	@Test
	@DisplayName("未入力")
	public void EmptyPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "");
		httpRequest.setParameter("teamName", "");

		String adminUser = "admin";
		String companyTestId = "CP00000001";
		String companyTestCode="V";

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyTestId);
		httpSession.setAttribute("companyCode", companyTestCode);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		createTeamAction.doPost(httpRequest, httpResponse);
		String[] error = {"チームIDはかならず入力してください。","チーム名はかならず入力してください。"};
		List<String> errorList = new ArrayList<String>(Arrays.asList(error));
		for(int i = 0; i < ((List<String>) httpRequest.getAttribute("errorList")).size(); i++) {
			assertThat(((List<String>) httpRequest.getAttribute("errorList")).get(i), is(errorList.get(i)));
		}
		assertThat(httpRequest.getAttribute("teamId"), is(""));
		assertThat(httpRequest.getAttribute("teamName"), is(""));
	}


	@Test
	@DisplayName("半角英数字")
	public void ErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "あいうえお");
		httpRequest.setParameter("teamName", "チーム名テスト2");

		String adminUser = "admin";
		String companyTestId = "CP00000001";
		String companyTestCode="V";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyTestId);
		httpSession.setAttribute("companyCode", companyTestCode);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		createTeamAction.doPost(httpRequest, httpResponse);

		String[] error = {"チームIDは半角英数字で入力してください。"};
		List<String> errorList = new ArrayList<String>(Arrays.asList(error));
		for(int i = 0; i < ((List<String>) httpRequest.getAttribute("errorList")).size(); i++) {
			assertThat(((List<String>) httpRequest.getAttribute("errorList")).get(i), is(errorList.get(i)));
		}
		assertThat(httpRequest.getAttribute("teamId"), is("あいうえお"));
		assertThat(httpRequest.getAttribute("teamName"), is("チーム名テスト2"));
	}


	@Test
	@DisplayName("文字数")
	public void AllInprn() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "A123456789B");
		httpRequest.setParameter("teamName", "A123456789B123456789C");

		String adminUser = "admin";
		String companyTestId = "CP00000001";
		String companyTestCode="V";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyTestId);
		httpSession.setAttribute("companyCode", companyTestCode);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		createTeamAction.doPost(httpRequest, httpResponse);
		String[] error = {"チームIDは10文字で入力してください。","チーム名は20文字以内で入力してください。"};
		List<String> errorList = new ArrayList<String>(Arrays.asList(error));
		for(int i = 0; i < ((List<String>) httpRequest.getAttribute("errorList")).size(); i++) {
			assertThat(((List<String>) httpRequest.getAttribute("errorList")).get(i), is(errorList.get(i)));
		}
		assertThat(httpRequest.getAttribute("teamId"), is("A123456789B"));
		assertThat(httpRequest.getAttribute("teamName"), is("A123456789B123456789C"));
	}


	@Test
	@DisplayName("形式チェック")
	public void AllInprn2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "T0000J0000");
		httpRequest.setParameter("teamName", "チーム名テスト2");

		String adminUser = "admin";
		String companyTestId = "CP00000001";
		String companyTestCode="V";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyTestId);
		httpSession.setAttribute("companyCode", companyTestCode);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		createTeamAction.doPost(httpRequest, httpResponse);
		String[] error = {"チームIDは半角英数字で形式通りに入力してください。"};
		List<String> errorList = new ArrayList<String>(Arrays.asList(error));
		for(int i = 0; i < ((List<String>) httpRequest.getAttribute("errorList")).size(); i++) {
			assertThat(((List<String>) httpRequest.getAttribute("errorList")).get(i), is(errorList.get(i)));
		}

		assertThat(httpRequest.getAttribute("teamId"), is("T0000J0000"));
		assertThat(httpRequest.getAttribute("teamName"), is("チーム名テスト2"));
	}


	@Test
	@DisplayName("形式チェック2")
	public void AllInprn3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "TVVVVVVVV1");
		httpRequest.setParameter("teamName", "チーム名テスト2");

		String adminUser = "admin";
		String companyTestId = "CP00000001";
		String companyTestCode="V";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyTestId);
		httpSession.setAttribute("companyCode", companyTestCode);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		createTeamAction.doPost(httpRequest, httpResponse);
		String[] error = {"チームIDは半角英数字で形式通りに入力してください。"};
		List<String> errorList = new ArrayList<String>(Arrays.asList(error));
		for(int i = 0; i < ((List<String>) httpRequest.getAttribute("errorList")).size(); i++) {
			assertThat(((List<String>) httpRequest.getAttribute("errorList")).get(i), is(errorList.get(i)));
		}
		assertThat(httpRequest.getAttribute("teamId"), is("TVVVVVVVV1"));
		assertThat(httpRequest.getAttribute("teamName"), is("チーム名テスト2"));
	}


	@Test
	@DisplayName("重複")
	public void Pattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("teamId", "T0000V0001");
		httpRequest.setParameter("teamName", "チーム名テスト2");

		String adminUser = "admin";
		String companyTestId = "CP00000001";
		String companyTestCode="V";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyTestId);
		httpSession.setAttribute("companyCode", companyTestCode);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		createTeamAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行するF
		createTeamAction.doPost(httpRequest, httpResponse);
		String[] error = {"チームIDは既に使われています。"};
		List<String> errorList = new ArrayList<String>(Arrays.asList(error));
		for(int i = 0; i < ((List<String>) httpRequest.getAttribute("errorList")).size(); i++) {
			assertThat(((List<String>) httpRequest.getAttribute("errorList")).get(i), is(errorList.get(i)));
		}
		assertThat(httpRequest.getAttribute("teamId"), is("T0000V0001"));
		assertThat(httpRequest.getAttribute("teamName"), is("チーム名テスト2"));
	}
}