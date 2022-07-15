package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class CheckExistTeamDAO {
	
	//チーム情報テーブルの削除済みではないチーム情報をSELECTするSQL文
	private final String LOGICAL_DELETE_CHECK_TEAM = "SELECT * FROM team_info"
			+ " WHERE team_id = ? AND delete_flg = false";

	/**
	 * チームIDと削除フラグを基にチームテーブルから削除済みではないチーム情報を取得する。
	 * 取得件数を返す。
	 * @param teamId チームIDを表す文字列
	 * @return チーム情報の未削除件数
	 * @throws ClassNotFoundException, SQLException 
	 */
	boolean isTeamExistDB(String teamId) throws ClassNotFoundException, SQLException {
		//DBに接続する
		try (Connection conn = DBConnection.getConnection()) {
			//チームIDと削除フラグを基にチーム情報テーブルから削除済みではないチーム情報を取得する。
			PreparedStatement pStmt = conn.prepareStatement(LOGICAL_DELETE_CHECK_TEAM);
			pStmt.setString(1, teamId);
			ResultSet rs = pStmt.executeQuery();
			//チームが削除済の場合、判定結果を戻り値として返す。
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
