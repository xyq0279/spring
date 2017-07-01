package pojo;

import java.util.List;

public class Teacher {
	private Integer tId;
	private String tName;
	private String tSex;
	private List<Student> studentList;
	public Integer gettId() {
		return tId;
	}
	public void settId(Integer tId) {
		this.tId = tId;
	}
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettSex() {
		return tSex;
	}
	public void settSex(String tSex) {
		this.tSex = tSex;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	@Override
	public String toString() {
		return "Teacher [tId=" + tId + ", tName=" + tName + ", tSex=" + tSex
				+ ", studentList=" + studentList + "]";
	}
	
	
}
