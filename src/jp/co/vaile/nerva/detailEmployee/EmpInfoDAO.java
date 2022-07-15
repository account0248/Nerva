package jp.co.vaile.nerva.detailEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class EmpInfoDAO {
	// 従業員IDをもとに従業員情報をDBからSELECTするSQL
	protected final String SELECT_BY_EMP_ID_FROM_EMPLOYEE_INFO = "SELECT employee_id, employee_name, sex, birth_date, "
			+ "company_name, office, employee_info.department_id AS department_id, department_name, "
			+ "employee_info.post_id AS post_id, post_name, postal_code, address, phone_number, mail "
			+ "FROM employee_info " + "JOIN m_belong_company ON employee_info.company_id = m_belong_company.company_id "
			+ "JOIN m_belong_department ON employee_info.department_id = m_belong_department.department_id "
			+ "JOIN m_post ON employee_info.post_id = m_post.post_id " + "WHERE employee_id = ? AND delete_flg = 0";

	/**
	 * 呼び出し元から渡された従業員IDをもとに従業員情報DTOにDBから取得した情報を格納する。
	 * 
	 * @param employeeId 従業員IDを表す文字列
	 * @return empInfoDTO 従業員情報DTOを表す
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	EmpInfoDTO selectEmpInfoByEmpId(String employeeId) throws SQLException, ClassNotFoundException {
		EmpInfoDTO empInfoDTO = new EmpInfoDTO();
		// 従業員情報DTOに格納されている従業員IDをもとに従業員情報テーブルから従業員名・性別・生年月日・事業所・郵便番号・住所・TEL
		// ・Mai、所属会社マスタから所属会社名、役職マスタから役職名を取得する。
		try (Connection conn = DBConnection.getConnection()) {

			PreparedStatement pStmt = conn.prepareStatement(SELECT_BY_EMP_ID_FROM_EMPLOYEE_INFO);
			pStmt.setString(1, employeeId);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				// DBから取得した値を従業員情報DTOに格納する。
				empInfoDTO.setEmployeeId(rs.getString("employee_id"));
				empInfoDTO.setEmployeeName(rs.getString("employee_name"));
				empInfoDTO.setSex(rs.getString("sex"));
				// DBから取得したDATE型の値をString型に変換してDTOにセットする
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				empInfoDTO.setBirthDate(simpleDateFormat.format(rs.getDate("birth_date")));
				empInfoDTO.setBelongCompany(rs.getString("company_name"));
				empInfoDTO.setOffice(rs.getString("office"));
				empInfoDTO.setDepartmentId(rs.getString("department_id"));
				empInfoDTO.setDepartmentName(rs.getString("department_name"));
				empInfoDTO.setPostId(rs.getString("post_id"));
				empInfoDTO.setPost(rs.getString("post_name"));
				empInfoDTO.setPostalCode(rs.getString("postal_code"));
				empInfoDTO.setAddress(rs.getString("address"));
				empInfoDTO.setPhoneNumber(rs.getString("phone_number"));
				empInfoDTO.setMail(rs.getString("mail"));
			}

			return empInfoDTO;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
