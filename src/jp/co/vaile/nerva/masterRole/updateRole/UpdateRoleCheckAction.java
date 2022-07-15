package jp.co.vaile.nerva.masterRole.updateRole;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
public class UpdateRoleCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	public List<String> checkUpdateRole(UpdateRoleDTO updateRoleDTO) {
		List<String> errorList = new ArrayList<String>();
		// 担当名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdateRoleName(updateRoleDTO.getRoleName()),
				errorList);
		return errorList;
	}

	/**
	 * 担当名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param RoleName 担当マスタメンテナンス画面の更新フォームで入力された担当名
	 * @return エラーメッセージ
	 */
	private String checkUpdateRoleName(String[] roleName) {

		String[] roleNameErrorList = { CommonConstants.ROLE_NAME, "10" };

		for (int i = 0; i < roleName.length; i++) {
			// 未入力の場合、エラーメッセージを返す。
			if (!lengthCheck.isNomNullOrNomEmpty(roleName[i])) {
				return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, roleNameErrorList);
			}
			// 入力された値が10文字以内でなければ、エラーメッセージを返す。
			else if (!lengthCheck.isMaxStringLength(roleName[i], 10)) {
				return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, roleNameErrorList);
			}
			// 重複処理
			CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
			int numRoleName = 0;
			try {
	
				numRoleName = checkDuplicateDAO.checkDuplicate(roleName[i],M_ROLE, COLUMN_ROLE_NAME);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if (numRoleName != 0) {
				return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, roleNameErrorList);
			}
		}
		return null;
	}
}
