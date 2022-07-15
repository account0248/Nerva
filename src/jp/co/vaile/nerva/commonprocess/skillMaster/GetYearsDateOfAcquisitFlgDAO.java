package jp.co.vaile.nerva.commonprocess.skillMaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class GetYearsDateOfAcquisitFlgDAO {
	/**
	 * スキル情報テーブルからスキル種別IDに紐づく年数/取得日フラグを取得する。
	 * @param  skillTypeId
	 * @return skillTypeDTOList スキル種別リスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Boolean getYearsDateOfAcquisit(String skillTypeId) throws SQLException, ClassNotFoundException {
		Boolean yearsDateOfAcquisition = false;
		try {
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ");
			sql.append(" years_date_of_acquisition_flg ");
			sql.append(" FROM ");
			sql.append(" m_skill_type ");
			sql.append(" WHERE ");
			sql.append(" skill_type_id = ? ");

			PreparedStatement stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, skillTypeId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				yearsDateOfAcquisition = rs.getBoolean("years_date_of_acquisition_flg");
			} else {
				yearsDateOfAcquisition = null;
			}
			return yearsDateOfAcquisition;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
