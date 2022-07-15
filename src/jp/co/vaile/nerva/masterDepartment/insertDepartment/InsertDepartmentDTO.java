package jp.co.vaile.nerva.masterDepartment.insertDepartment;

public class InsertDepartmentDTO {

	private String registUserId;
	private String departmentId;
	private String departmentName;

	InsertDepartmentDTO(String registUserId, String departmentId, String departmentName) {
		this.registUserId = registUserId;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public String getRegistUserId() {
		return registUserId;
	}

	public void setRegistUserId(String registUserId) {
		this.registUserId = registUserId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
