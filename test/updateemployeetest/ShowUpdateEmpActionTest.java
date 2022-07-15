package updateemployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.skillMaster.SkillTypeDTO;
import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO;
import jp.co.vaile.nerva.updateEmployee.ShowUpdateEmpAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowUpdateEmpActionTest {
	ShowUpdateEmpAction showUpdateEmpAction = new ShowUpdateEmpAction();

	@Test
	@DisplayName("正常動作パターン")
	public void nomalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// パラメータに従業員IDを設定
		String employeeId = "V000000001";

		// 従業員IDを疑似セッションスコープにセット
		httpRequest.setParameter("employeeId", employeeId);

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showUpdateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showUpdateEmpAction.doPost(httpRequest, httpResponse);

		// 実行結果を確認
		// 従業員DTO
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getEmployeeId(), is("V000000001"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getEmployeeName(), is("テスト１"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getSex(), is("男"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getBirthDate(), is("1988-01-01"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getBelongCompany(), is("株式会社V"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getOffice(), is("世田谷"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getDepartmentName(), is("基幹システム部"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getPost(), is("研修"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getPostalCode(), is("000-0000"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getAddress(), is("東京都世田谷１－１－１"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getPhoneNumber(), is("000-0000-0000"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getMail(), is("noname@me.jp"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getDepartmentId(), is("D000000006"));
		assertThat(((EmpInfoDTO) httpSession.getAttribute("empInfoDTO")).getPostId(), is("P000000005"));
		// スキル情報DTO
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(0).getSkillInfoId(),is(49));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(0).getSkillTypeId(),is("S000000001"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(0).getSkillDetail(),is("テスト１"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(0).getExperienceYears(),is("-"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(0).getAcquisitionYearMonth(),is("2013-01"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(0).getSkillType(),is("資格"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(0).isYearsDateOfAcquisitionFlg(),is(false));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(1).getSkillInfoId(),is(50));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(1).getSkillTypeId(),is("S000000004"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(1).getSkillDetail(),is("テスト１"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(1).getExperienceYears(),is("1年"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(1).getAcquisitionYearMonth(),is("-"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(1).getSkillType(),is("ツール"));
		assertThat(((List<EmpSkillInfoDTO>) httpSession.getAttribute("empSkillInfoDTOList")).get(1).isYearsDateOfAcquisitionFlg(),is(true));
		// 業務経験DTO
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getEmployeeExperienceId(),is(7100));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getResponsibleProject(),is("テストプロジェクト１"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getResponsibleIndustry(),is("情報通信"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getProjectAttributionCompany(),is("株式会社V"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getResponsibleManager(),is("権限　太郎"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getBelongTeam(),is("テストチーム１"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getRole(),is("メンバー"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getContractType(),is("準委任契約"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getMonthlyUnitPrice(),is(300000));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getTeamBelongStartDate(),is("2011-05-01"));
		assertThat(((List<EmpWorkExpDTO>) httpSession.getAttribute("empWorkExpDTOList")).get(0).getTeamBelongCompleteDate(),is("2013-03-29"));
		// スキル種別DTO
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(0).getSkillTypeId(),is("S000000001"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(0).getSkillTypeName(),is("資格"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(0).isYearsDateOfAcquisition(),is(false));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(1).getSkillTypeId(),is("S000000002"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(1).getSkillTypeName(),is("言語"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(1).isYearsDateOfAcquisition(),is(true));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(2).getSkillTypeId(),is("S000000003"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(2).getSkillTypeName(),is("DB"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(2).isYearsDateOfAcquisition(),is(true));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(3).getSkillTypeId(),is("S000000004"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(3).getSkillTypeName(),is("ツール"));
		assertThat(((List<SkillTypeDTO>) httpSession.getAttribute("skillTypeList")).get(3).isYearsDateOfAcquisition(),is(true));
		// マスタデータ 所属組織
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(0).getMasterData(),is("管理本部"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(0).getMasterDataId(),is("D000000001"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(1).getMasterData(),is("営業部"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(1).getMasterDataId(),is("D000000002"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(2).getMasterData(),is("システム本部"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(2).getMasterDataId(),is("D000000003"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(3).getMasterData(),is("金融システム部"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(3).getMasterDataId(),is("D000000004"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(4).getMasterData(),is("情報システム部"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(4).getMasterDataId(),is("D000000005"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(5).getMasterData(),is("基幹システム部"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(5).getMasterDataId(),is("D000000006"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(6).getMasterData(),is("基盤システム部"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("departmentDTOList").get(6).getMasterDataId(),is("D000000007"));
		// マスタデータ 役職
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(0).getMasterData(),is("役員"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(0).getMasterDataId(),is("P000000001"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(1).getMasterData(),is("部長"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(1).getMasterDataId(),is("P000000002"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(2).getMasterData(),is("課長"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(2).getMasterDataId(),is("P000000003"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(3).getMasterData(),is("一般"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(3).getMasterDataId(),is("P000000004"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(4).getMasterData(),is("研修"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("postDTOList").get(4).getMasterDataId(),is("P000000005"));
		// マスタデータ 担当
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(0).getMasterData(),is("リーダー"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(0).getMasterDataId(),is("T000000001"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(1).getMasterData(),is("サブリーダー"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(1).getMasterDataId(),is("T000000002"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(2).getMasterData(),is("メンバー"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(2).getMasterDataId(),is("T000000003"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(3).getMasterData(),is("その他"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("roleDTOList").get(3).getMasterDataId(),is("T000000004"));
		// マスタデータ 契約形態
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(0).getMasterData(),is("請負契約"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(0).getMasterDataId(),is("C000000001"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(1).getMasterData(),is("準委任契約"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(1).getMasterDataId(),is("C000000002"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(2).getMasterData(),is("派遣契約"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(2).getMasterDataId(),is("C000000003"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(3).getMasterData(),is("在籍出向"));
		assertThat(((Map<String, List<FetchAnyMasterTableDTO>>) httpRequest.getAttribute("masterTableInfo")).get("contractTypeDTOList").get(3).getMasterDataId(),is("C000000004"));
	}
}