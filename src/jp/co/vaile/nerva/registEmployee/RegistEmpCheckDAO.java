package jp.co.vaile.nerva.registEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class RegistEmpCheckDAO {
	private final String SELECT_EMPLOYEE_ID_NUM = "SELECT COUNT(employee_id) FROM (SELECT employee_id FROM employee_info WHERE employee_id = ? )AS employee_id";

	public int selectEmployeeId(String employeeId) throws SQLException, ClassNotFoundException {
		int targetNum = 0;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstatement = connection.prepareStatement(SELECT_EMPLOYEE_ID_NUM)) {
			pstatement.setString(1, employeeId); // 従業員ID
			try (ResultSet rs = pstatement.executeQuery()) {
				while (rs.next()) {
					targetNum = rs.getInt("COUNT(employee_id)");// 従業員ID数
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return targetNum;
	}
}
