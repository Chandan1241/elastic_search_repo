package com.chandan.boot.elasticsearch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chandan.boot.elasticsearch.model.StatusMessage;
import com.chandan.boot.elasticsearch.model.User;
import com.chandan.boot.elasticsearch.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * User controller for Elastic search along with swagger setup
 * 
 * @author Chandan
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "UserController", description = "User Controller for elastic search")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final String INDEX_NAME = "user_index";

	@Autowired
	private IUserService userService;

	/**
	 * Create index by given name
	 * 
	 * @param indexName
	 * @return
	 */
	@PutMapping(value = "/create_index", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "PUT: Create index in elastic search ::: click to create index")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "URL details does not exist"), })
	public @ResponseBody Map<String, Boolean> createIndex() {
		LOGGER.info("Started saving user data in elastic search.....");
		boolean isCreated = userService.createIndex(INDEX_NAME);
		Map<String, Boolean> result = new HashMap<>();
		result.put("isCreated", isCreated);

		return result;
	}

	/**
	 * Get user by email Id or phone number or last name
	 * 
	 * @param emailId
	 * @return
	 */
	@GetMapping(value = "/getUserByEmailOrLnameOrPhone/{criteria}", produces = "application/json")
	@ApiOperation(value = "Get: Get user data by email id or last name or phone number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "URL details does not exist"), })
	public List<User> getUserByEmailOrLnameOrPhone(@PathVariable("criteria") String criteria) {
		LOGGER.info("Started searching data in index :" + INDEX_NAME + " for criteria :" + criteria);
		return userService.getUserByEmailOrLnameOrPhone(criteria);
	}

	/**
	 * Get user by email Id or phone number or last name
	 * 
	 * @param emailId
	 * @return
	 */
	@GetMapping(value = "/getUserByUserId", produces = "application/json")
	@ApiOperation(value = "Get: Get user data by email id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "URL details does not exist"), })
	public User getUserByUserId(@RequestParam("userId") Long userId) {
		LOGGER.info("Started searching data in index :" + INDEX_NAME + " for userID :" + userId);
		return userService.getUserByUserId(userId);
	}
	
	
	/**
	 * Get all user from a index
	 * 
	 * @return
	 */
	@GetMapping(value = "/getAllUser", produces = "application/json")
	@ApiOperation(value = "Get: Get all user data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "URL details does not exist"), })
	public List<User> getAllUser() {
		LOGGER.info("Started searching data in index :" + INDEX_NAME);
		return userService.getAllUser();
	}

	/**
	 * save list of user object data in elastic search index
	 * 
	 * @param user
	 */
	@PostMapping(value = "/save", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "POST: Add list of user object data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "URL details does not exist"), })
	public @ResponseBody StatusMessage saveUserData(@RequestBody Set<User> user) {
		LOGGER.info("Started saving user in index name :" + INDEX_NAME + " user data : " + user);
		return userService.saveUser(user);

	}

	/**
	 * save list of user object data in elastic search index
	 * 
	 * @param user
	 */
	@PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "POST: Add list of user object data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "URL details does not exist"), })
	public @ResponseBody StatusMessage updateUser(@RequestBody Set<User> user) {
		LOGGER.info("Started updating user in index name: " + INDEX_NAME);
		return userService.saveUser(user);

	}

	/**
	 * save list of user object data in elastic search index
	 * 
	 * @param user
	 */
	@PostMapping(value = "/deleteUser", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "POST: Add list of user object data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Invalid input provided"),
			@ApiResponse(code = 404, message = "URL details does not exist"), })
	public @ResponseBody StatusMessage deleteUser(@RequestParam("userId") Long userId) {
		LOGGER.info("Started deleting user record from index :" + INDEX_NAME);
		return userService.deleteUser(userId);
	}
}
