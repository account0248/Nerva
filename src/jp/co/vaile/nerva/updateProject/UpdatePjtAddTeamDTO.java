package jp.co.vaile.nerva.updateProject;

public class UpdatePjtAddTeamDTO {

	private String teamId; //チームID
//	private String teamName; //チーム名
	private String registUsertId; //登録者ID
	private String assignStartDate; //配属開始日
	private String assignCompleteDate; //配属完了日
	private int projectInfoId; //案件情報ID

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	//	public String getTeamName() {
	//		return teamName;
	//	}
	//	public void setTeamName(String teamName) {
	//		this.teamName = teamName;
	//	}
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
	public String getRegistUsertId() {
		return registUsertId;
	}
	public void setRegistUsertId(String registUsertId) {
		this.registUsertId = registUsertId;
	}
	public int getProjectInfoId() {
		return projectInfoId;
	}
	public void setProjectInfoId(int projectInfoId) {
		this.projectInfoId = projectInfoId;
	}

}
