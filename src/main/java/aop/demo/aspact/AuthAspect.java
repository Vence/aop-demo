package aop.demo.aspact;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import aop.demo.annotation.Auth;

@Aspect
@Component
public class AuthAspect {

	@Pointcut("execution(* aop.demo.service.*.*(..))")
	private void log() {
	}

	@Before(value = "log() && @annotation(auth) && args(userId, entityId,..)")
	public Object beforeMethod(JoinPoint pjd, Auth auth, String userId,
			String entityId) throws Exception {
		System.out.println(auth.type() + "|" + auth.code());
		System.out.println(userId);
		System.out.println(entityId);

		throw new Exception("≈≈≥˝“Ï≥£");
	}
}
