package teansferApplicationtest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;

public class AllTransferApplicationTest {
	
	@Test
	public void AllTest() throws ClassNotFoundException, ServletException, IOException {
		TransferApplicationActionTest TransferApplicationActionTest = new TransferApplicationActionTest();
		TransferApplicationActionTest.transferApplicationActionApprove();
		TransferApplicationActionTest.transferApplicationAction();
		TransferApplicationActionTest.transferApplicationAction2();
		TransferApplicationActionTest.transferApplicationAction3();
		TransferApplicationActionTest.transferApplicationAction4();
	
	ShowTransferApplicationActionTest ShowTransferApplicationActionTest = new ShowTransferApplicationActionTest();
	ShowTransferApplicationActionTest.transferApplicationActionApprove();
	ShowTransferApplicationActionTest.transferApplicationActionApprove2();
	}
}
