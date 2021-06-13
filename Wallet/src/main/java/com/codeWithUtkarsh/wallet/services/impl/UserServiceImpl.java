package com.codeWithUtkarsh.wallet.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.codeWithUtkarsh.wallet.config.exception.UserServiceException;
import com.codeWithUtkarsh.wallet.model.entity.Wallet;
import com.codeWithUtkarsh.wallet.repository.UserRepository;
import com.codeWithUtkarsh.wallet.services.UserService;
import com.codeWithUtkarsh.wallet.util.MapperUtils;

@Component
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		com.codeWithUtkarsh.wallet.model.entity.User user = userRepository.findByUserName(username);
		return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

	@Override
	public com.codeWithUtkarsh.wallet.model.dto.UserDetails createUserProfile(
			com.codeWithUtkarsh.wallet.model.dto.UserDetails useProfile) throws UserServiceException
	{
		try
		{
			LOGGER.debug("User creation at:{}", System.currentTimeMillis());
			com.codeWithUtkarsh.wallet.model.entity.User createdUser = userRepository
					.save(MapperUtils.convertUserDetailsToUser(useProfile));
			return MapperUtils.convertUserToUserDetails(createdUser);
		}catch (Exception e) {
			throw new UserServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public com.codeWithUtkarsh.wallet.model.dto.UserDetails updateUserProfile(
			com.codeWithUtkarsh.wallet.model.dto.UserDetails useProfile) throws UserServiceException
	{
		try
		{
			LOGGER.debug("User updation at:{}", System.currentTimeMillis());
			com.codeWithUtkarsh.wallet.model.entity.User fetchedUser = userRepository.findByUserName(useProfile.getUserName());
			if(fetchedUser != null)
			{
				fetchedUser.setEmailId(useProfile.getEmailId());
				com.codeWithUtkarsh.wallet.model.entity.User updatedUser = userRepository.save(fetchedUser);
				return MapperUtils.convertUserToUserDetails(updatedUser);
			}
		}catch (Exception e) {
			throw new UserServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}

	@Override
	public void deleteUserProfileByUserName(String userName) throws UserServiceException
	{
		try
		{
			LOGGER.debug("User deletion at:{}", System.currentTimeMillis());
			com.codeWithUtkarsh.wallet.model.entity.User user = userRepository.findByUserName(userName);
			if(user != null)
			{
				userRepository.delete(user);
			}
		}catch (Exception e) {
			throw new UserServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public com.codeWithUtkarsh.wallet.model.dto.UserDetails getUserProfileByUserName(String userName) throws UserServiceException
	{
		try
		{
			LOGGER.debug("User retrieval at:{}", System.currentTimeMillis());
			com.codeWithUtkarsh.wallet.model.entity.User user = userRepository.findByUserName(userName);
			return MapperUtils.convertUserToUserDetails(user);
		}catch (Exception e) {
			throw new UserServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<com.codeWithUtkarsh.wallet.model.dto.UserDetails> getAllUserProfile() throws UserServiceException
	{
		try
		{
			LOGGER.debug("User retrieval at:{}", System.currentTimeMillis());
			List<com.codeWithUtkarsh.wallet.model.entity.User> findAll = userRepository.findAll();
			return MapperUtils.convertUserToUserDetails(findAll);
		}catch (Exception e) {
			throw new UserServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public com.codeWithUtkarsh.wallet.model.dto.UserDetails createSignUp(com.codeWithUtkarsh.wallet.model.entity.User user) 
			throws UserServiceException
	{
		try
		{
			LOGGER.debug("User SignUp Initiated at:{}", System.currentTimeMillis());
			user.setWallet(new Wallet(0)); //creating the user with wallet setup and amount 0
			com.codeWithUtkarsh.wallet.model.entity.User createdUser = userRepository.save(user);
			return MapperUtils.convertUserToUserDetails(createdUser);
		}catch (Exception e) {
			throw new UserServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public com.codeWithUtkarsh.wallet.model.entity.User getUserDbEntityByUserName(String userName) throws UserServiceException
	{
		try
		{
			LOGGER.debug("User entity retrieval at:{}", System.currentTimeMillis());
			return userRepository.findByUserName(userName);
		}catch (Exception e) {
			throw new UserServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
