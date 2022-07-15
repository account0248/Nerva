package masterCompanyTest.updateCompanyTest;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterCompany.updateCompany.UpdateCompanyMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateCompanyMstTest {
	
UpdateCompanyMstAction updateCompanyMstAction = new UpdateCompanyMstAction();
	
	@Test
	@DisplayName("一件更新")
	public void testInputOneUpdateCompany() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[companyId]", "[\"CP00000001\"]");
		httpRequest.setParameter("json[companyName]", "[\"株式会社V2\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		updateCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateCompanyMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("全件更新")
	public void testInputAllUpdateCompany() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[companyId]", "[\"CP00000001\",\"CP00000002\",\"CP00000003\"]");
		httpRequest.setParameter("json[companyName]", "[\"株式会社V2\",\"K2株式会社\",\"株式会社O2\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		updateCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateCompanyMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("未入力テスト")
	public void errorCheckUpdateCompanyTest1() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[companyId]", "[\"CP00000001\"]");
		httpRequest.setParameter("json[companyName]", "[\"\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateCompanyMstAction.doPost(httpRequest, httpResponse);
	}
	
	
	@Test
	@DisplayName("境界値テスト　文字数10")
	public void errorCheckUpdateCompanyTest2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[companyId]", "[\"CP00000001\"]");
		httpRequest.setParameter("json[companyName]", "[\"株式会社V12345\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateCompanyMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("境界値テスト　文字数11")
	public void errorCheckUpdateCompanyTest3() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[companyId]", "[\"CP00000001\"]");
		httpRequest.setParameter("json[companyName]", "[\"株式会社V123456\"]");
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateCompanyMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("重複テスト(1件)")
	public void errorCheckUpdateCompanyTest4() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[companyId]", "[\"CP00000001\"]");
		httpRequest.setParameter("json[companyName]", "[\"K2株式会社\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateCompanyMstAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("重複テスト(複数件)")
	public void errorCheckUpdateCompanyTest5() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[companyId]", "[\"CP00000001\",\"CP00000002\"]");
		httpRequest.setParameter("json[companyName]", "[\"株式会社Z\",\"株式会社Z\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateCompanyMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateCompanyMstAction.doPost(httpRequest, httpResponse);
	}

}
