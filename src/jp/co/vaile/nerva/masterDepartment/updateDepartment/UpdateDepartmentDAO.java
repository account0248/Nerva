package jp.co.vaile.nerva.masterDepartment.updateDepartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateDepartmentDAO {

	/**
	 * 所属組織マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし更新する。
	 * 
	 * @param updateDepartmentDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 **/
	public void updateDepartment(UpdateDepartmentDTO updateDepartmentDTO) throws SQLException, ClassNotFoundException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE ");
			sql.append(" m_belong_department ");
			sql.append(" SET ");
			sql.append(" department_name " + " = ?, ");
			sql.append(" update_user " + " = ?, ");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" department_id " + " = ? ");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.P1をDBの所属組織マスタに更新処理を行う。
				for (int i = 0; i < updateDepartmentDTO.getDepartmentName().length; i++) {
					stmt.setString(1, updateDepartmentDTO.getDepartmentName()[i]);
					stmt.setString(2, updateDepartmentDTO.getUpdateUserId());
					stmt.setString(3, updateDepartmentDTO.getDepartmentId()[i]);
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
