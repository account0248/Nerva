package jp.co.vaile.nerva.commonprocess.formatchecksub;

import jp.co.vaile.nerva.commonprocess.FormatCheck;

public class TimeFormatCheck extends FormatCheck{
	//開始時間等00:00
	public TimeFormatCheck() {
		super("(^[0-9]|[0-1][0-9]|2[0-6]):([0-9]|[0-5][0-9])");
	}
}
