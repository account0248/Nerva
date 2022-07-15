package jp.co.vaile.nerva.masterCompany.insertCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertCompanyDAO {

	/**
	 * 所属会社マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし登録する。
	 * @param insertCompanyDTO
	 * @param companyPrivilege
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertCompany(InsertCompanyDTO insertCompanyDTO,boolean companyPrivilege) throws ClassNotFoundException, SQLException {
		//1.DBに接続する。
		try (
			Connection con = DBConnection.getConnection();) {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO");
			sql.append(" m_belong_company ");
			sql.append("(");
			sql.append(" company_id"+",");
			sql.append(" company_name"+",");
			sql.append(" company_privilege"+",");
			sql.append(" company_code"+",");
			sql.append(" regist_time"+",");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(" );
			sql.append("?,?,?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");
			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				//2.P1をDBの所属会社マスタに登録処理を行う。
				stmt.setString(1, insertCompanyDTO.getCompanyId());
				stmt.setString(2, insertCompanyDTO.getCompanyName());
				if(companyPrivilege==true) {
					stmt.setBoolean(3, true);
				}if(companyPrivilege==false) {
					stmt.setBoolean(3, false);
				}
				stmt.setString(4, insertCompanyDTO.getCompanyCode());
				stmt.setString(5, insertCompanyDTO.getResistUserId());
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
