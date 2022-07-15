package searchEmployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.searchemployee.SearchEmpAction;
import jp.co.vaile.nerva.searchemployee.SearchEmpPageDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class SearchEmpActionTest {

	@Test
	public void testInputSearch() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V300000001");
		httpRequest.setParameter("json[employeeName]", "試験 一郎");
		httpRequest.setParameter("json[projectName]", "冷蔵庫");
		httpRequest.setParameter("json[teamName]", "チームテスト");
		httpRequest.setParameter("json[companyName]", "株式会社V");
		httpRequest.setParameter("json[teamManager]", "管理者");
		httpRequest.setParameter("json[skillFiltering]", "Java");

		String loginCompanyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", loginCompanyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is("V300000001"));
		assertThat(serchEmpPage.getEmployeeName(),is("試験 一郎"));
		assertThat(serchEmpPage.getProjectName(),is("冷蔵庫"));
		assertThat(serchEmpPage.getTeamName(),is("チームテスト"));
		assertThat(serchEmpPage.getCompanyName(),is("株式会社V"));
		assertThat(serchEmpPage.getTeamManager(),is("管理者"));
		assertThat(serchEmpPage.getSkillFiltering(),is("Java"));
	}
	
	@Test
	public void testInputSearchEmpAction() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
		
	@Test
	public void testSearchParamErrorCheck1() throws ServletException,IOException {

		//従業員ID9文字確認のエラー
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V00000000");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
	}
	@Test
	public void testSearchParamErrorCheck2() throws ServletException,IOException {

		//全項目が指定文字数で入力された場合、エラーメッセージを出力する処理が実行されること。
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "Z999999999");
		httpRequest.setParameter("json[employeeName]", "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴ");
		httpRequest.setParameter("json[projectName]", "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇ");
		httpRequest.setParameter("json[teamName]", "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴ");
		httpRequest.setParameter("json[companyName]", "A123456789");
		httpRequest.setParameter("json[teamManager]", "A123456789B123456789");
		httpRequest.setParameter("json[skillFiltering]", "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇ");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is("Z999999999"));
		assertThat(serchEmpPage.getEmployeeName(),is("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴ"));
		assertThat(serchEmpPage.getProjectName(),is("ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇ"));
		assertThat(serchEmpPage.getTeamName(),is("ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴ"));
		assertThat(serchEmpPage.getCompanyName(),is("A123456789"));
		assertThat(serchEmpPage.getTeamManager(),is("A123456789B123456789"));
		assertThat(serchEmpPage.getSkillFiltering(),is("ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇ"));
	}
	
	
	@Test
	public void testSearchParamErrorCheck3() throws ServletException,IOException {

		//従業員ID10文字以内下確認のエラー
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V0000000011");
		httpRequest.setParameter("json[employeeName]", "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴU");
		httpRequest.setParameter("json[projectName]", "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえ");
		httpRequest.setParameter("json[teamName]", "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴU");
		httpRequest.setParameter("json[companyName]", "株式会社V");
		httpRequest.setParameter("json[teamManager]", "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴU");
		httpRequest.setParameter("json[skillFiltering]", "ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんぁあぃいぅうぇえ");

		String companyId = "CP00000001";
		String companyTestPrivilege = "0";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	public void testInputSearchEmpAction2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "全角");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	public void testSearchParamErrorCheck4() throws ServletException,IOException {

		//所属会社選択時エラー
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "株式会社X");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	public void testSelectCompany1() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "株式会社V");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is("株式会社V"));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}

	@Test
	public void testSelectCompany2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "K株式会社");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is("K株式会社"));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSelectCompany3() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "株式会社O");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is("株式会社O"));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSelectCompany4() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "A123456789");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is("A123456789"));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchEmployeeName() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "試験 一郎");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is("試験 一郎"));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchEmployeeName2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "試験");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is("試験"));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchProject() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "冷蔵庫");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is("冷蔵庫"));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchProject2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "冷");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is("冷"));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchTeam() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "チームテスト");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is("チームテスト"));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchTeam2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "チーム");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is("チーム"));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}

	@Test
	public void testSearchManager() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "管理者");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is("管理者"));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchManager2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "管");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is("管"));
		assertThat(serchEmpPage.getSkillFiltering(),is(""));
	}
	
	@Test
	public void testSearchSkill() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "Java");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is("Java"));
	}
	
	@Test
	public void testSearchSkill2() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "J");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
		
		SearchEmpPageDTO serchEmpPage= (SearchEmpPageDTO)httpSession.getAttribute("searchEmpPageDTO");	
		assertThat(serchEmpPage.getEmployeeId(),is(""));
		assertThat(serchEmpPage.getEmployeeName(),is(""));
		assertThat(serchEmpPage.getProjectName(),is(""));
		assertThat(serchEmpPage.getTeamName(),is(""));
		assertThat(serchEmpPage.getCompanyName(),is(""));
		assertThat(serchEmpPage.getTeamManager(),is(""));
		assertThat(serchEmpPage.getSkillFiltering(),is("J"));
	}
	
	@Test
	public void testSearchNoResult() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//正常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V000000009");
		httpRequest.setParameter("json[employeeName]", "試験 三郎");
		httpRequest.setParameter("json[projectName]", "電子レンジ");
		httpRequest.setParameter("json[teamName]", "チームTest");
		httpRequest.setParameter("json[companyName]", "株式会社V");
		httpRequest.setParameter("json[teamManager]", "管理者");
		httpRequest.setParameter("json[skillFiltering]", "C#");

		String companyId = "CP00000001";
		String companyTestPrivilege = "1";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	public void testSelectCompanyError() throws ServletException,IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		//異常時
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[projectName]", "");
		httpRequest.setParameter("json[teamName]", "");
		httpRequest.setParameter("json[companyName]", "株式会社V");
		httpRequest.setParameter("json[teamManager]", "");
		httpRequest.setParameter("json[skillFiltering]", "");

		String companyId = "CP00000002";
		String companyTestPrivilege = "0";
		// 疑似セッションスコープに情報をセット

		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyTestPrivilege);
		httpRequest.setSession(httpSession);
		SearchEmpAction searchEmpAction = new SearchEmpAction();
		// 実行クラスがservletのときはおまじないとして記述
		searchEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		searchEmpAction.doPost(httpRequest, httpResponse);
	}
}
