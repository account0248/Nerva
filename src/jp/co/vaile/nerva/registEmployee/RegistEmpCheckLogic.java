package jp.co.vaile.nerva.registEmployee;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.companyMaster.SearchCompany;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongDepartmentInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.PostInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.SexInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.SkillTypeInfo;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DateFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.EmpIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumAtSignFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.MailFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PhoneNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PostalCodeFormatCheck;
import jp.co.vaile.nerva.commonprocess.skillMaster.GetYearsDateOfAcquisitFlgDAO;

public class RegistEmpCheckLogic {
	SearchCompany searchCompany = new SearchCompany();
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
	FormatCheck dateFormatCheck = new DateFormatCheck();
	CommonConstants commonConstants = new CommonConstants();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param registEmpPageDTO       登録画面で記載された内容
	 * @param registSkillInfoPageDTO 従業員登録画面で記載されたスキル情報。
	 * @return エラーメッセージが格納されたリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws ParseException
	 * 
	 */
	List<String> inputCheckRegistEmp(RegistEmpPageDTO registEmpPageDTO, RegistSkillInfoPageDTO registSkillInfoPageDTO)
			throws ClassNotFoundException, SQLException, ParseException{
		List<String> errorList = new ArrayList<String>();
		// 1.P1の従業員IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkRegistEmpId(registEmpPageDTO.getEmployeeId(), registEmpPageDTO.getCompanyId()), errorList);
		// 2.P1の従業員名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpName(registEmpPageDTO.getEmployeeName()), errorList);
		// 3.P1の性別のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpSex(registEmpPageDTO.getSex()), errorList);
		// 4.P1の生年月日のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpBirthDate(registEmpPageDTO.getBirthDate()), errorList);
		// 5.P1の所属会社のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpBelongCompany(registEmpPageDTO.getCompanyId()), errorList);
		// 6.P1の事業所のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpOffice(registEmpPageDTO.getOffice()), errorList);
		// 7.P1の所属組織のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpDepartment(registEmpPageDTO.getDepartmentId()), errorList);
		// 8.P1の役職のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpPost(registEmpPageDTO.getPostId()), errorList);
		// 9.P1の郵便番号のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpPostalCode(registEmpPageDTO.getPostalCode()), errorList);
		// 10.P1の住所のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpAdressname(registEmpPageDTO.getAddress()), errorList);
		// 11.P1の電話番号のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpPhoneNumber(registEmpPageDTO.getPhoneNumber()), errorList);
		// 12.P1のメールアドレスのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpMail(registEmpPageDTO.getMail()), errorList);
		// 13.P1の保有スキルのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkRegistEmpSkillInfo(registSkillInfoPageDTO), errorList);
		return errorList;
	}

	/**
	 * 従業員IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param employeeId 登録画面で記載された従業員ID
	 * @param companyId  登録画面で記載された所属会社ID
	 * @return エラーメッセージ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private String checkRegistEmpId(String employeeId, String companyId) throws ClassNotFoundException, SQLException {
		String[] employeeIdList = { EMPLOYEE_ID, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(employeeId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, employeeIdList);
		}
		// 2.P1が半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(employeeId) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, employeeIdList);
		}
		// 3. P1が10文字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isStringLength(employeeId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, employeeIdList);
		}
		/*
		 * 4. P1が形式通りに入力されているか確認する。
		 * 
		 */
		EmpIDFormatCheck empIdFormatCheck = new EmpIDFormatCheck();
		if (!empIdFormatCheck.isCorrectFormat(employeeId)) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, employeeIdList);
		}
		// 所属会社IDを引数に、選択した会社の会社識別コードを取得
		String companyCode = searchCompany.belongCompanyCode(companyId);
		// 4-1 P1とP2の文字の1番目の値が一致している確認する。形式通り入力できていない場合、エラーメッセージを返す。
		if (!(employeeId.substring(0, 1).equals(companyCode))) {
			return errorMsg.returnErrorMsg(PROJECT_ID_FORMAT_ERROR_MESSAGE, employeeIdList);
		}

		// 重複処理
		RegistEmpCheckDAO registEmpCheckDAO = new RegistEmpCheckDAO();
		int numEmoloyeeId = registEmpCheckDAO.selectEmployeeId(employeeId);
		if (numEmoloyeeId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, employeeIdList);
		}

		return null;
	}

	/**
	 * 従業員名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param employeeName
	 * @return エラーメッセージ
	 */
	private String checkRegistEmpName(String employeeName) {
		String[] employeeNameList = { EMPLOYEE_NAME, "20" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(employeeName) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, employeeNameList);
		}
		// 2.P1が10文字以内で入力されてない場合、エラーメッセージを返す。

		if (lengthCheck.isMaxStringLength(employeeName, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, employeeNameList);
		}
		// 入力されている場合nullを返す。
		return null;
	}

	/**
	 * 性別が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param sex 登録画面で記載された性別
	 * @return エラーメッセージ
	 */
	private String checkRegistEmpSex(String sex) {
		SexInfo sexInfo = new SexInfo();
		String[] sexList = { SEX };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		if (lengthCheck.isNomNullOrNomEmpty(sex) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, sexList);
		}
		// 2.P1の内容を確認する。
		// 2-2.上記以外の内容が入力されている場合エラーメッセージを返す。
		if (sexInfo.isThisExistInfo(sex) == false) {
			return errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, sexList);
		}
		// 2-1.P1が｢男｣または｢女｣が入力されている場合nullを返す。
		return null;
	}

	/**
	 * 生年月日が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param birthDate 登録画面で記載された生年月日
	 * @return エラーメッセージ
	 * @throws ParseException
	 */
	private String checkRegistEmpBirthDate(String birthDate) throws ParseException {
		String[] birthDateList = { BIRTHDATE };

		// P1が入力されていない場合、エラーメッセージを返す。
		if (lengthCheck.isNomNullOrNomEmpty(birthDate) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, birthDateList);
		}
		// 2.P1が半角数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		String replaceBirthDate = deleteHyphen(birthDate);
		if (harfWidthNumFormatCheck.isCorrectFormat(replaceBirthDate) == false) {
			return errorMsg.returnErrorMsg(HARF_WIDTH_NUM_ERROR_MESSAGE, birthDateList);
		}
		if (dateFormatCheck.isCorrectFormat(birthDate) == false) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, birthDateList);
		}
		//
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// P1を現在時刻と比較する。
		Date birthDateComparison = dateFormat.parse(birthDate);
		Date current_date = new Date();
		if (current_date.before(birthDateComparison)) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, birthDateList);
		}
		return null;
	}

	/**
	 * 所属会社が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param companyId 登録画面で記載された所属会社ID
	 * @return エラーメッセージ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private String checkRegistEmpBelongCompany(String companyId) throws ClassNotFoundException, SQLException {
		/*
		 * 1. P1が選択されていない場合、エラーメッセージを返す。
		 */
		String[] companyNameList = { COMPANY_NAME };
		if (!lengthCheck.isNomNullOrNomEmpty(companyId)) {
			return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, companyNameList);
		}

		return null;
	}

	/**
	 * 事業所が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param office 登録画面で記載された事業所名
	 * @return エラーメッセージ
	 */
	private String checkRegistEmpOffice(String office) {
		/*
		 * 1.P1が入力されていない場合、エラーメッセージを返す。 入力されている 場合2に進む
		 */
		String[] officeList = { OFFICE_NAME, "20" };
		if (lengthCheck.isNomNullOrNomEmpty(office) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, officeList);
		}
		/*
		 * 2.P1が20字以内で入力されていない場合、エラーメッセージを返す。
		 */
		if (lengthCheck.isMaxStringLength(office, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, officeList);
		}
		// nulを返す
		return null;
	}

	/**
	 * 所属組織が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param departmentId 登録画面で記載された所属組織ID
	 * @return エラーメッセージ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private String checkRegistEmpDepartment(String departmentId) throws ClassNotFoundException, SQLException {
		/*
		 * 1. P1が入力されていない場合、エラーメッセージを返す。 入力されている場合2に進む。
		 */
		String[] departmentList = { DEPARTMENT };
		if (lengthCheck.isNomNullOrNomEmpty(departmentId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, departmentList);
		}
		ExistCheck existCheck = new BelongDepartmentInfo();
		/*
		 * 2. P1が「システム本部」「金融システム部」「情報システム部」「基幹システム部」「基盤システム部」のIDでない場合、エラーメッセージを返す。
		 * 入力されている場合nullを返す。
		 */
		if (existCheck.isThisExistDB(departmentId) == false) {
			return errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, departmentList);
		}
		return null;
	}

	/**
	 * 役職が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param postId 登録画面で記載された役職ID
	 * @return エラーメッセージ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private String checkRegistEmpPost(String postId) throws ClassNotFoundException, SQLException {
		/*
		 * 1. P1が入力されていない場合、エラーメッセージを返す。 入力されている場合nullを返す。
		 */
		String[] postList = { POST };
		if (lengthCheck.isNomNullOrNomEmpty(postId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, postList);
		}
		ExistCheck existCheck = new PostInfo();
		if (existCheck.isThisExistDB(postId) == false) {
			return errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, postList);
		}
		return null;
	}

	/**
	 * 郵便番号が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param postalCode
	 * @return エラーメッセージ
	 */
	private String checkRegistEmpPostalCode(String postalCode) {
		/*
		 * 1. P1が入力されていない場合、エラーメッセージを返す。 入力されている場合2に進む。
		 */
		String[] postalCodeList = { POSTAL_CODE_NAME, "8" };
		if (lengthCheck.isNomNullOrNomEmpty(postalCode) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, postalCodeList);
		}
		/*
		 * 2. P1が8文字で入力されていない場合、エラーメッセージを返す。 入力されている場合nullを返す。
		 */
		// 入力されている場合3に進む。
		if (lengthCheck.isStringLength(postalCode, 8) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, postalCodeList);
		}
		FormatCheck postalCodeFormatCheck = new PostalCodeFormatCheck();
		/*
		 * 3. P1が形式通りに入力されていない場合、エラーメッセージを返す。 入力されている場合4に進む。
		 */
		if (postalCodeFormatCheck.isCorrectFormat(postalCode) == false) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, postalCodeList);
		}
		return null;
	}

	/**
	 * 住所が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param address 登録画面で記載された住所
	 * @return エラーメッセージ
	 */
	private String checkRegistEmpAdressname(String address) {
		/*
		 * 1. P1が入力されていない場合、エラーメッセージを返す。 入力されている場合2に進む。
		 */
		String[] addressList = { ADDRESS_NAME, "256" };
		if (lengthCheck.isNomNullOrNomEmpty(address) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, addressList);
		}
		/*
		 * 2. P1が256文字以内で入力されていない場合、エラーメッセージを返す。 入力されている場合nullを返す。
		 */
		if (lengthCheck.isMaxStringLength(address, 256) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, addressList);
		}
		return null;
	}

	/**
	 * TELが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param phoneNumber 登録画面で記載されたTEL
	 * @return エラーメッセージ
	 */
	private String checkRegistEmpPhoneNumber(String phoneNumber) {
		/*
		 * 1. P1が入力されていない場合、エラーメッセージを返す。 入力されている場合2に進む。。
		 */
		String[] phoneNumberList = { PHONE_NUMBER_NAME, "15" };
		if (lengthCheck.isNomNullOrNomEmpty(phoneNumber) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, phoneNumberList);
		}
		/*
		 * 3.P1が15文字以内で入力されていない場合、エラーメッセージを返す。 入力されている場合nullを返す
		 */
		if (lengthCheck.isMaxStringLength(phoneNumber, 15) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, phoneNumberList);
		}
		// 2.P1が半角数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		String replacePhoneNumber = deleteHyphen(phoneNumber);
		if (harfWidthNumFormatCheck.isCorrectFormat(replacePhoneNumber) == false) {
			return errorMsg.returnErrorMsg(HARF_WIDTH_NUM_ERROR_MESSAGE, phoneNumberList);
		}
		FormatCheck phoneNumFormatCheck = new PhoneNumFormatCheck();
		/*
		 * 2.P1が形式通りに入力されていない場合、エラーメッセージを返す。 入力されている場合3に進む。
		 */
		if (phoneNumFormatCheck.isCorrectFormat(phoneNumber) == false) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, phoneNumberList);
		}
		return null;
	}

	/**
	 * メールアドレスが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param mail 登録画面で記載されたメールアドレス
	 * @return エラーメッセージ
	 */
	private String checkRegistEmpMail(String mail) {
		/*
		 * 1. P1が入力されていない場合、エラーメッセージを返す。 入力されている場合2に進む。
		 */
		String[] mailList = { MAIL_NAME, "256" };
		if (lengthCheck.isNomNullOrNomEmpty(mail) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, mailList);
		}
		/*
		 * 2. P1が256文字以内で入力されていない場合、エラーメッセージを返す。 入力されている場合3に進む。
		 */
		if (lengthCheck.isMaxStringLength(mail, 256) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, mailList);
		}
		// 半角英数字
		FormatCheck harfWidthAlphanumAtSignFormatCheck = new HarfWidthAlphanumAtSignFormatCheck();
		/*
		 * 3. P1が半角英数字で入力されていない場合、エラーメッセージを返す。 入力されている場合nullを返す。
		 */
		if (harfWidthAlphanumAtSignFormatCheck.isCorrectFormat(mail) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, mailList);
		}
		// ＠を含んでいるか
		FormatCheck mailGFormatCheck = new MailFormatCheck();
		/*
		 * 4. P1が@を含んで入力されていない場合、エラーメッセージを返す。 入力されている場合nullを返す。
		 */
		if (mailGFormatCheck.isCorrectFormat(mail) == false) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, mailList);
		}
		return null;

	}

	/**
	 * 保有スキル欄が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param registSkillInfoPageDTO 登録画面で記載された保有スキル
	 * @return エラーメッセージ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private String checkRegistEmpSkillInfo(RegistSkillInfoPageDTO registSkillInfoPageDTO)
			throws ClassNotFoundException, SQLException {
		// 追記 年数/取得日フラグ取得処理があるインスタンス生成
		GetYearsDateOfAcquisitFlgDAO getYearsDateOfAcquisitFlgDAO = new GetYearsDateOfAcquisitFlgDAO();

		if (registSkillInfoPageDTO.getSkillType() == null) {
			return null;
		}
		ExistCheck existCheck = new SkillTypeInfo();
		for (int skillInfoIndex = 0; skillInfoIndex < registSkillInfoPageDTO.getSkillType().size(); skillInfoIndex++) {
			String skillType = registSkillInfoPageDTO.getSkillType().get(skillInfoIndex);
			String skillDetail = registSkillInfoPageDTO.getSkillDetail().get(skillInfoIndex);
			String experienceYears = registSkillInfoPageDTO.getExperienceYears().get(skillInfoIndex);
			String acquisitionYearMonth = registSkillInfoPageDTO.getAcquisitionYearMonth().get(skillInfoIndex);
			// 追記 年数/取得日フラグ取得
			boolean yearsDateOfAcquisitFlg = getYearsDateOfAcquisitFlgDAO.getYearsDateOfAcquisit(registSkillInfoPageDTO.getSkillType().get(skillInfoIndex));
			String[] skillInfoList = { SKILL_INFO_NAME };

			// スキル種別必須
			if (lengthCheck.isNomNullOrNomEmpty(skillType) == false) {
				return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, skillInfoList);
			}
			// スキル種別スキルマスタ内に存在するか
			if (existCheck.isThisExistDB(skillType) == false) {
				String[] skillTypeList = { SKILL_TYPE_NAME };
				return errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, skillTypeList);
			}
			String[] skillDetailList = { SKILL_DETAIL_NAME, "256" };
			// スキル内容必須チェック
			if (lengthCheck.isNomNullOrNomEmpty(skillDetail) == false) {
				return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, skillDetailList);
			}
			// スキル内容文字数チェック
			if (lengthCheck.isMaxStringLength(skillDetail, 256) == false) {
				return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, skillDetailList);
			}
			// スキル経験年数入力されている場合の文字数チェック
			if (yearsDateOfAcquisitFlg) {
				String[] experienceYearsMaxLengthList = { EXPERIENCE_YEARS_NAME, "3" };
				if (lengthCheck.isNomNullOrNomEmpty(experienceYears)) {
					if (lengthCheck.isMaxStringLength(experienceYears, 3) == false) {
						return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, experienceYearsMaxLengthList);
					}
				} else {
					// スキル経験年数が入力されていない場合、エラーメッセージを出力
					return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, experienceYearsMaxLengthList);
				}
			}
			// スキル取得年月が入力されている場合の形式チェック
			if (!yearsDateOfAcquisitFlg) {
				String[] acquisitionYearMonthLengthList = { ACQUISITION_YEAR_MONTH, "7" };
				if (lengthCheck.isNomNullOrNomEmpty(acquisitionYearMonth)) {
					if (!lengthCheck.isMaxStringLength(acquisitionYearMonth, 7)) {

						return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, acquisitionYearMonthLengthList);
					}
				} else {
					// スキル取得年月が入力されていない場合、エラーメッセージを出力
					return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, acquisitionYearMonthLengthList);
				}
			}
		}
		return null;
	}

	/**
	 * -取り除く
	 * 
	 * @param number
	 * @return
	 */
	private String deleteHyphen(String number) {
		String removed = number.replace("-", "");
		return removed;

	}
}
