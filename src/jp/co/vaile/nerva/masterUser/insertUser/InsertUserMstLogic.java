package jp.co.vaile.nerva.masterUser.insertUser;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

import java.sql.SQLException;
import java.text.ParseException;

import jp.co.vaile.nerva.commonprocess.ConvertPassword;

public class InsertUserMstLogic {

	public void insertUserMstLogic(InsertUserDTO insertUserDTO)
			throws ClassNotFoundException, SQLException, ParseException {
		ConvertPassword convertPassword = new ConvertPassword();

		// パスワードをハッシュ化。
		String hashPassword = convertPassword.hashPassword(insertUserDTO.getPassword());

		// boolean型のadminFlgを初期化。
		boolean adminFlg = false;

		// inserUserDTO内adminFlgの値が1の場合、adminFlgをtrue(管理者)に変更する。
		if (insertUserDTO.getAdminFlg().equals(String.valueOf(ADMIN_VALUE))) {
			adminFlg = true;
		}

		// insertUserDTOのパスワードをハッシュ化した値に変更。
		insertUserDTO.setPassword(hashPassword);
		InsertUserDAO insertUserDAO = new InsertUserDAO();

		// 所属組織登録処理の呼び出し。
		insertUserDAO.insertUser(insertUserDTO, adminFlg);
	}

}
