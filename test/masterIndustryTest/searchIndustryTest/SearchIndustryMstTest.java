
package masterIndustryTest.searchIndustryTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterIndustry.searchIndustry.SearchIndustryMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchIndustryMstTest {
	
	SearchIndustryMstAction searchIndustryMstAction = new SearchIndustryMstAction();
	
	@Test
	@DisplayName("条件検索(ID)")
	public void idfiltereSearchIndustry() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000001");
		httpRequest.setParameter("json[industryName]", ""); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchIndustryMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchIndustryMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchIndustryMstAction.destroy();

	}
	
	@Test
	@DisplayName("条件検索(業種名)")
	public void namefiltereSearchIndustry() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "");
		httpRequest.setParameter("json[industryName]", "金融"); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchIndustryMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchIndustryMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchIndustryMstAction.destroy();

	}

	@Test
	@DisplayName("条件検索(全入力)")
	public void filtereSearchIndustry() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000001");
		httpRequest.setParameter("json[industryName]", "金融"); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchIndustryMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchIndustryMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchIndustryMstAction.destroy();

	}

	@Test
	@DisplayName("全件検索")
	public void allSearchIndustry() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "");
		httpRequest.setParameter("json[industryName]", ""); 
		
		// 実行クラスがservletのときはおまじないとして記述
		searchIndustryMstAction.init(new MockServletConfig()); 
		
		// 実際にテスト対象のメソッドを実行する。
		searchIndustryMstAction.doPost(httpRequest, httpResponse);
		
        //サーブレット破棄
		searchIndustryMstAction.destroy();

	}

	@Test
	@DisplayName("検索結果0件チェック")
	public void searchIndustryResultCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000010");
		httpRequest.setParameter("json[industryName]", "金融");

		// サーブレット初期化
		searchIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchIndustryMstAction.destroy();

	}

	@Test
	@DisplayName("業種ID文字数チェック") //9文字
	public void industryIdLengthCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I12345678");
		httpRequest.setParameter("json[industryName]", "");

		// サーブレット初期化
		searchIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchIndustryMstAction.destroy();

	}
	
	@Test
	@DisplayName("業種ID文字数チェック") //10文字
	public void industryIdLengthCheck2() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I123456789");
		httpRequest.setParameter("json[industryName]", "");

		// サーブレット初期化
		searchIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchIndustryMstAction.destroy();

	}
	
	@Test
	@DisplayName("業種ID文字数チェック")//11文字
	public void industryIdLengthCheck3() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I1234567890");
		httpRequest.setParameter("json[industryName]", "");

		// サーブレット初期化
		searchIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchIndustryMstAction.destroy();

	}

	@Test
	@DisplayName("業種ID半角英数字チェック")
	public void industryIdMatchCheck() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I00000000あ");
		httpRequest.setParameter("json[industryName]", "");

		// サーブレット初期化
		searchIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchIndustryMstAction.destroy();

	}

	@Test
	@DisplayName("業種名文字数チェック")//10文字
	public void industryNameLengthCheck2() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "");
		httpRequest.setParameter("json[industryName]", "A123456789");

		// サーブレット初期化
		searchIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchIndustryMstAction.destroy();

	}
	@Test
	@DisplayName("業種名文字数チェック")//11文字
	public void industryNameLengthCheck3() throws ServletException, IOException {
		
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "");
		httpRequest.setParameter("json[industryName]", "A1234567890");

		// サーブレット初期化
		searchIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchIndustryMstAction.destroy();
		}
	
		@Test
		@DisplayName("業種名部分検索")
		public void industryNamePartCheck() throws ServletException, IOException {
			
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();

			// セッションをセット
			httpRequest.setSession(new MockHttpSession());

			// パラメータに入力内容を設定
			httpRequest.setParameter("json[industryId]", "");
			httpRequest.setParameter("json[industryName]", "金");

			// サーブレット初期化
			searchIndustryMstAction.init(new MockServletConfig());

			// テスト対象のメソッドを実行
			searchIndustryMstAction.doPost(httpRequest, httpResponse);

			// サーブレット破棄
			searchIndustryMstAction.destroy();
	}
}