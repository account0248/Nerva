package teamdetailtest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.teamDetail.ShowTeamDetailInfoAction;
import jp.co.vaile.nerva.teamDetail.TeamDetailPageDTO;
import jp.co.vaile.nerva.teamDetail.TeamEntryEmpDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class ShowTeamDetailInfoActionTest {

	ShowTeamDetailInfoAction showTeamDetailInfoAction = new ShowTeamDetailInfoAction();

	@Test
	@DisplayName("参照権限が可視範囲内である場合")
	public void testInReferenceAccess() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//パラメータに所属会社ID・チームIDを設定
		String companyId = "CP00000001";
		String teamId = "T0000V0001";
		String companyPrivilege = "1";

		//チームIDを疑似リクエストスコープにセット
		httpRequest.setAttribute("teamId", teamId);
		
		//所属会社IDを疑似セッションスコープにセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showTeamDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showTeamDetailInfoAction.doPost(httpRequest, httpResponse);
		
		TeamDetailPageDTO teamDetailPageDTO = (TeamDetailPageDTO)httpRequest.getAttribute("teamDetailPageDTO");
		List<TeamEntryEmpDTO> teamEntryEmpList = (List<TeamEntryEmpDTO>)httpRequest.getAttribute("teamEntryEmpList");
		
		assertThat(teamDetailPageDTO.getTeamId(), is("T0000V0001"));
		assertThat(teamDetailPageDTO.getTeamName(), is("チームT"));
		assertThat(teamDetailPageDTO.getResponsibleManager(), is("管理者"));
		assertThat(teamDetailPageDTO.getCompanyId(), is("CP00000001"));
		assertThat(teamDetailPageDTO.getTeamLeaderName(), is("小林　花子"));
		assertThat(teamDetailPageDTO.getResponsibleProjectId(), is("P0000001"));
		assertThat(teamDetailPageDTO.getResponsibleProjectName(), is("Test Project"));
		
		
		assertThat(teamEntryEmpList.get(0).getEmpId(), is("K000000001"));
		assertThat(teamEntryEmpList.get(0).getEmpName(), is("田中　太郎"));
		assertThat(teamEntryEmpList.get(0).getRole(), is("サブリーダー"));
		assertThat(teamEntryEmpList.get(0).getBelongCompany(), is("K株式会社"));
		assertThat(teamEntryEmpList.get(0).getContractType(), is("準委任契約"));
		assertThat(teamEntryEmpList.get(0).getAssignStartDate(), is("2022-04-01"));
		assertThat(teamEntryEmpList.get(0).getMonthlyUnitPrice(), is(1000));
		assertThat(teamEntryEmpList.get(1).getEmpId(), is("V000000001"));
		assertThat(teamEntryEmpList.get(1).getEmpName(), is("小林　花子"));
		assertThat(teamEntryEmpList.get(1).getRole(), is("リーダー"));
		assertThat(teamEntryEmpList.get(1).getBelongCompany(), is("株式会社V"));
		assertThat(teamEntryEmpList.get(1).getContractType(), is("請負契約"));
		assertThat(teamEntryEmpList.get(1).getAssignStartDate(), is("2022-04-01"));
		assertThat(teamEntryEmpList.get(1).getMonthlyUnitPrice(), is(0));

	}


	@Test
	@DisplayName("参照権限が可視範囲外である場合")
	public void testOutReferenceAccess() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//パラメータに所属会社ID・チームIDを設定
		String companyId = "CP00000002";
		String teamId = "T0000V0001";
		String companyPrivilege = "0";


		//チームIDを疑似リクエストスコープにセット
		httpRequest.setAttribute("teamId", teamId);
		
		//所属会社IDを疑似セッションスコープにセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showTeamDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showTeamDetailInfoAction.doPost(httpRequest, httpResponse);
	}


	@Test
	@DisplayName("Getメソッドで処理を受け取り、参照権限が可視範囲外である場合")
	public void testOutReferenceAccessDoGet() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		//パラメータに所属会社ID・チームIDを設定
		String companyId = "CP00000003";
		String teamId = "T0000V0001";
		String companyPrivilege = "0";

		//チームIDを疑似リクエストスコープにセット
		httpRequest.setAttribute("teamId", teamId);
		
		//所属会社IDを疑似セッションスコープにセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		showTeamDetailInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showTeamDetailInfoAction.doGet(httpRequest, httpResponse);
	}


	@Test
	@DisplayName("セッション破棄")
	public void sessionDestruction() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// パラメータに所属会社ID・チームIDを設定
		String companyId = "CP00000001";
		String teamId = "T0000V0001";
		String companyPrivilege = "1";
		
		// チームIDを疑似リクエストスコープにセット
		httpRequest.setAttribute("teamId", teamId);
		
		// 所属会社IDを疑似セッションスコープにセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpSession.setAttribute("companyCode", "V");
		httpSession.setAttribute("companyName", "株式会社V");
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		showTeamDetailInfoAction.init(new MockServletConfig());
		
		// 実際にテスト対象のメソッドを実行する。
		showTeamDetailInfoAction.doGet(httpRequest, httpResponse);
		assertThat(((String) httpSession.getAttribute("companyCode")), nullValue(null));
		assertThat(((String) httpSession.getAttribute("companyName")), nullValue(null));
	}
}

