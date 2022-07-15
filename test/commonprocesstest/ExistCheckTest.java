package commonprocesstest;

import java.sql.SQLException;

import org.junit.Test;

import jp.co.vaile.nerva.commonprocess.existchecksub.BelongCompanyInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.BelongDepartmentInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.ContractTypeInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.IndustryInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.PostInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.RoleInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.SexInfo;
import jp.co.vaile.nerva.commonprocess.existchecksub.SkillTypeInfo;

public class ExistCheckTest {
	@Test
	public void existCheckTest() throws ClassNotFoundException, SQLException {
		BelongCompanyInfo belongCompanyInfo = new BelongCompanyInfo();
		BelongDepartmentInfo belongDepartmentInfo = new BelongDepartmentInfo();
		ContractTypeInfo contractTypeInfo = new ContractTypeInfo();
		IndustryInfo industryInfo = new IndustryInfo();
		PostInfo postInfo = new PostInfo();
		RoleInfo roleInfo = new RoleInfo();
		SexInfo sexInfo = new SexInfo();
		SkillTypeInfo skillTypeInfo = new SkillTypeInfo();

		String companyPrivilege="1";
		String companyChildPrivilege="0";

		belongCompanyInfo.isThisExistDB("CP00000001");
		belongCompanyInfo.isThisExistDB("CP00000002");
		belongCompanyInfo.isThisExistDB("CP00000003");
		belongCompanyInfo.isThisExistDB("CP00000000000000000");
		belongCompanyInfo.fetchBelongCompanyList("CP00000001",companyPrivilege);
		belongCompanyInfo.fetchBelongCompanyList("CP00000002", companyChildPrivilege);
		belongCompanyInfo.fetchMasterTableList();

		belongDepartmentInfo.isThisExistDB("D000000001");
		belongDepartmentInfo.isThisExistDB("D000000002");
		belongDepartmentInfo.isThisExistDB("D000000003");
		belongDepartmentInfo.isThisExistDB("D000000004");
		belongDepartmentInfo.isThisExistDB("D000000005");
		belongDepartmentInfo.isThisExistDB("D000000006");
		belongDepartmentInfo.isThisExistDB("D000000007");
		belongDepartmentInfo.isThisExistDB("D0000000000000000001");
		belongCompanyInfo.fetchMasterTableList();

		contractTypeInfo.isThisExistDB("C000000001");
		contractTypeInfo.isThisExistDB("C000000002");
		contractTypeInfo.isThisExistDB("C000000003");
		contractTypeInfo.isThisExistDB("C000000004");
		contractTypeInfo.isThisExistDB("C000000000000000005");
		contractTypeInfo.fetchMasterTableList();

		industryInfo.isThisExistDB("I000000001");
		industryInfo.isThisExistDB("I000000002");
		industryInfo.isThisExistDB("I000000003");
		industryInfo.isThisExistDB("I000000004");
		industryInfo.isThisExistDB("I000000005");
		industryInfo.isThisExistDB("I00000000000005");
		industryInfo.fetchMasterTableList();

		postInfo.isThisExistDB("P000000001");
		postInfo.isThisExistDB("P000000002");
		postInfo.isThisExistDB("P000000003");
		postInfo.isThisExistDB("P000000004");
		postInfo.isThisExistDB("P000000005");
		postInfo.isThisExistDB("P0000000000000000001");
		postInfo.fetchMasterTableList();

		roleInfo.isThisExistDB("T000000001");
		roleInfo.isThisExistDB("T000000002");
		roleInfo.isThisExistDB("T000000003");
		roleInfo.isThisExistDB("T000000004");
		roleInfo.isThisExistDB("T0000000000000000000000005");
		roleInfo.fetchMasterTableList();

		sexInfo.isThisExistDB("男");
		sexInfo.isThisExistDB("女");
		sexInfo.isThisExistInfo("LGBTQ");
		sexInfo.fetchMasterTableList();

		skillTypeInfo.isThisExistDB("S000000001");
		skillTypeInfo.isThisExistDB("S000000002");
		skillTypeInfo.isThisExistDB("S000000003");
		skillTypeInfo.isThisExistDB("S000000004");
		skillTypeInfo.isThisExistDB("S000000000000000001");
		skillTypeInfo.fetchMasterTableList();

	}
}
