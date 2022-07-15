package updateProjecttest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Ignore;
import org.junit.Test;

import jp.co.vaile.nerva.updateProject.ShowUpdateProjectAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowUpdateProjectTest {

	ShowUpdateProjectAction showUpdateProjectAction = new ShowUpdateProjectAction();
	
	@Test
	//@Ignore
	public void allcheckUpdateProjectTest() throws ServletException, IOException {
		checkUpdateProjectTest1();
		checkUpdateProjectTest2();
		checkUpdateProjectTest3();
	}

	@Test
	@Ignore
	// 正常系
	public void checkUpdateProjectTest1() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		httpSession.setAttribute("projectId", "P0000060");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showUpdateProjectAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@Ignore
	// 正常系
	public void checkUpdateProjectTest2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		httpSession.setAttribute("projectId", "P00000061");
		// ログインユーザ情報
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showUpdateProjectAction.doPost(httpRequest, httpResponse);
	}
	

	@Test
	@Ignore
	// 異常系
	public void checkUpdateProjectTest3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		httpSession.setAttribute("projectId", "P0000999");
		// ログインユーザ情報
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

}
