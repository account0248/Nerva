package jp.co.vaile.nerva.masterUser.searchUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.MasterContents;

public class SearchUserDAO {

	/**
	 * ユーザーマスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * 
	 * @param searchUserDTO
	 * @return departmentId 所属会社IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchResultUserDTO> searchUserList(SearchUserDTO searchUserDTO)
			throws SQLException, ClassNotFoundException {
		List<SearchResultUserDTO> searchResultUserDTOList = new ArrayList<>();

		LengthCheck lengthCheck = new LengthCheck();
		try {
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  u.user_id" + ",");
			sql.append("  u.user_name" + ",");
			sql.append("  u.password" + ",");
			sql.append("  c.company_id" + ",");
			sql.append("  p.post_id" + ",");
			sql.append("  u.admin_flg" + ",");
			sql.append("  c.company_privilege");
			sql.append(" FROM ");
			sql.append("( ");
			sql.append("m_user u ");
			sql.append("INNER JOIN ");
			sql.append("m_belong_company c ");
			sql.append("ON ");
			sql.append("u.company_id = c.company_id ");
			sql.append("INNER JOIN ");
			sql.append("m_post p ");
			sql.append("ON ");
			sql.append("u.post_id = p.post_id ");
			sql.append(")");

			String conditionWord = " WHERE ";

			List<Object> searchUser = new ArrayList<>();

			// ログインユーザーの所属会社が子会社の場合、所属会社IDを検索条件に追加する。
			if (searchUserDTO.getCompanyGroup().equals(String.valueOf(MasterContents.SUBCOMPANY_VALUE))) {
				sql.append(conditionWord + "c.company_id =?");
				searchUser.add(searchUserDTO.getLoginUserCompanyId());
				conditionWord = " AND ";
			}

			// P1にユーザーIDが含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchUserDTO.getUserId()) == true) {
				sql.append(conditionWord + "u.user_id =?");
				searchUser.add(searchUserDTO.getUserId());
				conditionWord = " AND ";
			}
			// P1にユーザー名が含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchUserDTO.getUserName()) == true) {
				sql.append(conditionWord + "u.user_name LIKE ?");

				searchUser.add("%" + searchUserDTO.getUserName() + "%");
				conditionWord = " AND ";
			}

			// P1に所属会社が含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchUserDTO.getCompany()) == true) {
				sql.append(conditionWord + "u.company_id =?");
				searchUser.add(searchUserDTO.getCompany());
				conditionWord = " AND ";
			}

			// P1に役職が含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchUserDTO.getPost()) == true) {
				sql.append(conditionWord + "u.post_id =?");
				searchUser.add(searchUserDTO.getPost());
				conditionWord = " AND ";
			}

			// P1に権限が含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchUserDTO.getAdminFlg()) == true) {
				sql.append(conditionWord + "u.admin_flg =?");
				searchUser.add(searchUserDTO.getAdminFlg());
				conditionWord = " AND ";
			}

			if (lengthCheck.isNomNullOrNomEmpty(searchUserDTO.getAdminFlg()) == true) {
				sql.append(conditionWord + "u.admin_flg =?");
				searchUser.add(searchUserDTO.getAdminFlg());
				conditionWord = " AND ";
			}

			// ソート順を指定
			sql.append("ORDER BY ");
			sql.append("c.company_privilege DESC, u.user_id");

			// 作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < searchUser.size(); i++) {

				stmt.setString(1 + i, (String) searchUser.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			// 検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				SearchResultUserDTO searchResultUser = new SearchResultUserDTO();
				searchResultUser.setUserId(rs.getString("user_id"));
				searchResultUser.setUserName(rs.getString("user_name"));
				searchResultUser.setPassword(rs.getString("password"));
				searchResultUser.setCompany(rs.getString("company_id"));
				searchResultUser.setPost(rs.getString("post_id"));
				searchResultUser.setAdminFlg(rs.getString("admin_flg"));

				searchResultUserDTOList.add(searchResultUser);
			}

			// 検索結果DTOに検索結果を戻り値として返す。
			return searchResultUserDTOList;

		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw e;
		}

	}
}
