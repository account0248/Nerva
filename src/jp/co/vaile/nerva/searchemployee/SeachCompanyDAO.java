package jp.co.vaile.nerva.searchemployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class SeachCompanyDAO {
	
	private final String SELECT_BY_COMPANY_ID_M_BELONG_COMPANY = "SELECT company_id FROM m_belong_company WHERE"
			+ " company_name = ? ";

	/**
	 * 呼び出し元から渡された所属会社名をもとにDBから取得した所属会社IDを戻り値として返す。
	 * @param companyName 所属会社名を表す文字列
	 * @return companyId 所属会社IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String getCompanyId(String companyName) throws SQLException, ClassNotFoundException  {
		
		try {
			
			Connection con = DBConnection.getConnection();

			String companyId = "";
			PreparedStatement stmt = con.prepareStatement(SELECT_BY_COMPANY_ID_M_BELONG_COMPANY);
			stmt.setString(1, 	companyName);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				companyId = rs.getString("company_id");
			}

			return companyId;
			
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}


	}
}
