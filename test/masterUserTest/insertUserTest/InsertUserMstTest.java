package masterUserTest.insertUserTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import jp.co.vaile.nerva.masterUser.insertUser.InsertUserMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class InsertUserMstTest {
	InsertUserMstAction insertUserMstAction = new InsertUserMstAction();

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
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
		
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());
	
		
		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}

	/**
	 * ユーザーIDが未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザーID未入力")
	public void userIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 *ユーザーIDが10文字で入力されている場合、登録が完了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザーID文字数制限10")
	public void userIdLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "U123456789");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
		
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);


		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 *ユーザーIDが11文字で入力されている場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザーID文字数制限11")
	public void userIdLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "U1234567890");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}

	/**
	 * ユーザーIDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザーID半角英数字制限")
	public void userIdHarfWidthAlphanumErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "あいうえお");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
		
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}

	/**
	 * ユーザーIDが既に登録されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザーID重複制限")
	public void userIdDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "general");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}

	/**
	 * ユーザー名が未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザー名未入力制限")
	public void userNameRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test2");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * ユーザー名が20文字で入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザー名文字数制限20")
	public void userNameLengthCheckErrorPattern20() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test2");
		httpRequest.setParameter("json[userName]", "U123456789S123456789");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
						

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * ユーザー名が21文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザー名文字数制限21")
	public void userNameLengthCheckErrorPattern21() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test3");
		httpRequest.setParameter("json[userName]", "U123456789S123456789E");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
						
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * パスワードが未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード未入力制限")
	public void passwordRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test4");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
				
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}

	/**
	 * パスワード名が7文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード文字数制限7")
	public void passwordLengthCheckErrorPattern7() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test4");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Pass123");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
						
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("ussrId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * パスワードが8文字で入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード文字数制限8")
	public void passwordLengthCheckErrorPattern8() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test4");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Pass1234");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
						
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * パスワードが20文字で入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード文字数制限20")
	public void passwordLengthCheckErrorPattern20() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test5");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Pass1234567891234567");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
						
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}

	/**
	 * パスワード名が21文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード文字数制限21")
	public void passwordLengthCheckErrorPattern21() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test6");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Pass12345678912345678");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
						
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード半角英字なし")
	public void passwordHarfWidthAlphanumUpperErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test6");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "あｐ１２３４５６");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード数字なし")
	public void passwordHarfWidthAlphanumUpperErrorPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test6");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード小文字なし")
	public void passwordHarfWidthAlphanumUpperErrorPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test6");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "PASSWORD1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード大文字なし")
	public void passwordHarfWidthAlphanumUpperErrorPattern4() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test6");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");

		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 所属会社が未選択の場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社未選択")
	public void companyRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test6");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 所属会社Vが選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社選択(株式会社V)")
	public void companyRequiredPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test7");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 所属会社Kが選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社選択(K株式会社)")
	public void companyRequiredPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test8");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000002");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 所属会社Oが選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社選択(株式会社O)")
	public void companyRequiredPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test9");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000003");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 役職が未選択の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("役職未選択")
	public void postRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test10");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 役員が選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("役職選択(役員)")
	public void postRequiredPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test10");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 部長が選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("役職選択(部長)")
	public void postRequiredPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test11");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000002");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 課長が選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("役職選択(課長)")
	public void postRequiredPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test12");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000003");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 *  一般が選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("役職選択(一般)")
	public void postRequiredPattern4() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test13");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000004");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 *  研修が選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("役職選択(研修)")
	public void postRequiredPattern5() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test14");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000005");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 権限が未選択の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("権限未選択")
	public void adminFlgRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test15");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 管理者が選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("権限(管理者)")
	public void adminFlgRequiredPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test15");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "1");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
	
	/**
	 * 一般が選択された場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("権限(一般)")
	public void adminFlgRequiredPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "test16");
		httpRequest.setParameter("json[userName]", "テストユーザー");
		httpRequest.setParameter("json[password]", "Password1");
		httpRequest.setParameter("json[company]", "CP00000001");
		httpRequest.setParameter("json[post]", "P000000001");
		httpRequest.setParameter("json[adminFlg]", "0");
								
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
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
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[userId]", "");
		httpRequest.setParameter("json[userName]", "");
		httpRequest.setParameter("json[password]", "");
		httpRequest.setParameter("json[company]", "");
		httpRequest.setParameter("json[post]", "");
		httpRequest.setParameter("json[adminFlg]", "");
		
		// 疑似セッションスコープに情報をセット
		String userId = "admin";
		httpSession.setAttribute("userId", userId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertUserMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertUserMstAction.destroy();
	}
}
