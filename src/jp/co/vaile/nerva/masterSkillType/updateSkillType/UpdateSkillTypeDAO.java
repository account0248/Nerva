package jp.co.vaile.nerva.masterSkillType.updateSkillType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateSkillTypeDAO {
	public void updateSkillType(UpdateSkillTypeDTO updateSkillTypeDTO, Boolean[] yearsDateOfAcquisition)
			throws SQLException, ClassNotFoundException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE ");
			sql.append(" m_skill_type ");
			sql.append(" SET ");
			sql.append(" skill_type_name " + " = ?, ");
			sql.append(" years_date_of_acquisition_flg " + " = ?, ");
			sql.append(" update_user " + " = ?, ");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" skill_type_id " + " = ? ");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.P1をDBのスキル種別マスタに更新処理を行う。
				for (int i = 0; i < updateSkillTypeDTO.getSkillTypeName().length; i++) {
					stmt.setString(1, updateSkillTypeDTO.getSkillTypeName()[i]);
					if (yearsDateOfAcquisition[i]) {
						stmt.setBoolean(2, true);
					} else {
						stmt.setBoolean(2, false);
					}
					stmt.setString(3, updateSkillTypeDTO.getUpdateUserId());
					stmt.setString(4, updateSkillTypeDTO.getSkillTypeId()[i]);
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
