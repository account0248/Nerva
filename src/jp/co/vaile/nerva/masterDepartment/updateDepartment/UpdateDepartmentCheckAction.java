package jp.co.vaile.nerva.masterDepartment.updateDepartment;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class UpdateDepartmentCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param updateDepartmentDTO
	 * @return errorList
	 **/
	public List<String> checkUpdateDepartment(UpdateDepartmentDTO updateDepartmentDTO) {
		List<String> errorList = new ArrayList<String>();
		// 所属組織名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdateDepartmentName(updateDepartmentDTO.getDepartmentName()),
				errorList);
		return errorList;
	}

	/**
	 * 所属組織名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param departmentName
	 * @return エラーメッセージ
	 **/
	private String checkUpdateDepartmentName(String[] departmentName) {

		String[] departmentNameErrorList = { CommonConstants.DEPARTMENT_NAME, "10" };

		// 配列departmentNameの要素数分for文内の処理を実行する。
		for (int i = 0; i < departmentName.length; i++) {
			// 未入力の場合、エラーメッセージを返す。
			if (!lengthCheck.isNomNullOrNomEmpty(departmentName[i])) {
				return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, departmentNameErrorList);
			}
			// 入力された値が10文字以内でなければ、エラーメッセージを返す。
			else if (!lengthCheck.isMaxStringLength(departmentName[i], 10)) {
				return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, departmentNameErrorList);
			}
			// 重複処理
			CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
			int numDepartmentName = 0;
			try {
				numDepartmentName = checkDuplicateDAO.checkDuplicate(departmentName[i], M_BELONG_DEPARTMENT,
						COLUMN_DEPARTMENT_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (numDepartmentName != 0) {
				return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, departmentNameErrorList);
			}
			for (int j = 0; j < departmentName.length; j++) {
				if (i != j && departmentName[i].equals(departmentName[j])) {
					return errorMsg.returnErrorMsg(DUPLICATE_INPUT_ERROR_MESSAGE, departmentNameErrorList);
				}
			}
		}
		return null;
	}
}
