package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class HarfWidthAlphanumFormatCheck extends FormatCheck{
	public HarfWidthAlphanumFormatCheck() {
		super("^[0-9A-Za-z]+$");
	}
}
