package jp.co.vaile.nerva.teamDetail;

public class TeamEntryEmpDTO {



	private String empId;
	private String empName;
	private String role;
	private String belongCompany;
	private String contractType;
	private String assignStartDate;
	private Integer monthlyUnitPrice;

	/**
	 * @return empId
	 */
	public String getEmpId() {
		return empId;
	}
	/**
	 * @param empId セットする empId
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	/**
	 * @return empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName セットする empName
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role セットする role
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return belongCompany
	 */
	public String getBelongCompany() {
		return belongCompany;
	}
	/**
	 * @param belongCompany セットする belongCompany
	 */
	public void setBelongCompany(String belongCompany) {
		this.belongCompany = belongCompany;
	}
	/**
	 * @return contractType
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType セットする contractType
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return assignStartDate
	 */
	public String getAssignStartDate() {
		return assignStartDate;
	}
	/**
	 * @param assignStartDate セットする assignStartDate
	 */
	public void setAssignStartDate(String assignStartDate) {
		this.assignStartDate = assignStartDate;
	}
	/**
	 * @return monthlyUnitPrice
	 */
	public Integer getMonthlyUnitPrice() {
		return monthlyUnitPrice;
	}
	/**
	 * @param monthlyUnitPrice セットする monthlyUnitPrice
	 */
	public void setMonthlyUnitPrice(Integer monthlyUnitPrice) {
		this.monthlyUnitPrice = monthlyUnitPrice;
	}


}