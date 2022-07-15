package jp.co.vaile.nerva.updateProject;

public class AddableTeamDTO {

	private String teamId; //チームID
	private String teamName; //チーム名
	private String belongCompanyId; //所属会社ID
	private String belongCompanyName; //所属会社名
	private String teamManagerId; //所属部長ID
	private String teamManagerName; //所属部長名
	private String teamLeaderId; //チームリーダーID
	private String teamLeaderName; //チームリーダー名

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

}
