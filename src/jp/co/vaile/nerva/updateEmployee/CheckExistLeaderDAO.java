package jp.co.vaile.nerva.updateEmployee;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class CheckExistLeaderDAO {
	private final String SELECT_TEAM_LEADER = "SELECT employee_experience_id"
			+ " FROM employee_experience WHERE team_id = ? AND role_id = ? AND latest_flg = false "
			+ " AND team_belong_start_date < CURRENT_DATE "
			+ " AND (CASE WHEN team_belong_complete_date IS NOT NULL THEN "
			+ " (CASE WHEN team_belong_complete_date > ? THEN TRUE ELSE FALSE END) "
			+ " ELSE TRUE END)";

	boolean isThisExistDB(String teamId,String teamBelongStartDate) throws ClassNotFoundException, SQLException {
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SELECT_TEAM_LEADER);
			pStmt.setString(1, teamId);
			pStmt.setString(2, LEADER);
			pStmt.setString(3,teamBelongStartDate);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
