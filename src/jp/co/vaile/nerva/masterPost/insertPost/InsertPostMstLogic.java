package jp.co.vaile.nerva.masterPost.insertPost;

import java.sql.SQLException;
import java.text.ParseException;

public class InsertPostMstLogic {
	
	/**
	 * 役職登録処理を呼び出す。
	 * 
	 * @param insertPostDTO
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void insertPostMstLogic(InsertPostDTO insertPostDTO) throws ClassNotFoundException, SQLException, ParseException {
		
		//所属組織登録処理の呼び出し
		InsertPostDAO insertPostDAO = new InsertPostDAO();
		insertPostDAO.insertPost(insertPostDTO);
	}
	
}
