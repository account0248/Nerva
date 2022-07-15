package jp.co.vaile.nerva.commonprocess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderSourceDAO {

	// 発注元IDと発注元名を全レコード検索するSQL文
	private final String SELECT_ALL_ORDER_SOURCE_TABLE = "SELECT * FROM order_source";

	/**
	 * 発注元テーブルから発注元ID・発注元名を全件取り出し、リスト化する。
	 *
	 * @return 発注元DTOリスト
	 */
	public List<OrderSourceDTO> selectAllOrderSource() throws SQLException, ClassNotFoundException {

		List<OrderSourceDTO> OrderSourceList = new ArrayList<>();

		// DBに接続する。
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement()) {
			// 発注元テーブルを全件検索し、発注元ID・発注元名を発注元DTOにする。
			ResultSet rs = statement.executeQuery(SELECT_ALL_ORDER_SOURCE_TABLE);
			while (rs.next()) {
				OrderSourceDTO orderSourceData = new OrderSourceDTO();
				orderSourceData.setOrderSourceId(rs.getInt(1));
				orderSourceData.setOrderSourceName(rs.getString(2));
				// 発注元DTOを発注元DTOリストに追加する。
				OrderSourceList.add(orderSourceData);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}
		// 作成した発注元DTOリストを返す。
		return OrderSourceList;
	}
}
