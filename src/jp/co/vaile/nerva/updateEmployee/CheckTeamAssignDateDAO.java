package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class CheckTeamAssignDateDAO {
	public final String SELECT_TEAM_ASSIGN_COMPLETE_DATE_BY_TEAM_ID = "SELECT assign_complete_date FROM project_detail_info "
			+ "WHERE team_id = ? AND latest_flg = false";
	public final String SELECT_TEAM_ASSIGN_COMPLETE_DATE_BY_EMPLOYEE_EXPERIENCE_ID = "SELECT assign_complete_date FROM project_detail_info "
			+ "WHERE team_id = (SELECT team_id FROM employee_experience WHERE employee_experience_id = ?) AND latest_flg = false";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


	boolean checkTeamAssignCompleteDate(String teamId, String teamBelongCompleteStr) throws Exception {
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SELECT_TEAM_ASSIGN_COMPLETE_DATE_BY_TEAM_ID);
			pStmt.setString(1, teamId);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				Date assignCompleteDate = rs.getDate("assign_complete_date");
				Date teamBelongCompleteDate = sdf.parse(teamBelongCompleteStr);
				if (assignCompleteDate != null) {
					if (assignCompleteDate.compareTo(teamBelongCompleteDate) < 0) {
						return false;
					} else {
						return true;
					}
				} else {
					return true;
				}
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	boolean checkTeamAssignCompleteDate(int employeeExperienceId, String teamBelongCompleteStr) throws Exception {
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SELECT_TEAM_ASSIGN_COMPLETE_DATE_BY_EMPLOYEE_EXPERIENCE_ID);
			pStmt.setInt(1, employeeExperienceId);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				Date assignCompleteDate = rs.getDate("assign_complete_date");
				Date teamBelongCompleteDate = sdf.parse(teamBelongCompleteStr);
				if (assignCompleteDate != null) {
					if (assignCompleteDate.compareTo(teamBelongCompleteDate) < 0) {
						return false;
					} else {
						return true;
					}
				} else {
					return true;
				}
			} else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
