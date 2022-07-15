package commonprocesstest;

import java.sql.SQLException;

import org.junit.Test;

import jp.co.vaile.nerva.commonprocess.skillMaster.GetSkillTypeDAO;
import jp.co.vaile.nerva.commonprocess.skillMaster.GetYearsDateOfAcquisitFlgDAO;

public class CommonSkillTypeTest {
	GetYearsDateOfAcquisitFlgDAO getYearsDateOfAcquisitFlgDAO=new GetYearsDateOfAcquisitFlgDAO();
	GetSkillTypeDAO getSkillTypeDAO=new GetSkillTypeDAO();

	@Test	 
	public void commonSkillTypeTest() throws ClassNotFoundException, SQLException {
		String skillTypeId="S000000001";
		getYearsDateOfAcquisitFlgDAO.getYearsDateOfAcquisit(skillTypeId);
		getSkillTypeDAO.getSkillType();

	}
}
