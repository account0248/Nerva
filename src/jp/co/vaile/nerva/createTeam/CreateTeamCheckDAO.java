package jp.co.vaile.nerva.createTeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class CreateTeamCheckDAO {
	private final String SELECT_TEAM_ID_NUM = "SELECT COUNT(team_id) FROM (SELECT team_id FROM team_info WHERE team_id = ? )AS team_id";

	public int selectTeamId(String teamId) throws ClassNotFoundException, SQLException{
		int targetNum = 0;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstatement = connection.prepareStatement(SELECT_TEAM_ID_NUM)) {
			pstatement.setString(1, teamId); // チームID
			try (ResultSet rs = pstatement.executeQuery()) {
				while (rs.next()) {
					targetNum = rs.getInt("COUNT(team_id)");// チームID数
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return targetNum;
	}
}
