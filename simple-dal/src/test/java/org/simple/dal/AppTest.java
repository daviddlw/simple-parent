package org.simple.dal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.util.JUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-dal.xml" })
public class AppTest {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Test
	public void stringOpsGetAndSet() {
		String key = "member";
		redisTemplate.opsForValue().set(key, "david");
		String value = redisTemplate.opsForValue().get(key);
		Assert.assertEquals("david", value);
		System.out.println(value);
		redisTemplate.delete(key);
		String value2 = redisTemplate.opsForValue().get(key);
		Assert.assertNull(value2);
		
	}

}
