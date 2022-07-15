package registprojecttest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;
import jp.co.vaile.nerva.registproject.ShowRegistProjectAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ShowRegistProjectActionTest {
	ShowRegistProjectAction showRegistProjectAction = new ShowRegistProjectAction();

	@Test
	@DisplayName("正常動作")
	public void showNomalPattern() throws ServletException, IOException, ClassNotFoundException, SQLException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//パラメータに所属会社ID、所属会社グループ権限を設定
		String companyId = "CP00000001";
		String companyPrivilege = "1";
		
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);
		
		// 実行クラスがservletのときはおまじないとして記述
		showRegistProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		showRegistProjectAction.service(httpRequest, httpResponse);
		
		//実行結果を確認
		assertThat((String)httpRequest.getAttribute("companyName"),is("株式会社V"));
		
		assertThat(((List<OrderSourceDTO>)httpRequest.getAttribute("orderSourceDTOList")).get(0).getOrderSourceName(),is("発注元UT"));
		assertThat(((List<OrderSourceDTO>)httpRequest.getAttribute("orderSourceDTOList")).get(0).getOrderSourceId(),is(1));

		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(0).getMasterData(),is("金融"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(0).getMasterDataId(),is("I000000001"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(1).getMasterData(),is("医療"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(1).getMasterDataId(),is("I000000002"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(2).getMasterData(),is("教育"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(2).getMasterDataId(),is("I000000003"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(3).getMasterData(),is("情報通信"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(3).getMasterDataId(),is("I000000004"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(4).getMasterData(),is("製造"));
		assertThat(((List<FetchAnyMasterTableDTO> )httpRequest.getAttribute("industryDTOList")).get(4).getMasterDataId(),is("I000000005"));

	}
}
