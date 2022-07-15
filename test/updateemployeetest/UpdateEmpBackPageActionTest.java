package updateemployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.updateEmployee.UpdateEmpBackPageAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

public class UpdateEmpBackPageActionTest {
	UpdateEmpBackPageAction updateEmpBackPageAction = new UpdateEmpBackPageAction();
	
	@Test
	@DisplayName("正常動作")
	void NomalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		//パラメータに従業員IDを設定
		String employeeId = "V000000001";

		//チームIDを疑似リクエストスコープにセット
		httpRequest.setParameter("employeeId", employeeId);
		
		// 実行クラスがservletのときはおまじないとして記述
		updateEmpBackPageAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateEmpBackPageAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"), is("V000000001"));
	}
}