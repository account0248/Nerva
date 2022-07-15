package jp.co.vaile.nerva.managementattedance.displayWorkSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.managementattedance.WorkScheduleDTO;

public class DisplayWorkScheduleDAO {

	/**
	 * 勤怠情報管理画面で選択された年度、月、従業員IDを用いて、
	 * DBにアクセスし検索結果をDTOに格納する。
	 * @param displayDTO
	 * @return workScheduleDTOList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<WorkScheduleDTO> displayWSDTOList(DisplayWSDTO displayDTO) throws SQLException, ClassNotFoundException {
		List<WorkScheduleDTO> workScheduleDTOList = new ArrayList<>();
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" SELECT ");
			sql.append(" DATE_FORMAT(fiscal_year_month_date,'%e')" + ",");
			sql.append(" start_time" + ",");
			sql.append(" end_time" + ",");
			sql.append(" rest_time" + ",");
			sql.append(" holiday_working_time" + ",");
			sql.append(" holiday_period_id" + ",");
			sql.append(" holiday_days" + ",");
			sql.append(" holiday_time" + ",");
			sql.append(" late_time" + ",");
			sql.append(" remarks");
			sql.append(" FROM ");
			sql.append("attendance_management_info");
			sql.append(" WHERE ");
			sql.append("(");
			sql.append("employee_id = ?");
			sql.append(" AND ");
			sql.append("DATE_FORMAT(fiscal_year_month_date ,'%Y-%m') = ");
			sql.append("?");
			sql.append(")");
			
			
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			
			stmt.setString(1, displayDTO.getEmployeeId());
			stmt.setString(2, displayDTO.getYear() + '-' + displayDTO.getMonth());
			//stmt.setString(3, displayDTO.getMonth());
			ResultSet rs = stmt.executeQuery();
			// 検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				WorkScheduleDTO workScheduleDTO = new WorkScheduleDTO();
				workScheduleDTO.setDays(rs.getString("DATE_FORMAT(fiscal_year_month_date,'%e')"));
				workScheduleDTO.setStartTime(rs.getString("start_time"));
				workScheduleDTO.setEndTime(rs.getString("end_time"));
				workScheduleDTO.setRest(rs.getString("rest_time"));
				workScheduleDTO.setHolidayWorking(rs.getString("holiday_working_time"));
				workScheduleDTO.setHolidayPeriod(rs.getString("holiday_period_id"));
				workScheduleDTO.setHolidayDays(rs.getString("holiday_days"));
				workScheduleDTO.setHolidayTime(rs.getString("holiday_time"));
				workScheduleDTO.setLateTime(rs.getString("late_time"));
				workScheduleDTO.setRemarks(rs.getString("remarks"));
				workScheduleDTOList.add(workScheduleDTO);
			}
			// 検索結果DTOに検索結果を戻り値として返す。

			if (workScheduleDTOList.size() == 0) {

				workScheduleDTOList = null;
			}
			return workScheduleDTOList;
			
	}
}
