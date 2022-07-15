package masterUserTest.updateUserTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterUser.searchUser.SearchResultUserListDTO;
import jp.co.vaile.nerva.masterUser.updateUser.UpdateUserMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateUserMstTest {
	InitialInsertUserValue InitialValue = new InitialInsertUserValue();
	UpdateUserMstAction updateUserMstAction = new UpdateUserMstAction();

	/**
	 * 1ユーザーの全カラム値が変更された場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("一件更新、全カラム変更")
	public void testInputOneUpdateUser() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"管理者ユーザー\"]");
		httpRequest.setParameter("json[password]", "[\"Password1\"]");
		httpRequest.setParameter("json[post]", "[\"P000000002\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"0\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "管理者", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000001", "P000000001",
				"1" };
		String[] generalValue = { "general", "一般", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000001",
				"P000000002", "0" };
		String[] taroValue = { "taro", "太郎", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002", "P000000003",
						"0" };
		String[] hanakoValue = { "hanako", "花子", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002",
						"P000000004", "0" };
		String[] aValue = { "A123456789", "A123456789B123456789", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000003", "P000000005",
						"1" };
		
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) httpSession.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) httpSession.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者ユーザー"));
		assertThat(((SearchResultUserListDTO) httpSession.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) httpSession.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) httpSession.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) httpSession.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("0"));

		// サーブレット破棄
		updateUserMstAction.destroy();
	}

	/**
	 * 全ユーザーの全カラム値が変更された場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("全件更新、全カラム変更")
	public void testInputAllUpdateUser() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\",\"general\",\"A123456789\",\"hanako\",\"taro\"]");
		httpRequest.setParameter("json[userName]", "[\"管理者\",\"一般ユーザー\",\"A123456789B12345678\",\"山田 花子\",\"山田 太郎\"]");
		httpRequest.setParameter("json[password]", "[\"Password2\",\"Password1\",\"Password1\",\"Password1\",\"Password1\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\",\"P000000004\",\"P000000003\",\"P000000002\",\"P000000001\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\",\"1\",\"0\",\"1\",\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "管理者ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001", "P000000002",
						"0" };
		String[] generalValue = { "general", "一般", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000001",
						"P000000002", "0" };
		String[] taroValue = { "taro", "太郎", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002", "P000000003",
								"0" };
		String[] hanakoValue = { "hanako", "花子", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002",
								"P000000004", "0" };
		String[] aValue = { "A123456789", "A123456789B123456789", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000003", "P000000005",
								"1" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9237cb0fb91eb2a245845f9f3ef42defa2e494b6"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("general"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("一般ユーザー"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("1"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserName(), is("A123456789B12345678"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getAdminFlg(), is("0"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getUserName(), is("山田 花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getAdminFlg(), is("1"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getUserName(), is("山田 太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getAdminFlg(), is("1"));
		// サーブレット破棄
		updateUserMstAction.destroy();
	}

	/**
	 * ユーザー名が変更された場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("一件更新、ユーザー名のみ変更")
	public void testInputOneUpdateUserName() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"管理者ユーザー\"]");
		httpRequest.setParameter("json[password]", "[\"9237cb0fb91eb2a245845f9f3ef42defa2e494b6\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "管理者", "9237cb0fb91eb2a245845f9f3ef42defa2e494b6", "CP00000001", "P000000005",
								"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
								"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
										"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
										"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
										"0" };
						
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認 // セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("管理者ユーザー"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9237cb0fb91eb2a245845f9f3ef42defa2e494b6"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));

	}

	/**
	 * ユーザー名が未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("一件更新、ユーザー名のみ未入力")
	public void userNameRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"\"]");
		httpRequest.setParameter("json[password]", "[\"9237cb0fb91eb2a245845f9f3ef42defa2e494b6\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "管理者ユーザー", "9237cb0fb91eb2a245845f9f3ef42defa2e494b6", "CP00000001", "P000000005",
										"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
										"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
												"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
												"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
												"0" };
						
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * ユーザー名が20文字で入力された場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("境界値テスト ユーザー名20文字で更新")
	public void userNameLengthCheckErrorPattern20() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"9237cb0fb91eb2a245845f9f3ef42defa2e494b6\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "管理者ユーザー", "9237cb0fb91eb2a245845f9f3ef42defa2e494b6", "CP00000001", "P000000005",
												"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
												"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
														"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
														"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
														"0" };
		
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());
		
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("U123456789S123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("9237cb0fb91eb2a245845f9f3ef42defa2e494b6"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));
	}

	/**
	 * ユーザー名が21文字で入力された場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("境界値テスト ユーザー名21文字で更新")
	public void userNameLengthCheckErrorPattern21() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789E\"]");
		httpRequest.setParameter("json[password]", "[\"9237cb0fb91eb2a245845f9f3ef42defa2e494b6\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "9237cb0fb91eb2a245845f9f3ef42defa2e494b6", "CP00000001", "P000000005",
														"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
														"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																"0" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * パスワードが未入力の場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("一件更新、パスワードのみ未入力")
	public void passwordRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "9237cb0fb91eb2a245845f9f3ef42defa2e494b6", "CP00000001", "P000000005",
																"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																		"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																		"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																		"0" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * パスワードが7文字で入力された場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("境界値テスト パスワードを7文字で更新")
	public void passwordLengthCheckErrorPattern7() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"Pass123\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "9237cb0fb91eb2a245845f9f3ef42defa2e494b6", "CP00000001", "P000000005",
																"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																		"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																		"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																		"0" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * パスワードが8文字で入力された場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("境界値テスト パスワードを8文字で更新")
	public void passwordLengthCheckErrorPattern8() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"Pass1234\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "9237cb0fb91eb2a245845f9f3ef42defa2e494b6", "CP00000001", "P000000005",
																		"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																		"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																				"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																				"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																				"0" };
						
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("U123456789S123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("92c8b10157e05856af182a643de7dcea14472f74"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));
	}

	/**
	 * パスワードが20文字で入力された場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("境界値テスト パスワード20文字で更新")
	public void passwordLengthCheckErrorPattern20() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"Pass1234567891234567\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "92c8b10157e05856af182a643de7dcea14472f74", "CP00000001", "P000000005",
																				"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																				"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																						"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																						"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																						"0" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("U123456789S123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("90d8a1f70237a59788130d8851ac4c8d486071e3"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));
	}

	/**
	 * パスワードが21文字で入力された場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("境界値テスト パスワード21文字で更新")
	public void passwordLengthCheckErrorPattern21() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"Pass12345678912345678\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "90d8a1f70237a59788130d8851ac4c8d486071e3", "CP00000001", "P000000005",
																						"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																						"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																								"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																								"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																								"0" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード半角英数字制限(半角数字なし)")
	public void passwordHarfWidthAlphanumUpperErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"あｐ１２３４５６\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "90d8a1f70237a59788130d8851ac4c8d486071e3", "CP00000001", "P000000005",
																								"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																								"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																										"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																										"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																										"0" };
						
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード半角英数字制限(数字なし)")
	public void passwordHarfWidthAlphanumUpperErrorPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"Password\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "90d8a1f70237a59788130d8851ac4c8d486071e3", "CP00000001", "P000000005",
																								"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																								"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																										"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																										"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																										"0" };
						
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード半角英数字制限(子文字なし)")
	public void passwordHarfWidthAlphanumUpperErrorPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"PASSWORD1\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "90d8a1f70237a59788130d8851ac4c8d486071e3", "CP00000001", "P000000005",
																								"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																								"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																										"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																										"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																										"0" };
						
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("パスワード半角英数字制限(大文字なし)")
	public void passwordHarfWidthAlphanumUpperErrorPattern4() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\"]");
		httpRequest.setParameter("json[password]", "[\"password1\"]");
		httpRequest.setParameter("json[post]", "[\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		
		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "90d8a1f70237a59788130d8851ac4c8d486071e3", "CP00000001", "P000000005",
																								"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																								"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																										"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																										"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																										"0" };
						
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * 全ての役職を選択した場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("役職更新")
	public void updatePostPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		// 準正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"admin\",\"general\",\"A123456789\",\"hanako\",\"taro\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\",\"一般ユーザー\",\"A12345678B12345678\",\"山田 花子\",\"山田 太郎\"]");
		httpRequest.setParameter("json[password]", "[\"Pass1234567891234567\",\"Password1\",\"Password1\",\"Password1\",\"Password1\"]");
		httpRequest.setParameter("json[post]", "[\"P000000001\",\"P000000002\",\"P000000005\",\"P000000004\",\"P000000003\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"1\",\"1\",\"0\",\"1\",\"1\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		
		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "90d8a1f70237a59788130d8851ac4c8d486071e3", "CP00000001", "P000000005",
																								"1" };
		String[] generalValue = { "general", "一般ユーザー", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000001",
																								"P000000004", "1" };
		String[] taroValue = { "taro", "山田 太郎", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002", "P000000001",
																										"1" };
		String[] hanakoValue = { "hanako", "山田 花子", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000002",
																										"P000000002", "1" };
		String[] aValue = { "A123456789", "A123456789B12345678", "70ccd9007338d6d81dd3b6271621b9cf9a97ea00", "CP00000003", "P000000003",
																										"0" };
						
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getUserName(), is("U123456789S123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPassword(), is("90d8a1f70237a59788130d8851ac4c8d486071e3"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
				.getAdminFlg(), is("1"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserId(), is("general"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getUserName(), is("一般ユーザー"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
				.getAdminFlg(), is("1"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getUserName(), is("A12345678B12345678"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
				.getAdminFlg(), is("0"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getUserName(), is("山田 花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
				.getAdminFlg(), is("1"));
		
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getUserName(), is("山田 太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
				.getAdminFlg(), is("1"));
	}
	
	/**
	 * 全ての権限を選択した場合、更新が完了すること。
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName("権限更新")
	public void updateAuthorityPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();
		// 準正常時
		httpRequest.setParameter("json[userId]", "[\"admin\",\"A123456789\"]");
		httpRequest.setParameter("json[userName]", "[\"U123456789S123456789\",\"A123456789B12345678\"]");
		httpRequest.setParameter("json[password]", "[\"Pass1234567891234567\",\"Password1\"]");
		httpRequest.setParameter("json[post]", "[\"P000000001\",\"P000000005\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"0\",\"1\"]");
		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "U123456789S123456789", "90d8a1f70237a59788130d8851ac4c8d486071e3", "CP00000001", "P000000001",
																				"1" };
		String[] generalValue = { "general", "一般", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000001",
																				"P000000002", "0" };
		String[] taroValue = { "taro", "太郎", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002", "P000000003",
																						"0" };
		String[] hanakoValue = { "hanako", "山田 花子", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002",
																						"P000000004", "0" };
		String[] aValue = { "A123456789", "A123456789B123456789", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000003", "P000000005",
																						"1" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateUserMstAction.destroy();

		// セッション取得
		HttpSession session = httpRequest.getSession();

		// 実行結果を確認
		// セッションに検索結果が格納されていることで更新に成功したこととする。
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getUserId(), is("admin"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getUserName(), is("U123456789S123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getPassword(), is("90d8a1f70237a59788130d8851ac4c8d486071e3"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getPost(), is("P000000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(0)
						.getAdminFlg(), is("0"));
				
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
						.getUserId(), is("general"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
						.getUserName(), is("一般ユーザー"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
						.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
						.getCompany(), is("CP00000001"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
						.getPost(), is("P000000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(1)
						.getAdminFlg(), is("1"));
				
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
						.getUserId(), is("A123456789"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
						.getUserName(), is("A123456789B12345678"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
						.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
						.getCompany(), is("CP00000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
						.getPost(), is("P000000005"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(2)
						.getAdminFlg(), is("1"));

		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
						.getUserId(), is("hanako"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
						.getUserName(), is("山田 花子"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
						.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
						.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
						.getPost(), is("P000000004"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(3)
						.getAdminFlg(), is("1"));
				
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
						.getUserId(), is("taro"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
						.getUserName(), is("山田 太郎"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
						.getPassword(), is("70ccd9007338d6d81dd3b6271621b9cf9a97ea00"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
						.getCompany(), is("CP00000002"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
						.getPost(), is("P000000003"));
		assertThat(((SearchResultUserListDTO) session.getAttribute("origin")).getSearchResultUserDTOList().get(4)
						.getAdminFlg(), is("1"));
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

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[userId]", "[\"\"]");
		httpRequest.setParameter("json[userName]", "[\"\"]");
		httpRequest.setParameter("json[password]", "[\"\"]");
		httpRequest.setParameter("json[post]", "[\"\"]");
		httpRequest.setParameter("json[adminFlg]", "[\"\"]");

		// セッション取得
		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);

		// 初期値を設定
		String[] adminValue = { "admin", "管理者", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000001", "P000000001",
												"1" };
		String[] generalValue = { "general", "一般", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000001",
												"P000000002", "0" };
		String[] taroValue = { "taro", "太郎", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002", "P000000003",
														"0" };
		String[] hanakoValue = { "hanako", "花子", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000002",
														"P000000004", "0" };
		String[] aValue = { "A123456789", "A123456789B123456789", "9D4E1E23BD5B727046A9E3B4B7DB57BD8D6EE684", "CP00000003", "P000000005",
														"1" };
				
		httpSession.setAttribute("origin", InitialValue.initialValueList(adminValue, generalValue,taroValue,hanakoValue,aValue));
		httpSession.setAttribute("searchCondition", InitialValue.searchCondition());

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateUserMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateUserMstAction.doPost(httpRequest, httpResponse);
	}

}
