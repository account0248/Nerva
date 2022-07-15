package updateemployeetest;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AllTestUpdateEmployee {
	
	@Test
	@DisplayName("updateEmployeeパッケージテストの一括実行")
	public void updateEmployeeAllTest() throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
		ShowUpdateEmpActionTest showUpdateEmpActionTest = new ShowUpdateEmpActionTest();
		UpdateEmpBackPageActionTest updateEmpBackPageActionTest = new UpdateEmpBackPageActionTest();
		ReturnMasterTableInfoActionTest returnMasterTableInfoActionTest = new ReturnMasterTableInfoActionTest();
		ReturnTeamInfoActionTest returnTeamInfoActionTest = new ReturnTeamInfoActionTest();
		UpdateEmpCheckActionTest updateEmpCheckActionTest = new UpdateEmpCheckActionTest();
		UpdateEmpActionTest updateEmpActionTest = new UpdateEmpActionTest();
		DeleteEmpActionTest deleteEmpActionTest = new DeleteEmpActionTest();
		showUpdateEmpActionTest.nomalPattern();
		updateEmpBackPageActionTest.NomalPattern();
		returnMasterTableInfoActionTest.NomalPattern();
		returnTeamInfoActionTest.NomalPattern();
		updateEmpCheckActionTest.updateEmpCheckActionTest();
		deleteEmpActionTest.NomalPattern();
		deleteEmpActionTest.ErrorPattern();
		updateEmpActionTest.updateEmpActionTest();
	}
}
