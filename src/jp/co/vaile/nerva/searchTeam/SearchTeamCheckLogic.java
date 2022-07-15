package jp.co.vaile.nerva.searchTeam;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.FormatCheck;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;
import jp.co.vaile.nerva.commonprocess.formatchecksub.HarfWidthAlphanumFormatCheck;

public class SearchTeamCheckLogic {
	ErrorMsg errorMsg = new ErrorMsg();
	LengthCheck lengthCheck = new LengthCheck();
	FormatCheck harfWidthAlphanumFormatCheck = new HarfWidthAlphanumFormatCheck();
	CommonConstants commonConstants = new CommonConstants();

	/**
	 * 入力情報が正しいかチェックを行い、正しくない場合エラーリストに追加し、戻り値とする。
	 * @param searchTeamPageDTO チーム検索画面で記載された入力情報。
	 * @return エラーメッセージが格納されたリスト
	 */
	List<String> checkSearchTeam(SearchTeamPageDTO searchTeamPageDTO) {
		List<String> errorList = new ArrayList<String>();

		//1.P1のチームIDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkTeamId(searchTeamPageDTO.getTeamId()), errorList);
		//2.P1のチーム名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkTeamName(searchTeamPageDTO.getTeamName()), errorList);
		//3.P1のリーダーのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkTeamLeader(searchTeamPageDTO.getTeamLeaderName()), errorList);
		//4.P1の担当案件IDのエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkProjectId(searchTeamPageDTO.getProjectId()), errorList);
		//5.P1の担当案件名のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkProjectName(searchTeamPageDTO.getProjectName()), errorList);
		//6.P1の発注元のエラーメッセージがある場合、エラーリストに追加する。無い場合は追加しない。
		errorList = errorMsg.errorMsgNullCheck(
				checkOrderSource(searchTeamPageDTO.getOrderSourceName()), errorList);

		return errorList;
	}

	/**
	 * チームIDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param teamId チーム検索画面で記載されたチームID
	 * @return エラーメッセージ
	 */
	String checkTeamId(String teamId) {
		String[] teamIdList = { TEAM_ID, "10" };
		//1.P1に値が入っていない場合、nullを返す。入力されている場合は2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(teamId) == false) {
			return null;
		}

		//2.P1が半角英数字で入力されていない場合、エラーメッセージを返す。入力されている場合は3に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(teamId) == false) {
			return errorMsg.returnErrorMsg(PROJECT_ID_FORMAT_ERROR_MESSAGE, teamIdList);
		}

		//3.P1が10文字で入力されていない場合、エラーメッセージを返す。入力されている場合はnullを返す。
		if (lengthCheck.isStringLength(teamId, 10) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, teamIdList);
		}
		return null;
	}

	/**
	 * チーム名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param teamName
	 * @return エラーメッセージ
	 */
	String checkTeamName(String teamName) {
		String[] teamNameList = { TEAM_NAME, "20" };
		//1.P1に値が入っていない場合、nullを返す。入力されている場合は2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(teamName) == false) {
			return null;
		}

		//2.P1が20文字以内で入力されていない場合、エラーメッセージを返す。入力されている場合はnullを返す。
		if (lengthCheck.isMaxStringLength(teamName, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, teamNameList);
		}
		return null;
	}

	/**
	 * チームリーダー名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param teamLeader
	 * @return エラーメッセージ
	 */
	String checkTeamLeader(String teamLeader) {
		String[] teamLeaderList = { TEAM_LEADER, "20" };
		//1.P1に値が入っていない場合、nullを返す。入力されている場合は2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(teamLeader) == false) {
			return null;
		}
		//2.P1が20文字以内で入力されていない場合、エラーメッセージを返す。入力されている場合はnullを返す。
		if (lengthCheck.isMaxStringLength(teamLeader, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, teamLeaderList);
		}
		return null;
	}

	/**
	 * 担当案件IDが正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param projectId
	 * @return エラーメッセージ
	 */
	String checkProjectId(String projectId) {
		String[] projectIdList = { RESPONSIBLE_PROJECT_ID, "8" };
		//1.P1に値が入っていない場合、nullを返す。入力されている場合は2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(projectId) == false) {
			return null;
		}
		//2.P1が半角英数字で入力されていない場合、エラーメッセージを返す。入力されている場合は3に進む。
		if (harfWidthAlphanumFormatCheck.isCorrectFormat(projectId) == false) {
			return errorMsg.returnErrorMsg(PROJECT_ID_FORMAT_ERROR_MESSAGE, projectIdList);
		}
		//3.P1が8文字で入力されていない場合、エラーメッセージを返す。入力されている場合はnullを返す。
		if (lengthCheck.isStringLength(projectId, 8) == false) {
			return errorMsg.returnErrorMsg(INPUT_LENGTH_ERROR_MESSAGE, projectIdList);
		}

		return null;
	}

	/**
	 * 担当案件名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param projectName
	 * @return エラーメッセージ
	 */
	String checkProjectName(String projectName) {
		String[] projectNameList = { RESPONSIBLE_PROJECT_NAME, "256" };
		//1.P1に値が入っていない場合、nullを返す。入力されている場合は2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(projectName) == false) {
			return null;
		}
		//2.P1が256文字以内で入力されていない場合、エラーメッセージを返す。入力されている場合はnullを返す。
		if (lengthCheck.isMaxStringLength(projectName, 256) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, projectNameList);
		}
		return null;
	}

	/**
	 * 発注元名が正しく入力されていない場合、エラーメッセージを戻り値として返す。
	 * エラーがない場合、nullを返す。
	 * @param orderSourceName
	 * @return エラーメッセージ
	 */
	String checkOrderSource(String orderSourceName) {
		//1.P1に値が入っていない場合、nullを返す。入力されている場合は2に進む。
		String[] orderSourceNameList = { ORDER_SOURCE_NAME, "20" };
		//1.P1に値が入っていない場合、nullを返す。入力されている場合は2に進む。
		if (lengthCheck.isNomNullOrNomEmpty(orderSourceName) == false) {
			return null;
		}
		//2.P1が20文字以内で入力されていない場合、エラーメッセージを返す。入力されている場合はnullを返す。
		if (lengthCheck.isMaxStringLength(orderSourceName, 20) == false) {
			return errorMsg.returnErrorMsg(INPUT_MAX_LENGTH_ERROR_MESSAGE, orderSourceNameList);
		}
		return null;
	}
}
