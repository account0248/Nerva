package jp.co.vaile.nerva.updateEmployee;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.vaile.nerva.commonprocess.ExistCheck;
import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.existchecksub.ContractTypeInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.RoleInfo;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthNumFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TeamIDFormatCheck;

public class InputCheckWorkExpLogic {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	Map<String, String> errorMsgMap = new HashMap<String, String>();
	CheckTeamAssignDateDAO checkTeamAssignDateDAO = new CheckTeamAssignDateDAO();

	/**
	 * 業務経験更新欄の入力チェックを行う。入力が正しくない場合エラーメッセージを返す。
	 * @param updateEmpWorkExpDTOList
	 * @return エラーメッセージを格納したマップ
	 * @throws Exception
	 */
	public Map<String, String> checkUpdateWorkExpContent(List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList)
			throws Exception {
		//リストの長さ分繰り返す
		for (int i = 0; i < updateEmpWorkExpDTOList.size(); i++) {
			//所属開始日の入力チェックを行う。
			checkUpdateTeamBelongStartDate(updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate());
			//所属完了日の入力チェックを行う。
			checkTeamBelongCompleteDate(updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId(),
					updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate());
		}
		//エラーメッセージを返す。
		return errorMsgMap;
	}

	/**
	 * 業務経験登録欄の入力チェックを行う。入力が正しくない場合エラーメッセージを返す。
	 * @param registEmpWorkExpDTOList
	 * @return エラーメッセージを格納したマップ
	 * @throws Exception
	 */
	public Map<String, String> checkRegistWorkExpContent(List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList)
			throws Exception {
		for (int i = 0; i < registEmpWorkExpDTOList.size(); i++) {
			//所属チームの入力チェックを行う。
			checkBelongTeam(registEmpWorkExpDTOList.get(i).getBelongTeamId());
			//担当の入力チェックを行う。
			checkRole(registEmpWorkExpDTOList.get(i).getBelongTeamId(),
					registEmpWorkExpDTOList.get(i).getRoleId(),
					registEmpWorkExpDTOList.get(i).getTeamBelongStartDate());
			//契約形態の入力チェックを行う。
			checkContractType(registEmpWorkExpDTOList.get(i).getContractTypeId());
			//月単価の入力チェックを行う。
			checkMonthlyUnitPrice(registEmpWorkExpDTOList.get(i).getMonthlyUnitPrice());
			//所属開始日の入力チェックを行う。
			checkRegistTeamBelongStartDate(registEmpWorkExpDTOList.get(i).getTeamBelongStartDate());
			//所属完了日の入力チェックを行う。
			checkTeamBelongCompleteDate(registEmpWorkExpDTOList.get(i).getBelongTeamId(),
					registEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate());
		}
		//移管申請のチェックを行う。
		checkTotalTransferApplication(registEmpWorkExpDTOList);

		return errorMsgMap;
	}

	/**
	 * 業務経験欄の日付チェックを行う。入力が正しくない場合エラーメッセージを返す。
	 * @param checkDateEmpWorkExpDTO 業務経験日付比較用DTO
	 * @return エラーメッセージを格納したマップ
	 * @throws ParseException
	 */
	public Map<String, String> checkWorkExpDateContent(CheckDateEmpWorkExpDTO checkDateEmpWorkExpDTO)
			throws ParseException {
		String[] teamBelongStartDateErrorMsg = { TEAM_BELONG_START_DATE, TEAM_BELONG_COMPLETE_DATE };
		String[] teamBelongCompleteDateErrorMsg = { TEAM_BELONG_COMPLETE_DATE, TEAM_BELONG_START_DATE };

		//リストの長さの回数繰り返す
		for (int i = 0; i < checkDateEmpWorkExpDTO.getTeamBelongCompleteDateList().size(); i++) {
			String teamBelongStartStr = checkDateEmpWorkExpDTO.getTeamBelongStartDateList().get(i);
			String teamBelongCompleteStr = checkDateEmpWorkExpDTO.getTeamBelongCompleteDateList().get(i);
			//所属開始日が入力されているかチェックする
			if (!lengthCheck.isNomNullOrNomEmpty(teamBelongStartStr)) {
				errorMsgMap.put("requiredTeamBelongStartDate",
						errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, teamBelongStartDateErrorMsg));
				return errorMsgMap;
			} else if (!teamBelongStartStr.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
				errorMsgMap.put("validityTeamBelongStartDate",
						errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, teamBelongStartDateErrorMsg));
				return errorMsgMap;
			}
			Date teamBelongStartDate = sdf.parse(teamBelongStartStr);
			//所属完了日が入力されているかチェックする
			if (lengthCheck.isNomNullOrNomEmpty(teamBelongCompleteStr)
					|| teamBelongCompleteStr.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
				Date teamBelongCompleteDate = sdf.parse(teamBelongCompleteStr);
				//所属完了日が所属開始日よりも前の場合、エラーメッセージを返す。
				if (teamBelongCompleteDate.compareTo(teamBelongStartDate) == -1) {
					errorMsgMap.put("correctTeamBelongCompleteDate",
							errorMsg.returnErrorMsg(AFTER_DATE, teamBelongCompleteDateErrorMsg));
					return errorMsgMap;
				}
			}
			//所属開始日リストからひとつ前の所属開始日を取り出し、所属開始日と比較する。
			if (i >= 1) {
				Date teamBelongStartPreviousDate = sdf
						.parse(checkDateEmpWorkExpDTO.getTeamBelongStartDateList().get(i - 1));
				if (teamBelongStartPreviousDate.compareTo(teamBelongStartDate) >= 0) {
					errorMsgMap.put("validityTeamBelongStartDate",
							errorMsg.returnErrorMsg(DATE_INPUT_ORDER, teamBelongStartDateErrorMsg));
					return errorMsgMap;
				}
			}
			//所属完了日リストからひとつ前の所属完了日を取りだし、所属開始日と比較する。
			if (i >= 1) {
				String teamBelongCompletePreviousStr = checkDateEmpWorkExpDTO.getTeamBelongCompleteDateList()
						.get(i - 1);
				if (lengthCheck.isNomNullOrNomEmpty(teamBelongCompletePreviousStr)) {
					Date teamBelongCompletePreviousDate = sdf.parse(teamBelongCompletePreviousStr);
					//所属開始日がひとつ前の所属完了日よりも前の場合、エラーメッセージを返す。
					if (teamBelongStartDate.compareTo(teamBelongCompletePreviousDate) == -1) {
						errorMsgMap.put("correctTeamBelongStartDate",
								errorMsg.returnErrorMsg(AFTER_DATE, teamBelongStartDateErrorMsg));
						return errorMsgMap;
					}
				}
			}
		}
		return errorMsgMap;
	}

	/**
	 * 所属開始日の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param teamBelongStartDate 所属開始日を表す文字列
	 * @throws Exception
	 */
	private void checkUpdateTeamBelongStartDate(String teamBelongStartDate) throws Exception {
		String[] teamBelongStartDateErrorMsg = { TEAM_BELONG_START_DATE };
		//所属開始日が入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(teamBelongStartDate)) {
			errorMsgMap.put("requiredTeamBelongStartDate",
					errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, teamBelongStartDateErrorMsg));
			return;
		}
		//所属開始日が形式通り入力されていない場合、エラーメッセージを格納する。
		else if (!teamBelongStartDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
			errorMsgMap.put("validityTeamBelongStartDate",
					errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, teamBelongStartDateErrorMsg));
			return;
		}
	}

	/**
	 * 所属開始日の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param teamBelongStartDate 所属開始日を表す文字列
	 * @throws Exception
	 */
	private void checkRegistTeamBelongStartDate(String teamBelongStartDate) throws Exception {
		String[] teamBelongStartDateErrorMsg = { TEAM_BELONG_START_DATE };
		//所属開始日が入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(teamBelongStartDate)) {
			errorMsgMap.put("requiredTeamBelongStartDate",
					errorMsg.returnErrorMsg(REQUIRED_ERROR_MESSAGE, teamBelongStartDateErrorMsg));
			return;
		}
		//所属開始日が形式通り入力されていない場合、エラーメッセージを格納する。
		else if (!teamBelongStartDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
			errorMsgMap.put("validityTeamBelongStartDate",
					errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, teamBelongStartDateErrorMsg));
			return;
		}
	}

	/**
	 * 所属完了日の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param teamBelongCompleteDate 所属完了日を表す文字列
	 * @throws Exception
	 */
	private void checkTeamBelongCompleteDate(String teamId, String teamBelongCompleteDate) throws Exception {
		//所属完了日が入力されていない場合、処理を返す。
		if (teamBelongCompleteDate == null) {
			return;
		}
		String[] teamBelongCompleteDateErrorMsg = { TEAM_BELONG_COMPLETE_DATE };
		String[] teamAssignCompleteDateErrorMsg = { TEAM_BELONG_COMPLETE_DATE, ENTRIED_TEAM_ASSIGN_COMPLETE_DATE };
		//所属完了日が形式通り入力されていない場合、エラーメッセージを格納する。
		if (!teamBelongCompleteDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
			errorMsgMap.put("validityTeamBelongCompleteDate",
					errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, teamBelongCompleteDateErrorMsg));
			return;
		}
		//所属完了日がチームの配属完了日よりも後の日付が入力されている場合、エラーメッセージを格納する。
		else if (!checkTeamAssignDateDAO.checkTeamAssignCompleteDate(teamId, teamBelongCompleteDate)) {
			errorMsgMap.put("IntegrityTeamBelongCompleteDate",
					errorMsg.returnErrorMsg(BEFORE_DATE, teamAssignCompleteDateErrorMsg));
			return;
		}
	}

	/**
	 * 所属完了日の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param teamBelongCompleteDate 所属完了日を表す文字列
	 * @throws Exception
	 */
	private void checkTeamBelongCompleteDate(int emloyeeExperienceId, String teamBelongCompleteDate) throws Exception {
		//所属完了日が入力されていない場合、処理を返す。
		if (teamBelongCompleteDate == null) {
			return;
		}
		String[] teamBelongCompleteDateErrorMsg = { TEAM_BELONG_COMPLETE_DATE };
		String[] teamAssignCompleteDateErrorMsg = { TEAM_BELONG_COMPLETE_DATE, ENTRIED_TEAM_ASSIGN_COMPLETE_DATE };
		//所属完了日が形式通り入力されていない場合、エラーメッセージを格納する。
		if (!teamBelongCompleteDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
			errorMsgMap.put("validityTeamBelongCompleteDate",
					errorMsg.returnErrorMsg(FORMAT_INPUT_ERROR_MESSAGE, teamBelongCompleteDateErrorMsg));
			return;
		}
		//所属完了日がチームの配属完了日よりも後の日付が入力されている場合、エラーメッセージを格納する。
		else if (!checkTeamAssignDateDAO.checkTeamAssignCompleteDate(emloyeeExperienceId, teamBelongCompleteDate)) {
			errorMsgMap.put("IntegrityTeamBelongCompleteDate",
					errorMsg.returnErrorMsg(BEFORE_DATE, teamAssignCompleteDateErrorMsg));
			return;
		}
	}

	/**
	 * チームIDの入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param teamId チームIDを表す文字列
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void checkBelongTeam(String teamId) throws ClassNotFoundException, SQLException {
		FormatCheck teamIdFormatCheck = new TeamIDFormatCheck();
		String[] teamErrorMsg = { BELONG_TEAM };
		//チームが選択されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(teamId)) {
			errorMsgMap.put("requiredTeamId", errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, teamErrorMsg));
			return;
		}
		
		//選択されたチームがDBに存在しない場合、エラーメッセージを格納する。
		CheckExistTeamDAO checkExistTeam = new CheckExistTeamDAO();
		if (!checkExistTeam.isTeamExistDB(teamId)) {
			errorMsgMap.put("validityTeamId", errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, teamErrorMsg));
			return;
		}
		
		//チームIDが形式通り入力されていない場合、エラーメッセージを格納する。
		if (!teamIdFormatCheck.isCorrectFormat(teamId)) {
			errorMsgMap.put("formatTeamId", errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, teamErrorMsg));
			return;
		}
	}

	/**
	 * 担当IDの入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param teamId チームIDを表す文字列
	 * @param roleId 担当IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void checkRole(String teamId, String roleId, String teamBelongStartDate)
			throws ClassNotFoundException, SQLException {
		String[] roleErrorMsg = { ROLE };
		//担当が選択されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(roleId)) {
			errorMsgMap.put("requiredRoleId",
					errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, roleErrorMsg));
			return;
		}
		//選択された担当がDBに存在しない場合、エラーメッセージを格納する。
		ExistCheck existCheck = new RoleInfo();
		if (!existCheck.isThisExistDB(roleId)) {
			errorMsgMap.put("validityRoleId", errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, roleErrorMsg));
			return;
		}
		//リーダーがすでにチームに存在している場合、エラーメッセージを格納する。
		CheckExistLeaderDAO checkExistLeader = new CheckExistLeaderDAO();
		if (roleId.equals(LEADER)) {
			//所属開始日が形式通りに入力されているかチェックする
			if (teamBelongStartDate.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
				if (checkExistLeader.isThisExistDB(teamId, teamBelongStartDate)) {
					errorMsgMap.put("duplicateLeader",
							errorMsg.returnErrorMsg(ALREADY_LEADER));
					return;
				}
			} else {
				return;
			}
		}
	}

	/**
	 * 契約形態IDの入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param contractTypeId 契約形態IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void checkContractType(String contractTypeId) throws ClassNotFoundException, SQLException {
		String[] contractTypeErrorMsg = { CONTRACT_TYPE };
		//契約形態が選択されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isNomNullOrNomEmpty(contractTypeId)) {
			errorMsgMap.put("requiredContractTypeId",
					errorMsg.returnErrorMsg(REQUIRED_SELECT_ERROR_MESSAGE, contractTypeErrorMsg));
			return;
		}
		//選択された契約形態がDBに存在しない場合、エラーメッセージを格納する。
		ExistCheck existCheck = new ContractTypeInfo();
		if (!existCheck.isThisExistDB(contractTypeId)) {
			errorMsgMap.put("validityContractTypeId",
					errorMsg.returnErrorMsg(VALIDITY_SELECT_ERROR_MESSAGE, contractTypeErrorMsg));
			return;
		}
	}

	/**
	 * 月単価の入力チェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param monthlyUnitPrice 月単価を表す文字列
	 */
	private void checkMonthlyUnitPrice(String monthlyUnitPrice) {
		String[] monthlyUnitPriceErrorMsg = { MONTHLY_UNIT_PRICE, "9" };
		//月単価が入力されていない場合、処理を返す。
		if (monthlyUnitPrice == null) {
			return;
		}
		//月単価が半角数字で入力されていない場合、エラーメッセージを格納する。
		FormatCheck harfWidthNumFormatCheck = new HarfWidthNumFormatCheck();
		if (!harfWidthNumFormatCheck.isCorrectFormat(monthlyUnitPrice)) {
			errorMsgMap.put("halfWidthMonthlyUnitPrice",
					errorMsg.returnErrorMsg(HARF_WIDTH_NUM_ERROR_MESSAGE, monthlyUnitPriceErrorMsg));
			return;
		}
		//月単価が9文字以内で入力されていない場合、エラーメッセージを格納する。
		if (!lengthCheck.isMaxStringLength(monthlyUnitPrice, 9)) {
			errorMsgMap.put("lengthMonthlyUnitPrice",
					errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, monthlyUnitPriceErrorMsg));
			return;
		}
	}

	/**
	 * 移管申請のチェックを行う。正しくない場合エラーメッセージを格納する。
	 * @param registEmpWorkExpDTOList
	 * @throws Exception
	 */
	private void checkTotalTransferApplication(List<RegistEmpWorkExpDTO> registEmpWorkExpDTOList)
			throws Exception {
		int totalTransferApplication = 0;
		//リストの長さの回数繰り返す
		for (int i = 0; i < registEmpWorkExpDTOList.size(); i++) {
			//従業員が現在移管申請されているか確認する。
			TransferExistCheckDAO transferExistCheckDAO = new TransferExistCheckDAO();
			boolean transferInfoFlg = transferExistCheckDAO
					.isExistTransferInfo(registEmpWorkExpDTOList.get(i).getEmployeeId());
			//移管申請されている場合、エラーメッセージを格納する。
			if (transferInfoFlg) {
				errorMsgMap.put("duplicateTransferApplication",
						errorMsg.returnErrorMsg(TRNSFER_ERROR_MESSAGE));
				return;
			}
			//移管申請情報がない場合
			else {
				//従業員の現在の所属チーム部長IDを取得する
				SelectTeamManagerDAO selectTeamManagerDAO = new SelectTeamManagerDAO();
				String teamManagerId = selectTeamManagerDAO
						.SelectTeamManager(registEmpWorkExpDTOList.get(i).getEmployeeId());
				//現在チームに所属していない場合
				if (teamManagerId == null) {
					registEmpWorkExpDTOList.get(i).setApplicationFlag(false);
				}
				//チームの所属部長とログインユーザーが等しい場合
				else if (teamManagerId.equals(registEmpWorkExpDTOList.get(i).getLoginUserId())) {
					registEmpWorkExpDTOList.get(i).setApplicationFlag(false);
				}
				//チームの所属部長とログインユーザーが異なる場合
				else {
					totalTransferApplication++;
					registEmpWorkExpDTOList.get(i).setApplicationFlag(true);
				}
			}
			//移管申請情報が複数送られている場合、エラーメッセージを格納する。
			if (totalTransferApplication > 1) {
				errorMsgMap.put("overTransferApplication", errorMsg.returnErrorMsg(TOTAL_TRANSFER_APPLICATION));
				return;
			}
		}
	}
}
