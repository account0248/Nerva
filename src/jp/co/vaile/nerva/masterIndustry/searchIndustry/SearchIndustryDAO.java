package jp.co.vaile.nerva.masterIndustry.searchIndustry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class SearchIndustryDAO {

	/**
	 * 業種マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * @param searchIndustryDTO 
	 * @return searchIndustryDTOList 役職の検索結果を格納したDTO型リスト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchIndustryDTO> searchIndustryList(SearchIndustryDTO searchIndustryDTO) throws SQLException, ClassNotFoundException  {
		List<SearchIndustryDTO> searchIndustryDTOList=new ArrayList<>();
		
		LengthCheck lengthCheck = new LengthCheck();
		try {	
			//DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			//SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  industry_id"+",");
			sql.append("  industry_name");
			sql.append(" FROM " );
			sql.append("( ");
			sql.append("m_industry");
			sql.append(")");

			
			String conditionWord = " WHERE ";
			
			List<Object> searchIndustry=new ArrayList<>();
			//P1に業種IDが含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchIndustryDTO.getIndustryId()) == true) {
				sql.append(conditionWord + "industry_id =?");
				searchIndustry.add(searchIndustryDTO.getIndustryId());
				conditionWord = " AND ";
			}
			//P1に業種名が含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchIndustryDTO.getIndustryName()) == true) {
				sql.append(conditionWord + "industry_name LIKE ?");
				
				searchIndustry.add("%" + searchIndustryDTO.getIndustryName() + "%");
				conditionWord = " AND ";
			}
			sql.append("ORDER BY ");
			sql.append("industry_id" + " ASC ");
			
			//作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
				for (int i = 0; i < searchIndustry.size(); i++) {
					
					
					stmt.setString(1 + i, (String) searchIndustry.get(i));
				}
			
				ResultSet rs = stmt.executeQuery();
				//検索結果DTOに値を格納する。以下繰り返し文。
				while (rs.next()) {
					SearchIndustryDTO searchResultIndustry=new SearchIndustryDTO ();
					searchResultIndustry.setIndustryId(rs.getString("industry_id"));
					searchResultIndustry.setIndustryName(rs.getString("industry_name"));

					searchIndustryDTOList.add(searchResultIndustry);
				}
				//検索結果DTOに検索結果を戻り値として返す。
				return searchIndustryDTOList;

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw e;
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}