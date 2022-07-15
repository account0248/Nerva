
package masterDepartmentTest.searchDepartmentTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterDepartment.searchDepartment.SearchDepartmentMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchDepartmentMstTest {
	
	SearchDepartmentMstAction searchDepartmentMstAction = new SearchDepartmentMstAction();
	
	@Test
	@DisplayName("条件検索(ID)")
	public void idfiltereSearchDepartment() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[departmentName]", ""); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchDepartmentMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchDepartmentMstAction.destroy();

	}
	
	@Test
	@DisplayName("条件検索(所属組織名、完全一致)")
	public void namefiltereSearchDepartment1() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[departmentName]", "管理本部"); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchDepartmentMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchDepartmentMstAction.destroy();

	}

	@Test
	@DisplayName("条件検索(全入力)")
	public void filtereSearchDepartment() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[departmentName]", "管理本部"); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchDepartmentMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchDepartmentMstAction.destroy();

	}

	@Test
	@DisplayName("全件検索")
	public void allSearchDepartment() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[departmentName]", ""); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchDepartmentMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchDepartmentMstAction.destroy();

	}

	@Test
	@DisplayName("検索結果チェック")
	public void searchDepartmentResultCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000010");
		httpRequest.setParameter("json[departmentName]", "管理本部");

		// サーブレット初期化
		searchDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchDepartmentMstAction.destroy();

	}

	@Test
	@DisplayName("所属組織ID文字数チェック") //9文字
	public void departmentIdLengthCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D00000001");
		httpRequest.setParameter("json[departmentName]", "");

		// サーブレット初期化
		searchDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchDepartmentMstAction.destroy();

	}
	
	@Test
	@DisplayName("所属組織ID文字数チェック") //10文字
	public void departmentIdLengthCheck2() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[departmentName]", "");

		// サーブレット初期化
		searchDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchDepartmentMstAction.destroy();

	}
	
	@Test
	@DisplayName("所属組織ID文字数チェック")//11文字
	public void departmentIdLengthCheck3() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D0000000001");
		httpRequest.setParameter("json[departmentName]", "");

		// サーブレット初期化
		searchDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchDepartmentMstAction.destroy();

	}

	@Test
	@DisplayName("所属組織ID半角英数字チェック")
	public void departmentIdMatchCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D00000000あ");
		httpRequest.setParameter("json[departmentName]", "");

		// サーブレット初期化
		searchDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchDepartmentMstAction.destroy();

	}

	@Test
	@DisplayName("所属組織名文字数チェック")//10文字
	public void departmentNameLengthCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[departmentName]", "A123456789");

		// サーブレット初期化
		searchDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchDepartmentMstAction.destroy();

	}
	@Test
	@DisplayName("所属組織名文字数チェック")//11文字
	public void departmentNameLengthCheck2() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[departmentName]", "A1234567890");

		// サーブレット初期化
		searchDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchDepartmentMstAction.destroy();

	}
	
	@Test
	@DisplayName("条件検索(所属組織名、部分一致)")
	public void namefiltereSearchDepartment2() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[departmentName]", "システム"); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchDepartmentMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchDepartmentMstAction.destroy();

	}
	
	@Test
	@DisplayName("検索結果0件エラー")
	public void noResultCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000009");
		httpRequest.setParameter("json[departmentName]", "人事部"); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchDepartmentMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchDepartmentMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchDepartmentMstAction.destroy();

	}
}