package jp.co.vaile.nerva.teamDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class TeamEntryEmpDAO {

	private final String SELECT_BY_TEAM_ID = "SELECT employee_info.employee_id, employee_info.employee_name, m_role.role_name, m_belong_company.company_name, m_contract_type.contract_type, employee_experience.team_belong_start_date, employee_experience.monthly_unit_price, employee_experience.employee_id FROM employee_experience LEFT JOIN m_role ON  employee_experience.role_id = m_role.role_id LEFT JOIN m_contract_type ON  employee_experience.contract_type_id = m_contract_type.contract_type_id LEFT JOIN employee_info ON  employee_experience.employee_id = employee_info.employee_id LEFT JOIN m_belong_company ON  employee_info.company_id = m_belong_company.company_id WHERE employee_experience.team_id = ? AND employee_info.delete_flg = false AND employee_experience.latest_flg = false";

	/**
	 * チームIDを基にDBの従業員情報テーブル,担当マスタ,所属会社マスタ,契約形態マスタ,従業員業務経験テーブル
	 * からデータを取得し、チーム参加従業員DTOに格納する。
	 * チーム参加従業員DTOをリストに格納する。チーム参加従業員DTOのリストを返す。
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	List<TeamEntryEmpDTO> selectTeamDetail(String teamId) throws ClassNotFoundException, SQLException {

		// チーム参加従業員DTOのリストを作成する
		List<TeamEntryEmpDTO> teamEntryEmpList = new ArrayList<TeamEntryEmpDTO>();
		//	DBに接続する
		try (Connection con = DBConnection.getConnection()) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_BY_TEAM_ID)) {
				//チームIDを基にDBから情報を取得する
				stmt.setString(1, teamId);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					// チーム参加従業員DTOを作成する
					TeamEntryEmpDTO teamEntryEmpDTO = new TeamEntryEmpDTO();
					// 取得したデータをチーム参加従業員DTOに格納する
					teamEntryEmpDTO.setEmpId(rs.getString("employee_id"));
					teamEntryEmpDTO.setEmpName(rs.getString("employee_name"));
					teamEntryEmpDTO.setRole(rs.getString("role_name"));
					teamEntryEmpDTO.setBelongCompany(rs.getString("company_name"));
					teamEntryEmpDTO.setContractType(rs.getString("contract_type"));

					if (rs.getDate("team_belong_start_date") != null) {
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Date型String変換用フォーマット
						String assignStartDate = simpleDateFormat.format(rs.getDate("team_belong_start_date"));
						teamEntryEmpDTO.setAssignStartDate(assignStartDate);
					}

					Integer monthlyUnitPrice = (Integer) rs.getObject("monthly_unit_price");
					teamEntryEmpDTO.setMonthlyUnitPrice(monthlyUnitPrice);
					// チーム参加従業員DTOをチーム参加従業員DTOのリストに格納する
					teamEntryEmpList.add(teamEntryEmpDTO);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		// チーム参加従業員DTOのリストを返す
		return teamEntryEmpList;
	}
}
