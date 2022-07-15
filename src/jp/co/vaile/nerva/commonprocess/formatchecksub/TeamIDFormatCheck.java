package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class TeamIDFormatCheck extends FormatCheck{

	public TeamIDFormatCheck() {
		super("[T]{1}[0-9]{4}[A-Z]{1}[0-9]{4}");
	}

}
