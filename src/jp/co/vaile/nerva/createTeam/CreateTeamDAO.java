package jp.co.vaile.nerva.createTeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class CreateTeamDAO {
		private final String INSERT_TEAM_INFO = "INSERT INTO team_info  (" +
				"team_id," +
				"team_name," +
				"team_manager," +
				"regist_time," +
				"regist_user," +
				"delete_flg)" +
				"VALUES(?,?,?,CURRENT_TIMESTAMP,?,0);";

		/**
		 * DBのチーム情報テーブルに引数のチーム情報DTOを登録する。
		 * @param createTeamInfoDTO 登録される従業員情報
		 * @throws ClassNotFoundException 
		 * @throws SQLException 
		 */
		 int insertTeamInfo(CreateTeamInfoDTO createTeamInfoDTO) throws ClassNotFoundException, SQLException {
			int result = 0;
			//DBに接続する。
			try (Connection con = DBConnection.getConnection()) {
				try (PreparedStatement stmt = con.prepareStatement(INSERT_TEAM_INFO);) {
					stmt.setString(1, createTeamInfoDTO.getTeamId());
					stmt.setString(2, createTeamInfoDTO.getTeamName());
					stmt.setString(3, createTeamInfoDTO.getManagerId());
					stmt.setString(4, createTeamInfoDTO.getRegistUser());
					result = stmt.executeUpdate();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw e;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}

			return result;
		}
	}
