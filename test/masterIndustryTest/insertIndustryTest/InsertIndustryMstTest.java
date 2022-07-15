package masterIndustryTest.insertIndustryTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import jp.co.vaile.nerva.masterIndustry.insertIndustry.InsertIndustryMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class InsertIndustryMstTest {
	InsertIndustryMstAction insertIndustryMstAction = new InsertIndustryMstAction();

	/**
	 * 全項目が正常に入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws ServletException
	 */
	@Test
	@DisplayName("全項目入力")
	public void allInputSuccessPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000006");
		httpRequest.setParameter("json[industryName]", "不動産");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());
	
		
		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 業種IDが未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種ID未入力")
	public void industryIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "");
		httpRequest.setParameter("json[industryName]", "建設");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 業種IDが9文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種ID文字数制限9")
	public void industryIdLengthCheckErrorPattern9() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I12345678");
		httpRequest.setParameter("json[industryName]", "建設");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}
	
	
	/**
	 *業種IDが10文字で入力されている場合、登録が完了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種ID文字数制限10")
	public void industryIdLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I123456789");
		httpRequest.setParameter("json[industryName]", "建設");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}
	
	/**
	 *業種IDが11文字で入力されている場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種ID文字数制限11")
	public void industryIdLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I1234567890");
		httpRequest.setParameter("json[industryName]", "サービス");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 業種IDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種ID半角英数字制限")
	public void industryIdHarfWidthAlphanumErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I00000000あ");
		httpRequest.setParameter("json[industryName]", "サービス");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 業種IDが形式通りで入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種ID形式制限")
	public void industryIdFormatCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "Z000000005");
		httpRequest.setParameter("json[industryName]", "サービス");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 業種IDが既に登録されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種ID重複制限")
	public void industryIdDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000001");
		httpRequest.setParameter("json[industryName]", "サービス");

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 業種名が未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種名未入力制限")
	public void industryNameRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000006");
		httpRequest.setParameter("json[industryName]", "");

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}
	
	/**
	 * 業種名が10文字で入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種名文字数制限10")
	public void industryNameLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000008");
		httpRequest.setParameter("json[industryName]", "A123456789");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}
	
	/**
	 * 業種名が11文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種名文字数制限11")
	public void industryNameLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000009");
		httpRequest.setParameter("json[industryName]", "A1234567890");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 業種名が既に登録されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("業種名重複制限")
	public void industryNameDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "I000000009");
		httpRequest.setParameter("json[industryName]", "金融");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}

	/**
	 * 全項目が未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("全項目未入力")
	public void allInputRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[industryId]", "");
		httpRequest.setParameter("json[industryName]", "");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertIndustryMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertIndustryMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertIndustryMstAction.destroy();
	}
}
