package jp.co.vaile.nerva.managementattedance.importWorkSchedule;

import java.sql.SQLException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.DayFormatCheck;
import jp.co.vaile.nerva.commonprocess.formatchecksub.TimeFormatCheck;
import jp.co.vaile.nerva.managementattedance.HolidayPeriodDTO;
import jp.co.vaile.nerva.managementattedance.SearchHolidayPeriodDAO;
import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;
import jp.co.vaile.nerva.managementattedance.WorkscheduleContents;

public class WorkScheduleCheck {
	FormatCheck timeFormatCheck = new TimeFormatCheck();
	FormatCheck dayFormatCheck = new DayFormatCheck();
	LengthCheck lengthCheck = new LengthCheck();
	ErrorMsg errorMsg = new ErrorMsg();

	/**
	 * 勤務表DTOの内容が適切出ない場合エラーメッセージを返し、適切な場合はnullを返す
	 * @param workScheduleDTOList
	 * @return errorMsg
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String checkWorkSchedule(List<WorkScheduleDTO> workScheduleDTOList) throws ClassNotFoundException, SQLException {

		SearchHolidayPeriodDAO searchHolidayPeriodDAO = new SearchHolidayPeriodDAO();
		List<HolidayPeriodDTO> holidayPeriodDTOList = searchHolidayPeriodDAO.serchHolidayPeriodDTOList();

		for (WorkScheduleDTO wsDTO : workScheduleDTOList) {

			// 開始チェック
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getStartTime())) {
				if (!timeFormatCheck.isCorrectFormat(wsDTO.getStartTime()))
					return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
			}
			// 終了チェック
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getEndTime())) {
				if (!timeFormatCheck.isCorrectFormat(wsDTO.getEndTime()))
					return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
			}
			// 休憩チェック
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getRest())) {
				if (!timeFormatCheck.isCorrectFormat(wsDTO.getRest()))
					return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
			}
			// 法定チェック
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayWorking())) {
				if (!timeFormatCheck.isCorrectFormat(wsDTO.getHolidayWorking())) {
					return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
				}
			}

			boolean flg = true;
			for (HolidayPeriodDTO hpDTO : holidayPeriodDTOList) {

				// 勤務表に休暇期間等が入っている場合
				if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayPeriod())
						&& (hpDTO.getHolidayName().equals(wsDTO.getHolidayPeriod()))) {
					
					//一致した場合休暇日数チェック処理を呼び出す
					if(!checkHolidayDays(hpDTO, wsDTO)) {
						return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
					}
					
					//一致した場合休暇時間チェックを呼び出す
					if(!checkHolidayTime(hpDTO, wsDTO)) {
						return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
					}

					//一致した場合遅早時刻チェック処理を呼び出す
					if(!checkLateTime(hpDTO, wsDTO)) {
						return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
					}
					
					wsDTO.setHolidayPeriod(hpDTO.getHolidayId());

					flg = true;
					break;

				} else {
					flg = false;

				}
			}

			//休暇期間等が合致しなかった場合
			if (!flg) {
				// 休暇期間等未選択時に休暇日数・休暇時間チェック、遅早時刻フォーマットチェック
				if(lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayDays()))return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
				if(lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayTime()))return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
				if(lengthCheck.isNomNullOrNomEmpty(wsDTO.getLateTime())) {
					if(!timeFormatCheck.isCorrectFormat(wsDTO.getLateTime()))return errorMsg.returnErrorMsg(CommonConstants.FILE_FORMAT);
				}
				
			}
		}

		return null;
	}
	/**
	 * 休暇日数が適切か確認する
	 * @param hpDTO
	 * @param wsDTO
	 * @return true or false
	 */
	private boolean checkHolidayDays(HolidayPeriodDTO hpDTO,WorkScheduleDTO wsDTO) {
		// 一致した場合休暇期間等マスタの入力必須フラグを確認する
		boolean flg = true;
		if (hpDTO.isHolidayDaysFlg()) {
			// 必須の場合入力されているかチャック
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayDays())) {
				// 入力されていた場合フォーマットチェック
				if (!dayFormatCheck.isCorrectFormat(wsDTO.getHolidayDays())) {
					flg= false;
					return flg;
				}
			}

		} else {
			// 入力必須じゃなかった場合、0文字が確認する
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayDays())) {
				// 0字じゃなかった場合、休暇期間等マスタの内容と一致するか確認する
				if (hpDTO.getHolidayDays() == null) {
					// nullの場合、-と同じかチェックする
					if (!(wsDTO.getHolidayDays().equals(WorkscheduleContents.NONE))) {
						flg= false;
						return flg;
					}
				} else {
					// nullじゃない場合、休暇期間等マスタの内容と一致するか確認する
					if (!(wsDTO.getHolidayDays().equals(hpDTO.getHolidayDays()))) {
						flg= false;
						return flg;
					}
				}

			}
			
		}
		return flg;
	}
	/**
	 * 休暇時間が適切か判定する
	 * @param hpDTO
	 * @param wsDTO
	 * @return true or false
	 */
	private boolean checkHolidayTime(HolidayPeriodDTO hpDTO,WorkScheduleDTO wsDTO) {
		// 一致した場合休暇期間等マスタの入力必須フラグを確認する
		boolean flg = true;
		if (hpDTO.isHolidayTimeFlg()) {
			// 必須の場合入力されているかチャック
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayTime())) {
				// 入力されていた場合フォーマットチェック
				if (!timeFormatCheck.isCorrectFormat(wsDTO.getHolidayTime())) {
					flg= false;
					return flg;
				}
			}

		} else {
			// 入力必須じゃなかった場合、0文字が確認する
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getHolidayTime())) {
				// 0字じゃなかった場合、休暇期間等マスタの内容と一致するか確認する
				if (hpDTO.getHolidayTime() == null) {
					// nullの場合、-と同じかチェックする
					if (!(wsDTO.getHolidayTime().equals(WorkscheduleContents.NONE))) {

						flg= false;
						return flg;
					}
				} else {
					// nullじゃない場合、休暇期間等マスタの内容と一致するか確認する
					if (!(wsDTO.getHolidayTime().equals(hpDTO.getHolidayTime()))) {
						flg= false;
						return flg;
					}
				}

			}

		}
		return flg;
	}
	
	/**
	 * 遅早時刻が適切か確認する
	 * @param hpDTO
	 * @param wsDTO
	 * @return true or false
	 */
	private boolean checkLateTime(HolidayPeriodDTO hpDTO,WorkScheduleDTO wsDTO) {
		// 一致した場合休暇期間等マスタの入力必須フラグを確認する
		boolean flg = true;
		if (hpDTO.isLateTimeFlg()) {
			// 必須の場合入力されているかチャック
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getLateTime())) {
				// 入力されていた場合フォーマットチェック
				if (!timeFormatCheck.isCorrectFormat(wsDTO.getLateTime())) {
					flg= false;
					return flg;
				}
			}

		} else {
			// 入力必須じゃなかった場合、0文字が確認する
			if (lengthCheck.isNomNullOrNomEmpty(wsDTO.getLateTime())) {
				// 0字じゃなかった場合、休暇期間等マスタの内容と一致するか確認する
				if (hpDTO.getLateTime() == null) {
					// nullの場合、-と同じかチェックする
					if (!(wsDTO.getLateTime().equals(WorkscheduleContents.NONE))) {

						flg= false;
						return flg;
					}
				} else {
					// nullじゃない場合、休暇期間等マスタの内容と一致するか確認する
					if (!timeFormatCheck.isCorrectFormat(wsDTO.getLateTime())) {
						flg= false;
						return flg;
					}
				}

			}

		}
		return flg;
	}

}
