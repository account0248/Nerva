package jp.co.vaile.nerva.registproject;

import java.sql.Date;

public class RegistProjectInfoDTO {

	private String projectId;
	private String projectName;
	private String responsibleId;
	private String contractorId;
	private String orderSource;
	private String industryId;
	private Date projectStartDate;
	private Date projectCompleteDate;
	private String registUser;

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
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public Date getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public Date getProjectCompleteDate() {
		return projectCompleteDate;
	}
	public void setProjectCompleteDate(Date projectCompleteDate) {
		this.projectCompleteDate = projectCompleteDate;
	}
	public String getRegistUser() {
		return registUser;
	}
	public void setRegistUser(String registUser) {
		this.registUser = registUser;
	}

}
