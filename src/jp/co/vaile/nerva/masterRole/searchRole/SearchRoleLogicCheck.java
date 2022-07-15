package jp.co.vaile.nerva.masterRole.searchRole;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchRoleLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();

	public List<String> checkSearchRole(SearchRoleDTO searchRoleDTO) {
		List<String> errorList = new ArrayList<String>();
		//の従業員IDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchRoleId(searchRoleDTO.getRoleId()), errorList);
		//従業員名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchRoleName(searchRoleDTO.getRoleName()), errorList);

		return errorList;
	}
	/**担当IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param employeeId 担当マスタメンテナンス画面に記載された担当ID
	 * @return エラーメッセージ
	 */
	private String checkSearchRoleId(String roleId) {

		String[] RoleIderrorList= { CommonConstants.ROLE_ID, "10"};
		String[] RoleIdList = { CommonConstants.ROLE_ID };

		if (!lengthCheck.isNomNullOrNomEmpty(roleId)) {
			return null;
		}
		//入力された値が10文字でなければ、エラーメッセージを返す。
				else if (!lengthCheck.isStringLength(roleId, 10)) {
					return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, RoleIderrorList);
				}
		//半角英数字で入力されてない場合、エラーメッセージを返す。
		else if (!harfWidthAlphanumFormatCheck.isCorrectFormat(roleId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, RoleIdList);
		}
		return null;
	}
	/**
	 * 担当名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param projectName 担当マスタメンテナンス画面に記載された担当名
	 * @return　エラーメッセージ
	 */

	private String checkSearchRoleName(String RoleName) {
		
		//入力された値が10文字以内でなければ、エラーメッセージを返す。
		String[] RoleNameList = { CommonConstants.ROLE_NAME,"10"};
		if (!lengthCheck.isNomNullOrNomEmpty(RoleName)) {
			return null;
		}
		else if (!lengthCheck.isMaxStringLength(RoleName, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, RoleNameList);
		}
		return null;
	}

}