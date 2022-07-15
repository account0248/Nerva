package teansferApplicationtest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;

import jp.co.vaile.nerva.transferApplication.TransferApplicationAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;


public class TransferApplicationActionTest {
	
	TransferApplicationAction transferApplicationAction = new TransferApplicationAction();
	MockHttpSession httpSession = new MockHttpSession();
	
	@Test
	public void transferApplicationActionApprove() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//ユーザーID == パスワードとなるようにパラメータを設定
		httpRequest.setParameter("transferEmpName", "3");
		httpRequest.setParameter("applicationButton", "approve");
		
		String LoginUser = "V610000000";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", LoginUser);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		transferApplicationAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		transferApplicationAction.doGet(httpRequest, httpResponse);
}
		@Test
		public void transferApplicationAction() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();
			//ユーザーID == パスワードとなるようにパラメータを設定
			httpRequest.setParameter("transferEmpName", "3");
			httpRequest.setParameter("applicationButton", "reject");
			
			String LoginUser = "V610000000";
			
			// 疑似セッションスコープに情報をセット
			httpSession.setAttribute("userId", LoginUser);
			httpRequest.setSession(httpSession);

			// 実行クラスがservletのときはおまじないとして記述
			transferApplicationAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			transferApplicationAction.doGet(httpRequest, httpResponse);
	}
		@Test
		public void transferApplicationAction2() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();
			//ユーザーID == パスワードとなるようにパラメータを設定
			httpRequest.setParameter("transferEmpName", "1");
			httpRequest.setParameter("applicationButton", "approve");
			
			String LoginUser = "V610000000";
			
			// 疑似セッションスコープに情報をセット
			httpSession.setAttribute("userId", LoginUser);
			httpRequest.setSession(httpSession);

			// 実行クラスがservletのときはおまじないとして記述
			transferApplicationAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			transferApplicationAction.doGet(httpRequest, httpResponse);
	}

		@Test
		public void transferApplicationAction3() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();
			//ユーザーID == パスワードとなるようにパラメータを設定
			httpRequest.setParameter("transferEmpName", "4");
			httpRequest.setParameter("applicationButton", "approve");
			
			String LoginUser = "V610000000";
			
			// 疑似セッションスコープに情報をセット
			httpSession.setAttribute("userId", LoginUser);
			httpRequest.setSession(httpSession);

			// 実行クラスがservletのときはおまじないとして記述
			transferApplicationAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			transferApplicationAction.doGet(httpRequest, httpResponse);
	}
		@Test
		public void transferApplicationAction4() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();
			//ユーザーID == パスワードとなるようにパラメータを設定
			httpRequest.setParameter("transferEmpName", "5");
			httpRequest.setParameter("applicationButton", "approve");
			
			String LoginUser = "V610000000";
			
			// 疑似セッションスコープに情報をセット
			httpSession.setAttribute("userId", LoginUser);
			httpRequest.setSession(httpSession);

			// 実行クラスがservletのときはおまじないとして記述
			transferApplicationAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			transferApplicationAction.doGet(httpRequest, httpResponse);
	}
}
