package detailprojecttest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;
import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;
import jp.co.vaile.nerva.detailProject.ShowProjectDetailAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class ShowProjectDetailActionTest {

	ShowProjectDetailAction showProjectDetailAction = new ShowProjectDetailAction();



	@Test
	@DisplayName("正常系表示テスト")
	void testNormalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String companyId = "CP00000001";
		String projectId = "P0000001";
		String companyPrivilege = "1";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setParameter("projectId", projectId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showProjectDetailAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showProjectDetailAction.doPost(httpRequest, httpResponse);
		
		ProjectDetailPageDTO projectDetailPageDto = (ProjectDetailPageDTO) httpRequest.getAttribute("projectDetailPageDto");
		
		String projectId1 = projectDetailPageDto.getProjectId();
		String projectName = projectDetailPageDto.getProjectName();
		String responsibleId = projectDetailPageDto.getResponsibleId();
		String responsibleName = projectDetailPageDto.getResponsibleName();
		String contractorId = projectDetailPageDto.getContractorId();
		String contractorName = projectDetailPageDto.getContractorName();
		String industryId = projectDetailPageDto.getIndustryId();
		String industryName = projectDetailPageDto.getIndustryName();
		String projectStartDate = projectDetailPageDto.getProjectStartDate();
		String projectCompleteDate = projectDetailPageDto.getProjectCompleteDate();
		

		assertThat((String) httpSession.getAttribute("projectId"),is(projectId));
		assertThat(projectId1,is("P0000001"));
		assertThat(projectName,is("sample"));
		assertThat(responsibleId,is("admin"));
		assertThat(responsibleName,is("管理者"));
		assertThat(contractorId,is("CP00000001"));
		assertThat(contractorName,is("株式会社V"));
		assertThat(industryId,is("I000000001"));
		assertThat(industryName,is("金融"));
		assertThat(projectStartDate,is("2022-03-24"));
		assertThat(projectCompleteDate,is("2022-05-31"));
		
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getProjectDetailId(),is(7081));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamId(),is("T0000V0001"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamLeaderId(),is("V000000010"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamLeaderName(),is("山田　太郎"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamManagerId(),is("admin"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamManagerName(),is("管理者"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamName(),is("sample"));
	}
	
	@Test
	@DisplayName("案件参照権限なしテスト可視性")
	void testAbnormalPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		String companyId = "CP00000002";
		String projectId = "P0000001";
		String companyPrivilege = "0";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("projectId", projectId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showProjectDetailAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showProjectDetailAction.doPost(httpRequest, httpResponse);
		
		assertThat((String) httpSession.getAttribute("projectId"),is(projectId));
	}

	@Test
	@DisplayName("案件情報がないテスト")
	void testNotProjectPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String companyId = "CP00000001";
		String projectId = null;
		String companyPrivilege = "1";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("projectId", projectId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showProjectDetailAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showProjectDetailAction.doPost(httpRequest, httpResponse);
		
		assertThat((String) httpSession.getAttribute("projectId"),is(projectId));
		assertThat((String) httpRequest.getAttribute("errorMsg"),is("この案件は存在しません。"));
	}

	@Test
	@DisplayName("案件完了日がnullの場合")
	void testProjectCompleteDateisNull() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String companyId = "CP00000001";
		String projectId = "P0000002";
		String companyPrivilege = "1";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("projectId", projectId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showProjectDetailAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showProjectDetailAction.doPost(httpRequest, httpResponse);
		
		assertThat((String) httpSession.getAttribute("projectId"),is(projectId));
		
ProjectDetailPageDTO projectDetailPageDto = (ProjectDetailPageDTO) httpRequest.getAttribute("projectDetailPageDto");
		
		String projectId1 = projectDetailPageDto.getProjectId();
		String projectName = projectDetailPageDto.getProjectName();
		String responsibleId = projectDetailPageDto.getResponsibleId();
		String responsibleName = projectDetailPageDto.getResponsibleName();
		String contractorId = projectDetailPageDto.getContractorId();
		String contractorName = projectDetailPageDto.getContractorName();
		String industryId = projectDetailPageDto.getIndustryId();
		String industryName = projectDetailPageDto.getIndustryName();
		String projectStartDate = projectDetailPageDto.getProjectStartDate();
		String projectCompleteDate = projectDetailPageDto.getProjectCompleteDate();
		

		assertThat((String) httpSession.getAttribute("projectId"),is(projectId));
		assertThat(projectId1,is("P0000002"));
		assertThat(projectName,is("sample2"));
		assertThat(responsibleId,is("admin"));
		assertThat(responsibleName,is("管理者"));
		assertThat(contractorId,is("CP00000001"));
		assertThat(contractorName,is("株式会社V"));
		assertThat(industryId,is("I000000004"));
		assertThat(industryName,is("情報通信"));
		assertThat(projectStartDate,is("2022-03-24"));
		assertThat(projectCompleteDate,nullValue(null));

		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getProjectDetailId(),is(7080));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamId(),is("T0000V0002"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamLeaderId(),nullValue(null));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamLeaderName(),nullValue(null));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamManagerId(),is("admin"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamManagerName(),is("管理者"));
		assertThat(((ArrayList<ProjectEntryTeamDTO>) httpRequest.getAttribute("projectEntryTeamList")).get(0).getTeamName(),is("sample2"));

	}

}
