package searchEmployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCommonCompanyDTO;
import jp.co.vaile.nerva.searchemployee.RegistEmpPageAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class RegistPageActionTest {
	RegistEmpPageAction registPageAction =new RegistEmpPageAction();
	@Test
	// 正常動作
	public void testRegistEmpPageAction() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String userTestId = "admin";
		String companyTestPrivilege = "1";

		httpSession.setAttribute("userId", userTestId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege );
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registPageAction.doGet(httpRequest, httpResponse);
		
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(0).getCompanyName(),is("株式会社V"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(0).getCompanyId(),is("CP00000001"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(0).getCompanyCode(),is("V"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(1).getCompanyName(),is("K株式会社"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(1).getCompanyId(),is("CP00000002"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(1).getCompanyCode(),is("K"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(2).getCompanyName(),is("株式会社O"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(2).getCompanyId(),is("CP00000003"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(2).getCompanyCode(),is("O"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(3).getCompanyName(),is("A123456789"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(3).getCompanyId(),is("CP00000004"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(3).getCompanyCode(),is("Z"));
		
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(0).getMasterData(),is("管理本部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(0).getMasterDataId(),is("D000000001"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(1).getMasterData(),is("営業部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(1).getMasterDataId(),is("D000000002"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(2).getMasterData(),is("システム本部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(2).getMasterDataId(),is("D000000003"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(3).getMasterData(),is("金融システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(3).getMasterDataId(),is("D000000004"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(4).getMasterData(),is("情報システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(4).getMasterDataId(),is("D000000005"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(5).getMasterData(),is("基幹システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(5).getMasterDataId(),is("D000000006"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(6).getMasterData(),is("基盤システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(6).getMasterDataId(),is("D000000007"));
		
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(0).getMasterData(),is("役員"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(0).getMasterDataId(),is("P000000001"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(1).getMasterData(),is("部長"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(1).getMasterDataId(),is("P000000002"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(2).getMasterData(),is("課長"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(2).getMasterDataId(),is("P000000003"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(3).getMasterData(),is("一般"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(3).getMasterDataId(),is("P000000004"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(4).getMasterData(),is("研修"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(4).getMasterDataId(),is("P000000005"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(5).getMasterData(),is("A1234"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(5).getMasterDataId(),is("P000000006"));

		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(0).getMasterData(),is("資格"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(0).getMasterDataId(),is("S000000001"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(1).getMasterData(),is("言語"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(1).getMasterDataId(),is("S000000002"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(2).getMasterData(),is("DB"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(2).getMasterDataId(),is("S000000003"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(3).getMasterData(),is("ツール"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(3).getMasterDataId(),is("S000000004"));
		
	}

	public void testRegistChildEmpPageAction() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String userTestId = "adminK";
		String companyTestPrivilege = "0";

		httpSession.setAttribute("userId", userTestId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege );
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registPageAction.doGet(httpRequest, httpResponse);

		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(0).getCompanyName(),is("K株式会社"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(0).getCompanyId(),is("CP00000002"));
		assertThat(((List<SearchCommonCompanyDTO> ) httpRequest.getAttribute("companyList")).get(0).getCompanyCode(),is("K"));
		
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(0).getMasterData(),is("管理本部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(0).getMasterDataId(),is("D000000001"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(1).getMasterData(),is("営業部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(1).getMasterDataId(),is("D000000002"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(2).getMasterData(),is("システム本部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(2).getMasterDataId(),is("D000000003"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(3).getMasterData(),is("金融システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(3).getMasterDataId(),is("D000000004"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(4).getMasterData(),is("情報システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(4).getMasterDataId(),is("D000000005"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(5).getMasterData(),is("基幹システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(5).getMasterDataId(),is("D000000006"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(6).getMasterData(),is("基盤システム部"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("departmentList")).get(6).getMasterDataId(),is("D000000007"));
		
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(0).getMasterData(),is("役員"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(0).getMasterDataId(),is("P000000001"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(1).getMasterData(),is("部長"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(1).getMasterDataId(),is("P000000002"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(2).getMasterData(),is("課長"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(2).getMasterDataId(),is("P000000003"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(3).getMasterData(),is("一般"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(3).getMasterDataId(),is("P000000004"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(4).getMasterData(),is("研修"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(4).getMasterDataId(),is("P000000005"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(5).getMasterData(),is("A1234"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("postList")).get(5).getMasterDataId(),is("P000000006"));

		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(0).getMasterData(),is("資格"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(0).getMasterDataId(),is("S000000001"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(1).getMasterData(),is("言語"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(1).getMasterDataId(),is("S000000002"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(2).getMasterData(),is("DB"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(2).getMasterDataId(),is("S000000003"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(3).getMasterData(),is("ツール"));
		assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("skillTypeList")).get(3).getMasterDataId(),is("S000000004"));
	}
}