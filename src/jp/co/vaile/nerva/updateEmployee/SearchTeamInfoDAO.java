package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SearchTeamInfoDAO {
	private final String SELECT_TEAM_INFO = "SELECT COALESCE(project_name, '-') AS project_name,  "
			+ "COALESCE(industry_name, '-') AS industry_name, COALESCE(company_name, '-') AS company_name, "
			+ "COALESCE(user_name, '-') AS user_name, team_name , team_info.team_id AS team_id "
			+ "FROM team_info "
			+ "LEFT OUTER JOIN project_detail_info ON team_info.team_id = project_detail_info.team_id "
			+ "AND project_detail_info.latest_flg = false "
			+ "LEFT OUTER JOIN project_info ON project_detail_info.project_info_id = project_info.project_info_id "
			+ "LEFT OUTER JOIN m_industry ON project_info.industry_id = m_industry.industry_id "
			+ "LEFT OUTER JOIN m_belong_company ON project_info.contractor_id = m_belong_company.company_id "
			+ "LEFT OUTER JOIN m_user ON team_info.team_manager = m_user.user_id "
			+ "WHERE team_info.delete_flg = false AND team_manager = ?";

	 /**
	  * チーム情報テーブルからチーム名を取得し、チームが担当している案件を取得する。
	 * @return
	 * @throws ClassNotFoundException, SQLException 
	 */
	List<TeamInfoDTO> searchTeamInfo(String loginUserId) throws ClassNotFoundException, SQLException {
		List<TeamInfoDTO> teamInfoDTOList = new ArrayList<TeamInfoDTO>();
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SELECT_TEAM_INFO);
			pStmt.setString(1, loginUserId);
			ResultSet rs = pStmt.executeQuery();
			//取得した値を従業員業務経験DTOのリストに格納する。
			while (rs.next()) {
				TeamInfoDTO teamInfoDTO = new TeamInfoDTO();
				teamInfoDTO.setProjectName(rs.getString("project_name"));
				teamInfoDTO.setResponsibleIndustry(rs.getString("industry_name"));
				teamInfoDTO.setResposibleProjectCompany(rs.getString("company_name"));
				teamInfoDTO.setTeamManagerName(rs.getString("user_name"));
				teamInfoDTO.setTeamName(rs.getString("team_name"));
				teamInfoDTO.setTeamId(rs.getString("team_id"));
				teamInfoDTOList.add(teamInfoDTO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//戻り値を返す。
		return teamInfoDTOList;
	}

}
