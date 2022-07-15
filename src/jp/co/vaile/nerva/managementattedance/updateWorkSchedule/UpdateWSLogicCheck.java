package jp.co.vaile.nerva.managementattedance.updateWorkSchedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.managementattedance.HolidayPeriodDTO;
import jp.co.vaile.nerva.managementattedance.SearchHolidayPeriodDAO;
import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class UpdateWSLogicCheck {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();

	/**
	 * エラーチェック処理を呼び出し、エラーリストを返す
	 * @param workScheduleDTOList
	 * @return errorList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<String> checkUpdateWS(List<WorkScheduleDTO> workScheduleDTOList)
			throws ClassNotFoundException, SQLException {

		List<String> errorList = new ArrayList<>();
		// 休暇期間等取得
		SearchHolidayPeriodDAO searchHolidayPeriodDAO = new SearchHolidayPeriodDAO();
		List<HolidayPeriodDTO> holedayPeriodDTOList = searchHolidayPeriodDAO.serchHolidayPeriodDTOList();
		// エラーチェック
		errorList = errorMsg.errorMsgNullCheck(checkUpdateEndTimes(workScheduleDTOList), errorList);
		errorList = errorMsg.errorMsgNullCheck(checkUpdateStartTimes(workScheduleDTOList), errorList);
		errorList = errorMsg.errorMsgNullCheck(checkUpdateRestTimes(workScheduleDTOList), errorList);
		errorList = errorMsg.errorMsgNullCheck(checkRemarks(workScheduleDTOList), errorList);
		errorList = errorMsg.errorMsgNullCheck(checkUpdateHolidayPeriod(holedayPeriodDTOList, workScheduleDTOList),
				errorList);

		return errorList;
	}

	/**
	 * 開始が入力されている場合、終了が入力されているかチェックする
	 * 
	 * @param workScheduleDTOList
	 * @return errorMsg
	 */
	private String checkUpdateEndTimes(List<WorkScheduleDTO> workScheduleDTOList) {

		String[] errorEndTimeList = { CommonConstants.ATTENDANCE_MANAGEMENT_FINISH };

		for (int i = 0; i < workScheduleDTOList.size(); i++) {
			//開始が入力されていて、終了が入力されていなければエラーメッセージを返す
			if (lengthCheck.isNomNullOrNomEmpty(workScheduleDTOList.get(i).getStartTime())) {
				if (!lengthCheck.isNomNullOrNomEmpty(workScheduleDTOList.get(i).getEndTime())) {
					return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE, errorEndTimeList);
				}
			}
		}

		return null;
	}

	/**
	 * 終了が入力されている場合、開始が入力されているかチェックする
	 * 
	 * @param workScheduleDTOList
	 * @return errorMsg
	 */
	private String checkUpdateStartTimes(List<WorkScheduleDTO> workScheduleDTOList) {

		String[] errorStartTimeList = { CommonConstants.ATTENDANCE_MANAGEMENT_START };

		for (int i = 0; i < workScheduleDTOList.size(); i++) {
			//終了が入力されていて、開始が入力されていなければエラーメッセージを返す
			if (lengthCheck.isNomNullOrNomEmpty(workScheduleDTOList.get(i).getEndTime())) {

				if (!lengthCheck.isNomNullOrNomEmpty(workScheduleDTOList.get(i).getStartTime())) {

					return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE, errorStartTimeList);
				}
			}
		}

		return null;
	}

	/**
	 * 開始が入力されている場合、休憩が入力されているかチェックする
	 * 
	 * @param workScheduleDTOList
	 * @return
	 */
	private String checkUpdateRestTimes(List<WorkScheduleDTO> workScheduleDTOList) {

		String[] errorRestTimeList = { CommonConstants.ATTENDANCE_MANAGEMENT_REST };

		for (int i = 0; i < workScheduleDTOList.size(); i++) {
			//開始が入力されている場合、休憩が入力されているかチェックする
			if (lengthCheck.isNomNullOrNomEmpty(workScheduleDTOList.get(i).getStartTime())) {
				if (!lengthCheck.isNomNullOrNomEmpty(workScheduleDTOList.get(i).getRest())) {

					return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE, errorRestTimeList);
				}
			}
		}
		return null;
	}

	/**
	 * 備考が20文字以内か確認する
	 * 
	 * @param workScheduleDTOList
	 * @return errorMsg
	 */
	private String checkRemarks(List<WorkScheduleDTO> workScheduleDTOList) {

		String[] errorYearList = { CommonConstants.ATTENDANCE_MANAGEMENT_REMARK, "20" };
		boolean valueFlg = true;

		for (int i = 0; i < workScheduleDTOList.size(); i++) {

			//エラーチェック処理呼び出し
			valueFlg = lengthCheck.isMaxStringLength(workScheduleDTOList.get(i).getRemarks(), 20);

			//エラーだった場合、エラーメッセージを返す
			if (!valueFlg) {

				return errorMsg.returnErrorMsg(CommonConstants.INPUT_MAX_LENGTH_ERROR_MESSAGE, errorYearList);
			}
		}

		return null;
	}

	/**
	 * 休暇期間等マスタを基準に入力必須項目が入力されているかチェックを行い、
	 * 入力がなかった場合エラーメッセージを返す
	 * @param holidayPeriodDTOList
	 * @param workScheduleDTOList
	 * @return errorMsg
	 */
	private String checkUpdateHolidayPeriod(List<HolidayPeriodDTO> holidayPeriodDTOList,
			List<WorkScheduleDTO> workScheduleDTOList) {

		for (WorkScheduleDTO wsDTO : workScheduleDTOList) {
			for (HolidayPeriodDTO hpDTO : holidayPeriodDTOList) {
				if (wsDTO.getHolidayPeriod().length() > 0) {

					if (wsDTO.getHolidayPeriod().equals(hpDTO.getHolidayId())) {

						if (hpDTO.isHolidayTimeFlg()) {
							if (!lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayTime())) {

								String[] errorHolidayList = { CommonConstants.ATTENDANCE_MANAGEMENT_VACATION_PERIOD + hpDTO.getHolidayName() + CommonConstants.ATTENDANCE_MANAGEMENT_CASE+CommonConstants.ATTENDANCE_MANAGEMENT_VACATION_TIME };
								return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
										errorHolidayList);
							}
						} else if (hpDTO.isLateTimeFlg()) {
							if (!lengthCheck.isNomNullOrNomEmpty(wsDTO.getLateTime())) {

								String[] errorHolidayList = { CommonConstants.ATTENDANCE_MANAGEMENT_VACATION_PERIOD + hpDTO.getHolidayName() + CommonConstants.ATTENDANCE_MANAGEMENT_CASE+ CommonConstants.ATTENDANCE_MANAGEMENT_LATE_TIME };
								return errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE,
										errorHolidayList);
							}
						}

					}
				}
			}
		}

		return null;
	}

}
