package masterIndustryTest.updateIndustryTest;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterIndustry.updateIndustry.UpdateIndustryMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateIndustryMstTest {
	UpdateIndustryMstAction updateIndustryMstAction = new UpdateIndustryMstAction();
	
	@Test
	@DisplayName("一件更新")
	public void testInputOneUpdateIndustry() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[industryId]", "[\"I000000001\"]");
		httpRequest.setParameter("json[industryName]", "[\"金融2\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		updateIndustryMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateIndustryMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("全件更新")
	public void testInputAllUpdateIndustry() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[industryId]", "[\"I000000001\",\"I000000002\",\"I000000003\",\"I000000004\",\"I000000005\"]");
		httpRequest.setParameter("json[industryName]", "[\"金融3\",\"医療2\",\"教育2\",\"情報通信2\",\"製造2\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		updateIndustryMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateIndustryMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("未入力テスト")
	public void errorCheckUpdateIndustryTest1() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[industryId]", "[\"I000000001\"]");
		httpRequest.setParameter("json[industryName]", "[\"\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateIndustryMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateIndustryMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("境界値テスト　文字数10")
	public void errorCheckUpdateIndustryTest3() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[industryId]", "[\"I000000001\"]");
		httpRequest.setParameter("json[industryName]", "[\"A123456789\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateIndustryMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateIndustryMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("境界値テスト　文字数11")
	public void errorCheckUpdateIndustryTest4() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[industryId]", "[\"I000000001\"]");
		httpRequest.setParameter("json[industryName]", "[\"A1234567890\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateIndustryMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateIndustryMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("重複テスト")
	public void errorCheckUpdateIndustryTest5() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[industryId]", "[\"I000000001\"]");
		httpRequest.setParameter("json[industryName]", "[\"医療2\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateIndustryMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateIndustryMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("重複テスト（複数）")
	public void errorCheckUpdateIndustryTest6() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[industryId]", "[\"I000000001\",\"I000000002\"]");
		httpRequest.setParameter("json[industryName]", "[\"農業\",\"農業\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateIndustryMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateIndustryMstAction.doPost(httpRequest, httpResponse);
	}
}
