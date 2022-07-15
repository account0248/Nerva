package masterUserTest.insertUserTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AllTestInsertUser {

	@Test
	@DisplayName("insertUserパッケージテストの一括実行")
	public void AllTestUser() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		InsertUserMstTest insertUserMstTest = new InsertUserMstTest();
		// 全項目が正常に入力されている場合、登録が完了すること。
		insertUserMstTest.allInputSuccessPattern();
		// ユーザーIDが未入力の場合、エラーメッセージを出力すること。
		insertUserMstTest.userIdRequiredErrorPattern();
		// ユーザーIDが10文字で入力されている場合、登録が完了すること。
		insertUserMstTest.userIdLengthCheckErrorPattern10();
		// ユーザーIDが11文字で入力されている場合、エラーメッセージを出力すること。
		insertUserMstTest.userIdLengthCheckErrorPattern11();
		// ユーザーIDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
		insertUserMstTest.userIdHarfWidthAlphanumErrorPattern();
		// ユーザーIDが既に登録されている場合、エラーメッセージを出力すること。
		insertUserMstTest.userIdDuplicateCheckErrorPattern();
		// ユーザー名が未入力の場合、エラーメッセージを出力すること。
		insertUserMstTest.userNameRequiredErrorPattern();
		// ユーザー名が20文字で入力されている場合、登録が完了すること。
		insertUserMstTest.userNameLengthCheckErrorPattern20();
		// ユーザー名が21文字で入力されている場合、エラーメッセージを出力すること。
		insertUserMstTest.userNameLengthCheckErrorPattern21();
		// パスワードが未入力の場合、エラーメッセージを出力すること。
		insertUserMstTest.passwordRequiredErrorPattern();
		// パスワードが7文字で入力されている場合、エラーメッセージを出力すること。
		insertUserMstTest.passwordLengthCheckErrorPattern7();
		// パスワードが8文字で入力されている場合、登録が完了すること。
		insertUserMstTest.passwordLengthCheckErrorPattern8();
		// パスワードが20文字で入力されている場合、登録が完了すること。
		insertUserMstTest.passwordLengthCheckErrorPattern20();
		// パスワードが21文字で入力されている場合、エラーメッセージを出力すること。
		insertUserMstTest.passwordLengthCheckErrorPattern21();
		// パスワードが半角英数字大文字のそれぞれ一文字以上を含む文字列で入力されていない場合、エラーメッセージを出力すること。
		insertUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern();
		insertUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern2();
		insertUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern3();
		insertUserMstTest.passwordHarfWidthAlphanumUpperErrorPattern4();
		// 所属会社が未選択の場合、エラーメッセージを出力すること。
		insertUserMstTest.companyRequiredErrorPattern();
		// 所属会社が選択されている場合、登録が完了すること。
		insertUserMstTest.companyRequiredPattern();
		insertUserMstTest.companyRequiredPattern2();
		insertUserMstTest.companyRequiredPattern3();
		// 役職が未選択の場合、エラーメッセージを出力すること。
		insertUserMstTest.postRequiredErrorPattern();
		// 役職が選択されている場合、登録が完了すること。
		insertUserMstTest.postRequiredPattern();
		insertUserMstTest.postRequiredPattern2();
		insertUserMstTest.postRequiredPattern3();
		insertUserMstTest.postRequiredPattern4();
		insertUserMstTest.postRequiredPattern5();
		// 権限が未選択の場合、エラーメッセージを出力すること。
		insertUserMstTest.adminFlgRequiredErrorPattern();
		// 役職が選択されている場合、登録が完了すること。
		insertUserMstTest.adminFlgRequiredPattern();
		insertUserMstTest.adminFlgRequiredPattern2();
		// 全項目が未入力の場合、エラーメッセージを出力すること。
		insertUserMstTest.allInputRequiredErrorPattern();
	}
}
