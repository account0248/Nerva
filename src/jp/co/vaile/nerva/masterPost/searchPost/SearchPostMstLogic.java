package jp.co.vaile.nerva.masterPost.searchPost;

import java.sql.SQLException;
import java.util.List;

//役職検索処理を呼び出す。
public class SearchPostMstLogic {
	
	/**
	 * 役職検索処理を呼び出す。
	 * 
	 * @param searchPostDTO
	 * @return searchPostList
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public  List<SearchPostDTO>searchPostMstLogic (SearchPostDTO searchPostDTO) throws ClassNotFoundException, SQLException{
		SearchPostDAO searchPostDAO =new SearchPostDAO();

		return searchPostDAO.searchPostList(searchPostDTO);
	}
}



