package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class PostIDFormatCheck extends FormatCheck {

	public PostIDFormatCheck() {
		super("[P]{1}[0-9]{9}");
	}
}
