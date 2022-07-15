package jp.co.vaile.nerva.masterContract.searchContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class SearchContractDAO {


	/**
	 * 契約形態マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * @param searchContractDTO 
	 * @return ContractId 契約形態IDを表す文字列
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchContractDTO> searchContractList(SearchContractDTO searchContractDTO) throws SQLException, ClassNotFoundException  {
		List<SearchContractDTO> searchContractDTOList=new ArrayList<>();

		LengthCheck lengthCheck = new LengthCheck();
		try {		
			//DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			//SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  contract_type_id"+",");
			sql.append("  contract_type");
			sql.append(" FROM " );
			sql.append("( " );
			sql.append( "m_contract_type");
			sql.append(")" );

			String conditionWord = " WHERE ";

			List<Object> searchContract=new ArrayList<>();
			//P1に契約形態IDが含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchContractDTO.getContractId()) == true) {
				sql.append(conditionWord + "contract_type_id =?");
				searchContract.add(searchContractDTO.getContractId());
				conditionWord = " AND ";
			}
			//P1に契約形態名が含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchContractDTO.getContractName()) == true) {
				sql.append(conditionWord + "contract_type LIKE ?");

				searchContract.add("%" + searchContractDTO.getContractName() + "%");
				conditionWord = " AND ";
			}


			//作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < searchContract.size(); i++) {


				stmt.setString(1 + i, (String) searchContract.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			//検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				SearchContractDTO searchResultContract=new SearchContractDTO ();
				searchResultContract.setContractId(rs.getString("contract_type_id"));
				searchResultContract.setContractName(rs.getString("contract_type"));
				

				searchContractDTOList.add(searchResultContract);
			}
			//検索結果DTOに検索結果を戻り値として返す。
			return searchContractDTOList;

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


