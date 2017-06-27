package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class People {
	@Autowired
	private Dog dog;
	@Autowired
	private Cat cat;
	@Override
	public String toString() {
		return "People [dog=" + dog + ", cat=" + cat + "]";
	}
	
	
}
