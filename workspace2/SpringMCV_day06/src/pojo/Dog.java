package pojo;

public class Dog {
	private String dogName;

	public String getDogName() {
		return dogName;
	}

	public void setDogName(String dogName) {
		this.dogName = dogName;
	}

	@Override
	public String toString() {
		return "Dog [dogName=" + dogName + "]";
	}
	
}
