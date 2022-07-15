package managementattedanceTest.ImportWorkScheduleTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.poi.util.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.mock.web.MockPart;

import jp.co.vaile.nerva.managementattedance.importWorkSchedule.ImportExcelAction;
//import mock.MockHttpResponse;
//import mock.MockServletConfig;
import mock.MockServletConfig;

class ImportExcelTest {

	private byte[] convertFile(File file) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		return IOUtils.toByteArray(inputStream);
	}

	@Test
	@DisplayName("正常動作")
	public void excelImportNormalPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "12");
		mockMultipartHttpServletRequest.setParameter("days", "31");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(ファイル名がフォーマット通りでない)")
	public void fileNameErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "04");
		mockMultipartHttpServletRequest.setParameter("days", "30");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(パスワードでファイルが開けない)")
	public void canNotOpenFilePattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "12");
		mockMultipartHttpServletRequest.setParameter("days", "31");
		mockMultipartHttpServletRequest.setParameter("password", "");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(開始が文字列)")
	public void noStartTimeFormatPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "05");
		mockMultipartHttpServletRequest.setParameter("days", "31");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(終了が文字列)")
	public void noEndTimeFormatPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "06");
		mockMultipartHttpServletRequest.setParameter("days", "30");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(休憩が文字列)")
	public void noRestTimeFormatPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "07");
		mockMultipartHttpServletRequest.setParameter("days", "31");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(法定休日出勤時間が文字列)")
	public void noHolidayWorkingTimeFormatPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "08");
		mockMultipartHttpServletRequest.setParameter("days", "31");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(有給期間等:未入力、有給日数:入力)")
	public void holidayDayInjusticePattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "09");
		mockMultipartHttpServletRequest.setParameter("days", "30");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(有給期間等:未入力、有給時間:入力)")
	public void holidayTimeInjusticePattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "10");
		mockMultipartHttpServletRequest.setParameter("days", "31");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(遅早時刻が文字列)")
	public void noLateTimeFormatPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-test.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-test.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "11");
		mockMultipartHttpServletRequest.setParameter("days", "30");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	

	@Test
	@DisplayName("エラー動作(Excelファイルパスワードなしをインポート)")
	public void noPasswordExcelImportPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportExcelAction importExcelAction = new ImportExcelAction();
		File file = new File("C:\\work\\勤務表(2021)_V000000001-testNopass.xlsx");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表(2021)_V000000001-testNopass.xlsx");
		mockMultipartHttpServletRequest.addPart(part);
		mockMultipartHttpServletRequest.setParameter("month", "12");
		mockMultipartHttpServletRequest.setParameter("days", "31");
		mockMultipartHttpServletRequest.setParameter("password", "vaile2021");

		// 実行クラスがservletのときはおまじないとして記述
		importExcelAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importExcelAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}

}
