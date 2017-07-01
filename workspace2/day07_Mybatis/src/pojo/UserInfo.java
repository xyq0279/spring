package pojo;

public class UserInfo {
	private Integer userId;
	private String tel;
	private String qq;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", tel=" + tel + ", qq=" + qq
				+ "]";
	}
	
}
