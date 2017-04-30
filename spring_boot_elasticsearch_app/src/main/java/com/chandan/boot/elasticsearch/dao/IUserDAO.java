package com.chandan.boot.elasticsearch.dao;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.chandan.boot.elasticsearch.model.User;

@Repository
public interface IUserDAO extends ElasticsearchRepository<User, Long> {

	@Query("{\"bool\":{\"must\":[{\"query_string\":{\"fields\":[\"lastName\", \"email\", \"phoneNum\"],\"query\": \"?0\"}}]}}")
	List<User> getUserByEmailOrLnameOrPhone(String criteria);

}
