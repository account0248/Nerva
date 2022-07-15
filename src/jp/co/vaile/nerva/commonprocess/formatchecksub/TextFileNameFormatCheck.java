package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class TextFileNameFormatCheck extends FormatCheck{

	//拡張子無しの勤務表のファイル名（勤務表_0000_V000000000_従業員名）
	public TextFileNameFormatCheck() {
		super("^勤務表_[0-9]{4}_[A-Z]{1}[0-9]{9}_[ぁ-んァ-ヶｱ-ﾝﾞﾟ一-龠a-zA-Z]{1,20}.text");
		
	}

}
