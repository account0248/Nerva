package updateemployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.detailEmployee.EmpInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpSkillInfoDTO;
import jp.co.vaile.nerva.detailEmployee.EmpWorkExpDTO;
import jp.co.vaile.nerva.updateEmployee.RegistEmpWorkExpDTO;
import jp.co.vaile.nerva.updateEmployee.RegistSkillInfoDTO;
import jp.co.vaile.nerva.updateEmployee.UpdateEmpAction;
import jp.co.vaile.nerva.updateEmployee.UpdateEmpInfoDTO;
import jp.co.vaile.nerva.updateEmployee.UpdateEmpWorkExpDTO;
import jp.co.vaile.nerva.updateEmployee.UpdateSkillInfoDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateEmpActionTest {
	UpdateEmpAction updateEmpAction = new UpdateEmpAction();
	List<UpdateSkillInfoDTO> updateSkillInfoDTOList = new ArrayList<>();
	List<RegistSkillInfoDTO> registSkillInfoDTOList = new ArrayList<>();
	List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList = new ArrayList<>();
	List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList = new ArrayList<>();
	EmpInfoDTO empInfoDTO = new EmpInfoDTO();
	EmpSkillInfoDTO firstRowEmpSkillInfoDTO = new EmpSkillInfoDTO();
	EmpSkillInfoDTO secondRowEmpSkillInfoDTO = new EmpSkillInfoDTO();
	EmpSkillInfoDTO thirdRowEmpSkillInfoDTO = new EmpSkillInfoDTO();
	EmpSkillInfoDTO fourthRowEmpSkillInfoDTO = new EmpSkillInfoDTO();
	List<EmpSkillInfoDTO> empSkillInfoDTOList = new ArrayList<EmpSkillInfoDTO>();
	EmpWorkExpDTO firstRowEmpWorkExpDTO = new EmpWorkExpDTO();
	EmpWorkExpDTO secondRowEmpWorkExpDTO = new EmpWorkExpDTO();
	List<EmpWorkExpDTO> empWorkExpDTOList = new ArrayList<EmpWorkExpDTO>();

	@Test
	@DisplayName("全テスト実行")
	public void updateEmpActionTest() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		//テストNo1.全項目正常入力
		allInputPattern();
		//テストNo2.保持スキル・業務経験なし
		noSkillExperiencePattern();
		//テストNo3.保持スキル・業務経験登録行なし
		norRegistSkillExperiencePattern();
		//テストNo4.保持スキル・業務経験更新行なし
		noUpdateSkillExperiencePattern();
		//テストNo5.従業員情報最大文字数
		employeeInfoMaxLengthPattern();
		//テストNo6.保有スキル欄（更新行）スキル内容最大文字数
		updateSkillDetailMaxLengthPattern();
		//テストNo7.保有スキル欄（更新行）経験年数最大文字数
		updateExperienceYearsMaxLengthPattern();
		//テストNo8.保有スキル欄（更新行）経験年数最小文字数
		updateExperienceYearsMinLengthPattern();
		//テストNo9.保有スキル欄（登録行）スキル内容最大文字数
		registSkillDetailMaxLengthPattern();
		//テストNo10.保有スキル欄（登録行）経験年数最大文字数
		registExperienceYearsMaxLengthPattern();
		//テストNo11.保有スキル欄（登録行）経験年数最小文字数
		registExperienceYearsMinLengthPattern();
		//テストNo12.業務経験欄（更新行）所属完了日未入力
		updateNoTeamBelongCompleteDatePattern();
		//テストNo13.業務経験欄（更新行）配属完了日＝所属完了日入力
		updateTeamBelongCompleteDateEqualPattern();
		//テストNo14.業務経験欄（登録行）月単価未入力
		noMonthlyUnitPricePattern();
		//テストNo15.業務経験欄（登録行）月単価最大文字数
		monthlyUnitPriceMaxLengthPattern();
		//テストNo16.業務経験欄（登録行）所属完了日未入力
		registNoTeamBelongCompleteDatePattern();
		//テストNo17.業務経験欄（登録行）配属完了日＝所属完了日入力
		registTeamBelongCompleteDateEqualPattern();
		//テストNo18.業務経験欄（登録行）移管申請チーム所属部長＝ログインユーザ
		transferApplicationTeamManagerEqualPattern();
		//テストNo19.業務経験欄（登録行）移管申請チーム所属部長!＝ログインユーザ
		transferApplicationTeamManagerNotEqualPattern();
		//テストNo20.業務経験欄（更新行）同列所属開始日＝所属完了日入力
		updateTeamBelongCompleteDateSomeEqualPattern();
		//テストNo21.業務経験欄（登録行）同列所属開始日＝所属完了日入力
		registTeamBelongCompleteDateSomeEqualErrorPattern();
		//テストNo22.業務経験欄（更新行）前列所属完了日＝所属開始日入力
		updateTeamBelongStartDateBackCmpEqualPattern();
		//テストNo23.業務経験欄（登録行）前列所属完了日＝所属開始日入力
		registTeamBelongStartDateBackCmpEqualPattern();
		//テストNo24.業務経験欄（更新行＋登録行）前列所属完了日＝所属開始日入力
		teamBelongStartDateBackCmpEqualPattern();
		//テストNo25.業務経験欄チーム配属完了日無し
		noAssignCompleteDatePattern();
		//テストNo26.保有スキル欄（更新行）のスキル種別で全項目が選択
		updateSkillAllSelectPattern();
		//テストNo27.保有スキル欄（登録行）のスキル種別で全項目が選択
		registSkillAllSelectPattern();
		//テストNo28.画面情報差異有
		updateInfoNotEqualErrorPattern();
	}


	@Test
	@DisplayName("No1.正常動作_全項目正常入力")
	public void allInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１０";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル更新欄情報
		//1行目
		int updateSkillInfoIdF = 53;
		String updateSkillTypeIdF = "S000000001";
		String updateSkillDetailF = "基本情報処理技術者";
		String updateExperienceYearsF = "";
		String updateAcquisitionYearMonthF = "2013-10";	
		UpdateSkillInfoDTO firstRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdF, updateSkillTypeIdF, updateSkillDetailF, updateExperienceYearsF, updateAcquisitionYearMonthF);
		//リストに追加
		updateSkillInfoDTOList.add(firstRowUpdateSkillDTO);
		
		//2行目
		int updateSkillInfoIdS = 54;
		String updateSkillTypeIdS = "S000000002";
		String updateSkillDetailS = "java";
		String updateExperienceYearsS = "6年";
		String updateAcquisitionYearMonthS = "";	
		UpdateSkillInfoDTO secondRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdS, updateSkillTypeIdS, updateSkillDetailS, updateExperienceYearsS, updateAcquisitionYearMonthS);
		//リストに追加
		updateSkillInfoDTOList.add(secondRowUpdateSkillDTO);
		
		//保有スキル登録欄情報
		//1行目
		String registSkillTypeIdF = "S000000001";
		String registSkillDetailF = "応用情報処理技術者";
		String registExperienceYearsF = "";
		String registAcquisitionYearMonthF = "2016-10";
		RegistSkillInfoDTO firstRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdF, registSkillDetailF, registExperienceYearsF, registAcquisitionYearMonthF);
		//リストに追加
		registSkillInfoDTOList.add(firstRowRegistSkillDTO);
		
		//2行目
		String registSkillTypeIdS = "S000000003";
		String registSkillDetailS = "oracle";
		String registExperienceYearsS = "6年";
		String registAcquisitionYearMonthS = "";
		RegistSkillInfoDTO secondRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdS, registSkillDetailS, registExperienceYearsS, registAcquisitionYearMonthS);
		//リストに追加
		registSkillInfoDTOList.add(secondRowRegistSkillDTO);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7108;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2013-04-29";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000010";
		String employeeNameB = "テスト１０";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前保有スキル情報）
		//1行目
		int SkillInfoIdF = 53;
		firstRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdF);
		//リストに追加
		empSkillInfoDTOList.add(firstRowEmpSkillInfoDTO);
		
		//2行目
		int SkillInfoIdS = 54;
		secondRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdS);
		//リストに追加
		empSkillInfoDTOList.add(secondRowEmpSkillInfoDTO);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7108;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
		httpSession.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No2.正常動作_保持スキル・業務経験なし")
	public void noSkillExperiencePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１１";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000011";
		String employeeNameB = "テスト１１";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No3.正常動作_保持スキル・業務経験登録行なし")
	public void norRegistSkillExperiencePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１２";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル更新欄情報
		//1行目
		int updateSkillInfoIdF = 55;
		String updateSkillTypeIdF = "S000000001";
		String updateSkillDetailF = "基本情報処理技術者";
		String updateExperienceYearsF = "";
		String updateAcquisitionYearMonthF = "2013-10";	
		UpdateSkillInfoDTO firstRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdF, updateSkillTypeIdF, updateSkillDetailF, updateExperienceYearsF, updateAcquisitionYearMonthF);
		//リストに追加
		updateSkillInfoDTOList.add(firstRowUpdateSkillDTO);
		
		//2行目
		int updateSkillInfoIdS = 56;
		String updateSkillTypeIdS = "S000000002";
		String updateSkillDetailS = "java";
		String updateExperienceYearsS = "6年";
		String updateAcquisitionYearMonthS = "";	
		UpdateSkillInfoDTO secondRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdS, updateSkillTypeIdS, updateSkillDetailS, updateExperienceYearsS, updateAcquisitionYearMonthS);
		//リストに追加
		updateSkillInfoDTOList.add(secondRowUpdateSkillDTO);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7109;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2013-04-29";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000012";
		String employeeNameB = "テスト１２";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		
		//画面表示情報（更新前保有スキル情報）
		//1行目
		int SkillInfoIdF = 55;
		firstRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdF);
		//リストに追加
		empSkillInfoDTOList.add(firstRowEmpSkillInfoDTO);
		
		//2行目
		int SkillInfoIdS = 56;
		secondRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdS);
		//リストに追加
		empSkillInfoDTOList.add(secondRowEmpSkillInfoDTO);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7109;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No4.正常動作_保持スキル・業務経験更新行なし")
	public void noUpdateSkillExperiencePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１３";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル登録欄情報
		//1行目
		String registSkillTypeIdF = "S000000001";
		String registSkillDetailF = "応用情報処理技術者";
		String registExperienceYearsF = "";
		String registAcquisitionYearMonthF = "2016-10";
		RegistSkillInfoDTO firstRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdF, registSkillDetailF, registExperienceYearsF, registAcquisitionYearMonthF);
		//リストに追加
		registSkillInfoDTOList.add(firstRowRegistSkillDTO);
		
		//2行目
		String registSkillTypeIdS = "S000000003";
		String registSkillDetailS = "oracle";
		String registExperienceYearsS = "6年";
		String registAcquisitionYearMonthS = "";
		RegistSkillInfoDTO secondRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdS, registSkillDetailS, registExperienceYearsS, registAcquisitionYearMonthS);
		//リストに追加
		registSkillInfoDTOList.add(secondRowRegistSkillDTO);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "\\500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000013";
		String employeeNameB = "テスト１３";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No5.正常動作_従業員情報最大文字数")
	public void employeeInfoMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "ああああああああああああああああああああ";
		String office = "ああああああああああああああああああああ";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ";
		String phoneNumber = "000-1111-222222";
		String mail = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000014";
		String employeeNameB = "テスト１４";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No6.正常動作_保有スキル欄（更新行）スキル内容最大文字数")
	public void updateSkillDetailMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１５";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル更新欄情報
		//1行目
		int updateSkillInfoIdF = 57;
		String updateSkillTypeIdF = "S000000001";
		String updateSkillDetailF = "基本情報処理技術者";
		String updateExperienceYearsF = "";
		String updateAcquisitionYearMonthF = "2013-10";	
		UpdateSkillInfoDTO firstRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdF, updateSkillTypeIdF, updateSkillDetailF, updateExperienceYearsF, updateAcquisitionYearMonthF);
		//リストに追加
		updateSkillInfoDTOList.add(firstRowUpdateSkillDTO);
		
		//2行目
		int updateSkillInfoIdS = 58;
		String updateSkillTypeIdS = "S000000002";
		String updateSkillDetailS = "ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ";
		String updateExperienceYearsS = "6年";
		String updateAcquisitionYearMonthS = "";	
		UpdateSkillInfoDTO secondRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdS, updateSkillTypeIdS, updateSkillDetailS, updateExperienceYearsS, updateAcquisitionYearMonthS);
		//リストに追加
		updateSkillInfoDTOList.add(secondRowUpdateSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000015";
		String employeeNameB = "テスト１５";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		
		//画面表示情報（更新前保有スキル情報）
		//1行目
		int SkillInfoIdF = 57;
		firstRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdF);
		//リストに追加
		empSkillInfoDTOList.add(firstRowEmpSkillInfoDTO);
		
		//2行目
		int SkillInfoIdS = 58;
		secondRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdS);
		//リストに追加
		empSkillInfoDTOList.add(secondRowEmpSkillInfoDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No7.正常動作_保有スキル欄（更新行）経験年数最大文字数")
	public void updateExperienceYearsMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１６";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル更新欄情報
		//1行目
		int updateSkillInfoIdF = 59;
		String updateSkillTypeIdF = "S000000001";
		String updateSkillDetailF = "基本情報処理技術者";
		String updateExperienceYearsF = "";
		String updateAcquisitionYearMonthF = "2013-10";	
		UpdateSkillInfoDTO firstRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdF, updateSkillTypeIdF, updateSkillDetailF, updateExperienceYearsF, updateAcquisitionYearMonthF);
		//リストに追加
		updateSkillInfoDTOList.add(firstRowUpdateSkillDTO);
		
		//2行目
		int updateSkillInfoIdS = 60;
		String updateSkillTypeIdS = "S000000002";
		String updateSkillDetailS = "java";
		String updateExperienceYearsS = "60年";
		String updateAcquisitionYearMonthS = "";	
		UpdateSkillInfoDTO secondRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdS, updateSkillTypeIdS, updateSkillDetailS, updateExperienceYearsS, updateAcquisitionYearMonthS);
		//リストに追加
		updateSkillInfoDTOList.add(secondRowUpdateSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000016";
		String employeeNameB = "テスト１６";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		
		//画面表示情報（更新前保有スキル情報）
		//1行目
		int SkillInfoIdF = 59;
		firstRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdF);
		//リストに追加
		empSkillInfoDTOList.add(firstRowEmpSkillInfoDTO);
		
		//2行目
		int SkillInfoIdS = 60;
		secondRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdS);
		//リストに追加
		empSkillInfoDTOList.add(secondRowEmpSkillInfoDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No8.正常動作_保有スキル欄（更新行）経験年数最小文字数")
	public void updateExperienceYearsMinLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１７";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル更新欄情報
		//1行目
		int updateSkillInfoIdF = 61;
		String updateSkillTypeIdF = "S000000001";
		String updateSkillDetailF = "基本情報処理技術者";
		String updateExperienceYearsF = "";
		String updateAcquisitionYearMonthF = "2013-10";	
		UpdateSkillInfoDTO firstRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdF, updateSkillTypeIdF, updateSkillDetailF, updateExperienceYearsF, updateAcquisitionYearMonthF);
		//リストに追加
		updateSkillInfoDTOList.add(firstRowUpdateSkillDTO);
		
		//2行目
		int updateSkillInfoIdS = 62;
		String updateSkillTypeIdS = "S000000002";
		String updateSkillDetailS = "java";
		String updateExperienceYearsS = "年";
		String updateAcquisitionYearMonthS = "";	
		UpdateSkillInfoDTO secondRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdS, updateSkillTypeIdS, updateSkillDetailS, updateExperienceYearsS, updateAcquisitionYearMonthS);
		//リストに追加
		updateSkillInfoDTOList.add(secondRowUpdateSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000017";
		String employeeNameB = "テスト１７";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		
		//画面表示情報（更新前保有スキル情報）
		//1行目
		int SkillInfoIdF = 61;
		firstRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdF);
		//リストに追加
		empSkillInfoDTOList.add(firstRowEmpSkillInfoDTO);
		
		//2行目
		int SkillInfoIdS = 62;
		secondRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdS);
		//リストに追加
		empSkillInfoDTOList.add(secondRowEmpSkillInfoDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No9.正常動作_保有スキル欄（登録行）スキル内容最大文字数")
	public void registSkillDetailMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１８";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル登録欄情報
		//1行目
		String registSkillTypeIdF = "S000000001";
		String registSkillDetailF = "応用情報処理技術者";
		String registExperienceYearsF = "";
		String registAcquisitionYearMonthF = "2016-10";
		RegistSkillInfoDTO firstRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdF, registSkillDetailF, registExperienceYearsF, registAcquisitionYearMonthF);
		//リストに追加
		registSkillInfoDTOList.add(firstRowRegistSkillDTO);
		
		//2行目
		String registSkillTypeIdS = "S000000003";
		String registSkillDetailS = "ああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああああ";
		String registExperienceYearsS = "6年";
		String registAcquisitionYearMonthS = "";
		RegistSkillInfoDTO secondRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdS, registSkillDetailS, registExperienceYearsS, registAcquisitionYearMonthS);
		//リストに追加
		registSkillInfoDTOList.add(secondRowRegistSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000018";
		String employeeNameB = "テスト１８";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No10.正常動作_保有スキル欄（登録行）経験年数最大文字数")
	public void registExperienceYearsMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎１９";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル登録欄情報
		//1行目
		String registSkillTypeIdF = "S000000001";
		String registSkillDetailF = "応用情報処理技術者";
		String registExperienceYearsF = "";
		String registAcquisitionYearMonthF = "2016-10";
		RegistSkillInfoDTO firstRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdF, registSkillDetailF, registExperienceYearsF, registAcquisitionYearMonthF);
		//リストに追加
		registSkillInfoDTOList.add(firstRowRegistSkillDTO);
		
		//2行目
		String registSkillTypeIdS = "S000000003";
		String registSkillDetailS = "oracle";
		String registExperienceYearsS = "60年";
		String registAcquisitionYearMonthS = "";
		RegistSkillInfoDTO secondRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdS, registSkillDetailS, registExperienceYearsS, registAcquisitionYearMonthS);
		//リストに追加
		registSkillInfoDTOList.add(secondRowRegistSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000019";
		String employeeNameB = "テスト１９";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No11.正常動作_保有スキル欄（登録行）経験年数最小文字数")
	public void registExperienceYearsMinLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２０";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル登録欄情報
		//1行目
		String registSkillTypeIdF = "S000000001";
		String registSkillDetailF = "応用情報処理技術者";
		String registExperienceYearsF = "";
		String registAcquisitionYearMonthF = "2016-10";
		RegistSkillInfoDTO firstRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdF, registSkillDetailF, registExperienceYearsF, registAcquisitionYearMonthF);
		//リストに追加
		registSkillInfoDTOList.add(firstRowRegistSkillDTO);
		
		//2行目
		String registSkillTypeIdS = "S000000003";
		String registSkillDetailS = "oracle";
		String registExperienceYearsS = "年";
		String registAcquisitionYearMonthS = "";
		RegistSkillInfoDTO secondRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdS, registSkillDetailS, registExperienceYearsS, registAcquisitionYearMonthS);
		//リストに追加
		registSkillInfoDTOList.add(secondRowRegistSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000020";
		String employeeNameB = "テスト２０";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No12.正常動作_業務経験欄（更新行）所属完了日未入力")
	public void updateNoTeamBelongCompleteDatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２１";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7110;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000021";
		String employeeNameB = "テスト２１";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7110;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No13.正常動作_業務経験欄（更新行）配属完了日＝所属完了日入力")
	public void updateTeamBelongCompleteDateEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２２";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7111;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2013-04-30";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000022";
		String employeeNameB = "テスト２２";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7111;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No14.正常動作_業務経験欄（登録行）月単価未入力")
	public void noMonthlyUnitPricePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２３";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000023";
		String employeeNameB = "テスト２３";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No15.業務経験欄（登録行）月単価最大文字数")
	public void monthlyUnitPriceMaxLengthPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２４";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000024";
		String employeeNameB = "テスト２４";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No16.正常動作_業務経験欄（登録行）所属完了日未入力")
	public void registNoTeamBelongCompleteDatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２５";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "\\500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000025";
		String employeeNameB = "テスト２５";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No17.正常動作_業務経験欄（登録行）配属完了日＝所属完了日入力")
	public void registTeamBelongCompleteDateEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２６";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-30";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000026";
		String employeeNameB = "テスト２６";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No18.正常動作_業務経験欄（登録行）移管申請チーム所属部長＝ログインユーザ")
	public void transferApplicationTeamManagerEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２７";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7112;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2021-10-31";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000027";
		String employeeNameB = "テスト２７";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7112;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No19.正常動作_業務経験欄（登録行）移管申請チーム所属部長!＝ログインユーザ")
	public void transferApplicationTeamManagerNotEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２８";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7113;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2021-10-31";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = true;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000028";
		String employeeNameB = "テスト２８";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7113;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "maoka";
		String loginCheck = "true";
		String loginUserName = "山田　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No20.正常動作_業務経験欄（更新行）同列所属開始日＝所属完了日入力")
	public void updateTeamBelongCompleteDateSomeEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎２９";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7114;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2011-06-01";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000029";
		String employeeNameB = "テスト２９";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7114;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No21.正常動作_業務経験欄（登録行）同列所属開始日＝所属完了日入力")
	public void registTeamBelongCompleteDateSomeEqualErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎３０";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2013-07-01";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000030";
		String employeeNameB = "テスト３０";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No22.正常動作_業務経験欄（更新行）前列所属完了日＝所属開始日入力")
	public void updateTeamBelongStartDateBackCmpEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎３１";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		//1行目
		int updateEmployeeExperienceIdF = 7115;
		String updateTeamBelongStartDateF = "2011-06-01";
		String updateTeamBelongCompleteDateF = "2013-04-29";
		UpdateEmpWorkExpDTO firstRowUpdateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceIdF, updateTeamBelongStartDateF, updateTeamBelongCompleteDateF);
		//リストに追加
		updateEmpWorkExpDTOList.add(firstRowUpdateEmpWorkExpDTO);
		
		//2行目
		int updateEmployeeExperienceIdS = 7116;
		String updateTeamBelongStartDateS = "2013-04-29";
		String updateTeamBelongCompleteDateS = "2015-06-29";
		UpdateEmpWorkExpDTO secondRowUpdateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceIdS, updateTeamBelongStartDateS, updateTeamBelongCompleteDateS);
		//リストに追加
		updateEmpWorkExpDTOList.add(secondRowUpdateEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000031";
		String employeeNameB = "テスト３１";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		//1行目
		int employeeExperienceIdF = 7115;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceIdF);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		//2行目
		int employeeExperienceIdS = 7116;
		secondRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceIdS);
		//リストに追加
		empWorkExpDTOList.add(secondRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No23.正常動作_業務経験欄（登録行）前列所属完了日＝所属開始日入力")
	public void registTeamBelongStartDateBackCmpEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎３２";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験登録欄
		//1行目
		String belongTeamIdF = "T0000V0002";
		String contractTypeIdF = "C000000002";
		String roleIdF = "T000000003";
		String monthlyUnitPriceF = "500000";
		String registTeamBelongStartDateF = "2013-07-01";
		String registTeamBelongCompleteDateF = "2015-06-29";
		boolean applicationFlagF = false;
		RegistEmpWorkExpDTO firstRowRegistEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamIdF, contractTypeIdF, roleIdF, monthlyUnitPriceF, registTeamBelongStartDateF, registTeamBelongCompleteDateF);
		firstRowRegistEmpWorkExpDTO.setApplicationFlag(applicationFlagF);
		//リストに追加
		registEmpWorkExpDTOList.add(firstRowRegistEmpWorkExpDTO);
		
		//2行目
		String belongTeamIdS = "T0000V0003";
		String contractTypeIdS = "C000000002";
		String roleIdS = "T000000003";
		String monthlyUnitPriceS = "500000";
		String registTeamBelongStartDateS = "2015-06-29";
		String registTeamBelongCompleteDateS = "2015-06-29";
		boolean applicationFlagS = false;
		RegistEmpWorkExpDTO secondRowRegistEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamIdS, contractTypeIdS, roleIdS, monthlyUnitPriceS, registTeamBelongStartDateS, registTeamBelongCompleteDateS);
		secondRowRegistEmpWorkExpDTO.setApplicationFlag(applicationFlagS);
		//リストに追加
		registEmpWorkExpDTOList.add(secondRowRegistEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000032";
		String employeeNameB = "テスト３２";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No24.正常動作_業務経験欄（更新行＋登録行）前列所属完了日＝所属開始日入力")
	public void teamBelongStartDateBackCmpEqualPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎３３";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7117;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2013-04-29";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-04-29";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000033";
		String employeeNameB = "テスト３３";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7117;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}


	@Test
	@DisplayName("No25.正常動作_業務経験欄チーム配属完了日無し")
	public void noAssignCompleteDatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎３４";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7118;
		String updateTeamBelongStartDate = "2013-04-01";
		String updateTeamBelongCompleteDate = "2013-04-29";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0004";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-04-29";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000034";
		String employeeNameB = "テスト３４";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 7118;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}
	
	@Test
	@DisplayName("No26.正常動作_保有スキル欄（更新行）のスキル種別で全項目が選択")
	public void updateSkillAllSelectPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト３５";
		String office = "世田谷";
		String departmentId = "D000000006";
		String postId = "P000000005";
		String postalCode = "000-0000";
		String address = "東京都世田谷１－１－１";
		String phoneNumber = "000-0000-0000";
		String mail = "noname@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル更新欄情報
		//1行目
		int updateSkillInfoIdF = 63;
		String updateSkillTypeIdF = "S000000002";
		String updateSkillDetailF = "テスト３５更新";
		String updateExperienceYearsF = "1年";
		String updateAcquisitionYearMonthF = "";	
		UpdateSkillInfoDTO firstRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdF, updateSkillTypeIdF, updateSkillDetailF, updateExperienceYearsF, updateAcquisitionYearMonthF);
		//リストに追加
		updateSkillInfoDTOList.add(firstRowUpdateSkillDTO);
		
		//2行目
		int updateSkillInfoIdS = 64;
		String updateSkillTypeIdS = "S000000003";
		String updateSkillDetailS = "テスト３５更新";
		String updateExperienceYearsS = "1年";
		String updateAcquisitionYearMonthS = "";	
		UpdateSkillInfoDTO secondRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdS, updateSkillTypeIdS, updateSkillDetailS, updateExperienceYearsS, updateAcquisitionYearMonthS);
		//リストに追加
		updateSkillInfoDTOList.add(secondRowUpdateSkillDTO);
		
		//3行目
		int updateSkillInfoIdT = 65;
		String updateSkillTypeIdT = "S000000004";
		String updateSkillDetailT = "テスト３５更新";
		String updateExperienceYearsT = "1年";
		String updateAcquisitionYearMonthT = "";	
		UpdateSkillInfoDTO thirdRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdT, updateSkillTypeIdT, updateSkillDetailT, updateExperienceYearsT, updateAcquisitionYearMonthT);
		//リストに追加
		updateSkillInfoDTOList.add(thirdRowUpdateSkillDTO);
		
		//4行目
		int updateSkillInfoIdFo = 66;
		String updateSkillTypeIdFo = "S000000001";
		String updateSkillDetailFo = "テスト３５更新";
		String updateExperienceYearsFo = "";
		String updateAcquisitionYearMonthFo = "2013-1-1";	
		UpdateSkillInfoDTO fourthRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdFo, updateSkillTypeIdFo, updateSkillDetailFo, updateExperienceYearsFo, updateAcquisitionYearMonthFo);
		//リストに追加
		updateSkillInfoDTOList.add(fourthRowUpdateSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000035";
		String employeeNameB = "テスト３５";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		
		//画面表示情報（更新前保有スキル情報）
		//1行目
		int SkillInfoIdF = 63;
		firstRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdF);
		//リストに追加
		empSkillInfoDTOList.add(firstRowEmpSkillInfoDTO);
		
		//2行目
		int SkillInfoIdS = 64;
		secondRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdS);
		//リストに追加
		empSkillInfoDTOList.add(secondRowEmpSkillInfoDTO);
		
		//3行目
		int SkillInfoIdT = 65;
		thirdRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdT);
		//リストに追加
		empSkillInfoDTOList.add(thirdRowEmpSkillInfoDTO);
		
		//4行目
		int SkillInfoIdFo = 66;
		fourthRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdFo);
		//リストに追加
		empSkillInfoDTOList.add(fourthRowEmpSkillInfoDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}
	
	@Test
	@DisplayName("No27.正常動作_保有スキル欄（登録行）のスキル種別で全項目が選択")
	public void registSkillAllSelectPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト３５";
		String office = "世田谷";
		String departmentId = "D000000006";
		String postId = "P000000005";
		String postalCode = "000-0000";
		String address = "東京都世田谷１－１－１";
		String phoneNumber = "000-0000-0000";
		String mail = "noname@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル登録欄情報
		//1行目
		String registSkillTypeIdF = "S000000001";
		String registSkillDetailF = "テスト３５登録";
		String registExperienceYearsF = "";
		String registAcquisitionYearMonthF = "2016-01";
		RegistSkillInfoDTO firstRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdF, registSkillDetailF, registExperienceYearsF, registAcquisitionYearMonthF);
		//リストに追加
		registSkillInfoDTOList.add(firstRowRegistSkillDTO);
		
		//2行目
		String registSkillTypeIdS = "S000000002";
		String registSkillDetailS = "テスト３５登録";
		String registExperienceYearsS = "1年";
		String registAcquisitionYearMonthS = "";
		RegistSkillInfoDTO secondRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdS, registSkillDetailS, registExperienceYearsS, registAcquisitionYearMonthS);
		//リストに追加
		registSkillInfoDTOList.add(secondRowRegistSkillDTO);
		
		//3行目
		String registSkillTypeIdT = "S000000003";
		String registSkillDetailT = "テスト３５登録";
		String registExperienceYearsT = "1年";
		String registAcquisitionYearMonthT = "";
		RegistSkillInfoDTO thirdRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdT, registSkillDetailT, registExperienceYearsT, registAcquisitionYearMonthT);
		//リストに追加
		registSkillInfoDTOList.add(thirdRowRegistSkillDTO);
		
		//4行目
		String registSkillTypeIdFo = "S000000004";
		String registSkillDetailFo = "テスト３５登録";
		String registExperienceYearsFo = "1年";
		String registAcquisitionYearMonthFo = "";
		RegistSkillInfoDTO fourthRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdFo, registSkillDetailFo, registExperienceYearsFo, registAcquisitionYearMonthFo);
		//リストに追加
		registSkillInfoDTOList.add(fourthRowRegistSkillDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000035";
		String employeeNameB = "テスト３５";
		String sex = "男";
		String birthDate = "1988-01-01";
		String officeB = "世田谷";
		String postalCodeB = "000-0000";
		String addressB = "東京都世田谷１－１－１";
		String phoneNumberB = "000-0000-0000";
		String mailB = "noname@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"),is(employeeId));
	}
	
	@Test
	@DisplayName("No28.エラーパターン_画面情報差異有")
	public void updateInfoNotEqualErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//リストの値をクリア
		updateSkillInfoDTOList.clear();
		registSkillInfoDTOList.clear();
		updateEmpWorkExpDTOList.clear();
		registEmpWorkExpDTOList.clear();
		empSkillInfoDTOList.clear();
		empWorkExpDTOList.clear();
		
		// 入力情報格納DTO、画面表示情報格納DTOに情報をセット
		//従業員更新情報
		String employeeName = "テスト　太郎３６";
		String office = "中目黒";
		String departmentId = "D000000005";
		String postId = "P000000004";
		String postalCode = "111-2222";
		String address = "東京都中目黒１－１－１";
		String phoneNumber = "000-1111-2222";
		String mail = "taro@me.jp";
		UpdateEmpInfoDTO updateEmpInfoDTO = new UpdateEmpInfoDTO(employeeName, office, departmentId, postId, postalCode, address, phoneNumber, mail);
		
		//保有スキル更新欄情報
		//1行目
		int updateSkillInfoIdF = 67;
		String updateSkillTypeIdF = "S000000001";
		String updateSkillDetailF = "基本情報処理技術者";
		String updateExperienceYearsF = "";
		String updateAcquisitionYearMonthF = "2013-10";	
		UpdateSkillInfoDTO firstRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdF, updateSkillTypeIdF, updateSkillDetailF, updateExperienceYearsF, updateAcquisitionYearMonthF);
		//リストに追加
		updateSkillInfoDTOList.add(firstRowUpdateSkillDTO);
		
		//2行目
		int updateSkillInfoIdS = 68;
		String updateSkillTypeIdS = "S000000002";
		String updateSkillDetailS = "java";
		String updateExperienceYearsS = "6年";
		String updateAcquisitionYearMonthS = "";	
		UpdateSkillInfoDTO secondRowUpdateSkillDTO = new UpdateSkillInfoDTO(updateSkillInfoIdS, updateSkillTypeIdS, updateSkillDetailS, updateExperienceYearsS, updateAcquisitionYearMonthS);
		//リストに追加
		updateSkillInfoDTOList.add(secondRowUpdateSkillDTO);
		
		//保有スキル登録欄情報
		//1行目
		String registSkillTypeIdF = "S000000001";
		String registSkillDetailF = "応用情報処理技術者";
		String registExperienceYearsF = "";
		String registAcquisitionYearMonthF = "2016-10";
		RegistSkillInfoDTO firstRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdF, registSkillDetailF, registExperienceYearsF, registAcquisitionYearMonthF);
		//リストに追加
		registSkillInfoDTOList.add(firstRowRegistSkillDTO);
		
		//2行目
		String registSkillTypeIdS = "S000000003";
		String registSkillDetailS = "oracle";
		String registExperienceYearsS = "6年";
		String registAcquisitionYearMonthS = "";
		RegistSkillInfoDTO secondRowRegistSkillDTO = new RegistSkillInfoDTO(registSkillTypeIdS, registSkillDetailS, registExperienceYearsS, registAcquisitionYearMonthS);
		//リストに追加
		registSkillInfoDTOList.add(secondRowRegistSkillDTO);
		
		//業務経験更新欄情報
		int updateEmployeeExperienceId = 7119;
		String updateTeamBelongStartDate = "2011-06-01";
		String updateTeamBelongCompleteDate = "2013-04-29";
		UpdateEmpWorkExpDTO updateEmpWorkExpDTO = new UpdateEmpWorkExpDTO(updateEmployeeExperienceId, updateTeamBelongStartDate, updateTeamBelongCompleteDate);
		//リストに追加
		updateEmpWorkExpDTOList.add(updateEmpWorkExpDTO);
		
		//業務経験登録欄
		String belongTeamId = "T0000V0002";
		String contractTypeId = "C000000002";
		String roleId = "T000000003";
		String monthlyUnitPrice = "500000";
		String registTeamBelongStartDate = "2013-07-01";
		String registTeamBelongCompleteDate = "2015-06-29";
		boolean applicationFlag = false;
		RegistEmpWorkExpDTO registEmpWorkExpDTO = new RegistEmpWorkExpDTO(belongTeamId, contractTypeId, roleId, monthlyUnitPrice, registTeamBelongStartDate, registTeamBelongCompleteDate);
		registEmpWorkExpDTO.setApplicationFlag(applicationFlag);
		//リストに追加
		registEmpWorkExpDTOList.add(registEmpWorkExpDTO);
		
		//画面表示情報（更新前従業員情報）
		String employeeId = "V000000000";
		String employeeNameB = "テスト";
		String sex = "女";
		String birthDate = "1995-01-01";
		String officeB = "恵比寿";
		String postalCodeB = "222-1111";
		String addressB = "東京都恵比寿１－１－１";
		String phoneNumberB = "222-1111-0000";
		String mailB = "test@me.jp";
		
		empInfoDTO.setEmployeeId(employeeId);
		empInfoDTO.setEmployeeName(employeeNameB);
		empInfoDTO.setSex(sex);
		empInfoDTO.setBirthDate(birthDate);
		empInfoDTO.setOffice(officeB);
		empInfoDTO.setPostalCode(postalCodeB);
		empInfoDTO.setAddress(addressB);
		empInfoDTO.setPhoneNumber(phoneNumberB);
		empInfoDTO.setMail(mailB);
		
		//画面表示情報（更新前保有スキル情報）
		//1行目
		int SkillInfoIdF = 10;
		firstRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdF);
		//リストに追加
		empSkillInfoDTOList.add(firstRowEmpSkillInfoDTO);
		
		//2行目
		int SkillInfoIdS = 11;
		secondRowEmpSkillInfoDTO.setSkillInfoId(SkillInfoIdS);
		//リストに追加
		empSkillInfoDTOList.add(secondRowEmpSkillInfoDTO);
		
		//画面表示情報（更新前業務経験情報）
		int employeeExperienceId = 1000;
		firstRowEmpWorkExpDTO.setEmployeeExperienceId(employeeExperienceId);
		//リストに追加
		empWorkExpDTOList.add(firstRowEmpWorkExpDTO);
		
		//ログインユーザID
		String loginUserId = "adminUser";
		String loginCheck = "true";
		String loginUserName = "権限　太郎";
		String companyId = "CP00000001";
		String adminFlg = "true";
		
		// 疑似セッションスコープにログインユーザID、情報を格納したDTOを設定
		httpSession.setAttribute("userId", loginUserId);
		httpSession.setAttribute("loginCheck", loginCheck);
		httpSession.setAttribute("userName", loginUserName);
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("adminFlg", adminFlg);
		httpSession.setAttribute("updateEmpInfoDTO", updateEmpInfoDTO);
		httpSession.setAttribute("updateSkillInfoDTOList", updateSkillInfoDTOList);
		httpSession.setAttribute("registSkillInfoDTOList", registSkillInfoDTOList);
		httpSession.setAttribute("updateEmpWorkExpDTOList", updateEmpWorkExpDTOList);
		httpSession.setAttribute("registEmpWorkExpDTOList", registEmpWorkExpDTOList);
		httpSession.setAttribute("empInfoDTO", empInfoDTO);
		httpSession.setAttribute("empSkillInfoDTOList", empSkillInfoDTOList);
		httpSession.setAttribute("empWorkExpDTOList", empWorkExpDTOList);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpAction.doPost(httpRequest, httpResponse);
	}
}
