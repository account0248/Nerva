package jp.co.vaile.nerva.masterUser.insertUser;

public class InsertUserDTO {

	private String insertUserId;
	private String targetUserId;
	private String userName;
	private String password;
	private String company;
	private String post;
	private String adminFlg;

	public InsertUserDTO(String insertUserId, String userId, String userName, String password, String company,
			String post, String adminFlg) {
		this.insertUserId = insertUserId;
		this.targetUserId = userId;
		this.userName = userName;
		this.password = password;
		this.company = company;
		this.post = post;
		this.adminFlg = adminFlg;
	}

	public String getInsertUserId() {
		return insertUserId;
	}

	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAdminFlg() {
		return adminFlg;
	}

	public void setAdminFlg(String adminFlg) {
		this.adminFlg = adminFlg;
	}

}