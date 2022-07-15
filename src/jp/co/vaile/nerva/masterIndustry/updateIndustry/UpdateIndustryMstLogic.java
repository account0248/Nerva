package jp.co.vaile.nerva.masterIndustry.updateIndustry;

import java.sql.SQLException;

public class UpdateIndustryMstLogic {

	/**
	 * 所属組織更新処理を呼び出す。
	 * 
	 * @param updateIndustryDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public void updateIndustryMstLogic(UpdateIndustryDTO updateIndustryDTO) throws ClassNotFoundException, SQLException {
		
		UpdateIndustryDAO updateIndustryDAO = new UpdateIndustryDAO();
		updateIndustryDAO.updateIndustry(updateIndustryDTO);
		
	}

}
