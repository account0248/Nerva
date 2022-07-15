package searchEmployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.searchemployee.ShowSearchEmpAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowSearchEmpActionTest {

	@Test
	// 正常動作
	public void testShowSearchEmp() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String companyId = "CP00000001";
		String companyTestPrivilege="1";
		Map<String, String> searchEmployee = new HashMap<String, String>();
		searchEmployee.put("detailEmployee", "detailEmployee");
		httpRequest.setAttribute("searchEmployee", searchEmployee);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		ShowSearchEmpAction showSearchEmpAction =new ShowSearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		showSearchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showSearchEmpAction.service(httpRequest, httpResponse);
	}
	@Test
	// 正常動作
	//前の画面が詳細画面でない時（親会社）
	public void testShowSearchEmpAction() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String companyId = "CP00000001";
		String companyTestPrivilege="1";
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		ShowSearchEmpAction showSearchEmpAction =new ShowSearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		showSearchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showSearchEmpAction.service(httpRequest, httpResponse);
		
		List<FetchAnyMasterTableDTO> companyList= new ArrayList<FetchAnyMasterTableDTO>();
		FetchAnyMasterTableDTO fetch=new FetchAnyMasterTableDTO ();
		fetch.setMasterDataId("CP00000001");
		fetch.setMasterData("株式会社V");
		companyList.add(fetch);
		FetchAnyMasterTableDTO fetch1=new FetchAnyMasterTableDTO ();
		fetch1.setMasterDataId("CP00000002");
		fetch1.setMasterData("K株式会社");
		companyList.add(fetch1);
		FetchAnyMasterTableDTO fetch2=new FetchAnyMasterTableDTO ();
		fetch2.setMasterDataId("CP00000003");
		fetch2.setMasterData("株式会社O");
		companyList.add(fetch2);
		FetchAnyMasterTableDTO fetch3=new FetchAnyMasterTableDTO ();
		fetch3.setMasterDataId("CP00000004");
		fetch3.setMasterData("A123456789");
		companyList.add(fetch3);

		for(int i = 0; i < ((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("campanyNameList")).size(); i++) {
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("campanyNameList")).get(i).getMasterDataId(),is(companyList.get(i).getMasterDataId()));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("campanyNameList")).get(i).getMasterData(),is(companyList.get(i).getMasterData()));
		
		}
	}

	@Test
	// 正常動作
	//前の画面が詳細画面でない時(子会社)
	public void testShowChildSearchEmpAction() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String companyId = "CP00000002";
		String companyTestPrivilege="0";
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		ShowSearchEmpAction showSearchEmpAction =new ShowSearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		showSearchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showSearchEmpAction.service(httpRequest, httpResponse);


		List<FetchAnyMasterTableDTO> companyList= new ArrayList<FetchAnyMasterTableDTO>();
		FetchAnyMasterTableDTO fetch1=new FetchAnyMasterTableDTO ();
		fetch1.setMasterDataId("CP00000002");
		fetch1.setMasterData("K株式会社");
		companyList.add(fetch1);

		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("campanyNameList")).get(0).getMasterDataId(),is(companyList.get(0).getMasterDataId()));
		assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("campanyNameList")).get(0).getMasterData(),is(companyList.get(0).getMasterData()));
		
		
	}
}
