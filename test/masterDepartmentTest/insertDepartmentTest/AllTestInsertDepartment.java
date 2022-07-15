package masterDepartmentTest.insertDepartmentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AllTestInsertDepartment {

	@Test
	@DisplayName("insertDepartmentTestパッケージの一括実行")
	public void AllTestDepartment() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		InsertDepartmentMstTest insertDepartmentMstTest = new InsertDepartmentMstTest();
		// 全項目が正常に入力されている場合、登録が完了すること。
		insertDepartmentMstTest.allInputSuccessPattern();
		// 所属組織IDが未入力の場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentIdRequiredErrorPattern();
		// 所属組織IDが9文字で入力されている場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentIdLengthCheckErrorPattern9();
		// 所属組織IDが10文字で入力されている場合、登録が完了すること。
		insertDepartmentMstTest.departmentIdLengthCheckErrorPattern10();
		// 所属組織IDが11文字で入力されている場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentIdLengthCheckErrorPattern11();
		// 所属組織IDが半角英数字で入力されていない場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentIdHarfWidthAlphanumErrorPattern();
		// 所属組織IDが形式通りで入力されていない場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentIdFormatCheckErrorPattern();
		// 所属組織IDが既に登録されている場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentIdDuplicateCheckErrorPattern();
		// 所属組織名が未入力の場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentNameRequiredErrorPattern();
		// 所属組織名が10文字で入力されている場合、登録が完了すること。
		insertDepartmentMstTest.departmentNameLengthCheckErrorPattern10();
		// 所属組織名が11文字で入力されている場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentNameLengthCheckErrorPattern11();
		// 所属組織名が既に登録されている場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.departmentNameDuplicateCheckErrorPattern();
		// 全項目が未入力の場合、エラーメッセージを出力すること。
		insertDepartmentMstTest.allInputRequiredErrorPattern();
	}
}
