package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class PhoneNumFormatCheck extends FormatCheck {
	public PhoneNumFormatCheck() {
//		super("[0-9]{3,4}-[0-9]{3,4}-[0-9]{4}");
		super("^0\\d{2,3}-\\d{1,5}-\\d{4}$");
	}
}
