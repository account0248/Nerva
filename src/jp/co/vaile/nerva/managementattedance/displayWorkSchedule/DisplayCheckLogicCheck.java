package jp.co.vaile.nerva.managementattedance.displayWorkSchedule;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class DisplayCheckLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * エラーチェック処理を呼び出すし、エラーメッセージを返す
	 * @param year
	 * @param month
	 * @return errorList
	 */
	public List<String> checkDisplay(String year, String month) {
		List<String> errorList = new ArrayList<>();
		
		//エラーチェック処理の呼び出し
		errorList = errorMsg.errorMsgNullCheck(checkYear(year), errorList);
		errorList = errorMsg.errorMsgNullCheck(checkMonth(month), errorList);

		return errorList;
	}

	/**
	 * 年度が選択されているかチェックを行い、
	 * エラーメッセージを戻り値として返す。
	 * @param year
	 * @return errorMsg
	 */
	private String checkYear(String year) {
		String[] errorYearList = { CommonConstants.ATTENDANCE_MANAGEMENT_YEAR };
		boolean valueFlg = true;
		//エラーチェック
		valueFlg = lengthCheck.isNomNullOrNomEmpty(year);
		
		//エラーの場合エラーメッセージを返す
		if (!valueFlg) {
			
			return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_SELECT_ERROR_MESSAGE, errorYearList);
		}
		return null;
	}

	/**
	 * 月が選択されているかチェックを行い、
	 * エラーメッセージを戻り値として返す。
	 * @param month
	 * @return errorMsg
	 */
	private String checkMonth(String month) {
		String[] errorMonthList = { CommonConstants.ATTENDANCE_MANAGEMENT_MONTH };
		boolean valueFlg = true;
		//エラーチェック
		valueFlg = lengthCheck.isNomNullOrNomEmpty(month);
		
		//エラーの場合エラーメッセージを返す
		if (!valueFlg) {
			
			return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_SELECT_ERROR_MESSAGE, errorMonthList);
		}
		return null;
	}
}
