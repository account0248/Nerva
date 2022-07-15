package jp.co.vaile.nerva.searchTeam;

import java.sql.SQLException;

public class DeleteTeamLogic {


	/**
	 * チーム情報の削除処理を呼び出す。
	 * @param teamId 削除対象のチームID
	 * @return エラーメッセージ
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void deleteTeam(String teamId,String userId) throws ClassNotFoundException, SQLException {
		//1.Ｐ１のチームＩＤを引数にチーム削除処理を呼び出す。
		DeleteTeamDAO deleteTeamDAO =new DeleteTeamDAO();
		deleteTeamDAO.logicalDeleteTeamInfo(teamId,userId);
	}
}
