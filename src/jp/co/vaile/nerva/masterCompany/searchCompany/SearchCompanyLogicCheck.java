package jp.co.vaile.nerva.masterCompany.searchCompany;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchCompanyLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * @param searchCompanyDTO
	 * @return errorList
	 */
	public List<String> checkSearchCompany(SearchCompanyDTO searchCompanyDTO) {
		List<String> errorList = new ArrayList<String>();
		//所属会社IDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchCompanyId(searchCompanyDTO.getCompanyId()), errorList);
		//所属会社名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchCompanyName(searchCompanyDTO.getCompanyName()), errorList);

		return errorList;
	}


	/**所属会社IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param employeeId 所属会社マスタメンテナンス画面に記載された所属会社ID
	 * @return エラーメッセージ
	 */
	public String checkSearchCompanyId(String companyId) {

		String[] companyIderrorList= { CommonConstants.COMPANY_ID, "10"};
		String[] companyIdList = { CommonConstants.COMPANY_ID };

		if (!lengthCheck.isNomNullOrNomEmpty(companyId)) {
			return null;
		}

		//入力された値が10文字でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isStringLength(companyId, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, companyIderrorList);
		}
		//半角英数字で入力されてない場合、エラーメッセージを返す。
		else if (!harfWidthAlphanumFormatCheck.isCorrectFormat(companyId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, companyIdList);
		}

		return null;
	}
	/**
	 * 所属会社名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param projectName 所属会社マスタメンテナンス画面に記載された所属会社名
	 * @return　エラーメッセージ
	 */

	public String checkSearchCompanyName(String companyName) {
		//入力された値が10文字以内でなければ、エラーメッセージを返す。
		String[] companyNameList = { CommonConstants.COMPANY_NAME,"10"};
		if (!lengthCheck.isNomNullOrNomEmpty(companyName)) {
			return null;
		}
		else if (!lengthCheck.isMaxStringLength(companyName, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, companyNameList);
		}
		return null;
	}

}