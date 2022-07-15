package jp.co.vaile.nerva.registEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class RegistEmpDAO {

	private final String INSERT_EMP_INFO = "INSERT INTO employee_info  (" +
			"employee_id," +
			"employee_name," +
			"sex," +
			"birth_date," +
			"office," +
			"postal_code," +
			"address," +
			"phone_number," +
			"mail," +
			"company_id," +
			"post_id," +
			"department_id," +
			"regist_time," +
			"regist_user," +
			"latest_flg," +
			"delete_flg) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP,?,0,0);";

	/**
	 * DBの従業員情報テーブルに引数の従業員情報を登録する。
	 * @param registEmpInfoDTO 登録される従業員情報
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void insertEmpInfo(RegistEmpInfoDTO registEmpInfoDTO) throws ClassNotFoundException, SQLException {
		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(INSERT_EMP_INFO);) {
				//2.P1をDBの従業員情報テーブルに登録処理を行う。
				stmt.setString(1, registEmpInfoDTO.getEmployeeId());
				stmt.setString(2, registEmpInfoDTO.getEmployeeName());
				stmt.setString(3, registEmpInfoDTO.getSex());
				java.sql.Date birthDate = new java.sql.Date(registEmpInfoDTO.getBirthDate().getTime());
				stmt.setDate(4, birthDate);
				stmt.setString(5, registEmpInfoDTO.getOffice());
				stmt.setString(6, registEmpInfoDTO.getPostalCode());
				stmt.setString(7, registEmpInfoDTO.getAddress());
				stmt.setString(8, registEmpInfoDTO.getPhoneNumber());
				stmt.setString(9, registEmpInfoDTO.getMail());
				stmt.setString(10, registEmpInfoDTO.getCompanyId());
				stmt.setString(11, registEmpInfoDTO.getPostId());
				stmt.setString(12, registEmpInfoDTO.getDepartmentId());
				stmt.setString(13, registEmpInfoDTO.getRegistUser());

				stmt.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
