package jp.co.vaile.nerva.masterIndustry.insertIndustry;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.MasterContents;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.IndustryIDFormatCheck;

public class InsertIndustryLogicCheckAction{
	
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();	
	
	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param insertIndustryDTO 
	 * @return errorList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	public List<String> checkInsertIndustry(InsertIndustryDTO insertIndustryDTO) throws ClassNotFoundException, SQLException {
		List<String> errorList = new ArrayList<String>();
		// 1.P1の業種IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertIndustryId(insertIndustryDTO.getIndustryId()), errorList);
		// 2.P1の業種名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertIndustryName(insertIndustryDTO.getIndustryName()), errorList);
		return errorList;
	}
	
	/**
	 * 業種IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。エラーがない場合、nullを返す。
	 * 
	 * @param industryId 
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	
	private String checkInsertIndustryId (String industryId) throws ClassNotFoundException, SQLException {
		String[] industryIdList = { CommonConstants.INDUSTRY_ID, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(industryId) == false) {
			return errorMsg.returnErrorMsg( CommonConstants.REQUIRED_ERROR_MESSAGE, industryIdList);
		}
		// 2. P1が10文字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (lengthCheck.isStringLength(industryId, 10) == false) {
			return errorMsg.returnErrorMsg( CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, industryIdList);
		}
		// 3.P1が半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(industryId) == false) {
			return errorMsg.returnErrorMsg( CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, industryIdList);
		}
		//4. P1が形式通りに入力されていない場合、エラーメッセージを返す。
		//入力されている場合5に進む。
		IndustryIDFormatCheck industryIDFormatCheck = new IndustryIDFormatCheck();
		if (!industryIDFormatCheck.isCorrectFormat(industryId)) {
			return errorMsg.returnErrorMsg( CommonConstants.FORMAT_INPUT_ERROR_MESSAGE, industryIdList);
		}
		//5.P1で入力された値が既に使用されているならば、エラーメッセージを返す。
		//使用されていなければnullを返す。
		int numIndustryId = checkDuplicateDAO.checkDuplicate(industryId, MasterContents.M_INDUSTRY, MasterContents.COLUMN_INDUSTRY_ID);
		if (numIndustryId != 0) {
			return errorMsg.returnErrorMsg( CommonConstants.ID_EXIST_ERROR_MESSAGE, industryIdList);
		}
		return null;
	}
	
	/**
	 * 業種名が正しく入力されていない場合、エラーメッセージを戻り値として返す。エラーがない場合、nullを返す。
	 * 
	 * @param industryName 
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	private String checkInsertIndustryName (String industryName) throws ClassNotFoundException, SQLException {
		String[] industryNameList = {  CommonConstants.INDUSTRY_NAME, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(industryName) == false) {
			return errorMsg.returnErrorMsg( CommonConstants.REQUIRED_ERROR_MESSAGE, industryNameList);
		}
		// 2.P1が10文字以下で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (lengthCheck.isMaxStringLength(industryName, 10) == false) {
			return errorMsg.returnErrorMsg( CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, industryNameList);
		}
		// 3.P1で入力された値が既に使用されているならば、エラーメッセージを返す。
		//使用されていなければnullを返す。
		int numIndustryId = checkDuplicateDAO.checkDuplicate(industryName, MasterContents.M_INDUSTRY, MasterContents.COLUMN_INDUSTRY_NAME);
		if (numIndustryId != 0) {
			return errorMsg.returnErrorMsg( CommonConstants.ID_EXIST_ERROR_MESSAGE, industryNameList);
		}
		return null;
	}
}
