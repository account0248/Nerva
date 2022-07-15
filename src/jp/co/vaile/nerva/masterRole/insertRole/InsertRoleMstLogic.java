package jp.co.vaile.nerva.masterRole.insertRole;

import java.sql.SQLException;
import java.text.ParseException;

public class InsertRoleMstLogic {
	
	public void insertRoleMstLogic(InsertRoleDTO insertRoleDTO) throws ClassNotFoundException, SQLException, ParseException {
		
		//担当登録処理の呼び出し
		
		InsertRoleDAO insertRoleDAO = new InsertRoleDAO();
		insertRoleDAO.insertRole(insertRoleDTO);
	}
	
}
