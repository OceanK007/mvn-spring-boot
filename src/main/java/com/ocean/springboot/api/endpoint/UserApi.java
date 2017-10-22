package com.ocean.springboot.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.service.UserService;
import com.ocean.springboot.util.constant.ApiConstant;

@RestController
@RequestMapping(ApiConstant.USER_API)
public class UserApi 
{
	private static final Logger logger = LoggerFactory.getLogger(UserApi.class);
	/** ObjectMapper : To parse json to object | To convert object to json string | etc **/
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired	// This means to get the bean called userService, which is auto-generated by Spring
	UserService userService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public UserDTO createUser(@RequestBody UserDTO userDTO, @RequestHeader("zoneId") String zoneId) throws JsonProcessingException
	{
		userDTO = userService.createUser(userDTO, zoneId);
		logger.info("userDTO: "+objectMapper.writeValueAsString(userDTO));
		return userDTO;
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public UserDTO updateUser(@RequestBody UserDTO userDTO, @RequestHeader("zoneId") String zoneId)
	{
		return userService.updateUser(userDTO, zoneId);
	}
	
	/** 
		@GetMapping is a shortcut for @RequestMapping(method=GET)
		@GetMapping(path="/add") map only GET requests
		@RequestMapping maps all HTTP operations by default. Use @RequestMapping(method=GET) or other shortcut annotations to narrow this mapping.
	**/
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public Page<UserDTO> getUserByPage(@RequestParam(value="page", required=true) int page, @RequestParam(value="pageSize", required=true) int pageSize)
	{
		return userService.getUserByPage(page, pageSize);
	}
}