package jp.co.vaile.nerva.masterIndustry.insertIndustry;

import java.sql.SQLException;
import java.text.ParseException;

public class InsertIndustryMstLogic {
	
	/**
	 * 業種登録処理を呼び出す。
	 * 
	 * @param insertIndustryDTO 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	public void insertIndustryMstLogic(InsertIndustryDTO insertIndustryDTO) throws ClassNotFoundException, SQLException, ParseException {
		
		//業種登録処理の呼び出し
		
		InsertIndustryDAO insertIndustryDAO = new InsertIndustryDAO();
		insertIndustryDAO.insertIndustry(insertIndustryDTO);
	}
	
}
