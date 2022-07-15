package createteamtest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class AllCreateTeamTest {
	
	@Test
	@DisplayName("createteamtestパッケージの一括実行")
	public void AllTest() throws ClassNotFoundException, ServletException, IOException {
	CreateTeamActionTest CreateTeamActiontst = new CreateTeamActionTest();
	ShowCreateTeamActionTest ShowCreateTeamActiontst = new ShowCreateTeamActionTest();
	ShowCreateTeamActiontst.testNormalPattern();
	ShowCreateTeamActiontst.testNormalPattern2();
	ShowCreateTeamActiontst.testErrorCheckSession();
	CreateTeamActiontst.AllInputPattern();
	CreateTeamActiontst.ErrorPattern();
	CreateTeamActiontst.EmptyPattern();
	CreateTeamActiontst.AllInprn();
	CreateTeamActiontst.AllInprn2();
	CreateTeamActiontst.AllInprn3();
	CreateTeamActiontst.Pattern();
	}
}
