package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class ExcelFileNameFormatCheck  extends FormatCheck{
	//拡張子無しの勤務表のファイル名（勤務表(0000)_V000000000-従業員名）
	public ExcelFileNameFormatCheck() {
		super("^勤務表[(]{1}[0-9]{4}[)]{1}[_]{1}[A-Z]{1}[0-9]{9}-[ぁ-んァ-ヶｱ-ﾝﾞﾟ一-龠a-zA-Z]{1,20}.xlsx");
		
	}
}
