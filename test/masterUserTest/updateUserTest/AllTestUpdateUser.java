package masterUserTest.updateUserTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestUpdateUser {
	@Test
	@DisplayName("updateUserパッケージテストの一括実行")
	// エラーメッセージパターン
	public void AllTestUser() throws ClassNotFoundException, ServletException, IOException {
		UpdateUserMstTest updateUserMstTest = new UpdateUserMstTest();

		// 1ユーザーの全カラム値が変更された場合、更新が完了すること。
		 updateUserMstTest.testInputOneUpdateUser();

		// 全ユーザーの全カラム値が変更された場合、更新が完了すること。
		updateUserMstTest.testInputAllUpdateUser();

		// ユーザー名が変更された場合、更新が完了すること。
		updateUserMstTest.testInputOneUpdateUserName();

		// ユーザー名が未入力の場合、エラーメッセージを出力すること。
		updateUserMstTest.userNameRequiredErrorPattern();

		// ユーザー名が20文字で入力された場合、更新が完了すること。
		updateUserMstTest.userNameLengthCheckErrorPattern20();

		// ユーザー名が21文字で入力された場合、エラーメッセージを出力すること。
		updateUserMstTest.userNameLengthCheckErrorPattern21();

		// パスワードが未入力の場合、エラーメッセージを出力すること。
		updateUserMstTest.passwordRequiredErrorPattern();

		// パスワードが7文字で入力された場合、エラーメッセージを出力すること。
		updateUserMstTest.passwordLengthCheckErrorPattern7();

		// パスワードが8文字で入力された場合、更新が完了すること。
		updateUserMstTest.passwordLengthCheckErrorPattern8();

		// パスワードが20文字で入力された場合、更新が完了すること。
		updateUserMstTest.passwordLengthCheckErrorPattern20();

		// パスワードが21文字で入力された場合、エラーメッセージを出力すること。
		updateUserMstTest.passwordLengthCheckErrorPattern21();

		// パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
		updateUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern();
		updateUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern2();
		updateUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern3();
		updateUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern4();
		
		//役職更新
		updateUserMstTest.updatePostPattern();
		
		//権限更新
		updateUserMstTest.updateAuthorityPattern();
		
		// 全項目が未入力の場合、エラーメッセージを出力すること。
		updateUserMstTest.allInputRequiredErrorPattern();

	}
}
