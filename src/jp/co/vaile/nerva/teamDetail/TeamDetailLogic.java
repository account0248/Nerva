package jp.co.vaile.nerva.teamDetail;

import java.sql.SQLException;
import java.util.List;

public class TeamDetailLogic {

	/**
	 *チームIDを基に画面表示に必要な情報を取得し、取得した情報をチーム詳細情報ページDTOに格納する。
	 *チーム詳細情報ページDTOを返す。
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	TeamDetailPageDTO acquireTeamDetailInfo(String teamId) throws ClassNotFoundException, SQLException {
		//	チームIDを基にチーム情報詳細ページ情報を取得する
		TeamDetailPageDAO teamDetailPageDAO = new TeamDetailPageDAO();
		TeamDetailPageDTO teamDetailPageDTO = teamDetailPageDAO.selectFromTeamEntryEmp(teamId);

		//	チーム詳細情報ページDTOを返す
		return teamDetailPageDTO;
	}

	/**
	 * チームIDを基にチーム参加従業員DTOを作成する。
	 * チーム参加従業員DTOを返す。
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	List<TeamEntryEmpDTO> acquireTeamEntryEmp(String teamId) throws ClassNotFoundException, SQLException {
		//	チームIDを基にチーム参加従業員DTOのリストを取得する
		TeamEntryEmpDAO teamEntryEmpDAO = new TeamEntryEmpDAO();
		List<TeamEntryEmpDTO> teamEntryEmpList = teamEntryEmpDAO.selectTeamDetail(teamId);

		//	チーム参加従業員DTOのリストを返す
		return teamEntryEmpList;
	}
}
