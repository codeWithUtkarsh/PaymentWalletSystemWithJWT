package com.codeWithUtkarsh.wallet.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.codeWithUtkarsh.wallet.config.exception.UserServiceException;
import com.codeWithUtkarsh.wallet.model.dto.UserDetails;
import com.codeWithUtkarsh.wallet.model.entity.User;

@Component
public interface UserService extends UserDetailsService {

	public UserDetails createUserProfile(UserDetails useProfile) throws UserServiceException;
	public UserDetails updateUserProfile(UserDetails useProfile) throws UserServiceException;
	public void deleteUserProfileByUserName(String userName) throws UserServiceException;
	public UserDetails getUserProfileByUserName(String userName) throws UserServiceException;
	public List<UserDetails> getAllUserProfile() throws UserServiceException;
	public UserDetails createSignUp(User user) throws UserServiceException;
	public User getUserDbEntityByUserName(String userName) throws UserServiceException;
}
