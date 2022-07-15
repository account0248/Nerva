package jp.co.vaile.nerva.searchTeam;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class DeleteTeamCheckLogic {
	ErrorMsg errorMsg = new ErrorMsg();
	CommonConstants commonConstants = new CommonConstants();

	List<String> checkDeleteTeam(String teamId, String loginUserId) throws ClassNotFoundException, SQLException {
		List<String> errorList = new ArrayList<String>();
		//1.P1のチームIDとP2のログインユーザーを引数にチーム所属部長チェック処理を呼び出す
		DeleteTeamDAO deleteTeamDAO = new DeleteTeamDAO();
		//1-1チーム所属部長チェック処理結果がtrueの場合、2に進む。
		//1-2チーム所属部長チェック処理結果がfalseの場合、エラーメッセージを呼び出し、エラーメッセージリストに追加する。
		String[] canNotDeleteTeamList = { TEAM };
		if (deleteTeamDAO.checkTeamManager(teamId, loginUserId) == false) {

			String errorMessage = errorMsg.returnErrorMsg(CAN_NOT_DELETE_ERROR_MESSAGE, canNotDeleteTeamList);
			errorList.add(errorMessage);
			return errorList;
		}
		//2.P1のチームIDを引数にチーム所属チェック処理を呼び出す。
		//2-1チーム所属チェック処理結果がtrueの場合3に進む。
		//2-2チーム所属チェック処理結果がfalseの場合。エラーメッセージを呼び出し、エラーメッセージリストに追加する。
		if (deleteTeamDAO.checkBelongMemberByTeamId(teamId) == false) {
			String errorMessage = errorMsg.returnErrorMsg(CAN_NOT_DELETE_ERROR_MESSAGE, canNotDeleteTeamList);
			errorList.add(errorMessage);
			return errorList;
		}
		//3.P1のチームIDを引数に案件配属チェック処理を呼び出す。
		//3-1案件配属チェック処理結果がtrueの場合nullを返す。
		//3-2案件配属チェック処理結果がfalseの場合、エラーメッセージを呼び出し、エラーメッセージリストに追加する。
		if (deleteTeamDAO.checkBelongProject(teamId) == false) {
			String errorMessage = errorMsg.returnErrorMsg(CAN_NOT_DELETE_ERROR_MESSAGE, canNotDeleteTeamList);
			errorList.add(errorMessage);
			return errorList;
		}
		return errorList;
	}
}
