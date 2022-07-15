package jp.co.vaile.nerva.masterDepartment.updateDepartment;

import java.sql.SQLException;

public class UpdateDepartmentMstLogic {

	/**
	 * 所属組織更新処理を呼び出す。
	 * 
	 * @param updateDepartmentDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 **/
	public void updateDepartmentMstLogic(UpdateDepartmentDTO updateDepartmentDTO)
			throws ClassNotFoundException, SQLException {

		UpdateDepartmentDAO updateDepartmentDAO = new UpdateDepartmentDAO();
		updateDepartmentDAO.updateDepartment(updateDepartmentDTO);

	}

}
