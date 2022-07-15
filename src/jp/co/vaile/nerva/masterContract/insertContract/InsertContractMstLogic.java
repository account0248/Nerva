package jp.co.vaile.nerva.masterContract.insertContract;

import java.sql.SQLException;
import java.text.ParseException;

public class InsertContractMstLogic {
	
	public void insertContractMstLogic(InsertContractDTO insertContractDTO) throws ClassNotFoundException, SQLException, ParseException {
		
		//契約形態登録処理の呼び出し
		
		InsertContractDAO insertContractDAO = new InsertContractDAO();
		insertContractDAO.insertContract(insertContractDTO);
	}
	
}
