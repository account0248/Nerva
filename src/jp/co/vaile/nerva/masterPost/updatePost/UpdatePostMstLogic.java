package jp.co.vaile.nerva.masterPost.updatePost;

import java.sql.SQLException;

public class UpdatePostMstLogic {

	/**
	 * 役職更新処理を呼び出す。
	 * @param updatePostDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public void updatePostMstLogic(UpdatePostDTO updatePostDTO) throws ClassNotFoundException, SQLException {
		UpdatePostDAO updatePostDAO = new UpdatePostDAO();
		
		updatePostDAO.updatePost(updatePostDTO);
		
	}

}
