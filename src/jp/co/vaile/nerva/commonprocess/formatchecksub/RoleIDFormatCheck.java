package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class RoleIDFormatCheck extends FormatCheck {

	public RoleIDFormatCheck() {
		super("[T]{1}[0-9]{9}");
	}
}

