package com.codeWithUtkarsh.wallet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeWithUtkarsh.wallet.config.exception.UserServiceException;
import com.codeWithUtkarsh.wallet.model.dto.UserDetails;
import com.codeWithUtkarsh.wallet.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userManagementService;
	
	@PutMapping("/updateUserProfile")
	public UserDetails updateUserProfile(@RequestBody UserDetails userDetails) throws UserServiceException
	{
		return userManagementService.updateUserProfile(userDetails);
	}
	
	@GetMapping("/{username}")
	public UserDetails viewUserProfile(@PathVariable("username") String userName) throws UserServiceException
	{
		return userManagementService.getUserProfileByUserName(userName);
	}
}
