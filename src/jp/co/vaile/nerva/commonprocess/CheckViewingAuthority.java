package jp.co.vaile.nerva.commonprocess;

import static jp.co.vaile.nerva.commonprocess.MasterContents.*;

public class CheckViewingAuthority {
	private boolean statusOK;

	/**
	 * ログインユーザーの所属会社IDによってチェックする所属会社IDの可視性をチェックする。
	 *
	 * @param loginUserCompanyId
	 * @param checkParam
	 * @return
	 */
	public boolean checkViewingAuthority(String loginUserCompanyId,String loginUserCompanyPrivilege, String checkParam) {
		String parentCompanyValue=String.valueOf(PARENT_COMPANY_VALUE);
		// ログインユーザーの所属会社IDとチェックしたい所属会社IDの妥当性をチェックする。
		statusOK = false;
		if (loginUserCompanyPrivilege.equals(parentCompanyValue)) {
			statusOK = true;
			return statusOK;
		}else if(loginUserCompanyId.equals(checkParam)) {
			statusOK = true;
			return statusOK;
		}
		// trueは第二引数が第一引数の可視範囲である事を示す。
		// falseは第二引数が第一引数の可視範囲でないことを示す。
		return statusOK;
	}
}
