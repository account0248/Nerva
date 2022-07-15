package jp.co.vaile.nerva.commonprocess;
import java.util.regex.Pattern;

public abstract class FormatCheck {

	// 正規表現に使われる、形式を表す文字列。
	private String pattern;
	// メソッドの結果を示す。
	private boolean statusOK;

	// patternを初期化するコンストラクタ。
	public FormatCheck(String pattern){
		this.pattern = pattern;
	}


	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、形式のチェックを行う。
	 * 形式のチェック方式は値に応じて決まる。
	 * 真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @return
	 */
	public final boolean isCorrectFormat (String checkParam) {
		// 引数に対して正規表現のチェックを行う。
		statusOK = true;

		//matchしたときmatchCheckParamがtrue
		boolean matchCheckParam = Pattern.matches(pattern, checkParam);

		if(!matchCheckParam) {
		statusOK = false;
		}
		// trueは引数が、形式通りである事を示す。
		// falseは引数が、形式にそぐわない事を示す。
		return statusOK;
	}
}
