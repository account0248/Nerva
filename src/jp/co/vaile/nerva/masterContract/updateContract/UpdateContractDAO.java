package jp.co.vaile.nerva.masterContract.updateContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateContractDAO {

	public void updateContract(UpdateContractDTO updateContractDTO) throws SQLException, ClassNotFoundException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE ");
			sql.append(" m_contract_type ");
			sql.append(" SET ");
			sql.append(" contract_type " + " = ?, ");
			sql.append(" update_user " + " = ?, ");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" contract_type_id " + " = ? ");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.DBの契約形態マスタに更新処理を行う。
				for (int i = 0; i < updateContractDTO.getContractName().length; i++) {
					stmt.setString(1, updateContractDTO.getContractName()[i]);
					stmt.setString(2, updateContractDTO.getUpdateUserId());
					stmt.setString(3, updateContractDTO.getContractId()[i]);
					stmt.executeUpdate();
				}
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
