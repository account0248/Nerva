package jp.co.vaile.nerva.masterPost.updatePost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdatePostDAO {

	/**
	 * 役職マスタメンテナンス画面で入力された値を持つDTOを用いて、DBにアクセスし更新する。
	 * @param updatePostDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public void updatePost(UpdatePostDTO updatePostDTO) throws SQLException, ClassNotFoundException {
		// 1.DBに接続する。
		try (Connection con = DBConnection.getConnection();) {

			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE ");
			sql.append(" m_post ");
			sql.append(" SET ");
			sql.append(" post_name " + " = ?, ");
			sql.append(" update_user " + " = ?, ");
			sql.append(" update_time " + " = CURRENT_TIMESTAMP ");
			sql.append(" WHERE");
			sql.append(" post_id " + " = ? ");

			try (PreparedStatement stmt = con.prepareStatement(sql.toString());) {
				// 2.P1をDBの所属組織マスタに更新処理を行う。
				for (int i = 0; i < updatePostDTO.getPostName().length; i++) {
					stmt.setString(1, updatePostDTO.getPostName()[i]);
					stmt.setString(2, updatePostDTO.getUpdateUserId());
					stmt.setString(3, updatePostDTO.getPostId()[i]);
					stmt.executeUpdate();
				}
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
