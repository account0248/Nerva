package jp.co.vaile.nerva.managementattedance.updateWorkSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class InsertWSDAO {
	/**
	 * 勤務表テーブルに登録する
	 * @param updateWSDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void insertWS(UpdateWSDTO updateWSDTO) throws SQLException, ClassNotFoundException {
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append("INSERT INTO ");
			sql.append("attendance_management_info");
			sql.append("(");
			sql.append(" employee_id" + ",");
			sql.append(" fiscal_year_month_date" + ",");
			sql.append(" start_time" + ",");
			sql.append(" end_time" + ",");
			sql.append(" rest_time" + ",");
			sql.append(" holiday_working_time" + ",");
			sql.append(" holiday_period_id" + ",");
			sql.append(" holiday_days" + ",");
			sql.append(" holiday_time" + ",");
			sql.append(" late_time" + ",");
			sql.append(" remarks" + ",");
			sql.append(" regist_user" + ",");
			sql.append(" regist_time" + ",");
			sql.append(" delete_flg");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(");
			sql.append("?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,0");
			sql.append(")");

			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < updateWSDTO.getWorkScheduleDTOList().size(); i++) {
				//insert内容を適切な形に直す
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

				//パラメータをセット
				stmt.setString(1, updateWSDTO.getEmployeeId());
				stmt.setString(2, updateWSDTO.getYear() + "-" + updateWSDTO.getMonth() + "-" + days_str);
				stmt.setString(3, startTime);
				stmt.setString(4, endTime);
				stmt.setString(5, rest);
				stmt.setString(6, holidayWork);
				stmt.setString(7, holiday);
				stmt.setString(8, holidayDays);
				stmt.setString(9, holidayTime);
				stmt.setString(10, lateTime);
				stmt.setString(11, remarks);
				stmt.setString(12, updateWSDTO.getUserId());
				//insertを実行
				stmt.executeUpdate();
			}
	}
}
