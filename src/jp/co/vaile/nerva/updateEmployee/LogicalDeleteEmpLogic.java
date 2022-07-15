package jp.co.vaile.nerva.updateEmployee;

import java.sql.SQLException;

public class LogicalDeleteEmpLogic {
	/**
	 * 従業員ID、ログインユーザーIDをもとに従業員論理削除処理を呼び出す
	 * @param employeeId 従業員のIDを表す文字列
	 * @param loginUserId ログインしているユーザーのIDを表す文字列
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void logicalDeleteEmpInfo(String employeeId, String loginUserId) throws ClassNotFoundException, SQLException {
		//引数として受け取った従業員ID、ログインユーザーIDをもとに従業員削除処理を呼び出す。
		LogicalDeleteEmpInfoDAO logicalDeleteEmpInfoDAO = new LogicalDeleteEmpInfoDAO();
		logicalDeleteEmpInfoDAO.updateEmpInfoFlg(employeeId, loginUserId);
	}

	/**
	 * 従業員ID、ログインユーザーIDをもとに従業員業務経験論理削除処理を呼び出す
	 * @param employeeId 従業員のIDを表す文字列
	 * @param loginUserId ログインユーザーのIDを表す文字列
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void logicalDeleteEmpWorkExp(String employeeId, String loginUserId) throws ClassNotFoundException, SQLException {
		//引数として受け取った従業員ID、ログインユーザーIDをもとに従業員業務経験削除処理を呼び出す。
		LogicalDeleteEmpWorkExpDAO logicalDeleteEmpWorkExpDAO = new LogicalDeleteEmpWorkExpDAO();
		logicalDeleteEmpWorkExpDAO.updateEmpWorkExpFlg(employeeId, loginUserId);
	}
	
	/**
	 * 従業員ID、ログインユーザーIDをもとに保有スキル論理削除処理を呼び出す
	 * @param employeeId 従業員のIDを表す文字列
	 * @param loginUserId ログインユーザーのIDを表す文字列
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void logicalDeleteSkillInfo(String employeeId, String loginUserId) throws ClassNotFoundException, SQLException {
		//引数として受け取った従業員ID、ログインユーザーIDをもとに従業員業務経験削除処理を呼び出す。
		LogicalDeleteSkillInfoDAO logicalDeleteSkillInfoDAO = new LogicalDeleteSkillInfoDAO();
		logicalDeleteSkillInfoDAO.updateSkillInfoFlg(employeeId, loginUserId);
	}
	
	/**
	 * 従業員IDをもとに従業員情報削除チェック処理を呼び出す
	 * @param employeeId 従業員のIDを表す文字列
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	String logicalDeleteEmpCheck(String employeeId) throws ClassNotFoundException, SQLException {
		String errorMessage = null;
		//引数として受け取った従業員IDをもとに従業員情報削除チェック処理を呼び出す。
		LogicalDeleteCheckEmpDAO logicalDeleteCheckEmpDAO = new LogicalDeleteCheckEmpDAO();
		errorMessage = logicalDeleteCheckEmpDAO.selectEmpInfoDeleteFlg(employeeId);
		//エラーメッセージがある場合、エラーメッセージを返す。
		if (errorMessage != null) {
			return errorMessage;
		}
		return null;
	}
}
