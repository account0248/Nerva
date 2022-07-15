package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class EmpIDFormatCheck extends FormatCheck{

	public EmpIDFormatCheck() {
		super("[A-Z]{1}[0-9]{9}");
	}
}
