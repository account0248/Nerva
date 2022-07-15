package jp.co.vaile.nerva.updateProject;

import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.vaile.nerva.detailProject.ProjectDetailPageDAO;
import jp.co.vaile.nerva.detailProject.ProjectDetailPageDTO;
import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDAO;
import jp.co.vaile.nerva.detailProject.ProjectEntryTeamDTO;

public class ShowUpdatePjtLogic {

	/**
	 * 案件IDを基に最新の案件更新画面DTOを取得する
	 * 案件更新画面DTOを返す
	 * @param projectId 案件ID
	 * @return UpdatePjtPageDto 案件更新画面DTO
	 * @throws Exception
	 */
	ProjectDetailPageDTO acquireUpdatePjtInfo (String projectId) throws ClassNotFoundException, SQLException{

		//案件IDを基に最新案件情報取得処理を呼び出す
		ProjectDetailPageDTO updateProjectPageDto;

			updateProjectPageDto = new ProjectDetailPageDAO().selectFromProjectDetail(projectId);

		//案件更新画面DTOを返す
		return updateProjectPageDto;
	}

	/**
	 * 案件情報IDを基に案件参加チームDTOを作成する
	 * 案件更新画面参加チームDTOリストを返す
	 * @param projectInfoId 案件情報ID
	 * @return updatePjtEntryTeamList 案件更新画面参加チーム
	 * @throws Exception
	 */
	ArrayList<ProjectEntryTeamDTO> acquireUpdatePjtTeam (int projectInfoId) throws ClassNotFoundException, SQLException {

		//案件情報IDを基に案件更新画面参加チームDTOリストを取得する
		ArrayList<ProjectEntryTeamDTO> updatePjtEntryTeamList;

			updatePjtEntryTeamList = new ProjectEntryTeamDAO().selectEntryTeam(projectInfoId);

		//案件更新画面参加チームDTOリストを返す
		return updatePjtEntryTeamList;
	}

	/**
	 * 所属会社IDを基に参加可能チームDTOを作成する
	 * 参加可能チームDTOリストを返す
	 * @param loginBelongCompanyId 所属会社ID
	 * @return addableTeamList 参加可能チームDTOリスト
	 * @throws Exception
	 */
	ArrayList<AddableTeamDTO> fetchPjtAddableTeam (String loginBelongCompanyId) throws ClassNotFoundException, SQLException{

		//所属会社IDを基に参加可能チームDTOリストを取得する
		ArrayList<AddableTeamDTO> addableTeamList;

			addableTeamList = new ShowProjectUpdateDAO().selectAddableTeam( loginBelongCompanyId);

		//参加可能チームDTOリストを返す
		return  addableTeamList;
	}
}
