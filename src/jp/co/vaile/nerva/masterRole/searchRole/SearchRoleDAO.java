package jp.co.vaile.nerva.masterRole.searchRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class SearchRoleDAO {


	/**
	 * 担当マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * @param searchRoleDTO 
	 * @return roleId 担当IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchRoleDTO> searchRoleList(SearchRoleDTO searchRoleDTO) throws SQLException, ClassNotFoundException  {
		List<SearchRoleDTO> searchRoleDTOList=new ArrayList<>();

		LengthCheck lengthCheck = new LengthCheck();
		try {		
			//DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			//SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  role_id"+",");
			sql.append("  role_name");
			sql.append(" FROM " );
			sql.append("( " );
			sql.append( "m_role");
			sql.append(")" );

			String conditionWord = " WHERE ";

			List<Object> searchRole=new ArrayList<>();
			//P1に担当IDが含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchRoleDTO.getRoleId()) == true) {
				sql.append(conditionWord + "role_id =?");
				searchRole.add(searchRoleDTO.getRoleId());
				conditionWord = " AND ";
			}
			//P1に担当名が含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchRoleDTO.getRoleName()) == true) {
				sql.append(conditionWord + "role_name LIKE ?");

				searchRole.add("%" + searchRoleDTO.getRoleName() + "%");
				conditionWord = " AND ";
			}


			//作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < searchRole.size(); i++) {


				stmt.setString(1 + i, (String) searchRole.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			//検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				SearchRoleDTO searchResultRole=new SearchRoleDTO ();
				searchResultRole.setRoleId(rs.getString("role_id"));
				searchResultRole.setRoleName(rs.getString("role_name"));
				

				searchRoleDTOList.add(searchResultRole);
			}
			//検索結果DTOに検索結果を戻り値として返す。
			return searchRoleDTOList;

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


