package jp.co.vaile.nerva.updateEmployee;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.existchecksub.SkillTypeInfo;
import jp.co.vaile.nerva.commonprocess.skillMaster.GetYearsDateOfAcquisitFlgDAO;

public class InputCheckSkillLogic {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	Map<String, String> errorMsgMap = new HashMap<String, String>();

	/**
	 * 保有スキル更新欄の入力チェックを行う。入力が正しくない場合エラーメッセージを返す。
	 * 
	 * @param updateSkillInfoDTOList 保有スキル更新用DTOを格納したリスト
	 * @return エラーメッセージを格納したマップ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, String> checkUpdateSkillContent(List<UpdateSkillInfoDTO> updateSkillInfoDTOList)
			throws ClassNotFoundException, SQLException {
		// リストの長さの回数繰り返す
		for (int i = 0; i < updateSkillInfoDTOList.size(); i++) {
			// スキル種別の入力チェックを行う。
			checkSkillType(updateSkillInfoDTOList.get(i).getSkillTypeId());
			// スキル内容の入力チェックを行う。
			checkSkillDetail(updateSkillInfoDTOList.get(i).getSkillDetail());
			// 経験年数の入力チェックを行う。
			checkExperienceYears(updateSkillInfoDTOList.get(i).getSkillTypeId(),
					updateSkillInfoDTOList.get(i).getExperienceYears());
			// 取得年月の入力チェックを行う。
			checkAcquisitionYearMonth(updateSkillInfoDTOList.get(i).getSkillTypeId(),
					updateSkillInfoDTOList.get(i).getAcquisitionYearMonth());
		}
		return errorMsgMap;
	}

	/**
	 * 保有スキル登録欄の入力チェックを行う。入力が正しくない場合エラーメッセージを返す。
	 * 
	 * @param registSkillInfoDTOList 保有スキル更新用DTOを格納したリスト
	 * @return エラーメッセージを格納したマップ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Map<String, String> checkRegistSkillContent(List<RegistSkillInfoDTO> registSkillInfoDTOList)
			throws ClassNotFoundException, SQLException {
		// リストの長さの回数繰り返す
		for (int i = 0; i < registSkillInfoDTOList.size(); i++) {
			// スキル種別の入力チェックを行う。
			checkSkillType(registSkillInfoDTOList.get(i).getSkillTypeId());
			// スキル種別の入力チェックを行う。
			checkSkillDetail(registSkillInfoDTOList.get(i).getSkillDetail());
			// 経験年数の入力チェックを行う。
			checkExperienceYears(registSkillInfoDTOList.get(i).getSkillTypeId(),
					registSkillInfoDTOList.get(i).getExperienceYears());
			// 取得年月の入力チェックを行う。
			checkAcquisitionYearMonth(registSkillInfoDTOList.get(i).getSkillTypeId(),
					registSkillInfoDTOList.get(i).getAcquisitionYearMonth());
		}
		return errorMsgMap;
	}

	/**
	 * スキル種別IDの入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * 
	 * @param skillTypeId スキル種別IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void checkSkillType(String skillTypeId) throws ClassNotFoundException, SQLException {
		ExistCheck existCheck = new SkillTypeInfo();
		String[] skillTypeErrorMsg = { SKILL_TYPE_MASTER };
		// スキル種別が選択されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(skillTypeId)) {
			errorMsgMap.put("requiredSkillType", errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, skillTypeErrorMsg));
			return;
		}
		// 選択されたスキル種別がDBに存在しない場合、エラーメッセージを格納する。
		if (!existCheck.isThisExistDB(skillTypeId)) {
			errorMsgMap.put("validitySkillType",
					errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, skillTypeErrorMsg));
			return;
		}
	}

	/**
	 * スキル内容の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * 
	 * @param skillDetail スキル内容を表す文字列
	 */
	private void checkSkillDetail(String skillDetail) {
		String[] skillDetailErrorMsg = { SKILL_DETAIL_NAME, "256" };
		// スキル内容が入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(skillDetail)) {
			errorMsgMap.put("requiredSkillDetail",
					errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, skillDetailErrorMsg));
			return;
		}
		// スキル内容が256文字以内で入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isMaxStringLength(skillDetail, 256)) {
			errorMsgMap.put("lengthSkillDetail",
					errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, skillDetailErrorMsg));
			return;
		}
	}

	/**
	 * 経験年数の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * 
	 * @param skillTypeId     スキル種別IDを表す文字列
	 * @param experienceYears 経験年数を表す文字列
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void checkExperienceYears(String skillTypeId, String experienceYears)
			throws ClassNotFoundException, SQLException {
		// 追記 年数/取得日フラグ取得処理があるインスタンス生成
		GetYearsDateOfAcquisitFlgDAO getYearsDateOfAcquisitFlgDAO = new GetYearsDateOfAcquisitFlgDAO();
		// 追記 年数/取得日フラグ取得
		Boolean yearsDateOfAcquisitFlg = getYearsDateOfAcquisitFlgDAO.getYearsDateOfAcquisit(skillTypeId);
		String[] experienceYearsErrorMsg = { EXPERIENCE_YEARS_NAME };
		
		// 年数/取得日フラグがnullの場合
		if (yearsDateOfAcquisitFlg == null) {
			return;
		// 年数/取得日フラグがtrueの場合
		} else if (yearsDateOfAcquisitFlg) {
			// スキル種別が資格以外の時に経験年数が入力されていない場合、エラーメッセージを格納する。
			if (!lengthCheck.isNomNullOrNomEmpty(experienceYears)) {
				errorMsgMap.put("requiredExperienceYears",
						errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, experienceYearsErrorMsg));
				return;
				// 経験年数が3文字以内で入力されていない場合、エラーメッセージを格納する。
			} else if (!lengthCheck.isMaxStringLength(experienceYears, 3)) {
				String[] experienceYearsMaxLengthErrorMsg = { EXPERIENCE_YEARS_NAME, "3" };
				errorMsgMap.put("lengthExperienceYears",
						errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, experienceYearsMaxLengthErrorMsg));
				return;
			}
		}
	}

	/**
	 * 取得年月の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * 
	 * @param skillTypeId          スキル種別IDを表す文字列
	 * @param acquisitionYearMonth 取得年月を表す文字列
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void checkAcquisitionYearMonth(String skillTypeId, String acquisitionYearMonth)
			throws ClassNotFoundException, SQLException {
		// 追記 年数/取得日フラグ取得処理があるインスタンス生成
		GetYearsDateOfAcquisitFlgDAO getYearsDateOfAcquisitFlgDAO = new GetYearsDateOfAcquisitFlgDAO();
		// 追記 年数/取得日フラグ取得
		Boolean yearsDateOfAcquisitFlg = getYearsDateOfAcquisitFlgDAO.getYearsDateOfAcquisit(skillTypeId);
		String[] acquisitionYearMonthErrorMsg = { ACQUISITION_YEAR_MONTH };
		
		// 年数/取得日フラグがnullの場合
		if (yearsDateOfAcquisitFlg == null) {
			return;
		// 年数/取得日フラグがfalseの場合
		} else if (!yearsDateOfAcquisitFlg) {
			// スキル種別が資格の時に取得年月が入力されていない場合、エラーメッセージを格納する。
			if (!lengthCheck.isNomNullOrNomEmpty(acquisitionYearMonth)) {
				errorMsgMap.put("requiredAcquisitionYearMonth",
						errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, acquisitionYearMonthErrorMsg));
				return;
				// 取得年月が形式通り入力されていない場合、エラーメッセージを格納する。
			} else if (!acquisitionYearMonth.matches("^[0-9]{4}-[0-9]{2}$")) {
				errorMsgMap.put("validityAcquisitionYearMonth",
						errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, acquisitionYearMonthErrorMsg));
				return;
			}
		}
	}
}
