package jp.co.vaile.nerva.managementattedance.updateWorkSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class UpdateWSDAO {

	/**
	 * 勤務表テーブルの更新を行う
	 * @param updateWSDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void updateWS(UpdateWSDTO updateWSDTO) throws SQLException, ClassNotFoundException {
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" UPDATE ");
			sql.append("attendance_management_info");
			sql.append(" SET ");
			sql.append(" start_time" + " = ?,");
			sql.append(" end_time" + " = ?,");
			sql.append(" rest_time" + " = ?,");
			sql.append(" holiday_working_time" + " = ?,");
			sql.append(" holiday_period_id" + " = ?,");
			sql.append(" holiday_days" + " = ?,");
			sql.append(" holiday_time" + " = ?,");
			sql.append(" late_time" + " = ?,");
			sql.append(" remarks" + " = ?,");
			sql.append(" update_user " + " = ?, ");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append("(");
			sql.append("employee_id = ?");
			sql.append(" AND ");
			sql.append("fiscal_year_month_date = ");
			sql.append("?");
			sql.append(")");

			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < updateWSDTO.getWorkScheduleDTOList().size(); i++) {

				// アップデートに適した形に直す
				WorkScheduleDTO workScheduleDTO = updateWSDTO.getWorkScheduleDTOList().get(i);
				String startTime = workScheduleDTO.getStartTime();
				String endTime = workScheduleDTO.getEndTime();
				String rest = workScheduleDTO.getRest();
				String holidayWork = workScheduleDTO.getHolidayWorking();
				String holiday = workScheduleDTO.getHolidayPeriod();
				String holidayDays = workScheduleDTO.getHolidayDays();
				String holidayTime = workScheduleDTO.getHolidayTime();
				String lateTime = workScheduleDTO.getLateTime();
				String remarks = workScheduleDTO.getRemarks();
				Integer days = Integer.valueOf(i + 1);
				String days_str = days.toString();
				if (days_str.length() == 1)
					days_str = '0' + days_str;
				if (startTime.length() == 0)
					startTime = null;
				if (endTime.length() == 0)
					endTime = null;
				if (rest.length() == 0)
					rest = null;
				if (holidayWork.length() == 0)
					holidayWork = null;
				if (holiday.length() == 0)
					holiday = null;
				if (holidayDays.length() == 0)
					holidayDays = null;
				if (holidayTime.length() == 0)
					holidayTime = null;
				if (lateTime.length() == 0)
					lateTime = null;
				if (remarks.length() == 0)
					remarks = null;

				stmt.setString(1, startTime);
				stmt.setString(2, endTime);
				stmt.setString(3, rest);
				stmt.setString(4, holidayWork);
				stmt.setString(5, holiday);
				stmt.setString(6, holidayDays);
				stmt.setString(7, holidayTime);
				stmt.setString(8, lateTime);
				stmt.setString(9, remarks);
				stmt.setString(10, updateWSDTO.getUserId());
				stmt.setString(11, updateWSDTO.getEmployeeId());
				// 月を変数monthに代入する。
				String month = updateWSDTO.getMonth();
				stmt.setString(12, updateWSDTO.getYear() + '-' + month + '-' + days_str);
				stmt.executeUpdate();
			}
	}

}
