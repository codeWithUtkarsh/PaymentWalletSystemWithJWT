package com.codeWithUtkarsh.wallet.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.codeWithUtkarsh.wallet.config.exception.UtilServiceException;
import com.codeWithUtkarsh.wallet.model.dto.UserDetails;
import com.codeWithUtkarsh.wallet.model.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MapperUtils {

	public static UserDetails convertUserToUserDetails(User user) throws UtilServiceException
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.convertValue(user, UserDetails.class);
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static User convertUserDetailsToUser(UserDetails userDetails) throws UtilServiceException
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.convertValue(userDetails, User.class);
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static List<UserDetails> convertUserToUserDetails(List<User> user) throws UtilServiceException
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.convertValue(user, new TypeReference<List<UserDetails>>(){});
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static List<User> convertUserDetailsToUser(List<UserDetails> userDetails) throws UtilServiceException
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.convertValue(userDetails, new TypeReference<List<User>>(){});
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
