package registprojecttest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AllTestRegistProject {

	@Test
	@DisplayName("registProjctパッケージテストの一括実行")
	public void registProjctAllTest() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		ShowRegistProjectActionTest showRegistProjectActionTest = new ShowRegistProjectActionTest();
		showRegistProjectActionTest.showNomalPattern();
		RegistProjectActionTest registProjectActionTest = new RegistProjectActionTest();
		registProjectActionTest.registProjectActionTest();
		RegistProjectBackPageActionTest registProjectBackPageActionTest = new RegistProjectBackPageActionTest();
		registProjectBackPageActionTest.showNomalPattern();
	}

}
