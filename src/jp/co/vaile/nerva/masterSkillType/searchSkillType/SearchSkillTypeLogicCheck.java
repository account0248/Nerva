package jp.co.vaile.nerva.masterSkillType.searchSkillType;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchSkillTypeLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	
	/**スキル種別マスタメンテナンス画面での入力値をチェックするメソッドを呼び出し、それぞれの戻り値をリストに格納し返す。
	* @param searchSkillTypeDTO スキル種別マスタメンテナンス画面での入力値
	* @return エラーメッセージ
	*/
	public List<String> checkSearchSkillType(SearchSkillTypeDTO searchSkillTypeDTO) {
		List<String> errorList = new ArrayList<String>();
		//スキル種別IDにエラーメッセージがある場合、エラーリストに追加する。無い場合追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchSkillTypeId(searchSkillTypeDTO.getSkillTypeId()), errorList);
		//スキル種別名にエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkSearchSkillTypeName(searchSkillTypeDTO.getSkillTypeName()), errorList);
			
		return errorList;
	}
	

	/**スキル種別IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	* エラーがない場合、nullを返す。
	* @param skillTypeId スキル種別マスタメンテナンス画面で入力されたスキル種別ID
	* @return エラーメッセージ
	*/
	private String checkSearchSkillTypeId(String skillTypeId) {
		String[] SkillTypeIderrorList= { CommonConstants.SKILL_TYPE_ID, "10"};
		//String[] SkillTypeIdList = { CommonConstants.SKILL_TYPE_ID };

		//未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(skillTypeId)) {
			return null;
		}
		//入力された値が10文字でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isStringLength(skillTypeId, 10)) {
			return errorMsg.returnErrorMsg(CommonConstants.INPUT_LENGTH_ERROR_MESSAGE, SkillTypeIderrorList);
		}
		//半角英数字で入力されてない場合、エラーメッセージを返す。
		if (!harfWidthAlphanumFormatCheck.isCorrectFormat(skillTypeId)) {
			return errorMsg.returnErrorMsg(CommonConstants.HALFWIDTH_ALPHANUMERIC_ERROR_MESSAGE, SkillTypeIderrorList);
		}
		
		return null;
	}
	/**
	* スキル種別名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	* エラーがない場合、nullを返す。
	* @param skillTypeName スキル種別マスタメンテナンス画面で入力されたスキル種別名
	* @return　エラーメッセージ
	*/
	private String checkSearchSkillTypeName(String skillTypeName) {
		String errorContext = null;
		String[] SkillTypeNameList = { CommonConstants.SKILL_TYPE_NAME,"20"};
		
		//未入力の場合、nullを返す。
		if (!lengthCheck.isNomNullOrNomEmpty(skillTypeName)) {
			return errorContext;
		}
		//入力された値が20文字以内でなければ、エラーメッセージを返す。
		else if (!lengthCheck.isMaxStringLength(skillTypeName, 20)) {
			errorContext = errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, SkillTypeNameList);
		}
		return errorContext;
	}

}
