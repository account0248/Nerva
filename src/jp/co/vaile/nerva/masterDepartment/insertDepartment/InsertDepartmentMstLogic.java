package jp.co.vaile.nerva.masterDepartment.insertDepartment;

import java.sql.SQLException;
import java.text.ParseException;

public class InsertDepartmentMstLogic {

	/**
	 * 所属組織登録処理を呼び出す。
	 * 
	 * @param insertDepartmentDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ParseException
	 **/
	public void insertDepartmentMstLogic(InsertDepartmentDTO insertDepartmentDTO)
			throws ClassNotFoundException, SQLException, ParseException {

		// 所属組織登録処理の呼び出し
		InsertDepartmentDAO insertDepartmentDAO = new InsertDepartmentDAO();
		insertDepartmentDAO.insertDepartment(insertDepartmentDTO);
	}

}
