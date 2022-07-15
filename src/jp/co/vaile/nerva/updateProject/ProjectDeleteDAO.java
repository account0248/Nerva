package jp.co.vaile.nerva.updateProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class ProjectDeleteDAO {
	//案件情報テーブルのレコード数を案件情報IDから検索するSQL
	private final String COUNT_PJT_INFO_BY_PJT_ID = "SELECT COUNT(*) as cnt FROM project_info WHERE latest_flg = false AND project_id = ?";
	//案件情報テーブルで指定した案件情報IDのレコードの更新フラグを変更するSQL
	private final String LOGICAL_DELETE_PJT_INFO = "UPDATE project_info SET latest_flg = true , update_time =  now() , "
			+ "update_user = ? "
			+ "WHERE project_id =  ?";
	//案件詳細情報テーブルで指定した案件情報IDのレコードの更新フラグを変更するSQL
	private final String LOGICAL_DELETE_PJT_DETAIL = "UPDATE project_detail_info SET latest_flg = true , update_time = now() , "
			+ "update_user = ? "
			+ "WHERE project_info_id = ?";
	//従業員業務経験情報テーブルで指定した案件IDのレコードの更新フラグを変更するSQL
	private final String LOGICAL_DELETE_EMP_EXP_BY_PJT_ID = "UPDATE employee_experience SET latest_flg = true , update_time = now() , "
			+ "update_user = ? "
			+ "WHERE project_info_id = ?";

	/**
	 * 案件IDと更新フラグを基に案件情報テーブルのレコード数を取得する
	 * @param projectId 案件情報ID
	 * @return lastestDataRecord 最新版のレコード数
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	int selectPjtInfoByLastestFlg(String projectId) throws ClassNotFoundException, SQLException {
		int recordCount = 0;
		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(COUNT_PJT_INFO_BY_PJT_ID)) {
				//案件IDを基に案件情報テーブルの更新フラグが0であるデータを取得する
				stmt.setString(1, projectId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					recordCount = rs.getInt("cnt");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}
		//取得したレコード数を返す
		return recordCount;
	}

	/**
	 *案件情報IDを基に案件テーブルの更新フラグを変更する
	 * @param projectId 案件ID
	 * @param userId ログインユーザーID
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	void logicalDeletePjtInfo(String projectId, String userId) throws ClassNotFoundException, SQLException {
		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(LOGICAL_DELETE_PJT_INFO)) {
				//案件IDを基に案件情報テーブルの更新フラグがfalseのレコードを更新する
				//更新者IDをログインユーザーIDに変更する
				//更新フラグをtrueに変更する
				stmt.setString(1, userId);
				stmt.setString(2, projectId);
				stmt.executeUpdate();
			}
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

	/**
	 * 案件情報詳細IDを基に案件情報詳細テーブルの更新フラグを変更する
	 * @param projectInfoId 案件情報ID
	 * @param userId ログインユーザーID
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	void logicalDeletePjtDetail(int projectInfoId, String userId) throws ClassNotFoundException, SQLException {
		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(LOGICAL_DELETE_PJT_DETAIL)) {
				//案件情報IDを基に案件情報詳細テーブルの更新フラグがfalseのレコードを更新する
				//更新者IDをログインユーザーIDに変更する
				//更新フラグをtrueに変更する
				stmt.setString(1, userId);
				stmt.setInt(2, projectInfoId);
				stmt.executeUpdate();
			}
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

	/**
	 * 案件情報詳細IDを基に従業員業務経験情報テーブルの更新フラグを変更する
	 * @param projectInfoId 案件情報ID
	 * @param userId ログインユーザーID
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	void logicalDeleteEmpExpByPjtId(int projectInfoId, String userId) throws ClassNotFoundException, SQLException {
		
		//DBに接続する
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(LOGICAL_DELETE_EMP_EXP_BY_PJT_ID)) {
				//案件情報IDを基に従業員業務経験テーブルの更新フラグがfalseのレコードを更新する
				//更新者IDをログインユーザーIDに変更する
				//更新フラグをtrueに変更する
				stmt.setString(1, userId);
				stmt.setInt(2, projectInfoId);
				stmt.executeUpdate();
			}
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
