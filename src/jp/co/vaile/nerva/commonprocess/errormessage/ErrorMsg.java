package jp.co.vaile.nerva.commonprocess.errormessage;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ErrorMsg {

	/**
	 * 第一引数からエラーメッセージを取得し、第二引数でエラーメッセージを生成する。
	 * エラーメッセージを返す。
	 *
	 * @param key メッセージID
	 * @param replaceElement エラーメッセージに対して置換するべき要素
	 * @return
	 */
	public String returnErrorMsg(String key, String[] replaceElement) {
		// 第一引数を用いて、エラーメッセージを取得するメソッドからエラーメッセージを取得し、
		// 第二引数を用いて、取得したエラーメッセージの一部を置換し、エラーメッセージを完成させる。
		String errorMsg = LoadPropertyfile.getProperty(key);
		for (int i = 0; i < replaceElement.length; i++) {
			errorMsg = errorMsg.replace("{" + i + "}", replaceElement[i]);
		}
		// 完成したエラーメッセージを返す。
		return errorMsg;
	}
	
	public String returnErrorMsg(String key, String replaceElement) {
		// 第一引数を用いて、エラーメッセージを取得するメソッドからエラーメッセージを取得し、
		// 第二引数を用いて、取得したエラーメッセージの一部を置換し、エラーメッセージを完成させる。
		String errorMsg = LoadPropertyfile.getProperty(key);
		for (int i = 0; i < replaceElement.length(); i++) {
			errorMsg = errorMsg.replace("{" + i + "}", replaceElement);
		}
		// 完成したエラーメッセージを返す。
		return errorMsg;
	}

	/**
	 * 引数からエラーメッセージを取得し、エラーメッセージを返す。
	 *
	 * @param key メッセージID
	 * @return
	 */
	public String returnErrorMsg(String key) {
		// 引数を用いて、エラーメッセージを取得するメソッドからエラーメッセージを取得し、エラーメッセージを返す。
		String errorMsg = LoadPropertyfile.getProperty(key);
		return errorMsg;
	}

	/**
	 * このメソッドは第一引数がnullでない場合エラーリストに格納し、戻り値とする。
	 *
	 * @param errorMsg チェックしたいパラメータ
	 * @param errorMsgList 格納するエラーメッセージリスト
	 * @return エラーメッセージが追加されたエラーメッセージリスト
	 */
	public List<String> errorMsgNullCheck(String errorMsg, List<String> errorMsgList) {
		if (errorMsg != null) {
			// 第一引数がnullでない場合、第一引数を第二引数のリストに追加する。
			errorMsgList.add(errorMsg);
			// 追加されたリストを返す。
			return errorMsgList;
		}
		// 第一引数がnullの場合は、第二引数のエラーリストを戻り値とする。
		return errorMsgList;
	}

	/**
	 * このメソッドは第一引数の値がnullでない場合エラーリストに格納し、戻り値とする。
	 * @param addErrorMsgMap チェックしたいパラメータ
	 * @param errorMsgMap 格納するエラーメッセージマップ
	 * @return エラーメッセージが追加されたMap
	 */
	public Map<String, String> errorMsgMapNullCheck(Map<String, String> addErrorMsgMap,
			Map<String, String> errorMsgMap) {
		// 第一引数がnullの場合は第二引数を戻り値とする。
		if (addErrorMsgMap == null) {
			return errorMsgMap;
		}
		for (Entry<String, String> entry : addErrorMsgMap.entrySet()) {
			String value = entry.getValue();
			String key = entry.getKey();
			// 第一引数の値がnullでない場合、第二引数に追加する。
			if (value != null) {
				errorMsgMap.put(key, value);
			}
		}
		// 第一引数の値がnullの場合は第二引数を戻り値とする。
		return errorMsgMap;
	}
}
