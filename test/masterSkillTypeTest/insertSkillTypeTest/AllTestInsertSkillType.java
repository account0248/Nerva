package masterSkillTypeTest.insertSkillTypeTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AllTestInsertSkillType {
	@Test
	@DisplayName("registSkillTypeパッケージテストの一括実行")
	public void AllTestSkillType()
			throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		InsertSkillTypeMstTest insertSkillTypeMstTest = new InsertSkillTypeMstTest();
		// 全項目が正常に入力されている場合、登録が完了すること。 （年数）
		insertSkillTypeMstTest.allInputYearsSuccessPattern();
		// 全項目が正常に入力されている場合、登録が完了すること。 （取得日）
		insertSkillTypeMstTest.allInputDateOfAcquisitionSuccessPattern();
		// スキル種別IDが未入力の場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeIdRequiredErrorPattern();
		// スキル種別IDが9文字で入力されている場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeIdLengthCheckErrorPattern9();
		// スキル種別IDが10文字で入力されている場合、登録が完了すること。
		insertSkillTypeMstTest.skillTypeIdLengthCheckErrorPattern10();
		// スキル種別IDが11文字で入力されている場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeIdLengthCheckErrorPattern11();
		// スキル種別IDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeIdHarfWidthAlphanumErrorPattern();
		// スキル種別IDが形式通りで入力されていない場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeIdFormatCheckErrorPattern();
		// スキル種別IDが既に登録されている場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeIdDuplicateCheckErrorPattern();
		// スキル種別名が未入力の場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeNameRequiredErrorPattern();
		// スキル種別名が20文字で入力されている場合、登録が完了すること。
		insertSkillTypeMstTest.skillTypeNameLengthCheckErrorPattern10();
		// スキル種別名が21文字で入力されている場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeNameLengthCheckErrorPattern11();
		// スキル種別名が既に登録されている場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.skillTypeNameDuplicateCheckErrorPattern();
		// 年数/取得日フラグが未選択の場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.yearsDateOfAcquisitionInputRequiredErrorPattern();
		// 全項目が未入力の場合、エラーメッセージを出力すること。
		insertSkillTypeMstTest.allInputRequiredErrorPattern();
	}
}
