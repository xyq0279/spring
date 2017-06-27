package factory;

import java.util.Calendar;

import org.springframework.beans.factory.FactoryBean;

public class SpringFactory implements FactoryBean<Calendar>{
	
	//工厂对象中最为重要的模块 通过该方法该方法生成对象
	@Override
	public Calendar getObject() throws Exception {
		return Calendar.getInstance();
	}
	//获取对象的类型
	@Override
	public Class<?> getObjectType() {
		return Calendar.class;
	}
	//是否一个单例
	@Override
	public boolean isSingleton() {
		return false;
	}
	
}
