package factory;

import java.util.Calendar;

public class NewInstanceFactory {
	
	public NewInstanceFactory(){
		System.out.println("我是一个实例工厂");
	}
	
	public Calendar getCalendar(){
		return Calendar.getInstance();
	}
}
