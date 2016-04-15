import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import aop.demo.service.DemoService;

public class Demo {
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
}
