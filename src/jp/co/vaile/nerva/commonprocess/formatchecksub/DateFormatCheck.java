package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class DateFormatCheck extends FormatCheck{

	public DateFormatCheck() {
		super("\\d{4}\\-\\d{2}\\-\\d{2}");
	}
}
