package detailprojecttest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.detailProject.DetailPjtBackPageAction;
import jp.co.vaile.nerva.searchProject.SearchProjectPageDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class DetailPjtBackPageActionTest {
	DetailPjtBackPageAction detailPjtBackPageAction = new DetailPjtBackPageAction();



	@Test
	@DisplayName("正常系テスト")
	void testNormalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		SearchProjectPageDTO  projectSearchPageDTO = new SearchProjectPageDTO();
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("projectSearchPageDTO", projectSearchPageDTO);
		httpRequest.setSession(httpSession);

		assertThat((SearchProjectPageDTO) httpSession.getAttribute("projectSearchPageDTO"),is(projectSearchPageDTO));
		
		// 実行クラスがservletのときはおまじないとして記述
		detailPjtBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		detailPjtBackPageAction.doGet(httpRequest, httpResponse);
	
		// サーブレット破棄
		detailPjtBackPageAction.destroy();
	}

}
