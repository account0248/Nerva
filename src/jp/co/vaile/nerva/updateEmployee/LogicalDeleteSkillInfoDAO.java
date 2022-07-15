package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class LogicalDeleteSkillInfoDAO {
	//保有スキルテーブルの更新者ID、更新日時、更新フラグをUPDATEするSQL文を表す
	private final String LOGICAL_DELETE_SLILL_INFO = "UPDATE skill_info SET update_user = ?,"
			+ "update_time = CURRENT_TIMESTAMP, latest_flg = true WHERE employee_id = ? AND latest_flg = false";

	/**
	 * 従業員IDを基に保有スキルテーブルの更新フラグを変更する
	 * 処理結果を返す
	 * @param employeeId 従業員IDを表す文字列
	 * @param userId ログインユーザーIDを表す文字列
	 * @throws ClassNotFoundException, SQLException 
	 */
	void updateSkillInfoFlg(String employeeId, String userId) throws ClassNotFoundException, SQLException {
		//DBに接続する
		try (Connection conn = DBConnection.getConnection()) {
			//従業員IDを基に保有スキルテーブルの更新フラグがfalseのものをtrueに、更新者IDをログインユーザーIDに、
			//更新日時をDBサーバ時刻に更新する。
			PreparedStatement pStmt = conn.prepareStatement(LOGICAL_DELETE_SLILL_INFO);
			pStmt.setString(1,userId);
			pStmt.setString(2, employeeId);
			pStmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
