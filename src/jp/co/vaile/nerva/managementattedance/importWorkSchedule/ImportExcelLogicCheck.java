package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.ExcelFileNameFormatCheck;

public class ImportExcelLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();

	/**
	 * ファイル名がフォーマット通りかチェックを行い、
	 * 正しくない場合エラーリストに追加し、戻り値とする。
	 * @param fileName
	 * @param file
	 * @param password
	 * @return errorList
	 * @throws GeneralSecurityException
	 */
	public List<String> checkImportExcel(String fileName, Part file, String password)
			throws GeneralSecurityException {
		List<String> errorList = new ArrayList<>();
		//エラーチェック処理を呼び出す
		errorList = errorMsg.errorMsgNullCheck(checkExcelFile(fileName, file, password), errorList);
		return errorList;
	}

	/**
	 * ファイル名がフォーマットでない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param fileName
	 * @param file
	 * @param password
	 * @return errorMsg
	 * @throws GeneralSecurityException
	 */
	private String checkExcelFile(String fileName, Part file, String password)
			throws GeneralSecurityException {
		FormatCheck excelFileNameFormatCheck = new ExcelFileNameFormatCheck();
		ImportExcelService importExcelService = new ImportExcelService();
	
		//エラーチェック
		boolean flg = true;
		flg = excelFileNameFormatCheck.isCorrectFormat(fileName);
		//エラーの場合エラーメッセージを返す
		if (!flg)
			return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);

		//エラーチェック
		flg = importExcelService.openExcelCheck(file, password);
		//エラーの場合エラーメッセージを返す
		if (!flg)
			return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);

		return null;
	}

}
