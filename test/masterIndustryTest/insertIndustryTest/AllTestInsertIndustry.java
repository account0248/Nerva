package masterIndustryTest.insertIndustryTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AllTestInsertIndustry {

	@Test
	@DisplayName("registIndustryパッケージテストの一括実行")
	public void AllTestIndustry() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		InsertIndustryMstTest insertIndustryMstTest = new InsertIndustryMstTest();
		// 全項目が正常に入力されている場合、登録が完了すること。
		insertIndustryMstTest.allInputSuccessPattern();
		// 業種IDが未入力の場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryIdRequiredErrorPattern();
		// 業種IDが9文字で入力されている場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryIdLengthCheckErrorPattern9();
		// 業種IDが10文字で入力されている場合、登録が完了すること。
		insertIndustryMstTest.industryIdLengthCheckErrorPattern10();
		// 業種IDが11文字で入力されている場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryIdLengthCheckErrorPattern11();
		// 業種IDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryIdHarfWidthAlphanumErrorPattern();
		// 業種IDが形式通りで入力されていない場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryIdFormatCheckErrorPattern();
		// 業種IDが既に登録されている場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryIdDuplicateCheckErrorPattern();
		// 業種名が未入力の場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryNameRequiredErrorPattern();
		// 業種名が10文字で入力されている場合、登録が完了すること。
		insertIndustryMstTest.industryNameLengthCheckErrorPattern10();
		// 業種名が11文字で入力されている場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryNameLengthCheckErrorPattern11();
		// 業種名が既に登録されている場合、エラーメッセージを出力すること。
		insertIndustryMstTest.industryNameDuplicateCheckErrorPattern();
		// 全項目が未入力の場合、エラーメッセージを出力すること。
		insertIndustryMstTest.allInputRequiredErrorPattern();
	}
}
