package com.controler;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.principle.UserDetails;

@Controller
public class MainController {

	@RequestMapping(value = "/dasboard", method = RequestMethod.GET)
	public String homepage() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		boolean isAdmin = user.getRoles().get(0).equals("admin_8role");
		if (isAdmin) {
			return "admin-dashboard";
		} else {
			return "user-dashboard";
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHome() {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		return "login";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getHello() {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		System.out.println(user.getFirstName());
		return "hello";
	}

}