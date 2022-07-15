package masterSkillTypeTest.updateSkillTypeTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterSkillType.searchSkillType.SearchSkillTypeDTO;
import jp.co.vaile.nerva.masterSkillType.updateSkillType.UpdateSkillTypeMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateSkillTypeMstTest {
	UpdateSkillTypeMstAction updateSkillTypeMstAction = new UpdateSkillTypeMstAction();

	@Test
	@DisplayName("No1 変更なし")
	public void testNoChangeUpdateSkillType() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[]");
		httpRequest.setParameter("json[skillTypeName]", "[]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格", "0");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語", "1");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "DB", "1");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000001", "資格", "0");
		SearchSkillTypeDTO searchSkillTypeDTO6 = new SearchSkillTypeDTO("S000000002", "言語", "1");
		SearchSkillTypeDTO searchSkillTypeDTO7 = new SearchSkillTypeDTO("S000000003", "DB", "1");
		SearchSkillTypeDTO searchSkillTypeDTO8 = new SearchSkillTypeDTO("S000000004", "ツール", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList2 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList2.add(searchSkillTypeDTO5);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO6);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO7);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO8);
		// 実行結果を確認
		// セッションに検索結果リストが格納されていることで更新に成功
		for (int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList"))
				.size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeId(), is(searchSkillTypeDTOList2.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeName(), is(searchSkillTypeDTOList2.get(i).getSkillTypeName()));
			assertThat(
					((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
							.getYearsDateOfAcquisition(),
					is(searchSkillTypeDTOList2.get(i).getYearsDateOfAcquisition()));
		}

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No2 一件更新")
	public void testInputOneUpdateSkillType() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000002\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"プログラミング言語\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"0\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格", "0");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語", "1");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "DB", "1");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000001", "資格", "0");
		SearchSkillTypeDTO searchSkillTypeDTO6 = new SearchSkillTypeDTO("S000000002", "プログラミング言語", "0");
		SearchSkillTypeDTO searchSkillTypeDTO7 = new SearchSkillTypeDTO("S000000003", "DB", "1");
		SearchSkillTypeDTO searchSkillTypeDTO8 = new SearchSkillTypeDTO("S000000004", "ツール", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList2 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList2.add(searchSkillTypeDTO5);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO6);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO7);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO8);
		// 実行結果を確認
		// セッションに検索結果リストが格納されていることで更新に成功
		for (int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList"))
				.size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeId(), is(searchSkillTypeDTOList2.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeName(), is(searchSkillTypeDTOList2.get(i).getSkillTypeName()));
			assertThat(
					((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
							.getYearsDateOfAcquisition(),
					is(searchSkillTypeDTOList2.get(i).getYearsDateOfAcquisition()));
		}

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No3 全件更新")
	public void testInputAllUpdateSkillType() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000001\",\"S000000002\",\"S000000003\",\"S000000004\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"資格2\",\"言語2\",\"DB2\",\"ツール2\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"1\",\"0\",\"0\",\"0\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格", "0");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "プログラミング言語", "1");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "DB", "1");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO6 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO7 = new SearchSkillTypeDTO("S000000003", "DB2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO8 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList2 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList2.add(searchSkillTypeDTO5);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO6);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO7);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO8);
		// 実行結果を確認
		// セッションに検索結果リストが格納されていることで更新に成功
		for (int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList"))
				.size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeId(), is(searchSkillTypeDTOList2.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeName(), is(searchSkillTypeDTOList2.get(i).getSkillTypeName()));
			assertThat(
					((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
							.getYearsDateOfAcquisition(),
					is(searchSkillTypeDTOList2.get(i).getYearsDateOfAcquisition()));
		}

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No4 未入力テスト")
	public void errorCheckUpdateSkillTypeTest1() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000002\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"0\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "DB2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("No5 境界値テスト　文字数20")
	public void errorCheckUpdateSkillTypeTest2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000003\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"A1234567891234567890\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"0\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "DB2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO6 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO7 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO8 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList2 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList2.add(searchSkillTypeDTO5);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO6);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO7);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO8);
		// 実行結果を確認
		// セッションに検索結果リストが格納されていることで更新に成功
		for (int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList"))
				.size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeId(), is(searchSkillTypeDTOList2.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeName(), is(searchSkillTypeDTOList2.get(i).getSkillTypeName()));
			assertThat(
					((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
							.getYearsDateOfAcquisition(),
					is(searchSkillTypeDTOList2.get(i).getYearsDateOfAcquisition()));
		}

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No6 境界値テスト　文字数21")
	public void errorCheckUpdateSkillTypeTest3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000004\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"A12345678912345678901\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"0\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No7 重複テスト")
	public void errorCheckUpdateSkillTypeTest4() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000004\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"資格2\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"1\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No8 年数/取得日のみ更新 年数")
	public void testInputYearsUpdateSkillType() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000002\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"言語2\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"1\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO6 = new SearchSkillTypeDTO("S000000002", "言語2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO7 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO8 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList2 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList2.add(searchSkillTypeDTO5);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO6);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO7);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO8);
		// 実行結果を確認
		// セッションに検索結果リストが格納されていることで更新に成功
		for (int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList"))
				.size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeId(), is(searchSkillTypeDTOList2.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeName(), is(searchSkillTypeDTOList2.get(i).getSkillTypeName()));
			assertThat(
					((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
							.getYearsDateOfAcquisition(),
					is(searchSkillTypeDTOList2.get(i).getYearsDateOfAcquisition()));
		}
		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No9 年数/取得日のみ更新 取得日")
	public void testInputDateOfAcquisitionUpdateSkillType() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000002\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"言語2\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"0\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO6 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO7 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO8 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList2 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList2.add(searchSkillTypeDTO5);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO6);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO7);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO8);
		// 実行結果を確認
		// セッションに検索結果リストが格納されていることで更新に成功
		for (int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList"))
				.size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeId(), is(searchSkillTypeDTOList2.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeName(), is(searchSkillTypeDTOList2.get(i).getSkillTypeName()));
			assertThat(
					((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
							.getYearsDateOfAcquisition(),
					is(searchSkillTypeDTOList2.get(i).getYearsDateOfAcquisition()));
		}
		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No10 複数項目の名称を同一")
	public void errorCheckUpdateSkillTypeTest5() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000001\",\"S000000002\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"プログラミング言語\",\"プログラミング言語\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"1\",\"0\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール2", "0");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList1.add(searchSkillTypeDTO4);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No11 事前データの一部を検索し、そのデータを対象に正しい入力値で更新")
	public void testSearchPartUpdateSkillType() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000004\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"外国語\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"1\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000004", "ツール2", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000001", "資格2", "1");
		SearchSkillTypeDTO searchSkillTypeDTO6 = new SearchSkillTypeDTO("S000000002", "言語2", "0");
		SearchSkillTypeDTO searchSkillTypeDTO7 = new SearchSkillTypeDTO("S000000003", "A1234567891234567890", "0");
		SearchSkillTypeDTO searchSkillTypeDTO8 = new SearchSkillTypeDTO("S000000004", "外国語", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList2 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList2.add(searchSkillTypeDTO5);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO6);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO7);
		searchSkillTypeDTOList2.add(searchSkillTypeDTO8);
		// 実行結果を確認
		// セッションに検索結果リストが格納されていることで更新に成功
		for (int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList"))
				.size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeId(), is(searchSkillTypeDTOList2.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
					.getSkillTypeName(), is(searchSkillTypeDTOList2.get(i).getSkillTypeName()));
			assertThat(
					((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i)
							.getYearsDateOfAcquisition(),
					is(searchSkillTypeDTOList2.get(i).getYearsDateOfAcquisition()));
		}

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}

	@Test
	@DisplayName("No12 事前データの一部を検索し、そのデータを対象に不正な入力値で更新")
	public void errorCheckUpdateSkillTypeTest6() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[skillTypeId]", "[\"S000000004\"]");
		httpRequest.setParameter("json[skillTypeName]", "[\"資格2\"]");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "[\"1\"]");

		// ログイン者ID
		String loginUserId = "admin";
		// 検索結果
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000004", "外国語", "1");
		// 検索結果リスト
		List<SearchSkillTypeDTO> searchSkillTypeDTOList1 = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList1.add(searchSkillTypeDTO1);
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("searchSkillTypeDTO", searchSkillTypeDTO1);
		httpSession.setAttribute("searchSkillTypeDTOList", searchSkillTypeDTOList1);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		updateSkillTypeMstAction.destroy();
	}
}
