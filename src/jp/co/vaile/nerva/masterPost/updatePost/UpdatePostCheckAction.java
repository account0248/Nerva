package jp.co.vaile.nerva.masterPost.updatePost;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class UpdatePostCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param updatePostDTO 
	 * @return errorList
	 */
	public List<String> checkUpdatePost(UpdatePostDTO updatePostDTO) {
		List<String> errorList = new ArrayList<String>();
		// 所属組織名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdatePostName(updatePostDTO.getPostName()),
				errorList);
		return errorList;
	}

	/**
	 * 役職名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param postName
	 * @return エラーメッセージ
	 */
	public String checkUpdatePostName(String[] postName) {
		String[] postNameErrorList = { CommonConstants.POST_NAME, "5" };
		
		//配列postNameの要素数分for分内の処理を繰り返す。
		for (int i = 0; i < postName.length; i++) {
			// 未入力の場合、エラーメッセージを返す。
			if (!lengthCheck.isNomNullOrNomEmpty(postName[i])) {
				return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, postNameErrorList);
			}
			// 入力された値が5文字以内でなければ、エラーメッセージを返す。
			else if (!lengthCheck.isMaxStringLength(postName[i], 5)) {
				return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, postNameErrorList);
			}
			// 重複処理
			CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
			int numPostName = 0;
			try {
				numPostName = checkDuplicateDAO.checkDuplicate(postName[i], M_POST, 
						COLUMN_POST_NAME);
			} catch (ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if (numPostName != 0) {
				return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, postNameErrorList);
			}
		}
		return null;
	}
}
