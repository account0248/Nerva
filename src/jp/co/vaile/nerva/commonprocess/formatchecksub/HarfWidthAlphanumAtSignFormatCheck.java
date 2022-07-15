package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class HarfWidthAlphanumAtSignFormatCheck extends FormatCheck{
	public HarfWidthAlphanumAtSignFormatCheck() {
		super("^[0-9A-Za-z@._-]+$");
	}
}
