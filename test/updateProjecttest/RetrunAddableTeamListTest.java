package updateProjecttest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Ignore;
import org.junit.Test;

import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;
import jp.co.vaile.nerva.updateProject.RetrunAddableTeamListAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class RetrunAddableTeamListTest {
	
	RetrunAddableTeamListAction retrunAddableTeamListAction = new RetrunAddableTeamListAction();
	
	@Test
	public void allRetrunAddableTeamListTest() throws ServletException, IOException {
		retrunAddableTeamListTest1();
		retrunAddableTeamListTest2();
	}
	
	@Test
	@Ignore
	// 正常系
	public void retrunAddableTeamListTest1() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(9999);
		projectDetailPageDTO.setProjectId("P0000999");
		projectDetailPageDTO.setProjectName("テスト案件９９９");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(9999);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020-10-10");
		projectDetailPageDTO.setProjectCompleteDate("2021-10-10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		retrunAddableTeamListAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		retrunAddableTeamListAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系
	public void retrunAddableTeamListTest2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(9999);
		projectDetailPageDTO.setProjectId("P0000999");
		projectDetailPageDTO.setProjectName("テスト案件９９９");
		projectDetailPageDTO.setResponsibleId("K711");
		projectDetailPageDTO.setResponsibleName("K会社　太郎");
		projectDetailPageDTO.setContractorId("CP00000002");
		projectDetailPageDTO.setClientId(9999);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020-10-10");
		projectDetailPageDTO.setProjectCompleteDate("2021-10-10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// ログインユーザ情報
		String adminUser = "K711";
		String companyId = "CP00000002";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		retrunAddableTeamListAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		retrunAddableTeamListAction.doPost(httpRequest, httpResponse);
	}

}
