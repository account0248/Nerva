package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TextFileNameFormatCheck;

/**
 * 
 * @author konishi.tokinori
 *
 */
public class ImportTextLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();

	/**
	 * エラーチェックを呼び出しエラーリストを返す
	 * 
	 * @param fileName
	 * @return errorList
	 */
	public List<String> checkImportText(String fileName) {
		List<String> errorList = new ArrayList<>();
		//エラーチェック処理呼び出し
		errorList = errorMsg.errorMsgNullCheck(checkTextFileName(fileName), errorList);

		return errorList;
	}

	/**
	 * ファイル名がフォーマットでない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param fileName
	 * @return errorMsg
	 */
	private String checkTextFileName(String fileName) {
		// インスタンス生成
		FormatCheck textFileNameFormatCheck = new TextFileNameFormatCheck();
		boolean flg = true;
		// ファイル名チェック
		flg = textFileNameFormatCheck.isCorrectFormat(fileName);

		if (!flg)
			// エラーメッセージを返す
			return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);

		return null;
	}
}
