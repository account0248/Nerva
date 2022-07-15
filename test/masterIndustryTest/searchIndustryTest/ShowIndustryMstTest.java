package masterIndustryTest.searchIndustryTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterIndustry.searchIndustry.ShowIndustryMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

public class ShowIndustryMstTest {
	// 正常動作(マスタ管理画面から遷移)
	ShowIndustryMstAction showIndustryMstAction = new ShowIndustryMstAction(); 
		@Test
		@DisplayName("正常動作(マスタ管理画面から遷移)")
		public void masterIndustryPattern() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();


			// 実行クラスがservletのときはおまじないとして記述
			showIndustryMstAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			showIndustryMstAction.doGet(httpRequest, httpResponse);
		}
}
