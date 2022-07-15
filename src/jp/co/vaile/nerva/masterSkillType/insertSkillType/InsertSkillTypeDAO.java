package jp.co.vaile.nerva.masterSkillType.insertSkillType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class InsertSkillTypeDAO {
	/**
	* スキル種別の登録処理を行う
	* @param insertSkillTypeDTO スキル種別マスタメンテナンス画面での入力値
	* @param yearsDateOfAcquisition 年数/取得日の真偽値
	* @return　エラーメッセージ
	*/
	public void insertSkillType(InsertSkillTypeDTO insertSkillTypeDTO, boolean yearsDateOfAcquisition) throws ClassNotFoundException, SQLException {
		// 1.DBに接続する。
		try(Connection con = DBConnection.getConnection();){
					
			StringBuilder sql = new StringBuilder();
					
			sql.append(" INSERT INTO ");
			sql.append(" m_skill_Type ");
			sql.append("(");
			sql.append(" skill_type_id,");
			sql.append(" skill_type_name,");
			sql.append(" years_date_of_acquisition_flg,");
			sql.append(" regist_time,");
			sql.append(" regist_user");
			sql.append(")");
			sql.append(" VALUES ");
			sql.append("(" );
			sql.append("?,?,?,CURRENT_TIMESTAMP,?");
			sql.append(")");
		
					
			PreparedStatement stmt = con.prepareStatement(sql.toString());
			//2.P1をDBのスキル種別マスタに登録処理を行う。
			stmt.setString(1, insertSkillTypeDTO.getSkillTypeId());
			stmt.setString(2, insertSkillTypeDTO.getSkillTypeName());
			if(yearsDateOfAcquisition) {
				stmt.setBoolean(3, yearsDateOfAcquisition);
			}else {
				stmt.setBoolean(3, yearsDateOfAcquisition);
			}
			stmt.setString(4, insertSkillTypeDTO.getRegistUserId());
			stmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
