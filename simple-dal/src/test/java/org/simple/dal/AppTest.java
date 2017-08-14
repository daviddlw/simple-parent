package org.simple.dal;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.util.JUnit4ClassRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.david.dal.model.MemberInfo;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext-dal.xml" })
public class AppTest {

	private static final Logger log = LoggerFactory.getLogger(AppTest.class);

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

	private MemberInfo getMemberInfo() {
		MemberInfo memberInfo = new MemberInfo();
		memberInfo.setAge(RandomUtils.nextInt(0, 50));
		memberInfo.setEmail(RandomStringUtils.randomNumeric(9) + "@qq.com");
		memberInfo.setId(RandomUtils.nextInt(1, 1000));
		memberInfo.setMemberNo(RandomStringUtils.randomAlphanumeric(32));
		memberInfo.setMobile("135" + RandomStringUtils.randomNumeric(8));
		memberInfo.setRealname("daivdreal_" + RandomStringUtils.randomNumeric(6));
		memberInfo.setUsername("daivduser_" + RandomStringUtils.randomNumeric(6));
		memberInfo.setStatus(1);
		memberInfo.setCreateTime(new Date());
		memberInfo.setModifyTime(new Date());
		return memberInfo;
	}

	@Test
	public void stringOpsGetAndSetObject() {
		log.info("redist str key start");
		String key = "member_info";
		MemberInfo memberInfo = getMemberInfo();
		redisTemplate.opsForValue().set(key, JSON.toJSONString(memberInfo));
		String value = redisTemplate.opsForValue().get(key);
		MemberInfo resultMemberInfo = JSON.parseObject(value, MemberInfo.class);
		System.out.println(value);
		Assert.assertEquals(memberInfo.getMemberNo(), resultMemberInfo.getMemberNo());
		System.out.println("set value for ttl");
		String newkey = "member_info_new";
		redisTemplate.opsForValue().set(newkey, JSON.toJSONString(memberInfo), 1, TimeUnit.SECONDS);
		String ttlValue = redisTemplate.opsForValue().get(newkey);
		System.out.println("ttlValue=" + ttlValue);
		Assert.assertNotNull(ttlValue);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException ex) {
			log.error(ex.getMessage(), ex);
		}
		String ttlValueExpired = redisTemplate.opsForValue().get(newkey);
		System.out.println("ttlValueExpired=" + ttlValueExpired);
		Assert.assertNull(ttlValueExpired);
		log.info("redist str key end");
	}
	
	@Test
	public void listOpsGetAndSet() {
		String listkey = "list_key";
		redisTemplate.delete(listkey);
		redisTemplate.opsForList().leftPush(listkey, RandomStringUtils.randomAlphabetic(6));
		redisTemplate.opsForList().leftPush(listkey, RandomStringUtils.randomAlphabetic(6));
		List<String> list = redisTemplate.opsForList().range(listkey, 0, 2);
		System.out.println(list);
		Assert.assertEquals(2, list.size());
	}

}
