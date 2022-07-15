package masterDepartmentTest.insertDepartmentTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import jp.co.vaile.nerva.masterDepartment.insertDepartment.InsertDepartmentMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class InsertDepartmentMstTest {
	InsertDepartmentMstAction insertDepartmentMstAction = new InsertDepartmentMstAction();

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
		httpRequest.setParameter("json[departmentId]", "D000000008");
		httpRequest.setParameter("json[departmentName]", "総務部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織IDが未入力の場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織ID未入力")
	public void departmentIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[departmentName]", "経理部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織IDが9文字で入力されている場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織ID文字数制限9")
	public void departmentIdLengthCheckErrorPattern9() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D12345678");
		httpRequest.setParameter("json[departmentName]", "経理部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織IDが10文字で入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織ID文字数制限10")
	public void departmentIdLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D123456789");
		httpRequest.setParameter("json[departmentName]", "経理部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織IDが11文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織ID文字数制限11")
	public void departmentIdLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D1234567890");
		httpRequest.setParameter("json[departmentName]", "監査部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織IDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織ID半角英数字制限")
	public void departmentIdHarfWidthAlphanumErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D00000000あ");
		httpRequest.setParameter("json[departmentName]", "監査部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織IDが形式通りで入力されていない場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織ID形式制限")
	public void departmentIdFormatCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "Z000000008");
		httpRequest.setParameter("json[departmentName]", "監査部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織IDが既に登録されている場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織ID重複制限")
	public void departmentIdDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[departmentName]", "監査部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織名が未入力の場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織名未入力制限")
	public void departmentNameRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000009");
		httpRequest.setParameter("json[departmentName]", "");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織名が10文字で入力されている場合、登録が完了すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織名文字数制限10")
	public void departmentNameLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000011");
		httpRequest.setParameter("json[departmentName]", "A123456789");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織名が11文字で入力されている場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織名文字数制限11")
	public void departmentNameLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000012");
		httpRequest.setParameter("json[departmentName]", "A1234567890");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}

	/**
	 * 所属組織名が既に登録されている場合、エラーメッセージを出力すること。
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("所属組織名重複制限")
	public void departmentNameDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[departmentId]", "D000000012");
		httpRequest.setParameter("json[departmentName]", "管理本部");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
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
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[departmentName]", "");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertDepartmentMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertDepartmentMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertDepartmentMstAction.destroy();
	}
}
