package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ExcelFileNameFormatCheck;

public class ImportCheckLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	ExcelFileNameFormatCheck fileNameFormatCheck = new ExcelFileNameFormatCheck();
	/**
	 * ファイル選択がされているかチェックを行い、
	 * 正しくない場合エラーリストに追加し、戻り値とする。
	 * @param fileName
	 * @return errorList
	 */
	public List<String> checkImport(String fileName){
		List<String> errorList = new ArrayList<>();
		//エラーチェック処理呼び出し
		errorList = errorMsg.errorMsgNullCheck(checkFileChoice(fileName),errorList);
		errorList = errorMsg.errorMsgNullCheck(checkFileName(fileName),errorList);
		
		return errorList;
	}

	/**
	 * ファイル名が0文字の場合エラーメッセージを返し、0文字より多い場合nullを返す
	 * @param fileName
	 * @return errorMsg
	 */
	private String checkFileChoice(String fileName) {
		boolean fileFlg = true;
		fileFlg = lengthCheck.isNomNullOrNomEmpty(fileName);
		String[] errorFile = {CommonConstants.ATTENDANCE_MANAGEMENT_FILE};
		
		//エラーの場合、エラーメッセージを返す
		if(!fileFlg) {
			
			return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_SELECT_ERROR_MESSAGE, errorFile);
		}
		
		return null;
	}
	
	/**
	 * ファイルがExcelまたはテキストの場合nullを返し、そうでない場合エラーメッセージを返す
	 * @param fileName
	 * @return errorMsg
	 */
	private String checkFileName(String fileName) {
		int strIndex = fileName.indexOf(".");
		//拡張子切り取り
		String extension = fileName.substring(strIndex+1);
		//拡張子を判定する
		if(!(extension.equals("xlsx")||extension.equals("text"))){
			
			return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
		}
		
		return null;
	}
}
