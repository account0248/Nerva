package jp.co.vaile.nerva.masterUser.updateUser;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumUpperLetterFormatCheck;

public class UpdateUserCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck HarfWidthNumUpperLetterFormatCheck = new HarfWidthNumUpperLetterFormatCheck();
	List<String> errorList = new ArrayList<String>();

	/**
	 * ユーザーマスタメンテナンス画面で編集されたユーザー名のエラーチェック処理を行い、エラーメッセージをリストに格納して返す。
	 * 
	 * @param userName
	 * @return errorList エラーメッセージのリスト
	 */
	public List<String> checkUpdateUserNameErrMsg(String userName) {
		// ユーザー名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdateUserName(userName), errorList);
		return errorList;
	}

	/**
	 * ユーザーマスタメンテナンス画面で編集されたパスワードのエラーチェック処理を行い、エラーメッセージをリストに格納して返す。
	 * 
	 * @param password
	 * @return errorList エラーメッセージのリスト
	 */
	public List<String> checkUpdatePasswordErrMsg(String password)
			throws ClassNotFoundException, SQLException, ParseException {
		// パスワードのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdatePassword(password), errorList);
		return errorList;
	}

	/**
	 * ユーザー名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param userName ユーザーマスタメンテナンス画面の更新フォームで入力されたユーザー名
	 * @return エラーメッセージ
	 */
	private String checkUpdateUserName(String userName) {

		String[] userNameErrorList = { CommonConstants.USER_NAME, "20" };

		// 未入力の場合、エラーメッセージを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(userName)) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, userNameErrorList);
		}
		// 入力された値が20文字以内でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isMaxStringLength(userName, 20)) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, userNameErrorList);
		}

		return null;
	}

	/**
	 * パスワードが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param password ユーザーマスタメンテナンス画面の更新フォームで入力されたパスワード
	 * @return エラーメッセージ
	 */
	private String checkUpdatePassword(String password) throws ClassNotFoundException, SQLException, ParseException {

		String[] passwordList = { PASSWORD, "8" };
		String[] passwordLimitList = { PASSWORD, "20" };

		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(password) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, passwordList);
		}
		// 2. P1が8文字以上で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isMinStringLength(password, 8) == false) {
			return errorMsg.returnErrorMsg(INPUT_MIN_LENGTH_ERROR_MESSAGE, passwordList);
		}
		// 3. P1が20文字以内で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isMaxStringLength(password, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, passwordLimitList);
		}
		// 4. P1で入力された値が大文字、小文字、数字を含む半角英数字でなければ、エラーメッセージを返す。
		// 大文字、小文字、数字を含む半角英数字であればnullを返す。
		if (HarfWidthNumUpperLetterFormatCheck.isCorrectFormat(password) == false) {
			return errorMsg.returnErrorMsg(PASSWORD_FORMAT, passwordList);

		}
		return null;
	}
}
