package service;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import anno.PersonInfo;
import anno.Tran;

import dao.PersonDao;


@Service("person")
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonDao personDao;
	
	@Override
//	@Tran
	@PersonInfo(name="hah")
	public void save() {
		personDao.save();
//		int a =2/0;
	}

}
