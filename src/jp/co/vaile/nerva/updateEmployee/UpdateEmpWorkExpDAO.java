package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateEmpWorkExpDAO {
	//従業員業務経験テーブルの変更があったレコードをUPDATEするSQL文
	private final String UPDATE_EMP_EXP_SET_OLDER = "UPDATE employee_experience SET update_user = ?, "
			+ "update_time = CURRENT_TIMESTAMP, latest_flg = 1 WHERE employee_experience_id = ?";
	//従業員業務経験テーブルの変更があったレコードをINSERTするSQL文
	private final String INSERT_MODIFICATION_EMP_EXP = "INSERT INTO employee_experience (employee_id, team_id,"
			+ "team_belong_start_date, role_id, contract_type_id, monthly_unit_price, team_belong_complete_date,"
			+ "regist_user, regist_time, latest_flg, project_info_id) "
			+ "VALUES (?,(SELECT team_id FROM (SELECT team_id FROM employee_experience WHERE employee_experience_id = ? ) AS SUB1),?,"
			+ "(SELECT role_id FROM (SELECT role_id FROM employee_experience WHERE employee_experience_id = ? ) AS SUB2) , "
			+ "(SELECT contract_type_id FROM (SELECT contract_type_id FROM employee_experience WHERE employee_experience_id = ? ) AS SUB3 ) , "
			+ "(SELECT monthly_unit_price FROM (SELECT monthly_unit_price FROM employee_experience WHERE employee_experience_id = ? ) AS SUB4) ,?,?,"
			+ "CURRENT_TIMESTAMP, 0, (SELECT project_info_id FROM (SELECT project_info_id FROM employee_experience WHERE employee_experience_id = ? ) AS SUB5) )";
	//従業員業務経験テーブルに新しくレコードをINSERTするSQL文
	private final String INSERT_EMP_EXP = "INSERT INTO employee_experience (employee_id, team_id,"
			+ "team_belong_start_date, role_id, contract_type_id, monthly_unit_price, team_belong_complete_date,"
			+ "regist_user, regist_time, latest_flg, project_info_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, 0, "
			+ "(SELECT project_info_id FROM project_detail_info WHERE team_id = ? AND latest_flg = FALSE))";
	//移管申請情報テーブルに新しくレコードをINSERTするSQL文
	private final String INSERT_TRANSFER_APPLICATION = "INSERT INTO transfer_application (applicant_id,"
			+ "application_belong_company, transfer_emp_id, role_id, contract_type_id, month_unit_price, now_affiliation_team,"
			+ "transfer_application_team, transfer_preferred_date, apply_date, unapproved_flg, team_finish_date) VALUES"
			+ "(?, (SELECT company_id FROM m_user WHERE user_id = ?), ?, ?, ?, ?,"
			+ "(SELECT team_id FROM employee_experience WHERE employee_id = ? AND team_belong_start_date <= CURRENT_DATE"
			+ " AND latest_flg = 0 ORDER BY team_belong_start_date DESC LIMIT 1), ?, ?, CURRENT_TIMESTAMP, 0 ,?)";

	/**
	 * 従業員業務経験更新用DTOのリストを受け取り、従業員業務経験を更新する。
	 * @param updateEmpWorkExpDTOList 従業員業務経験更新用DTOを格納したリスト
	 * @throws ClassNotFoundException, SQLException 
	 */
	void updateEmpWorkExp(List<UpdateEmpWorkExpDTO> updateEmpWorkExpDTOList) throws ClassNotFoundException, SQLException {
		//DBに接続する。
		try (Connection conn = DBConnection.getConnection()) {
			//リストの長さ分繰り返す。
			for (int i = 0; i < updateEmpWorkExpDTOList.size(); i++) {

				//DTOから・従業員業務経験情報ID・ログインユーザーIDを取り出し、SQL文に追加して更新フラグを変更するSQL文を送る。
				int employeeExperienceId = updateEmpWorkExpDTOList.get(i).getEmployeeExperienceId();
				String loginUserId = updateEmpWorkExpDTOList.get(i).getLoginUserId();
				PreparedStatement pStmt = conn.prepareStatement(UPDATE_EMP_EXP_SET_OLDER);
				pStmt.setString(1, loginUserId);
				pStmt.setInt(2, employeeExperienceId);
				pStmt.executeUpdate();
				
				//DTOから従業員業務経験情報を取り出し、SQL文に追加してSQL文を送る。
				String employeeId = updateEmpWorkExpDTOList.get(i).getEmployeeId();
				String teamBelongStartDate = updateEmpWorkExpDTOList.get(i).getTeamBelongStartDate();
				String teamBelongCompleteDate = updateEmpWorkExpDTOList.get(i).getTeamBelongCompleteDate();
				pStmt = conn.prepareStatement(INSERT_MODIFICATION_EMP_EXP);
				pStmt.setString(1, employeeId);
				pStmt.setInt(2, employeeExperienceId);
				pStmt.setString(3, teamBelongStartDate);
				pStmt.setInt(4, employeeExperienceId);
				pStmt.setInt(5, employeeExperienceId);
				pStmt.setInt(6, employeeExperienceId);
				pStmt.setString(7, teamBelongCompleteDate);
				pStmt.setString(8, loginUserId);
				pStmt.setInt(9, employeeExperienceId);
				pStmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 従業員業務経験登録用DTOを受け取り、従業員業務経験を登録する。
	 * @param registEmpWorkExpDTO 従業員業務経験登録用DTO
	 * @throws ClassNotFoundException, SQLException 
	 */
	public void insertEmpWorkExp(RegistEmpWorkExpDTO registEmpWorkExpDTO) throws ClassNotFoundException, SQLException {
		//DBに接続する。
		try (Connection conn = DBConnection.getConnection()) {
			//従業員業務経験登録用DTOの内容をSQL文に追加してSQL文を送る。
			PreparedStatement pStmt = conn.prepareStatement(INSERT_EMP_EXP);
			pStmt.setString(1, registEmpWorkExpDTO.getEmployeeId());
			pStmt.setString(2, registEmpWorkExpDTO.getBelongTeamId());
			pStmt.setString(3, registEmpWorkExpDTO.getTeamBelongStartDate());
			pStmt.setString(4, registEmpWorkExpDTO.getRoleId());
			pStmt.setString(5, registEmpWorkExpDTO.getContractTypeId());
			pStmt.setString(6, registEmpWorkExpDTO.getMonthlyUnitPrice());
			pStmt.setString(7, registEmpWorkExpDTO.getTeamBelongCompleteDate());
			pStmt.setString(8, registEmpWorkExpDTO.getLoginUserId());
			pStmt.setString(9, registEmpWorkExpDTO.getBelongTeamId());
			pStmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 従業員業務経験登録用DTOを受け取り、移管申請情報を登録する。
	 * @param registEmpWorkExpDTO 従業員業務経験登録用DTOを表す
	 * @throws ClassNotFoundException, SQLException 
	 */
	void InsertTransferApplication(RegistEmpWorkExpDTO registEmpWorkExpDTO) throws ClassNotFoundException, SQLException {
		//DBに接続する。
		try (Connection conn = DBConnection.getConnection()) {
			//従業員業務経験登録用DTOの内容をSQL文に追加してSQL文を送る。
			//※登録の際現在の所属チームIDは、従業員IDをもとに従業員業務経験テーブルから所属開始日がDBサーバの日付以前かつ最新のレコード
			//かつ更新フラグがfalseのチームIDを取得し格納する。
			PreparedStatement pStmt = conn.prepareStatement(INSERT_TRANSFER_APPLICATION);
			pStmt.setString(1, registEmpWorkExpDTO.getLoginUserId());
			pStmt.setString(2, registEmpWorkExpDTO.getLoginUserId());
			pStmt.setString(3, registEmpWorkExpDTO.getEmployeeId());
			pStmt.setString(4, registEmpWorkExpDTO.getRoleId());
			pStmt.setString(5, registEmpWorkExpDTO.getContractTypeId());
			pStmt.setString(6, registEmpWorkExpDTO.getMonthlyUnitPrice());
			pStmt.setString(7, registEmpWorkExpDTO.getEmployeeId());
			pStmt.setString(8, registEmpWorkExpDTO.getBelongTeamId());
			pStmt.setString(9, registEmpWorkExpDTO.getTeamBelongStartDate());
			pStmt.setString(10, registEmpWorkExpDTO.getTeamBelongCompleteDate());
			pStmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
