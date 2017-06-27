package aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import anno.Tran;

import tran.TranManager;


@Component
public class TxAspect {
	
	@Autowired
	private TranManager tx;
	
	public void before(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException{
		
		String name = joinPoint.getSignature().getName();
		Method method = joinPoint.getTarget().getClass().getMethod(name);
		System.out.println(joinPoint.getTarget().getClass());
		System.out.println(joinPoint.getSignature().getName());
	}
	
	private long time;
	public void around(ProceedingJoinPoint joinPoint) throws Throwable{
		
		tx.begin();
		time=System.currentTimeMillis();
		Class targetClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		joinPoint.proceed();
		time = System.currentTimeMillis()-time;
		System.out.println(targetClass+"的"+methodName+"方法调用的时间为:"+time+"ms");
		tx.commit();
		
	}
	
	
	public void around1(ProceedingJoinPoint joinPoint) throws Throwable{
		
		
		Class targetClass = joinPoint.getTarget().getClass();
		String methodName = joinPoint.getSignature().getName();
		
//		Method method = targetClass.getMethod(methodName);
//		System.out.println(method.isAnnotationPresent(Tran.class));
		Method method=joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName());
		System.out.println(method.isAnnotationPresent(Tran.class));
		if(method.isAnnotationPresent(Tran.class)){
			tx.begin();
			time=System.currentTimeMillis();	
			joinPoint.proceed();
			time = System.currentTimeMillis()-time;
			System.out.println(targetClass+"的"+methodName+"方法调用的时间为:"+time+"ms");
			tx.commit();
		}else{
			joinPoint.proceed();
		}
		
	}
	
}
