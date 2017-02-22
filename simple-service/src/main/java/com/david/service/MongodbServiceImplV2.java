package com.david.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.david.dto.User;
import com.david.facade.MongodbServiceV2;
import com.mongodb.WriteResult;

@Service
public class MongodbServiceImplV2 implements MongodbServiceV2 {

	@Autowired
	private MongoTemplate mongoTemlate;

	@Override
	public void mongodbInsertUser(User user) {
		mongoTemlate.insert(user);
	}

	@Override
	public void mongodbDeleteUser(Long userId) {
		Query query = getQueryOne(userId);
		WriteResult result = mongoTemlate.remove(query, User.class);
		System.err.println("删除 " + result.getN() + "条记录");
	}

	@Override
	public void mongodbUpdateUser(User user) {
		Query query = getQueryOne(user.getUserId());
		Update update = new Update();
		String timestamp = String.valueOf(System.currentTimeMillis());
		update = update.set("name", user.getName() + "_" + timestamp);
		update = update.set("phone", user.getPhone() + "_" + timestamp);
		WriteResult result = mongoTemlate.updateFirst(query, update, User.class);
		System.err.println("更新了" + result.getN() + "条记录");
	}

	@Override
	public User mongodbQueryUser(Long userId) {
		Query query = getQueryOne(userId);
		return mongoTemlate.findOne(query, User.class);
	}

	private Query getQueryOne(Long userId) {
		Criteria criteria = new Criteria("userId");
		criteria = criteria.is(userId);
		Query query = Query.query(criteria);
		return query;
	}

	@Override
	public List<User> mongodbQueryUsers() {
		return mongoTemlate.findAll(User.class);
	}

}
