package com.chandan.boot.elasticsearch.service;

import java.util.List;
import java.util.Set;

import com.chandan.boot.elasticsearch.model.StatusMessage;
import com.chandan.boot.elasticsearch.model.User;

public interface IUserService {

	StatusMessage saveUser(Set<User> user);

	boolean createIndex(String indexName);

	List<User> getUserByEmailOrLnameOrPhone(String emailId);

	StatusMessage deleteUser(Long userId);

	User getUserByUserId(Long userId);

	List<User> getAllUser();

}
