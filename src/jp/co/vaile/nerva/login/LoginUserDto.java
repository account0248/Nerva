package jp.co.vaile.nerva.login;

public class LoginUserDto {
	private String userId;
	private String userName;
	private String companyId;
	private boolean adminFlg;
	private boolean loginSuccess;
	private boolean companyPrivilege;

	public LoginUserDto(LoginEntity loginEntity) {
		userId = loginEntity.userId;
		userName = loginEntity.userName;
		companyId = loginEntity.companyId;
		adminFlg = loginEntity.adminFlg;
		loginSuccess = loginEntity.loginSuccess;
		companyPrivilege = loginEntity.companyPrivilege;
	}

	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public boolean isAdminFlg() {
		return adminFlg;
	}
	public boolean isLoginSuccess() {
		return loginSuccess;
	}
	public boolean isCompanyPrivilege() {
		return companyPrivilege;
		
	}
}
