package jp.co.vaile.nerva.detailProject;

import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class ProjectDetailLogic {
	ErrorMsg returnErrorMsg = new ErrorMsg();
	
	/**
	 * 案件IDを基に画面表示に必要な情報を取得し、取得した情報を案件詳細情報ページDTOに格納する。
	 * 案件詳細情報ページDTOを返す。
	 * @param projectId 案件ID
	 * @return projectDetailPageDTO 案件詳細情報ページDTO
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	ProjectDetailPageDTO acquireProjectDetailInfo(String projectId) throws ClassNotFoundException, SQLException {
		ProjectDetailPageDTO projectDetailPageDto = new ProjectDetailPageDTO();

		//案件IDを基に案件情報詳細ページ情報を取得する
		ProjectDetailPageDAO projectDetailPageDAO = new ProjectDetailPageDAO();
		projectDetailPageDto = projectDetailPageDAO.selectFromProjectDetail(projectId);

		//案件詳細情報ページDTOを返す
		return projectDetailPageDto;
	}

	/**
	 * 案件詳細情報DTOを基に案件参加チームDTOリストを作成する。
	 * 案件参加チームDTOリストを返す。
	 * @param projectId 案件ID
	 * @return projectEntryTeamList 案件参加チームDTOのリスト
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	ArrayList<ProjectEntryTeamDTO> acquireProjectEntryTeam(int projectInfoId) throws ClassNotFoundException, SQLException {
		ArrayList<ProjectEntryTeamDTO> projectEntryTeamList = new ArrayList<>();

		//案件IDを基にチーム情報DTOのリストを取得する
		ProjectEntryTeamDAO projectEntryTeamDAO = new ProjectEntryTeamDAO();
		projectEntryTeamList = projectEntryTeamDAO.selectEntryTeam(projectInfoId);

		//参加チームDTOのリストを返す
		return projectEntryTeamList;
	}

	/**
	 * 呼び出し元から渡されたログインユーザーの所属会社ID、所属会社グループ権限、チームIDに基づき、参照権限があるかを検査する。
	 * 参照権限がない場合はエラーメッセージIDを返す。
	 * 
	 * @param userCompanyId ログインユーザーの所属会社ID
	 * @param companyPrivilege ログインユーザーの所属会社グループ権限
	 * @param teamId     チームID
	 * @return messageId エラーメッセージID
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	String checkTeamReferenceAuthority(String userCompanyId,String companyPrivilege, String teamId) throws ClassNotFoundException, SQLException {
		ProjectEntryTeamDAO projectEntryteamDao = new ProjectEntryTeamDAO();
		String messageId = "";

		//チームIDを基にチームの所属会社IDを取得
		String teamBelongCampany = projectEntryteamDao.selectTeamBelongCompany(teamId);

		//ログインユーザーの所属会社IDとチーム情報DTOの所属会社IDを基にチームの参照権限があるか検査する
		boolean viewingAuthority = new CheckViewingAuthority().checkViewingAuthority(userCompanyId,companyPrivilege, teamBelongCampany);
		//参照権限がない場合
		if (viewingAuthority == false) {
			//エラーメッセージIDを返す
			messageId = returnErrorMsg.returnErrorMsg(CommonConstants.TEAM_NO_AVAILABLE);
		}
		//参照権限がある場合
		//空文字を返す
		return messageId;

	}
}
