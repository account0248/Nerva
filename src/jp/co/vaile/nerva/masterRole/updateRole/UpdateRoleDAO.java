package jp.co.vaile.nerva.masterRole.updateRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateRoleDAO {

	public void updateRole(UpdateRoleDTO updateRoleDTO) throws SQLException, ClassNotFoundException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE ");
			sql.append(" m_role ");
			sql.append(" SET ");
			sql.append(" role_name " + " = ?, ");
			sql.append(" update_user " + " = ?, ");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" role_id " + " = ? ");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.DBの担当マスタに更新処理を行う。
				for (int i = 0; i < updateRoleDTO.getRoleName().length; i++) {
					stmt.setString(1, updateRoleDTO.getRoleName()[i]);
					stmt.setString(2, updateRoleDTO.getUpdateUserId());
					stmt.setString(3, updateRoleDTO.getRoleId()[i]);
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
