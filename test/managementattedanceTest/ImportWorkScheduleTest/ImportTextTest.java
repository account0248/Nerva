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

import jp.co.vaile.nerva.managementattedance.importWorkSchedule.ImportTextAction;
import mock.MockServletConfig;

class ImportTextTest {

	private byte[] convertFile(File file) throws IOException {
		FileInputStream inputStream = new FileInputStream(file);
		return IOUtils.toByteArray(inputStream);
	}

	@Test
	@DisplayName("正常動作")
	public void textFileImportNormalPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎一.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎一.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	

	@Test
	@DisplayName("正常動作(休暇期間等のみ入力)")
	public void textFileHolidayImportNormalPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎二.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎二.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	
	@Test
	@DisplayName("エラー動作(休暇日数「入力必須」に日数フォーマット以外の値)")
	public void requiredHolidayDaysErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎三.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎三.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(休暇日数「入力不要」に-以外の値)")
	public void noneHolidayDaysErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎四.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎四.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(休暇日数「入力不要」に休暇期間等マスタでの設定値以外の値)")
	public void provisionHolidayDaysErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎五.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎五.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(休暇時間「入力必須」に時間フォーマット以外の値)")
	public void requiredHolidayTimeErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎六.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎六.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}

	@Test
	@DisplayName("エラー動作(休暇時間「入力不要」に-以外の値)")
	public void noneHolidayTimeErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎七.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎七.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(休暇時間「入力不要」に休暇期間等マスタでの設定値以外の値)")
	public void provisionHolidayTimeErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎八.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎八.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}


	@Test
	@DisplayName("エラー動作(遅早時刻「入力必須」に時間フォーマット以外の値)")
	public void requiredLateTimeErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎九.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎九.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}

	@Test
	@DisplayName("エラー動作(遅早時刻「入力不要」に-以外の値)")
	public void noneLateTimeErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎十.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎十.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	
	@Test
	@DisplayName("エラー動作(遅早時刻「任意入力」に時間フォーマット以外の値)")
	public void anyLateTimeErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001_ベイル太郎十一.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001_ベイル太郎十一.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}
	

	@Test
	@DisplayName("エラー動作(ファイル名がフォーマット通りでない)")
	public void fileNameErrorPattern() throws IOException, ServletException {
		MockHttpServletResponse httpResponse = new MockHttpServletResponse();
		MockMultipartHttpServletRequest mockMultipartHttpServletRequest = new MockMultipartHttpServletRequest();

		ImportTextAction importTextAction = new ImportTextAction();
		File file = new File("C:\\work\\勤務表_2021_V000000001.text");
		byte[] fileByte = convertFile(file);
		MockPart part = new MockPart("file", fileByte);

		// 疑似リクエストスコープに情報をセット
		mockMultipartHttpServletRequest.setContentType("multipart/form-date");
		mockMultipartHttpServletRequest.setParameter("fileName", "勤務表_2021_V000000001.text");
		mockMultipartHttpServletRequest.addPart(part);

		// 実行クラスがservletのときはおまじないとして記述
		importTextAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		importTextAction.doPost(mockMultipartHttpServletRequest, httpResponse);

	}

}
