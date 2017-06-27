package cn.tedu.factory;

import cn.tedu.Utils.PropUtils;

public class BasicFactory {
	//创建实例
	private static BasicFactory factory = new BasicFactory();
	//私有化构造方法
	private BasicFactory(){}
	//提供方法返回工厂实例
	public static BasicFactory getFactory(){
		return factory;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T getInstance(Class<T> cls){
		try {
			//1、读取配置文件中的配置信息（类的全限定名称）
			String className = PropUtils.getPro(cls.getSimpleName());
			
			//2、通过类的全限定名称获取该类的Class对象
			
			Class clz = Class.forName(className);
			
			//3、通过Class对象创建该类的实例并返回
			return (T) clz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
