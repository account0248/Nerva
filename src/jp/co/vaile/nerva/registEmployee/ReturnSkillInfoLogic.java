package jp.co.vaile.nerva.registEmployee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.vaile.nerva.commonprocess.skillMaster.GetSkillTypeDAO;
import jp.co.vaile.nerva.commonprocess.skillMaster.SkillTypeDTO;

public class ReturnSkillInfoLogic {

	List<SkillTypeDTO> returnSkillTypeList() throws ClassNotFoundException, SQLException {
		List<SkillTypeDTO> skillTypeList = new ArrayList<SkillTypeDTO>();
		GetSkillTypeDAO getSkillTypeDAO = new GetSkillTypeDAO();
		//1.	スキル種別情報取得処理の呼び出しを行う。
		skillTypeList = getSkillTypeDAO.getSkillType();

		// 2.	スキル種別マスタの情報を戻り値とする。
		return skillTypeList;
	}
}
