package jp.co.vaile.nerva.masterPost.insertPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertPostDAO {
	
	/**
	 * 役職マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし登録する。
	 * 
	 * @param insertPostDTO
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public void insertPost(InsertPostDTO insertPostDTO) throws ClassNotFoundException, SQLException {
		//1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO");
			sql.append(" m_post ");
			sql.append("(");
			sql.append(" post_id,");
			sql.append(" post_name,");
			sql.append(" regist_time,");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(" );
			sql.append("?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");

			
			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				//2.P1をDBの所属組織マスタに登録処理を行う。
				stmt.setString(1, insertPostDTO.getPostId());
				stmt.setString(2, insertPostDTO.getPostName());
				stmt.setString(3, insertPostDTO.getRegistUserId());
				stmt.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
