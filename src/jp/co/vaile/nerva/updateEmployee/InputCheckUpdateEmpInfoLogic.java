package jp.co.vaile.nerva.updateEmployee;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongDepartmentInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.PostInfo;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumAtSignFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.MailFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PhoneNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PostalCodeFormatCheck;

public class InputCheckUpdateEmpInfoLogic {
	LengthCheck lengthCheck = new LengthCheck();
	ErrorMsg errorMsg = new ErrorMsg();
	Map<String, String> errorMsgMap = new HashMap<String, String>();

	/**
	 * 従業員情報の入力チェックを行う。入力が正しくない場合エラーメッセージをリストに追加する。
	 * @param updateEmpInfoDTO 従業員情報を格納したDTO
	 * @return errorMsgList エラーメッセージを格納したリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Map<String, String> checkEmpInfoContent(UpdateEmpInfoDTO updateEmpInfoDTO)
			throws ClassNotFoundException, SQLException {

		//従業員名の入力チェックを行う。
		checkEmployeeName(updateEmpInfoDTO.getEmployeeName());

		//事業所の入力チェックを行う。
		checkOffice(updateEmpInfoDTO.getOffice());

		//所属組織の入力チェックを行う。
		checkDepartmentId(updateEmpInfoDTO.getBelongDepartmentId());

		//役職の入力チェックを行う。
		checkPostId(updateEmpInfoDTO.getPostId());

		//郵便番号の入力チェックを行う。
		checkPostalCode(updateEmpInfoDTO.getPostalCode());

		//住所の入力チェックを行う。
		checkAddress(updateEmpInfoDTO.getAddress());

		//電話番号の入力チェックを行う。
		checkPhoneNumber(updateEmpInfoDTO.getPhoneNumber());

		//メールアドレスの入力チェックを行う。
		checkMail(updateEmpInfoDTO.getMail());

		//戻り値を返す。
		return errorMsgMap;
	}

	/**
	 * 従業員名の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param employeeName 従業員名を表す文字列
	 */
	private void checkEmployeeName(String employeeName) {
		String[] employeeNameErrorMsg = { EMPLOYEE_NAME, "20" };
		//従業員名が入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(employeeName)) {
			errorMsgMap.put("requiredEmployeeName",
					errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, employeeNameErrorMsg));
			return;
		}
		//従業員名が20文字以内で入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isMaxStringLength(employeeName, 20)) {
			errorMsgMap.put("lengthEmployeeName",
					errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, employeeNameErrorMsg));
			return;
		}
	}

	/**
	 * 事業所の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param office 事業所を表す文字列
	 */
	private void checkOffice(String office) {
		String[] officeErrorMsg = { OFFICE_NAME, "20" };
		//事業所が入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(office)) {
			errorMsgMap.put("requiredOffice", errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, officeErrorMsg));
			return;
		}
		//事業所が20文字以内で入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isMaxStringLength(office, 20)) {
			errorMsgMap.put("lengthOffice", errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, officeErrorMsg));
			return;
		}
	}

	/**
	 * 所属組織IDの入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param departmentId 所属組織IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void checkDepartmentId(String departmentId) throws ClassNotFoundException, SQLException {
		String[] departmentErrorMsg = { DEPARTMENT };
		//所属組織が選択されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(departmentId)) {
			errorMsgMap.put("requiredDepartmentId",
					errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, departmentErrorMsg));
			return;
		}
		//選択された所属組織がDBに存在しない場合、エラーメッセージを格納する。
		ExistCheck existCheck = new BelongDepartmentInfo();
		if (!existCheck.isThisExistDB(departmentId)) {
			errorMsgMap.put("validityDepartmentId",
					errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, departmentErrorMsg));
			return;
		}
	}

	/**
	 * 役職IDの入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param postId 役職IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void checkPostId(String postId) throws ClassNotFoundException, SQLException {
		String[] postErrorMsg = { POST };
		//役職が選択されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(postId)) {
			errorMsgMap.put("requiredPostId", errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, postErrorMsg));
			return;
		}
		//選択された役職がDBに存在しない場合エラーメッセージを格納する。
		ExistCheck existCheck = new PostInfo();
		if (!existCheck.isThisExistDB(postId)) {
			errorMsgMap.put("validityPostId", errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, postErrorMsg));
			return;
		}
	}

	/**
	 * 郵便番号の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param postalCode 郵便番号を表す文字列
	 */
	private void checkPostalCode(String postalCode) {
		String[] postalCodeErrorMsg = { POSTAL_CODE_NAME, "8" };
		//郵便番号が入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(postalCode)) {
			errorMsgMap.put("requiredPostalCode", errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, postalCodeErrorMsg));
			return;
		}
		//郵便番号が8文字以内で入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isStringLength(postalCode, 8)) {
			errorMsgMap.put("lengthPostalCode",
					errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, postalCodeErrorMsg));
			return;
		}
		//郵便番号が形式通り入力されていない場合、エラーメッセージを格納する。
		FormatCheck postalCodeFormatCheck = new PostalCodeFormatCheck();
		if (!postalCodeFormatCheck.isCorrectFormat(postalCode)) {
			errorMsgMap.put("validityPostalCode",
					errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, postalCodeErrorMsg));
			return;
		}
	}

	/**
	 * 住所の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param address 住所を表す文字列
	 */
	private void checkAddress(String address) {
		String[] addressErrorMsg = { ADDRESS_NAME, "256" };
		//住所が入力されていない場合エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(address)) {
			errorMsgMap.put("requiredAddress", errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, addressErrorMsg));
			return;
		}
		//住所が256文字以内で入力されていない場合エラーメッセージを格納する。
		if (!lengthCheck.isMaxStringLength(address, 256)) {
			errorMsgMap.put("lengthAddress", errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, addressErrorMsg));
			return;
		}
	}

	/**
	 * 電話番号の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param phoneNumber 電話番号を表す文字列
	 */
	private void checkPhoneNumber(String phoneNumber) {
		String[] phoneNumberErrorMsg = { PHONE_NUMBER_NAME, "15" };
		//電話番号が入力されていない場合エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(phoneNumber)) {
			errorMsgMap.put("requiredPhoneNumber",
					errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, phoneNumberErrorMsg));
			return;
		}
		//電話番号が15文字以内で入力されていない場合エラーメッセージを格納する。
		if (!lengthCheck.isMaxStringLength(phoneNumber, 15)) {
			errorMsgMap.put("lengthPhoneNumber",
					errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, phoneNumberErrorMsg));
			return;
		}
		
		//電話番号が半角数字で入力されていない場合エラーメッセージを格納する。
		FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
		String replacePhoneNumber = phoneNumber.replace("-", "");
		if (!harfWidthNumFormatCheck.isCorrectFormat(replacePhoneNumber)) {
			errorMsgMap.put("halfWidthPhoneNumber",
					errorMsg.returnErrorMsg(HARF_WIDTH_NUM_ERROR_MESSAGE, phoneNumberErrorMsg));
			return;
		}
		
		//電話番号が形式通りに入力されていない場合エラーメッセージを格納する。
		FormatCheck phoneNumFormatCheck = new PhoneNumFormatCheck();
		if (!phoneNumFormatCheck.isCorrectFormat(phoneNumber)) {
			errorMsgMap.put("validityPhoneNumber",
					errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, phoneNumberErrorMsg));
			return;
		}
	}

	/**
	 * メールアドレスの入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param mail メールアドレスを表す文字列
	 */
	private void checkMail(String mail) {
		String[] mailErrorMsg = { MAIL_NAME, "256" };
		//メールアドレスが入力されていない場合エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(mail)) {
			errorMsgMap.put("requiredMail", errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, mailErrorMsg));
			return;
		}
		//メールアドレスが256文字以内で入力されていない場合エラーメッセージを格納する。
		if (!lengthCheck.isMaxStringLength(mail, 256)) {
			errorMsgMap.put("lengthMail", errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, mailErrorMsg));
			return;
		}
		
		//メールアドレスが半角英数字で入力されていない場合エラーメッセージを格納する。
		FormatCheck harfWidthAlphanumAtSignFormatCheck = new HarfWidthAlphanumAtSignFormatCheck();
		if (!harfWidthAlphanumAtSignFormatCheck.isCorrectFormat(mail)) {
			errorMsgMap.put("halfWidthMail", errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, mailErrorMsg));
			return;
		}
		
		//メールアドレスが形式通りに入力されていない場合エラーメッセージを格納する。
		FormatCheck mailFormatCheck = new MailFormatCheck();
		if (!mailFormatCheck.isCorrectFormat(mail)) {
			errorMsgMap.put("validityMail", errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, mailErrorMsg));
			return;
		}
	}
}
