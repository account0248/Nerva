package jp.co.vaile.nerva.masterSkillType.insertSkillType;

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
import jp.co.vaile.nerva.commonprocess.formatchecksub.SkillTypeIDFormatCheck;

public class InsertSkillTypeCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();

	/**スキル種別マスタメンテナンス画面での入力値をチェックするメソッドを呼び出し、それぞれの戻り値をリストに格納し返す。
	* @param insertSkillTypeDTO スキル種別マスタメンテナンス画面での入力値
	* @return エラーメッセージ
	*/
	public List<String> checkInsertSkillType(InsertSkillTypeDTO insertSkillTypeDTO) throws ClassNotFoundException, SQLException, ParseException {
		List<String> errorList = new ArrayList<String>();
		// P1のスキル種別IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertSkillTypeId(insertSkillTypeDTO.getSkillTypeId()),errorList);
		// P1のスキル種別名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertSkillTypeName(insertSkillTypeDTO.getSkillTypeName()),errorList);
		// P1の年数/取得日のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkInsertYearsDateOfAcquisition(insertSkillTypeDTO.getYearsDateOfAcquisition()),errorList);
		
		return errorList;
	}
	
	/**スキル種別IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	* エラーがない場合、nullを返す。
	* @param skillTypeId スキル種別マスタメンテナンス画面で入力されたスキル種別ID
	* @return エラーメッセージ
	*/
	private String checkInsertSkillTypeId(String skillTypeId) throws ClassNotFoundException, SQLException, ParseException {
		String[] skillTypeIdList = {SKILL_TYPE_ID, "10"};
		//未入力の場合、エラーメッセージを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(skillTypeId)) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, skillTypeIdList);
		}
		//10文字で入力されてない場合、エラーメッセージを返す。
		if (!lengthCheck.isStringLength(skillTypeId, 10)) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, skillTypeIdList);
		}
		//半角英数字で入力されてない場合、エラーメッセージを返す。
		if (!harfWidthAlphanumFormatCheck.isCorrectFormat(skillTypeId)) {
			return errorMsg.returnErrorMsg(HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, skillTypeIdList);
		}
		// P1が形式通りに入力されていない場合、エラーリストにエラーメッセージを追加する
		SkillTypeIDFormatCheck skillTypeIDFormatCheck = new SkillTypeIDFormatCheck();
		if (!skillTypeIDFormatCheck.isCorrectFormat(skillTypeId)) {
			return errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, skillTypeIdList);
		}
		// 重複チェック
		int numSkillTypeId = checkDuplicateDAO.checkDuplicate(skillTypeId, M_SKILL_TYPE, COLUMN_SKILL_TYPE_ID);
		if (numSkillTypeId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, skillTypeIdList);
		}
		return null;
	}
	/**
	* スキル種別名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	* エラーがない場合、nullを返す。
	* @param skillTypeName スキル種別マスタメンテナンス画面で入力されたスキル種別名
	* @return　エラーメッセージ
	*/
	private String checkInsertSkillTypeName(String skillTypeName) throws ClassNotFoundException, SQLException, ParseException {
		String[] skillTypeNameList = { SKILL_TYPE_NAME, "20" };
		//未入力の場合、エラーメッセージを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(skillTypeName)) {
			return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, skillTypeNameList);
		}
		// P1が20文字以下で入力されてない場合、エラーメッセージを返す。
		if (!lengthCheck.isMaxStringLength(skillTypeName, 20)) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, skillTypeNameList);
		}
		// 重複チェック
		int numSkillTypeId = checkDuplicateDAO.checkDuplicate(skillTypeName, M_SKILL_TYPE, COLUMN_SKILL_TYPE);
		if (numSkillTypeId != 0) {
			return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, skillTypeNameList);
		}
		return null;
	}
	/**
	* 年数/取得日が正しく選択されていない場合、エラーメッセージを戻り値として返す。
	* エラーがない場合、nullを返す。
	* @param skillTypeName スキル種別マスタメンテナンス画面で入力されたスキル種別名
	* @return　エラーメッセージ
	*/
	private String checkInsertYearsDateOfAcquisition(String yearsDateOfAcquisition) throws ParseException {
		String[] skillTypeNameList = { SKILL_TYPE_FLAG };
		//選択されていない場合、エラーメッセージを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(yearsDateOfAcquisition)) {
			return errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, skillTypeNameList);
		}
		return null;
	}
}
