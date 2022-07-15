package jp.co.vaile.nerva.masterCompany.updateCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateCompanyDAO {

	/**
	 * 所属会社マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし更新する。
	 * @param updateCompanyDTO
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void updateCompany(UpdateCompanyDTO updateCompanyDTO) throws SQLException, ClassNotFoundException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE ");
			sql.append(" m_belong_company ");
			sql.append(" SET ");
			sql.append(" company_name " + " = ?, ");
			sql.append(" update_user " + " = ?, " );
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" company_id " + " = ? ");
			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.P1をDBの所属会社マスタに更新処理を行う。
				for (int i = 0; i < updateCompanyDTO.getCompanyName().length; i++) {
					stmt.setString(1, updateCompanyDTO.getCompanyName()[i]);
					stmt.setString(2, updateCompanyDTO.getUpdateUserId());
					stmt.setString(3, updateCompanyDTO.getCompanyId()[i]);
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
