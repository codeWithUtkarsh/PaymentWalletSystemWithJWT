package com.codeWithUtkarsh.wallet.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeWithUtkarsh.wallet.model.UserAuthRequest;
import com.codeWithUtkarsh.wallet.model.entity.User;
import com.codeWithUtkarsh.wallet.services.UserService;
import com.codeWithUtkarsh.wallet.util.AccessTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
		
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private UserService userManagementService;
	
	@PostMapping("/signup")
	public String signup(@RequestBody UserAuthRequest authenticationRequest) throws Exception
	{
		com.codeWithUtkarsh.wallet.model.dto.UserDetails createUser = userManagementService
				.createSignUp(new User(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		if(createUser != null)
		{
			return "SUCCESS, SignIn Now";
		}else
		{
			return "FAILED, Contact to the admin";
		}
	}
	
	@PostMapping("/signin")
	public String signin(@RequestBody UserAuthRequest authenticationRequest) throws Exception
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userManagementService
				.loadUserByUsername(authenticationRequest.getUsername());
		return accessTokenUtil.generateToken(userDetails);
	}

}
