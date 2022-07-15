package jp.co.vaile.nerva.commonprocess;

import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class CheckMasterResult {
	
	/**
	 * 検索結果が0件の場合、エラーメッセージを戻り値として返す。
	 * 
	 * @param checkMasterNane
	 * @return error
	 */
	public String checkMasterResult(String checkMasterNane) {
		ErrorMsg errorMsg = new ErrorMsg();

		//変数checkMasterNaneと定数SEARCH_RESULT_EMPTY_ERROR_MESSAGEを引数にエラーメッセージ取得クラスを呼び出す。
		String error = errorMsg.returnErrorMsg(CommonConstants.SEARCH_RESULT_EMPTY_ERROR_MESSAGE,  checkMasterNane);
		return error;
	}

}
