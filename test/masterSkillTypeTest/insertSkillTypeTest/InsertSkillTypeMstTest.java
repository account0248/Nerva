package masterSkillTypeTest.insertSkillTypeTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import jp.co.vaile.nerva.masterSkillType.insertSkillType.InsertSkillTypeMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class InsertSkillTypeMstTest {
	InsertSkillTypeMstAction insertSkillTypeMstAction = new InsertSkillTypeMstAction();

	/**
	 * 全項目が正常に入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws ServletException
	 */
	@Test
	@DisplayName("No1 全項目入力 （年数）")
	public void allInputYearsSuccessPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000005");
		httpRequest.setParameter("json[skillTypeName]", "スポーツ");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());
		
		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}
	
	/**
	 * 全項目が正常に入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws ServletException
	 */
	@Test
	@DisplayName("No2 全項目入力 （取得日）")
	public void allInputDateOfAcquisitionSuccessPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000006");
		httpRequest.setParameter("json[skillTypeName]", "免許");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "0");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());
		
		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別IDが未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No3 スキル種別ID未入力")
	public void skillTypeIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "外国語");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別IDが9文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No4 スキル種別ID文字数制限9")
	public void skillTypeIdLengthCheckErrorPattern9() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S12345678");
		httpRequest.setParameter("json[skillTypeName]", "外国語");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);
		
		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}
	
	
	/**
	 *スキル種別IDが10文字で入力されている場合、登録が完了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No5 スキル種別ID文字数制限10")
	public void skillTypeIdLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S123456789");
		httpRequest.setParameter("json[skillTypeName]", "外国語");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}
	
	/**
	 *スキル種別IDが11文字で入力されている場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No6 スキル種別ID文字数制限11")
	public void skillTypeIdLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		
		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S1234567890");
		httpRequest.setParameter("json[skillTypeName]", "フレームワーク");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別IDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No7 スキル種別ID半角英数字制限")
	public void skillTypeIdHarfWidthAlphanumErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S00000000あ");
		httpRequest.setParameter("json[skillTypeName]", "フレームワーク");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別IDが形式通りで入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No8 スキル種別ID形式制限")
	public void skillTypeIdFormatCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "Z000000005");
		httpRequest.setParameter("json[skillTypeName]", "フレームワーク");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別IDが既に登録されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No9 スキル種別ID重複制限")
	public void skillTypeIdDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000001");
		httpRequest.setParameter("json[skillTypeName]", "フレームワーク");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別名が未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No10 スキル種別名未入力制限")
	public void skillTypeNameRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000006");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別名が20文字で入力されている場合、登録が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No11 スキル種別名文字数制限20")
	public void skillTypeNameLengthCheckErrorPattern10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000008");
		httpRequest.setParameter("json[skillTypeName]", "A1234567891234567890");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}
	
	/**
	 * スキル種別名が21文字で入力されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No12 スキル種別名文字数制限21")
	public void skillTypeNameLengthCheckErrorPattern11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000009");
		httpRequest.setParameter("json[skillTypeName]", "A12345678912345678901");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * スキル種別名が既に登録されている場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No13 スキル種別名重複制限")
	public void skillTypeNameDuplicateCheckErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000009");
		httpRequest.setParameter("json[skillTypeName]", "言語");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}
	
	/**
	 * 年数/取得日フラグが未選択の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No14 年数/取得日フラグ未選択制限")
	public void yearsDateOfAcquisitionInputRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000009");
		httpRequest.setParameter("json[skillTypeName]", "料理");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}

	/**
	 * 全項目が未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("No15 全項目未入力")
	public void allInputRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");
		
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// サーブレット初期化
		insertSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		insertSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		insertSkillTypeMstAction.destroy();
	}
}
