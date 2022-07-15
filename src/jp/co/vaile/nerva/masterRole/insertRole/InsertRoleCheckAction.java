package jp.co.vaile.nerva.masterRole.insertRole;

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
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.RoleIDFormatCheck;

public class InsertRoleCheckAction{
	
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
	
	public List<String> checkInsertRole(InsertRoleDTO insertRoleDTO) throws ClassNotFoundException, SQLException, ParseException {
		List<String> errorList = new ArrayList<String>();
		// 1.担当IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertRoleId(insertRoleDTO.getRoleId()), errorList);
		// 2.担当名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertRoleName(insertRoleDTO.getRoleName()), errorList);
		return errorList;
	}
	
	private String checkInsertRoleId (String roleId) throws ClassNotFoundException, SQLException, ParseException {
		String[] roleIdList = { ROLE_ID, "10" };
		// 1.担当IDが入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(roleId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, roleIdList);
		}
		// 2. 担当IDが10文字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isStringLength(roleId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, roleIdList);
		}
		// 3.担当IDが半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(roleId) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, roleIdList);
		}
		/*
		 * 4. 担当IDが形式通りに入力されていない場合、エラーリストにエラーメッセージを追加する 
		 */
		RoleIDFormatCheck roleIDFormatCheck = new RoleIDFormatCheck();
		if (!roleIDFormatCheck.isCorrectFormat(roleId)) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, roleIdList);
		}
		//重複チェック
		int numRoleId = checkDuplicateDAO.checkDuplicate(roleId, M_ROLE, COLUMN_ROLE_ID);
		if (numRoleId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, roleIdList);
		}
		return null;
	}
	
	private String checkInsertRoleName (String roleName) throws ClassNotFoundException, SQLException, ParseException {
		String[] roleNameList = { ROLE_NAME, "10" };
		// 1.担当名が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(roleName) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, roleNameList);
		}
		// 2. 担当名が10文字以下で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isMaxStringLength(roleName, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, roleNameList);
		}
		//重複チェック
		int numRoleName = checkDuplicateDAO.checkDuplicate(roleName, M_ROLE, COLUMN_ROLE_NAME);
		if (numRoleName != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, roleNameList);
		}
		return null;
	}
}
