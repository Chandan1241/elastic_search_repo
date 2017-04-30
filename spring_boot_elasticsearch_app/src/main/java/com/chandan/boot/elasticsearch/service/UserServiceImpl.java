package com.chandan.boot.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import com.chandan.boot.elasticsearch.dao.IUserDAO;
import com.chandan.boot.elasticsearch.model.StatusMessage;
import com.chandan.boot.elasticsearch.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Value("${user.mapping.file}")
	private String userMappingFile;

	@Autowired
	private IUserDAO userDao;

	/**
	 * 
	 */
	@Override
	public StatusMessage saveUser(Set<User> user) {
		if (null == user || user.isEmpty()) {
			throw new IllegalArgumentException("User object must not be null");
		}
		userDao.save(user);
		StatusMessage message = new StatusMessage();
		message.setStatus("success");
		message.setDescrption("User saved successfully.");
		return message;
	}

	/**
	 * Create index if not available
	 * 
	 * if it is available use it.
	 * 
	 * @param indexName
	 * @return
	 */
	public boolean createIndex(String indexName) {
		if (!elasticsearchTemplate.indexExists(indexName)) {
			return elasticsearchTemplate.createIndex(indexName);
		}

		return false;
	}

	@Override
	public List<User> getUserByEmailOrLnameOrPhone(String criteria) {
		if (null == criteria || criteria.isEmpty()) {
			throw new IllegalArgumentException("Criteria must not be null");
		}
		List<User> userList = userDao.getUserByEmailOrLnameOrPhone(criteria);
		return userList;
	}

	@Override
	public StatusMessage deleteUser(Long userId) {
		if (null == userId) {
			throw new IllegalArgumentException("UserId must not be null");
		}
		userDao.delete(userId);
		StatusMessage message = new StatusMessage();
		message.setStatus("success");
		message.setDescrption("User : " + userId + " deleted successfully ");
		return message;
	}

	@Override
	public User getUserByUserId(Long userId) {
		if (null == userId) {
			throw new IllegalArgumentException("UserId must not be null");
		}
		return userDao.findOne(userId);
	}

	@Override
	public List<User> getAllUser() {
		List<User> userList = new ArrayList<>();
		Iterable<User> response = userDao.findAll();
		if (null != response) {
			for (User user : response) {
				userList.add(user);
			}
		}
		return userList;
	}

}
