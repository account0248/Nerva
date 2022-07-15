package masterUserTest.searchUserTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.masterUser.searchUser.ShowUserMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowUserMstTest {
	// 正常動作(マスタ管理画面から遷移)
	ShowUserMstAction showUserMstAction = new ShowUserMstAction(); 
		@Test
		@DisplayName("正常動作(親会社）")
		public void UserMstParentcompanyPattern() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();
			MockHttpSession httpSession = new MockHttpSession();
			
			String companyId = "CP00000001";
			String companyPrivilege = "1";
			//セッションをセット。
			httpSession.setAttribute("companyId",companyId);
			httpSession.setAttribute("companyPrivilege",companyPrivilege);
			httpRequest.setSession(httpSession);
			
			// 実行クラスがservletのときはおまじないとして記述。
			showUserMstAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			showUserMstAction.doGet(httpRequest, httpResponse);
			
			//実行結果を確認。
			assertThat(httpRequest.getAttribute("admin").toString(), is("管理者"));
			assertThat(httpRequest.getAttribute("general").toString(), is("一般"));
			assertThat(httpRequest.getAttribute("adminValue").toString(), is("1"));
			assertThat(httpRequest.getAttribute("generalValue").toString(), is("0"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(0).getMasterData(),is("株式会社V"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(0).getMasterDataId(),is("CP00000001"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(1).getMasterData(),is("K株式会社"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(1).getMasterDataId(),is("CP00000002"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(2).getMasterData(),is("株式会社O"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(2).getMasterDataId(),is("CP00000003"));
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
	
		}
		@Test
		@DisplayName("正常動作(子会社）")
		public void UserMstSubsidiaryPattern() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();
			MockHttpSession httpSession = new MockHttpSession();
			
			String companyId = "CP00000002";
			String companyPrivilege = "0";
			//セッションをセット。
			httpSession.setAttribute("companyId",companyId);
			httpSession.setAttribute("companyPrivilege",companyPrivilege);
			httpRequest.setSession(httpSession);
			
			// 実行クラスがservletのときはおまじないとして記述。
			showUserMstAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			showUserMstAction.doGet(httpRequest, httpResponse);
			
			//実行結果を確認。
			assertThat(httpRequest.getAttribute("admin").toString(), is("管理者"));
			assertThat(httpRequest.getAttribute("general").toString(), is("一般"));
			assertThat(httpRequest.getAttribute("adminValue").toString(), is("1"));
			assertThat(httpRequest.getAttribute("generalValue").toString(), is("0"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(0).getMasterData(),is("K株式会社"));
			assertThat(((List<FetchAnyMasterTableDTO> ) httpRequest.getAttribute("companyNameList")).get(0).getMasterDataId(),is("CP00000002"));
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
	
		}
}
