
package masterDepartmentTest.updateDepartmentTest;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterDepartment.updateDepartment.UpdateDepartmentMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateDepartmentMstTest {
	UpdateDepartmentMstAction updateDepartmentMstAction = new UpdateDepartmentMstAction();
	
	@Test
	@DisplayName("一件更新")
	public void testInputOneUpdateDepartment() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[departmentId]", "[\"D000000001\"]");
		httpRequest.setParameter("json[departmentName]", "[\"管理\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		updateDepartmentMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateDepartmentMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("複数件更新")
	public void testInputAllUpdateDepartment() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[departmentId]", "[\"D000000001\",\"D000000003\",\"D000000005\"]");
		httpRequest.setParameter("json[departmentName]", "[\"管理本部\",\"システム\",\"情報システム\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		updateDepartmentMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateDepartmentMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("未入力テスト")
	public void errorCheckUpdateDepartmentTest1() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[departmentId]", "[\"D000000001\"]");
		httpRequest.setParameter("json[departmentName]", "[\"\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateDepartmentMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateDepartmentMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("境界値テスト　文字数10")
	public void errorCheckUpdateDepartmentTest2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[departmentId]", "[\"D000000002\"]");
		httpRequest.setParameter("json[departmentName]", "[\"A123456789\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateDepartmentMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateDepartmentMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("境界値テスト　文字数11")
	public void errorCheckUpdateDepartmentTest3() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[departmentId]", "[\"D000000002\"]");
		httpRequest.setParameter("json[departmentName]", "[\"A1234567890\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateDepartmentMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateDepartmentMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("重複テスト(1件)")
	public void errorCheckUpdateDepartmentTest4() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[departmentId]", "[\"D000000002\"]");
		httpRequest.setParameter("json[departmentName]", "[\"管理本部\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateDepartmentMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateDepartmentMstAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("重複テスト(複数件)")
	public void errorCheckUpdateDepartmentTest5() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[departmentId]", "[\"D000000002\",\"D000000004\"]");
		httpRequest.setParameter("json[departmentName]", "[\"人事部\",\"人事部\"]");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateDepartmentMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateDepartmentMstAction.doPost(httpRequest, httpResponse);
	}

}
