package jp.co.vaile.nerva.detailEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class EmpWorkExpDAO {
	//従業員IDをもとに従業員業務経験情報をDBから検索するSQL文の文字列
	private final String SELECT_BY_EMP_ID_FROM_EMPLOYEE_EXPERIENCE = "SELECT employee_experience_id,"
			+ " COALESCE(project_name, '-') AS project_name, COALESCE(industry_name, '-') AS industry_name, "
			+ "COALESCE(company_name, '-') AS company_name, user_name, team_name, role_name, contract_type,"
			+ "monthly_unit_price, team_belong_start_date, team_belong_complete_date FROM employee_experience "
			+ "LEFT OUTER JOIN project_info ON employee_experience.project_info_id = project_info.project_info_id "
			+ "LEFT OUTER JOIN m_industry ON project_info.industry_id = m_industry.industry_id "
			+ "LEFT OUTER JOIN m_belong_company ON project_info.contractor_id = m_belong_company.company_id "
			+ "JOIN team_info ON employee_experience.team_id = team_info.team_id AND team_info.delete_flg = false "
			+ "JOIN m_user ON team_info.team_manager = m_user.user_id "
			+ "JOIN m_role ON employee_experience.role_id = m_role.role_id "
			+ "JOIN m_contract_type ON employee_experience.contract_type_id = m_contract_type.contract_type_id "
			+ "WHERE (employee_id = ?) AND (employee_experience.latest_flg = false) "
			+ "ORDER BY employee_experience.team_belong_start_date ASC";

	/**
	 * 呼び出し元から渡された従業員IDをもとにDBから取得した情報を格納する。
	 * @param employeeId 従業員IDを表す文字列
	 * @return empWorkExpDTOList 従業員業務経験DTOを格納したリスト
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	List<EmpWorkExpDTO> selectWorkExpByEmpId(String employeeId) throws ClassNotFoundException, SQLException {
		List<EmpWorkExpDTO> empWorkExpDTOList = new ArrayList<EmpWorkExpDTO>();
		//従業員IDをもとに従業員業務経験テーブルから所属開始日・月単価・所属完了日、
		//チーム情報テーブルからチーム名、案件情報テーブルから案件名、担当マスタから担当名、契約形態マスタから契約形態名、
		//業種マスタから業種名を取得する。
		//(※1従業員業務経験テーブルからは更新フラグが0のもののみを取得する)
		//（※2.従業員業務経験テーブルからデータを取得する際には所属開始日順にソートして取得する）
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SELECT_BY_EMP_ID_FROM_EMPLOYEE_EXPERIENCE);
			pStmt.setString(1, employeeId);
			ResultSet rs = pStmt.executeQuery();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			//取得した値を従業員業務経験DTOのリストに格納する。
			while (rs.next()) {
				EmpWorkExpDTO empWorkExpDTO = new EmpWorkExpDTO();
				empWorkExpDTO.setEmployeeExperienceId(rs.getInt("employee_experience_id"));
				empWorkExpDTO.setResponsibleProject(rs.getString("project_name"));
				empWorkExpDTO.setResponsibleIndustry(rs.getString("industry_name"));
				empWorkExpDTO.setProjectAttributionCompany(rs.getString("company_name"));
				empWorkExpDTO.setResponsibleManager(rs.getString("user_name"));
				empWorkExpDTO.setBelongTeam(rs.getString("team_name"));
				empWorkExpDTO.setRole(rs.getString("role_name"));
				empWorkExpDTO.setContractType(rs.getString("contract_type"));
				Integer monthlyUnitPrice = (Integer) rs.getObject("monthly_unit_price");
				empWorkExpDTO.setMonthlyUnitPrice(monthlyUnitPrice);
				//DBから取得したDATE型の値をString型に変換してDTOにセットする
				empWorkExpDTO.setTeamBelongStartDate(simpleDateFormat.format(rs.getDate("team_belong_start_date")));
				if (rs.getDate("team_belong_complete_date") != null) {
					empWorkExpDTO
							.setTeamBelongCompleteDate(
									simpleDateFormat.format(rs.getDate("team_belong_complete_date")));
				}else {
					empWorkExpDTO
					.setTeamBelongCompleteDate("-");
				}
				empWorkExpDTOList.add(empWorkExpDTO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return empWorkExpDTOList;
	}
}
