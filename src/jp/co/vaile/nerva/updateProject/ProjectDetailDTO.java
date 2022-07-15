package jp.co.vaile.nerva.updateProject;

public class ProjectDetailDTO {

	private String projectDetailId; //案件詳細情報ID
	private String ProjectInfoId; //案件情報ID
	private String teamId; //チームID
	private String assignStartDate; //配属開始日
	private String assignCompleteDate; //配属完了日

	public String getProjectDetailId() {
		return projectDetailId;
	}
	public void setProjectDetailId(String projectDetailId) {
		this.projectDetailId = projectDetailId;
	}
	public String getProjectInfoId() {
		return ProjectInfoId;
	}
	public void setProjectInfoId(String projectInfoId) {
		ProjectInfoId = projectInfoId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getAssignStartDate() {
		return assignStartDate;
	}
	public void setAssignStartDate(String assignStartDate) {
		this.assignStartDate = assignStartDate;
	}
	public String getAssignCompleteDate() {
		return assignCompleteDate;
	}
	public void setAssignCompleteDate(String assignCompleteDate) {
		this.assignCompleteDate = assignCompleteDate;
	}

}
