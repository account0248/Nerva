package updateProjecttest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.Ignore;
import org.junit.Test;

import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;
import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;
import jp.co.vaile.nerva.updateProject.DeleteProjectAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class DeleteProjectTest {
	
	DeleteProjectAction deleteProjectAction = new DeleteProjectAction();
	
	@Test
	public void alldeleteProjectTest() throws ServletException, IOException {
		deleteProjectTest1();
		deleteProjectTest2();
		deleteProjectTest3();
	}
	
	
	@Test
	@Ignore
	// 正常系（案件情報+案件詳細+従業員業務経験テーブル論理削除＋新規従業員業務経験作成）
	// 従業員の所属完了日・月単価がnullでない場合
	public void deleteProjectTest1() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(72337);
		projectDetailPageDTO.setProjectId("P0000050");
		projectDetailPageDTO.setProjectName("テスト案件５０");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7187);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");
		
		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7434);
		projectEntryTeamDTO.setTeamId("T0000V0050");
		projectEntryTeamDTO.setTeamName("テストチーム５０");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
		entryTeamList.add(projectEntryTeamDTO);
		
		httpSession.setAttribute("updatePjtEntryTeamDto", entryTeamList);
		
		// ログインユーザ情報
		String adminUser = "adminUser";
		
		httpSession.setAttribute("userId", adminUser);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件情報+案件詳細+従業員業務経験テーブル論理削除＋新規従業員業務経験作成）
	// 従業員の所属完了日・月単価がnullでない場合
	public void deleteProjectTest2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(72338);
		projectDetailPageDTO.setProjectId("P0000051");
		projectDetailPageDTO.setProjectName("テスト案件５１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7155);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");
		
		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
		projectEntryTeamDTO.setProjectDetailId(7435);
		projectEntryTeamDTO.setTeamId("T0000V0051");
		projectEntryTeamDTO.setTeamName("テストチーム５１");
		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
		projectEntryTeamDTO.setTeamManagerId("adminUser");
		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
		projectEntryTeamDTO.setTeamLeaderId("adminUser");
		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
		entryTeamList.add(projectEntryTeamDTO);
		
		httpSession.setAttribute("updatePjtEntryTeamDto", entryTeamList);
		
		// ログインユーザ情報
		String adminUser = "adminUser";
		
		httpSession.setAttribute("userId", adminUser);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（案件が既に削除済みの場合）
	public void deleteProjectTest3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		// 案件情報
		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(99999);
		projectDetailPageDTO.setProjectId("P0000999");
		projectDetailPageDTO.setProjectName("テスト案件９９９");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(9999);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");
		
		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		ArrayList<ProjectEntryTeamDTO> entryTeamList = new ArrayList<ProjectEntryTeamDTO>();
//		ProjectEntryTeamDTO projectEntryTeamDTO = new ProjectEntryTeamDTO();
//		projectEntryTeamDTO.setProjectDetailId(7434);
//		projectEntryTeamDTO.setTeamId("T0000V0050");
//		projectEntryTeamDTO.setTeamName("テストチーム５０");
//		projectEntryTeamDTO.setBelongCompanyId("CP00000001");
//		projectEntryTeamDTO.setBelongCompanyName("株式会社V");
//		projectEntryTeamDTO.setTeamManagerId("adminUser");
//		projectEntryTeamDTO.setTeamManagerName("権限　太郎");
//		projectEntryTeamDTO.setTeamLeaderId("adminUser");
//		projectEntryTeamDTO.setTeamLeaderName("権限　太郎");
//		projectEntryTeamDTO.setAssignStartDate("2020-10-10");
//		projectEntryTeamDTO.setAssignCompleteDate("2020-10-10");
//		entryTeamList.add(projectEntryTeamDTO);
		
		httpSession.setAttribute("updatePjtEntryTeamDto", entryTeamList);
		
		// ログインユーザ情報
		String adminUser = "adminUser";
		
		httpSession.setAttribute("userId", adminUser);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		deleteProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		deleteProjectAction.doPost(httpRequest, httpResponse);
	}

}
