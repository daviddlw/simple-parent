package org.simple.util;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class JUnit4ClassRunner extends SpringJUnit4ClassRunner {

	/**
	 * 在初始化springJUnit4ClassRunner之前事先执行log4j.properties加载
	 */
	static {
		String rootPath = System.getProperty("user.dir");
		PropertyConfigurator.configure(rootPath.concat("/src/main/resources/file/log4j.properties"));
	}

	public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

}
