package jp.co.vaile.nerva.commonprocess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckDuplicateDAO {

	/**
	 * 検索処理を実行し、テーブル内のデータに重複がないか確認する。戻り値として検索結果の件数を返す。
	 * 
	 * @param inputData
	 * @param tableName
	 * @param columnName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int checkDuplicate(String inputData, String tableName, String columnName) throws SQLException, ClassNotFoundException {
		int targetNum = 0;
		try (Connection connection = DBConnection.getConnection();){
			StringBuilder sql = new StringBuilder();
			
			//SQL文を作成
			sql.append("SELECT COUNT");
			sql.append("(" + columnName + ")");
			sql.append("  FROM ");
			sql.append(" (SELECT " + columnName + " FROM ");
			sql.append(tableName);
			sql.append(" WHERE ");
			sql.append(columnName + "= ? )" );
			sql.append("AS " + columnName);

			PreparedStatement pstatement = connection.prepareStatement(sql.toString());
			
			pstatement.setString(1, inputData);
			
			//SQL文を実行
			ResultSet rs = pstatement.executeQuery();
			
			//実行結果を格納
			while (rs.next()) {
				targetNum = rs.getInt("COUNT(" + columnName + ")");
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
