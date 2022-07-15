package jp.co.vaile.nerva.masterUser.searchUser;

import java.sql.SQLException;
import java.util.List;

public class SearchUserMstLogic {

	/**
	 * ユーザーマスタメンテナンス画面で入力された値を持つDTOを受け取り、検索処理を呼び出し検索結果を返す。
	 * 
	 * @param searchUserDTO
	 * @return searchUserDAO.searchUserList(searchUserDTO)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchResultUserDTO> searchUserMstLogic(SearchUserDTO searchUserDTO)
			throws ClassNotFoundException, SQLException {
		SearchUserDAO searchUserDAO = new SearchUserDAO();

		// ユーザーマスタメンテナンス画面で入力された値を持つDTOを引数に、検索処理を呼び出し検索結果を返す。
		return searchUserDAO.searchUserList(searchUserDTO);
	}
}
