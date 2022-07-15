package jp.co.vaile.nerva.masterIndustry.insertIndustry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertIndustryDAO {
	
	/**
	 *業種マスタメンテナンス画面で入力された値を持つDTOを用いてDBにアクセスし登録する。
	 * 
	 * @param insertIndustryDTO 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public void insertIndustry(InsertIndustryDTO insertIndustryDTO) throws ClassNotFoundException, SQLException {
		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO");
			sql.append(" m_industry ");
			sql.append("(");
			sql.append(" industry_id,");
			sql.append(" industry_name,");
			sql.append(" regist_time,");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(" );
			sql.append("?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				//2.P1をDBの業種マスタに登録処理を行う。
				stmt.setString(1, insertIndustryDTO.getIndustryId());
				stmt.setString(2, insertIndustryDTO.getIndustryName());
				stmt.setString(3, insertIndustryDTO.getRegistUserId());
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
