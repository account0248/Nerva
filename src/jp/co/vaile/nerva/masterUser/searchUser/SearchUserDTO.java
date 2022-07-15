package jp.co.vaile.nerva.masterUser.searchUser;

public class SearchUserDTO {
	private String userId;
	private String userName;
	private String company;
	private String post;
	private String adminFlg;
	private String companyGroup;
	private String loginUserCompanyId;

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getCompanyGroup() {
		return companyGroup;
	}
	public void setCompanyGroup(String companyGroup) {
		this.companyGroup = companyGroup;
	}
	public String getLoginUserCompanyId() {
		return loginUserCompanyId;
	}
	public void setLoginUserCompanyId(String loginUserCompanyId) {
		this.loginUserCompanyId = loginUserCompanyId;
	}


}
