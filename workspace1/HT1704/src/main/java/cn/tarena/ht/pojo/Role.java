package cn.tarena.ht.pojo;

public class Role extends BaseEntity{
	
	private String roleId;
	private String name;//角色名称
	private String remarks;//备注信息
	private Integer orderNo;
	private Boolean checked;
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	/*
	 * 根据zTree树的需要id，name属性，需要手写getId方法
	 */
	public String getId(){
		return roleId;
	}
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", remarks=" + remarks + ", orderNo=" + orderNo
				+ ", checked=" + checked + "]";
	}
	
}
