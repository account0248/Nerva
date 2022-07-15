package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class CompanyIDFormatCheck extends FormatCheck{

	public CompanyIDFormatCheck() {
		super("[CP]{2}[0-9]{8}");
	}

}
