package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class IndustryIDFormatCheck extends FormatCheck {

	public IndustryIDFormatCheck() {
		super("[I]{1}[0-9]{9}");
	}
}
