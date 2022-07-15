package masterCompanyTest.searchCompanyTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterCompany.searchCompany.SearchCompanyMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchCompanyMstTest {

	SearchCompanyMstAction searchCompanyMstAction = new SearchCompanyMstAction();

	@Test
	@DisplayName("条件検索(所属会社ID)")
	public void idfiltereSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");
		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("条件検索(所属会社名)")
	public void namefiltereSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", "株式会社A"); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");
		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("部分一致検索(所属会社名)")
	public void partialMatcheSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", "会社A"); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");
		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("条件検索(所属会社グループ権限)")
	public void groupfiltereParentSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "");
		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}
	
	@Test
	@DisplayName("条件検索(所属会社グループ権限)")
	public void groupfiltereChildSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "0");
		httpRequest.setParameter("json[companyCode]", "");
		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("条件検索（会社識別コード）")
	public void codefiltereSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "A");

		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}
	
	@Test
	@DisplayName("条件検索（会社識別コード）")
	public void codefiltereSearchCompany2() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "M");

		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("条件検索（会社識別コード）")
	public void codefiltereSearchCompany3() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "Z");

		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}
	
	@Test
	@DisplayName("条件検索")
	public void filtereSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[companyName]", "株式会社A"); 
		httpRequest.setParameter("json[companyGroup]", "親会社");
		httpRequest.setParameter("json[companyCode]", "A");

		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("全件検索(親会社)")
	public void allSearchCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");

		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("全件検索(子会社)")
	public void allSearchChildCompany() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="0";
		String loginUserId="admin2";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");

		// 実行クラスがservletのときはおまじないとして記述
		searchCompanyMstAction.init(new MockServletConfig()); 

		// 実際にテスト対象のメソッドを実行する。
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("検索結果チェック")
	public void searchCompanyResultCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000010");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");

		// サーブレット初期化
		searchCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("所属会社ID文字数チェック") //9文字
	public void companyIdLengthCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP0000001");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");

		// サーブレット初期化
		searchCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("所属会社ID文字数チェック") //10文字
	public void companyIdLengthCheck2() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");
		// サーブレット初期化
		searchCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("所属会社ID文字数チェック")//11文字
	public void companyIdLengthCheck3() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP000000001");
		httpRequest.setParameter("json[companyName]", ""); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");

		// サーブレット初期化
		searchCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("所属会社ID半角英数字チェック")
	public void companyIdMatchCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "C00000000あ");
		httpRequest.setParameter("json[companyName]", "株式会社V"); 
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");

		// サーブレット初期化
		searchCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchCompanyMstAction.destroy();

	}

	@Test
	@DisplayName("所属会社名文字数チェック")//10文字
	public void companyNameLengthCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", "Z123456789");
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");


		// サーブレット初期化
		searchCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchCompanyMstAction.destroy();

	}
	@Test
	@DisplayName("所属会社名文字数チェック")//11文字
	public void companyNameLengthCheck1() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		String testCompanyPrivilege="1";
		String loginUserId="admin";
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", "Z1234567890");
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");

		// サーブレット初期化
		searchCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchCompanyMstAction.destroy();

	}
	
	

}
