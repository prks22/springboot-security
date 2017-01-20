package com.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.principle.UserDetails;

@Component
public class RealamProvider implements AuthenticationProvider {
	// @Autowired
	// UserDao dao;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		// UserDetails user=dao.getUserDetails("username");
		UserDetails userDetails = new UserDetails();
		userDetails.setEmail("xyx");
		userDetails.setUsername("admin");
		userDetails.setFirstName("prakash");
		userDetails.setLastname("Bisht");
		userDetails.setPassword("password");
		List<String> roles = new ArrayList<String>();
		roles.add("admin_8role");
		userDetails.setRoles(roles);

		if (userDetails == null
				|| !userDetails.getUsername().equalsIgnoreCase(username)) {
			throw new BadCredentialsException("Username not found.");
		}

		if (!password.equals(userDetails.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}

		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority(userDetails.getRoles().get(
				0)));

		return new UsernamePasswordAuthenticationToken(userDetails,
				userDetails.getPassword(), grantedAuths);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
