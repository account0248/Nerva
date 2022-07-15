package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class ContractIDFormatCheck extends FormatCheck {

	public ContractIDFormatCheck() {
		super("[C]{1}[0-9]{9}");
	}
}
