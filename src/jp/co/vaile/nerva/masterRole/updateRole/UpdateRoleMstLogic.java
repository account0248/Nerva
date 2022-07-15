package jp.co.vaile.nerva.masterRole.updateRole;

import java.sql.SQLException;

public class UpdateRoleMstLogic {

	public void updateRoleMstLogic(UpdateRoleDTO updateRoleDTO) throws ClassNotFoundException, SQLException {
		UpdateRoleDAO updateRoleDAO = new UpdateRoleDAO();
		updateRoleDAO.updateRole(updateRoleDTO);
		
	}

}
