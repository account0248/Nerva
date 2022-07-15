package searchprojecttest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;

import jp.co.vaile.nerva.searchProject.SearchProjectDAO;
import jp.co.vaile.nerva.searchProject.SearchProjectToStringDAO;

public class AllTestSearchProject {

	@Test
	public void allTestSearchProject() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

		ShowProjectTest showProjectTest = new ShowProjectTest();
		showProjectTest.showProjectTest();
		SearchProjectTest searchProjectTest = new SearchProjectTest();
		searchProjectTest.searchProjectTest();
		SearchProjectToStringDAO searchProjectToStringDAO  = new SearchProjectToStringDAO ();
        SearchProjectDAO searchProjectDAO = new SearchProjectDAO();

		
	}


}
