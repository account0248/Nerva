package jp.co.vaile.nerva.masterIndustry.updateIndustry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateIndustryDAO {

	/**
	 * 業種マスタメンテナンス画面で入力された値を持つDTOを用いてDBにアクセスし更新する。
	 * 
	 * @param updateIndustryDTO 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 **/
	public void updateIndustry(UpdateIndustryDTO updateIndustryDTO) throws SQLException, ClassNotFoundException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE ");
			sql.append(" m_industry ");
			sql.append(" SET ");
			sql.append(" industry_name " + " = ?, ");
			sql.append(" update_user " + " = ?, ");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" industry_id " + " = ? ");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.P1をDBの業種マスタに更新処理を行う。
				for (int i = 0; i < updateIndustryDTO.getIndustryName().length; i++) {
					stmt.setString(1, updateIndustryDTO.getIndustryName()[i]);
					stmt.setString(2, updateIndustryDTO.getUpdateUserId());
					stmt.setString(3, updateIndustryDTO.getIndustryId()[i]);
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
