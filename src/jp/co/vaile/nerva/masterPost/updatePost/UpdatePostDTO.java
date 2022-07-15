package jp.co.vaile.nerva.masterPost.updatePost;

public class UpdatePostDTO {
	//役職マスタメンテナンス画面の更新時に入力される値を格納
	private String updateUserId;
	private String[] postId;
	private String[] postName;
	
	//コンストラクタ
	UpdatePostDTO(String updateUserId, String[] postId, String[] postName){
		this.updateUserId = updateUserId;
		this.postId = postId;
		this.postName = postName;
	}
	
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String[] getPostId() {
		return postId;
	}
	public void setPostId(String[] postId) {
		this.postId = postId;
	}
	public String[] getPostName() {
		return postName;
	}
	public void setPostName(String[] postName) {
		this.postName = postName;
	}
}
