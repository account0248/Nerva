package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SelectTeamManagerDAO {
	//チームIDと日付情報をもとに現在所属しているチームの部長IDを取得するSQL文を表す。
	private final String SELECT_TEAM_MANAGER = "SELECT team_manager FROM employee_experience JOIN team_info "
			+ "ON employee_experience.team_id = team_info.team_id WHERE team_belong_start_date <= CURRENT_DATE "
			+ "AND (CASE WHEN team_belong_complete_date IS NOT NULL THEN (CASE WHEN team_belong_complete_date >= "
			+ "CURRENT_DATE THEN TRUE ELSE FALSE END ) ELSE TRUE END ) "
			+ "AND employee_experience.latest_flg = false AND employee_id = ? ORDER BY team_belong_start_date DESC "
			+ "LIMIT 1";

	/**
	 * チームIDと日付情報ををもとに現在所属しているチームの部長IDを取得する。
	 * @param employeeId 従業員IDを表す文字列
	 * @return 従業員が現在所属しているチームの所属部長ID
	 * @throws Exception
	 */
	String SelectTeamManager(String employeeId) throws Exception {
		String teamManagerId = null;
		//DBに接続する。
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SELECT_TEAM_MANAGER);
			pStmt.setString(1, employeeId);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				teamManagerId = rs.getString("team_manager");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//戻り値を返す。
		return teamManagerId;
	}

}
