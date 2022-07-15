package jp.co.vaile.nerva.detailEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class EmpSkillDAO {
	//従業員IDをもとに従業員のスキル情報をDBから検索するSQL分の文字列
	private final String SELECT_BY_EMP_ID_FROM_SKILL_INFO = "SELECT skill_info_id, "
			+ "skill_info.skill_type_id AS skill_type_id, skill_type_name, skill_detail, "
			+ "COALESCE(experience_years, '-') AS experience_years,"
			+ " COALESCE(DATE_FORMAT(acquisition_year_month,'%Y-%m'), '-') AS acquisition_year_month, "
			+ " m_skill_type.years_date_of_acquisition_flg " + " FROM skill_info JOIN m_skill_type "
			+ " ON m_skill_type.skill_type_id = skill_info.skill_type_id "
			+ " WHERE employee_id = ? AND latest_flg = false ";

	/**
	 * 呼び出し元から渡された従業員IDをもとにスキル情報DTOのリストにDBから取得した情報を格納する。
	 * 
	 * @param employeeId 従業員IDを表す文字列
	 * @return empSkillInfoDTOList スキル情報DTOを格納したリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<EmpSkillInfoDTO> selectEmpSkillByEmpId(String employeeId) throws ClassNotFoundException, SQLException {
		List<EmpSkillInfoDTO> empSkillInfoDTOList = new ArrayList<EmpSkillInfoDTO>();
		//従業員IDをもとにスキル情報テーブルから内容・経験年数・取得年月、スキル種別マスタからスキル種別名を取得する。
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SELECT_BY_EMP_ID_FROM_SKILL_INFO);
			pStmt.setString(1, employeeId);
			ResultSet rs = pStmt.executeQuery();
			//取得した値をスキル情報DTOのリストに格納する。
			while (rs.next()) {
				EmpSkillInfoDTO empSkillInfoDTO = new EmpSkillInfoDTO();
				empSkillInfoDTO.setSkillInfoId(rs.getInt("skill_info_id"));
				empSkillInfoDTO.setSkillTypeId(rs.getString("skill_type_id"));
				empSkillInfoDTO.setSkillType(rs.getString("skill_type_name"));
				empSkillInfoDTO.setSkillDetail(rs.getString("skill_detail"));
				empSkillInfoDTO.setExperienceYears(rs.getString("experience_years"));
				empSkillInfoDTO.setAcquisitionYearMonth(rs.getString("acquisition_year_month"));
				empSkillInfoDTO.setYearsDateOfAcquisitionFlg(rs.getBoolean("years_date_of_acquisition_flg"));
				empSkillInfoDTOList.add(empSkillInfoDTO);
			}
			return empSkillInfoDTOList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
