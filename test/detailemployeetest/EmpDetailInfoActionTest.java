package detailemployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.detailEmployee.EmpDetailInfoAction;
import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class EmpDetailInfoActionTest {
	EmpDetailInfoAction empDetailInfoAction = new EmpDetailInfoAction();

	@Test
	@DisplayName("親会社テスト_従業員在籍パターン")
	public void testEmpDetailInfoActionParentEmpEnrolled() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 従業員IDをリクエストスコープにセット
		String employeeId = "V000000001";
		httpRequest.setAttribute("employeeId", employeeId);

		// ログインユーザーの所属会社IDをセッションにセット
		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		empDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		empDetailInfoAction.doPost(httpRequest, httpResponse);
		EmpInfoDTO empInfoDTO = (EmpInfoDTO) httpRequest.getAttribute("empInfoDTO");
		String employeeTestId = empInfoDTO.getEmployeeId();
		String employeeTestName = empInfoDTO.getEmployeeName();
		String testSex = empInfoDTO.getSex();
		String testBirthDate = empInfoDTO.getBirthDate();
		String testBelongCompany = empInfoDTO.getBelongCompany();
		String testOffice = empInfoDTO.getOffice();
		String departmentTestName = empInfoDTO.getDepartmentName();
		String testPost = empInfoDTO.getPost();
		String postalTestCode = empInfoDTO.getPostalCode();
		String testAddress = empInfoDTO.getAddress();
		String testPhoneNumber = empInfoDTO.getPhoneNumber();
		String testMail = empInfoDTO.getMail();
		String departmentTestId = empInfoDTO.getDepartmentId();
		String postTestId = empInfoDTO.getPostId();
		assertThat(employeeTestId, is("V000000001"));
		assertThat(employeeTestName, is("試験 一郎"));
		assertThat(testSex, is("男"));
		assertThat(testBirthDate, is("2022-03-03"));
		assertThat(testBelongCompany, is("株式会社V"));
		assertThat(testOffice, is("中目黒"));
		assertThat(departmentTestName, is("管理本部"));
		assertThat(testPost, is("役員"));
		assertThat(postalTestCode, is("104-0054"));
		assertThat(testAddress, is("東京都"));
		assertThat(testPhoneNumber, is("080-1111-1111"));
		assertThat(testMail, is("mail@test.co.jp"));
		assertThat(departmentTestId, is("D000000001"));
		assertThat(postTestId, is("P000000001"));

		ArrayList<EmpSkillInfoDTO> empSkillInfoDTOList = (ArrayList<EmpSkillInfoDTO>) httpRequest
				.getAttribute("empSkillInfoDTOList");
		for (EmpSkillInfoDTO empSkillDTO : empSkillInfoDTOList) {
			assertThat(empSkillDTO.getSkillType(), is("資格"));
			assertThat(empSkillDTO.getSkillDetail(), is("Java"));
			assertThat(empSkillDTO.getExperienceYears(), is("-"));
			assertThat(empSkillDTO.getAcquisitionYearMonth(), is("2022-01"));
		}

		ArrayList<EmpWorkExpDTO> empWorkExpDTOList = (ArrayList<EmpWorkExpDTO>) httpRequest
				.getAttribute("empWorkExpDTOList");

		for (EmpWorkExpDTO empWorkExpDTO : empWorkExpDTOList) {
			assertThat(empWorkExpDTO.getResponsibleProject(), is("冷蔵庫"));
			assertThat(empWorkExpDTO.getResponsibleIndustry(), is("製造"));
			assertThat(empWorkExpDTO.getProjectAttributionCompany(), is("株式会社V"));
			assertThat(empWorkExpDTO.getResponsibleManager(), is("管理者"));
			assertThat(empWorkExpDTO.getBelongTeam(), is("チームテスト"));
			assertThat(empWorkExpDTO.getRole(), is("リーダー"));
			assertThat(empWorkExpDTO.getContractType(), is("請負契約"));
			assertThat(empWorkExpDTO.getMonthlyUnitPrice(), is(0));
			assertThat(empWorkExpDTO.getTeamBelongStartDate(), is("2022-03-02"));
			assertThat(empWorkExpDTO.getTeamBelongCompleteDate(), is("2022-03-31"));

		}

	}

	@Test
	@DisplayName("親会社テスト_従業員不在パターン")
	public void testEmpDetailInfoActionParentEmpUnEnrolled() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 従業員IDをリクエストスコープにセット
		String employeeId = "V000000000";
		httpRequest.setAttribute("employeeId", employeeId);

		// ログインユーザーの所属会社IDをセッションにセット
		// （ログインユーザーに参照権限がある場合）
		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		empDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		empDetailInfoAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("子会社テスト_従業員在籍パターン")
	public void testEmpDetailInfoActionSubsidiaryEmpEnrolled() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 従業員IDをリクエストスコープにセット
		String employeeId = "K000000004";
		httpRequest.setAttribute("employeeId", employeeId);

		// ログインユーザーの所属会社IDをセッションにセット
		// （ログインユーザーに参照権限がある場合）
		String companyId = "CP00000002";
		String companyTestPrivilege = "0";
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		empDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		empDetailInfoAction.doPost(httpRequest, httpResponse);

		EmpInfoDTO empInfoDTO = (EmpInfoDTO) httpRequest.getAttribute("empInfoDTO");
		String employeeTestId = empInfoDTO.getEmployeeId();
		String employeeTestName = empInfoDTO.getEmployeeName();
		String testSex = empInfoDTO.getSex();
		String testBirthDate = empInfoDTO.getBirthDate();
		String testBelongCompany = empInfoDTO.getBelongCompany();
		String testOffice = empInfoDTO.getOffice();
		String departmentTestName = empInfoDTO.getDepartmentName();
		String testPost = empInfoDTO.getPost();
		String postalTestCode = empInfoDTO.getPostalCode();
		String testAddress = empInfoDTO.getAddress();
		String testPhoneNumber = empInfoDTO.getPhoneNumber();
		String testMail = empInfoDTO.getMail();
		String departmentTestId = empInfoDTO.getDepartmentId();
		String postTestId = empInfoDTO.getPostId();
		assertThat(employeeTestId, is("K000000004"));
		assertThat(employeeTestName, is("試験 三郎"));
		assertThat(testSex, is("男"));
		assertThat(testBirthDate, is("2022-03-29"));
		assertThat(testBelongCompany, is("K株式会社"));
		assertThat(testOffice, is("中目黒"));
		assertThat(departmentTestName, is("基幹システム部"));
		assertThat(testPost, is("研修"));
		assertThat(postalTestCode, is("111-1111"));
		assertThat(testAddress, is("東京都"));
		assertThat(testPhoneNumber, is("080-1111-1111"));
		assertThat(testMail, is("mail@test.co.jp"));
		assertThat(departmentTestId, is("D000000006"));
		assertThat(postTestId, is("P000000005"));

		ArrayList<EmpSkillInfoDTO> empSkillInfoDTOList = (ArrayList<EmpSkillInfoDTO>) httpRequest
				.getAttribute("empSkillInfoDTOList");
		for (EmpSkillInfoDTO empSkillDTO : empSkillInfoDTOList) {
			assertThat(empSkillDTO.getSkillType(), is("資格"));
			assertThat(empSkillDTO.getSkillDetail(), is("Java"));
			assertThat(empSkillDTO.getExperienceYears(), is("-"));
			assertThat(empSkillDTO.getAcquisitionYearMonth(), is("2021-12"));
		}

		ArrayList<EmpWorkExpDTO> empWorkExpDTOList = (ArrayList<EmpWorkExpDTO>) httpRequest
				.getAttribute("empWorkExpDTOList");

		for (EmpWorkExpDTO empWorkExpDTO : empWorkExpDTOList) {
			assertThat(empWorkExpDTO.getResponsibleProject(), is("テクノロジー"));
			assertThat(empWorkExpDTO.getResponsibleIndustry(), is("情報通信"));
			assertThat(empWorkExpDTO.getProjectAttributionCompany(), is("株式会社V"));
			assertThat(empWorkExpDTO.getResponsibleManager(), is("管理者"));
			assertThat(empWorkExpDTO.getBelongTeam(), is("小野寺"));
			assertThat(empWorkExpDTO.getRole(), is("リーダー"));
			assertThat(empWorkExpDTO.getContractType(), is("派遣契約"));
			assertThat(empWorkExpDTO.getMonthlyUnitPrice(), is(200000));
			assertThat(empWorkExpDTO.getTeamBelongStartDate(), is("2022-03-28"));
			assertThat(empWorkExpDTO.getTeamBelongCompleteDate(), is("2022-03-29"));

		}
	}

	@Test
	@DisplayName("子会社テスト_従業員不在パターン")
	public void testEmpDetailInfoActionSubsidiaryEmpUnEnrolled() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 従業員IDをリクエストスコープにセット
		String employeeId = "V000000001";
		httpRequest.setAttribute("employeeId", employeeId);

		// ログインユーザーの所属会社IDをセッションにセット
		// （ログインユーザーに参照権限がある場合）
		String companyId = "CP00000002";
		String companyTestPrivilege = "0";
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		empDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		empDetailInfoAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("選択したユーザの取得年月、担当案件、担当業種、案件帰属会社、所属完了日が登録されていなかった場合「-」と表示されること")
	public void testEmpDetailInfoActionUnset() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 従業員IDをリクエストスコープにセット
		String employeeId = "K000000003";
		httpRequest.setAttribute("employeeId", employeeId);

		// ログインユーザーの所属会社IDをセッションにセット
		// （ログインユーザーに参照権限がある場合）
		String companyId = "CP00000002";
		String companyTestPrivilege = "0";
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		empDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		empDetailInfoAction.doPost(httpRequest, httpResponse);

		EmpInfoDTO empInfoDTO = (EmpInfoDTO) httpRequest.getAttribute("empInfoDTO");
		String employeeTestId = empInfoDTO.getEmployeeId();
		String employeeTestName = empInfoDTO.getEmployeeName();
		String testSex = empInfoDTO.getSex();
		String testBirthDate = empInfoDTO.getBirthDate();
		String testBelongCompany = empInfoDTO.getBelongCompany();
		String testOffice = empInfoDTO.getOffice();
		String departmentTestName = empInfoDTO.getDepartmentName();
		String testPost = empInfoDTO.getPost();
		String postalTestCode = empInfoDTO.getPostalCode();
		String testAddress = empInfoDTO.getAddress();
		String testPhoneNumber = empInfoDTO.getPhoneNumber();
		String testMail = empInfoDTO.getMail();
		String departmentTestId = empInfoDTO.getDepartmentId();
		String postTestId = empInfoDTO.getPostId();
		assertThat(employeeTestId, is("K000000003"));
		assertThat(employeeTestName, is("試験 二郎"));
		assertThat(testSex, is("男"));
		assertThat(testBirthDate, is("2022-03-29"));
		assertThat(testBelongCompany, is("K株式会社"));
		assertThat(testOffice, is("中目黒"));
		assertThat(departmentTestName, is("管理本部"));
		assertThat(testPost, is("部長"));
		assertThat(postalTestCode, is("111-1111"));
		assertThat(testAddress, is("東京都"));
		assertThat(testPhoneNumber, is("080-1111-1111"));
		assertThat(testMail, is("mail@test.co.jp"));
		assertThat(departmentTestId, is("D000000001"));
		assertThat(postTestId, is("P000000002"));

		ArrayList<EmpSkillInfoDTO> empSkillInfoDTOList = (ArrayList<EmpSkillInfoDTO>) httpRequest
				.getAttribute("empSkillInfoDTOList");
		for (EmpSkillInfoDTO empSkillDTO : empSkillInfoDTOList) {
			assertThat(empSkillDTO.getSkillType(), is("言語"));
			assertThat(empSkillDTO.getSkillDetail(), is("JavaScript"));
			assertThat(empSkillDTO.getExperienceYears(), is("1"));
			assertThat(empSkillDTO.getAcquisitionYearMonth(), is("-"));
		}

		ArrayList<EmpWorkExpDTO> empWorkExpDTOList = (ArrayList<EmpWorkExpDTO>) httpRequest
				.getAttribute("empWorkExpDTOList");

		for (EmpWorkExpDTO empWorkExpDTO : empWorkExpDTOList) {
			assertThat(empWorkExpDTO.getResponsibleProject(), is("-"));
			assertThat(empWorkExpDTO.getResponsibleIndustry(), is("-"));
			assertThat(empWorkExpDTO.getProjectAttributionCompany(), is("-"));
			assertThat(empWorkExpDTO.getResponsibleManager(), is("管理者"));
			assertThat(empWorkExpDTO.getBelongTeam(), is("小野寺"));
			assertThat(empWorkExpDTO.getRole(), is("リーダー"));
			assertThat(empWorkExpDTO.getContractType(), is("派遣契約"));
			assertThat(empWorkExpDTO.getMonthlyUnitPrice(), nullValue());
			assertThat(empWorkExpDTO.getTeamBelongStartDate(), is("2022-03-30"));
			assertThat(empWorkExpDTO.getTeamBelongCompleteDate(), is("-"));

		}

	}
}
