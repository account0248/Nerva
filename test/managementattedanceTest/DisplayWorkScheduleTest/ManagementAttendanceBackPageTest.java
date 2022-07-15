package managementattedanceTest.DisplayWorkScheduleTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.FetchAnyMasterTableDTO;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;
import jp.co.vaile.nerva.managementattedance.ManagementAttendanceBackPageAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class ManagementAttendanceBackPageTest {
	ManagementAttendanceBackPageAction managementAttendanceBackPageAction = new ManagementAttendanceBackPageAction();

	@Test
	@DisplayName("正常動作(勤怠情報管理画面から遷移)")
	public void managementAttendanceBackPageTransition()
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		String companyId = "CP00000001";
		String companyPrivilege = "1";

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("companyId", companyId);
		httpSession.setAttribute("companyPrivilege", companyPrivilege);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		managementAttendanceBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		managementAttendanceBackPageAction.doGet(httpRequest, httpResponse);

		Map<String, String> searchEmployee = new HashMap<String, String>();
		searchEmployee.put("managementattedance", "managementattedance");
		
		List<FetchAnyMasterTableDTO> campanyNameList = new ArrayList<>();
		BelongCompanyInfo belongCompanyInfo = new BelongCompanyInfo();
		campanyNameList = belongCompanyInfo.fetchBelongCompanyList(companyId, companyPrivilege);

		// 実行結果を確認
		assertThat(httpRequest.getAttribute("searchEmployee"), is(searchEmployee));
		for (int i = 0; i < campanyNameList.size(); i++) {
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("campanyNameList")).get(i)
					.getMasterDataId(), is(campanyNameList.get(i).getMasterDataId()));
			assertThat(((List<FetchAnyMasterTableDTO>) httpRequest.getAttribute("campanyNameList")).get(i)
					.getMasterData(), is(campanyNameList.get(i).getMasterData()));
		}
	}

}
