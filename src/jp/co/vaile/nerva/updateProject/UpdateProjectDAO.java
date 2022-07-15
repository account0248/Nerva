package jp.co.vaile.nerva.updateProject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;

public class UpdateProjectDAO {

	// 案件情報テーブルにデータをアップデートする
	private final String UPDATE_PROJECT_INFO = "UPDATE project_info SET update_time=CURRENT_TIME,update_user=?,latest_flg=1 WHERE project_info_id=?;";
	// 案件情報テーブルにデータをインサートする
	private final String INSERT_PROJECT_INFO = "INSERT INTO project_info VALUES(null,?,?,?,?,?,?,?,?,null,null,false,CURRENT_TIME,?);";

	// 案件詳細情報テーブルにデータをアップデートする
	private final String UPDATE_PROJECT_DETAIL = "UPDATE project_detail_info SET update_time=CURRENT_TIME,update_user=?,latest_flg=1 WHERE project_detail_id=?;";
	// 案件詳細情報テーブルにデータをインサート
	private final String INSERT_PROJECT_DETAIL = "INSERT INTO project_detail_info VALUES(null,?,?,?,?,CURRENT_TIME,null,null,false);";

	// 従業員業務経験情報テーブルをアップデートするSQL
	private final String UPDATE_EMP_EXP = "UPDATE employee_experience "
			+ "SET latest_flg=1,update_time = CURRENT_TIME ,update_user = ? WHERE employee_experience_id=?";
	// 従業員業務経験情報テーブルにインサートするSQL
	private final String INSERT_EMP_EXP = "INSERT INTO employee_experience "
			+ "VALUES (null,? , ? , ? , ? , ? , ? , ? , ? , ?, CURRENT_TIME,null,null,0) ";

	/**
	 * 更新対象の案件情報テーブルのlatest_flgを1（最新でない）にする
	 * 
	 * @param projectInfoDTO 案件情報（更新内容）
	 * @param registUser     ログインユーザ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void updateProjectInfo(ProjectInfoDTO projectInfoDTO, String registUser)
			throws ClassNotFoundException, SQLException {

		int projectInfoId = projectInfoDTO.getProjectInfoId();

		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(UPDATE_PROJECT_INFO);) {
				stmt.setString(1, registUser);
				stmt.setInt(2, projectInfoId);
				stmt.executeUpdate();
			}
		}
	}

	/**
	 * 最新の案件情報を案件情報テーブルにインサートする
	 * 
	 * @param projectInfoDto    案件情報（更新内容）
	 * @param registUser        ログインユーザ
	 * @param oldProjectInfoDTO 更新対象の案件情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void insertProjectInfo(ProjectInfoDTO projectInfoDto, String registUser, ProjectInfoDTO oldProjectInfoDTO)
			throws ClassNotFoundException, SQLException {

		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_PROJECT_INFO);) {

				stmt.setString(1, oldProjectInfoDTO.getProjectId());
				stmt.setString(2, oldProjectInfoDTO.getProjectName());
				stmt.setString(3, oldProjectInfoDTO.getResponsibleId());
				stmt.setString(4, oldProjectInfoDTO.getContractorId());
				stmt.setString(5, oldProjectInfoDTO.getClientId());
				stmt.setString(6, oldProjectInfoDTO.getIndustryId());
				stmt.setString(7, projectInfoDto.getProjectStartDate());
				if (projectInfoDto.getProjectCompleteDate().equals("")) {
					stmt.setNull(8, java.sql.Types.NULL);
				} else {
					stmt.setString(8, projectInfoDto.getProjectCompleteDate());
				}
				stmt.setString(9, registUser);
				stmt.executeUpdate();
			}
		}
	}

	/**
	 * 更新対象の案件情報テーブルのlatest_flgを1（最新でない）にする
	 * 
	 * @param projectDetailId 案件情報詳細ID
	 * @param registUser      ログインユーザ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void updateProjectDetailInfo(String projectDetailId, String registUser)
			throws ClassNotFoundException, SQLException {

		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(UPDATE_PROJECT_DETAIL);) {
				stmt.setString(1, registUser);
				stmt.setString(2, projectDetailId);
				stmt.executeUpdate();
			}
		}
	}

	/**
	 * 最新の案件詳細情報を案件詳細情報テーブルにインサートする
	 * 
	 * @param latestProjectInfoId         最新の案件情報ID
	 * @param entryTeamAssignCompleteDate 参加済みチームの配属完了日リスト
	 * @param entryTeamList               参加済みチームDTOのリスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void insertProjectDetailInfo(int latestProjectInfoId, String entryTeamAssignCompleteDate,
			ProjectEntryTeamDTO entryTeamList) throws ClassNotFoundException, SQLException {

		String teamId = entryTeamList.getTeamId();
		String strartDay = entryTeamList.getAssignStartDate();

		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_PROJECT_DETAIL);) {
				stmt.setInt(1, latestProjectInfoId);
				stmt.setString(2, teamId);
				stmt.setString(3, strartDay);
				if (entryTeamAssignCompleteDate.equals("")) {
					stmt.setNull(4, java.sql.Types.NULL);
				} else {
					stmt.setString(4, entryTeamAssignCompleteDate);
				}
				stmt.executeUpdate();
			}
		}

	}

	/**
	 * 追加チーム情報を案件詳細情報テーブルにインサートする
	 * 
	 * @param latestProjectInfoId 最新の案件情報ID
	 * @param updateAddTeamDTO    追加するチーム情報
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void insertProjectDetailInfo(int latestProjectInfoId, UpdatePjtAddTeamDTO updateAddTeamDTO)
			throws ClassNotFoundException, SQLException {
		String teamId = updateAddTeamDTO.getTeamId();
		String strartDay = updateAddTeamDTO.getAssignStartDate();
		String completeDate = updateAddTeamDTO.getAssignCompleteDate();
//		if (completeDate.equals("")||completeDate==null) {
//		}
		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_PROJECT_DETAIL);) {
				stmt.setInt(1, latestProjectInfoId);
				stmt.setString(2, teamId);
				stmt.setString(3, strartDay);
				if (completeDate == "" || completeDate == null) {
					stmt.setNull(4, java.sql.Types.NULL);
				} else {
					stmt.setString(4, completeDate);
				}
				stmt.executeUpdate();
			}
		}

	}

	/**
	 * 追加チームの従業員業務経験テーブルのlatest_flgを1（最新でない）にする
	 * 
	 * @param employeeExperienceId 従業員業務経験ID
	 * @param registUser           ログインユーザ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void updateEmpEx(int employeeExperienceId, String registUser) throws SQLException, ClassNotFoundException {
		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(UPDATE_EMP_EXP);) {
				stmt.setString(1, registUser);
				stmt.setInt(2, employeeExperienceId);
				stmt.executeUpdate();
			}
		}
	}

	/**
	 * 追加チームの従業員業務経験情報を従業員業務経験テーブルにインサートする
	 * 
	 * @param latestProjectInfoId 最新の案件情報ID
	 * @param teamId              チームID
	 * @param latestEmpWorkExpDTO 従業員情報
	 * @param registUser          ログインユーザ
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void insertEmpEx(int latestProjectInfoId, String teamId, StubEmpExpDTO latestEmpWorkExpDTO, String registUser)
			throws ClassNotFoundException, SQLException {

		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_EMP_EXP);) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// Date型をString型に変換
				stmt.setString(1, latestEmpWorkExpDTO.getEmployeeId());
				stmt.setInt(2, latestProjectInfoId);
				stmt.setString(3, teamId);
				stmt.setString(4, dateFormat.format(latestEmpWorkExpDTO.getTeamBelongStartDate()));
				stmt.setString(5, latestEmpWorkExpDTO.getRoleId());
				stmt.setString(6, latestEmpWorkExpDTO.getContractTypeId());

				if ("" + latestEmpWorkExpDTO.getMonthlyUnitPrice() == ""
						|| latestEmpWorkExpDTO.getMonthlyUnitPrice() == null) {
					stmt.setNull(7, java.sql.Types.NULL);

				} else {
					stmt.setString(7, "" + latestEmpWorkExpDTO.getMonthlyUnitPrice());
				}

				if (latestEmpWorkExpDTO.getTeamBelongCompleteDate() == null) {

					stmt.setNull(8, java.sql.Types.NULL);
				} else {
					stmt.setString(8, dateFormat.format(latestEmpWorkExpDTO.getTeamBelongCompleteDate()));
				}
				stmt.setString(9, registUser);

				stmt.executeUpdate();
			}
		}
	}

	/**
	 * 従業員業務経験情報DTOを基にDBの従業員業務経験情報テーブルにインサートする 案件削除用
	 * 
	 * @param EmpExpDtoDto 従業員業務経験情報DTO
	 * @param userId       ログインユーザーID
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	void insertEmpExp(StubEmpExpDTO empExpDto, String userId) throws ClassNotFoundException, SQLException {
		/* TODO 従業員業務経験情報DTOは従業員更新待ち(スタブ仮置き) */

		// DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_EMP_EXP)) {
				// 従業員業務経験情報DTOのデータをDBの従業員業務経験情報テーブルにインサートする
				stmt.setString(1, empExpDto.getEmployeeId());
				stmt.setNull(2, java.sql.Types.NULL);
				stmt.setString(3, empExpDto.getTeamId());
				stmt.setDate(4, (Date) empExpDto.getTeamBelongStartDate());
				stmt.setString(5, empExpDto.getRoleId());
				stmt.setString(6, empExpDto.getContractTypeId());
				if (empExpDto.getMonthlyUnitPrice() != null) {
					stmt.setInt(7, empExpDto.getMonthlyUnitPrice());
				} else {
					stmt.setNull(7, java.sql.Types.NULL);
				}
				stmt.setDate(8, (Date) empExpDto.getTeamBelongCompleteDate());
				stmt.setString(9, userId);
				stmt.executeUpdate();

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
	}
}
