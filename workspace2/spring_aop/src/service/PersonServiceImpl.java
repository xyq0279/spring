package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anno.Tran;

import dao.PersonDao;


@Service("person")
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDao personDao;
	@Override
	public void save() {
		
		personDao.save();
	}

}
