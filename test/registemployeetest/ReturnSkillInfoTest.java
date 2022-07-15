package registemployeetest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.registEmployee.ReturnSkillInfoAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class ReturnSkillInfoTest {
	ReturnSkillInfoAction returnSkillInfoAction = new ReturnSkillInfoAction();

	@Test
	public void testReturnSkillInfo()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		//1.スキル種別を取得し、正常に処理が終了すること。
		testSuccessPattern();
	}

	// スキル種別を取得し、正常に処理が終了すること。
	@Test
	public void testSuccessPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		returnSkillInfoAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		returnSkillInfoAction.doPost(httpRequest, httpResponse);
	}
}
