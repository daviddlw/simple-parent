package org.simple.service;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import com.david.api.HelloWorld;
import com.david.api.impl.HelloWorldHandler;
import com.david.api.impl.HelloWorldImpl;

public class TestDynamicProxy {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDynamicProxy() {
		HelloWorld helloWorld = new HelloWorldImpl();
		InvocationHandler handler = new HelloWorldHandler(helloWorld);
		HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(helloWorld.getClass().getClassLoader(), helloWorld.getClass().getInterfaces(), handler);
		proxy.sayHello();
	}

}
