package masterSkillTypeTest.searchSkillTypeTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterSkillType.searchSkillType.SearchSkillTypeDTO;
import jp.co.vaile.nerva.masterSkillType.searchSkillType.SearchSkillTypeMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchSkillTypeMstTest {
	SearchSkillTypeMstAction searchSkillTypeMstAction = new SearchSkillTypeMstAction();

	@Test
	@DisplayName("No.1 条件検索(ID)")
	public void idfiltereSearchSkillType() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000001");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// 実行クラスがservletのときはおまじないとして記述
		searchSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO("S000000001", "資格", "0");
		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeId(),is(searchSkillTypeDTO.getSkillTypeId()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeName(), is(searchSkillTypeDTO.getSkillTypeName()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getYearsDateOfAcquisition(), is(searchSkillTypeDTO.getYearsDateOfAcquisition()));

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.2 条件検索(スキル種別名)")
	public void namefiltereSearchSkillType() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "資格");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// 実行クラスがservletのときはおまじないとして記述
		searchSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO("S000000001", "資格", "0");

		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeId(),is(searchSkillTypeDTO.getSkillTypeId()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeName(), is(searchSkillTypeDTO.getSkillTypeName()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getYearsDateOfAcquisition(), is(searchSkillTypeDTO.getYearsDateOfAcquisition()));

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.3 条件検索(年数/取得日フラグ 年数)")
	public void flgfiltereYearsSearchSkillType() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "1");

		// 実行クラスがservletのときはおまじないとして記述
		searchSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);
		
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000002", "言語", "1");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000003", "DB", "1");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000004", "ツール", "1");
		
		List<SearchSkillTypeDTO> searchSkillTypeDTOList = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList.add(searchSkillTypeDTO3);
		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		for(int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getSkillTypeId(),is(searchSkillTypeDTOList.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getSkillTypeName(), is(searchSkillTypeDTOList.get(i).getSkillTypeName()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getYearsDateOfAcquisition(), is(searchSkillTypeDTOList.get(i).getYearsDateOfAcquisition()));
		}

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}
	
	@Test
	@DisplayName("No.4 条件検索(年数/取得日フラグ 取得日)")
	public void flgfiltereDateOfAcquisitionSearchSkillType() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "0");

		// 実行クラスがservletのときはおまじないとして記述
		searchSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);
		
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格", "0");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000005", "A1234567891234567890", "0");
		
		List<SearchSkillTypeDTO> searchSkillTypeDTOList = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList.add(searchSkillTypeDTO2);
		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		for(int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getSkillTypeId(),is(searchSkillTypeDTOList.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getSkillTypeName(), is(searchSkillTypeDTOList.get(i).getSkillTypeName()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getYearsDateOfAcquisition(), is(searchSkillTypeDTOList.get(i).getYearsDateOfAcquisition()));
		}

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.5 条件検索(全入力)")
	public void filtereSearchSkillType() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000001");
		httpRequest.setParameter("json[skillTypeName]", "資格");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "0");

		// 実行クラスがservletのときはおまじないとして記述
		searchSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);
		
		SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO("S000000001", "資格", "0");
		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeId(),is(searchSkillTypeDTO.getSkillTypeId()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeName(), is(searchSkillTypeDTO.getSkillTypeName()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getYearsDateOfAcquisition(), is(searchSkillTypeDTO.getYearsDateOfAcquisition()));


		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.6 全件検索")
	public void allSearchSkillType() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// 実行クラスがservletのときはおまじないとして記述
		searchSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);
		
		SearchSkillTypeDTO searchSkillTypeDTO1 = new SearchSkillTypeDTO("S000000001", "資格", "0");
		SearchSkillTypeDTO searchSkillTypeDTO2 = new SearchSkillTypeDTO("S000000002", "言語", "1");
		SearchSkillTypeDTO searchSkillTypeDTO3 = new SearchSkillTypeDTO("S000000003", "DB", "1");
		SearchSkillTypeDTO searchSkillTypeDTO4 = new SearchSkillTypeDTO("S000000004", "ツール", "1");
		SearchSkillTypeDTO searchSkillTypeDTO5 = new SearchSkillTypeDTO("S000000005", "A1234567891234567890", "0");
		
		List<SearchSkillTypeDTO> searchSkillTypeDTOList = new ArrayList<SearchSkillTypeDTO>();
		searchSkillTypeDTOList.add(searchSkillTypeDTO1);
		searchSkillTypeDTOList.add(searchSkillTypeDTO2);
		searchSkillTypeDTOList.add(searchSkillTypeDTO3);
		searchSkillTypeDTOList.add(searchSkillTypeDTO4);
		searchSkillTypeDTOList.add(searchSkillTypeDTO5);
		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		for(int i = 0; i < ((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).size(); i++) {
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getSkillTypeId(),is(searchSkillTypeDTOList.get(i).getSkillTypeId()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getSkillTypeName(), is(searchSkillTypeDTOList.get(i).getSkillTypeName()));
			assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(i).getYearsDateOfAcquisition(), is(searchSkillTypeDTOList.get(i).getYearsDateOfAcquisition()));
		}
		
		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.7 検索結果0件チェック")
	public void searchSkillTypeResultCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000010");
		httpRequest.setParameter("json[skillTypeName]", "資格");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "0");

		// サーブレット初期化
		searchSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.8 スキル種別ID文字数チェック") // 9文字
	public void skillTypeIdLengthCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S00000001");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// サーブレット初期化
		searchSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.9 スキル種別ID文字数チェック") // 10文字
	public void skillTypeIdLengthCheck2() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S000000001");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// サーブレット初期化
		searchSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションにログイン情報が格納されていることでログインに成功したこととする。
		SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO("S000000001", "資格", "0");

		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeId(),is(searchSkillTypeDTO.getSkillTypeId()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeName(), is(searchSkillTypeDTO.getSkillTypeName()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getYearsDateOfAcquisition(), is(searchSkillTypeDTO.getYearsDateOfAcquisition()));


		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.10 スキル種別ID文字数チェック") // 11文字
	public void skillTypeIdLengthCheck3() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S0000000001");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// サーブレット初期化
		searchSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.11 スキル種別ID半角英数字チェック")
	public void skillTypeIdMatchCheck() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "S00000000あ");
		httpRequest.setParameter("json[skillTypeName]", "");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// サーブレット初期化
		searchSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.12 スキル種別名文字数チェック") // 20文字
	public void skillTypeNameLengthCheck2() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "A1234567891234567890");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// サーブレット初期化
		searchSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO("S000000005", "A1234567891234567890", "0");

		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeId(),is(searchSkillTypeDTO.getSkillTypeId()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeName(), is(searchSkillTypeDTO.getSkillTypeName()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getYearsDateOfAcquisition(), is(searchSkillTypeDTO.getYearsDateOfAcquisition()));

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}

	@Test
	@DisplayName("No.13 スキル種別名文字数チェック") // 21文字
	public void skillTypeNameLengthCheck3() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "A12345678912345678901");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// サーブレット初期化
		searchSkillTypeMstAction.init(new MockServletConfig());

		// テスト対象のメソッドを実行
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}
	
	@Test
	@DisplayName("No.14 条件検索(スキル種別名 部分一致)")
	public void namePartfiltereSearchSkillType() throws ServletException, IOException {

		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// セッションをセット
		httpRequest.setSession(new MockHttpSession());
		//セッション取得
		HttpSession httpSession = httpRequest.getSession();

		// パラメータに入力内容を設定
		httpRequest.setParameter("json[skillTypeId]", "");
		httpRequest.setParameter("json[skillTypeName]", "資");
		httpRequest.setParameter("json[yearsDateOfAcquisition]", "");

		// 実行クラスがservletのときはおまじないとして記述
		searchSkillTypeMstAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchSkillTypeMstAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		//セッションに検索結果リストが格納されていることで検索に成功
		SearchSkillTypeDTO searchSkillTypeDTO = new SearchSkillTypeDTO("S000000001", "資格", "0");

		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeId(),is(searchSkillTypeDTO.getSkillTypeId()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getSkillTypeName(), is(searchSkillTypeDTO.getSkillTypeName()));
		assertThat(((List<SearchSkillTypeDTO>) httpSession.getAttribute("searchSkillTypeDTOList")).get(0).getYearsDateOfAcquisition(), is(searchSkillTypeDTO.getYearsDateOfAcquisition()));

		// サーブレット破棄
		searchSkillTypeMstAction.destroy();

	}
}
