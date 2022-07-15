package jp.co.vaile.nerva.masterRole.searchRole;

public class SearchRoleDTO {
	private String roleId;
	private String roleType;
	
	
	public SearchRoleDTO() {
		super();
	}

	public SearchRoleDTO(String roleId, String roleType) {
		super();
		this.roleId = roleId;
		this.roleType = roleType;
	}

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleType;
	}
	public void setRoleName(String roleType) {
		this.roleType = roleType;
	}

	

}
