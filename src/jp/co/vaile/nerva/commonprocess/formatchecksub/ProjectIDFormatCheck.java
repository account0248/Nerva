package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class ProjectIDFormatCheck extends FormatCheck{
	public ProjectIDFormatCheck() {
		super("[P]{1}[0-9]{7}");
	}
}
