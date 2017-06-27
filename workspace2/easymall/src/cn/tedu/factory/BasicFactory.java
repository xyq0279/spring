package cn.tedu.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cn.tedu.Utils.PropUtils;
import cn.tedu.Utils.TranManage;
import cn.tedu.anno.Tran;
import cn.tedu.dao.Dao;
import cn.tedu.service.Service;

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
			
			
			
			
			//区分t是service层的对象到时候dao层对象
			if(Service.class.isAssignableFrom(clz)){//service层对象
				//3、通过Class对象创建该类的实例并返回
				final T t = (T) clz.newInstance();
				T proxy = (T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), 
						new InvocationHandler() {
							public Object invoke(Object proxy, Method method, Object[] args)
									throws Throwable {
								// 日志处理、事务处理、权限处理
								Object result = null; 
								//判断当前方法是否使用事务的注解
								if(method.isAnnotationPresent(Tran.class)){
									//返回代理对象
									try{
										
										TranManage.startTran();
										result =  method.invoke(t, args);
										TranManage.commitTran();
									}catch(InvocationTargetException ite){
//										ite.printStackTrace();
										TranManage.rollbackTran();
										throw ite.getCause();
									}catch(Exception e){
										TranManage.rollbackTran();
										e.printStackTrace();
									}finally{
										TranManage.release();
									}
								}else{
									try{
										result =  method.invoke(t, args);
									}catch(InvocationTargetException ite){
//										ite.printStackTrace();
										throw ite.getCause();
									}catch(Exception e){
										e.printStackTrace();
									}finally{
										TranManage.release();
									}
								}
								return result;
							}
					});
				
				return proxy;
			}else if(Dao.class.isAssignableFrom(clz)){//dao层对象
				//通过Class对象创建该类的实例并返回
				return (T) clz.newInstance();
			}else{
				System.out.println("别捣乱");
				return null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
