package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class PostalCodeFormatCheck extends FormatCheck {
	public PostalCodeFormatCheck() {
		super("[0-9]{3}-[0-9]{4}");
	}
}
