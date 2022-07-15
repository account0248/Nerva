package updateProjecttest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.Ignore;
import org.junit.Test;

import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;
import jp.co.vaile.nerva.updateProject.ProjectInfoDTO;
import jp.co.vaile.nerva.updateProject.UpdatePjtAddTeamDTO;
import jp.co.vaile.nerva.updateProject.UpdateProjectAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class UpdateProjectTest {

	UpdateProjectAction updateProjectAction = new UpdateProjectAction();

	@Test
	@Ignore
	public void allupdateProjectTest() throws ServletException, IOException {
		updateProjectTest1();
		updateProjectTest2();
		updateProjectTest3();
		updateProjectTest4();
		updateProjectTest5();
		updateProjectTest6();
		updateProjectTest7();
		updateProjectTest8();
		updateProjectTest9();
		updateProjectTest10();
		updateProjectTest11();
		updateProjectTest12();
		updateProjectTest13();
		updateProjectTest14();
		updateProjectTest15();
		updateProjectTest16();
		updateProjectTest17();
		updateProjectTest18();
		updateProjectTest19();
		updateProjectTest20();
		updateProjectTest21();
		updateProjectTest22();
		updateProjectTest23();
		updateProjectTest24();
	}

	@Test
	@Ignore
	// 異常系（排他性チェック・案件情報テーブル）
	public void updateProjectTest1() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(7211);
		projectInfoDTO.setProjectId("P0000070");
		projectInfoDTO.setProjectName("テスト案件７０");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7160");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7185);
		projectEntryTeamDTO.setTeamId("T0000V0070");
		projectEntryTeamDTO.setTeamName("テストチーム７０");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2020-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0071");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7184);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（排他性チェック・案件情報詳細テーブル）
	public void updateProjectTest2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(7212);
		projectInfoDTO.setProjectId("P0000071");
		projectInfoDTO.setProjectName("テスト案件７１");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7161");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7186);
		projectEntryTeamDTO.setTeamId("T0000V0071");
		projectEntryTeamDTO.setTeamName("テストチーム７１");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2020-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0071");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7184);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（排他性チェック・案件情報、案件情報詳細テーブル）
	public void updateProjectTest3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(7213);
		projectInfoDTO.setProjectId("P0000072");
		projectInfoDTO.setProjectName("テスト案件７２");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7162");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7187);
		projectEntryTeamDTO.setTeamId("T0000V0072");
		projectEntryTeamDTO.setTeamName("テストチーム７２");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2020-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0071");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7184);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	//@Ignore
	// 正常系（案件情報テーブルのみ更新対象の場合）
	// 案件開始日を変更。案件完了日はそのまま。
	public void updateProjectTest4() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72135);
		projectInfoDTO.setProjectId("P0000073");
		projectInfoDTO.setProjectName("テスト案件７３");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7164");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7188);
//		projectEntryTeamDTO.setTeamId("T0000V0073");
//		projectEntryTeamDTO.setTeamName("テストチーム７３");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
//		entryTeamList.add(projectEntryTeamDTO);
//		
		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);
//		
//		String[] entryTeamAssignCompleteDate = { "2021-10-10" };
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0071");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7184);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブルのみ更新対象の場合）
	// 案件開始日はそのまま。案件完了日を変更。（値なし⇒値あり）
	public void updateProjectTest5() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72136);
		projectInfoDTO.setProjectId("P0000074");
		projectInfoDTO.setProjectName("テスト案件７４");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7165");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7189);
//		projectEntryTeamDTO.setTeamId("T0000V0074");
//		projectEntryTeamDTO.setTeamName("テストチーム７４");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("");
//		entryTeamList.add(projectEntryTeamDTO);
//		
		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);
//		
//		String[] entryTeamAssignCompleteDate = {""};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0073");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7191);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブルのみ更新対象の場合）
	// 案件開始日はそのまま。案件完了日を変更。（値あり⇒値なし）
	public void updateProjectTest6() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72140);
		projectInfoDTO.setProjectId("P0000075");
		projectInfoDTO.setProjectName("テスト案件７５");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7166");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7189);
//		projectEntryTeamDTO.setTeamId("T0000V0074");
//		projectEntryTeamDTO.setTeamName("テストチーム７４");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
//		entryTeamList.add(projectEntryTeamDTO);
//		
		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {""};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0073");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7191);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブルのみ更新対象の場合）
	// 案件開始日はそのまま。案件完了日を変更。（値あり⇒値あり）
	public void updateProjectTest7() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72141);
		projectInfoDTO.setProjectId("P0000076");
		projectInfoDTO.setProjectName("テスト案件７６");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7167");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2022-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7199);
//		projectEntryTeamDTO.setTeamId("T0000V0075");
//		projectEntryTeamDTO.setTeamName("テストチーム７５");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0073");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7191);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報詳細テーブルのみ更新対象の場合・既存チームあり追加チームなし・従業員なし）
	// 既存チームの配属完了日を変更。（値なし⇒値あり）
	public void updateProjectTest8() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72142);
		projectInfoDTO.setProjectId("P0000077");
		projectInfoDTO.setProjectName("テスト案件７７");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7168");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7334);
		projectEntryTeamDTO.setTeamId("T0000V0073");
		projectEntryTeamDTO.setTeamName("テストチーム７３");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2020-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0077");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(72142);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報詳細テーブルのみ更新対象の場合・既存チームあり追加チームなし・従業員なし）
	// 既存チームの配属完了日を変更。（値あり⇒値なし）
	public void updateProjectTest9() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72148);
		projectInfoDTO.setProjectId("P0000078");
		projectInfoDTO.setProjectName("テスト案件７８");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7169");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7335);
		projectEntryTeamDTO.setTeamId("T0000V0074");
		projectEntryTeamDTO.setTeamName("テストチーム７４");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0077");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(72142);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報詳細テーブルのみ更新対象の場合・既存チームあり追加チームなし・従業員なし）
	// 既存チームの配属完了日を変更。（値あり⇒値あり）
	public void updateProjectTest10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72150);
		projectInfoDTO.setProjectId("P0000079");
		projectInfoDTO.setProjectName("テスト案件７９");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7170");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7336);
		projectEntryTeamDTO.setTeamId("T0000V0075");
		projectEntryTeamDTO.setTeamName("テストチーム７５");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2021-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0080");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(72150);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報詳細テーブルのみ更新対象の場合・既存チームなし追加チームあり・従業員なし）
	// 新規チームの配属完了日を変更。（値なし）
	public void updateProjectTest11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72152);
		projectInfoDTO.setProjectId("P0000080");
		projectInfoDTO.setProjectName("テスト案件８０");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7171");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7211);
//		projectEntryTeamDTO.setTeamId("T0000V0081");
//		projectEntryTeamDTO.setTeamName("テストチーム８１");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
//		entryTeamList.add(projectEntryTeamDTO);
//		
		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);
//		
//		String[] entryTeamAssignCompleteDate = {"2020-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0076");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("");
		updatePjtAddTeamDTO.setProjectInfoId(72152);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報詳細テーブルのみ更新対象の場合・既存チームなし追加チームあり・従業員なし）
	// 新規チームの配属完了日を変更。（値あり）
	public void updateProjectTest12() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72166);
		projectInfoDTO.setProjectId("P0000081");
		projectInfoDTO.setProjectName("テスト案件８１");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7172");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7189);
//		projectEntryTeamDTO.setTeamId("T0000V0074");
//		projectEntryTeamDTO.setTeamName("テストチーム７４");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
//		entryTeamList.add(projectEntryTeamDTO);
//		
		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {""};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0077");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
		updatePjtAddTeamDTO.setProjectInfoId(72166);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報詳細テーブル+従業員業務経験テーブルが更新対象の場合・既存チームなし追加チームあり・従業員あり）
	// 新規追加チームあり。（従業員あり（所属完了日・月単価NULLでない））
	// ※従業員業務経験テーブルが更新されることを確認
	public void updateProjectTest13() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72167);
		projectInfoDTO.setProjectId("P0000082");
		projectInfoDTO.setProjectName("テスト案件８２");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7173");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7339);
//		projectEntryTeamDTO.setTeamId("T0000V0078");
//		projectEntryTeamDTO.setTeamName("テストチーム７８");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0078");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
		updatePjtAddTeamDTO.setProjectInfoId(72167);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報詳細テーブル+従業員業務経験テーブルが更新対象の場合・既存チームなし追加チームあり・従業員あり）
	// 新規追加チームあり。（従業員あり（所属完了日・月単価NULL））
	// ※従業員業務経験テーブルが更新されることを確認
	public void updateProjectTest14() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72221);
		projectInfoDTO.setProjectId("P0000083");
		projectInfoDTO.setProjectName("テスト案件８３");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7174");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7340);
//		projectEntryTeamDTO.setTeamId("T0000V0079");
//		projectEntryTeamDTO.setTeamName("テストチーム７９");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0079");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
		updatePjtAddTeamDTO.setProjectInfoId(7191);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブルが更新対象の場合・既存チームあり追加チームなし・従業員なし）
	// 案件開始日を変更。案件完了日はそのまま。
	// 既存チームの配属完了日を変更。（値なし⇒値あり）
	public void updateProjectTest15() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72230);
		projectInfoDTO.setProjectId("P0000084");
		projectInfoDTO.setProjectName("テスト案件８４");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7175");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7341);
		projectEntryTeamDTO.setTeamId("T0000V0080");
		projectEntryTeamDTO.setTeamName("テストチーム８０");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2020-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0073");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7191);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブルが更新対象の場合・既存チームあり追加チームなし・従業員なし）
	// 案件開始日を変更。案件完了日はそのまま。
	// 既存チームの配属完了日を変更。（値あり⇒値なし）
	public void updateProjectTest16() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72232);
		projectInfoDTO.setProjectId("P0000085");
		projectInfoDTO.setProjectName("テスト案件８５");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7176");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7342);
		projectEntryTeamDTO.setTeamId("T0000V0081");
		projectEntryTeamDTO.setTeamName("テストチーム８１");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0073");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7191);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブルが更新対象の場合・既存チームあり追加チームなし・従業員なし）
	// 案件開始日を変更。案件完了日はそのまま。
	// 既存チームの配属完了日を変更。（値あり⇒値あり）
	public void updateProjectTest17() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72257);
		projectInfoDTO.setProjectId("P0000086");
		projectInfoDTO.setProjectName("テスト案件８６");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7177");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7343);
		projectEntryTeamDTO.setTeamId("T0000V0082");
		projectEntryTeamDTO.setTeamName("テストチーム８２");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2021-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0073");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(7191);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブルが更新対象の場合・既存チームなし追加チームあり・従業員なし）
	// 案件開始日を変更。案件完了日はそのまま。
	// 新規チームの配属完了日を変更。（値なし）
	public void updateProjectTest18() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72258);
		projectInfoDTO.setProjectId("P0000087");
		projectInfoDTO.setProjectName("テスト案件８７");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7178");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7343);
//		projectEntryTeamDTO.setTeamId("T0000V0082");
//		projectEntryTeamDTO.setTeamName("テストチーム８２");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0083");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("");
		updatePjtAddTeamDTO.setProjectInfoId(72258);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブルが更新対象の場合・既存チームなし追加チームあり・従業員なし）
	// 案件開始日を変更。案件完了日はそのまま。
	// 新規チームの配属完了日を変更。（値あり）
	public void updateProjectTest19() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72259);
		projectInfoDTO.setProjectId("P0000088");
		projectInfoDTO.setProjectName("テスト案件８８");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7179");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7343);
//		projectEntryTeamDTO.setTeamId("T0000V0082");
//		projectEntryTeamDTO.setTeamName("テストチーム８２");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0084");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
		updatePjtAddTeamDTO.setProjectInfoId(72259);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブル+従業員業務経験テーブルが更新対象の場合・既存チームあり追加チームなし・従業員あり）
	// 案件開始日を変更。案件完了日はそのまま。
	// 既存チームの配属完了日を変更。（従業員（所属完了日・月単価NULLでない））
	// ※従業員業務経験テーブルが更新されることを確認
	public void updateProjectTest20() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72260);
		projectInfoDTO.setProjectId("P0000089");
		projectInfoDTO.setProjectName("テスト案件８９");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7180");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7344);
		projectEntryTeamDTO.setTeamId("T0000V0085");
		projectEntryTeamDTO.setTeamName("テストチーム８５");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2021-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0084");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(72259);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブル+従業員業務経験テーブルが更新対象の場合・既存チームあり追加チームなし・従業員あり）
	// 案件開始日を変更。案件完了日はそのまま。
	// 既存チームの配属完了日を変更。（従業員（所属完了日・月単価NULL））
	// ※従業員業務経験テーブルが更新されることを確認
	public void updateProjectTest21() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72261);
		projectInfoDTO.setProjectId("P0000090");
		projectInfoDTO.setProjectName("テスト案件９０");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7181");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7345);
		projectEntryTeamDTO.setTeamId("T0000V0086");
		projectEntryTeamDTO.setTeamName("テストチーム８６");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

		String[] entryTeamAssignCompleteDate = { "2021-10-10" };
		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0084");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(72259);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブル+従業員業務経験テーブルが更新対象の場合・既存チームなし追加チームあり・従業員あり）
	// 案件開始日を変更。案件完了日はそのまま。
	// 新規チームあり。（従業員（所属完了日・月単価NULLでない））
	// ※従業員業務経験テーブルが更新されることを確認
	public void updateProjectTest22() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72262);
		projectInfoDTO.setProjectId("P0000091");
		projectInfoDTO.setProjectName("テスト案件９１");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7181");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7345);
//		projectEntryTeamDTO.setTeamId("T0000V0086");
//		projectEntryTeamDTO.setTeamName("テストチーム８６");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0087");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
		updatePjtAddTeamDTO.setProjectInfoId(72262);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報テーブル+案件情報詳細テーブル+従業員業務経験テーブルが更新対象の場合・既存チームなし追加チームあり・従業員あり）
	// 案件開始日を変更。案件完了日はそのまま。
	// 新規チームあり。（従業員（所属完了日・月単価NULL））
	// ※従業員業務経験テーブルが更新されることを確認
	public void updateProjectTest23() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72294);
		projectInfoDTO.setProjectId("P0000092");
		projectInfoDTO.setProjectName("テスト案件９２");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7185");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2019-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7345);
//		projectEntryTeamDTO.setTeamId("T0000V0086");
//		projectEntryTeamDTO.setTeamName("テストチーム８６");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
		updatePjtAddTeamDTO.setTeamId("T0000V0088");
		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
		updatePjtAddTeamDTO.setProjectInfoId(72294);
		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);

		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（更新するものがない場合）
	public void updateProjectTest24() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
		projectInfoDTO.setProjectInfoId(72295);
		projectInfoDTO.setProjectId("P0000093");
		projectInfoDTO.setProjectName("テスト案件９３");
		projectInfoDTO.setResponsibleId("adminUser");
		projectInfoDTO.setContractorId("CP00000001");
		projectInfoDTO.setClientId("7186");
		projectInfoDTO.setIndustryId("I000000004");
		projectInfoDTO.setProjectStartDate("2020-10-10");
		projectInfoDTO.setProjectCompleteDate("2021-10-10");
		projectInfoDTO.setRegistUser("adminUser");

		httpSession.setAttribute("projectInfoDTO", projectInfoDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7346);
//		projectEntryTeamDTO.setTeamId("T0000V0086");
//		projectEntryTeamDTO.setTeamName("テストチーム８６");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2021-10-10");
//		entryTeamList.add(projectEntryTeamDTO);

		httpSession.setAttribute("updatePjtEntryTeamList", entryTeamList);

//		String[] entryTeamAssignCompleteDate = {"2021-10-10"};
//		httpSession.setAttribute("entryTeamAssignCompleteDate", entryTeamAssignCompleteDate);

		// 追加チーム情報
//		ArrayList<UpdatePjtAddTeamDTO> updateAddTeamDTOArr = new ArrayList<UpdatePjtAddTeamDTO>();
//		UpdatePjtAddTeamDTO updatePjtAddTeamDTO = new UpdatePjtAddTeamDTO();
//		updatePjtAddTeamDTO.setTeamId("T0000V0084");
//		updatePjtAddTeamDTO.setRegistUsertId("adminUser");
//		updatePjtAddTeamDTO.setAssignStartDate("2020-10-10");
//		updatePjtAddTeamDTO.setAssignCompleteDate("2020-10-10");
//		updatePjtAddTeamDTO.setProjectInfoId(72259);
//		updateAddTeamDTOArr.add(updatePjtAddTeamDTO);
//		
//		httpSession.setAttribute("addTeamDTOArr", updateAddTeamDTOArr);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";

		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateProjectAction.doPost(httpRequest, httpResponse);
	}

}
