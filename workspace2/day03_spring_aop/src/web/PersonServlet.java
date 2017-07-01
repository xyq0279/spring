package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.PersonService;

@Controller
public class PersonServlet {
	
	@Autowired
	private PersonService person;
	
	public void save(){
		person.save();
	}
}
