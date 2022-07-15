package jp.co.vaile.nerva.masterIndustry.searchIndustry;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchIndustryLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param searchIndustryDTO
	 * @return errorList
	 */
	public List<String> checkSearchIndustry(SearchIndustryDTO searchIndustryDTO) {
		List<String> errorList = new ArrayList<String>();
		// 業種IDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchIndustryId(searchIndustryDTO.getIndustryId()), errorList);
		// 業種名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchIndustryName(searchIndustryDTO.getIndustryName()), errorList);

		return errorList;
	}

	/**
	 * 業種IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param industryId 業種マスタメンテナンス画面に記載された業種ID
	 * @return エラーメッセージ
	 */
	private String checkSearchIndustryId(String industryId) {

		String[] industryIderrorList = { CommonConstants.INDUSTRY_ID, "10" };
		String[] industryIdList = { CommonConstants.INDUSTRY_ID };

		// 未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(industryId)) {
			return null;
		}
		// 半角英数字で入力されてない場合、エラーメッセージを返す。
		else if (!harfWidthAlphanumFormatCheck.isCorrectFormat(industryId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, industryIdList);
		}
		// 入力された値が10文字でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isStringLength(industryId, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, industryIderrorList);
		}
		return null;
	}

	/**
	 * 業種名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param industryName 業種マスタメンテナンス画面に記載された業種名
	 * @return エラーメッセージ
	 */

	private String checkSearchIndustryName(String industryName) {
		String[] industryNameList = { CommonConstants.INDUSTRY_NAME, "10" };
		
		// 未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(industryName)) {
			return null;
		}
		// 入力された値が10文字以内でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isMaxStringLength(industryName, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, industryNameList);
		}
		return null;
	}

}