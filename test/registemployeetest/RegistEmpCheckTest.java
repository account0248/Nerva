package registemployeetest;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.registEmployee.RegistEmpCheckAction;
import jp.co.vaile.nerva.registEmployee.RegistEmpPageDTO;
import jp.co.vaile.nerva.registEmployee.RegistSkillInfoPageDTO;
import mock.MockHttpRequest;
import mock.MockHttpResponse;
import mock.MockHttpSession;
import mock.MockServletConfig;



public class RegistEmpCheckTest {
	RegistEmpCheckAction registEmpCheckAction = new RegistEmpCheckAction();

	/**
	 * 全パターンテスト
	 * @throws ServletException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Test
	public void allTest()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		//1.全項目が正常に入力されている場合、正常に処理が終了すること。
		testSuccessPatternAllInput();
		//2.スキル情報が未入力の場合、正常に処理が終了すること。(株式会社V)
		testSuccessPatternNotSkillInfo();
		//3.全項目が未入力の場合、エラーメッセージを出力すること。
		testFailurePatternNotInput();
		//4.指定形式でない場合、エラーメッセージを出力すること。
		testFailurePatternFormat();
		//5.指定文字数以上入力された場合、エラーメッセージを出力すること。
		testFailurePatternLength();
		//6.従業員IDが形式通りでない場合、エラーメッセージを出力すること。
		testFailurePatternEmployeeIdFormat();
		//7.従業員IDと所属会社が一致しない場合、エラーメッセージを出力すること。（株式会社V）
		testFailurePatternEmployeeWithCompanyMatchV();
		//8.従業員IDと所属会社が一致しない場合、エラーメッセージを出力すること。（K株式会社）
		testFailurePatternEmployeeWithCompanyMatchK();
		//9.従業員IDと所属会社が一致しない場合、エラーメッセージを出力すること。（株式会社O）
		testFailurePatternEmployeeWithCompanyMatchO();
		//10.妥当性エラーの場合、エラーメッセージを出力すること。
		testFailurePatternValidation();
		//11.所属会社がK株式会社の場合、正常に処理が終了すること。
		testSuccessPatternCompanyK();
		//12.所属会社が株式会社Oの場合、正常に処理が終了すること。
		testSuccessPatternCompanyO();
		//13.生年月日がYYYY-MM-DDの構成でない場合、エラーメッセージを出力すること。
		testFailurePatternBirthDateFormat();
		//14.スキル内容が未入力の場合、エラーメッセージを出力すること。
		testFailurePatternNotInputSkillDetail();
		//15.経験年数が指定文字数以上入力された場合、エラーメッセージを出力すること。
		testFailurePatternLengthExperienceYears();
		//16.取得年月が指定文字数以上入力された場合、エラーメッセージを出力すること。
		testFailurePatternNotInputAcquisitionYearMonth();
		//17.スキル種別の年数/取得日フラグが"1"の時に取得年数が入力されてた場合、エラーメッセージを出力すること。
		testFailurePatternValidityExperienceYears();
		//18.スキル種別の年数/取得日フラグが"1"の時に取得年数が入力されてた場合、エラーメッセージを出力すること。
		testFailurePatternValidityAcquisitionYearMonth();
		//19.郵便番号、メールアドレスが形式外で入力された場合、エラーメッセージを出力すること。
		testFailurePatternValidityOutOfFormat();
		//20.スキル種別の年数/取得日フラグが"0"の時に取得年月が入力されていない場合、エラーメッセージを出力すること。
		testFailurePatternNotAcquisitionYearMonth();
		//21.スキル種別の年数/取得日フラグが"1"の時に経験年数が入力されてたいない場合、エラーメッセージを出力すること。
		testFailurePatternNotExperienceYears();
	}

	/**
	 * 	全項目が正常に入力されている場合、正常に処理が終了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testSuccessPatternAllInput() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010001");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000001");
		httpRequest.setParameter("json[skillType][]", "S000000002");
		httpRequest.setParameter("json[skillDetail][]", "基本情報技術者");
		httpRequest.setParameter("json[skillDetail][]", "Java");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[experienceYears][]", "2年");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "2018-10");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
		
		//セッションから取り出した従業員情報の入力値
		RegistEmpPageDTO registEmpPageDTO = (RegistEmpPageDTO)httpSession.getAttribute("registEmpPageDTO");
		//セッションから取り出したスキル情報の入力値
		RegistSkillInfoPageDTO registSkillInfoPageDTO = (RegistSkillInfoPageDTO)httpSession.getAttribute("registSkillInfoPageDTO");
		String[] skillType = {"S000000001","S000000002"};
		String[] skillDetail = {"基本情報技術者","Java"};
		String[] experienceYears = {null,"2年"};
		String[] acquisitionYearMonth = {"2018-10",null};
		
		// 実行結果を確認
		//セッションに従業員情報、スキル情報の入力値が格納されていることで成功
		assertThat(registEmpPageDTO.getEmployeeId(),is("V502010001"));
		assertThat(registEmpPageDTO.getEmployeeName(),is("従業員名UT"));
		assertThat(registEmpPageDTO.getSex(),is("男"));
		assertThat(registEmpPageDTO.getBirthDate(),is("1996-11-11"));
		assertThat(registEmpPageDTO.getCompanyId(),is("CP00000001"));
		assertThat(registEmpPageDTO.getOffice(),is("事業所UT"));
		assertThat(registEmpPageDTO.getDepartmentId(),is("D000000001"));
		assertThat(registEmpPageDTO.getPostId(),is("P000000001"));
		assertThat(registEmpPageDTO.getPostalCode(),is("340-0202"));
		assertThat(registEmpPageDTO.getAddress(),is("中目黒"));
		assertThat(registEmpPageDTO.getPhoneNumber(),is("090-0000-0000"));
		assertThat(registEmpPageDTO.getMail(),is("hoge@hoge.co.jp"));
		for(int i = 0; i < registSkillInfoPageDTO.getSkillType().size(); i++) {
			assertThat(registSkillInfoPageDTO.getSkillType().get(i),is(skillType[i]));
			assertThat(registSkillInfoPageDTO.getSkillDetail().get(i),is(skillDetail[i]));
			assertThat(registSkillInfoPageDTO.getExperienceYears().get(i),is(experienceYears[i]));
			assertThat(registSkillInfoPageDTO.getAcquisitionYearMonth().get(i),is(acquisitionYearMonth[i]));
		}
	}

	/**
	 * スキル情報が未入力の場合、正常に処理が終了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testSuccessPatternNotSkillInfo() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010002");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
		
		//セッションから取り出した従業員情報の入力値
		RegistEmpPageDTO registEmpPageDTO = (RegistEmpPageDTO)httpSession.getAttribute("registEmpPageDTO");
		//セッションから取り出したスキル情報の入力値
		RegistSkillInfoPageDTO registSkillInfoPageDTO = (RegistSkillInfoPageDTO)httpSession.getAttribute("registSkillInfoPageDTO");
				
		// 実行結果を確認
		//セッションに従業員情報の入力値が格納されていること、スキル情報の入力値がnullであることで成功
		assertThat(registEmpPageDTO.getEmployeeId(),is("V502010002"));
		assertThat(registEmpPageDTO.getEmployeeName(),is("従業員名UT"));
		assertThat(registEmpPageDTO.getSex(),is("男"));
		assertThat(registEmpPageDTO.getBirthDate(),is("1996-11-11"));
		assertThat(registEmpPageDTO.getCompanyId(),is("CP00000001"));
		assertThat(registEmpPageDTO.getOffice(),is("事業所UT"));
		assertThat(registEmpPageDTO.getDepartmentId(),is("D000000001"));
		assertThat(registEmpPageDTO.getPostId(),is("P000000001"));
		assertThat(registEmpPageDTO.getPostalCode(),is("340-0202"));
		assertThat(registEmpPageDTO.getAddress(),is("中目黒"));
		assertThat(registEmpPageDTO.getPhoneNumber(),is("090-0000-0000"));
		assertThat(registEmpPageDTO.getMail(),is("hoge@hoge.co.jp"));
		assertThat(registSkillInfoPageDTO.getSkillType(),nullValue());
		assertThat(registSkillInfoPageDTO.getSkillDetail(),nullValue());
		assertThat(registSkillInfoPageDTO.getExperienceYears(),nullValue());
		assertThat(registSkillInfoPageDTO.getAcquisitionYearMonth(),nullValue());
	}

	/**
	 * 全項目が未入力の場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternNotInput() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "");
		httpRequest.setParameter("json[employeeName]", "");
		httpRequest.setParameter("json[sex]", "");
		httpRequest.setParameter("json[birthDate]", "");
		httpRequest.setParameter("json[companyId]", "");
		httpRequest.setParameter("json[office]", "");
		httpRequest.setParameter("json[departmentId]", "");
		httpRequest.setParameter("json[postId]", "");
		httpRequest.setParameter("json[postalCode]", "");
		httpRequest.setParameter("json[address]", "");
		httpRequest.setParameter("json[phoneNumber]", "");
		httpRequest.setParameter("json[mail]", "");
		httpRequest.setParameter("json[skillType][]", "");
		httpRequest.setParameter("json[skillDetail][]", "");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	
	}

	/**
	 * 指定形式でない場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternFormat() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "テスト");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "テスト");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "999A9999");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "999A9999A9999");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
		
	}

	/**
	 * 指定文字数以上入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternLength() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "TEST");
		httpRequest.setParameter("json[employeeName]", "Ａ２３４５６７８９０Ｂ２３４５６７８９０Ｃ");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "Ａ２３４５６７８９０Ｂ２３４５６７８９０Ｃ");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "1234567");
		httpRequest.setParameter("json[address]", "Ａ２３４５６７８９０Ｂ２３４５６７８９０Ｃ２３４５６７８９０Ｄ２３４５６７８９０Ｅ２３４５６７８９０Ｆ２３４５６７８９０Ｇ２３４５６７８９０Ｈ２３４５６７８９０Ｉ２３４５６７８９０Ｊ２３４５６７８９０Ｋ２３４５６７８９０Ｌ２３４５６７８９０Ｍ２３４５６７８９０Ｎ２３４５６７８９０Ｏ２３４５６７８９０Ｐ２３４５６７８９０Ｑ２３４５６７８９０Ｒ２３４５６７８９０Ｓ２３４５６７８９０Ｔ２３４５６７８９０Ｕ２３４５６７８９０Ｖ２３４５６７８９０Ｗ２３４５６７８９０Ｘ２３４５６７８９０Ｙ２３４５６７８９０Ｚ２３４５６７");
		httpRequest.setParameter("json[phoneNumber]", "1234567890123456");
		httpRequest.setParameter("json[mail]", "Ａ２３４５６７８９０Ｂ２３４５６７８９０Ｃ２３４５６７８９０Ｄ２３４５６７８９０Ｅ２３４５６７８９０Ｆ２３４５６７８９０Ｇ２３４５６７８９０Ｈ２３４５６７８９０Ｉ２３４５６７８９０Ｊ２３４５６７８９０Ｋ２３４５６７８９０Ｌ２３４５６７８９０Ｍ２３４５６７８９０Ｎ２３４５６７８９０Ｏ２３４５６７８９０Ｐ２３４５６７８９０Ｑ２３４５６７８９０Ｒ２３４５６７８９０Ｓ２３４５６７８９０Ｔ２３４５６７８９０Ｕ２３４５６７８９０Ｖ２３４５６７８９０Ｗ２３４５６７８９０Ｘ２３４５６７８９０Ｙ２３４５６７８９０Ｚ２３４５６７");
		httpRequest.setParameter("json[skillType][]", "S000000001");
		httpRequest.setParameter("json[skillType][]", "S000000002");
		httpRequest.setParameter("json[skillDetail][]", "Ａ２３４５６７８９０Ｂ２３４５６７８９０Ｃ２３４５６７８９０Ｄ２３４５６７８９０Ｅ２３４５６７８９０Ｆ２３４５６７８９０Ｇ２３４５６７８９０Ｈ２３４５６７８９０Ｉ２３４５６７８９０Ｊ２３４５６７８９０Ｋ２３４５６７８９０Ｌ２３４５６７８９０Ｍ２３４５６７８９０Ｎ２３４５６７８９０Ｏ２３４５６７８９０Ｐ２３４５６７８９０Ｑ２３４５６７８９０Ｒ２３４５６７８９０Ｓ２３４５６７８９０Ｔ２３４５６７８９０Ｕ２３４５６７８９０Ｖ２３４５６７８９０Ｗ２３４５６７８９０Ｘ２３４５６７８９０Ｙ２３４５６７８９０Ｚ２３４５６７");
		httpRequest.setParameter("json[skillDetail][]", "Java");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[experienceYears][]", "A234");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "2018-10-01");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");
		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 従業員IDが形式通りでない場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternEmployeeIdFormat() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "a502010006");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");


		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 従業員IDと所属会社が一致しない場合、エラーメッセージを出力すること。（株式会社V）
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternEmployeeWithCompanyMatchV() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010007");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000003");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 従業員IDと所属会社が一致しない場合、エラーメッセージを出力すること。（K株式会社）
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternEmployeeWithCompanyMatchK() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010008");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000002");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * 従業員IDと所属会社が一致しない場合、エラーメッセージを出力すること。（株式会社O）
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternEmployeeWithCompanyMatchO() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "O502010009");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

		/**
	 * 妥当性エラーの場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternValidation () throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010010");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "テスト");
		httpRequest.setParameter("json[birthDate]", "9999-12-31");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D999999999");
		httpRequest.setParameter("json[postId]", "P999999999");
		httpRequest.setParameter("json[postalCode]", "12345678");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "12345678");
		httpRequest.setParameter("json[mail]", "test");
		httpRequest.setParameter("json[skillType][]", "S999999999");
		httpRequest.setParameter("json[skillDetail][]", "");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 所属会社がK株式会社の場合、正常に処理が終了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testSuccessPatternCompanyK() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "K502010011");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000002");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
		
		//セッションから取り出した従業員情報の入力値
		RegistEmpPageDTO registEmpPageDTO = (RegistEmpPageDTO)httpSession.getAttribute("registEmpPageDTO");
		//セッションから取り出したスキル情報の入力値
		RegistSkillInfoPageDTO registSkillInfoPageDTO = (RegistSkillInfoPageDTO)httpSession.getAttribute("registSkillInfoPageDTO");
				
		// 実行結果を確認
		//セッションに従業員情報の入力値が格納されていること、スキル情報の入力値がnullであることで成功
		assertThat(registEmpPageDTO.getEmployeeId(),is("K502010011"));
		assertThat(registEmpPageDTO.getEmployeeName(),is("従業員名UT"));
		assertThat(registEmpPageDTO.getSex(),is("男"));
		assertThat(registEmpPageDTO.getBirthDate(),is("1996-11-11"));
		assertThat(registEmpPageDTO.getCompanyId(),is("CP00000002"));
		assertThat(registEmpPageDTO.getOffice(),is("事業所UT"));
		assertThat(registEmpPageDTO.getDepartmentId(),is("D000000001"));
		assertThat(registEmpPageDTO.getPostId(),is("P000000001"));
		assertThat(registEmpPageDTO.getPostalCode(),is("340-0202"));
		assertThat(registEmpPageDTO.getAddress(),is("中目黒"));
		assertThat(registEmpPageDTO.getPhoneNumber(),is("090-0000-0000"));
		assertThat(registEmpPageDTO.getMail(),is("hoge@hoge.co.jp"));
		assertThat(registSkillInfoPageDTO.getSkillType(),nullValue());
		assertThat(registSkillInfoPageDTO.getSkillDetail(),nullValue());
		assertThat(registSkillInfoPageDTO.getExperienceYears(),nullValue());
		assertThat(registSkillInfoPageDTO.getAcquisitionYearMonth(),nullValue());
	}
	
	/**
	 * 所属会社がO株式会社の場合、正常に処理が終了すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testSuccessPatternCompanyO() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "O502010012");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000003");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
		
		//セッションから取り出した従業員情報の入力値
		RegistEmpPageDTO registEmpPageDTO = (RegistEmpPageDTO)httpSession.getAttribute("registEmpPageDTO");
		//セッションから取り出したスキル情報の入力値
		RegistSkillInfoPageDTO registSkillInfoPageDTO = (RegistSkillInfoPageDTO)httpSession.getAttribute("registSkillInfoPageDTO");
		
		// 実行結果を確認
		//セッションに従業員情報の入力値が格納されていること、スキル情報の入力値がnullであることで成功
		assertThat(registEmpPageDTO.getEmployeeId(),is("O502010012"));
		assertThat(registEmpPageDTO.getEmployeeName(),is("従業員名UT"));
		assertThat(registEmpPageDTO.getSex(),is("男"));
		assertThat(registEmpPageDTO.getBirthDate(),is("1996-11-11"));
		assertThat(registEmpPageDTO.getCompanyId(),is("CP00000003"));
		assertThat(registEmpPageDTO.getOffice(),is("事業所UT"));
		assertThat(registEmpPageDTO.getDepartmentId(),is("D000000001"));
		assertThat(registEmpPageDTO.getPostId(),is("P000000001"));
		assertThat(registEmpPageDTO.getPostalCode(),is("340-0202"));
		assertThat(registEmpPageDTO.getAddress(),is("中目黒"));
		assertThat(registEmpPageDTO.getPhoneNumber(),is("090-0000-0000"));
		assertThat(registEmpPageDTO.getMail(),is("hoge@hoge.co.jp"));
		assertThat(registSkillInfoPageDTO.getSkillType(),nullValue());
		assertThat(registSkillInfoPageDTO.getSkillDetail(),nullValue());
		assertThat(registSkillInfoPageDTO.getExperienceYears(),nullValue());
		assertThat(registSkillInfoPageDTO.getAcquisitionYearMonth(),nullValue());
	}

	/**
	 * 生年月日がYYYY-MM-DDの構成でない場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternBirthDateFormat() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();
		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010002");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "19961111");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * スキル内容が未入力の場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	public void testFailurePatternNotInputSkillDetail() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010001");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000001");
		httpRequest.setParameter("json[skillDetail][]", "");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "2018-10");


		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 経験年数が指定文字数以上入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	public void testFailurePatternLengthExperienceYears() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010001");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000002");
		httpRequest.setParameter("json[skillDetail][]", "Java");
		httpRequest.setParameter("json[experienceYears][]", "A234");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");


		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * 取得年月が指定文字数以上入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	public void testFailurePatternNotInputAcquisitionYearMonth() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010001");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000001");
		httpRequest.setParameter("json[skillDetail][]", "基本情報技術者");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "2018-10-1");


		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * スキル種別の年数/取得日フラグが"0"の時に経験年数が入力されてた場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	public void testFailurePatternValidityExperienceYears() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010001");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000001");
		httpRequest.setParameter("json[skillDetail][]", "基本情報技術者");
		httpRequest.setParameter("json[experienceYears][]", "2年");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");


		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}

	/**
	 * スキル種別の年数/取得日フラグが"1"の時に取得年数が入力されてた場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	public void testFailurePatternValidityAcquisitionYearMonth() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010001");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000002");
		httpRequest.setParameter("json[skillDetail][]", "Java");
		httpRequest.setParameter("json[experienceYears][]", "2年");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "2018-10");


		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * 	郵便番号、メールアドレスが形式外で入力された場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternValidityOutOfFormat() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010020");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "１２３-４５６７");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "あいうえお@test.com");
		httpRequest.setParameter("json[skillType][]", "S000000001");
		httpRequest.setParameter("json[skillType][]", "S000000002");
		httpRequest.setParameter("json[skillDetail][]", "基本情報技術者");
		httpRequest.setParameter("json[skillDetail][]", "Java");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[experienceYears][]", "2年");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "2018-10");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * 	スキル種別の年数/取得日フラグが"0"の時に取得年月が入力されていない場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternNotAcquisitionYearMonth() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010020");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000001");
		httpRequest.setParameter("json[skillDetail][]", "基本情報技術者");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}
	
	/**
	 * 	スキル種別の年数/取得日フラグが"1"の時に経験年数が入力されてたいない場合、エラーメッセージを出力すること。
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void testFailurePatternNotExperienceYears() throws ServletException, IOException {
		MockHttpRequest httpRequest = new MockHttpRequest();
		MockHttpSession httpSession = new MockHttpSession();
		MockHttpResponse httpResponse = new MockHttpResponse();

		// 疑似リクエストスコープに情報をセット
		httpRequest.setParameter("json[employeeId]", "V502010020");
		httpRequest.setParameter("json[employeeName]", "従業員名UT");
		httpRequest.setParameter("json[sex]", "男");
		httpRequest.setParameter("json[birthDate]", "1996-11-11");
		httpRequest.setParameter("json[companyId]", "CP00000001");
		httpRequest.setParameter("json[office]", "事業所UT");
		httpRequest.setParameter("json[departmentId]", "D000000001");
		httpRequest.setParameter("json[postId]", "P000000001");
		httpRequest.setParameter("json[postalCode]", "340-0202");
		httpRequest.setParameter("json[address]", "中目黒");
		httpRequest.setParameter("json[phoneNumber]", "090-0000-0000");
		httpRequest.setParameter("json[mail]", "hoge@hoge.co.jp");
		httpRequest.setParameter("json[skillType][]", "S000000002");
		httpRequest.setParameter("json[skillDetail][]", "Java");
		httpRequest.setParameter("json[experienceYears][]", "");
		httpRequest.setParameter("json[acquisitionYearMonth][]", "");

		String adminUser = "adminUser";
		String companyId = "CP00000001";
		// 疑似セッションスコープに情報をセット
		httpSession.setAttribute("userId", adminUser);
		httpSession.setAttribute("companyId", companyId);
		httpRequest.setSession(httpSession);

		// 実行クラスがservletのときはおまじないとして記述
		registEmpCheckAction.init(new MockServletConfig());

		// 実際にテスト対象のメソッドを実行する。
		registEmpCheckAction.doPost(httpRequest, httpResponse);
	}
}