package jp.co.vaile.nerva.registEmployee;

public class RegistEmpPageDTO {
	private String employeeId;
	private String employeeName;
	private String sex;
	private String birthDate;
	private String companyId;
	private String office;
	private String departmentId;
	private String postId;
	private String postalCode;
	private String address;
	private String phoneNumber;
	private String mail;


	public RegistEmpPageDTO(String employeeId, String employeeName, String sex, String birthDate,
			String companyId,
			String office, String departmentId, String postId, String postalCode, String address, String phoneNumber,
			String mail) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.sex = sex;
		this.birthDate = birthDate;
		this.companyId = companyId;
		this.office = office;
		this.departmentId = departmentId;
		this.postId = postId;
		this.postalCode = postalCode;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
		
		
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public String getSex() {
		return sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getOffice() {
		return office;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getPostId() {
		return postId;
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

	
	
}