package masterCompanyTest.insertCompanyTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import jp.co.vaile.nerva.masterCompany.insertCompany.InsertCompanyMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class InsertCompanyMstTest {
	InsertCompanyMstAction insertCompanyMstAction = new InsertCompanyMstAction();

	/**
	 *親会社の管理者が全項目を正常に入力されている場合、正常に処理が終了すること。
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
		String loginloginUserId="admin";
		String testCompanyPrivilege="true";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginloginUserId);
		httpRequest.setParameter("json[companyId]", "CP00000004");
		httpRequest.setParameter("json[companyName]", "株式会社B");
		httpRequest.setParameter("json[companyGroup]", "0");
		httpRequest.setParameter("json[companyCode]", "B");
		httpRequest.setSession(httpSession);
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());


		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 *子会社の管理者が全項目を正常に入力されている場合、正常に処理が終了すること。
	 * 
	 * @throws ServletException
	 * @throws ServletException
	 */
	@Test
	@DisplayName("全項目入力")
	public void allInputChildSuccessPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginloginUserId="general";
		String testCompanyPrivilege="false";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginloginUserId);
		httpRequest.setParameter("json[companyId]", "CP00000005");
		httpRequest.setParameter("json[companyName]", "株式会社C");
		httpRequest.setParameter("json[companyGroup]", "0");
		httpRequest.setParameter("json[companyCode]", "C");
		httpRequest.setSession(httpSession);
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());


		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社IDを未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社ID未入力")
	public void companyIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", "株式会社D");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "D");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社IDを9文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社ID文字数制限9")
	public void companyIdLengthCheckErrorPattern9() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP1234567");
		httpRequest.setParameter("json[companyName]", "株式会社D");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "D");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}


	/**
	 *親会社の管理者が所属会社IDを10文字で入力されている場合、登録が完了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社ID文字数制限10")
	public void companyIdLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP12345678");
		httpRequest.setParameter("json[companyName]", "株式会社D");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "D");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 *親会社の管理者が所属会社IDを11文字で入力されている場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社ID文字数制限11")
	public void companyIdLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP123456789");
		httpRequest.setParameter("json[companyName]", "株式会社E");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "E");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社IDを半角英数字で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社ID半角英数字制限")
	public void companyIdHarfWidthAlphanumErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP0000000あ");
		httpRequest.setParameter("json[companyName]", "株式会社E");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "E");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社IDを形式通りで入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社ID形式制限")
	public void companyIdFormatCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "Z000000006");
		httpRequest.setParameter("json[companyName]", "株式会社E");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "E");


		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社IDを既に登録されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社ID重複制限")
	public void companyIdDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[companyName]", "株式会社E");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "E");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社名を未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社名未入力制限")
	public void companyNameRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000006");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "E");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}


	/**
	 * 親会社の管理者が所属会社名を10文字で入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社名文字数制限10")
	public void companyNameLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setParameter("json[companyId]", "CP00000006");
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyName]", "E123456789");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "E");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社名を11文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社名文字数制限11")
	public void companyNameLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000007");
		httpRequest.setParameter("json[companyName]", "F1234567890");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "F");

		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}

	/**
	 * 親会社の管理者が所属会社名を既に登録されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属会社名重複制限")
	public void companyNameDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000007");
		httpRequest.setParameter("json[companyName]", "株式会社E");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "F");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}


	/**
	 * 親会社の管理者がグループを選択されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName(" 所属会社グループ権限未選択")
	public void companyGroupSelectCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000007");
		httpRequest.setParameter("json[companyName]", "株式会社F");
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "F");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}
	
	/**
	 * 親会社の管理者がグループを選択した場合、登録が完了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName(" 所属会社グループ権限選択(親会社)")
	public void companyGroupSelectPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000007");
		httpRequest.setParameter("json[companyName]", "株式会社F");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "F");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}
	
	/**
	 * 親会社の管理者がグループを選択した場合、登録が完了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName(" 所属会社グループ権限選択(子会社)")
	public void companyGroupSelectPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000008");
		httpRequest.setParameter("json[companyName]", "株式会社G");
		httpRequest.setParameter("json[companyGroup]", "0");
		httpRequest.setParameter("json[companyCode]", "G");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}


	/**
	 *親会社の管理者が会社識別コードを選択されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName(" 会社識別コード未選択")
	public void companyCodeSelectCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000007");
		httpRequest.setParameter("json[companyName]", "株式会社E");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}
	
	/**
	 *親会社の管理者が一番上にある会社識別コードを選択した場合、登録が完了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName(" 会社識別コード選択(一番上)")
	public void companyCodeSelectPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000009");
		httpRequest.setParameter("json[companyName]", "株式会社H");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "H");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}
	
	/**
	 *親会社の管理者が真ん中にある会社識別コードを選択されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName(" 会社識別コード選択(真ん中)")
	public void companyCodeSelectPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000010");
		httpRequest.setParameter("json[companyName]", "株式会社Q");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "Q");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}
	
	/**
	 *親会社の管理者が一番下にある会社識別コードを選択されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName(" 会社識別コード選択(一番下)")
	public void companyCodeSelectPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "CP00000011");
		httpRequest.setParameter("json[companyName]", "株式会社Y");
		httpRequest.setParameter("json[companyGroup]", "1");
		httpRequest.setParameter("json[companyCode]", "Y");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}
	
	/**
	 * 親会社の管理者が全項目を未入力の場合、エラーメッセージを出力すること。
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
		String loginUserId="admin";
		String testCompanyPrivilege="1";
		httpSession.setAttribute("companyPrivilege", testCompanyPrivilege);
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[companyGroup]", "");
		httpRequest.setParameter("json[companyCode]", "");
		// サーブレット初期化
		insertCompanyMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertCompanyMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertCompanyMstAction.destroy();
	}
}


