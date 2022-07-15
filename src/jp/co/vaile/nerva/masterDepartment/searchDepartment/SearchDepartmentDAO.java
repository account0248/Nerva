package jp.co.vaile.nerva.masterDepartment.searchDepartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class SearchDepartmentDAO {

	/**
	 * 所属組織マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * 
	 * @param searchDepartmentDTO
	 * @return searchDepartmentDTOList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchDepartmentDTO> searchDepartmentList(SearchDepartmentDTO searchDepartmentDTO)
			throws SQLException, ClassNotFoundException {
		List<SearchDepartmentDTO> searchDepartmentDTOList = new ArrayList<>();

		LengthCheck lengthCheck = new LengthCheck();
		try {
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  department_id" + ",");
			sql.append("  department_name");
			sql.append(" FROM ");
			sql.append("( ");
			sql.append("m_belong_department");
			sql.append(")");

			String conditionWord = " WHERE ";

			List<Object> searchDepartment = new ArrayList<>();
			// P1に所属組織IDが含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchDepartmentDTO.getDepartmentId()) == true) {
				sql.append(conditionWord + "department_id =?");
				searchDepartment.add(searchDepartmentDTO.getDepartmentId());
				conditionWord = " AND ";
			}
			// P1に所属組織名が含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchDepartmentDTO.getDepartmentName()) == true) {
				sql.append(conditionWord + "department_name LIKE ?");

				searchDepartment.add("%" + searchDepartmentDTO.getDepartmentName() + "%");
				conditionWord = " AND ";
			}
			sql.append("ORDER BY ");
			sql.append("department_id" + " ASC ");			

			// 作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < searchDepartment.size(); i++) {

				stmt.setString(1 + i, (String) searchDepartment.get(i));
			}

			ResultSet rs = stmt.executeQuery();
			// 検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				SearchDepartmentDTO searchResultDepartment = new SearchDepartmentDTO();
				searchResultDepartment.setDepartmentId(rs.getString("department_id"));
				searchResultDepartment.setDepartmentName(rs.getString("department_name"));

				searchDepartmentDTOList.add(searchResultDepartment);
			}
			// 検索結果DTOに検索結果を戻り値として返す。
			return searchDepartmentDTOList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}
}
