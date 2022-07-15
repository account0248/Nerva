package jp.co.vaile.nerva.commonprocess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnyMasterTableDAO {

	// 任意のマスターテーブルを全検索するSQL文
	private final String SELECT_ALL_ANY_MASTERTABLE = "SELECT * FROM ";


	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータを用いて、
	 * 任意のマスタテーブルの値を取得する。
	 * Listを返す。
	 *
	 * @param masterTableName マスタテーブル名
	 * @return マスタテーブルのデータ一覧
	 */
	public List<FetchAnyMasterTableDTO> fetchAnyMasterTable(String masterTableName)
			throws SQLException, ClassNotFoundException {
		// 第一引数とフィールドを用いてSQL文を生成し、DBのテーブルから値を取得し、Listに追加する。
		List<FetchAnyMasterTableDTO> masterTableList = new ArrayList<>();

		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement()) {
			String sql = SELECT_ALL_ANY_MASTERTABLE + masterTableName;

			ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					FetchAnyMasterTableDTO masterTableData = new FetchAnyMasterTableDTO();
					masterTableData.setMasterDataId(rs.getString(1));
					masterTableData.setMasterData(rs.getString(2));
					masterTableList.add(masterTableData);
				}
		}
		// DTOのリストを返す。
		return masterTableList;
	}
}