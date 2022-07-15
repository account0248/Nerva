package logintest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

	
public class AllTestLogin {
	@Test
	@DisplayName("LoginActionTestクラスの全テスト実行")
		
	 public void allTestContract() throws ClassNotFoundException, ServletException, IOException {
	LoginActionTest loginActionTest = new LoginActionTest();
	
	//ログイン機能全テストパターン
	//全項目が正常に入力されている場合、メニュー画面に遷移すること。(親会社)
	loginActionTest.allInputSuccessPattern();
	//全項目が正常に入力されている場合、メニュー画面に遷移すること。(子会社)
	loginActionTest.allInputSuccessPattern2();
	//ユーザIDが未入力の場合、エラーメッセージを出力すること。
	loginActionTest.userIdRequiredErrorPattern();
	//パスワードが未入力の場合、エラーメッセージを出力すること。
	loginActionTest.passwordRequiredErrorPattern();
	//ユーザID、パスワードが未入力の場合、エラーメッセージを出力すること
	loginActionTest.userIdAndPasswordRequiredErrorPattern();
	//ユーザIDが不正な場合、エラーメッセージを出力すること。
	loginActionTest.incorrectUuserIdErrorPattern();
	//パスワードが不正な場合、エラーメッセージを出力すること。
	loginActionTest.incorrectPasswordErrorPattern();
	//ユーザID、パスワードが不正な場合、エラーメッセージを出力すること。
	loginActionTest.incorrectUserIdAndPasswordErrorPattern();
	 }
}
