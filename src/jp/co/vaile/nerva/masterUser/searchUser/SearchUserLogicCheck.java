package jp.co.vaile.nerva.masterUser.searchUser;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchUserLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();

	/**
	 * ユーザーマスタメンテナンス画面で入力された値のエラーチェック処理を行い、エラーメッセージをリストに格納して返す。
	 * 
	 * @param searchUserDTO
	 * @return errorList エラーメッセージのリスト
	 */
	public List<String> checkSearchUser(SearchUserDTO searchUserDTO) {
		List<String> errorList = new ArrayList<String>();

		// ユーザーIDのエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchUserId(searchUserDTO.getUserId()), errorList);

		// ユーザー名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchUserName(searchUserDTO.getUserName()), errorList);

		return errorList;
	}

	/**
	 * ユーザーIDが正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param userId ユーザーマスタメンテナンス画面に記載されたユーザーID
	 * @return エラーメッセージ
	 */
	private String checkSearchUserId(String userId) {
		String[] userIderrorList = { CommonConstants.USER_ID, "10" };
		String[] userIdList = { CommonConstants.USER_ID };

		// 入力値が存在しない場合、エラーチェックを行わない。
		if (!lengthCheck.isNomNullOrNomEmpty(userId)) {
			return null;
		}

		// 入力された値が10文字以内でなければ、エラーメッセージを返す。
		if (!lengthCheck.isMaxStringLength(userId, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, userIderrorList);
		}

		// 半角英数字で入力されてない場合、エラーメッセージを返す。
		if (!harfWidthAlphanumFormatCheck.isCorrectFormat(userId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, userIdList);
		}
		return null;
	}

	/**
	 * ユーザー名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param userName ユーザーマスタメンテナンス画面に記載されたユーザー名
	 * @return エラーメッセージ
	 */
	private String checkSearchUserName(String userName) {
		String[] userNameList = { CommonConstants.USER_NAME, "20" };

		// 入力値が存在しない場合、エラーチェックを行わない。
		if (!lengthCheck.isNomNullOrNomEmpty(userName)) {
			return null;
		}

		// 入力された値が20文字以内でなければ、エラーメッセージを返す。
		if (!lengthCheck.isMaxStringLength(userName, 20)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, userNameList);
		}
		return null;
	}

}