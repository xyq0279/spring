package aspect;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import threadLocal.PriThreadLocal;
import anno.PersonInfo;

@Component
@Aspect
public class PriAspect {
	
	@Around("execution(* service..*.*(..)) && @annotation(pri)")
	public Object around(ProceedingJoinPoint joinPoint,PersonInfo pri) throws Throwable{
		Object result = null;
		List<String> list = PriThreadLocal.getThreadLocal();
		String methodName = joinPoint.getSignature().getName();
//		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();
		Class[] argClass = new Class[args.length];
		for (int i = 0; i < argClass.length; i++) {
			argClass[i]=args[i].getClass();
		}
		
		Method method = joinPoint.getTarget().getClass().getMethod(methodName,argClass);
		if(method.isAnnotationPresent(PersonInfo.class)){
			
			PersonInfo pi = method.getAnnotation(PersonInfo.class);
			String p = pi.name();
			if(list.contains(p)){
				result = joinPoint.proceed();
				System.out.println("权限满足");
			}else{
				System.out.println("权限不足，请联系管理员");
			}
			
		}else{
			result = joinPoint.proceed();
			System.out.println("不需要权限");
		}
		
		return result;
	}
}
