package jp.co.vaile.nerva.masterDepartment.searchDepartment;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchDepartmentLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param searchDepartmentDTO
	 * @return errorList
	 */
	public List<String> checkSearchDepartment(SearchDepartmentDTO searchDepartmentDTO) {
		List<String> errorList = new ArrayList<String>();
		// の従業員IDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchDepartmentId(searchDepartmentDTO.getDepartmentId()),
				errorList);
		// 従業員名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchDepartmentName(searchDepartmentDTO.getDepartmentName()),
				errorList);

		return errorList;
	}

	/**
	 * 所属組織IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param departmentId
	 * @return エラーメッセージ
	 */
	private String checkSearchDepartmentId(String departmentId) {

		String[] departmentIderrorList = { CommonConstants.DEPARTMENT_ID, "10" };
		String[] departmentIdList = { CommonConstants.DEPARTMENT_ID };

		// 未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(departmentId)) {
			return null;
		}
		// 半角英数字で入力されてない場合、エラーメッセージを返す。
		else if (!harfWidthAlphanumFormatCheck.isCorrectFormat(departmentId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, departmentIdList);
		}
		// 入力された値が10文字でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isStringLength(departmentId, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, departmentIderrorList);
		}
		return null;
	}

	/**
	 * 所属組織名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param departmentName
	 * @return エラーメッセージ
	 */
	private String checkSearchDepartmentName(String departmentName) {

		String[] departmentNameList = { CommonConstants.DEPARTMENT_NAME, "10" };

		// 未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(departmentName)) {
			return null;
		}
		// 入力された値が10文字以内でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isMaxStringLength(departmentName, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, departmentNameList);
		}
		return null;
	}
}