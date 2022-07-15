package masterSkillTypeTest.searchSkillTypeTest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.masterSkillType.searchSkillType.ShowSkillTypeMstAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

public class ShowSkillTypeMstTest {
	// 正常動作(マスタ管理画面から遷移)
		ShowSkillTypeMstAction showSkillTypeMstAction = new ShowSkillTypeMstAction(); 
			@Test
			@DisplayName("正常動作(マスタ管理画面から遷移)")
			public void masterSkillTypePattern() throws ServletException, IOException {
				MockHttpRequest httpRequest = new MockHttpRequest();
				MockHttpResponse httpResponse = new MockHttpResponse();

				// 実行クラスがservletのときはおまじないとして記述
				showSkillTypeMstAction.init(new MockServletConfig());

				// 実際にテスト対象のメソッドを実行する。
				showSkillTypeMstAction.doGet(httpRequest, httpResponse);
				
				// 実行結果を確認
				//セッションに検索結果リストが格納されていることで検索に成功
				assertThat(httpRequest.getAttribute("years"), is("年数"));
				assertThat(httpRequest.getAttribute("dateOfAcquisition"), is("取得日"));
				assertThat(httpRequest.getAttribute("yearsValue"), is('1'));
				assertThat(httpRequest.getAttribute("dateOfAcquisitionValue"), is('0'));
			}
}
