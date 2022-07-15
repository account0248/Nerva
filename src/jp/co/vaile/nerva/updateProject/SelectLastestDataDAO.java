package jp.co.vaile.nerva.updateProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SelectLastestDataDAO {

	// 案件情報IDを基に最新の案件情報テーブルからデータを取得するSQL
	private final String SELECT_LATEST_PROJECT_INFO_BY_PJT_ID = "SELECT project_start_date,project_complete_date,latest_flg ,project_info_id FROM project_info WHERE latest_flg = 0 AND project_info_id = ?";
	// 案件情報詳細IDを基に最新の案件情報詳細テーブルからデータを取得するSQL
	private final String SELECT_PJ_DETAIL_ALL_INFO_BY_PK = "SELECT * FROM project_detail_info WHERE project_detail_id = ? AND latest_flg=0";

	// 案件情報ID
	private final String SELECT_PJT_ALL_INFO_BY_PK = "SELECT * FROM project_detail_info WHERE project_info_id = ? AND latest_flg=0";

	// 案件情報IDを基に案件情報テーブルから参加チームデータを取得するSQL
	private final String SELECT_PJT_INFO_BY_PK = "SELECT team_id FROM project_detail_info WHERE project_info_id = ? AND latest_flg=0";
	// チームIDを基にチーム情報テーブルからデータを取得するSQL
	private final String SELECT_PJT_DETAIL_BY_PK = "SELECT team_name FROM team_info WHERE team_id = ? AND delete_flg=0";
	// 案件IDを基に従業員業務経験情報テーブルからデータを取得するSQL
	private final String SELECT_EMP_EXP_BY_PJT_ID = "SELECT * FROM employee_experience WHERE project_info_id = ? AND latest_flg=0";

	// 案件情報IDを基に案件情報テーブルからデータを取得するSQL
	private final String SELECT_PJT_INFO_FLG_BY_PK = "SELECT * FROM project_info WHERE project_info_id = ? AND latest_flg=0";
	// 案件IDを基に最新の案件情報テーブルから案件情報IDを取得するSQL
	private final String SELECT_LATEST_PROJECT_INFO_ID_BY_PJT_ID = "SELECT project_info_id FROM project_info WHERE project_id = ? AND latest_flg =0";

	private final String SELECT_EMP_EX = "SELECT *  FROM employee_experience  "
			+ "WHERE latest_flg= 0 AND team_id =? AND project_info_id =? ;";
	// 従業員業務経験から追加されるチームの従業員の情報を取得するSQL
	private final String SELECT_EMP_EX_Null = "SELECT *  FROM employee_experience  "
			+ "WHERE latest_flg= 0 AND team_id =? AND project_info_id IS NULL ;";
	// 更新対象の従業員業務経験IDを取得
	private final String SELECT_EMP_EX_ID1 = "SELECT employee_experience_id FROM employee_experience "
			+ "WHERE employee_id = ? AND team_belong_start_date = ? AND " + "role_id = ? AND contract_type_id = ? ";
	private final String SELECT_EMP_EX_ID2 = "AND team_belong_complete_date = ? ";
	private final String SELECT_EMP_EX_ID2_NULL = "AND team_belong_complete_date IS ?  ";
	private final String SELECT_EMP_EX_ID3 = "AND monthly_unit_price = ? ";
	private final String SELECT_EMP_EX_ID3_NULL = "AND monthly_unit_price IS ? ";
	private final String SELECT_EMP_EX_ID4 = "AND project_info_id = ?;";

	private final String SELECT_LATEST_EXP_ENTRY_TEAM = "SELECT * FROM "
			+ "(SELECT * FROM employee_experience WHERE (employee_id,team_belong_start_date) IN "
			+ "(SELECT emp_table.employee_id ,MAX(emp_table.team_belong_start_date) FROM "
			+ "(SELECT * FROM employee_experience WHERE team_belong_start_date<=CURRENT_DATE) AS emp_table "
			+ "GROUP BY emp_table.employee_id)) AS employee_max_table " + "WHERE team_id=? AND project_info_id=? AND latest_flg=false;";

	/**
	 * 案件情報IDを基に最新の案件情報テーブルからデータを取得する
	 *
	 * @param projectInfoId 案件情報ID
	 * @return latestProjectInfoDto 取得結果
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	ProjectInfoDTO selectLatestProjectInfo(int projectInfoId) throws ClassNotFoundException, SQLException {

		ProjectInfoDTO latestProjectInfoDto = new ProjectInfoDTO();

		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_LATEST_PROJECT_INFO_BY_PJT_ID)) {

				stmt.setInt(1, projectInfoId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {

					latestProjectInfoDto.setProjectStartDate(rs.getString("project_start_date"));
					latestProjectInfoDto.setProjectCompleteDate(rs.getString("project_complete_date"));
				}
				return latestProjectInfoDto;
			}
		}
	}

	/**
	 * 案件情報詳細IDを基に最新の案件情報詳細テーブルからデータを取得する
	 *
	 * @param projectDetailId 案件情報詳細ID
	 * @returnlatestProjectDetailDto 取得結果
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	ProjectDetailDTO selectLatestProjectDetailInfo(int projectDetailId) throws ClassNotFoundException, SQLException {

		ProjectDetailDTO latestProjectDetailDto = new ProjectDetailDTO();

		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_PJ_DETAIL_ALL_INFO_BY_PK)) {

				stmt.setInt(1, projectDetailId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					latestProjectDetailDto.setProjectDetailId(rs.getString("project_detail_id"));
				}
				return latestProjectDetailDto;
			}
		}
	}


	/**
	 * 案件情報詳細IDを基に最新の案件情報詳細テーブルからデータを取得する（リスト）
	 * @param projectInfoId 案件情報ID
	 * @return latestEntryTeamInfoDtoArr 取得結果のリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	ArrayList<ProjectDetailDTO> selectLatestEntryTeamInfo(int projectInfoId)
			throws ClassNotFoundException, SQLException {

		ArrayList<ProjectDetailDTO> latestEntryTeamInfoDtoArr = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_PJT_ALL_INFO_BY_PK)) {

				stmt.setInt(1, projectInfoId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ProjectDetailDTO projectDetailDTO = new ProjectDetailDTO();
					projectDetailDTO.setAssignCompleteDate(rs.getString("assign_complete_date"));
					projectDetailDTO.setProjectDetailId(rs.getString("project_detail_id"));
					latestEntryTeamInfoDtoArr.add(projectDetailDTO);
				}
				return latestEntryTeamInfoDtoArr;
			}
		}
	}


	/**
	 * 案件情報IDを基に案件情報詳細テーブルからデータを取得する
	 *
	 * @param projectInfoId 案件情報ID
	 * @return teamIdList チームIDリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	ArrayList<String> selectAddableTeamId(int projectInfoId) throws ClassNotFoundException, SQLException {
		ArrayList<String> teamIdList = new ArrayList<>();
		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_PJT_INFO_BY_PK)) {
				// 案件情報IDを基に案件情報テーブルからデータを取得する
				stmt.setInt(1, projectInfoId);
				ResultSet rs = stmt.executeQuery();
				// チームIDリストに取得データを格納する
				while (rs.next()) {
					teamIdList.add(rs.getString("team_id"));
				}
			}
		}

		// 案件情報DTOを返す
		return teamIdList;
	}

	/**
	 * チームIDを基に案件詳細情報テーブルからデータを取得する
	 *
	 * @param teamId チームID
	 * @return exisitTeamList チーム名リスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	ArrayList<String> selectexisitTeam(String teamId) throws ClassNotFoundException, SQLException {

		ArrayList<String> exisitTeamList = new ArrayList<>();
		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_PJT_DETAIL_BY_PK)) {
				// チームIDを基に案件詳細情報テーブルからデータを取得する
				stmt.setString(1, teamId);
				ResultSet rs = stmt.executeQuery();
				// チーム名リストに取得データを格納する
				while (rs.next()) {
					exisitTeamList.add(rs.getString("team_name"));
				}
			}
		}
		// 案件詳細情報DTOリストを返す
		return exisitTeamList;
	}

	/**
	 * 案件IDを基に従業員業務経験情報テーブルからデータを取得する
	 * @param projectInfoId 案件情報ID
	 * @return empExpDtoList 従業員業務経験情報DTOリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	ArrayList<StubEmpExpDTO> selectEmpExpByPjtInfoId(int projectInfoId) throws ClassNotFoundException, SQLException {
		// 従業員業務経験情報DTOのリストを作成する
		ArrayList<StubEmpExpDTO> empExpDtoList = new ArrayList<>();
		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_EMP_EXP_BY_PJT_ID)) {
				// 案件IDを基に従業員業務経験情報テーブルからデータを取得する
				stmt.setInt(1, projectInfoId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					// 従業員業務経験情報DTOに取得データを格納する
					StubEmpExpDTO empExpDto = new StubEmpExpDTO();
					empExpDto.setEmployeeExperienceId(rs.getInt("employee_experience_id"));
					empExpDto.setEmployeeId(rs.getString("employee_id"));
					empExpDto.setTeamId(rs.getString("team_id"));
					empExpDto.setTeamBelongStartDate(rs.getDate("team_belong_start_date"));
					empExpDto.setTeamBelongCompleteDate(rs.getDate("team_belong_complete_date"));
					empExpDto.setRoleId(rs.getString("role_id"));
					empExpDto.setContractTypeId(rs.getString("contract_type_id"));
					empExpDto.setMonthlyUnitPrice((Integer) rs.getObject("monthly_unit_price"));
					empExpDtoList.add(empExpDto);
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
		// 従業員業務経験情報DTOリストを返す
		return empExpDtoList;
	}

	/**
	 * 更新対象の案件情報を全て取得する
	 * @param projectInfoId
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	ProjectInfoDTO selectLatestProjectInfoFlg(int projectInfoId) throws ClassNotFoundException, SQLException {

		ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();

		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_PJT_INFO_FLG_BY_PK)) {

				stmt.setInt(1, projectInfoId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					projectInfoDTO.setProjectId(rs.getString("project_id"));
					projectInfoDTO.setProjectName(rs.getString("project_name"));
					projectInfoDTO.setResponsibleId(rs.getString("responsible_id"));
					projectInfoDTO.setContractorId(rs.getString("contractor_id"));
					projectInfoDTO.setClientId(rs.getString("client_id"));
					projectInfoDTO.setIndustryId(rs.getString("industry_id"));

				}
				return projectInfoDTO;
			}
		}
	}

	/**
	 * 案件IDから案件情報IDを取得する
	 * @param projectId 案件ID
	 * @return latestProjectInfoId 取得結果
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	int searchLatestProjectInfoId(String projectId) throws ClassNotFoundException, SQLException {

		int latestProjectInfoId = 0;
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_LATEST_PROJECT_INFO_ID_BY_PJT_ID)) {

				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					latestProjectInfoId = Integer.parseInt(rs.getString("project_info_id"));
				}
				return latestProjectInfoId;
			}
		}
	}

	/**
	 * 対象のチーム(更新対象の案件に配属されている)に所属している従業員経験情報を取得する
	 * @param teamId チームID
	 * @param projectInfoId 案件情報ID
	 * @return latestEmpWorkExpDTOArr 対象のチーム(更新対象の案件に配属されている)に所属している従業員経験情報リスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	ArrayList<StubEmpExpDTO> searchLatestEmpEx(String teamId, int projectInfoId)
			throws ClassNotFoundException, SQLException, ParseException {

		ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_EMP_EX)) {

				stmt.setString(1, teamId);
				stmt.setInt(2, projectInfoId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					StubEmpExpDTO latestEmpWorkExpDTO = new StubEmpExpDTO();
					latestEmpWorkExpDTO.setEmployeeId(rs.getString("employee_id"));
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// Date型に変換
					latestEmpWorkExpDTO
							.setTeamBelongStartDate(dateFormat.parse(rs.getString("team_belong_start_date")));
					if (rs.getString("team_belong_complete_date") == null) {
						latestEmpWorkExpDTO.setTeamBelongCompleteDate(null);
					} else {
						latestEmpWorkExpDTO.setTeamBelongCompleteDate(
								dateFormat.parse(rs.getString("team_belong_complete_date")));
					}
					latestEmpWorkExpDTO.setRoleId(rs.getString("role_id"));
					latestEmpWorkExpDTO.setContractTypeId(rs.getString("contract_type_id"));
					if (rs.getString("monthly_unit_price") == null) {
						latestEmpWorkExpDTO.setMonthlyUnitPrice(null);
					} else {
						latestEmpWorkExpDTO.setMonthlyUnitPrice(Integer.parseInt(rs.getString("monthly_unit_price")));
					}
					latestEmpWorkExpDTOArr.add(latestEmpWorkExpDTO);
				}
				return latestEmpWorkExpDTOArr;
			}
		}

	}

	/**
	 * 従業員業務経験から追加されるチームの従業員の情報を取得
	 * @param teamId チームID
	 * @return latestEmpWorkExpDTOArr チームの従業員DTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 */
	ArrayList<StubEmpExpDTO> searchLatestEmpEx(String teamId)
			throws ClassNotFoundException, SQLException, ParseException {

		ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<>();

		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_EMP_EX_Null)) {

				stmt.setString(1, teamId);
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					StubEmpExpDTO latestEmpWorkExpDTO = new StubEmpExpDTO();
					latestEmpWorkExpDTO.setEmployeeExperienceId(rs.getInt("employee_experience_id"));
					latestEmpWorkExpDTO.setEmployeeId(rs.getString("employee_id"));
					latestEmpWorkExpDTO.setTeamId(rs.getString("team_id"));
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					// Date型に変換
					latestEmpWorkExpDTO
							.setTeamBelongStartDate(dateFormat.parse(rs.getString("team_belong_start_date")));
					if (rs.getString("team_belong_complete_date") == null) {
						latestEmpWorkExpDTO.setTeamBelongCompleteDate(null);
					} else {
						latestEmpWorkExpDTO.setTeamBelongCompleteDate(
								dateFormat.parse(rs.getString("team_belong_complete_date")));
					}
					latestEmpWorkExpDTO.setRoleId(rs.getString("role_id"));
					latestEmpWorkExpDTO.setContractTypeId(rs.getString("contract_type_id"));
					if (rs.getString("monthly_unit_price") == null) {
						latestEmpWorkExpDTO.setMonthlyUnitPrice(null);
					} else {
						latestEmpWorkExpDTO.setMonthlyUnitPrice(Integer.parseInt(rs.getString("monthly_unit_price")));
					}
					latestEmpWorkExpDTOArr.add(latestEmpWorkExpDTO);
				}
				return latestEmpWorkExpDTOArr;
			}
		}

	}

	/**
	 * 更新対象の従業員業務経験IDを取得する
	 * @param latestEmpWorkExpDTO 最新の従業員業務経験情報
	 * @param oldProjectInfoId 案件情報ID
	 * @return empWorkExpID 更新対象の従業員業務経験ID
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	int searcholdEmpWorkExpID(StubEmpExpDTO latestEmpWorkExpDTO, int oldProjectInfoId)
			throws SQLException, ClassNotFoundException {

		int empWorkExpID = 0;
		String sql = SELECT_EMP_EX_ID1;
		if (latestEmpWorkExpDTO.getTeamBelongCompleteDate() == null) {
			sql = sql + SELECT_EMP_EX_ID2_NULL;
		} else {
			sql = sql + SELECT_EMP_EX_ID2;
		}
		if (latestEmpWorkExpDTO.getMonthlyUnitPrice() == null) {
			sql = sql + SELECT_EMP_EX_ID3_NULL;
		} else {
			sql = sql + SELECT_EMP_EX_ID3;
		}
		sql = sql + SELECT_EMP_EX_ID4;
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(sql)) {

				stmt.setString(1, latestEmpWorkExpDTO.getEmployeeId());
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// Date型をString型に変換
				stmt.setString(2, dateFormat.format(latestEmpWorkExpDTO.getTeamBelongStartDate()));
				stmt.setString(3, latestEmpWorkExpDTO.getRoleId());
				stmt.setString(4, latestEmpWorkExpDTO.getContractTypeId());
				if (latestEmpWorkExpDTO.getTeamBelongCompleteDate() == null) {
					stmt.setNull(5, java.sql.Types.NULL);
				} else {
					stmt.setString(5, dateFormat.format(latestEmpWorkExpDTO.getTeamBelongCompleteDate()));
				}

				if (latestEmpWorkExpDTO.getMonthlyUnitPrice() == null) {
					stmt.setNull(6, java.sql.Types.NULL);
				} else {
					stmt.setString(6, "" + latestEmpWorkExpDTO.getMonthlyUnitPrice());
				}
				stmt.setInt(7, oldProjectInfoId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					empWorkExpID = Integer.parseInt(rs.getString("employee_experience_id"));
				}
				return empWorkExpID;
			}
		}
	}


	// 配属済みのチームの従業員情報を取得
	ArrayList<StubEmpExpDTO> searchLatestEmpExEntryTeam(String teamId, int projectId)
			throws ClassNotFoundException, SQLException, ParseException {
		ArrayList<StubEmpExpDTO> latestEmpWorkExpDTOArr = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_LATEST_EXP_ENTRY_TEAM)) {

				stmt.setString(1, teamId);
				stmt.setInt(2, projectId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					StubEmpExpDTO stubEmpExpDTO = new StubEmpExpDTO();
					stubEmpExpDTO.setEmployeeExperienceId(rs.getInt("employee_experience_id"));
					stubEmpExpDTO.setEmployeeId(rs.getString("employee_id"));
					stubEmpExpDTO.setTeamId(rs.getString("team_id"));
					stubEmpExpDTO
							.setTeamBelongStartDate(dateFormat.parse(rs.getString("team_belong_start_date")));
					if (rs.getString("team_belong_complete_date") == null) {
						stubEmpExpDTO.setTeamBelongCompleteDate(null);
					} else {
						stubEmpExpDTO.setTeamBelongCompleteDate(
								dateFormat.parse(rs.getString("team_belong_complete_date")));
					}
					latestEmpWorkExpDTOArr.add(stubEmpExpDTO);
				}
				return latestEmpWorkExpDTOArr;
			}
		}
	}
}
