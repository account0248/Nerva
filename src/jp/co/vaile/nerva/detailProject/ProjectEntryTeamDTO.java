package jp.co.vaile.nerva.detailProject;

public class ProjectEntryTeamDTO {

	private int projectDetailId; //案件詳細情報ID
	private String teamId; //チームID
	private String teamName; //チーム名
	private String belongCompanyId; //所属会社ID
 	private String belongCompanyName; //所属会社名
	private String teamManagerId; //所属部長ID
	private String teamManagerName; //所属部長名
	private String teamLeaderId; //チームリーダーID
	private String teamLeaderName; //チームリーダー名
	private String assignStartDate; //配属開始日
	private String assignCompleteDate; //配属完了日

	public int getProjectDetailId() {
		return projectDetailId;
	}
	public void setProjectDetailId(int projectDetailId) {
		this.projectDetailId = projectDetailId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getBelongCompanyId() {
		return belongCompanyId;
	}
	public void setBelongCompanyId(String belongCompanyId) {
		this.belongCompanyId = belongCompanyId;
	}
	public String getBelongCompanyName() {
		return belongCompanyName;
	}
	public void setBelongCompanyName(String belongCompanyName) {
		this.belongCompanyName = belongCompanyName;
	}
	public String getTeamManagerId() {
		return teamManagerId;
	}
	public void setTeamManagerId(String teamManagerId) {
		this.teamManagerId = teamManagerId;
	}
	public String getTeamManagerName() {
		return teamManagerName;
	}
	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	public String getTeamLeaderId() {
		return teamLeaderId;
	}
	public void setTeamLeaderId(String teamLeaderId) {
		this.teamLeaderId = teamLeaderId;
	}
	public String getTeamLeaderName() {
		return teamLeaderName;
	}
	public void setTeamLeaderName(String teamLeaderName) {
		this.teamLeaderName = teamLeaderName;
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
