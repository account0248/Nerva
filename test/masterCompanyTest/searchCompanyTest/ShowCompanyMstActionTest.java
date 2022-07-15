package masterCompanyTest.searchCompanyTest;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterCompany.searchCompany.ShowCompanyMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowCompanyMstActionTest {

	ShowCompanyMstAction showCompanyMstAction = new ShowCompanyMstAction(); 

	@Test
	@DisplayName("正常動作(親会社の管理者がマスタ管理画面から遷移)")
	public void masterCompanyPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showCompanyMstAction.doGet(httpRequest, httpResponse);

		Character[] COMPANY_CODE1 = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		Character[] COMPANY_CODE2 = {'B','C','D','E','F','G','H','I','J','K','L','N','O','P','Q','R','S','T','U','V','W','X','Y'};
		List<Character> companyAllCodeList= new ArrayList<Character>(Arrays.asList(COMPANY_CODE1));
		List<Character> companyCodeList= new ArrayList<Character>(Arrays.asList(COMPANY_CODE2));

		assertThat((String)httpRequest.getAttribute("parentCompanyValue"), is("1"));
		assertThat((String)httpRequest.getAttribute("subCompanyValue"), is("0"));
		assertThat(httpRequest.getAttribute("companyParent"), is("親会社"));
		assertThat(httpRequest.getAttribute("companyChild"), is("子会社"));
		for(int i = 0; i < ((List<Character>) httpRequest.getAttribute("companyCodeList")).size(); i++) {
			assertThat(((List<Character>) httpRequest.getAttribute("companyCodeList")).get(i), is(companyCodeList.get(i)));
		}
		for(int i = 0; i < ((List<Character>) httpRequest.getAttribute("companyAllCodeList")).size(); i++) {
			assertThat(((List<Character>) httpRequest.getAttribute("companyAllCodeList")).get(i), is(companyAllCodeList.get(i)));
		}


		// サーブレット破棄
		showCompanyMstAction .destroy();

	}

	@Test
	@DisplayName("正常動作(子会社の管理者がマスタ管理画面から遷移)")
	public void masterChildCompanyPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String testCompanyPrivilege="0";
		String loginUserId="general";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showCompanyMstAction.init(new MockServletConfig());
		// 実際にテスト対象のメソッドを実行する。
		showCompanyMstAction.doGet(httpRequest, httpResponse);
		Character[] COMPANY_CODE2 = {'B','C','D','E','F','G','H','I','J','K','L','N','O','P','Q','R','S','T','U','V','W','X','Y'};
		List<Character> companyCodeList= new ArrayList<Character>(Arrays.asList(COMPANY_CODE2));

		httpRequest.getAttribute("parentCompanyValue");

		assertThat((String)httpRequest.getAttribute("subCompanyValue"), is("0"));
		assertThat((String)httpRequest.getAttribute("companyChild"), is("子会社"));
		assertThat(httpRequest.getAttribute("companyBelongCode"), is("M"));
		for(int i = 0; i < ((List<Character>) httpRequest.getAttribute("companyCodeList")).size(); i++) {
			assertThat(((List<Character>) httpRequest.getAttribute("companyCodeList")).get(i), is(companyCodeList.get(i)));
		}
		// サーブレット破棄
		showCompanyMstAction .destroy();

	}


}
