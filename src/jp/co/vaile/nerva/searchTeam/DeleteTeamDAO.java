package jp.co.vaile.nerva.searchTeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class DeleteTeamDAO {
	/*	チームテーブルからチームIDと所属部長IDを元に検索するSQL文*/
	private final String SELECT_TEAM_BY_TEAM_ID_MANAGER_ID = "SELECT team_manager FROM team_info WHERE team_id= ?";
	/*従業員業務経験テーブルからチームIDをもとに検索するSQL文*/
	private final String SELECT_EXP_BY_TEAM_ID = "SELECT COUNT(*) FROM employee_experience WHERE team_id = ? AND latest_flg = false AND ( CURRENT_DATE <= team_belong_complete_date OR team_belong_complete_date IS NULL )";
	/*移管申請情報テーブルからチームIDをもとに検索するSQL文*/
	private final String SELECT_TRANSFER_APP_BY_TEAM_ID = "SELECT COUNT(*) FROM transfer_application WHERE transfer_application_team = ? AND unapproved_flg =false";
	/*案件詳細情報テーブルからチームＩＤをもとに検索するＳＱＬ文*/
	private final String SELECT_PROJECT_BY_TEAM_ID = "SELECT COUNT(*) FROM project_detail_info WHERE team_id = ? AND (CURRENT_DATE <= assign_complete_date OR assign_complete_date IS NULL) AND latest_flg = false";
	/*チーム情報を論理削除するSQL文*/
	private final String LOGICAL_DELETE_TEAM_INFO = "UPDATE team_info SET  delete_flg = true , delete_time = CURTIME() , delete_user=? WHERE team_id= ? ";

	/**
	 * チームの削除処理を行う。
	 * @param teamId 削除対象のチームID
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void logicalDeleteTeamInfo(String teamId, String userId) throws SQLException, ClassNotFoundException  {

		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			try (PreparedStatement stmt = con.prepareStatement(LOGICAL_DELETE_TEAM_INFO);) {
				//2.チーム情報テーブルでチームＩＤがP1のチームＩＤのレコードの削除フラグをtrueに更新する。
				stmt.setString(1, userId);
				stmt.setString(2, teamId);
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
	 * 指定のチームの所属部長がログインユーザーか確認する。
	 * 一致した場合trueを戻り値とする。一致しなかった場合falseを戻り値とする。
	 * @param teamId チームID
	 * @param loginUserId ログインユーザーのID
	 * @return チーム所属部長の比較結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	boolean checkTeamManager(String teamId, String loginUserId) throws ClassNotFoundException, SQLException {
		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_TEAM_BY_TEAM_ID_MANAGER_ID);) {
				//2.チーム情報テーブルからP1のチームIDを検索条件として検索処理を行う。
				stmt.setString(1, teamId);
				ResultSet rs = stmt.executeQuery();
				String teamManagerId = "";
				while (rs.next()) {
					teamManagerId = rs.getString("team_manager");
				}
				//3.検索結果の所属部長IDとP2のログインユーザーIDを比較する。
				if (loginUserId.equals(teamManagerId)) {
					//3-1.等しい場合はtrueを戻り値とする。
					return true;
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
		return false;
	}

	/**
	 * 指定のチームに所属中または所属予定の従業員がいるか確認する。
	 * 所属中・所属予定の従業員がいなかった場合trueを戻り値とする。
	 * 所属中・所属予定の従業員がいた場合falseを戻り値とする。
	 * @param teamId チームID
	 * @return 所属中・所属予定の従業員確認結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	boolean checkBelongMemberByTeamId(String teamId) throws ClassNotFoundException, SQLException {

		//1.	DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_EXP_BY_TEAM_ID);) {
				//2.従業員業務経験テーブルからチームIDがP1・更新フラグがfalse・システム日時が所属完了日以前の条件で検索する。
				stmt.setString(1, teamId);
				ResultSet rs = stmt.executeQuery();
				int result = 0;
				while (rs.next()) {
					result = rs.getInt("COUNT(*)");
				}
				//2-1.検索結果が0件だった場合、3に進む。
				if (result != 0) {
					//2-2.検索結果が1件以上存在した場合、falseを戻り値とする。
					return false;
				}
			}

			try (PreparedStatement stmt = con.prepareStatement(SELECT_TRANSFER_APP_BY_TEAM_ID);) {
				//3.移管申請情報テーブルから配属先チームIDがP1・未承認フラグがfalseの条件で検索する。
				stmt.setString(1, teamId);
				ResultSet rs = stmt.executeQuery();
				int result = 0;
				while (rs.next()) {
					result = rs.getInt("COUNT(*)");
				}
				if (result == 0) {
					return true;
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
		//3-2.検索結果が1件以上存在した場合、falseを戻り値とする。
		return false;
	}

	/**
	 * 指定のチームが案件に所属しているか確認する。
	 * 案件に所属していなかった場合trueを戻り値とする。
	 * 案件に所属していた場合falseを戻り値とする。
	 * @param teamId チームID
	 * @return 案件配属確認結果
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	boolean checkBelongProject(String teamId) throws SQLException, ClassNotFoundException {

		//1.	DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			try (PreparedStatement stmt = con.prepareStatement(SELECT_PROJECT_BY_TEAM_ID);) {
				//2.	案件詳細情報テーブルからチームIDがP1・システム日時が配属完了日以前・更新フラグがfalseの条件で検索する。
				stmt.setString(1, teamId);
				ResultSet rs = stmt.executeQuery();
				int result = 0;
				while (rs.next()) {
					result = rs.getInt("COUNT(*)");
				}
				//3-1.等しい場合はtrueを戻り値とする。
				if (result == 0) {
					return true;
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
		//3-2.検索結果が1件以上存在した場合、falseを戻り値とする。
		return false;
	}
}