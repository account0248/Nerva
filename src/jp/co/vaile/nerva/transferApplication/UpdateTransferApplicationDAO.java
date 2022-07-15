package jp.co.vaile.nerva.transferApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class UpdateTransferApplicationDAO {

	//sql文
	static String sqlupdate;

	public static PreparedStatement ps;

	static final String UPDATE = " UPDATE ";

	static final String WH = " WHERE ";

	static final String SET = " SET ";

	static final String CURRENT_TIMESTAMP = " CURRENT_TIMESTAMP ";

	//join用テーブル
	static final String APPLICATION＿TRANSFER = " transfer_application ";

	//更新用カラム
	static final String CHANGE_DATE = " change_date ";

	static final String UNAPPROVED_FLG = " unapproved_flg ";

	static final String APPLICATION_NUM = " application_num ";

	//join用カラム

	public void updateTransferApplication(int applicationNum) throws ClassNotFoundException, SQLException  {
		try (Connection con = DBConnection.getConnection();) {
			sqlupdate = UPDATE + APPLICATION＿TRANSFER + SET + CHANGE_DATE +  "="+ CURRENT_TIMESTAMP +"," + UNAPPROVED_FLG + " = ? "
					+ WH + APPLICATION_NUM + " = ? ;";



			try (PreparedStatement ps = con.prepareStatement(sqlupdate);) {

				ps.setBoolean(1, true);
				ps.setInt(2, applicationNum);



				ps.executeUpdate();

				ps.close();
			}
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
