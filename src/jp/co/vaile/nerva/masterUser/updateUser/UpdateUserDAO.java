package jp.co.vaile.nerva.masterUser.updateUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateUserDAO {

	public void updateUser(UpdateUserDTO updateUserDTO, boolean[] adminFlg)
			throws SQLException, ClassNotFoundException {

		// DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE ");
			sql.append(" m_user ");
			sql.append(" SET ");
			sql.append(" user_name " + " = ? " + ",");
			sql.append(" password " + " = ? " + ",");
			sql.append(" post_id " + " = ? " + ",");
			sql.append(" admin_flg " + " = ? " + ",");
			sql.append(" update_user " + " = ? " + ",");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" user_id " + " = ? ");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {

				// P1をDBのユーザーマスタに更新処理を行う。
				for (int i = 0; i < updateUserDTO.getTargetUserId().length; i++) {
					stmt.setString(1, updateUserDTO.getUserName()[i]);
					stmt.setString(2, updateUserDTO.getPassword()[i]);
					stmt.setString(3, updateUserDTO.getPost()[i]);
					stmt.setBoolean(4, adminFlg[i]);
					stmt.setString(5, updateUserDTO.getUpdateUserId());
					stmt.setString(6, updateUserDTO.getTargetUserId()[i]);

					stmt.executeUpdate();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
