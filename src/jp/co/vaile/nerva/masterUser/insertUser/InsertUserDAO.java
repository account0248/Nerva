package jp.co.vaile.nerva.masterUser.insertUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertUserDAO {

	public void insertUser(InsertUserDTO insertUserDTO, boolean adminFlg) throws ClassNotFoundException, SQLException {

		// DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO");
			sql.append(" m_user ");
			sql.append("(");
			sql.append(" user_id,");
			sql.append(" password,");
			sql.append(" user_name,");
			sql.append(" company_id,");
			sql.append(" post_id,");
			sql.append(" department,");
			sql.append(" admin_flg,");
			sql.append(" regist_time,");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(");
			sql.append("?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {

				// DBのユーザーマスタに登録処理を行う。
				stmt.setString(1, insertUserDTO.getTargetUserId());
				stmt.setString(2, insertUserDTO.getPassword());
				stmt.setString(3, insertUserDTO.getUserName());
				stmt.setString(4, insertUserDTO.getCompany());
				stmt.setString(5, insertUserDTO.getPost());
				stmt.setString(6, "NULL");
				stmt.setBoolean(7, adminFlg);
				stmt.setString(8, insertUserDTO.getInsertUserId());
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
