package jp.co.vaile.nerva.masterSkillType.updateSkillType;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;
import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class UpdateSkillTypeCheckAction {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	/**スキル種別マスタメンテナンス画面での入力値をチェックするメソッドを呼び出し、それぞれの戻り値をリストに格納し返す。
	* @param updateSkillTypeDTO スキル種別マスタメンテナンス画面での入力値
	* @param check エラーチェックを行うかの真偽値
	* @return エラーメッセージ
	*/
	public List<String> checkUpdateSkillType(UpdateSkillTypeDTO updateSkillTypeDTO, boolean[] check) {
		List<String> errorList = new ArrayList<String>();
		// スキル種別名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(checkUpdateSkillTypeName(updateSkillTypeDTO.getSkillTypeName(), check),
				errorList);
		return errorList;
	}

	/**
	 * スキル種別名が正しく入力されていない場合、エラーメッセージを戻り値として返す。 エラーがない場合、nullを返す。
	 * 
	 * @param skillTypeName スキル種別マスタメンテナンス画面の更新フォームで入力されたスキル種別名
	 * @return エラーメッセージ
	 */
	private String checkUpdateSkillTypeName(String[] skillTypeName, boolean[] check) {
		String[] skillTypeNameErrorList = { CommonConstants.SKILL_TYPE_NAME, "20" };

		for (int i = 0; i < skillTypeName.length; i++) {
			if (check[i]) {
				// 未入力の場合、エラーメッセージを返す。
				if (!lengthCheck.isNomNullOrNomEmpty(skillTypeName[i])) {
					return errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, skillTypeNameErrorList);
				}
				// 入力された値が20文字以内でなければ、エラーメッセージを返す。
				else if (!lengthCheck.isMaxStringLength(skillTypeName[i], 20)) {
					return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, skillTypeNameErrorList);
				}
				// 重複処理
				CheckDuplicateDAO checkDuplicateDAO = new CheckDuplicateDAO();
				int numSkillTypeName = 0;
				try {
					numSkillTypeName = checkDuplicateDAO.checkDuplicate(skillTypeName[i], M_SKILL_TYPE,
							COLUMN_SKILL_TYPE);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (numSkillTypeName != 0) {
					return errorMsg.returnErrorMsg(ID_EXIST_ERROR_MESSAGE, skillTypeNameErrorList);
				}
				// 重複処理
				for(int j = 0; j < skillTypeName.length; j++) {
					if(i != j && skillTypeName[i].equals(skillTypeName[j])) {
						return errorMsg.returnErrorMsg(DUPLICATE_INPUT_ERROR_MESSAGE, skillTypeNameErrorList);
					}
				}
			}
		}
		return null;
	}
}
