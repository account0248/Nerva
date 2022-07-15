package jp.co.vaile.nerva.detailEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SearchEmpCompanyIdDAO {
	private final String SELECT_BY_COMPANY_ID_FROM_EMPLOYEE_INFO = "SELECT company_id FROM employee_info WHERE"
			+ " employee_id = ? ";

	/**
	 * 呼び出し元から渡された従業員IDをもとにDBから取得した所属会社IDを戻り値として返す。
	 * 
	 * @param employeeId 従業員IDを表す文字列
	 * @return companyId 所属会社IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	String searchCompanyId(String employeeId) throws SQLException, ClassNotFoundException {
		String companyId = "";
		// DBに接続
		Connection conn;
		try {
			conn = DBConnection.getConnection();

			PreparedStatement pStmt = conn.prepareStatement(SELECT_BY_COMPANY_ID_FROM_EMPLOYEE_INFO);
			pStmt.setString(1, employeeId);

			// SQLを実行
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {

				companyId = rs.getString("company_id");

			}
			return companyId;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
