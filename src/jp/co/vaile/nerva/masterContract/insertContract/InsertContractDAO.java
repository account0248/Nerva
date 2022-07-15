package jp.co.vaile.nerva.masterContract.insertContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertContractDAO {
	
	public void insertContract(InsertContractDTO insertContractDTO) throws ClassNotFoundException, SQLException {
		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO");
			sql.append(" m_contract_type ");
			sql.append("(");
			sql.append(" contract_type_id,");
			sql.append(" contract_type,");
			sql.append(" regist_time,");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(" );
			sql.append("?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");

			
			PreparedStatement stmt = con.prepareStatement(sql.toString()); 
				//2.DBの契約形態マスタに登録処理を行う。
				stmt.setString(1, insertContractDTO.getContractId());
				stmt.setString(2, insertContractDTO.getContractName());
				stmt.setString(3, insertContractDTO.getRegistUserId());
				stmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
