package commonprocesstest;

import org.junit.jupiter.api.Test;

import jp.co.vaile.nerva.commonprocess.LengthCheck;

public class UnitTestLengthCheck {
	@Test
	public void unitTestLengthCheck () {
		LengthCheck lengthCheck = new LengthCheck();

		// 入力文字列がnullまたは空文字である場合、falseを返す。
		System.out.println("LengthCheck.isNomNullOrNomEmpty");
		System.out.println(lengthCheck.isNomNullOrNomEmpty(null));
		System.out.println(lengthCheck.isNomNullOrNomEmpty(""));
		// 入力文字列がスペース(\u0020, \u00A0, \u3000)のみであった場合、falseを返す。
		System.out.println(lengthCheck.isNomNullOrNomEmpty("\u0020"));
		System.out.println(lengthCheck.isNomNullOrNomEmpty(" "));
		System.out.println(lengthCheck.isNomNullOrNomEmpty("\u00A0"));
		System.out.println(lengthCheck.isNomNullOrNomEmpty("\u3000"));
		System.out.println(lengthCheck.isNomNullOrNomEmpty("　"));
		System.out.println(lengthCheck.isNomNullOrNomEmpty("\u0020\u00A0\u3000"));
		System.out.println(lengthCheck.isNomNullOrNomEmpty("\u00A0\u00A0\u00A0"));
		System.out.println("");
		// 入力文字列がスペース(\u0020, \u00A0, \u3000)と文字列の組み合わせであった場合、trueを返す。
		System.out.println(lengthCheck.isNomNullOrNomEmpty("\u0020a\u3000"));
		System.out.println(lengthCheck.isNomNullOrNomEmpty("a\u3000"));
		System.out.println(lengthCheck.isNomNullOrNomEmpty("\u0020a"));
		System.out.println("");


		String strLengthCheckParam = "aaaaa";
		System.out.println("LengthCheck.isStringLength");
		// 入力文字列が指定した文字列長さと等しい長さでなかった場合、falseを返す。
		System.out.println(lengthCheck.isStringLength(strLengthCheckParam, strLengthCheckParam.length()+1));
		System.out.println(lengthCheck.isStringLength(strLengthCheckParam, strLengthCheckParam.length()+100));
		System.out.println(lengthCheck.isStringLength(strLengthCheckParam, strLengthCheckParam.length()-100));
		System.out.println("");
		// 入力文字列が指定した文字列長さと等しい長さであった場合、trueを返す。
		System.out.println(lengthCheck.isStringLength(strLengthCheckParam, strLengthCheckParam.length()));
		System.out.println("");


		System.out.println("LengthCheck.isMaxStringLength");
		// 入力文字列が指定した文字列長さより長かった場合、falseを返す。
		System.out.println(lengthCheck.isMaxStringLength(strLengthCheckParam, strLengthCheckParam.length()-1));
		System.out.println(lengthCheck.isMaxStringLength(strLengthCheckParam, strLengthCheckParam.length()-100));
		System.out.println("");
		// 入力文字列が指定した文字列長さ以下であった場合、trueを返す。
		System.out.println(lengthCheck.isMaxStringLength(strLengthCheckParam, strLengthCheckParam.length()));
		System.out.println(lengthCheck.isMaxStringLength(strLengthCheckParam, strLengthCheckParam.length()+1));
		System.out.println(lengthCheck.isMaxStringLength(strLengthCheckParam, strLengthCheckParam.length()+100));
		System.out.println("");


		int intLengthCheckParam = 1111111111;
		int checkParamlength = String.valueOf(intLengthCheckParam).length();
		System.out.println("LengthCheck.isMaxIntegerLength");
		// 入力文字列が指定した文字列長さより長かった場合、falseを返す
		System.out.println(lengthCheck.isMaxIntegerLength(intLengthCheckParam, checkParamlength-1));
		System.out.println(lengthCheck.isMaxIntegerLength(intLengthCheckParam, checkParamlength-100));
		System.out.println("");
		// 入力文字列が指定した文字列長さ以下であった場合、trueを返す。
		System.out.println(lengthCheck.isMaxIntegerLength(intLengthCheckParam, checkParamlength));
		System.out.println(lengthCheck.isMaxIntegerLength(intLengthCheckParam, checkParamlength+1));
		System.out.println(lengthCheck.isMaxIntegerLength(intLengthCheckParam, checkParamlength+100));
		System.out.println("");
	}
}
