package jp.co.vaile.nerva.masterCompany.searchCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;
import jp.co.vaile.nerva.commonprocess.MasterContents;

public class SearchCompanyDAO {

	/**
	 * 所属会社マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * 
	 * @param searchCompanyDTO ,companyPrivilege
	 * @return searchCompanyDTOList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchCompanyDTO> searchCompanyList(SearchCompanyDTO searchCompanyDTO, Boolean searchCompanyPrivilege,
			String companyPrivilege, String userId) throws SQLException, ClassNotFoundException {
		String parentCompanyValue = String.valueOf(MasterContents.PARENT_COMPANY_VALUE);
		String subCompanyValue = String.valueOf(MasterContents.SUBCOMPANY_VALUE);
		List<SearchCompanyDTO> searchCompanyDTOList = new ArrayList<>();
		List<Object> searchCompany = new ArrayList<>();
		LengthCheck lengthCheck = new LengthCheck();
		String conditionWord = " WHERE ";
		try {
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  m_belong_company.company_id" + ",");
			sql.append("  m_belong_company.company_name" + ",");
			sql.append("  m_belong_company.company_privilege" + ",");
			sql.append("  m_belong_company.company_code");
			sql.append(" FROM ");
			sql.append("m_belong_company");
			//子会社検索
			if (companyPrivilege.equals(subCompanyValue)) {
				sql.append(" INNER JOIN");
				sql.append("  m_user");
				sql.append("  ON");
				sql.append("  m_user.company_id");
				sql.append("  =");
				sql.append(" m_belong_company.company_id ");
				sql.append(conditionWord + "  user_id");
				sql.append("  = ");
				sql.append("?");
				searchCompany.add(userId);
				conditionWord = " AND ";
			}

			// P1に所属会社IDが含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchCompanyDTO.getCompanyId()) == true) {
				sql.append(conditionWord + " m_belong_company.company_id =?");
				searchCompany.add(searchCompanyDTO.getCompanyId());
				conditionWord = " AND ";
			}
			// P1に所属会社名が含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchCompanyDTO.getCompanyName()) == true) {
				sql.append(conditionWord + " m_belong_company.company_name LIKE ?");
				searchCompany.add("%" + searchCompanyDTO.getCompanyName() + "%");
				conditionWord = " AND ";
			}
			// P2の値がnullでない場合は、検索条件に追加する。(完全一致検索)
			if (searchCompanyPrivilege != null) {
				if (searchCompanyPrivilege) {
					sql.append(conditionWord + " m_belong_company.company_privilege= true ");
					conditionWord = " AND ";
				} else {
					sql.append(conditionWord + " m_belong_company.company_privilege= false ");
					conditionWord = " AND ";
				}
			}
			// P1に会社識別コードが含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchCompanyDTO.getCompanyCode()) == true) {
				sql.append(conditionWord + " m_belong_company.company_Code LIKE ?");
				searchCompany.add("%" + searchCompanyDTO.getCompanyCode() + "%");
				conditionWord = " AND ";
			}

			sql.append(" ORDER BY  m_belong_company.company_id ASC ");

			// 作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < searchCompany.size(); i++) {
				stmt.setString(1 + i, (String) searchCompany.get(i));
			}
		
			ResultSet rs = stmt.executeQuery();
			// 検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				SearchCompanyDTO searchResultCompany = new SearchCompanyDTO();
				searchResultCompany.setCompanyId(rs.getString("company_id"));
				searchResultCompany.setCompanyName(rs.getString("company_name"));
				if (rs.getBoolean("company_privilege")) {
					searchResultCompany.setCompanyGroup(parentCompanyValue);
				} else {
					searchResultCompany.setCompanyGroup(subCompanyValue);
				}
				searchResultCompany.setCompanyCode(rs.getString("company_code"));
				searchCompanyDTOList.add(searchResultCompany);
			}
			// 検索結果DTOに検索結果を戻り値として返す。
			return searchCompanyDTOList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * DBにアクセスし、会社識別コードを取得。
	 * 
	 * @return companyCodeList
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Character> searchCompanyCode() throws SQLException, ClassNotFoundException {

		try {
			List<Character> companyCodeList = new ArrayList<>();
			// DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			// SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  company_code");
			sql.append(" FROM ");
			sql.append("( ");
			sql.append("m_belong_company");
			sql.append(")");
			// 作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			// 検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				char companyCode = (char) rs.getByte("company_code");
				companyCodeList.add(companyCode);
			}
			return companyCodeList;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

	}
	 
}
