package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class TransferExistCheckDAO {
	private final String SERCH_TRANSFER_RECORD = "SELECT application_num FROM transfer_application"
			+ " WHERE transfer_emp_id = ? AND unapproved_flg = false AND (CASE WHEN team_finish_date IS NOT NULL "
			+ " THEN team_finish_date > CURRENT_DATE ELSE TRUE END )";

	boolean isExistTransferInfo(String employeeId) throws Exception {
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement pStmt = conn.prepareStatement(SERCH_TRANSFER_RECORD);
			pStmt.setString(1, employeeId);
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
