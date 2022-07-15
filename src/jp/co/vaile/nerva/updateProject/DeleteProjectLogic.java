package jp.co.vaile.nerva.updateProject;

import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;

public class DeleteProjectLogic {

	/**
	 * 案件情報がすでに削除済みかを確認する
	 * @param projectId 案件ID
	 * @return messageId エラーメッセージID
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	String checkProjectDeleted(String projectId) throws ClassNotFoundException, SQLException {
		String messageId = "";

		//案件IDを基に案件情報の最新版レコード数取得処理を呼び出す
		ProjectDeleteDAO projectDeleteDao = new ProjectDeleteDAO();
			int lastestRecordCount = projectDeleteDao.selectPjtInfoByLastestFlg(projectId);
		//戻り値が0だった場合
		if(lastestRecordCount == 0) {
			//エラーメッセージIDを返す
			messageId = CommonConstants.CAN_NOT_DELETE_ERROR_MESSAGE;
		}
		//戻り値が0以外だった場合
		//空文字を返す
		return messageId;
	}

	/**
	 * 案件情報の削除処理を行う
	 * @param projectInfoId 案件情報詳細ID
	 * @param projectId 案件情報ID
	 * @param userId ユーザID
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	void deleteProject(int projectInfoId, String projectId, String userId) throws ClassNotFoundException, SQLException {
		
		ProjectDeleteDAO projectDeleteDao = new ProjectDeleteDAO();

		//案件IDを基に案件情報論理削除処理を呼び出す
		projectDeleteDao.logicalDeletePjtInfo(projectId, userId);

		//案件情報IDを基に案件詳細情報論理削除処理を呼び出す
		projectDeleteDao.logicalDeletePjtDetail(projectInfoId, userId);

		//案件情報IDを基に従業員業務経験情報取得処理を呼び出す
		ArrayList<StubEmpExpDTO> empExpDtoList = new SelectLastestDataDAO().selectEmpExpByPjtInfoId(projectInfoId);
		//従業員業務経験情報論理削除処理を呼び出す
		projectDeleteDao.logicalDeleteEmpExpByPjtId(projectInfoId, userId);

		//従業員業務経験情報DTOの案件IDをnullに変更
		//従業員業務経験情報更新処理を呼び出す
		UpdateProjectDAO updateProjectDao = new UpdateProjectDAO();
		for(StubEmpExpDTO empExpDto : empExpDtoList) {
			updateProjectDao.insertEmpExp(empExpDto, userId);
		}
	}
}
