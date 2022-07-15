package jp.co.vaile.nerva.masterRole.insertRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertRoleDAO {
	
	public void insertRole(InsertRoleDTO insertRoleDTO) throws ClassNotFoundException, SQLException {
		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO");
			sql.append(" m_role ");
			sql.append("(");
			sql.append(" role_id,");
			sql.append(" role_name,");
			sql.append(" regist_time,");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(" );
			sql.append("?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");

			
			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				//2.DBの担当マスタに登録処理を行う。
				stmt.setString(1, insertRoleDTO.getRoleId());
				stmt.setString(2, insertRoleDTO.getRoleName());
				stmt.setString(3, insertRoleDTO.getRegistUserId());
				stmt.executeUpdate();
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
