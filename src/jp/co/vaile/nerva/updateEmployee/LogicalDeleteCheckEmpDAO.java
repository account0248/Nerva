package jp.co.vaile.nerva.updateEmployee;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class LogicalDeleteCheckEmpDAO {
	//エラーメッセージ用のインスタンス、変数を宣言
	ErrorMsg errorMsg = new ErrorMsg();
	String[] deleteErrorMsg = { DELETE };
	
	//従業員情報テーブルの削除済みではない従業員情報をSELECTするSQL文
	private final String LOGICAL_DELETE_CHECK_EMP_INFO = "SELECT * FROM employee_info"
			+ " WHERE employee_id = ? AND delete_flg = false";

	/**
	 * 従業員IDと削除フラグを基に従業員テーブルから削除済みではない従業員情報を取得する。
	 * 取得件数を返す。
	 * @param employeeId 従業員IDを表す文字列
	 * @return 従業員情報の未削除件数
	 * @throws ClassNotFoundException, SQLException 
	 */
	String selectEmpInfoDeleteFlg(String employeeId) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//DBに接続する
		try (Connection conn = DBConnection.getConnection()) {
			//従業員IDと削除フラグを基に従業員テーブルから削除済みではない従業員情報を取得する。
			PreparedStatement pStmt = conn.prepareStatement(LOGICAL_DELETE_CHECK_EMP_INFO);
			pStmt.setString(1, employeeId);
			ResultSet rs = pStmt.executeQuery();
			rs.last();
			//従業員が削除済の場合、エラーメッセージを追加し戻り値を返す。
			if (rs.getRow() == 0) {
				errorMessage = errorMsg.returnErrorMsg(PROCESS_FAILURE_ERROR_MESSAGE, deleteErrorMsg);
				return errorMessage;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;
		}
		//戻り値を返す。
		return errorMessage;
	}
}
