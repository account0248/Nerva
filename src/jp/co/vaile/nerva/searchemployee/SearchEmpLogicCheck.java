package jp.co.vaile.nerva.searchemployee;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;

public class SearchEmpLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
	CheckViewingAuthority checkViewingAuthority = new CheckViewingAuthority();

	public List<String> checkSerchEmp(SearchEmpPageDTO serchEmpPageDTO, String loginUserCompanyId,String companyId,String companyPrivilege) {
		List<String> errorList = new ArrayList<String>();
		//の従業員IDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSerchEmpId(serchEmpPageDTO.getEmployeeId()), errorList);
		//従業員名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
			errorList = errorMsg.errorMsgNullCheck(checkSerchEmpName(serchEmpPageDTO.getEmployeeName()), errorList);
			//担当案件のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
			errorList = errorMsg.errorMsgNullCheck(checkSerchProjectName(serchEmpPageDTO.getProjectName()), errorList);
			//所属会社のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
			errorList = errorMsg.errorMsgNullCheck(checkSerchCompanyName(loginUserCompanyId,companyId,companyPrivilege), errorList);
			//所属チームのチームにエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
			errorList = errorMsg.errorMsgNullCheck(checkSerchTeamName(serchEmpPageDTO.getTeamName()), errorList);
			//担当部長にエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
			errorList = errorMsg.errorMsgNullCheck(checkSerchTeamManager(serchEmpPageDTO.getTeamManager()), errorList);
			//スキル絞り込みにエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
			errorList = errorMsg.errorMsgNullCheck(checkSerchSkillFilter(serchEmpPageDTO.getSkillFiltering()), errorList);
		return errorList;
	}

	public String checkSerchEmp() {
		ErrorMsg errorMsg = new ErrorMsg();
		String[] searchEmpList = { CommonConstants.SEARCH_EMPLOYEE };
		String error = errorMsg.returnErrorMsg(CommonConstants.SEARCH_RESULT_EMPTY_ERROR_MESSAGE, searchEmpList);
		return error;
	}

	/**従業員IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	* エラーがない場合、nullを返す。
	* @param employeeId 従業員検索画面にに記載された従業員ID
	* @return エラーメッセージ
	*/
	public String checkSerchEmpId(String employeeId) {
		String errorContext = null;
		String[] employeeIderrorList= { CommonConstants.EMPLOYEE_ID, "10"};
		String[] employeeIdList = { CommonConstants.EMPLOYEE_ID };

		if (!lengthCheck.isNomNullOrNomEmpty(employeeId)) {
			return errorContext;
		}
		//半角英数字で入力されてない場合、エラーメッセージを返す。
		else if (!harfWidthAlphanumFormatCheck.isCorrectFormat(employeeId)) {
			errorContext= errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, employeeIdList);
		}
		//入力された値が10文字でなければ、エラーメッセージを返す。s
		else if (!lengthCheck.isStringLength(employeeId, 10)) {
			errorContext= errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, employeeIderrorList);
		}
		return errorContext;
	}
	/**
		* 従業員名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
		* エラーがない場合、nullを返す。
		* @param projectName 従業員検索画面にに記載された担当案件
		* @return　エラーメッセージ
		*/

	public String checkSerchEmpName(String employeeName) {
		String errorContext = null;
		String[] employeeNameList = { CommonConstants.EMPLOYEE_NAME,"20"};
		if (!lengthCheck.isNomNullOrNomEmpty(employeeName)) {
			return errorContext;
		}
		else if (!lengthCheck.isMaxStringLength(employeeName, 20)) {
			errorContext = errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, employeeNameList);
		}
		return errorContext;
	}

	/**
		* 担当案件が正しく入力されていない場合、エラーメッセージを戻り値として返す。
		* エラーがない場合、nullを返す。
		* @param  projectName 従業員検索画面にに記載された担当案件
		* @return エラーメッセージ
		*/

	public String checkSerchProjectName(String projectName) {
		String errorContext = null;
		String[] ProjectNameList = { CommonConstants.RESPONSIBLE_PROJECT_NAME ,"256"};
		if (!lengthCheck.isNomNullOrNomEmpty(projectName)) {
			return errorContext;
		}
		else if (!lengthCheck.isMaxStringLength(projectName, 256)) {
			errorContext = errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, ProjectNameList);
		}
		return errorContext;
	}

	/**
		* スキル絞り込みが正しく入力されていない場合、エラーメッセージを戻り値として返す。
		* エラーがない場合、nullを返す。
		* @param skillFiltering 従業員検索画面にに記載されたスキル絞り込み
		* @return エラーメッセージ
		*/

	public String checkSerchSkillFilter(String skillFiltering) {
		String errorContext = null;
		String[] skillFilterList = { CommonConstants.SKILL_FILTERING ,"256"};
		if (!lengthCheck.isNomNullOrNomEmpty(skillFiltering)) {
			return errorContext;
		}
		else if (!lengthCheck.isMaxStringLength(skillFiltering, 256)) {
			errorContext = errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, skillFilterList);
		}
		return errorContext;
	}

	/**
		* 所属チームが正しく入力されていない場合、エラーメッセージを戻り値として返す。
		* エラーがない場合、nullを返す。
		* @param teamName 従業員検索画面にに記載された所属チーム
		* @return エラーメッセージ
		*/

	public String checkSerchTeamName(String teamName) {
		String errorContext = null;
		String[] TeamNameList = { CommonConstants.TEAM_NAME ,"20"};
		if (!lengthCheck.isNomNullOrNomEmpty(teamName)) {
			return errorContext;
		}
		else if (!lengthCheck.isMaxStringLength(teamName, 20)) {
			errorContext = errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, TeamNameList);
		}
		return errorContext;
	}

	/**
		* 担当部長が正しく入力されていない場合、エラーメッセージを戻り値として返す。
		* エラーがない場合、nullを返す。
		* @param teamManager 従業員検索画面にに記載された担当部長
		* @return エラーメッセージ
		*/

	public String checkSerchTeamManager(String teamManager) {
		String errorContext = null;
		String[] TeamManagerList = { CommonConstants.TEAM_MANAGER ,"20"};
		if (!lengthCheck.isNomNullOrNomEmpty(teamManager)) {
			return errorContext;
		}
		else if (!lengthCheck.isMaxStringLength(teamManager, 20)) {
			errorContext = errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, TeamManagerList);
		}
		return errorContext;
	}

	/**
	   * 所属会社が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	   * エラーがない場合、nullを返す。
	   * @param companyName 従業員検索画面に記載された所属会社
	   * @return エラーメッセージ
	   */
	public String checkSerchCompanyName(String loginUserCompanyId, String companyId,String companyPrivilege) {
		String errorContext = null;
		String[] belongCompanyList = { CommonConstants.BELONGC_COMPANY_NAME };

		if (!checkViewingAuthority.checkViewingAuthority(loginUserCompanyId,companyPrivilege, companyId)) {
			errorContext = errorMsg.returnErrorMsg(CommonConstants.VALIDITY_SELECT_ERROR_MESSAGE, belongCompanyList);
		}
		return errorContext;

	}

}
