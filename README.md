##aop-demo
利用AOP技术实现日志管理、权限验证等，这些都有一个共同的特点：就是面向切面。

### Spring配置AOP
    <context:component-scan base-package="aop.demo">
	</context:component-scan>
	<aop:aspectj-autoproxy/>
扫描包aop.demo；
开启AOP自动代理；

### 设置AOP

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
		
		// TODO process
		
		return "success";
	}
	}

设置切入点，这里只针对aop.demo.service包下所有类的所有方法。

beforeMethod会在AOP拦截的方法之前执行，这里对beforeMethod设置了前置条件
>1. 拥有注解auth
>2. 方法参数满足：第一个参数名是userId，第二个参数名是entityId

### 切入点
    @Service("demoService")
	public class DemoServiceImpl implements DemoService {
	
		@Override
		@Auth(type=AuthType.USER, code="0001")
		public void test(String userId, String entityId) {
			System.out.println("test");
		}
	
		@Override
		@Auth(type=AuthType.PROJECT, code="0002")
		public void test1(String userId, String entityId, String test) {
			System.out.println(test);
		}

	}

这里的切入点必须满足上一节说的两个条件。

### 测试
    @Test
	public void inteceptorTest() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		DemoService bean = (DemoService) ctx.getBean("demoService");
		
		try {
		bean.test("userId1111", "entityId222");
		bean.test1("userId1111", "entityId222", "ccc");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

输入结果：
>USER|0001
>
>userId1111
>
>entityId222
>
>test
>
>PROJECT|0002
>
>userId1111
>
>entityId222
>
>ccc

