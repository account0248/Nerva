package masterUserTest.searchUserTest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AllTestSearchUser {
	@Test
	@DisplayName("searchUserパッケージテストの一括実行")
    public void allTestUser() throws ClassNotFoundException, ServletException, IOException {
		ShowUserMstTest showUserMstTest = new ShowUserMstTest();
		SearchUserMstTest searchUserMstTest = new SearchUserMstTest();
		
		//ユーザーマスタメンテナンス画面に遷移する
		showUserMstTest.UserMstParentcompanyPattern();
		showUserMstTest.UserMstSubsidiaryPattern();
		// 検索機能全テストパターン
		searchUserMstTest.idfiltereSearchUser();
		searchUserMstTest.namefiltereSearchUser();
		searchUserMstTest.companyfiltereSearchUser();
		searchUserMstTest.companyfiltereSearchUser2();
		searchUserMstTest.companyfiltereSearchUser3();
		searchUserMstTest.postfiltereSearchUser();
		searchUserMstTest.postfiltereSearchUser2();
		searchUserMstTest.postfiltereSearchUser3();
		searchUserMstTest.postfiltereSearchUser4();
		searchUserMstTest.postfiltereSearchUser5();
		searchUserMstTest.adminFLgtfiltereSearchUser();
		searchUserMstTest.adminFLgtfiltereSearchUser2();
		searchUserMstTest.partialMatchSearchUser();
		searchUserMstTest.filtereSearchUser();
		searchUserMstTest.parentcompanyAllSearchUser();
		searchUserMstTest.subsidiaryAllSearchUser();
		searchUserMstTest.searchUserResultCheck();
		searchUserMstTest.userIdLengthCheck10();
		searchUserMstTest.userIdLengthCheck11();
		searchUserMstTest.userIdMatchCheck();
		searchUserMstTest.userNameLengthCheck20();
		searchUserMstTest.userNameLengthCheck21();
	}
}