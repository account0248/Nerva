package registprojecttest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.registproject.RegistProjectAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class RegistProjectActionTest {
	RegistProjectAction registProjectAction = new RegistProjectAction();

	@Test
	@DisplayName("全テスト実行")
	public void registProjectActionTest() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		//テストNo1.全項目入力
		allInputPattern();
		//テストNo2.完了日未入力
		projectCompleteDateRequiredPattern();
		//テストNo3.案件ID未入力
		projectIdRequiredErrorPattern();
		//テストNo4.案件ID全角入力
		projectIdharfWidthErrorPattern();
		//テストNo5.案件ID文字数超過
		projectIdLengthErrorPattern();
		//テストNo6.案件ID入力形式不正
		projectIdFormatErrorPattern();
		//テストNo7.案件ID重複
		projectIdExistErrorPattern();
		//テストNo8.案件名未入力
		projectNameRequiredErrorPattern();
		//テストNo9.案件名文字数超過
		projectNameLengthErrorPattern();
		//テストNo10.発注元未入力
		orderSourceRequiredErrorPattern();
		//テストNo11.発注元文字数超過
		orderSourceLengthErrorPattern();
		//テストNo12.業種未選択
		industryIdRequiredErrorPattern();
		
		//テストNo13.業種「金融」選択
		industryChoseFinancePattern();
		//テストNo14.業種「医療」選択
		industryChoseMedicalPattern();
		//テストNo15.業種「教育」選択
		industryChoseEducationPattern();
		//テストNo16.業種「情報通信」選択
		industryChoseTelecommunicationsPattern();
		//テストNo17.業種「製造」選択
		industryChoseManufacturingPattern();
		
		
		//テストNo18.業種DB未登録
		industryIdValidityErrorPattern();
		//テストNo19.案件開始日未入力
		projectStartDateRequiredErrorPattern();
		//テストNo20.案件開始日入力形式不正
		projectStartDateFormatErrorPattern();
		//テストNo21.案件完了日入力形式不正
		projectCompleteDateFormatErrorPattern();
		//テストNo22.案件開始日、案件完了日入力形式不正
		projectStartAndCompleteDateFormatErrorPattern();
		//テストNo23.案件完了日開始日以前
		projectCompleteDateAfterErrorPattern();
	}

	@Test
	@DisplayName("正常動作_全項目入力")
	public void allInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
				
		String userid = "admin";		
		String companyId = "CP00000001";
	
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000001");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userid);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String) httpSession.getAttribute("projectId"),is("P0000001"));
	}

	@Test
	@DisplayName("正常動作_完了日未入力")
	public void projectCompleteDateRequiredPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000002");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "");

		String userid = "admin";		
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userid);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String) httpSession.getAttribute("projectId"),is("P0000002"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件ID_未入力")
	public void projectIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userid = "admin";		
		String companyId = "CP00000001";	
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userid);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件IDはかならず入力してください。"));
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_案件ID_全角入力")
	public void projectIdharfWidthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "あああああ");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userid = "admin";
		String companyId = "CP00000001";
			
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userid);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件IDは半角英数字で入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件ID_文字数超過")
	public void projectIdLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000000001");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件IDは8文字で入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件ID_入力形式不正")
	public void projectIdFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P000000A");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件IDは半角英数字で形式通りに入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件ID_重複")
	public void projectIdExistErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000001");
		httpRequest.setParameter("projectName", "案件名UTExist");
		httpRequest.setParameter("orderSource", "発注元UTExist");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件IDは既に使われています。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件名_未入力")
	public void projectNameRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000003");
		httpRequest.setParameter("projectName", "");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件名はかならず入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件名_文字数超過")
	public void projectNameLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000003");
		httpRequest.setParameter("projectName", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件名は256文字以内で入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_発注元_未入力")
	public void orderSourceRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000003");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("発注元はかならず入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_発注元_文字数超過")
	public void orderSourceLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000003");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "aaaaaaaaaaaaaaaaaaaaa");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("発注元は20文字以内で入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_業種_未選択")
	public void industryIdRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000003");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("業種はかならず選択してください。"));
	}
	
	@Test
	@DisplayName("正常動作_業種_「金融」選択")
	public void industryChoseFinancePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000003");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String) httpSession.getAttribute("projectId"),is("P0000003"));
	}
	
	@Test
	@DisplayName("正常動作_業種_「医療」選択")
	public void industryChoseMedicalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000004");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000002");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String) httpSession.getAttribute("projectId"),is("P0000004"));
	}
	
	@Test
	@DisplayName("正常動作_業種_「教育」選択")
	public void industryChoseEducationPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000005");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000003");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String) httpSession.getAttribute("projectId"),is("P0000005"));
	}
	
	@Test
	@DisplayName("正常動作_業種_「情報通信」選択")
	public void industryChoseTelecommunicationsPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000006");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000004");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String) httpSession.getAttribute("projectId"),is("P0000006"));
	}
	
	@Test
	@DisplayName("正常動作_業種_「製造」選択")
	public void industryChoseManufacturingPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000007");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000005");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String) httpSession.getAttribute("projectId"),is("P0000007"));
	}
	
	
	

	@Test
	@DisplayName("エラーメッセージパターン_業種_DB未登録")
	public void industryIdValidityErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000008");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000006");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("業種は正しく選択してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件開始日_未入力")
	public void projectStartDateRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000008");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件開始日はかならず入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件開始日_入力形式不正")
	public void projectStartDateFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000008");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2");
		httpRequest.setParameter("projectCompleteDate", "2019-01-02");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件開始日は形式通りに入力してください。"));
		
	}



	@Test
	@DisplayName("エラーメッセージパターン_案件完了日_入力形式不正")
	public void projectCompleteDateFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000008");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-01");
		httpRequest.setParameter("projectCompleteDate", "2");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件完了日は形式通りに入力してください。"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_案件開始日案件完了日_入力形式不正")
	public void projectStartAndCompleteDateFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000008");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2");
		httpRequest.setParameter("projectCompleteDate", "3");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件開始日は形式通りに入力してください。"));
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(1),is("案件完了日は形式通りに入力してください。"));
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_案件完了日_案件開始日以前")
	public void projectCompleteDateAfterErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("projectId", "P0000008");
		httpRequest.setParameter("projectName", "案件名UT");
		httpRequest.setParameter("orderSource", "発注元UT");
		httpRequest.setParameter("industryId", "I000000001");
		httpRequest.setParameter("projectStartDate", "2019-01-02");
		httpRequest.setParameter("projectCompleteDate", "2019-01-01");

		String userId = "admin";
		String companyId = "CP00000001";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", userId);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registProjectAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(((List<String>) httpRequest.getAttribute("errorMsgList")).get(0),is("案件完了日は案件開始日と同日以降の日付を選択してください。"));
	}
}