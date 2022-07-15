package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class SkillTypeIDFormatCheck extends FormatCheck{
	
	public SkillTypeIDFormatCheck() {
		super("[S]{1}[0-9]{9}");
	}
}
