package masterCompanyTest.insertCompanyTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


public class AllTestInsertCompany {

	@Test
	@DisplayName("registCompanyパッケージテストの一括実行")
	public void AllTestCompany() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		InsertCompanyMstTest insertCompanyMstTest = new InsertCompanyMstTest();
		// 親会社の管理者が全項目を正常に入力されている場合、正常に処理が終了すること。
		insertCompanyMstTest.allInputSuccessPattern();
		// 子会社の管理者が全項目を正常に入力されている場合、登録が完了すること。
		insertCompanyMstTest.allInputChildSuccessPattern();
		// 親会社の管理者が所属会社IDを未入力の場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyIdRequiredErrorPattern();
		// 親会社の管理者が所属会社IDを9文字で入力されている場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyIdLengthCheckErrorPattern9();
		// 親会社の管理者が所属会社IDを10文字で入力されている場合、登録が完了すること。
		insertCompanyMstTest.companyIdLengthCheckErrorPattern10();
		// 親会社の管理者が所属会社IDを11文字で入力されている場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyIdLengthCheckErrorPattern11();
		// 親会社の管理者が所属会社IDを半角英数字で入力されていない場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyIdHarfWidthAlphanumErrorPattern();
		// 親会社の管理者が所属会社IDを形式通りで入力されていない場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyIdFormatCheckErrorPattern();
		// 親会社の管理者が所属会社IDを既に登録されている場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyIdDuplicateCheckErrorPattern();
		// 親会社の管理者が所属会社名を未入力の場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyNameRequiredErrorPattern();
		// 親会社の管理者が所属会社名を10文字で入力されている場合、登録が完了すること。
		insertCompanyMstTest.companyNameLengthCheckErrorPattern10();
		// 親会社の管理者が所属会社名を11文字で入力されている場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyNameLengthCheckErrorPattern11();
		// 親会社の管理者が所属会社名を既に登録されている場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyNameDuplicateCheckErrorPattern();
		// 親会社の管理者が所属会社グループ権限を選択されていない場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyGroupSelectCheckErrorPattern();
		// 親会社の管理者が所属会社グループ権限を選択した場合、登録が完了すること
		insertCompanyMstTest.companyGroupSelectPattern();
		insertCompanyMstTest.companyGroupSelectPattern2();
		// 親会社の管理者が会社識別コードを選択されていない場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyCodeSelectCheckErrorPattern();
		// 親会社の管理者が会社識別コードを選択されていない場合、エラーメッセージを出力すること。
		insertCompanyMstTest.companyCodeSelectPattern();
		insertCompanyMstTest.companyCodeSelectPattern2();
		insertCompanyMstTest.companyCodeSelectPattern3();
		// 親会社の管理者が全項目を未入力の場合、エラーメッセージを出力すること。
		insertCompanyMstTest.allInputRequiredErrorPattern();
	}

}
