package updateemployeetest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.updateEmployee.ReturnMasterTableInfoAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

public class ReturnMasterTableInfoActionTest {
	ReturnMasterTableInfoAction returnMasterTableInfoAction = new ReturnMasterTableInfoAction();
	
	@Test
	@DisplayName("正常動作")
	void NomalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 実行クラスがservletのときはおまじないとして記述
		returnMasterTableInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		returnMasterTableInfoAction.doPost(httpRequest, httpResponse);
	}
}
