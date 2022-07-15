package jp.co.vaile.nerva.masterRole.insertRole;

public class InsertRoleDTO {

	private String registUserId;
	private String roleId;
	private String roleName;
	
	InsertRoleDTO(String registUserId, String roleId, String roleName){
		this.registUserId = registUserId;
		this.roleId = roleId;
		this.roleName = roleName;
	}
		
	
	public String getRegistUserId() {
		return registUserId;
	}


	public void setRegistUserId(String registUserId) {
		this.registUserId = registUserId;
	}


	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
