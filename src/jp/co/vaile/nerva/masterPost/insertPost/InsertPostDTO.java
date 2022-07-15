package jp.co.vaile.nerva.masterPost.insertPost;

public class InsertPostDTO {

	private String registUserId;
	private String postId;
	private String postName;
	
	InsertPostDTO(String registUserId, String postId, String postName){
		this.registUserId = registUserId;
		this.postId = postId;
		this.postName = postName;
	}
		
	public String getRegistUserId() {
		return registUserId;
	}
	public void setRegistUserId(String registUserId) {
		this.registUserId = registUserId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	
}
