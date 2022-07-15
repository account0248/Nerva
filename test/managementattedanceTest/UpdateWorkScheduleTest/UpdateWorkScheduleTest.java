package managementattedanceTest.UpdateWorkScheduleTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.managementattedance.updateWorkSchedule.UpdateWorkScheduleAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

class UpdateWorkScheduleTest {

	UpdateWorkScheduleAction updateWorkScheduleAction = new UpdateWorkScheduleAction();

	private String getString(String[] values) {

		String valueStr = "";
		String bracketsLeft = "[";
		String bracketsRight = "]";
		valueStr = bracketsLeft;

		for (int i = 0; i < values.length; i++) {
			if (i > 0)
				valueStr = valueStr + ",";
			valueStr = valueStr + "\"" + values[i] + "\"";
		}

		valueStr = valueStr + bracketsRight;

		return valueStr;
	}

	@Test
	@DisplayName("正常動作(正常に登録)")
	public void insertPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "09:00", "", "", "", "13:00", "09:00", "", "11:00", "09:00", "09:00", "09:00",
				"11:00", "15:00", "", "10:00", "", "", "09:00", "23:00", "09:00", "09:00", "09:00", "09:00", "09:00",
				"09:00", "", "", "", "" };
		
		String[] end = { "18:00", "04:00", "", "", "", "19:00", "12:00", "", "19:00", "16:00", "23:00", "19:00",
				"16:00", "04:00", "", "14:00", "", "", "14:00", "05:00", "09:00", "19:00", "19:00", "19:00", "23:00",
				"19:00", "", "", "", "" };
		
		String[] rest = { "01:00", "01:00", "", "", "", "00:00", "00:00", "", "01:00", "01:00", "01:00", "01:00",
				"01:00", "02:00", "", "01:00", "", "", "01:00", "00:00", "04:00", "01:00", "01:00", "01:00", "01:00",
				"01:00", "", "", "", "" };
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "13:00", "", "", "", "", "" };
		
		String[] holiday = { "", "", "H000000001", "", "", "H000000002", "H000000002", "H000000003", "H000000004",
				"H000000004", "", "", "H000000004", "", "H000000009", "H000000008", "H000000007", "", "", "", "", "",
				"", "", "", "", "H000000005", "H000000005", "", "H000000006" };
		
		String[] holidayDays = { "", "", "1.0", "", "", "0.5", "0.5", "1.0", "", "", "", "", "", "", "", "0.5", "1.0",
				"", "", "", "", "", "", "", "", "", "1.0", "1.0", "", "1.0" };
		
		String[] holidayTime = { "", "", "08:00", "", "", "03:00", "05:00", "08:00", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "08:00", "04:00", "", "08:00" };
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "02:00", "02:00", "", "", "04:00", "", "", "01:00", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "" };
		
		String[] remarks = { "", "", "", "", "", "", "", "", "腹痛により遅刻", "体調不良により早退", "", "", "遅刻・早退", "シフト(深夜休憩１H)", "",
				"台風に伴う早退指令", "台風に伴う自宅待機", "", "", "シフト勤務", "徹夜(深夜休憩3h)", "", "電故", "", "", "", "3/20分振休", "4/19分振休", "",
				"4/25分振休" };

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "04");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作(正常に更新)")
	public void updatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "09:00", "", "", "", "13:00", "09:00", "", "11:00", "09:00", "09:00", "09:00",
				"11:00", "15:00", "", "10:00", "", "", "09:00", "23:00", "09:00", "09:00", "09:00", "09:00", "09:00",
				"09:00", "", "", "", "" };
		
		String[] end = { "18:00", "04:00", "", "", "", "19:00", "12:00", "", "19:00", "16:00", "23:00", "19:00",
				"16:00", "04:00", "", "14:00", "", "", "14:00", "05:00", "09:00", "19:00", "19:00", "19:00", "23:00",
				"19:00", "", "", "", "" };
		
		String[] rest = { "01:00", "01:00", "", "", "", "00:00", "00:00", "", "01:00", "01:00", "01:00", "01:00",
				"01:00", "02:00", "", "01:00", "", "", "01:00", "00:00", "04:00", "01:00", "01:00", "01:00", "01:00",
				"01:00", "", "", "", "" };
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "13:00", "", "", "", "", "" };
		
		String[] holiday = { "", "", "H000000001", "", "", "H000000002", "H000000002", "H000000003", "H000000004",
				"H000000004", "", "", "H000000004", "", "H000000009", "H000000008", "H000000007", "", "", "", "", "",
				"", "", "", "", "H000000005", "H000000005", "", "H000000006" };
		
		String[] holidayDays = { "", "", "1.0", "", "", "0.5", "0.5", "1.0", "", "", "", "", "", "", "", "0.5", "1.0",
				"", "", "", "", "", "", "", "", "", "1.0", "1.0", "", "1.0" };
		
		String[] holidayTime = { "", "", "08:00", "", "", "03:00", "05:00", "08:00", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "08:00", "04:00", "", "08:00" };
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "02:00", "02:00", "", "", "04:00", "", "", "01:00", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "" };
		
		String[] remarks = { "", "", "", "", "", "", "", "", "腹痛により遅刻", "体調不良により早退", "", "", "遅刻・早退", "シフト(深夜休憩１H)", "",
				"台風に伴う早退指令", "台風に伴う自宅待機", "", "", "シフト勤務", "徹夜(深夜休憩3h)", "", "電故", "", "", "", "3/20分振休", "4/19分振休", "",
				"4/25分振休" };

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "04");
		httpRequest.setParameter("json[employeeId]", "V000000002");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}
	

	@Test
	@DisplayName("正常動作(正常に登録「2月」)")
	public void shortMonthInsertPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", ""};
		
		String[] end = { "19:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" };
		
		String[] rest = { "01:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", ""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", ""};
		
		String[] holiday = { "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "02");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}
	

	@Test
	@DisplayName("正常動作(正常に更新「2月」)")
	public void shortMonthUpdatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", ""};
		
		String[] end = { "19:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" };
		
		String[] rest = { "01:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", ""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", ""};
		
		String[] holiday = { "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "02");
		httpRequest.setParameter("json[employeeId]", "V000000002");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}


	@Test
	@DisplayName("正常動作(正常に登録「7月」)")
	public void longMonthInsertPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] end = { "19:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","",""};
		
		String[] rest = { "01:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","","", ""};
		
		String[] holiday = { "", "", "", "","","","", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "07");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作(正常に更新「7月」)")
	public void longMonthUpdatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] end = { "19:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","",""};
		
		String[] rest = { "01:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","","", ""};
		
		String[] holiday = { "", "", "", "","","","", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "07");
		httpRequest.setParameter("json[employeeId]", "V000000002");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("正常動作(最初と最後のみ登録「6月」)")
	public void firstAndLastInsertPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","09:00"};
		
		String[] end = { "19:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","19:00"};
		
		String[] rest = { "01:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","01:00"};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","", ""};
		
		String[] holiday = { "", "", "", "","","","", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				 "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "06");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("正常動作(最初と最後のみ入力し更新「6月」)")
	public void firstAndLastUpdatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "09:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","09:00"};
		
		String[] end = { "19:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","19:00"};
		
		String[] rest = { "01:00", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","01:00"};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","", ""};
		
		String[] holiday = { "", "", "", "","","","", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				 "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "06");
		httpRequest.setParameter("json[employeeId]", "V000000002");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}
	
	

	@Test
	@DisplayName("正常動作(有給期間等すべて選択し登録「8月」)")
	public void holidayAllInsertPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] end = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","",""};
		
		String[] rest = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","","", ""};
		
		String[] holiday = { "H000000001", "H000000002", "H000000003", "H000000004","H000000005","H000000006","H000000007", "H000000008", "H000000009", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "1.0", "0.5", "1.0", "", "1.0", "1.0","1.0","0.5","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "08:00", "03:00", "08:00", "", "05:00", "05:00", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "01:00", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "08");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}


	@Test
	@DisplayName("正常動作(有給期間等すべて選択し更新「8月」)")
	public void holidayAllUpdatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] end = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","",""};
		
		String[] rest = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","","", ""};
		
		String[] holiday = { "H000000001", "H000000002", "H000000003", "H000000004","H000000005","H000000006","H000000007", "H000000008", "H000000009", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "1.0", "0.5", "1.0", "", "1.0", "1.0","1.0","0.5","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "08:00", "03:00", "08:00", "", "05:00", "05:00", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "01:00", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "08");
		httpRequest.setParameter("json[employeeId]", "V000000002");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	
	@Test
	@DisplayName("エラー動作(開始未入力)")
	public void noInputStartTimePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "06");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", "[\"\"]");
		httpRequest.setParameter("json[end]", "[\"18:00:00\"]");
		httpRequest.setParameter("json[rest]", "[\"01:00:00\"]");
		httpRequest.setParameter("json[holidayWork]", "[\"08:00:00\"]");
		httpRequest.setParameter("json[holiday]", "[\"\"]");
		httpRequest.setParameter("json[holidayDays]", "[\"\"]");
		httpRequest.setParameter("json[holidayTime]", "[\"\"]");
		httpRequest.setParameter("json[lateTime]", "[\"\"]");
		httpRequest.setParameter("json[remarks]", "[\"\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}


	@Test
	@DisplayName("正常動作(すべて未入力で正常に登録「5月」)")
	public void noValueInsertPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] end = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","",""};
		
		String[] rest = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","","", ""};
		
		String[] holiday = { "", "", "", "","","","", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "05");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}
	

	@Test
	@DisplayName("正常動作(すべて未入力で正常に更新「5月」)")
	public void noValueUpdatePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		//入力内容
		String[] start = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] end = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "" ,"","",""};
		
		String[] rest = { "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "","","",""};
		
		String[] holidayWork = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "","","","", ""};
		
		String[] holiday = { "", "", "", "","","","", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "" };
		
		String[] holidayDays = { "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] holidayTime = { "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] lateTime = { "", "", "", "", "", "", "", "", "", "", "","","","", "", "", "", "", "", "",
				"", "", "", "", "", "", "", "", "", "", ""};
		
		String[] remarks = { "", "", "", "", "", "", "", "", "", "", "", "", "", "","","","", "",
				"", "", "", "", "", "", "", "", "", "", "", "", ""};

		//入力内容をString型に直す
		String startStr = getString(start);
		String endStr = getString(end);
		String restStr = getString(rest);
		String holidayWorkStr = getString(holidayWork);
		String hoidayStr = getString(holiday);
		String holidayDaysStr = getString(holidayDays);
		String holidayTimeStr = getString(holidayTime);
		String lateTimeStr = getString(lateTime);
		String remarksStr = getString(remarks);
		
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "05");
		httpRequest.setParameter("json[employeeId]", "V000000002");
		httpRequest.setParameter("json[start]", startStr);
		httpRequest.setParameter("json[end]", endStr);
		httpRequest.setParameter("json[rest]", restStr);
		httpRequest.setParameter("json[holidayWork]", holidayWorkStr);
		httpRequest.setParameter("json[holiday]", hoidayStr);
		httpRequest.setParameter("json[holidayDays]", holidayDaysStr);
		httpRequest.setParameter("json[holidayTime]", holidayTimeStr);
		httpRequest.setParameter("json[lateTime]", lateTimeStr);
		httpRequest.setParameter("json[remarks]", remarksStr);

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@DisplayName("エラー動作(終了未入力)")
	public void noInputEndTimePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "06");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", "[\"09:30:00\"]");
		httpRequest.setParameter("json[end]", "[\"\"]");
		httpRequest.setParameter("json[rest]", "[\"01:00:00\"]");
		httpRequest.setParameter("json[holidayWork]", "[\"08:00:00\"]");
		httpRequest.setParameter("json[holiday]", "[\"\"]");
		httpRequest.setParameter("json[holidayDays]", "[\"\"]");
		httpRequest.setParameter("json[holidayTime]", "[\"\"]");
		httpRequest.setParameter("json[lateTime]", "[\"\"]");
		httpRequest.setParameter("json[remarks]", "[\"\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラー動作(休憩未入力)")
	public void noInputRestTimePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "06");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", "[\"09:30:00\"]");
		httpRequest.setParameter("json[end]", "[\"18:00:00\"]");
		httpRequest.setParameter("json[rest]", "[\"\"]");
		httpRequest.setParameter("json[holidayWork]", "[\"08:00:00\"]");
		httpRequest.setParameter("json[holiday]", "[\"\"]");
		httpRequest.setParameter("json[holidayDays]", "[\"\"]");
		httpRequest.setParameter("json[holidayTime]", "[\"\"]");
		httpRequest.setParameter("json[lateTime]", "[\"\"]");
		httpRequest.setParameter("json[remarks]", "[\"\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラー動作(休暇時間未入力)")
	public void noInputHolidayTimePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "06");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", "[\"\"]");
		httpRequest.setParameter("json[end]", "[\"\"]");
		httpRequest.setParameter("json[rest]", "[\"\"]");
		httpRequest.setParameter("json[holidayWork]", "[\"\"]");
		httpRequest.setParameter("json[holiday]", "[\"H000000002\"]");
		httpRequest.setParameter("json[holidayDays]", "[\"0.5\"]");
		httpRequest.setParameter("json[holidayTime]", "[\"\"]");
		httpRequest.setParameter("json[lateTime]", "[\"\"]");
		httpRequest.setParameter("json[remarks]", "[\"\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラー動作(休暇時間未入力)")
	public void noInputLateTimePattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "06");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", "[\"\"]");
		httpRequest.setParameter("json[end]", "[\"\"]");
		httpRequest.setParameter("json[rest]", "[\"\"]");
		httpRequest.setParameter("json[holidayWork]", "[\"\"]");
		httpRequest.setParameter("json[holiday]", "[\"H000000004\"]");
		httpRequest.setParameter("json[holidayDays]", "[\"\"]");
		httpRequest.setParameter("json[holidayTime]", "[\"\"]");
		httpRequest.setParameter("json[lateTime]", "[\"\"]");
		httpRequest.setParameter("json[remarks]", "[\"\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("正常動作(備考:正常に登録)")
	public void remarksNormalPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "09");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", "[\"\"]");
		httpRequest.setParameter("json[end]", "[\"\"]");
		httpRequest.setParameter("json[rest]", "[\"\"]");
		httpRequest.setParameter("json[holidayWork]", "[\"\"]");
		httpRequest.setParameter("json[holiday]", "[\"\"]");
		httpRequest.setParameter("json[holidayDays]", "[\"\"]");
		httpRequest.setParameter("json[holidayTime]", "[\"\"]");
		httpRequest.setParameter("json[lateTime]", "[\"\"]");
		httpRequest.setParameter("json[remarks]", "[\"12345678911234567892\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@DisplayName("エラー動作(備考文字数超過)")
	public void remarksErrorPattern() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpResponse httpResponse = new MockHttpResponse();
		MockHttpSession httpSession = new MockHttpSession();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[year]", "2021");
		httpRequest.setParameter("json[month]", "09");
		httpRequest.setParameter("json[employeeId]", "V000000001");
		httpRequest.setParameter("json[start]", "[\"\"]");
		httpRequest.setParameter("json[end]", "[\"\"]");
		httpRequest.setParameter("json[rest]", "[\"\"]");
		httpRequest.setParameter("json[holidayWork]", "[\"\"]");
		httpRequest.setParameter("json[holiday]", "[\"\"]");
		httpRequest.setParameter("json[holidayDays]", "[\"\"]");
		httpRequest.setParameter("json[holidayTime]", "[\"\"]");
		httpRequest.setParameter("json[lateTime]", "[\"\"]");
		httpRequest.setParameter("json[remarks]", "[\"123456789112345678921\"]");

		// 疑似セッションスコープに情報をセット
		String loginUserId = "admin";
		httpSession.setAttribute("userId", loginUserId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		updateWorkScheduleAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		updateWorkScheduleAction.doPost(httpRequest, httpResponse);
	}

}
