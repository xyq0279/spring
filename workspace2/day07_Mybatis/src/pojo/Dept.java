package pojo;

import java.util.List;

public class Dept {
	
	private Integer deptId;
	private String deptName;
	private List<User> userList;
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "dept [deptId=" + deptId + ", deptName=" + deptName
				+ ", userList=" + userList + "]";
	}
	
}
