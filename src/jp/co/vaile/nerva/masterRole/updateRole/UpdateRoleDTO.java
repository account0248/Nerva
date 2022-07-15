package jp.co.vaile.nerva.masterRole.updateRole;

public class UpdateRoleDTO {

	private String updateUserId;
	private String[] roleId;
	private String[] roleName;
	
	UpdateRoleDTO(String updateUserId, String[] roleId, String[] roleName) {
		this.updateUserId = updateUserId;
		this.roleId = roleId;
		this.setRoleName(roleName);
	}
	
	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String[] getRoleId() {
		return roleId;
	}

	public void setRoleId(String[] roleId) {
		this.roleId = roleId;
	}
	public String[] getRoleName() {
		return roleName;
	}
	public void setRoleName(String[] roleName) {
		this.roleName = roleName;
	}

	
	
	
}
