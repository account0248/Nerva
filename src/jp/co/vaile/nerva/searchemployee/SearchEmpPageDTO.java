package jp.co.vaile.nerva.searchemployee;

public class SearchEmpPageDTO {
	private String employeeId;
	private String employeeName;
	private String projectName;
	private String teamName;
	private String companyName;
	private String teamManager;
	private String skillFiltering;

	public SearchEmpPageDTO(String employeeId, String employeeName, String projectName, String teamName,
			String companyName, String teamManager, String skillFiltering) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.projectName = projectName;
		this.teamName = teamName;
		this.companyName = companyName;
		this.teamManager = teamManager;
		this.skillFiltering = skillFiltering;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public String getProjectName() {
		return projectName;
	}
	public String getTeamName() {
		return teamName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getTeamManager() {
		return teamManager;
	}
	public String getSkillFiltering() {
		return skillFiltering;
	}


}