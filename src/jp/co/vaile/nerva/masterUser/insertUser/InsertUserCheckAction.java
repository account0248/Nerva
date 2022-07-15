package jp.co.vaile.nerva.masterUser.insertUser;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumUpperLetterFormatCheck;

public class InsertUserCheckAction {

	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	FormatCheck HarfWidthNumUpperLetterFormatCheck = new HarfWidthNumUpperLetterFormatCheck();
	CommonConstants commonConstants = new CommonConstants();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();

	public List<String> checkInsertUser(InsertUserDTO insertUserDTO)
			throws ClassNotFoundException, SQLException, ParseException {
		List<String> errorList = new ArrayList<String>();
		// 1.P1のユーザーIDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertUserId(insertUserDTO.getTargetUserId()), errorList);
		// 2.P1のユーザー名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertUserName(insertUserDTO.getUserName()), errorList);
		// 3.P1のパスワードにエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertPassword(insertUserDTO.getPassword()), errorList);
		// 4.P1の所属会社にエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertCompany(insertUserDTO.getCompany()), errorList);
		// 5.P1の役職にエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertPost(insertUserDTO.getPost()), errorList);
		// 6.P1の権限にエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertPrivilege(insertUserDTO.getAdminFlg()), errorList);
		return errorList;
	}

	private String checkInsertUserId(String UserId) throws ClassNotFoundException, SQLException, ParseException {
		String[] userIdList = { USER_ID, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(UserId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, userIdList);
		}
		// 2. P1が10文字以内で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (lengthCheck.isMaxStringLength(UserId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, userIdList);
		}
		// 3.P1が半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(UserId) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, userIdList);
		}
		// P1で入力された値が既に使用されているならば、エラーメッセージを返す。
		// 使用されていなければnullを返す。
		int numUserId = checkDuplicateDAO.checkDuplicate(UserId, M_USER, COLUMN_USER_ID);
		if (numUserId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, userIdList);
		}
		return null;
	}

	private String checkInsertUserName(String userName) {
		String[] userNameList = { USER_NAME, "20" };

		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(userName) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, userNameList);
		}
		// 2. P1が20文字以内で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isMaxStringLength(userName, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, userNameList);
		}
		return null;
	}

	private String checkInsertPassword(String password) throws ClassNotFoundException, SQLException, ParseException {
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

	private String checkInsertCompany(String company) {
		String[] companyList = { USER_COMPANY };
		// 1.P1で選択されていなければ、エラーメッセージを返す。
		// 選択されていればnullを返す。
		if (lengthCheck.isNomNullOrNomEmpty(company) == false) {
			return errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, companyList);
		}
		return null;
	}

	private String checkInsertPost(String post) {
		String[] postList = { USER_POST };
		// 1.P1で選択されていなければ、エラーメッセージを返す。
		// 選択されていればnullを返す。
		if (lengthCheck.isNomNullOrNomEmpty(post) == false) {
			return errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, postList);
		}
		return null;
	}

	private String checkInsertPrivilege(String privilege) {
		String[] privilegeList = { USER_PRIVILEGE };
		// 1.P1で選択されていなければ、エラーメッセージを返す。
		// 選択されていればnullを返す。
		if (lengthCheck.isNomNullOrNomEmpty(privilege) == false) {
			return errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, privilegeList);
		}
		return null;
	}

}
