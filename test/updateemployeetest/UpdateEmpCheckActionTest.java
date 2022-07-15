package updateemployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.updateEmployee.RegistEmpWorkExpDTO;
import jp.co.vaile.nerva.updateEmployee.RegistSkillInfoDTO;
import jp.co.vaile.nerva.updateEmployee.UpdateEmpCheckAction;
import jp.co.vaile.nerva.updateEmployee.UpdateEmpInfoDTO;
import jp.co.vaile.nerva.updateEmployee.UpdateEmpWorkExpDTO;
import jp.co.vaile.nerva.updateEmployee.UpdateSkillInfoDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateEmpCheckActionTest {
	UpdateEmpCheckAction updateEmpCheckAction = new UpdateEmpCheckAction();
	EmpInfoDTO empInfoDTO = new EmpInfoDTO();

	@Test
	@DisplayName("全テスト実行")
	public void updateEmpCheckActionTest()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		// テストNo1.全項目入力
		allInputPattern();
		// テストNo2.保持スキル・業務経験なし
		noSkillExperiencePattern();
		// テストNo3.保持スキル・業務経験登録行なし
		norRegistSkillExperiencePattern();
		// テストNo4.保持スキル・業務経験更新行なし
		noUpdateSkillExperiencePattern();
		// テストNo5.従業員情報未入力
		employeeInfoRequiredErrorPattern();
		// テストNo6.従業員情報最大文字数
		employeeInfoMaxLengthPattern();
		// テストNo7.従業員情報文字数超過
		employeeInfoLengthErrorPattern();
		// テストNo8.従業員情報DB未登録
		employeeInfoValidityErrorPattern();
		// テストNo9.従業員情報入力形式不正
		employeeInfoFormatErrorPattern();
		// テストNo10.郵便番号指定文字数以下
		postalCodeLengthDownErrorPattern();
		// テストNo11.郵便番号指定文字数超過
		postalCodeLengthOverErrorPattern();
		// テストNo12.メールアドレス形式不正
		mailFormatErrorPattern();
		// テストNo13.保有スキル欄（更新行）スキル種別未選択
		updateSkillTypeRequiredErrorPattern();
		// テストNo14.保有スキル欄（更新行）スキル内容未入力
		updateSkillDetailRequiredErrorPattern();
		// テストNo15.保有スキル欄（更新行）スキル種別DB未登録
		updateSkillTypeValidityErrorPattern();
		// テストNo16.保有スキル欄（更新行）スキル内容最大文字数
		updateSkillDetailMaxLengthPattern();
		// テストNo17.保有スキル欄（更新行）スキル内容文字数超過
		updateSkillDetailLengthErrorPattern();
		// テストNo18.保有スキル欄（更新行）経験年数入力不正
		updateExperienceYearsInputErrorPattern();
		// テストNo19.保有スキル欄（更新行）経験年数未入力
		updateExperienceYearsRequiredErrorPattern();
		// テストNo20.保有スキル欄（更新行）経験年数最大文字数
		updateExperienceYearsMaxLengthPattern();
		// テストNo21.保有スキル欄（更新行）経験年数指定文字数超過
		updateExperienceYearsLengthErrorPattern();
		// テストNo22.保有スキル欄（更新行）経験年数最小文字数
		updateExperienceYearsMinLengthPattern();
		// テストNo23.保有スキル欄（更新行）取得年月入力不正
		updateAcquisitionYearMonthInputErrorPattern();
		// テストNo24.保有スキル欄（更新行）取得年月未入力
		updateAcquisitionYearMonthRequiredErrorPattern();
		// テストNo25.保有スキル欄（更新行）取得年月形式不正
		updateAcquisitionYearMonthFormatErrorPattern();
		// テストNo26.保有スキル欄（登録行）スキル種別未選択
		registSkillTypeRequiredErrorPattern();
		// テストNo27.保有スキル欄（登録行）スキル内容未入力
		registSkillDetailRequiredErrorPattern();
		// テストNo28.保有スキル欄（登録行）スキル種別DB未登録
		registSkillTypeValidityErrorPattern();
		// テストNo29.保有スキル欄（登録行）スキル内容最大文字数
		registSkillDetailMaxLengthPattern();
		// テストNo30.保有スキル欄（登録行）スキル内容文字数超過
		registSkillDetailLengthErrorPattern();
		// テストNo31.保有スキル欄（登録行）経験年数入力不正
		registExperienceYearsInputErrorPattern();
		// テストNo32.保有スキル欄（登録行）経験年数未入力
		registExperienceYearsRequiredErrorPattern();
		// テストNo33.保有スキル欄（登録行）経験年数最大文字数
		registExperienceYearsMaxLengthPattern();
		// テストNo34.保有スキル欄（登録行）経験年数指定文字数超過
		registExperienceYearsLengthErrorPattern();
		// テストNo35.保有スキル欄（登録行）経験年数最小文字数
		registExperienceYearsMinLengthPattern();
		// テストNo36.保有スキル欄（登録行）取得年月入力不正
		registAcquisitionYearMonthInputErrorPattern();
		// テストNo37.保有スキル欄（登録行）取得年月未入力
		registAcquisitionYearMonthRequiredErrorPattern();
		// テストNo38.保有スキル欄（登録行）取得年月形式不正
		registAcquisitionYearMonthFormatErrorPattern();
		// テストNo39.業務経験欄（更新行）所属開始日未入力
		updateTeamBelongStartDateRequiredErrorPattern();
		// テストNo40.業務経験欄（更新行）所属完了日未入力
		updateNoTeamBelongCompleteDatePattern();
		// テストNo41.業務経験欄（更新行）日付形式不正
		updateWorkExpDateFormatErrorPattern();
		// テストNo42.業務経験欄（更新行）配属完了日＜所属完了日入力
		updateTeamBelongCompleteDateAfterErrorPattern();
		// テストNo43.業務経験欄（更新行）配属完了日＝所属完了日入力
		updateTeamBelongCompleteDateEqualPattern();
		// テストNo44.業務経験欄（登録行）業務経験未選択
		registWorkExpRequiredErrorPattern();
		// テストNo45.業務経験欄（登録行）業務経験DB未登録
		registWorkExpValidityErrorPattern();
		// テストNo46.業務経験欄（登録行）チームリーダー既存在
		roleIdLeaderErrorPattern();
		// テストNo47.業務経験欄（登録行）月単価未入力
		noMonthlyUnitPricePattern();
		// テストNo48.業務経験欄（登録行）月単価全角入力
		monthlyUnitPriceHarfWidthErrorPattern();
		// テストNo49.業務経験欄（登録行）月単価最大文字数
		monthlyUnitPriceMaxLengthPattern();
		// テストNo50.業務経験欄（登録行）月単価文字数超過
		monthlyUnitPriceLengthErrorPattern();
		// テストNo51.業務経験欄（登録行）所属開始日未入力
		registTeamBelongStartDateRequiredErrorPattern();
		// テストNo52.業務経験欄（登録行）所属完了日未入力
		registNoTeamBelongCompleteDatePattern();
		// テストNo53.業務経験欄（登録行）日付形式不正
		registWorkExpDateFormatErrorPattern();
		// テストNo54.業務経験欄（登録行）配属完了日＜所属完了日入力
		registTeamBelongCompleteDateAfterErrorPattern();
		// テストNo55.業務経験欄（登録行）配属完了日＝所属完了日入力
		registTeamBelongCompleteDateEqualPattern();
		// テストNo56.業務経験欄（登録行）既移管申請
		transferApplicationErrorPattern();
		// テストNo57.業務経験欄（登録行）移管申請チーム所属部長＝ログインユーザ
		transferApplicationTeamManagerEqualPattern();
		// テストNo58.業務経験欄（登録行）移管申請チーム所属部長!＝ログインユーザ
		transferApplicationTeamManagerNotEqualPattern();
		// テストNo59.業務経験欄（登録行）既複数移管申請
		transferApplicationPluralErrorPattern();
		// テストNo60.業務経験欄（更新行）同列所属開始日＞所属完了日入力
		updateTeamBelongCompleteDateSomeBeforeErrorPattern();
		// テストNo61.業務経験欄（更新行）同列所属開始日＝所属完了日入力
		updateTeamBelongCompleteDateSomeEqualPattern();
		// テストNo62.業務経験欄（登録行）同列所属開始日＞所属完了日入力
		registTeamBelongCompleteDateSomeBeforeErrorPattern();
		// テストNo63.業務経験欄（登録行）同列所属開始日＝所属完了日入力
		registTeamBelongCompleteDateSomeEqualErrorPattern();
		// テストNo64.業務経験欄（更新行）前列所属開始日＞所属開始日入力
		updateTeamBelongStartDateBackStaBeforeErrorPattern();
		// テストNo65.業務経験欄（更新行）前列所属開始日＝所属開始日入力
		updateTeamBelongStartDateBackStaEqualErrorPattern();
		// テストNo66.業務経験欄（登録行）前列所属開始日＞所属開始日入力
		registTeamBelongStartDateBackStaBeforeErrorPattern();
		// テストNo67.業務経験欄（登録行）前列所属開始日＝所属開始日入力
		registTeamBelongStartDateBackStaEqualErrorPattern();
		// テストNo68.業務経験欄（更新行＋登録行）前列所属開始日＞所属開始日入力
		teamBelongStartDateBackBeforeStaErrorPattern();
		// テストNo69.業務経験欄（更新行＋登録行）前列所属開始日＝所属開始日入力
		teamBelongStartDateBackStaEqualErrorPattern();
		// テストNo70.業務経験欄（更新行）前列所属完了日＞所属開始日入力
		updateTeamBelongStartDateBackCmpBeforeErrorPattern();
		// テストNo71.業務経験欄（更新行）前列所属完了日＝所属開始日入力
		updateTeamBelongStartDateBackCmpEqualPattern();
		// テストNo72.業務経験欄（登録行）前列所属完了日＞所属開始日入力
		registTeamBelongStartDateBackCmpBeforeErrorPattern();
		// テストNo73.業務経験欄（登録行）前列所属完了日＝所属開始日入力
		registTeamBelongStartDateBackCmpEqualPattern();
		// テストNo74.業務経験欄（更新行＋登録行）前列所属完了日＞所属開始日入力
		teamBelongStartDateBackCmpBeforeErrorPattern();
		// テストNo75.業務経験欄（更新行＋登録行）前列所属完了日＝所属開始日入力
		teamBelongStartDateBackCmpEqualPattern();
		// テストNo76.業務経験欄チーム配属完了日無し
		noAssignCompleteDatePattern();
		// テストNo77.業務経験欄情報（更新行）DB未登録
		noWorkExpErrorPattern();
		// テストNo78.例外動作
		exceptionPattern();
	}

	@Test
	@DisplayName("正常動作_全項目入力")
	public void allInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "49");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "50");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7100");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000001");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "admin";
		String employeeId = "V000000001";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateSkillInfoDTO updateSkillInfoDTO1 = new UpdateSkillInfoDTO(49, "S000000001", "基本情報処理技術者", "-", "2013-10");
		UpdateSkillInfoDTO updateSkillInfoDTO2 = new UpdateSkillInfoDTO(50, "S000000002", "java", "6年", "-");

		List<UpdateSkillInfoDTO> updateSkillDTOList = new ArrayList<UpdateSkillInfoDTO>();

		updateSkillDTOList.add(updateSkillInfoDTO1);
		updateSkillDTOList.add(updateSkillInfoDTO2);

		for (int i = 0; i < ((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillInfoId(), is(updateSkillDTOList.get(i).getSkillInfoId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(updateSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(updateSkillDTOList.get(i).getSkillDetail()));
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(updateSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(updateSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}

		RegistSkillInfoDTO registSkillInfoDTO1 = new RegistSkillInfoDTO("S000000001", "応用情報処理技術者", "-", "2016-10");
		RegistSkillInfoDTO registSkillInfoDTO2 = new RegistSkillInfoDTO("S000000003", "oracle", "6年", "-");

		List<RegistSkillInfoDTO> registSkillDTOList = new ArrayList<RegistSkillInfoDTO>();

		registSkillDTOList.add(registSkillInfoDTO1);
		registSkillDTOList.add(registSkillInfoDTO2);

		for (int i = 0; i < ((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(registSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(registSkillDTOList.get(i).getSkillDetail()));
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(registSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(registSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}

		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7100, "2011-06-01", "2013-04-29");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(
					((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));

		}

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000001",
				"500000", "2013-07-01", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(
					((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("正常動作_保持スキル・業務経験なし")
	public void noSkillExperiencePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));
	}

	@Test
	@DisplayName("正常動作_保持スキル・業務経験登録行なし")
	public void norRegistSkillExperiencePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "49");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "-");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "50");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7100");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000001";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateSkillInfoDTO updateSkillInfoDTO1 = new UpdateSkillInfoDTO(49, "S000000001", "基本情報処理技術者", "-", "2013-10");
		UpdateSkillInfoDTO updateSkillInfoDTO2 = new UpdateSkillInfoDTO(50, "S000000002", "java", "6年", "-");

		List<UpdateSkillInfoDTO> updateSkillDTOList = new ArrayList<UpdateSkillInfoDTO>();

		updateSkillDTOList.add(updateSkillInfoDTO1);
		updateSkillDTOList.add(updateSkillInfoDTO2);

		for (int i = 0; i < ((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillInfoId(), is(updateSkillDTOList.get(i).getSkillInfoId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(updateSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(updateSkillDTOList.get(i).getSkillDetail()));
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(updateSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(updateSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}

		}
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7100, "2011-06-01", "2013-04-29");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(
					((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("正常動作_保持スキル・業務経験更新行なし")
	public void noUpdateSkillExperiencePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistSkillInfoDTO registSkillInfoDTO1 = new RegistSkillInfoDTO("S000000001", "応用情報処理技術者", "-", "2016-10");
		RegistSkillInfoDTO registSkillInfoDTO2 = new RegistSkillInfoDTO("S000000003", "oracle", "6年", "-");

		List<RegistSkillInfoDTO> registSkillDTOList = new ArrayList<RegistSkillInfoDTO>();

		registSkillDTOList.add(registSkillInfoDTO1);
		registSkillDTOList.add(registSkillInfoDTO2);

		for (int i = 0; i < ((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(registSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(registSkillDTOList.get(i).getSkillDetail()));
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(registSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(registSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-07-01", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(
					((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_従業員情報未入力")
	public void employeeInfoRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "");
		httpRequest.setParameter("json[employeeInfo][office]", "");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "");
		httpRequest.setParameter("json[employeeInfo][postId]", "");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "");
		httpRequest.setParameter("json[employeeInfo][address]", "");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "");
		httpRequest.setParameter("json[employeeInfo][mail]", "");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_従業員情報最大文字数")
	public void employeeInfoMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "ああああああああああああああああああああ");
		httpRequest.setParameter("json[employeeInfo][office]", "ああああああああああああああああああああ");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]",
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]",
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(),
				is("ああああああああああああああああああああ"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(),
				is("ああああああああああああああああああああ"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is(
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@me.jp"));
	}

	@Test
	@DisplayName("エラーメッセージパターン_従業員情報文字数超過")
	public void employeeInfoLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "あああああああああああああああああああああ");
		httpRequest.setParameter("json[employeeInfo][office]", "あああああああああああああああああああああ");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]",
				"あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222222");
		httpRequest.setParameter("json[employeeInfo][mail]",
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_従業員情報DB未登録")
	public void employeeInfoValidityErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000009");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000009");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_従業員情報入力形式不正")
	public void employeeInfoFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "ああああああああ");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "00011112222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_郵便番号指定文字数以下")
	public void postalCodeLengthDownErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_郵便番号文字数超過")
	public void postalCodeLengthOverErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "1111-22222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_メールアドレス形式不正")
	public void mailFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taromejp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）スキル種別未選択")
	public void updateSkillTypeRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）スキル内容未入力")
	public void updateSkillDetailRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）スキル種別DB未登録")
	public void updateSkillTypeValidityErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000005");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_保有スキル欄（更新行）スキル内容最大文字数")
	public void updateSkillDetailMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]",
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎３"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateSkillInfoDTO updateSkillInfoDTO1 = new UpdateSkillInfoDTO(51, "S000000001", "基本情報処理技術者", "-", "2013-10");
		UpdateSkillInfoDTO updateSkillInfoDTO2 = new UpdateSkillInfoDTO(52, "S000000002",
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ",
				"6年", "-");

		List<UpdateSkillInfoDTO> updateSkillDTOList = new ArrayList<UpdateSkillInfoDTO>();

		updateSkillDTOList.add(updateSkillInfoDTO1);
		updateSkillDTOList.add(updateSkillInfoDTO2);

		for (int i = 0; i < ((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillInfoId(), is(updateSkillDTOList.get(i).getSkillInfoId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(updateSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(updateSkillDTOList.get(i).getSkillDetail()));
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(updateSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(updateSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）スキル内容文字数超過")
	public void updateSkillDetailLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]",
				"あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）経験年数入力不正")
	public void updateExperienceYearsInputErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		//従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");
		
		//保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");
		
		//保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");
		
		//業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");
		
		//業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");
		
		//セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）経験年数未入力")
	public void updateExperienceYearsRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_保有スキル欄（更新行）経験年数最大文字数")
	public void updateExperienceYearsMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "60年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎３"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateSkillInfoDTO updateSkillInfoDTO1 = new UpdateSkillInfoDTO(51, "S000000001", "基本情報処理技術者", "-", "2013-10");
		UpdateSkillInfoDTO updateSkillInfoDTO2 = new UpdateSkillInfoDTO(52, "S000000002", "java", "60年", "-");

		List<UpdateSkillInfoDTO> updateSkillDTOList = new ArrayList<UpdateSkillInfoDTO>();

		updateSkillDTOList.add(updateSkillInfoDTO1);
		updateSkillDTOList.add(updateSkillInfoDTO2);

		for (int i = 0; i < ((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillInfoId(), is(updateSkillDTOList.get(i).getSkillInfoId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(updateSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(updateSkillDTOList.get(i).getSkillDetail()));
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(updateSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(updateSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）経験年数指定文字数超過")
	public void updateExperienceYearsLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "600年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_保有スキル欄（更新行）経験年数最小文字数")
	public void updateExperienceYearsMinLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎３"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateSkillInfoDTO updateSkillInfoDTO1 = new UpdateSkillInfoDTO(51, "S000000001", "基本情報処理技術者", "-", "2013-10");
		UpdateSkillInfoDTO updateSkillInfoDTO2 = new UpdateSkillInfoDTO(52, "S000000002", "java", "年", "-");

		List<UpdateSkillInfoDTO> updateSkillDTOList = new ArrayList<UpdateSkillInfoDTO>();

		updateSkillDTOList.add(updateSkillInfoDTO1);
		updateSkillDTOList.add(updateSkillInfoDTO2);

		for (int i = 0; i < ((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillInfoId(), is(updateSkillDTOList.get(i).getSkillInfoId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(updateSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(updateSkillDTOList.get(i).getSkillDetail()));
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(updateSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(updateSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<UpdateSkillInfoDTO>) httpSession.getAttribute("updateSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）取得年月入力不正")
	public void updateAcquisitionYearMonthInputErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		//従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");
		
		//保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2013-10");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "2011-10");
		
		//保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");
		
		//業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");
		
		//業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");
		
		//セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）取得年月未入力")
	public void updateAcquisitionYearMonthRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "2011-10");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（更新行）取得年月形式不正")
	public void updateAcquisitionYearMonthFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎３");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "2");
		httpRequest.setParameter("json[updateSkill][0][skillInfoId]", "51");
		httpRequest.setParameter("json[updateSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[updateSkill][0][skillDetail]", "基本情報処理技術者");
		httpRequest.setParameter("json[updateSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[updateSkill][0][acquisitionYearMonth]", "2011-100");
		httpRequest.setParameter("json[updateSkill][1][skillInfoId]", "52");
		httpRequest.setParameter("json[updateSkill][1][skillTypeId]", "S000000002");
		httpRequest.setParameter("json[updateSkill][1][skillDetail]", "java");
		httpRequest.setParameter("json[updateSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[updateSkill][1][acquisitionYearMonth]", "2011-10");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000003";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）スキル種別未選択")
	public void registSkillTypeRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）スキル内容未入力")
	public void registSkillDetailRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）スキル種別DB未登録")
	public void registSkillTypeValidityErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000005");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_保有スキル欄（登録行）スキル内容最大文字数")
	public void registSkillDetailMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]",
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistSkillInfoDTO registSkillInfoDTO1 = new RegistSkillInfoDTO("S000000001", "応用情報処理技術者", "-", "2016-10");
		RegistSkillInfoDTO registSkillInfoDTO2 = new RegistSkillInfoDTO("S000000003",
				"ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ",
				"6年", "-");

		List<RegistSkillInfoDTO> registSkillDTOList = new ArrayList<RegistSkillInfoDTO>();

		registSkillDTOList.add(registSkillInfoDTO1);
		registSkillDTOList.add(registSkillInfoDTO2);

		for (int i = 0; i < ((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(registSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(registSkillDTOList.get(i).getSkillDetail()));
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(registSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(registSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}

	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）スキル内容文字数超過")
	public void registSkillDetailLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]",
				"あああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）経験年数入力不正")
	public void registExperienceYearsInputErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		//従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");
		
		//保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");
		
		//保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");
		
		//業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");
		
		//業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");
		
		//セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）経験年数未入力")
	public void registExperienceYearsRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_保有スキル欄（登録行）経験年数最大文字数")
	public void registExperienceYearsMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "60年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistSkillInfoDTO registSkillInfoDTO1 = new RegistSkillInfoDTO("S000000001", "応用情報処理技術者", "-", "2016-10");
		RegistSkillInfoDTO registSkillInfoDTO2 = new RegistSkillInfoDTO("S000000003", "oracle", "60年", "-");

		List<RegistSkillInfoDTO> registSkillDTOList = new ArrayList<RegistSkillInfoDTO>();

		registSkillDTOList.add(registSkillInfoDTO1);
		registSkillDTOList.add(registSkillInfoDTO2);

		for (int i = 0; i < ((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(registSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(registSkillDTOList.get(i).getSkillDetail()));
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(registSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(registSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}

	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）経験年数指定文字数超過")
	public void registExperienceYearsLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "600年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_保有スキル欄（登録行）経験年数最小文字数")
	public void registExperienceYearsMinLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistSkillInfoDTO registSkillInfoDTO1 = new RegistSkillInfoDTO("S000000001", "応用情報処理技術者", "-", "2016-10");
		RegistSkillInfoDTO registSkillInfoDTO2 = new RegistSkillInfoDTO("S000000003", "oracle", "年", "-");

		List<RegistSkillInfoDTO> registSkillDTOList = new ArrayList<RegistSkillInfoDTO>();

		registSkillDTOList.add(registSkillInfoDTO1);
		registSkillDTOList.add(registSkillInfoDTO2);

		for (int i = 0; i < ((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList"))
				.size(); i++) {
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillTypeId(), is(registSkillDTOList.get(i).getSkillTypeId()));
			assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getSkillDetail(), is(registSkillDTOList.get(i).getSkillDetail()));
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getAcquisitionYearMonth() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), is(registSkillDTOList.get(i).getAcquisitionYearMonth()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getAcquisitionYearMonth(), nullValue());
			}
			if (((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
					.getExperienceYears() != null) {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), is(registSkillDTOList.get(i).getExperienceYears()));
			} else {
				assertThat(((List<RegistSkillInfoDTO>) httpSession.getAttribute("registSkillInfoDTOList")).get(i)
						.getExperienceYears(), nullValue());
			}
		}
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）取得年月入力不正")
	public void registAcquisitionYearMonthInputErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		//従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");
		
		//保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");
		
		//保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2016-10");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "2013-10");
		
		//業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");
		
		//業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");
		
		//セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）取得年月未入力")
	public void registAcquisitionYearMonthRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_保有スキル欄（登録行）取得年月形式不正")
	public void registAcquisitionYearMonthFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "2");
		httpRequest.setParameter("json[registSkill][0][skillTypeId]", "S000000001");
		httpRequest.setParameter("json[registSkill][0][skillDetail]", "応用情報処理技術者");
		httpRequest.setParameter("json[registSkill][0][experienceYears]", "");
		httpRequest.setParameter("json[registSkill][0][acquisitionYearMonth]", "2013-100");
		httpRequest.setParameter("json[registSkill][1][skillTypeId]", "S000000003");
		httpRequest.setParameter("json[registSkill][1][skillDetail]", "oracle");
		httpRequest.setParameter("json[registSkill][1][experienceYears]", "6年");
		httpRequest.setParameter("json[registSkill][1][acquisitionYearMonth]", "");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行）所属開始日未入力")
	public void updateTeamBelongStartDateRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（更新行）所属完了日未入力")
	public void updateNoTeamBelongCompleteDatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎４"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7101, "2011-06-01", "-");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(), nullValue());

		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行）日付形式不正")
	public void updateWorkExpDateFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-100-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-100-30");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行）配属完了日＜所属完了日入力")
	public void updateTeamBelongCompleteDateAfterErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-05-01");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（更新行）配属完了日＝所属完了日入力")
	public void updateTeamBelongCompleteDateEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-30");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎４"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7101, "2011-06-01", "2013-04-30");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(
					((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));

		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）業務経験未選択")
	public void registWorkExpRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）業務経験DB未登録")
	public void registWorkExpValidityErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T000000009");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000009");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000009");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）チームリーダー既存在")
	public void roleIdLeaderErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0003");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000001");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（登録行）月単価未入力")
	public void noMonthlyUnitPricePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003", "",
				"2013-07-01", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), nullValue());
			assertThat(
					((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));

		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）月単価全角入力")
	public void monthlyUnitPriceHarfWidthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "あああああ");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）月単価最大文字数")
	public void monthlyUnitPriceMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）月単価文字数超過")
	public void monthlyUnitPriceLengthErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "5000000000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）所属開始日未入力")
	public void registTeamBelongStartDateRequiredErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（登録行）所属完了日未入力")
	public void registNoTeamBelongCompleteDatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-07-01", "");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(
					((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(), nullValue());

		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）日付形式不正")
	public void registWorkExpDateFormatErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000001");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-100-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-100-01");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）配属完了日＜所属完了日入力")
	public void registTeamBelongCompleteDateAfterErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-07-01");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（登録行）配属完了日＝所属完了日入力")
	public void registTeamBelongCompleteDateEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-30");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-07-01", "2015-06-30");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(
					((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
							.getTeamBelongCompleteDate(),
					is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）既移管申請")
	public void transferApplicationErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎５");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7102");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2011-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000005";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（登録行）移管申請チーム所属部長＝ログインユーザ")
	public void transferApplicationTeamManagerEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎６");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7103");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2011-10-31");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000006";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎６"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));
	
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7103, "2011-06-01", "2011-10-31");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-07-01", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("正常動作_業務経験欄（登録行）移管申請チーム所属部長!＝ログインユーザ")
	public void transferApplicationTeamManagerNotEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎６");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7103");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2011-10-31");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "maoka";
		String employeeId = "V000000006";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎６"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));
	
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7103, "2011-06-01", "2011-10-31");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-07-01", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）既複数移管申請")
	public void transferApplicationPluralErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎６");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7103");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2021-10-31");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "2");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "");
		httpRequest.setParameter("json[registWorkExp][1][teamId]", "T0000V0003");
		httpRequest.setParameter("json[registWorkExp][1][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][1][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][1][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongCompleteDate]", "");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "maoka";
		String employeeId = "V000000006";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行）同列所属開始日＞所属完了日入力")
	public void updateTeamBelongCompleteDateSomeBeforeErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2010-05-01");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（更新行）同列所属開始日＝所属完了日入力")
	public void updateTeamBelongCompleteDateSomeEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2011-06-01");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎４"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7101, "2011-06-01", "2011-06-01");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
			
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）同列所属開始日＞所属完了日入力")
	public void registTeamBelongCompleteDateSomeBeforeErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2013-04-01");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（登録行）同列所属開始日＝所属完了日入力")
	public void registTeamBelongCompleteDateSomeEqualErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2013-07-01");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-07-01", "2013-07-01");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
						.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
						.getTeamBelongCompleteDate(),is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行）前列所属開始日＞所属開始日入力")
	public void updateTeamBelongStartDateBackStaBeforeErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎７");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "2");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7104");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");
		httpRequest.setParameter("json[updateWorkExp][1][employeeExperienceId]", "7105");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongStartDate]", "2011-05-01");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000007";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行）前列所属開始日＝所属開始日入力")
	public void updateTeamBelongStartDateBackStaEqualErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎７");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "2");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7104");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2013-04-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");
		httpRequest.setParameter("json[updateWorkExp][1][employeeExperienceId]", "7105");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongStartDate]", "2013-04-01");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000007";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）前列所属開始日＞所属開始日入力")
	public void registTeamBelongStartDateBackStaBeforeErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "2");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");
		httpRequest.setParameter("json[registWorkExp][1][teamId]", "T0000V0003");
		httpRequest.setParameter("json[registWorkExp][1][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][1][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][1][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongStartDate]", "2013-04-30");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）前列所属開始日＝所属開始日入力")
	public void registTeamBelongStartDateBackStaEqualErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "2");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");
		httpRequest.setParameter("json[registWorkExp][1][teamId]", "T0000V0003");
		httpRequest.setParameter("json[registWorkExp][1][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][1][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][1][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行＋登録行）前列所属開始日＞所属開始日入力")
	public void teamBelongStartDateBackBeforeStaErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2011-05-30");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行＋登録行）前列所属開始日＝所属開始日入力")
	public void teamBelongStartDateBackStaEqualErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行）前列所属完了日＞所属開始日入力")
	public void updateTeamBelongStartDateBackCmpBeforeErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎７");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "2");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7104");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");
		httpRequest.setParameter("json[updateWorkExp][1][employeeExperienceId]", "7105");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongStartDate]", "2013-04-01");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000007";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（更新行）前列所属完了日＝所属開始日入力")
	public void updateTeamBelongStartDateBackCmpEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎７");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "2");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7104");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");
		httpRequest.setParameter("json[updateWorkExp][1][employeeExperienceId]", "7105");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongStartDate]", "2013-04-29");
		httpRequest.setParameter("json[updateWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000007";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎７"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7104, "2011-06-01", "2013-04-29");
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO2 = new UpdateEmpWorkExpDTO(7105, "2013-04-29", "2015-06-29");
		
		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO2);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
			
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（登録行）前列所属完了日＞所属開始日入力")
	public void registTeamBelongStartDateBackCmpBeforeErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "2");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");
		httpRequest.setParameter("json[registWorkExp][1][teamId]", "T0000V0003");
		httpRequest.setParameter("json[registWorkExp][1][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][1][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][1][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongStartDate]", "2014-05-01");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（登録行）前列所属完了日＝所属開始日入力")
	public void registTeamBelongStartDateBackCmpEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎２");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "0");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "2");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-07-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");
		httpRequest.setParameter("json[registWorkExp][1][teamId]", "T0000V0003");
		httpRequest.setParameter("json[registWorkExp][1][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][1][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][1][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongStartDate]", "2015-06-29");
		httpRequest.setParameter("json[registWorkExp][1][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000002";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎２"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-07-01", "2015-06-29");
		RegistEmpWorkExpDTO registEmpWorkExpDTO2 = new RegistEmpWorkExpDTO("T0000V0003", "C000000002", "T000000003",
				"500000", "2015-06-29", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO2);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			System.out.println();
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄（更新行＋登録行）前列所属完了日＞所属開始日入力")
	public void teamBelongStartDateBackCmpBeforeErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-04-01");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作_業務経験欄（更新行＋登録行）前列所属完了日＝所属開始日入力")
	public void teamBelongStartDateBackCmpEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎４");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7101");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2011-06-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0002");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-04-29");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000004";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎４"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7101, "2011-06-01", "2013-04-29");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0002", "C000000002", "T000000003",
				"500000", "2013-04-29", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
						.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
						.getTeamBelongCompleteDate(),is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}

	@Test
	@DisplayName("正常動作_業務経験欄チーム配属完了日無し")
	public void noAssignCompleteDatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎９");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");

		// 保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");

		// 保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");

		// 業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "7107");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2013-04-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");

		// 業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "1");
		httpRequest.setParameter("json[registWorkExp][0][teamId]", "T0000V0004");
		httpRequest.setParameter("json[registWorkExp][0][contractTypeId]", "C000000002");
		httpRequest.setParameter("json[registWorkExp][0][roleId]", "T000000003");
		httpRequest.setParameter("json[registWorkExp][0][monthlyUnitPrice]", "500000");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongStartDate]", "2013-04-29");
		httpRequest.setParameter("json[registWorkExp][0][teamBelongCompleteDate]", "2015-06-29");

		// セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000009";
		empInfoDTO.setEmployeeId(employeeId);

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		// セッションに要素が格納されていることで更新に成功したこととする。
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getEmployeeName(), is("テスト　太郎９"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getOffice(), is("中目黒"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getBelongDepartmentId(),
				is("D000000005"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostId(), is("P000000004"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPostalCode(), is("111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getAddress(), is("東京都中目黒１－１－１"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getPhoneNumber(),
				is("000-1111-2222"));
		assertThat(((UpdateEmpInfoDTO) httpSession.getAttribute("updateEmpInfoDTO")).getMail(), is("taro@me.jp"));

		UpdateEmpWorkExpDTO updateEmpWorkExpDTO1 = new UpdateEmpWorkExpDTO(7107, "2013-04-01", "2013-04-29");

		List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<UpdateEmpWorkExpDTO>();

		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO1);

		for (int i = 0; i < ((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getEmployeeExperienceId(), is(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<UpdateEmpWorkExpDTO>) httpSession.getAttribute("updateEmpWorkExpDTOList")).get(i)
					.getTeamBelongCompleteDate(),is(updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}

		RegistEmpWorkExpDTO registEmpWorkExpDTO1 = new RegistEmpWorkExpDTO("T0000V0004", "C000000002", "T000000003",
				"500000", "2013-04-29", "2015-06-29");

		List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<RegistEmpWorkExpDTO>();

		registEmpWorkExpDTOList.add(registEmpWorkExpDTO1);

		for (int i = 0; i < ((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList"))
				.size(); i++) {
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getBelongTeamId(), is(registEmpWorkExpDTOList.get(i).getBelongTeamId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getContractTypeId(), is(registEmpWorkExpDTOList.get(i).getContractTypeId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getRoleId(), is(registEmpWorkExpDTOList.get(i).getRoleId()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
					.getTeamBelongStartDate(), is(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
						.getMonthlyUnitPrice(), is(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice()));
			assertThat(((List<RegistEmpWorkExpDTO>) httpSession.getAttribute("registEmpWorkExpDTOList")).get(i)
						.getTeamBelongCompleteDate(),is(registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate()));
		}
	}
	
	@Test
	@DisplayName("エラーメッセージパターン_業務経験欄情報（更新行）DB未登録")
	public void noWorkExpErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		//従業員情報
		httpRequest.setParameter("json[employeeInfo][employeeName]", "テスト　太郎");
		httpRequest.setParameter("json[employeeInfo][office]", "中目黒");
		httpRequest.setParameter("json[employeeInfo][departmentId]", "D000000005");
		httpRequest.setParameter("json[employeeInfo][postId]", "P000000004");
		httpRequest.setParameter("json[employeeInfo][postalCode]", "111-2222");
		httpRequest.setParameter("json[employeeInfo][address]", "東京都中目黒１－１－１");
		httpRequest.setParameter("json[employeeInfo][phoneNumber]", "000-1111-2222");
		httpRequest.setParameter("json[employeeInfo][mail]", "taro@me.jp");
		
		//保有スキル更新欄
		httpRequest.setParameter("json[updateSkillRow]", "0");
		
		//保有スキル登録欄
		httpRequest.setParameter("json[registSkillRow]", "0");
		
		//業務経験更新欄
		httpRequest.setParameter("json[updateWorkExpRow]", "1");
		httpRequest.setParameter("json[updateWorkExp][0][employeeExperienceId]", "1000");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongStartDate]", "2013-04-01");
		httpRequest.setParameter("json[updateWorkExp][0][teamBelongCompleteDate]", "2013-04-29");
		
		//業務経験登録欄
		httpRequest.setParameter("json[registWorkExpRow]", "0");
		
		//セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "V000000009";
		empInfoDTO.setEmployeeId(employeeId);
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("例外動作")
	public void exceptionPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//セッションにログインユーザID、従業員IDを設定
		String loginUserId = "adminUser";
		String employeeId = "";
		empInfoDTO.setEmployeeId(employeeId);
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpCheckAction.doPost(httpRequest, httpResponse);
	}
}
