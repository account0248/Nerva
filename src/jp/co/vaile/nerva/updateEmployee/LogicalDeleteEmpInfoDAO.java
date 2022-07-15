package jp.co.vaile.nerva.updateEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class LogicalDeleteEmpInfoDAO {
	//従業員情報テーブルの更新日時、更新者ID、更新フラグ、削除日時、削除者ID、削除フラグをUPDATEするSQL文
	private final String LOGICAL_DELETE_EMP_INFO = "UPDATE employee_info SET update_time = CURRENT_TIMESTAMP,"
			+ "update_user = ?, latest_flg = true, delete_time = CURRENT_TIMESTAMP, delete_user = ?, delete_flg = true"
			+ " WHERE employee_id = ?";

	/**
	 * 従業員ID、ログインユーザーIDを基に従業員情報テーブルの情報を論理削除する。
	 * 処理件数を返す
	 * @param employeeId 従業員IDを表す文字列
	 * @param loginUserId ログインユーザーIDを表す文字列
	 * @throws ClassNotFoundException, SQLException 
	 */
	void updateEmpInfoFlg(String employeeId, String loginUserId) throws ClassNotFoundException, SQLException {
		//DBに接続する
		try (Connection conn = DBConnection.getConnection()) {
			//従業員IDを基に従業員情報テーブルの更新フラグ・削除フラグがfalseのものをtrueに、更新者ID・削除者IDを第二引数に、
			//更新日時・削除日時をシステム時刻に更新する。
			PreparedStatement pStmt = conn.prepareStatement(LOGICAL_DELETE_EMP_INFO);
			pStmt.setString(1, loginUserId);
			pStmt.setString(2, loginUserId);
			pStmt.setString(3, employeeId);
			pStmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
