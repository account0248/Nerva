package jp.co.vaile.nerva.masterPost.searchPost;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchPostLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param searchPostDTO 
	 * @return errorList
	 */
	public List<String> checkSearchPost(SearchPostDTO searchPostDTO) {
		List<String> errorList = new ArrayList<String>();
		// 役職IDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchDepartmentId(searchPostDTO.getPostId()), errorList);
		// 役職名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchPostName(searchPostDTO.getPostName()), errorList);

		return errorList;
	}

	/**
	 * 役職IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param postId 
	 * @return エラーメッセージ
	 */
	private String checkSearchDepartmentId(String postId) {

		String[] postIderrorList = { CommonConstants.POST_ID, "10" };
		String[] postIdList = { CommonConstants.POST_ID };

		// 未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(postId)) {
			return null;
		}
		// 半角英数字で入力されてない場合、エラーメッセージを返す。
		if (!harfWidthAlphanumFormatCheck.isCorrectFormat(postId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, postIdList);
		}
		// 入力された値が10文字でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isStringLength(postId, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, postIderrorList);
		}
		return null;
	}

	/**
	 * 役職名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param postName 
	 * @return エラーメッセージ
	 */
	private String checkSearchPostName(String postName) {

		String[] postNameList = { CommonConstants.POST_NAME, "5" };

		// 未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(postName)) {
			return null;
		}
		// 入力された値が5文字以内でなければ、エラーメッセージを返す。
		if (!lengthCheck.isMaxStringLength(postName, 5)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, postNameList);
		}
		return null;
	}
}