package jp.co.vaile.nerva.searchTeam;

import static jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants.*;

import java.sql.SQLException;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;

public class SearchTeamLogic {
	/**
	 * チーム情報の検索処理を呼び出す。
	 * @param searchTeamPageDTO チーム検索画面で記載された入力情報。
	 * @return チーム検索結果DTOリスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<SearchTeamDTO> searchTeam(SearchTeamPageDTO searchTeamPageDTO, String belongCompanyId)
			throws ClassNotFoundException, SQLException {
		SearchTeamDAO searchTeamDAO = new SearchTeamDAO();
		//1.	チーム検索入力情報DTOを引数にチーム検索処理の呼び出しを行う。
		//2.	チーム検索結果DTOリストを戻り値とする。
		return searchTeamDAO.selectByTeamList(searchTeamPageDTO, belongCompanyId);
	}

	/**
	 * チーム削除失敗時のエラーメッセージを返す。
	 * @return エラーメッセージ
	 */
	String returnSearchTeamErrorMsg() {
		//1.	チーム削除失敗時のエラーメッセージを取得する。
		ErrorMsg errorMsg = new ErrorMsg();
		String[] searchTeamList = { TEAM };
		String error = errorMsg.returnErrorMsg(SEARCH_RESULT_EMPTY_ERROR_MESSAGE, searchTeamList);
		//2.	エラーメッセージを戻り値とする。
		return error;
	}
}
