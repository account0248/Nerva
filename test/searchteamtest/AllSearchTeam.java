package searchteamtest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;

public class AllSearchTeam {

	@Test
	public void searchTeamAllTest() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		SearchTeamTest searchTeamTest =new SearchTeamTest();
		DeleteTeamTest deleteTeamTest =new DeleteTeamTest();
		SearchTeamSessionTest searchTeamSessionTest =new SearchTeamSessionTest();
		ShowSearchTeamTest showSearchTeamTest =new ShowSearchTeamTest();
		
		searchTeamTest.testSearchTeam();
		deleteTeamTest.testDeleteTeam();
		searchTeamSessionTest.testSearchTeamSession();
		showSearchTeamTest.testShowSearchTeam();
	}

}
