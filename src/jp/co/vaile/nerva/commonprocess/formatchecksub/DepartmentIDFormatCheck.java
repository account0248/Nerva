package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class DepartmentIDFormatCheck extends FormatCheck {

	public DepartmentIDFormatCheck() {
		super("[D]{1}[0-9]{9}");
	}
}
