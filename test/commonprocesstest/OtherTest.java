package commonprocesstest;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import jp.co.vaile.nerva.commonprocess.CheckDuplicateDAO;
import jp.co.vaile.nerva.commonprocess.CheckMasterResult;
import jp.co.vaile.nerva.commonprocess.CheckViewingAuthority;
import jp.co.vaile.nerva.commonprocess.ConvertPassword;
import jp.co.vaile.nerva.commonprocess.OrderSourceDAO;
import jp.co.vaile.nerva.commonprocess.OrderSourceDTO;

public class OtherTest {
	OrderSourceDAO orderSourceDAO = new OrderSourceDAO();
	CheckViewingAuthority checkViewingAuthority = new CheckViewingAuthority(); 
	CheckDuplicateDAO checkDuplicateDAO=new CheckDuplicateDAO();
	CheckMasterResult checkMasterResult=new CheckMasterResult();
	ConvertPassword convertPassword = new ConvertPassword();
	@Test
	public void otherTest() throws ClassNotFoundException, SQLException {
		List<OrderSourceDTO> orderSourceList = orderSourceDAO.selectAllOrderSource();
		String companyPrivilege="1";
		String companyChildPrivilege="0";
		for(int i=0; i<orderSourceList.size(); i++) {
			orderSourceList.get(i).getOrderSourceId();
			orderSourceList.get(i).getOrderSourceName();
		}
		String loginUserCompanyId1 = "CP00000001";
		String checkParam1 = "CP00000001";
		String loginUserCompanyId2 = "CP00000002";
		String checkParam2 = "CP00000002";
	
		checkViewingAuthority.checkViewingAuthority(loginUserCompanyId1,companyPrivilege,checkParam1);
		checkViewingAuthority.checkViewingAuthority(loginUserCompanyId2, companyChildPrivilege,checkParam2);
		checkViewingAuthority.checkViewingAuthority(loginUserCompanyId1, companyChildPrivilege,checkParam2);
	
	}
	@Test
	public void checkDuplicateDaoTest() throws ClassNotFoundException, SQLException {
		String inputData="CP00000001";
		String tableName="m_belong_company";
		String columnName="company_id";
		checkDuplicateDAO.checkDuplicate(inputData, tableName,  columnName);

	}
	
	@Test
	public void checkMasterResultTest() throws ClassNotFoundException, SQLException {
		String checkMasterNane="所属会社";
		checkMasterResult.checkMasterResult(checkMasterNane);
	}
	
	@Test
	public void hashPassword() throws ClassNotFoundException, SQLException {
		String password="pass";
		convertPassword.hashPassword(password);

	}
	
}
