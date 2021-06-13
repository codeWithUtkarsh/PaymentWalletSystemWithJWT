package com.codeWithUtkarsh.wallet.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codeWithUtkarsh.wallet.config.exception.UtilServiceException;
import com.codeWithUtkarsh.wallet.services.UserService;
import com.codeWithUtkarsh.wallet.util.AccessTokenUtil;

@Component
public class RequestFilterConfig extends OncePerRequestFilter 
{
	@Autowired
	private UserService userManagementService;

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			try {
				username = accessTokenUtil.extractUserName(jwt);
			} catch (UtilServiceException e) {
				e.printStackTrace();
			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userManagementService.loadUserByUsername(username);

			try {
				if (accessTokenUtil.validateToken(jwt, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch (UtilServiceException e) {
				e.printStackTrace();
			}
		}
		chain.doFilter(request, response);
	}
}
