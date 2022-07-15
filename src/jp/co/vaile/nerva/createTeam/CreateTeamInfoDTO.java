package jp.co.vaile.nerva.createTeam;

public class CreateTeamInfoDTO {


	private String teamId;
	private String teamName;
	private String managerId;
	private String registUser;

	/**
	 * @return teamId
	 */
	public String getTeamId() {
		return teamId;
	}
	/**
	 * @param teamId セットする teamId
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	/**
	 * @return teamName
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * @param teamName セットする teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	/**
	 * @return managerId
	 */
	public String getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId セットする managerId
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	/**
	 * @return registUser
	 */
	public String getRegistUser() {
		return registUser;
	}
	/**
	 * @param registUser セットする registUser
	 */
	public void setRegistUser(String registUser) {
		this.registUser = registUser;
	}

}
