package jp.co.vaile.nerva.searchProject;

import java.sql.Date;


public class SearchProjectDTO {

	private String projectId;

	private String projectName;

	private String responsibleName;

	private String contratorName;

	private String clientName;

	private String inductryName;

	private String totalTeamsProject;

	private String totalEmpProject;

	private Date projectStartDate;

	private Date projectCompleteDate;


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

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getContratorName() {
		return contratorName;
	}

	public void setContratorName(String contratorName) {
		this.contratorName = contratorName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getInductryName() {
		return inductryName;
	}

	public void setInductryName(String inductryName) {
		this.inductryName = inductryName;
	}

	public String getTotalTeamsProject() {
		return totalTeamsProject;
	}

	public void setTotalTeamsProject(String totalTeamsProject) {
		this.totalTeamsProject = totalTeamsProject;
	}

	public String getTotalEmpProject() {
		return totalEmpProject;
	}

	public void setTotalEmpProject(String totalEmpProject) {
		this.totalEmpProject = totalEmpProject;
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



}
