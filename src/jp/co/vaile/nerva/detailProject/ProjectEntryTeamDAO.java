package jp.co.vaile.nerva.detailProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class ProjectEntryTeamDAO {


	//案件詳細テーブル,チーム情報テーブル,従業員業務経験テーブル,従業員情報テーブル,所属会社マスタ,ユーザーマスタを案件情報IDで参照するSQL文
	static final String SELECT_FROM_PROJECT_ID =
			"SELECT                                                                                       "+
			"DISTINCT project_detail_info.project_detail_id AS project_detail_id,                                  "+
			"team_info.team_id AS team_id,                                                                "+
			"team_info.team_name AS team_name,                                                            "+
			"m_belong_company.company_id AS company_id,                                                   "+
			"m_belong_company.company_name AS company_name,                                               "+
			"m_user.user_id AS user_id,                                                                   "+
			"m_user.user_name AS user_name,                                                               "+
			"team_leader.employee_id AS employee_id,                                                      "+
			"team_leader.employee_name AS employee_name,                                                  "+
			"project_detail_info.assign_start_date AS assign_start_date,                                  "+
			"project_detail_info.assign_complete_date AS assign_complete_date                             "+
			"FROM team_info                                                                               "+
			"LEFT JOIN m_user                                                                             "+
			"ON team_info.team_manager = m_user.user_id                                                   "+
			"LEFT JOIN m_belong_company                                                                   "+
			"ON m_user.company_id = m_belong_company.company_id                                           "+
			"LEFT JOIN(                                                                                   "+
			"SELECT                                                                                       "+
			"      employee_info.employee_name,                                                           "+
			"      employee_info.employee_id,                                                             "+
			"      employee_experience.team_id                                                            "+
			"    FROM                                                                                     "+
			"      employee_experience                                                                    "+
			"      LEFT JOIN employee_info ON employee_experience.employee_id = employee_info.employee_id "+
			"    WHERE                                                                                    "+
			"      employee_experience.team_belong_start_date <= CURRENT_DATE                             "+
			"      AND (                                                                                  "+
			"        employee_experience.team_belong_complete_date IS NULL                                "+
			"        OR CURRENT_DATE <= employee_experience.team_belong_complete_date                     "+
			"      )                                                                                      "+
			"      AND employee_info.delete_flg = false                                                   "+
			"      AND employee_experience.latest_flg = false                                             "+
			"      AND employee_experience.role_id = 'T000000001'                                         "+
			"      AND employee_experience.regist_time IN (                                               "+
			"        SELECT                                                                               "+
			"          MAX(regist_time)                                                                   "+
			"        FROM                                                                                 "+
			"          employee_experience                                                                "+
			"        GROUP BY                                                                             "+
			"          employee_experience.employee_id                                                    "+
			"      )                                                                                      "+
			"      )AS team_leader                                                                        "+
			"ON team_info.team_id = team_leader.team_id                                                   "+
			"LEFT JOIN project_detail_info                                                                "+
			"ON team_info.team_id = project_detail_info.team_id                                           "+
			"WHERE project_detail_info.latest_flg = false                                                 "+
			"AND project_detail_info.project_info_id = ?                                                  "+
	        "AND team_info.delete_flg = false												  ";

	//チーム情報テーブルの所属会社IDをチームIDで参照するSQL
	static final String SELECT_TEAM_BELONG_COMPANY = "SELECT m_user.company_id "
			+ "FROM team_info "
			+ "INNER JOIN m_user ON team_info.team_manager = m_user.user_id "
			+ "WHERE team_info.team_id = ?";

	/**
	 * 案件情報IDを基にDBの案件詳細テーブル,チーム情報テーブル,従業員業務経験テーブル,従業員情報テーブル,所属会社マスタ,ユーザーマスタ
	 * からデータを取得し、案件参加チームDTOに格納する。
	 * 案件参加チームDTOのリストに格納する。案件参加チームDTOのリストを返す。
	 * @param projectInfoId 案件情報ID
	 * @return ProjectEntryTeamList 案件参加チームDTOのリスト
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<ProjectEntryTeamDTO> selectEntryTeam(int projectInfoId) throws SQLException, ClassNotFoundException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //Date型String変換用フォーマット
		String assignStartDate;
		String assignComplteDate;
		
		//案件参加チームDTOのリストを作成する
		ArrayList<ProjectEntryTeamDTO> ProjectEntryTeamList = new ArrayList<ProjectEntryTeamDTO>();

		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_FROM_PROJECT_ID)) {

				//案件情報IDを基にDBから情報を取得する
				stmt.setInt(1, projectInfoId);
				/*取得件数分リストに格納する処理*/
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					//案件参加チームDTOを作成する
					ProjectEntryTeamDTO projectEntryTeamDto = new ProjectEntryTeamDTO();
					//取得したデータを案件参加チームDTOに格納する
					projectEntryTeamDto.setProjectDetailId(rs.getInt("project_detail_id"));
					projectEntryTeamDto.setTeamId(rs.getString("team_id"));
					projectEntryTeamDto.setTeamName(rs.getString("team_name"));
					projectEntryTeamDto.setBelongCompanyId(rs.getString("company_id"));
					projectEntryTeamDto.setBelongCompanyName(rs.getString("company_name"));
					projectEntryTeamDto.setTeamManagerId(rs.getString("user_id"));
					projectEntryTeamDto.setTeamManagerName(rs.getString("user_name"));
					projectEntryTeamDto.setTeamLeaderId(rs.getString("employee_id"));
					projectEntryTeamDto.setTeamLeaderName(rs.getString("employee_name"));
					/*Date型はStringに変換してから格納*/
					assignStartDate = simpleDateFormat.format(rs.getDate("assign_start_date"));
					projectEntryTeamDto.setAssignStartDate(assignStartDate);
					/*配属完了日はnullの場合があるのでチェック*/
					if (rs.getDate("assign_complete_date") != null) {
						assignComplteDate = simpleDateFormat.format(rs.getDate("assign_complete_date"));
						projectEntryTeamDto.setAssignCompleteDate(assignComplteDate);
					}
					//案件参加チームDTOを案件参加チームDTOのリストに格納する
					ProjectEntryTeamList.add(projectEntryTeamDto);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
		//案件参加チームDTOのリストを返す
		return ProjectEntryTeamList;
	}
	/**
	 * チームIDを基にチーム情報テーブルからチームの所属会社IDを取得する
	 * チームの所属会社IDを返す
	 * @param teamId チームID
	 * @return teamBelongCompany チームの所属会社ID
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	String selectTeamBelongCompany(String teamId) throws ClassNotFoundException, SQLException {
		String teamBelongCompany = ""; //チームの所属会社ID

		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_TEAM_BELONG_COMPANY);) {

				//チームIDを基にチーム情報テーブルから所属会社IDを取得する
				stmt.setString(1, teamId);

				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					teamBelongCompany = rs.getString("company_id");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
		//所属会社IDを返す
		return teamBelongCompany;

	}
}
