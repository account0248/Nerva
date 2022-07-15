package jp.co.vaile.nerva.masterDepartment.insertDepartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertDepartmentDAO {

	/**
	 * 所属組織マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし登録する。
	 * 
	 * @param insertDepartmentDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public void insertDepartment(InsertDepartmentDTO insertDepartmentDTO) throws ClassNotFoundException, SQLException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO");
			sql.append(" m_belong_department ");
			sql.append("(");
			sql.append(" department_id,");
			sql.append(" department_name,");
			sql.append(" regist_time,");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(");
			sql.append("?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.P1をDBの所属組織マスタに登録処理を行う。
				stmt.setString(1, insertDepartmentDTO.getDepartmentId());
				stmt.setString(2, insertDepartmentDTO.getDepartmentName());
				stmt.setString(3, insertDepartmentDTO.getRegistUserId());
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
