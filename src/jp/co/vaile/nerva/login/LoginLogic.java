package jp.co.vaile.nerva.login;

import java.sql.SQLException;

import jp.co.vaile.nerva.commonprocess.ConvertPassword;

public class LoginLogic {

	public LoginUserDto doLogin(String userId, String password) throws ClassNotFoundException, SQLException {
		//パスワードハッシュ化処理を呼び出し
		ConvertPassword convertPassword = new ConvertPassword();
		return new LoginUserDto(new LoginDao().getLoginUserInfo(userId, convertPassword.hashPassword(password)));
	}

}
