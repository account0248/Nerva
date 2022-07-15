package jp.co.vaile.nerva.masterDepartment.updateDepartment;

public class UpdateDepartmentDTO {
	// 所属組織マスタメンテナンス画面の更新時に入力される値を格納
	private String updateUserId;
	private String[] departmentId;
	private String[] departmentName;

	// コンストラクタ
	UpdateDepartmentDTO(String updateUserId, String[] departmentId, String[] departmentName) {
		this.updateUserId = updateUserId;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String[] getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String[] departmentId) {
		this.departmentId = departmentId;
	}

	public String[] getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String[] departmentName) {
		this.departmentName = departmentName;
	}
}
