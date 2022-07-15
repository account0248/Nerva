package searchprojecttest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;
import jp.co.vaile.nerva.searchProject.SearchProjectPageDTO;
import jp.co.vaile.nerva.searchProject.ShowProjectAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowProjectTest {

	ShowProjectAction showProjectAction = new ShowProjectAction();
	
	@Test
	public void showProjectTest() throws ServletException, IOException, ClassNotFoundException {
		sessionDestruction();
		transitionPattern1();
		parentCompanyPattern();
		SubsidiaryPattern();
		
	}
	
	@Test
	@DisplayName("セッション破棄")
	public void sessionDestruction() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		SearchProjectPageDTO searchProjectPageDTO = new SearchProjectPageDTO();
		searchProjectPageDTO.setClientName("株式会社B");
		searchProjectPageDTO.setInductryId("I000000001");
		searchProjectPageDTO.setProjectId("P0000001");
		searchProjectPageDTO.setProjectName("sample");
		searchProjectPageDTO.setResponsibleName("管理者");
		searchProjectPageDTO.setTotalEmpProject("2");
		searchProjectPageDTO.setTotalTeamsProject("3");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", "admin");
		httpSession.setAttribute("companyId", "CP00000001");
		httpSession.setAttribute("projectSearchPageDTO", searchProjectPageDTO);
		httpSession.setAttribute("projectId", "P0000001");
		httpSession.setAttribute("companyPrivilege", "1");

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showProjectAction.doGet(httpRequest, httpResponse);
		
		assertThat(((SearchProjectPageDTO) httpSession.getAttribute("projectSearchPageDTO")),nullValue(null));
		assertThat(((String) httpSession.getAttribute("projectId")),nullValue(null));
		
	}
		
	@Test
	@DisplayName("案件詳細画面からの遷移テスト")
	public void transitionPattern1() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		SearchProjectPageDTO searchProjectPageDTO = new SearchProjectPageDTO();
		searchProjectPageDTO.setClientName("株式会社B");
		searchProjectPageDTO.setInductryId("I000000001");
		searchProjectPageDTO.setProjectId("P0000001");
		searchProjectPageDTO.setProjectName("sample");
		searchProjectPageDTO.setResponsibleName("管理者");
		searchProjectPageDTO.setTotalEmpProject("2");
		searchProjectPageDTO.setTotalTeamsProject("3");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", "admin");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setAttribute("projectSearchPageDTO", searchProjectPageDTO);
		httpSession.setAttribute("companyPrivilege", "1");

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showProjectAction.doGet(httpRequest, httpResponse);
		
		
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpRequest.getAttribute("projectSearchPageDTO");
	
		
		assertThat(projectSearchPageDTO.getClientName(),is("株式会社B"));
		assertThat(projectSearchPageDTO.getInductryId(),is("I000000001"));
		assertThat(projectSearchPageDTO.getProjectId(),is("P0000001"));
		assertThat(projectSearchPageDTO.getProjectName(),is("sample"));
		assertThat(projectSearchPageDTO.getResponsibleName(),is("管理者"));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is("3"));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is("2"));
		
		

		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(0).getOrderSourceId(),is(7062));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(0).getOrderSourceName(),is("株式会社B"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(1).getOrderSourceId(),is(7063));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(1).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(2).getOrderSourceId(),is(7064));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(2).getOrderSourceName(),is("株式会社B"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(3).getOrderSourceId(),is(7065));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(3).getOrderSourceName(),is("株式会社B"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(4).getOrderSourceId(),is(7066));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(4).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(5).getOrderSourceId(),is(7067));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(5).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(6).getOrderSourceId(),is(7068));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(6).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(7).getOrderSourceId(),is(7069));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(7).getOrderSourceName(),is("A123456789B123456789"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(0).getMasterData(),is("株式会社V"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(0).getMasterDataId(),is("CP00000001"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(1).getMasterData(),is("K株式会社"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(1).getMasterDataId(),is("CP00000002"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(2).getMasterData(),is("株式会社O"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(2).getMasterDataId(),is("CP00000003"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(0).getMasterData(),is("金融"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(0).getMasterDataId(),is("I000000001"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(1).getMasterData(),is("医療"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(1).getMasterDataId(),is("I000000002"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(2).getMasterData(),is("教育"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(2).getMasterDataId(),is("I000000003"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(3).getMasterData(),is("情報通信"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(3).getMasterDataId(),is("I000000004"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(4).getMasterData(),is("製造"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(4).getMasterDataId(),is("I000000005"));
		
	}
	
	@Test
	@DisplayName( "親会社正常動作")
	public void parentCompanyPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", "admin");
		httpSession.setAttribute("companyId", "CP00000001");
		httpSession.setAttribute("companyPrivilege", "1");

		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showProjectAction.doGet(httpRequest, httpResponse);
		
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(0).getOrderSourceId(),is(7062));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(0).getOrderSourceName(),is("株式会社B"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(1).getOrderSourceId(),is(7063));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(1).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(2).getOrderSourceId(),is(7064));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(2).getOrderSourceName(),is("株式会社B"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(3).getOrderSourceId(),is(7065));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(3).getOrderSourceName(),is("株式会社B"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(4).getOrderSourceId(),is(7066));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(4).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(5).getOrderSourceId(),is(7067));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(5).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(6).getOrderSourceId(),is(7068));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(6).getOrderSourceName(),is("株式会社J"));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(7).getOrderSourceId(),is(7069));
		assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(7).getOrderSourceName(),is("A123456789B123456789"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(0).getMasterData(),is("株式会社V"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(0).getMasterDataId(),is("CP00000001"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(1).getMasterData(),is("K株式会社"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(1).getMasterDataId(),is("CP00000002"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(2).getMasterData(),is("株式会社O"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(2).getMasterDataId(),is("CP00000003"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(0).getMasterData(),is("金融"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(0).getMasterDataId(),is("I000000001"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(1).getMasterData(),is("医療"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(1).getMasterDataId(),is("I000000002"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(2).getMasterData(),is("教育"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(2).getMasterDataId(),is("I000000003"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(3).getMasterData(),is("情報通信"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(3).getMasterDataId(),is("I000000004"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(4).getMasterData(),is("製造"));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(4).getMasterDataId(),is("I000000005"));
		
	}
		
		@Test
		@DisplayName( "子会社正常動作")
		public void SubsidiaryPattern() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpSession httpSession = new MockHttpSession();
			MockHttpResponse httpResponse = new MockHttpResponse();

			// 疑似セッションスコープに情報をセット
			httpSession.setAttribute("userId", "taro");
			httpSession.setAttribute("companyId", "CP00000002");
			httpSession.setAttribute("companyPrivilege", "0");
			
			httpRequest.setSession(httpSession);

			// 実行クラスがservletのときはおまじないとして記述
			showProjectAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			showProjectAction.doGet(httpRequest, httpResponse);
			
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(0).getOrderSourceId(),is(7062));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(0).getOrderSourceName(),is("株式会社B"));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(1).getOrderSourceId(),is(7063));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(1).getOrderSourceName(),is("株式会社J"));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(2).getOrderSourceId(),is(7064));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(2).getOrderSourceName(),is("株式会社B"));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(3).getOrderSourceId(),is(7065));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(3).getOrderSourceName(),is("株式会社B"));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(4).getOrderSourceId(),is(7066));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(4).getOrderSourceName(),is("株式会社J"));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(5).getOrderSourceId(),is(7067));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(5).getOrderSourceName(),is("株式会社J"));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(6).getOrderSourceId(),is(7068));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(6).getOrderSourceName(),is("株式会社J"));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(7).getOrderSourceId(),is(7069));
			assertThat(((List<OrderSourceDTO>) httpRequest.getAttribute("clientData")).get(7).getOrderSourceName(),is("A123456789B123456789"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(0).getMasterData(),is("K株式会社"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("constractorData")).get(0).getMasterDataId(),is("CP00000002"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(0).getMasterData(),is("金融"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(0).getMasterDataId(),is("I000000001"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(1).getMasterData(),is("医療"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(1).getMasterDataId(),is("I000000002"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(2).getMasterData(),is("教育"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(2).getMasterDataId(),is("I000000003"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(3).getMasterData(),is("情報通信"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(3).getMasterDataId(),is("I000000004"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(4).getMasterData(),is("製造"));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("inductryData")).get(4).getMasterDataId(),is("I000000005"));
			
	}

}
