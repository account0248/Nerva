package jp.co.vaile.nerva.commonprocess;

public class LengthCheck {

	// メソッドの結果を示す。
	private boolean statusOK;

	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、
	 * 必須入力のチェックを行う。
	 * 真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @return
	 */
	public boolean isNomNullOrNomEmpty(String checkParam) {
		// 引数に対して両端の半角スペースと全角スペースを削除し、nullチェック、空文字チェックの両方を実行し、真偽値を返す。
		statusOK = true;

		if (checkParam == null || checkParam.length() == 0) {
			statusOK = false;
			return statusOK;
		}

		int targetChara = 0;
		int checkParamLength = checkParam.length();
		char[] val = checkParam.toCharArray();

		while ((targetChara < checkParamLength) && ((val[targetChara] <= '\u0020') || (val[targetChara] == '\u00A0')
				|| (val[targetChara] == '\u3000'))) {
			targetChara++;
		}
		while ((targetChara < checkParamLength) && ((val[checkParamLength - 1] <= '\u0020')
				|| (val[checkParamLength - 1] == '\u00A0') || (val[checkParamLength - 1] == '\u3000'))) {
			checkParamLength--;
		}
		if (targetChara > 0 || checkParamLength < checkParam.length()) {
			checkParam = checkParam.substring(targetChara, checkParamLength);
			if (checkParam.length() == 0) {
				statusOK = false;
				return statusOK;
			}
		}
		// trueは引数がスペース削除後、null、または空文字でない事を示す。
		// falseは引数がスペース削除後、null、または空文字である事を示す。
		return statusOK;
	}

	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、入力桁数のチェックを行う。
	 * 真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @param lengthSize チェックしたいパラメータの長さ
	 * @return
	 */
	public boolean isStringLength(String checkParam, int lengthSize) {
		// 第一引数に対して、第二引数を用いて文字列の長さをチェックし、真偽値を返す。
		statusOK = true;
		int checkParamlength = checkParam.length();
		if (checkParamlength != lengthSize) {
			statusOK = false;
		}
		// trueは第一引数の文字列の長さが第二引数の長さである事を示す。
		// falseは第一引数の文字列の長さが第二引数の長さでない事を示す。
		return statusOK;
	}

	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、最大入力桁数のチェックを行う。
	 * 真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @param maxLengthSize チェックしたいパラメータの長さ
	 * @return
	 */
	public boolean isMaxStringLength(String checkParam, int maxLengthSize) {
		// 第一引数に対して、第二引数を用いて文字列の長さをチェックし、真偽値を返す。
		statusOK = true;

		int checkParamlength = checkParam.length();
		if (checkParamlength > maxLengthSize) {
			statusOK = false;
		}
		// trueは第一引数の文字列の長さが第二引数の長さ以下である事を示す。
		// falseは第一引数の文字列の長さが第二引数の長さ以下でない事を示す。
		return statusOK;
	}

	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、最大入力桁数のチェックを行う。
	 * 真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @param maxLengthSize チェックしたいパラメータの長さ
	 * @return
	 */
	public boolean isMaxIntegerLength(int checkParam, int maxLengthSize) {
		// 第一引数に対して、第二引数を用いて文字列の長さをチェックし、真偽値を返す。
		statusOK = true;

		int checkParamlength = String.valueOf(checkParam).length();
		if (checkParamlength > maxLengthSize) {
			statusOK = false;
		}
		// trueは第一引数の文字列の長さが第二引数の長さ以下である事を示す。
		// falseは第一引数の文字列の長さが第二引数の長さ以下でない事を示す。
		return statusOK;
	}

	/**
	 * このメソッドは呼び出し元のメソッドから渡されたパラメータに対して、最低入力桁数のチェックを行う。
	 * 真偽値を返す。
	 *
	 * @param checkParam チェックしたいパラメータ
	 * @param minLengthSize チェックしたいパラメータの長さ
	 * @return
	 */
	public boolean isMinStringLength(String checkParam, int minLengthSize) {
		// 第一引数に対して、第二引数を用いて文字列の長さをチェックし、真偽値を返す。
		statusOK = true;

		int checkParamlength = String.valueOf(checkParam).length();
		if (checkParamlength < minLengthSize) {
			statusOK = false;
		}
		// trueは第一引数の文字列の長さが第二引数の長さ以上である事を示す。
		// falseは第一引数の文字列の長さが第二引数の長さ以上でない事を示す。
		return statusOK;
	}
}