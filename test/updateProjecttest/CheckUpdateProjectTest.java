package updateProjecttest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Ignore;
import org.junit.Test;

import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;
import jp.co.vaile.nerva.updateProject.CheckUpdateProjectAction;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class CheckUpdateProjectTest {

	// 案件情報更新画面の更新ボタン押下後、更新処理を呼び出し案件詳細情報画面に遷移する
	CheckUpdateProjectAction checkUpdateProjectAction = new CheckUpdateProjectAction();

	@Test
	@Ignore
	public void allcheckUpdateProjectTest() throws ServletException, IOException {

		checkUpdateProjectTest1();
		checkUpdateProjectTest2();
		checkUpdateProjectTest3();
		checkUpdateProjectTest4();
		checkUpdateProjectTest5();
		checkUpdateProjectTest6();
		checkUpdateProjectTest7();
		checkUpdateProjectTest8();
		//checkUpdateProjectTest9();
		//checkUpdateProjectTest10();
		checkUpdateProjectTest11();
		checkUpdateProjectTest12();
		checkUpdateProjectTest13();
		checkUpdateProjectTest14();
		checkUpdateProjectTest15();
		checkUpdateProjectTest16();
		checkUpdateProjectTest17();
		checkUpdateProjectTest18();
		checkUpdateProjectTest19();
		checkUpdateProjectTest20();
		checkUpdateProjectTest21();
		checkUpdateProjectTest22();
		checkUpdateProjectTest23();
		checkUpdateProjectTest24();
		checkUpdateProjectTest25();
		//checkUpdateProjectTest26();
		//checkUpdateProjectTest27();
		checkUpdateProjectTest28();
		checkUpdateProjectTest29();
		//checkUpdateProjectTest30();
		checkUpdateProjectTest31();
		checkUpdateProjectTest32();
		//checkUpdateProjectTest33();
		//checkUpdateProjectTest34();
		checkUpdateProjectTest35();
		checkUpdateProjectTest36();
		checkUpdateProjectTest37();
		checkUpdateProjectTest38();
		checkUpdateProjectTest39();
		checkUpdateProjectTest40();
		checkUpdateProjectTest41();
		checkUpdateProjectTest42();
		checkUpdateProjectTest43();
		checkUpdateProjectTest44();
		checkUpdateProjectTest45();
		checkUpdateProjectTest46();
	}

	@Test
	@Ignore
	// 正常系（既参加チームなし追加チームなし）
	public void checkUpdateProjectTest1() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（案件開始日チェック・空）
	public void checkUpdateProjectTest2() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（案件開始日チェック・フォーマット）
	public void checkUpdateProjectTest3() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-100");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（案件完了日チェック・フォーマット）
	public void checkUpdateProjectTest4() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-100");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（既参加チームあり追加チームなし・配属完了日あり）
	public void checkUpdateProjectTest5() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2020-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7125);
		projectDetailPageDTO.setProjectId("P0000002");
		projectDetailPageDTO.setProjectName("テスト案件２");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7109);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（既参加チームあり追加チームなし・配属完了日なし）
	public void checkUpdateProjectTest6() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7126);
		projectDetailPageDTO.setProjectId("P0000003");
		projectDetailPageDTO.setProjectName("テスト案件３");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7110);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（配属済みチームの配属開始日チェック・空）
	public void checkUpdateProjectTest7() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7127);
		projectDetailPageDTO.setProjectId("P0000004");
		projectDetailPageDTO.setProjectName("テスト案件４");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7111);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（配属済みチームの配属開始日チェック・フォーマット）
	public void checkUpdateProjectTest8() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7128);
		projectDetailPageDTO.setProjectId("P0000005");
		projectDetailPageDTO.setProjectName("テスト案件５");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7112);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-110");
		httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（案件開始日と配属済みチームの配属開始日の日付チェック・案件開始日＞配属済みチームの配属開始日）
	public void checkUpdateProjectTest9() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-11");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7129);
		projectDetailPageDTO.setProjectId("P0000006");
		projectDetailPageDTO.setProjectName("テスト案件６");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7113);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（案件開始日と配属済みチームの配属開始日の日付チェック・配属済みチームの配属開始日＞案件開始日）
	public void checkUpdateProjectTest10() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-09");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7130);
		projectDetailPageDTO.setProjectId("P0000007");
		projectDetailPageDTO.setProjectName("テスト案件７");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7114);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（配属済みチームの配属完了日の入力チェック・フォーマット）
	public void checkUpdateProjectTest11() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7131);
		projectDetailPageDTO.setProjectId("P0000008");
		projectDetailPageDTO.setProjectName("テスト案件８");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7115);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-110");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（配属済みチームの配属完了日の入力チェック・チームの従業員の所属完了日＞配属済みチームの配属完了日）
	public void checkUpdateProjectTest12() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7132);
		projectDetailPageDTO.setProjectId("P0000009");
		projectDetailPageDTO.setProjectName("テスト案件９");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7116);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（配属済みチームの配属完了日の入力チェック・配属済みチームの配属完了日＞チームの従業員の所属完了日）
	public void checkUpdateProjectTest13() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7133);
		projectDetailPageDTO.setProjectId("P0000010");
		projectDetailPageDTO.setProjectName("テスト案件１０");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7117);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（配属済みのチームの配属開始日と配属完了日の日付チェック・チームの配属開始日＞チームの配属完了日）
	public void checkUpdateProjectTest14() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7134);
		projectDetailPageDTO.setProjectId("P0000011");
		projectDetailPageDTO.setProjectName("テスト案件１１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7118);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-11");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（配属済みのチームの配属開始日と配属完了日の日付チェック・チームの配属完了日＞配属開始日）
	public void checkUpdateProjectTest15() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7135);
		projectDetailPageDTO.setProjectId("P0000012");
		projectDetailPageDTO.setProjectName("テスト案件１２");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7119);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-11");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（配属済みのチームの配属開始日と案件完了日の日付チェック・配属済みのチームの配属開始日＞案件完了日）
	public void checkUpdateProjectTest16() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7136);
		projectDetailPageDTO.setProjectId("P0000013");
		projectDetailPageDTO.setProjectName("テスト案件１３");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7120);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2021-10-11");
		httpRequest.setParameter("json[assignCompleteDate][]", "");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（配属済みのチームの配属開始日と案件完了日の日付チェック・案件完了日＞配属済みのチームの配属開始日）
	public void checkUpdateProjectTest17() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7137);
		projectDetailPageDTO.setProjectId("P0000014");
		projectDetailPageDTO.setProjectName("テスト案件１４");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7113);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2021-10-09");
		httpRequest.setParameter("json[assignCompleteDate][]", "");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（配属済みのチームの配属完了日と案件完了日の日付チェック・配属済みのチームの配属完了日＞案件完了日）
	public void checkUpdateProjectTest18() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7138);
		projectDetailPageDTO.setProjectId("P0000015");
		projectDetailPageDTO.setProjectName("テスト案件１５");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7122);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-11");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（配属済みのチームの配属完了日と案件完了日の日付チェック・案件完了日＞配属済みのチームの配属完了日）
	public void checkUpdateProjectTest19() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7139);
		projectDetailPageDTO.setProjectId("P0000016");
		projectDetailPageDTO.setProjectName("テスト案件１６");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7123);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-09");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（既参加チームなし追加チームあり・配属完了日なし）
	public void checkUpdateProjectTest20() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7140);
		projectDetailPageDTO.setProjectId("P0000017");
		projectDetailPageDTO.setProjectName("テスト案件１７");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7124);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0016");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（同じチーム追加チェック・同じチームを２つ追加）
	public void checkUpdateProjectTest21() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7141);
		projectDetailPageDTO.setProjectId("P0000018");
		projectDetailPageDTO.setProjectName("テスト案件１８");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7125);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0017");
		httpRequest.setParameter("json[addTeamId][]", "T0000V0017");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームが重複していないかチェック・既存チームを追加）
	public void checkUpdateProjectTest22() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7142);
		projectDetailPageDTO.setProjectId("P0000019");
		projectDetailPageDTO.setProjectName("テスト案件１９");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7126);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-10");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0018");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 異常系（追加したチームの存在チェック・存在しないチームを追加）
	public void checkUpdateProjectTest23() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7143);
		projectDetailPageDTO.setProjectId("P0000020");
		projectDetailPageDTO.setProjectName("テスト案件２０");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7127);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0099");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの配属開始日の入力チェック・空）
	public void checkUpdateProjectTest24() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7144);
		projectDetailPageDTO.setProjectId("P0000021");
		projectDetailPageDTO.setProjectName("テスト案件２１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7128);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0020");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの配属開始日の入力チェック・フォーマット）
	public void checkUpdateProjectTest25() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7145);
		projectDetailPageDTO.setProjectId("P0000022");
		projectDetailPageDTO.setProjectName("テスト案件２２");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7129);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0021");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-100");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの従業員の所属開始日とチームの配属開始日の日付チェック・チームの配属開始日＞従業員の所属開始日）
	public void checkUpdateProjectTest26() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7146);
		projectDetailPageDTO.setProjectId("P0000023");
		projectDetailPageDTO.setProjectName("テスト案件２３");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7130);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0022");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-11");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（追加したチームの従業員の所属開始日とチームの配属開始日の日付チェック）
	public void checkUpdateProjectTest27() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7147);
		projectDetailPageDTO.setProjectId("P0000024");
		projectDetailPageDTO.setProjectName("テスト案件２４");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7131);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0023");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（配属済みチームなし新規追加チームあり・配属完了日あり）
	public void checkUpdateProjectTest28() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2020-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7148);
		projectDetailPageDTO.setProjectId("P0000025");
		projectDetailPageDTO.setProjectName("テスト案件２５");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7132);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0024");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2020-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの配属完了日の入力チェック・フォーマット）
	public void checkUpdateProjectTest29() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-11");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7149);
		projectDetailPageDTO.setProjectId("P0000026");
		projectDetailPageDTO.setProjectName("テスト案件２６");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7133);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0025");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-110");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの従業員の所属完了日とチームの配属完了日の日付チェック・フォーマット）
	public void checkUpdateProjectTest30() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7150);
		projectDetailPageDTO.setProjectId("P0000027");
		projectDetailPageDTO.setProjectName("テスト案件２７");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7134);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0026");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-100");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの従業員の所属完了日とチームの配属完了日の日付チェック・従業員の所属完了日＞チームの配属完了日）
	public void checkUpdateProjectTest31() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7151);
		projectDetailPageDTO.setProjectId("P0000028");
		projectDetailPageDTO.setProjectName("テスト案件２８");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7135);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0027");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（追加したチームの従業員の所属完了日とチームの配属完了日の日付チェック・チームの配属完了日＞従業員の所属完了日）
	public void checkUpdateProjectTest32() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7152);
		projectDetailPageDTO.setProjectId("P0000029");
		projectDetailPageDTO.setProjectName("テスト案件２９");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7136);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0028");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（案件開始日と追加した配属開始日の日付チェック・案件開始日＞追加されたチームの配属開始日）
	public void checkUpdateProjectTest33() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-11");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7153);
		projectDetailPageDTO.setProjectId("P0000030");
		projectDetailPageDTO.setProjectName("テスト案件３０");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7137);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0029");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（案件開始日と追加した配属開始日の日付チェック・案件開始日＞追加されたチームの配属開始日）
	public void checkUpdateProjectTest34() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7154);
		projectDetailPageDTO.setProjectId("P0000031");
		projectDetailPageDTO.setProjectName("テスト案件３１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7138);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0030");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-09");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの配属開始日と配属完了日の日付チェック・追加されたチームの配属開始日＞追加されたチームの配属完了日）
	public void checkUpdateProjectTest35() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7155);
		projectDetailPageDTO.setProjectId("P0000032");
		projectDetailPageDTO.setProjectName("テスト案件３２");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7139);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0031");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-11");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2020-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（追加したチームの配属開始日と配属完了日の日付チェック・追加されたチームの配属完了日＞追加されたチームの配属開始日）
	public void checkUpdateProjectTest36() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7156);
		projectDetailPageDTO.setProjectId("P0000033");
		projectDetailPageDTO.setProjectName("テスト案件３３");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7140);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0032");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2020-10-11");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの配属完了日と案件完了日の日付チェック・追加されたチームの配属完了日＞案件完了日）
	public void checkUpdateProjectTest37() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7157);
		projectDetailPageDTO.setProjectId("P0000034");
		projectDetailPageDTO.setProjectName("テスト案件３４");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7141);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0033");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2021-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-11");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 正常系（追加したチームの配属完了日と案件完了日の日付チェック・案件完了日＞追加されたチームの配属完了日）
	public void checkUpdateProjectTest38() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7158);
		projectDetailPageDTO.setProjectId("P0000035");
		projectDetailPageDTO.setProjectName("テスト案件３５");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7142);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0034");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-09");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore

	// 異常系（追加したチームの配属開始日と案件完了日の日付チェック・追加されたチームの配属開始日＞案件完了日）
	public void checkUpdateProjectTest39() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7159);
		projectDetailPageDTO.setProjectId("P0000036");
		projectDetailPageDTO.setProjectName("テスト案件３６");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7143);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0035");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2021-10-11");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（追加したチームの配属開始日と案件完了日の日付チェック・案件完了日＞追加されたチームの配属開始日）
	public void checkUpdateProjectTest40() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7160);
		projectDetailPageDTO.setProjectId("P0000037");
		projectDetailPageDTO.setProjectName("テスト案件３７");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7144);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		httpRequest.setParameter("json[addTeamId][]", "T0000V0036");
		httpRequest.setParameter("json[addTeamAssignStartDate][]", "2021-10-09");
		httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系正常系（配属済みチームの配属完了日の入力チェック・配属済みチームの配属完了日=チームの従業員の所属完了日）
	public void checkUpdateProjectTest41() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-30");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7177);
		projectDetailPageDTO.setProjectId("P0000038");
		projectDetailPageDTO.setProjectName("テスト案件３８");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7145);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-30");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", "2021-10-09");
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}
	
	@Test
	@Ignore
	// 異常系（案件開始日と案件完了日の日付チェック・案件開始日＞案件完了日）
	public void checkUpdateProjectTest42() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-11");
		httpRequest.setParameter("json[projectCompleteDate]", "2020-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件開始日と案件完了日の日付チェック・案件完了日＞案件開始日）
	public void checkUpdateProjectTest43() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-11");
		httpRequest.setParameter("json[projectCompleteDate]", "2020-10-12");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（案件開始日と案件完了日の日付チェック・案件完了日=案件開始日）
	public void checkUpdateProjectTest44() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2020-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamId][]", null);
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// （空行追加・チーム追加なし）
	public void checkUpdateProjectTest45() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7124);
		projectDetailPageDTO.setProjectId("P0000001");
		projectDetailPageDTO.setProjectName("テスト案件１");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7108);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/10");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		// httpRequest.setParameter("json[assignStartDate][]", "2020-10-01");
		// httpRequest.setParameter("json[assignCompleteDate][]", "2021-10-01");

		// 追加チーム情報
		 httpRequest.setParameter("json[addTeamId][]", "");
		 httpRequest.setParameter("json[addTeamId][]", "");
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", null);
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", null);

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}

	@Test
	@Ignore
	// 正常系（配属済みチームの配属完了日の入力チェック・配属済みチームの配属完了日=チームの従業員の所属完了日）
	public void checkUpdateProjectTest46() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		// 案件情報
		httpRequest.setParameter("json[projectStartDate]", "2020-10-10");
		httpRequest.setParameter("json[projectCompleteDate]", "2021-10-10");

		ProjectDetailPageDTO projectDetailPageDTO = new ProjectDetailPageDTO();
		projectDetailPageDTO.setProjectInfoId(7197);
		projectDetailPageDTO.setProjectId("P0000039");
		projectDetailPageDTO.setProjectName("テスト案件３９");
		projectDetailPageDTO.setResponsibleId("adminUser");
		projectDetailPageDTO.setResponsibleName("権限　太郎");
		projectDetailPageDTO.setContractorId("CP00000001");
		projectDetailPageDTO.setClientId(7154);
		projectDetailPageDTO.setClientName("テスト株式会社");
		projectDetailPageDTO.setIndustryId("I000000004");
		projectDetailPageDTO.setIndustryName("情報通信");
		projectDetailPageDTO.setProjectStartDate("2020/10/10");
		projectDetailPageDTO.setProjectCompleteDate("2021/10/11");

		httpSession.setAttribute("updatePjtPageDto", projectDetailPageDTO);

		// 既参加チーム情報
		httpRequest.setParameter("json[assignStartDate][]", "2020-10-10");
		httpRequest.setParameter("json[assignCompleteDate][]", "2020-10-10");

		// 追加チーム情報
		// httpRequest.setParameter("json[addTeamAssignStartDate][]", "2021-10-09");
		// httpRequest.setParameter("json[addTeamAssignCompleteDate][]", "2021-10-10");

		// ログインユーザ情報
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		checkUpdateProjectAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		checkUpdateProjectAction.doPost(httpRequest, httpResponse);
	}
}
