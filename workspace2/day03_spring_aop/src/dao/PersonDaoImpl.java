package dao;

import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao{

	@Override
	public void save() {
		System.out.println("保存成功");
		
	}

}
