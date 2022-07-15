package teamdetailtest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class AllTestTeamDetail {
	ShowTeamDetailInfoActionTest showTeamDetailInfoActionTest = new ShowTeamDetailInfoActionTest();
	TeamDetailBackPageActionTest teamDetailBackPageActionTest = new TeamDetailBackPageActionTest();
	TeamDetailSessionActionTest teamDetailSessionActionTest = new TeamDetailSessionActionTest();

	@Test
	@DisplayName("teamDetailパッケージテストの一括実行")
	void testAllTestDetail() throws ServletException, IOException {
		showTeamDetailInfoActionTest.testInReferenceAccess();
		showTeamDetailInfoActionTest.testOutReferenceAccess();
		showTeamDetailInfoActionTest.testOutReferenceAccessDoGet();
		showTeamDetailInfoActionTest.sessionDestruction();
		teamDetailBackPageActionTest.testTeamIdPattern();
		teamDetailBackPageActionTest.testProjectIdPattern();
		teamDetailSessionActionTest.testNormalPattern();
	}
}
