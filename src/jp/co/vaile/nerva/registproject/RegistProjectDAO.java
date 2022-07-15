package jp.co.vaile.nerva.registproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class RegistProjectDAO {

	private final String INSERT_PROJECT_INFO = "INSERT INTO project_info (project_id, project_name, responsible_id, contractor_id, client_id, industry_id, project_start_date, project_complete_date, latest_flg, regist_time, regist_user) VALUES(?, ?, ?, ?, ?, ?, ?, ?,false , CURRENT_TIMESTAMP, ?)";
	private final String INSERT_ORDER_SOURCE_INFO = "INSERT INTO order_source VALUES(null, ?, ?, CURRENT_TIMESTAMP)";
	private final String SELECT_ORDER_SOURCE_ID = "SELECT * FROM order_source ORDER BY order_source_id DESC LIMIT 1";
	private final String SELECT_PROJECT_ID_NUM = "SELECT COUNT(project_id) FROM (SELECT project_id FROM project_info WHERE project_ID = ? ) AS project_info_id";

	/**
	 * DBの案件情報テーブルに案件情報を登録する。
	 *
	 * 1. DBに接続する。 
	 * 2. 発注元登録処理を呼び出す。 
	 * 3. P1と2の戻り値を使ってDBの案件情報テーブルに案件情報を登録する。
	 *
	 * @param registProjectInfoDTO 登録する従業員情報
	 * @throws Exception
	 */
	public void insertProjectInfo(RegistProjectInfoDTO registProjectInfoDTO) throws Exception {
		try {
			// 発注元IDを取得
			int registOrderSourceID = registOrderSource(registProjectInfoDTO.getOrderSource(),
					registProjectInfoDTO.getRegistUser());

			try (Connection connection = DBConnection.getConnection();
					PreparedStatement pstatement = connection.prepareStatement(INSERT_PROJECT_INFO)) {

				pstatement.setString(1, registProjectInfoDTO.getProjectId()); // 案件ID
				pstatement.setString(2, registProjectInfoDTO.getProjectName()); // 案件名
				pstatement.setString(3, registProjectInfoDTO.getResponsibleId()); // 責任者ID
				pstatement.setString(4, registProjectInfoDTO.getContractorId()); // 受注元ID
				pstatement.setInt(5, registOrderSourceID); // 発注元
				pstatement.setString(6, registProjectInfoDTO.getIndustryId()); // 業種
				pstatement.setDate(7, registProjectInfoDTO.getProjectStartDate()); // 案件開始日
				pstatement.setDate(8, registProjectInfoDTO.getProjectCompleteDate()); // 案件完了日
				pstatement.setString(9, registProjectInfoDTO.getRegistUser()); // 登録者ID

				// SQLを実行
				pstatement.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * DBの発注元テーブルに発注元情報を登録する。
	 *
	 * 1. DBに接続する。
	 * 2. P1, P2を発注元テーブルに登録する。 
	 * 3. 発注元IDを返す。
	 *
	 * @param orderSource 登録する発注元情報
	 * @param registUser  登録者ID
	 * @return 登録レコードの発注元ID
	 * @throws Exception
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private int registOrderSource(String orderSource, String registUser) throws Exception {

		int registOrderSourceID = 0;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstatement = connection.prepareStatement(INSERT_ORDER_SOURCE_INFO)) {
			pstatement.setString(1, orderSource); // 発注元情報
			pstatement.setString(2, registUser); // 登録者ID
			pstatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
			throw e1;
		}
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstatement = connection.prepareStatement(SELECT_ORDER_SOURCE_ID)) {
			ResultSet rs = pstatement.executeQuery();
			while (rs.next()) {
				registOrderSourceID = rs.getInt("order_source_id");// 発注元ID
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}

		return registOrderSourceID;
	}
	
	/**
	 * DBの案件情報テーブルに存在する案件IDのレコード数を取得する。
	 * 
	 * @param projectId
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int selectProjectId(String projectId) throws ClassNotFoundException, SQLException {
		int projectIdNum = 0;
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement pstatement = connection.prepareStatement(SELECT_PROJECT_ID_NUM)) {
			pstatement.setString(1, projectId); // 案件ID
			try (ResultSet rs = pstatement.executeQuery()) {
				while (rs.next()) {
					projectIdNum = rs.getInt("COUNT(project_id)");// 案件ID数
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return projectIdNum;
	}
}
