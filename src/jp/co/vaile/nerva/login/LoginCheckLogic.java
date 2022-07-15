package jp.co.vaile.nerva.login;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class LoginCheckLogic {
	
	LengthCheck lengthCheck = new LengthCheck();

	boolean checkLogin(String userId, String password) {

		boolean loginFlg = loginInputCheck( userId,password);
	
		return loginFlg;
	}
	
	private boolean loginInputCheck(String userId,String password) {

		if (lengthCheck.isNomNullOrNomEmpty(userId) == false || lengthCheck.isNomNullOrNomEmpty(password) == false) {
			return true;
		}
		return false;
	}

}
