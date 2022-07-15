package jp.co.vaile.nerva.updateEmployee;

public class UpdateEmpInfoDTO extends UpdateEmpRelationDTO {
	private String employeeName;
	private String office;
	private String postalCode;
	private String address;
	private String phoneNumber;
	private String mail;
	private String postId;
	private String belongDepartmentId;

	public UpdateEmpInfoDTO(String employeeName, String office, String departmentId, String postId,
			String postalCode, String address, String phoneNumber, String mail) {
		this.employeeName = employeeName;
		this.office = office;
		this.postalCode = postalCode;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		this.postId = postId;
		this.belongDepartmentId = departmentId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getOffice() {
		return office;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public String getPostId() {
		return postId;
	}

	public String getBelongDepartmentId() {
		return belongDepartmentId;
	}
}
