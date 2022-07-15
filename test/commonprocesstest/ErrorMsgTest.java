package commonprocesstest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import jp.co.vaile.nerva.commonprocess.errormessage.CommonConstants;
import jp.co.vaile.nerva.commonprocess.errormessage.ErrorMsg;


public class ErrorMsgTest {
	CommonConstants commonConstants = new CommonConstants();
	ErrorMsg errorMsg = new ErrorMsg();

	@Test
	public void returnErrorMsgTest() {
		errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE);
		String[] repaceMsg = {CommonConstants.EMPLOYEE_ID};
		String errorMsgReplace = errorMsg.returnErrorMsg(CommonConstants.REQUIRED_ERROR_MESSAGE, repaceMsg);
		String errorMsgNull = null;
		Map<String, String> addErrorMsgMap = new HashMap<String, String>();
		addErrorMsgMap.put("key1", "エラー");
		Map<String, String> addErrorMsgMapNull;
		addErrorMsgMapNull=null;
		Map<String, String> errorMsgMap= new HashMap<String, String>();
		errorMsgMap.put("key1", "エラー");
		List<String> errorMsgList = new ArrayList<>();
		errorMsg.errorMsgNullCheck(errorMsgReplace, errorMsgList);
		errorMsg.errorMsgNullCheck(errorMsgNull, errorMsgList);
		//ver1.0で実装されていないを追加。
		errorMsg.errorMsgMapNullCheck(addErrorMsgMap,errorMsgMap);
		errorMsg.errorMsgMapNullCheck(addErrorMsgMapNull,errorMsgMap);
	}
}
