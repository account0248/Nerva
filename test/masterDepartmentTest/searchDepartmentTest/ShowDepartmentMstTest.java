package masterDepartmentTest.searchDepartmentTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterDepartment.searchDepartment.ShowDepartmentMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

public class ShowDepartmentMstTest {
	// 正常動作(マスタ管理画面から遷移)
	ShowDepartmentMstAction showDepartmentMstAction = new ShowDepartmentMstAction(); 
		@Test
		@DisplayName("正常動作(マスタ管理画面から遷移)")
		public void masterDepartmentPattern() throws ServletException, IOException {
			MockHttpRequest httpRequest = new MockHttpRequest();
			MockHttpResponse httpResponse = new MockHttpResponse();


			// 実行クラスがservletのときはおまじないとして記述
			showDepartmentMstAction.init(new MockServletConfig());

			// 実際にテスト対象のメソッドを実行する。
			showDepartmentMstAction.doGet(httpRequest, httpResponse);
		}
}
