package managementattedanceTest.ImportWorkScheduleTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.managementattedance.importWorkSchedule.ImportCheckAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockServletConfig;

class ImportCheckTest {
	ImportCheckAction importCheckAction = new ImportCheckAction();

	@Test
	@DisplayName("正常動作(拡張子が.xlsx)")
	public void excelFileNamePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[fileName]", "勤務表(2021)_V000000001-test.xlsx");
		
		// 実行クラスがservletのときはおまじないとして記述
		importCheckAction.init(new MockServletConfig());
		
		// 実際にテスト対象のメソッドを実行する。
		importCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("正常動作(拡張子が.text)")
	public void textFileNamePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[fileName]", "勤務表_2021_V000000001_ベイル太郎.text");
		
		// 実行クラスがservletのときはおまじないとして記述
		importCheckAction.init(new MockServletConfig());
		
		// 実際にテスト対象のメソッドを実行する。
		importCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラー動作(ファイル未選択)")
	public void noFileNamePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[fileName]", "");
		
		// 実行クラスがservletのときはおまじないとして記述
		importCheckAction.init(new MockServletConfig());
		
		// 実際にテスト対象のメソッドを実行する。
		importCheckAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラー動作(拡張子が「.xlsx」「.text」以外のファイル)")
	public void incompatibleFileNamePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[fileName]", "test.txt");
		
		// 実行クラスがservletのときはおまじないとして記述
		importCheckAction.init(new MockServletConfig());
		
		// 実際にテスト対象のメソッドを実行する。
		importCheckAction.doPost(httpRequest, httpResponse);
	}

}
