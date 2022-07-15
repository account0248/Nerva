package jp.co.vaile.nerva.masterDepartment.insertDepartment;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DepartmentIDFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class InsertDepartmentCheckAction {

	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param insertDepartmentDTO
	 * @return errorList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	public List<String> checkInsertDepartment(InsertDepartmentDTO insertDepartmentDTO)
			throws ClassNotFoundException, SQLException, ParseException {
		List<String> errorList = new ArrayList<String>();
		// 1.P1の所属組織IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertDepartmentId(insertDepartmentDTO.getDepartmentId()),
				errorList);
		// 2.P1の所属組織名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertDepartmentName(insertDepartmentDTO.getDepartmentName()),
				errorList);
		return errorList;
	}

	/**
	 * 所属組織IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。エラーがない場合、nullを返す。
	 * 
	 * @param departmentId
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	private String checkInsertDepartmentId(String departmentId)
			throws ClassNotFoundException, SQLException, ParseException {
		String[] departmentIdList = { DEPARTMENT_ID, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(departmentId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, departmentIdList);
		}
		// 2. P1が10文字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isStringLength(departmentId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, departmentIdList);
		}
		// 3.P1が半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(departmentId) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, departmentIdList);
		}
		/*
		 * 4. P1が形式通りに入力されていない場合、エラーリストにエラーメッセージを追加する
		 */
		DepartmentIDFormatCheck departmentIDFormatCheck = new DepartmentIDFormatCheck();
		if (!departmentIDFormatCheck.isCorrectFormat(departmentId)) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, departmentIdList);
		}
		// 重複チェック
		int numDepartmentId = checkDuplicateDAO.checkDuplicate(departmentId, M_BELONG_DEPARTMENT, COLUMN_DEPARTMENT_ID);
		if (numDepartmentId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, departmentIdList);
		}
		return null;
	}

	/**
	 * 所属組織名が正しく入力されていない場合、エラーメッセージを戻り値として返す。エラーがない場合、nullを返す。
	 * 
	 * @param departmentName
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	private String checkInsertDepartmentName(String departmentName)
			throws ClassNotFoundException, SQLException, ParseException {
		String[] departmentNameList = { DEPARTMENT_NAME, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(departmentName) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, departmentNameList);
		}
		// 2. P1が10文字以下で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isMaxStringLength(departmentName, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, departmentNameList);
		}
		// 重複チェック
		int numDepartmentName = checkDuplicateDAO.checkDuplicate(departmentName, M_BELONG_DEPARTMENT,
				COLUMN_DEPARTMENT_NAME);
		if (numDepartmentName != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, departmentNameList);
		}
		return null;
	}
}
