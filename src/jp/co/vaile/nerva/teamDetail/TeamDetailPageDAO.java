package jp.co.vaile.nerva.teamDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class TeamDetailPageDAO {

	private final String SELECT_BY_TEAM_ID =
	"		SELECT                                                                                              "+
	"        team_info.team_id                                                                                  "+
	"        ,team_info.team_name                                                                               "+
	"        ,m_user.user_name                                                                                  "+
	"        ,m_belong_company.company_id                                                                       "+
	"        ,team_leader.employee_name                                                                         "+
	"        ,project_info.project_id                                                                           "+
	"        ,project_info.project_name                                                                         "+
	"    FROM                                                                                                   "+
	"        team_info                                                                                          "+
	"            LEFT JOIN employee_experience                                                                  "+
	"                ON team_info.team_id = employee_experience.team_id                                         "+
	"            LEFT JOIN (                                                                                    "+
	"                SELECT                                                                                     "+
	"                        employee_info.employee_name                                                        "+
	"                        ,employee_info.employee_id                                                         "+
	"                        ,employee_experience.team_id                                                       "+
	"                    FROM                                                                                   "+
	"                        employee_experience                                                                "+
	"                            LEFT JOIN employee_info                                                        "+
	"                                ON employee_experience.employee_id = employee_info.employee_id             "+
	"                    WHERE                                                                                  "+
	"                        employee_experience.team_belong_start_date <= CURRENT_DATE                         "+
	"                        AND (                                                                              "+
	"                            employee_experience.team_belong_complete_date IS NULL                          "+
	"                            OR employee_experience.team_belong_complete_date >= CURRENT_DATE               "+
	"                        )                                                                                  "+
	"                        AND employee_experience.latest_flg = false                                         "+
	"                        AND employee_experience.role_id = 'T000000001'                                     "+
	"                        AND employee_experience.regist_time IN (                                           "+
	"                            SELECT                                                                         "+
	"                                    MAX(regist_time)                                                       "+
	"                                FROM                                                                       "+
	"                                    employee_experience                                                    "+
	"                                GROUP BY                                                                   "+
	"                                    employee_experience.employee_id                                        "+
	"                        )                                                                                  "+
	"            ) AS team_leader                                                                               "+
	"                ON employee_experience.team_id = team_leader.team_id                                       "+
	"            LEFT JOIN m_user                                                                               "+
	"                ON team_info.team_manager = m_user.user_id                                                 "+
	"            LEFT JOIN m_belong_company                                                                     "+
	"                ON m_user.company_id = m_belong_company.company_id                                         "+
	"            LEFT JOIN project_detail_info                                                                  "+
	"                ON team_info.team_id = project_detail_info.team_id                                         "+
	"            LEFT JOIN project_info                                                                         "+
	"                ON project_detail_info.project_info_id = project_info.project_info_id                      "+
	"                AND project_detail_info.latest_flg = false                                                 "+
	"    WHERE                                                                                                  "+
	"        team_info.team_id = ?                                                                   "+
	"    ORDER BY                                                                                               "+
	"        employee_experience.team_belong_start_date DESC LIMIT 1;                                           ";

	/**
	 * チームIDを基にチーム名、担当部長、チームリーダー、担当案件ID(P0~形式)、担当案件を
	 * チーム情報テーブル、ユーザー管理マスタ、従業員情報、案件情報から取得する
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public TeamDetailPageDTO selectFromTeamEntryEmp(String teamId) throws ClassNotFoundException, SQLException {

		TeamDetailPageDTO teamDetailPageDTO = new TeamDetailPageDTO();

		//	DBに接続する
		try (Connection con = DBConnection.getConnection()) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_BY_TEAM_ID);) {
				// チームIDを基にDBからチーム情報テーブル、ユーザー管理マスタ、従業員情報、案件情報から取得する
				stmt.setString(1, teamId);
				// 取得したデータを案件詳細情報ページDTOに格納する
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					// 取得したデータをチーム情報DTOに格納する
					teamDetailPageDTO.setTeamId(rs.getString("team_id"));
					teamDetailPageDTO.setTeamName(rs.getString("team_name"));
					teamDetailPageDTO.setResponsibleManager(rs.getString("user_name"));
					teamDetailPageDTO.setCompanyId(rs.getString("company_id"));
					teamDetailPageDTO.setTeamLeaderName(rs.getString("employee_name"));
					teamDetailPageDTO.setResponsibleProjectId(rs.getString("project_id"));
					teamDetailPageDTO.setResponsibleProjectName(rs.getString("project_name"));
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//	チーム情報DTOを返す
		return teamDetailPageDTO;
	}
}
