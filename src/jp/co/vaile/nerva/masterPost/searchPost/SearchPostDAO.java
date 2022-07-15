package jp.co.vaile.nerva.masterPost.searchPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.DBConnection;
import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class SearchPostDAO {


	/**
	 * 役職マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし検索結果をDTOに格納する。
	 * @param searchPostDTO 
	 * @return searchPostDTOList 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<SearchPostDTO> searchPostList(SearchPostDTO searchPostDTO) throws SQLException, ClassNotFoundException  {
		List<SearchPostDTO> searchPostDTOList=new ArrayList<>();
		
		LengthCheck lengthCheck = new LengthCheck();
		try {		
			//DBに接続する。
			Connection con = DBConnection.getConnection();
			StringBuilder sql = new StringBuilder();
			//SQL文を作成する。
			sql.append(" SELECT");
			sql.append("  post_id"+",");
			sql.append("  post_name");
			sql.append(" FROM " );
			sql.append("( " );
			sql.append( "m_post");
			sql.append(")" );
			
			String conditionWord = " WHERE ";
			
			List<Object> searchPost=new ArrayList<>();
			//P1に役職IDが含まれている場合は、検索条件に追加する。(完全一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchPostDTO.getPostId()) == true) {
				sql.append(conditionWord + "post_id =?");
				searchPost.add(searchPostDTO.getPostId());
				conditionWord = " AND ";
			}
			//P1に役職名が含まれている場合は、検索条件に追加する。(部分一致検索)
			if (lengthCheck.isNomNullOrNomEmpty(searchPostDTO.getPostName()) == true) {
				sql.append(conditionWord + "post_name LIKE ?");
				
				searchPost.add("%" + searchPostDTO.getPostName() + "%");
				conditionWord = " AND ";
			}
		
		
			//作成したSQL文で検索を行う。
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			for (int i = 0; i < searchPost.size(); i++) {
				
				stmt.setString(1 + i, (String) searchPost.get(i));
			}
			
			ResultSet rs = stmt.executeQuery();
			//検索結果DTOに値を格納する。以下繰り返し文。
			while (rs.next()) {
				SearchPostDTO searchResultPost=new SearchPostDTO ();
				searchResultPost.setPostId(rs.getString("post_id"));
				searchResultPost.setPostName(rs.getString("post_name"));

				searchPostDTOList.add(searchResultPost);
			}
			//検索結果DTOに検索結果を戻り値として返す。
			return searchPostDTOList;

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


