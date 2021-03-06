package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class TxAspect {
	
	//定义通知
	
	public void around(ProceedingJoinPoint joinPoint) throws Throwable{
		//让目标方法执行
		joinPoint.proceed();
	}
}
