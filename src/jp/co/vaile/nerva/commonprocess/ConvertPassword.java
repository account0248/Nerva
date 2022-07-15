package jp.co.vaile.nerva.commonprocess;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConvertPassword {
	
	
	/**
	 * 受け取ったパスワードをSHA-1形式の文字列にハッシュ化して返す。
	 * @param password
	 * @return hashedPassword ハッシュ化したパスワード
	 */
	public String hashPassword(String password) {
		String hashedPassword = "";
		try {
			hashedPassword = String.format("%040x", new BigInteger(1, MessageDigest.getInstance("SHA-1").digest(password.getBytes())));
		} catch (NoSuchAlgorithmException e) {
			// プログラマのミスのためログを出力して終了
			e.printStackTrace();
		}
		return hashedPassword;
	}
}
