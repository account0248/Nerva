package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateEmpInfoDAO {
	//従業員IDをもとに従業員情報テーブルをUPDATEするSQL文
	private final String UPDATE_EMPLOYEE_INFO = "UPDATE employee_info SET employee_name = ?, office = ?, postal_code = ?,"
			+ "address = ?, phone_number = ?, mail = ?, post_id = ?, department_id = ?, update_time = CURRENT_TIMESTAMP,"
			+ "update_user = ? ,latest_flg = true WHERE employee_id = ?";

	/**
	 * 従業員情報DTOを受け取り、従業員情報を更新する。
	 * @param updateEmpInfoDTO 従業員情報DTOを表す
	 * @return 従業員情報の更新処理件数
	 * @throws ClassNotFoundException, SQLException 
	 */
	int updateEmpInfo(UpdateEmpInfoDTO updateEmpInfoDTO) throws ClassNotFoundException, SQLException {
		int resultCount = 0;
		//DBに接続する。
		try (Connection conn = DBConnection.getConnection()) {

			//従業員情報DTOの内容をSQL文に格納しSQL文を送る。
			PreparedStatement pStmt = conn.prepareStatement(UPDATE_EMPLOYEE_INFO);
			pStmt.setString(1, updateEmpInfoDTO.getEmployeeName());
			pStmt.setString(2, updateEmpInfoDTO.getOffice());
			pStmt.setString(3, updateEmpInfoDTO.getPostalCode());
			pStmt.setString(4, updateEmpInfoDTO.getAddress());
			pStmt.setString(5, updateEmpInfoDTO.getPhoneNumber());
			pStmt.setString(6, updateEmpInfoDTO.getMail());
			pStmt.setString(7, updateEmpInfoDTO.getPostId());
			pStmt.setString(8, updateEmpInfoDTO.getBelongDepartmentId());
			pStmt.setString(9, updateEmpInfoDTO.getLoginUserId());
			pStmt.setString(10, updateEmpInfoDTO.getEmployeeId());
			resultCount = pStmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//戻り値を返す。
		return resultCount;
	}
}
