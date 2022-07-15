
package masterUserTest.searchUserTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterUser.searchUser.SearchResultUserListDTO;
import jp.co.vaile.nerva.masterUser.searchUser.SearchUserMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchUserMstTest {

	SearchUserMstAction searchUserMstAction = new SearchUserMstAction();

	@Test
	@DisplayName("条件検索(ユーザーID)")
	public void idfiltereSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "admin");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");
		
		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));
	}

	@Test
	@DisplayName("条件検索(ユーザー名)")
	public void namefiltereSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "管理者");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");
		
		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認 
		//セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

	}

	@Test
	@DisplayName("所属会社条件検索(株式会社V)")
	public void companyfiltereSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");
		
		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("general"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("一般"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("0"));

	}
	
	@Test
	@DisplayName("所属会社条件検索(K株式会社)")
	public void companyfiltereSearchUser2() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "CP00000002");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");
		
		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("0"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("0"));

	}

	@Test
	@DisplayName("所属会社条件検索(株式会社O)")
	public void companyfiltereSearchUser3() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "CP00000003");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("A123456789B123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

	}

	@Test
	@DisplayName("役職条件検索(役員)")
	public void postfiltereSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

	}

	@Test
	@DisplayName("役職条件検索(部長)")
	public void postfiltereSearchUser2() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "P000000002");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("general"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("一般"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("0"));

	}
	
	@Test
	@DisplayName("役職条件検索(課長)")
	public void postfiltereSearchUser3() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "P000000003");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("0"));

	}

	@Test
	@DisplayName("役職条件検索(一般)")
	public void postfiltereSearchUser4() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "P000000004");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("0"));

	}
	
	@Test
	@DisplayName("役職条件検索(研修)")
	public void postfiltereSearchUser5() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "P000000005");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("A123456789B123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

	}
	
	@Test
	@DisplayName("権限条件検索(管理者)")
	public void adminFLgtfiltereSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "1");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("A123456789B123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("1"));

	}

	@Test
	@DisplayName("権限条件検索(一般)")
	public void adminFLgtfiltereSearchUser2() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "0");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("general"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("一般"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("0"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("0"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserName(), is("太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getAdminFlg(), is("0"));
	}
	
	@Test
	@DisplayName("部分一致検索")
	public void partialMatchSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "管");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

	}

	@Test
	@DisplayName("条件検索(全項目)")
	public void filtereSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "admin");
		httpRequest.setParameter("json[userName]", "管理者");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "1");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

	}

	@Test
	@DisplayName("全件検索(親会社)")
	public void parentcompanyAllSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();
		
		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("general"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("一般"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("0"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserName(), is("A123456789B123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getAdminFlg(), is("1"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getUserName(), is("花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getAdminFlg(), is("0"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getUserName(), is("太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getAdminFlg(), is("0"));
	}

	@Test
	@DisplayName("全件検索(子会社)")
	public void subsidiaryAllSearchUser() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000002";
		String companyPrivilege = "0";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();
		
		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("0"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("0"));

	}

	@Test
	@DisplayName("検索結果0件チェック")
	public void searchUserResultCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "vaile");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		searchUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

	}

	@Test
	@DisplayName("ユーザーID文字数チェック") // 10文字
	public void userIdLengthCheck10() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "A123456789");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		searchUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();
		
		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getUserName(), is("A123456789B123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getAdminFlg(), is("1"));
	}

	@Test
	@DisplayName("ユーザーID文字数チェック") // 11文字
	public void userIdLengthCheck11() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "A1234567890");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		searchUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

	}

	@Test
	@DisplayName("ユーザーID半角英数字チェック")
	public void userIdMatchCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "あいうえお");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		searchUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();

	}

	@Test
	@DisplayName("ユーザー名文字数チェック") // 20文字
	public void userNameLengthCheck20() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "A123456789B123456789");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		searchUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();
		
		// セッション取得
		HttpSession session = httpRequest.getSession();
				
		// 実行結果を確認
		// セッションに検索結果が格納されていることで検索に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
								.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
								.getUserName(), is("A123456789B123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
								.getPassword(), is("9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
								.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
								.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
								.getAdminFlg(), is("1"));

	}

	@Test
	@DisplayName("ユーザー名文字数チェック") // 21文字
	public void userNameLengthCheck21() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "A123456789B1234567890");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");

		String companyId = "CP00000001";
		String companyPrivilege = "1";
		//セッションをセット。
		httpSession.setAttribute("companyId",companyId);
		httpSession.setAttribute("companyPrivilege",companyPrivilege);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		searchUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchUserMstAction.destroy();
	}

}