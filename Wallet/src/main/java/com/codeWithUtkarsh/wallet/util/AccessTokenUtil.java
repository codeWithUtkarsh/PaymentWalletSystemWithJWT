package com.codeWithUtkarsh.wallet.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.codeWithUtkarsh.wallet.config.exception.UtilServiceException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AccessTokenUtil {

	private final String SECRET_KEY = "secret";

	public Date extractExpirationTime(String token) throws UtilServiceException
	{
		try
		{
			return extractClaim(token, Claims::getExpiration);
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws UtilServiceException
	{
		try
		{
			final Claims claims = extractAllClaims(token);
			return claimsResolver.apply(claims);
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Claims extractAllClaims(String token) throws UtilServiceException
	{
		try
		{
			return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean isTokenExpired(String token) throws UtilServiceException
	{
		try
		{
			return extractExpirationTime(token).before(new Date());
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public String generateToken(UserDetails userDetails) throws UtilServiceException
	{
		try
		{
			Map<String, Object> claims = new HashMap<>();
			return createToken(claims, userDetails.getUsername());
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String createToken(Map<String, Object> claims, String subject) throws UtilServiceException
	{
		try
		{
			return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
					.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public Boolean validateToken(String token, UserDetails userDetails) throws UtilServiceException
	{
		try
		{
			final String username = extractUserName(token);
			return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public String extractUserName(String jwt) throws UtilServiceException
	{
		try
		{
			return extractClaim(jwt, Claims::getSubject);
		}catch (Exception e) {
			throw new UtilServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
