package commonprocesstest;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class LengthCheckTest {
	LengthCheck lengthcheck = new LengthCheck();

	@Test
	public void lengthCheckTest() {
		lengthcheck.isNomNullOrNomEmpty(null);
		lengthcheck.isNomNullOrNomEmpty("");
		lengthcheck.isNomNullOrNomEmpty("a");

		lengthcheck.isNomNullOrNomEmpty(" ");
		lengthcheck.isNomNullOrNomEmpty("a ");
		lengthcheck.isNomNullOrNomEmpty(" a");
		lengthcheck.isNomNullOrNomEmpty("　");
		lengthcheck.isNomNullOrNomEmpty("a　");
		lengthcheck.isNomNullOrNomEmpty("　a");
		lengthcheck.isNomNullOrNomEmpty("\u00A0");
		lengthcheck.isNomNullOrNomEmpty("\u00A0a");
		lengthcheck.isNomNullOrNomEmpty("a\u00A0");

		String checkParam = "stringCheckParam";
		lengthcheck.isStringLength(checkParam, (checkParam.length()+1));
		lengthcheck.isStringLength(checkParam, (checkParam.length()-1));
		lengthcheck.isStringLength(checkParam, (checkParam.length()));

		lengthcheck.isMaxStringLength(checkParam, checkParam.length());
		lengthcheck.isMaxStringLength(checkParam, checkParam.length()+1);
		lengthcheck.isMaxStringLength(checkParam, checkParam.length()-1);

		lengthcheck.isMinStringLength(checkParam, checkParam.length());
		lengthcheck.isMinStringLength(checkParam, checkParam.length()+1);
		lengthcheck.isMinStringLength(checkParam, checkParam.length()-1);

		int checkParamInt = 10000;
		lengthcheck.isMaxIntegerLength(checkParamInt, 5);
		lengthcheck.isMaxIntegerLength(checkParamInt, 4);
		lengthcheck.isMaxIntegerLength(checkParamInt, 6);

	}
}
