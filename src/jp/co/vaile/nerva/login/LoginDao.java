package jp.co.vaile.nerva.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.DBConnection;

public class LoginDao {
	private final static String USER_SELECT_SQL = "select u.user_id,u.user_name,u.company_id,u.admin_flg,c.company_privilege from (m_user u INNER JOIN m_belong_company c ON u.company_id = c.company_id) where user_id = ? and password = ?;";

	Connection con;

	public LoginEntity getLoginUserInfo(String userId, String password) throws ClassNotFoundException, SQLException {
		LoginEntity loginEtity = new LoginEntity();
		try {
			con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(USER_SELECT_SQL);
			ps.setString(1, userId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				loginEtity.loginSuccess = true;
				loginEtity.userId = rs.getString(1);
				loginEtity.userName = rs.getString(2);
				loginEtity.companyId = rs.getString(3);
				loginEtity.adminFlg = rs.getBoolean(4);
				loginEtity.companyPrivilege = rs.getBoolean(5);
			}
		} catch (ClassNotFoundException classNotFoundException) {
			throw classNotFoundException;
		} catch (SQLException SQLException) {
			throw SQLException;
		} finally {
			DBConnection.closeConnection();
		}
		return loginEtity;
	}

}
