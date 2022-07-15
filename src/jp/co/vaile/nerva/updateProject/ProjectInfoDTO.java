package jp.co.vaile.nerva.updateProject;

public class ProjectInfoDTO {

	private int projectInfoId; //案件情報ID
	private String projectId; //案件ID
	private String projectName; //案件名
	private String responsibleId; //責任者ID
	private String contractorId; //受注元ID
	private String clientId; //発注元ID
	private String industryId; //業種ID
	private String projectStartDate; //案件開始日
	private String projectCompleteDate; //案件完了日
	private String registUser; //登録者ID

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getResponsibleId() {
		return responsibleId;
	}

	public void setResponsibleId(String responsibleId) {
		this.responsibleId = responsibleId;
	}

	public String getContractorId() {
		return contractorId;
	}

	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getIndustryId() {
		return industryId;
	}

	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}

	public String getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public String getProjectCompleteDate() {
		return projectCompleteDate;
	}

	public void setProjectCompleteDate(String projectCompleteDate) {
		this.projectCompleteDate = projectCompleteDate;
	}

	public String getRegistUser() {
		return registUser;
	}

	public void setRegistUser(String registUser) {
		this.registUser = registUser;
	}

	public int getProjectInfoId() {
		return projectInfoId;
	}

	public void setProjectInfoId(int projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

}
