package jp.co.vaile.nerva.detailProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class ProjectDetailPageDAO {

	//案件IDを基に案件情報テーブル、ユーザーマスタ、所属会社マスタ、発注元テーブル,業種マスタからデータを取得するSQL
	static final String SELECT_BY_PROJECT_ID = "SELECT project_info.project_info_id , project_info.project_id , project_info.project_name , "
			+ "project_info.responsible_id , m_user.user_name , "
			+ "project_info.contractor_id , m_belong_company.company_name , "
			+ "project_info.client_id , order_source.order_source_name , "
			+ "project_info.industry_id , m_industry.industry_name , "
			+ "project_info. project_start_date , project_info.project_complete_date "
			+ "FROM project_info "
			+ "INNER JOIN m_user ON project_info.responsible_id = m_user.user_id "
			+ "INNER JOIN order_source ON project_info.client_id = order_source.order_source_id "
			+ "INNER JOIN m_industry ON project_info.industry_id = m_industry.industry_id "
			+ "INNER JOIN m_belong_company ON project_info.contractor_id = m_belong_company.company_id "
			+ "WHERE project_info.latest_flg = false "
			+ "AND project_info.project_id = ?";

	/**
	 * 案件IDを基に案件名、責任者名、受注元名、発注元名、業種名、案件開始日、案件完了日を
	 * 案件情報テーブル、ユーザーマスタ、所属会社マスタ、発注元テーブル,業種マスタから取得する
	 * @param projectId 案件ID
	 * @return projectDetailPageDTO 案件詳細情報ページDTO
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public ProjectDetailPageDTO selectFromProjectDetail(String projectId) throws ClassNotFoundException, SQLException {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //Date型String変換用フォーマット
		String projectStartDate; //案件開始日String格納用
		String projectCompleteDate; //案件完了日String格納用

		//案件情報詳細ページDTOを作成する
		ProjectDetailPageDTO ProjectDetailPageDto = new ProjectDetailPageDTO();
		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_BY_PROJECT_ID)) {
				//案件IDを基にDBから案件情報テーブル,ユーザーマスタ,所属会社マスタ,発注元テーブル,業種マスタの情報を取得する
				stmt.setString(1, projectId);
				//取得したデータを案件詳細情報ページDTOに格納する
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					ProjectDetailPageDto.setProjectInfoId(rs.getInt("project_info_id"));
					ProjectDetailPageDto.setProjectId(rs.getString("project_id"));
					ProjectDetailPageDto.setProjectName(rs.getString("project_name"));
					ProjectDetailPageDto.setResponsibleId(rs.getString("responsible_id"));
					ProjectDetailPageDto.setResponsibleName(rs.getString("user_name"));
					ProjectDetailPageDto.setContractorId(rs.getString("contractor_id"));
					ProjectDetailPageDto.setContractorName(rs.getString("company_name"));
					ProjectDetailPageDto.setClientId(rs.getInt("client_id"));
					ProjectDetailPageDto.setClientName(rs.getString("order_source_name"));
					ProjectDetailPageDto.setIndustryId(rs.getString("industry_id"));
					ProjectDetailPageDto.setIndustryName(rs.getString("industry_name"));
					/*Date型はStringに変換してから格納*/
					projectStartDate = simpleDateFormat.format(rs.getDate("project_start_date"));
					ProjectDetailPageDto.setProjectStartDate(projectStartDate);
					/*案件完了日はnullの場合があるのでチェック*/
					if (rs.getDate("project_complete_date") != null) {
						projectCompleteDate = simpleDateFormat.format(rs.getDate("project_complete_date"));
						ProjectDetailPageDto.setProjectCompleteDate(projectCompleteDate);
					}
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
		//案件詳細情報ページDTOを返す
		return ProjectDetailPageDto;
	}

}
