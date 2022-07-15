package logintest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import jp.co.vaile.nerva.login.LoginAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class LoginTestSample {
	LoginAction loginAction = new LoginAction();
	/**
	 * 全パターンテスト
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	@DisplayName("全テスト実行")
	public void testLoginAction() throws ServletException, IOException {
		//全項目が正常に入力されている場合、メニュー画面に遷移すること。
		allInputSuccessPattern();
		//ユーザIDが未入力の場合、エラーメッセージを出力すること。
		userIdRequiredErrorPattern();
		//パスワードが未入力の場合、エラーメッセージを出力すること。
		passwordRequiredErrorPattern();
		//ユーザID、パスワードが未入力の場合、エラーメッセージを出力すること
		userIdAndPasswordRequiredErrorPattern();
		//ユーザIDが不正な場合、エラーメッセージを出力すること。
		incorrectUuserIdErrorPattern();
		//パスワードが不正な場合、エラーメッセージを出力すること。
		incorrectPasswordErrorPattern();
		//ユーザID、パスワードが不正な場合、エラーメッセージを出力すること。
		incorrectUserIdAndPasswordErrorPattern();
	}

	/**
	 * 全項目が正常に入力されている場合、メニュー画面に遷移すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("全項目入力")
	public void allInputSuccessPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//セッションをセット
		httpRequest.setSession(new MockHttpSession());

		//パラメータに入力内容を設定
		httpRequest.setParameter("userId", "test01");
		httpRequest.setParameter("password", "pass");

		//サーブレット初期化
		loginAction.init(new MockServletConfig());

		//テスト対象のメソッドを実行
		loginAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		loginAction.destroy();

		//セッション取得
		HttpSession session = httpRequest.getSession();

		//実行結果を確認
		//セッションにログイン情報が格納されていることでログインに成功したこととする。
		assertThat(session.getAttribute("loginCheck").toString(), is("true"));
		assertThat(session.getAttribute("userId").toString(), is("test01"));
		assertThat(session.getAttribute("userName").toString(), is("テスト０１"));
		assertThat(session.getAttribute("companyId").toString(), is("CP00000001"));
		assertThat(session.getAttribute("adminFlg").toString(), true);
	}

	/**
	 * ユーザIDが未入力の場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("ユーザID未入力")
	public void userIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//セッションをセット
		httpRequest.setSession(new MockHttpSession());

		//パラメータに入力内容を設定
		httpRequest.setParameter("userId", "");
		httpRequest.setParameter("password", "pass");

		//サーブレット初期化
		loginAction.init(new MockServletConfig());

		//テスト対象のメソッドを実行
		loginAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		loginAction.destroy();

		//実行結果を確認
		//エラーメッセージが想定結果通りであること
		assertThat(httpRequest.getAttribute("errorList"), is("ユーザID、パスワードはかならず入力してください。"));
	}

	/**
	 * パスワードが未入力の場合、エラーメッセージを出力すること。
	 */
	@Test
	@DisplayName("パスワード未入力")
	public void passwordRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//セッションをセット
		httpRequest.setSession(new MockHttpSession());

		//パラメータに入力内容を設定
		httpRequest.setParameter("userId", "test01");
		httpRequest.setParameter("password", "");

		//サーブレット初期化
		loginAction.init(new MockServletConfig());

		//テスト対象のメソッドを実行
		loginAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		loginAction.destroy();

		//実行結果を確認
		//エラーメッセージが想定結果通りであること
		assertThat(httpRequest.getAttribute("errorList"), is("ユーザID、パスワードはかならず入力してください。"));
	}

	/**
	 * ユーザID、パスワードが未入力の場合、エラーメッセージを出力すること。
	 */
	@Test
	@DisplayName("ユーザID、パスワード未入力")
	public void userIdAndPasswordRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//セッションをセット
		httpRequest.setSession(new MockHttpSession());

		//パラメータに入力内容を設定
		httpRequest.setParameter("userId", "");
		httpRequest.setParameter("password", "");

		//サーブレット初期化
		loginAction.init(new MockServletConfig());

		//テスト対象のメソッドを実行
		loginAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		loginAction.destroy();

		//実行結果を確認
		//エラーメッセージが想定結果通りであること
		assertThat(httpRequest.getAttribute("errorList"), is("ユーザID、パスワードはかならず入力してください。"));
	}

	/**
	 * ユーザIDが不正な場合、エラーメッセージを出力すること。
	 */
	@Test
	@DisplayName("ユーザID不正")
	public void incorrectUuserIdErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//セッションをセット
		httpRequest.setSession(new MockHttpSession());

		//パラメータに入力内容を設定
		httpRequest.setParameter("userId", "test02");
		httpRequest.setParameter("password", "pass");

		//サーブレット初期化
		loginAction.init(new MockServletConfig());

		//テスト対象のメソッドを実行
		loginAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		loginAction.destroy();

		//実行結果を確認
		//エラーメッセージが想定結果通りであること
		assertThat(httpRequest.getAttribute("errorList"), is("ログインに失敗しました。"));
	}

	/**
	 * パスワードが不正な場合、エラーメッセージを出力すること。
	 */
	@Test
	@DisplayName("パスワード不正")
	public void incorrectPasswordErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//セッションをセット
		httpRequest.setSession(new MockHttpSession());

		//パラメータに入力内容を設定
		httpRequest.setParameter("userId", "test01");
		httpRequest.setParameter("password", "password");

		//サーブレット初期化
		loginAction.init(new MockServletConfig());

		//テスト対象のメソッドを実行
		loginAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		loginAction.destroy();

		//実行結果を確認
		//エラーメッセージが想定結果通りであること
		assertThat(httpRequest.getAttribute("errorList"), is("ログインに失敗しました。"));
	}

	/**
	 * ユーザID、パスワードが不正な場合、エラーメッセージを出力すること。
	 */
	@Test
	@DisplayName("ユーザID、パスワードが不正")
	public void incorrectUserIdAndPasswordErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//セッションをセット
		httpRequest.setSession(new MockHttpSession());

		//パラメータに入力内容を設定
		httpRequest.setParameter("userId", "test02");
		httpRequest.setParameter("password", "password");

		//サーブレット初期化
		loginAction.init(new MockServletConfig());

		//テスト対象のメソッドを実行
		loginAction.doPost(httpRequest, httpResponse);

		//サーブレット破棄
		loginAction.destroy();

		//実行結果を確認
		//エラーメッセージが想定結果通りであること
		assertThat(httpRequest.getAttribute("errorList"), is("ログインに失敗しました。"));
	}
}
