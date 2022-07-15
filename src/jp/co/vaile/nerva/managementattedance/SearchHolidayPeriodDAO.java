package jp.co.vaile.nerva.managementattedance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

/**
 * 
 * @author konishi.tokinori
 *
 */
public class SearchHolidayPeriodDAO {

	/**
	 * 休暇期間等マスタテーブルに登録されている内容をすべて取得し、
	 * 取得した内容をリターンする。
	 * @return holidayPeriodDTOList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<HolidayPeriodDTO> serchHolidayPeriodDTOList() throws SQLException, ClassNotFoundException {
		List<HolidayPeriodDTO> holidayPeriodDTOList = new ArrayList<>();
		
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" SELECT ");
			sql.append("holiday_period_id" + ",");
			sql.append(" holiday_period_name" + ",");
			sql.append(" holiday_days" + ",");
			sql.append(" holiday_days_flg" + ",");
			sql.append(" holiday_time" + ",");
			sql.append(" holiday_time_flg" + ",");
			sql.append(" late_time" + ",");
			sql.append(" late_time_flg");
			sql.append(" FROM ");
			sql.append("m_holiday_period");

			PreparedStatement stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			// 検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				HolidayPeriodDTO holidayPeriodDTO = new HolidayPeriodDTO();
				holidayPeriodDTO.setHolidayId(rs.getString("holiday_period_id"));
				holidayPeriodDTO.setHolidayName(rs.getString("holiday_period_name"));
				holidayPeriodDTO.setHoliday_days(rs.getString("holiday_days"));
				holidayPeriodDTO.setHoliday_days_flg(rs.getBoolean("holiday_days_flg"));
				holidayPeriodDTO.setHoliday_time(rs.getString("holiday_time"));
				holidayPeriodDTO.setHoliday_time_flg(rs.getBoolean("holiday_time_flg"));
				holidayPeriodDTO.setLate_time(rs.getString("late_time"));
				holidayPeriodDTO.setLate_time_flg(rs.getBoolean("late_time_flg"));
				holidayPeriodDTOList.add(holidayPeriodDTO);
			}
			// 検索結果DTOに検索結果を戻り値として返す。
			return holidayPeriodDTOList;
			

	}
}
