package org.simple.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.david.dto.User;
import com.david.facade.MongodbServiceV2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext-service.xml" })
public class TestMongodb {

	@Autowired
	private MongodbServiceV2 mongodbServiceV2;

	private Logger logger = Logger.getLogger(TestMongodb.class);

	
    public boolean canConstruct(String ransomNote, String magazine) {
    	return magazine.contains(ransomNote);
    }
	
	@Test
	public void springLearning() {
		HandlerMapping handlerMapping = null;
		DispatcherServlet dispatcherServlet = null;
		RequestMappingHandlerMapping rmhm = null;
		WebApplicationContext webApplicationContext = null;
	}

	@Test
	public void testStringMask() {
		String str = "123456";
		String start = StringUtils.substring(str, 0, 2);
		String end = StringUtils.substring(str, str.length() - 2, str.length());
		System.err.println("start: " + start);
		System.err.println("end: " + end);
		String result = StringUtils.substring(str, 2, str.length() - 2);
		System.err.println(result);
		String newresult = result.replaceAll("\b[a-z]\b+", "*");
		System.err.println("result: " + newresult);
	}

	@Test
	public void testMongoInsert() {
		String timpstamp = String.valueOf(System.currentTimeMillis());
		User user = new User();
		user.setUserId(Long.parseLong(timpstamp));
		user.setName("红烧狮子头" + timpstamp);
		user.setPhone(timpstamp);
		user.setBirth(new Date());
		mongodbServiceV2.mongodbInsertUser(user);
		logger.info("新增成功！");
	}

	@Test
	public void testMongoQueryList() {
		List<User> users = mongodbServiceV2.mongodbQueryUsers();
		for (User user : users) {
			System.err.println(user);
		}
	}

	@Test
	public void testMongoDelete() {
		mongodbServiceV2.mongodbDeleteUser(1471343423666l);
	}

	@Test
	public void testMongoQueryOne() {
		User user = mongodbServiceV2.mongodbQueryUser(1471343581588l);
		System.err.println(user);
	}

	@Test
	public void testMongoUpdateOne() {
		User user = mongodbServiceV2.mongodbQueryUser(1471343581588l);
		user.setName("更新后的狮子头");
		user.setPhone("123123");
		mongodbServiceV2.mongodbUpdateUser(user);
	}
}
