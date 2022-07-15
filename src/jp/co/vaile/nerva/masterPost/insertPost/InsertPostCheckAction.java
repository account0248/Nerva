package jp.co.vaile.nerva.masterPost.insertPost;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.PostIDFormatCheck;

public class InsertPostCheckAction{
	
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
	
	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * 
	 * @param insertPostDTO
	 * @return errorList
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public List<String> checkInsertPost(InsertPostDTO insertPostDTO) throws ClassNotFoundException, SQLException, ParseException {
		List<String> errorList = new ArrayList<String>();
		// 1.P1の担当IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertPostId(insertPostDTO.getPostId()), errorList);
		// 2.P1の担当名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkInsertPostName(insertPostDTO.getPostName()), errorList);
		return errorList;
	}
	
	/**
	 * 役職IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。エラーがない場合、nullを返す。
	 * 
	 * @param postId
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String checkInsertPostId (String postId) throws ClassNotFoundException, SQLException, ParseException {
		String[] postIdList = { ROLE_ID, "10" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(postId) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, postIdList);
		}
		// 2. P1が10文字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isStringLength(postId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, postIdList);
		}
		// 3.P1が半角英数字で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合3に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(postId) == false) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, postIdList);
		}
		/*
		 * 4. P1が形式通りに入力されていない場合、エラーリストにエラーメッセージを追加する 
		 */
		PostIDFormatCheck postIDFormatCheck = new PostIDFormatCheck();
		if (!postIDFormatCheck.isCorrectFormat(postId)) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, postIdList);
		}
		//重複チェック
		int numPostId = checkDuplicateDAO.checkDuplicate(postId, M_BELONG_DEPARTMENT, COLUMN_DEPARTMENT_ID);
		if (numPostId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, postIdList);
		}
		return null;
	}
	
	/**
	 * 役職名が正しく入力されていない場合、エラーメッセージを戻り値として返す。エラーがない場合、nullを返す。
	 * 
	 * @param postName
	 * @return エラーメッセージ
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String checkInsertPostName (String postName) throws ClassNotFoundException, SQLException, ParseException {
		String[] postNameList = { ROLE_NAME, "5" };
		// 1.P1が入力されていない場合、エラーメッセージを返す。
		// 入力されている場合2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(postName) == false) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, postNameList);
		}
		// 2. P1が5文字以下で入力されてない場合、エラーメッセージを返す。
		// 入力されている場合4に進む。
		if (lengthCheck.isMaxStringLength(postName, 5) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, postNameList);
		}
		//重複チェック
		int numPostName = checkDuplicateDAO.checkDuplicate(postName, M_BELONG_DEPARTMENT, COLUMN_DEPARTMENT_NAME);
		if (numPostName != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, postNameList);
		}
		return null;
	}
}
