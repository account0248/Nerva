package jp.co.vaile.nerva.detailProject;

public class ProjectDetailPageDTO {

	private int projectInfoId; //案件情報ID
	private String projectId; //案件ID
	private String projectName; //案件名
	private String responsibleId; //責任者ID
	private String responsibleName; //責任者名
	private String contractorId; //受注元ID
	private String contractorName; //受注元名
	private int clientId; //発注元ID
	private String clientName; //発注元名
	private String industryId; //業種ID
	private String industryName; //業種名
	private String projectStartDate; //案件開始日
	private String projectCompleteDate; //案件完了日

	public int getProjectInfoId() {
		return projectInfoId;
	}
	public void setProjectInfoId(int projectInfoId) {
		this.projectInfoId = projectInfoId;
	}
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
	public String getResponsibleName() {
		return responsibleName;
	}
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}
	public String getContractorId() {
		return contractorId;
	}
	public void setContractorId(String contractorId) {
		this.contractorId = contractorId;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
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

}
