package detailprojecttest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.detailProject.DetailProjectSessionAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class DetailProjectSessionActionTest {
	DetailProjectSessionAction detailProjectSessionAction = new DetailProjectSessionAction();



	@Test
	@DisplayName("正常系テスト")
	void testNormalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String teamId = "T0000V0001";
		String companyId = "CP00000001";
		String companyPrivilege = "1";
		// 疑似セッションスコープに情報をセット
		httpRequest.setParameter("teamId", teamId);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		detailProjectSessionAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		detailProjectSessionAction.doPost(httpRequest, httpResponse);
		
		assertThat((String) httpRequest.getAttribute("teamId"),is(teamId));
		
	}
	
	@Test
	@DisplayName("チーム参照権限無しテスト")
	void testNotAuthority() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String teamId = "T0000V0001";
		String companyId = "CP00000004";
		String companyPrivilege = "0";
		
		// 疑似セッションスコープに情報をセット
		httpRequest.setParameter("teamId", teamId);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		detailProjectSessionAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		detailProjectSessionAction.doPost(httpRequest, httpResponse);
		
		
		assertThat(((ArrayList<String>)httpRequest.getAttribute("errorMsgList")).get(0),is("このチームの詳細情報は閲覧できません。"));
	}
}
