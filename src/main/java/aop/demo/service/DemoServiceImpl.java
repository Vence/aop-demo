package aop.demo.service;

import org.springframework.stereotype.Service;

import aop.demo.annotation.Auth;
import aop.demo.annotation.AuthType;

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
