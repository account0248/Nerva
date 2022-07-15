package searchprojecttest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.searchProject.SearchProjectAction;
import jp.co.vaile.nerva.searchProject.SearchProjectCheckLogic;
import jp.co.vaile.nerva.searchProject.SearchProjectLogic;
import jp.co.vaile.nerva.searchProject.SearchProjectPageDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchProjectTest {
	SearchProjectAction searchProjectAction = new SearchProjectAction();
	SearchProjectCheckLogic searchProjectCheckLogic = new SearchProjectCheckLogic();
	SearchProjectLogic searchProjectLogic = new SearchProjectLogic();
	SearchProjectPageDTO searchProjectPageDTO = new SearchProjectPageDTO();

	/**
	 * 全パターンテスト
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Test
	public void searchProjectTest() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		
		AllInputPattern();
		projectIdInputPattern();
		projectNameInputPattern();
		partialMatchProjectNameInputPattern();
		responsibleNameInputPattern();
		partialMatchResponsibleNameInputPattern();
		contratorNameInputPattern();
		contratorNameInputPattern2();
		contratorNameInputPattern3();
		clientNameInputPattern();
		industryNameInputPattern();
		industryNameInputPattern2();
		industryNameInputPattern3();
		industryNameInputPattern4();
		industryNameInputPattern5();
		totalTeamsProjectInputPattern();
		totalEmpProjectInputPattern();
		noInputPattern();
		noInputPattern2();
		noResultPattern();
		projectIdCheckPattern();
		projectIdCheckPattern2();
		projectIdCheckPattern3();
		projectIdCheckPattern4();
		projectIdCheckPattern5();
		projectNameCheckPattern();
		projectNameCheckPattern2();
		responsibleNameCheckPattern();
		responsibleNameCheckPattern2();
		contractorNameCheckPattern();
		clientNameCheckPattern();
		clientNameCheckPattern2();
		industryNameCheckPattern();
		totalTeamsProjectCheckPattern();
		totalTeamsProjectCheckPattern2();
		totalTeamsProjectCheckPattern3();
		totalEmpProjectCheckPattern();
		totalEmpProjectCheckPattern2();
		totalEmpProjectCheckPattern3();
		
	}


	/**
	 * 1.全項目が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（全入力）")
	public void AllInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "P0000001");
		httpRequest.setParameter("json[projectName]", "sample");
		httpRequest.setParameter("json[resposibleName]", "管理者");
		httpRequest.setParameter("json[contratorName]", "CP00000001");
		httpRequest.setParameter("json[clientName]", "株式会社B");
		httpRequest.setParameter("json[inductryName]", "I000000001");
		httpRequest.setParameter("json[totalTeamsProject]", "2");
		httpRequest.setParameter("json[totalEmpProject]", "3");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is("P0000001"));
		assertThat(projectSearchPageDTO.getProjectName(),is("sample"));
		assertThat(projectSearchPageDTO.getResponsibleName(),is("管理者"));
		assertThat(projectSearchPageDTO.getContratorId(),is("CP00000001"));
		assertThat(projectSearchPageDTO.getClientName(),is("株式会社B"));
		assertThat(projectSearchPageDTO.getInductryId(),is( "I000000001"));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is("2"));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is("3"));
		
		
		// サーブレット破棄
		searchProjectAction.destroy();
		
	}
	
	/**
	 * 2.案件IDが正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（案件ID）")
	public void projectIdInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "P0000001");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		
		
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is("P0000001"));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is (""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 3.案件名が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（案件名）")
	public void projectNameInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "sample2");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is("sample2"));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 4.案件名が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "部分一致 条件検索（案件名）")
	public void partialMatchProjectNameInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "le2");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is("le2"));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 5.責任者が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（責任者）")
	public void responsibleNameInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "管理者");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is("管理者"));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 6.責任者が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "部分一致 条件検索（責任者）")
	public void partialMatchResponsibleNameInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "管理");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is("管理"));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 7.受注元が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（受注元）株式会社V")
	public void contratorNameInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "CP00000001");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

        SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is("CP00000001"));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}

	/**
	 * 8.受注元が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（受注元）K株式会社")
	public void contratorNameInputPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "CP00000002");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

        SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is("CP00000002"));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	

	/**
	 * 9.受注元が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（受注元）株式会社O")
	public void contratorNameInputPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "CP00000003");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

        SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is("CP00000003"));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 10.発注元が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（発注元）")
	public void clientNameInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "株式会社J");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),is("株式会社J"));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
	}
	
	/**
	 * 11.業種が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（業種）金融")
	public void industryNameInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "I000000001");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is("I000000001"));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
			
		
	}
	
	/**
	 * 12.業種が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（業種）医療")
	public void industryNameInputPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "I000000002");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is("I000000002"));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
			
		
	}
	
	/**
	 * 13.業種が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（業種）教育")
	public void industryNameInputPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "I000000003");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is("I000000003"));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
			
		
	}
	
	/**
	 * 14.業種が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（業種）情報通信")
	public void industryNameInputPattern4() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "I000000004");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is("I000000004"));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
			
		
	}
	

	/**
	 * 15.業種が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（業種）製造")
	public void industryNameInputPattern5() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "I000000005");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is("I000000005"));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
			
		
	}
	
	/**
	 * 16.参加チーム数が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（参加チーム数）")
	public void totalTeamsProjectInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "1");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is("1"));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 17.総参加人数が正常に入力されている場合、検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "条件検索（総参加人数）")
	public void totalEmpProjectInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "3");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is("3"));
		
		
	}
	
	/**
	 * 18.全項目が未入力の場合、全件検索を行い検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@Test
	@DisplayName( "全件検索(親会社)")
	public void noInputPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", null);
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 19.全項目が未入力の場合、全件検索を行い検索結果が表示されること。
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@Test
	@DisplayName( "全件検索(子会社)")
	public void noInputPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", null);
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "0");
		httpSession.setAttribute("companyId", "CP00000002");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is(""));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 20.検索結果が存在しない条件で検索した場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	@DisplayName( "検索結果0件チェック")
	public void noResultPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "P0000001");
		httpRequest.setParameter("json[projectName]", "sample");
		httpRequest.setParameter("json[resposibleName]", "管理者");
		httpRequest.setParameter("json[contratorName]", "CP00000001");
		httpRequest.setParameter("json[clientName]", "株式会社B");
		httpRequest.setParameter("json[inductryName]", "I000000001");
		httpRequest.setParameter("json[totalTeamsProject]", "0");
		httpRequest.setParameter("json[totalEmpProject]", "0");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
	}
	
	/**
	 * 21.案件IDが7文字で入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "案件ID7文字チェック")
	public void projectIdCheckPattern() throws  ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "P000002");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * 22.案件IDが8文字で入力された場合、検索結果を表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "案件ID8文字チェック")
	public void projectIdCheckPattern2() throws  ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "P0000002");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

        SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
		
		//セッション内容との比較
		assertThat(projectSearchPageDTO.getProjectId(),is("P0000002"));
		assertThat(projectSearchPageDTO.getProjectName(),is(""));
		assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
		assertThat(projectSearchPageDTO.getContratorId(),is(""));
		assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
		assertThat(projectSearchPageDTO.getInductryId(),is(""));
		assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
		assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}
	
	/**
	 * 23.案件IDが9文字で入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "案件ID9文字チェック")
	public void projectIdCheckPattern3() throws  ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "P00000002");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * 24.案件IDが半角英数字でない場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "案件ID半角英数字チェック")
	public void projectIdCheckPattern4() throws  ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "P000000あ");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 25.案件IDが形式通りでない場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "案件ID形式チェック")
	public void projectIdCheckPattern5() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "Z0000001");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 26.案件名が256文字で入力された場合、検索結果を表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "案件名256文字チェック")
	public void projectNameCheckPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "A234567890B234567890C234567890D234567890E234567890F234567890G234567890H234567890I234567890J234567890K234567890L234567890M234567890N234567890O234567890P234567890Q234567890R234567890S234567890T234567890U234567890V234567890W234567890X234567890Y234567890Z23456");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		
		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is("A234567890B234567890C234567890D234567890E234567890F234567890G234567890H234567890I234567890J234567890K234567890L234567890M234567890N234567890O234567890P234567890Q234567890R234567890S234567890T234567890U234567890V234567890W234567890X234567890Y234567890Z23456"));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
			
		
	}

	/**
	 * 27.案件名が257文字で入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "案件名257文字チェック")
	public void projectNameCheckPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "A234567890B234567890C234567890D234567890E234567890F234567890G234567890H234567890I234567890J234567890K234567890L234567890M234567890N234567890O234567890P234567890Q234567890R234567890S234567890T234567890U234567890V234567890W234567890X234567890Y234567890Z234567");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 28.責任者名が20文字で入力された場合、検索結果を表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "責任者20文字チェック")
	public void responsibleNameCheckPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "A234567890B234567890");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is("A234567890B234567890"));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}

	/**
	 * 29.責任者名が21文字で入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "責任者21文字チェック")
	public void responsibleNameCheckPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "A234567890B234567890C");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);
				
		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 30.受注元が妥当でない場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "受注元妥当チェック")
	public void contractorNameCheckPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "CP00000006");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
	}
	/**
	 * 31.発注元が20文字で入力された場合、検索結果を表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "発注元20文字チェック")
	public void clientNameCheckPattern() throws  ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "A234567890B234567890");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),is("A234567890B234567890"));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}

	/**
	 * 32.発注元が21文字で入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "発注元21文字チェック")
	public void clientNameCheckPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "A234567890B234567890C");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 33.業種が妥当でない場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "業種妥当チェック")
	public void industryNameCheckPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "I000000006");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);
				
		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 34.参加チーム数が指定形式でない場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "参加チーム数形式チェック")
	public void totalTeamsProjectCheckPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "あ");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 35.参加チーム数が3文字で入力された場合、検索結果を表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "参加チーム数3文字チェック")
	public void totalTeamsProjectCheckPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "001");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);

		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is("001"));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is(""));
		
	}

	/**
	 * 36.参加チーム数が4文字で入力された場合、エラーメッセージを表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "参加チーム数4文字チェック")
	public void totalTeamsProjectCheckPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "0001");
		httpRequest.setParameter("json[totalEmpProject]", "");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 37.総参加人数が指定形式でない場合、エラーメッセージを出力すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "総参加人数形式チェック")
	public void totalEmpProjectCheckPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "あ");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 38.総参加人数が4文字で入力された場合、検索結果を表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "総参加人数4文字チェック")
	public void totalEmpProjectCheckPattern2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "0003");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
		
		 SearchProjectPageDTO projectSearchPageDTO = (SearchProjectPageDTO)httpSession.getAttribute("projectSearchPageDTO");
			
			//セッション内容との比較
			assertThat(projectSearchPageDTO.getProjectId(),is(""));
			assertThat(projectSearchPageDTO.getProjectName(),is(""));
			assertThat(projectSearchPageDTO.getResponsibleName(),is(""));
			assertThat(projectSearchPageDTO.getContratorId(),is(""));
			assertThat(projectSearchPageDTO.getClientName(),nullValue(null));
			assertThat(projectSearchPageDTO.getInductryId(),is(""));
			assertThat(projectSearchPageDTO.getTotalTeamsProject(),is(""));
			assertThat(projectSearchPageDTO.getTotalEmpProject(),is("0003"));
		
	}
	
	/**
	 * 39.総参加人数が5文字で入力された場合、エラーメッセージを表示すること。
	 * @throws ServletException 
	 * @throws IOException 
	 */
	@Test
	@DisplayName( "総参加人数5文字チェック")
	public void totalEmpProjectCheckPattern3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[projectId]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[resposibleName]", "");
		httpRequest.setParameter("json[contratorName]", "");
		httpRequest.setParameter("json[clientName]", "");
		httpRequest.setParameter("json[inductryName]", "");
		httpRequest.setParameter("json[totalTeamsProject]", "");
		httpRequest.setParameter("json[totalEmpProject]", "00003");

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyPrivilege", "1");
		httpSession.setAttribute("companyId", "CP00000001");
		httpRequest.setSession(httpSession);
				
		// 実行クラスがservletのときはおまじないとして記述
		searchProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchProjectAction.doPost(httpRequest, httpResponse);
	}

	
}
