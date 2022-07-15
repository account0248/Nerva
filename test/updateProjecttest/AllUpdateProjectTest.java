package updateProjecttest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;

public class AllUpdateProjectTest {
	
	@Test
	public void allUpdateProjectTest() throws ServletException, IOException {
		
		ShowUpdateProjectTest showUpdateProjectTest = new ShowUpdateProjectTest();
		CheckUpdateProjectTest checkUpdateProjectTest = new CheckUpdateProjectTest();
		UpdateProjectTest updateProjectTest = new UpdateProjectTest();
		RetrunAddableTeamListTest retrunAddableTeamListTest = new RetrunAddableTeamListTest();
		DeleteProjectTest deleteProjectTest = new DeleteProjectTest();
		
		showUpdateProjectTest.allcheckUpdateProjectTest();
		checkUpdateProjectTest.allcheckUpdateProjectTest();
		updateProjectTest.allupdateProjectTest();
		retrunAddableTeamListTest.allRetrunAddableTeamListTest();
		deleteProjectTest.alldeleteProjectTest();
	}

}
