package jp.co.vaile.nerva.searchProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SearchProjectDAO {

	//sql文
	static String sql;

	static ResultSet rs;

	public static Statement s;

	static final String SELECT = " SELECT ";

	static final String LEFT_OUTER_JOIN = " LEFT OUTER JOIN ";

	static final String ON = " ON ";

	static final String GROUP_BY = " GROUP BY ";

	static final String WH = " WHERE ";

	static final String FROM = " FROM ";

	static final String AS = " AS ";

	static final String AND = " AND ";

	static final String COUNT_ALL = " COUNT( * ) ";

	static final String COUNT_PROJECT_ID = " COUNT(project_id) ";

	static final String LIKE = " LIKE '%";

	//join用テーブル
	static final String PROJECT_INFO = " project_info ";

	static final String PROJECT_DETAIL_INFO = " project_detail_info ";

	static final String TEAM_INFO = " team_info ";

	static final String M_USER = " m_user ";

	static final String M_INDUSTRY = " m_industry ";

	static final String ORDER_SOURCE = " order_source ";

	static final String M_BELONG_COMPANY = " m_belong_company ";

	static final String PROJECT = " project ";

	static final String PROJECT_TEAM = " project_team ";

	static final String PROJECT_EMP = " project_emp ";

	static final String EMP_EXPERIENCE = " employee_experience ";

	static final String PROJECT_EMP_EXPERIENCE = " project_employee_experience ";

	//取得カラム
	static final String PROJECT_ID = " project_id ";

	static final String PROJECT_NAME = " project_name ";

	static final String PROJECT_START_DATE = " project_start_date ";

	static final String PROJECT_COMPLETE_DATE = " project_complete_date ";

	static final String ORDER_SOURCE_NAME = " order_source_name ";

	static final String INDUSTRY_NAME = " industry_name ";

	static final String USER_NAME = " user_name ";

	static final String COMPANY_NAME = " company_name ";

	static final String TOTAL_TEAMS_PROJECT = " total_teams_project ";

	static final String TOTAL_EMP_PROJECT = " total_emp_project ";

	//join用カラム
	static final String REGIST_USER = " regist_user ";

	static final String USER_ID = " user_id ";

	static final String INDUSTRY_ID = " industry_id ";

	static final String CLIENT_ID = " client_id ";

	static final String ORDER_SOURCE_ID = " order_source_id ";

	static final String CONTRACTOR_ID = " contractor_id ";

	static final String COMPANY_ID = " company_id ";

	static final String TEAM_ID = " team_id ";

	static final String LASTEST_FLG = " lastest_flg ";

	static final String LATEST_FLG = " latest_flg ";

	static final String PROJECT_INFO_ID = " project_info_id ";

	static final String EMP_ID = " employee_id ";

	static final String PROJECT_FLG = " project_flg ";

	//案件検索実行
	public static ArrayList<SearchProjectDTO> searchProject(SearchProjectPageDTO searchProjectPageDTO)
			throws SQLException, ClassNotFoundException {

		ArrayList<SearchProjectDTO> searchProjectDTOArr = new ArrayList<SearchProjectDTO>();

		String projectId = searchProjectPageDTO.getProjectId();
		String projectName = searchProjectPageDTO.getProjectName();
		String orderSourceName = searchProjectPageDTO.getClientName();
		String industryId = searchProjectPageDTO.getInductryId();
		String responsibleName = searchProjectPageDTO.getResponsibleName();
		String companyId = searchProjectPageDTO.getContratorId();
		String totalTeamsProject = searchProjectPageDTO.getTotalTeamsProject();
		String totalEmpProject = searchProjectPageDTO.getTotalEmpProject();

		String whProjectId = "";
		String whProjectName = "";
		String whOrderName = "";
		String whIndustryId = "";
		String whUserName = "";
		String whCompanyId = "";
		String whTotalTeamsProject = "";
		String whTotalEmpProject = "";

		if (projectId != "") {
			whProjectId = PROJECT_INFO + "." + PROJECT_ID + " = '" + projectId + "'" + AND;
		}
		if (projectName != "") {
			whProjectName = PROJECT_INFO + "." + PROJECT_NAME + LIKE + projectName + "%' " + AND;
		}
		if (orderSourceName != null) {
			whOrderName = ORDER_SOURCE + "." + ORDER_SOURCE_NAME + " =  '" + orderSourceName + "'" + AND;
		}
		if (industryId != "") {
			whIndustryId = PROJECT_INFO + "." + INDUSTRY_ID + " =  '" + industryId + "'" + AND;
		}
		if (responsibleName != "") {
			whUserName = USER_NAME + LIKE + responsibleName + "%' " + AND;
		}
		if (companyId != "") {
			whCompanyId = PROJECT_INFO + "." + CONTRACTOR_ID + " =  '" + companyId + "'" + AND;
		}
		if (totalTeamsProject != "") {
			if (totalTeamsProject.equals("0")) {
				whTotalTeamsProject = "project_emp.total_emp_project  IS NULL AND ";
			} else {
				whTotalTeamsProject = TOTAL_TEAMS_PROJECT + " =  '" + totalTeamsProject + "'" + AND;
			}
		}
		if (totalEmpProject != "") {
			if (totalEmpProject.equals("0")) {
				whTotalEmpProject = "project_emp.total_emp_project  IS NULL AND ";
			} else {
				whTotalEmpProject = TOTAL_EMP_PROJECT + " =  '" + totalEmpProject + "'" + AND;
			}
		}
		

		String where = WH;

		try (Connection con = DBConnection.getConnection();) {

			sql = "SELECT project_info.project_id,project_info.project_name,project_info.project_start_date "+
			        ",project_info.project_complete_date,m_user.user_name,m_industry.industry_name,order_source.order_source_name "+
			        ",m_belong_company.company_name,project_team.total_teams_project,project_emp.total_emp_project "+
			    "FROM project_info "+
			    "LEFT OUTER JOIN m_user ON project_info.responsible_id = m_user.user_id "+
			     "LEFT OUTER JOIN m_industry ON project_info.industry_id = m_industry.industry_id "+
			            "LEFT OUTER JOIN order_source ON project_info.client_id = order_source.order_source_id "+
			            "LEFT OUTER JOIN m_belong_company ON project_info.contractor_id = m_belong_company.company_id "+
			            "LEFT OUTER JOIN ( SELECT project_id,COUNT(team_id) AS total_teams_project "
			            + "FROM (SELECT project_info.project_id,project_detail_info.team_id,project_info.latest_flg "
			            + "FROM project_info "
			            + "LEFT OUTER JOIN project_detail_info ON project_info.project_info_id = project_detail_info.project_info_id "
			            + "LEFT OUTER JOIN team_info ON team_info.team_id = project_detail_info.team_id "		            
			            + "WHERE project_info.latest_flg = false AND team_info.delete_flg = false AND project_detail_info.latest_flg = 0) AS project GROUP BY project_id ) "
			            + " AS project_team ON project_info.project_id = project_team.project_id "
			            + "LEFT OUTER JOIN ( SELECT project_info_id,COUNT(*) AS total_emp_project FROM employee_experience "
			            + "WHERE team_belong_start_date <= CURRENT_DATE AND (team_belong_complete_date IS NULL "
			            + "OR CURRENT_DATE <= team_belong_complete_date) AND latest_flg = false AND team_belong_start_date "
			            + "IN (SELECT MAX(team_belong_start_date)FROM employee_experience GROUP BY employee_id) "
			            + " GROUP BY project_info_id) project_emp ON project_info.project_info_id = project_emp.project_info_id "
			            + where + whProjectId + whProjectName + whOrderName + whIndustryId
					+ whUserName + whCompanyId + whTotalTeamsProject
					+ whTotalEmpProject + PROJECT_INFO + "." + LATEST_FLG + " = ? ";

			try (PreparedStatement ps = con.prepareStatement(sql);) {

				ps.setBoolean(1, false);

				rs = ps.executeQuery();

				while (rs.next()) {
					SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
					searchProjectDTO = setSearchProject(rs);

					searchProjectDTOArr.add(searchProjectDTO);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		return searchProjectDTOArr;
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	//案件検索結果をDTOに格納する
	public static SearchProjectDTO setSearchProject(ResultSet rs) throws SQLException {

		SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
		try {
		searchProjectDTO.setProjectId(rs.getString("project_Id"));

		searchProjectDTO.setProjectName(rs.getString("project_name"));

		searchProjectDTO.setResponsibleName(rs.getString("user_name"));

		searchProjectDTO.setContratorName(rs.getString("company_name"));

		searchProjectDTO.setClientName(rs.getString("order_source_name"));

		searchProjectDTO.setInductryName(rs.getString("industry_name"));

		
			searchProjectDTO.setTotalTeamsProject(rs.getString("total_teams_project"));

		String total_emp_project = "0";

		if (rs.getString("total_emp_project") != null) {

			total_emp_project = rs.getString("total_emp_project");
		}

		searchProjectDTO.setTotalEmpProject(total_emp_project);

		searchProjectDTO.setProjectStartDate(rs.getDate("project_start_date"));

		searchProjectDTO.setProjectCompleteDate(rs.getDate("project_complete_date"));

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		
		return searchProjectDTO;
	}

	//案件責任者の所属会社IDを取得（案件の検索権限があるかチェックするメソッドに必要）
	public static String searchCompanyId(String responsibleName) throws ClassNotFoundException, SQLException {

		String companyId = "";

		try (Connection con = DBConnection.getConnection();) {

			sql = SELECT + COMPANY_ID + FROM + M_USER + WH + USER_NAME + " = ? ;";

			try (PreparedStatement ps = con.prepareStatement(sql);) {

				ps.setString(1, responsibleName);

				rs = ps.executeQuery();

				while (rs.next()) {
					companyId = rs.getString("company_Id");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return companyId;
	}
}