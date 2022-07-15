package jp.co.vaile.nerva.masterUser.updateUser;

public class UpdateUserDTO {
	// 所属組織マスタメンテナンス画面の更新時に入力される値を格納。
	private String[] targetUserId;
	private String[] userName;
	private String[] password;
	private String[] post;
	private String[] adminFlg;
	private String updateUserId;

	public UpdateUserDTO() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public String[] getTargetUserId() {
		return targetUserId;
	}

	public void setTargetUserId(String[] targetUserId) {
		this.targetUserId = targetUserId;
	}

	public String[] getUserName() {
		return userName;
	}

	public void setUserName(String[] userName) {
		this.userName = userName;
	}

	public String[] getPassword() {
		return password;
	}

	public void setPassword(String[] password) {
		this.password = password;
	}

	public String[] getPost() {
		return post;
	}

	public void setPost(String[] post) {
		this.post = post;
	}

	public String[] getAdminFlg() {
		return adminFlg;
	}

	public void setAdminFlg(String[] adminFlg) {
		this.adminFlg = adminFlg;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

}