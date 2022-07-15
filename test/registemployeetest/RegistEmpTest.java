package registemployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.registEmployee.RegistEmpAction;
import jp.co.vaile.nerva.registEmployee.RegistEmpPageDTO;
import jp.co.vaile.nerva.registEmployee.RegistSkillInfoPageDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;

public class RegistEmpTest {
	RegistEmpAction registEmpAction =new RegistEmpAction();

	@Test
	public void testRegistEmp()throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		//全項目が正常に入力されている場合、従業員登録を行い、正常に処理が終了すること。
		 testSuccessPatternAllInput();
		 //スキル情報が未入力の場合、従業員登録を行い、正常に処理が終了すること。
		 testSuccessPatternNotSkillInfo();
	 	//全てのスキル情報を選択している場合、従業員登録を行い、正常に処理が終了すること。
		 testSuccessPatternAllSkillInput();
	}

	@Test
	// 全項目が正常に入力されている場合、従業員登録を行い、正常に処理が終了すること。
	public void testSuccessPatternAllInput() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット

		RegistEmpPageDTO registEmpPageDTO =new RegistEmpPageDTO(
				"V502020001",
				"従業員名UT",
				"男",
				"1996-11-11",
				"CP00000001",
				"事業所UT",
				"D000000001",
				"P000000001",
				"340-0202",
				"中目黒",
				"090-0000-0000", "hoge@hoge.co.jp");

		String[] skillType = {"S000000001","S000000002"};
		String[] skillDetail = {"基本情報技術者","Java"};
		String[] experienceYears = {null,"2年"};
		String[] acquisitionYearMonth = {"2018-10",null};


		RegistSkillInfoPageDTO registSkillInfoPageDTO = new RegistSkillInfoPageDTO(skillType, skillDetail, experienceYears, acquisitionYearMonth);

		httpSession.setAttribute("registEmpPageDTO", registEmpPageDTO);
		httpSession.setAttribute("registSkillInfoPageDTO", registSkillInfoPageDTO);

		String adminUser = "adminUser";
		String companyId = "CP00000001";

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"), is("V502020001"));
	}

	@Test
	// スキル情報が未入力の場合、従業員登録を行い、正常に処理が終了すること。
	public void testSuccessPatternNotSkillInfo() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット

		RegistEmpPageDTO registEmpPageDTO =new RegistEmpPageDTO(
				"V502020002",
				"従業員名UT",
				"男",
				"1996-11-11",
				"CP00000001",
				"事業所UT",
				"D000000001",
				"P000000001",
				"340-0202",
				"中目黒",
				"090-0000-0000", "hoge@hoge.co.jp");

		RegistSkillInfoPageDTO registSkillInfoPageDTO = new RegistSkillInfoPageDTO(null, null, null, null);

		httpSession.setAttribute("registEmpPageDTO", registEmpPageDTO);
		httpSession.setAttribute("registSkillInfoPageDTO", registSkillInfoPageDTO);

		String adminUser = "adminUser";
		String companyId = "CP00000001";

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"), is("V502020002"));
	}

	@Test
	// 全てのスキル情報を選択している場合、従業員登録を行い、正常に処理が終了すること。
	public void testSuccessPatternAllSkillInput() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット

		RegistEmpPageDTO registEmpPageDTO =new RegistEmpPageDTO(
				"V502020003",
				"従業員名UT",
				"男",
				"1996-11-11",
				"CP00000001",
				"事業所UT",
				"D000000001",
				"P000000001",
				"340-0202",
				"中目黒",
				"090-0000-0000", "hoge@hoge.co.jp");

		String[] skillType = {"S000000001","S000000002","S000000003","S000000004"};
		String[] skillDetail = {"基本情報技術者","Java","MySQL","Excel"};
		String[] experienceYears = {null,"2年","2年","2年"};
		String[] acquisitionYearMonth = {"2018-10",null,null,null};


		RegistSkillInfoPageDTO registSkillInfoPageDTO = new RegistSkillInfoPageDTO(skillType, skillDetail, experienceYears, acquisitionYearMonth);

		httpSession.setAttribute("registEmpPageDTO", registEmpPageDTO);
		httpSession.setAttribute("registSkillInfoPageDTO", registSkillInfoPageDTO);

		String adminUser = "adminUser";
		String companyId = "CP00000001";

		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpAction.doPost(httpRequest, httpResponse);
		
		// 実行結果を確認
		assertThat(httpRequest.getAttribute("employeeId"), is("V502020003"));
	}

}
