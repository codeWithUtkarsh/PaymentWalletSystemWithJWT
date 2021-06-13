package com.codeWithUtkarsh.wallet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.codeWithUtkarsh.wallet.services.UserService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserService userManagementService;
	
	@Autowired
	private RequestFilterConfig filterConfig;
	
	private static final String[] AUTH_URL= {"/auth/**"};
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userManagementService);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.csrf().disable().authorizeRequests()
				.antMatchers(AUTH_URL).permitAll().anyRequest().authenticated()
				.and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(filterConfig, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/h2-console/**");
    }
	
}
