package jp.co.vaile.nerva.commonprocess.skillMaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class GetSkillTypeDAO {
	/**
	 * スキル種別を全件取得する。
	 * @param 
	 * @return skillTypeDTOList スキル種別リスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SkillTypeDTO> getSkillType() throws SQLException, ClassNotFoundException {
		List<SkillTypeDTO> skillTypeDTOList = new ArrayList<>();
		try {
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT ");
			sql.append(" * ");
			sql.append(" FROM ");
			sql.append(" m_skill_type ");

			PreparedStatement stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				SkillTypeDTO skillTypeDTO = new SkillTypeDTO();
				skillTypeDTO.setSkillTypeId(rs.getString("skill_type_id"));
				skillTypeDTO.setSkillTypeName(rs.getString("skill_type_name"));
				skillTypeDTO.setYearsDateOfAcquisition(rs.getBoolean("years_date_of_acquisition_flg"));
				skillTypeDTOList.add(skillTypeDTO);
			}
			return skillTypeDTOList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}
}
