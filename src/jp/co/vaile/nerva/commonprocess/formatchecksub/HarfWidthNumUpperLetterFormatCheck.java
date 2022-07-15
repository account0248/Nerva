package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class HarfWidthNumUpperLetterFormatCheck extends FormatCheck{

	public HarfWidthNumUpperLetterFormatCheck() {
		super("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$");
	}
}
