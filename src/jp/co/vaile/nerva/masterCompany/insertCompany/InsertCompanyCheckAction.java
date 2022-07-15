package jp.co.vaile.nerva.masterCompany.insertCompany;

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
import jp.co.vaile.nerva.commonprocess.formatchecksub.CompanyIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class InsertCompanyCheckAction{
	
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CommonConstants commonConstants = new CommonConstants();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();	
	
	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * @param insertCompanyDTO
	 * @return errorList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public List<String> checkInsertCompany(InsertCompanyDTO insertCompanyDTO) throws ClassNotFoundException, SQLException, ParseException{
		List<String> errorList = new ArrayList<String>();
		// 1.P1の所属会社IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertCompanyId(insertCompanyDTO.getCompanyId()), errorList);
		// 2.P1の所属会社名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertCompanyName(insertCompanyDTO.getCompanyName()), errorList);
		// 3.P1のグループのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertCompanyGroup(insertCompanyDTO.getCompanyGroup()), errorList);
		// 4.P1の会社識別コードのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertCompanyCode(insertCompanyDTO.getCompanyCode()), errorList);		
		return errorList;
	}
	
	/**
	 * 所属会社IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * @param companyId
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String checkInsertCompanyId (String companyId) throws ClassNotFoundException, SQLException, ParseException {
		String[] companyIdList = { COMPANY_ID, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(companyId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, companyIdList);
		}
		// 2. P1が10文字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (lengthCheck.isStringLength(companyId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, companyIdList);
		}
		// 3.P1が半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(companyId) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, companyIdList);
		}
		//4. P1が形式通りに入力されていない場合、エラーメッセージを返す。
		//入力されている場合5に進む。
		CompanyIDFormatCheck companyIDFormatCheck = new CompanyIDFormatCheck();
		if (!companyIDFormatCheck.isCorrectFormat(companyId)) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, companyIdList);
		}
		//5.P1で入力された値が既に使用されているならば、エラーメッセージを返す。
		//使用されていなければnullを返す。
		int numCompanyId = checkDuplicateDAO.checkDuplicate(companyId, M_BELONG_COMPANY, COLUMN_COMPANY_ID);
		if (numCompanyId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, companyIdList);
		}
		return null;
	}
	
	/**
	 * 所属会社名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * @param companyName
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String checkInsertCompanyName (String companyName) throws ClassNotFoundException, SQLException, ParseException {
		String[] companyNameList = { COMPANY_NAME, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(companyName) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, companyNameList);
		}
		// 2. P1が10文字以下で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (lengthCheck.isMaxStringLength(companyName, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, companyNameList);
		}
		// 3.P1で入力された値が既に使用されているならば、エラーメッセージを返す。
		//使用されていなければnullを返す。
		int numCompanyName = checkDuplicateDAO.checkDuplicate(companyName, M_BELONG_COMPANY, COLUMN_COMPANY_NAME);
		if (numCompanyName != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, companyNameList);
		}
		return null;
	}
	/**
	 * グループが正しく選択されていない場合、エラーメッセージを戻り値として返す。
	 * @param companyGroup
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String checkInsertCompanyGroup (String companyGroup) throws ClassNotFoundException, SQLException, ParseException {
		String[] companyNameList = {GROUP_ID};
		if (!lengthCheck.isNomNullOrNomEmpty(companyGroup)) {
			return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, companyNameList);
		}
		return null;
	}
	
	
	
	/**
	 * 会社識別コードが正しく選択されていない場合、エラーメッセージを戻り値として返す。 
	 * @param companyCode
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String checkInsertCompanyCode (String companyCode) throws ClassNotFoundException, SQLException, ParseException {
		String[] companyNameList = {COMPANYCODE};
		if (!lengthCheck.isNomNullOrNomEmpty(companyCode)) {
			return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, companyNameList);
		}
		return null;
	}
}
