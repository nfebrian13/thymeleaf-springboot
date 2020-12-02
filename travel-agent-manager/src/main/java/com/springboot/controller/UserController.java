package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.model.User;
import com.springboot.service.RoleService;
import com.springboot.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleService roleService;

	@RequestMapping("/user-index")
	public String viewHomePage(Model model) {
		List<User> listUsers = userService.listAll();
		model.addAttribute("listUsers", listUsers);

		return "views/user/user_index";
	}

	@RequestMapping("/user")
	public String showNewUserForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.findAll());
		return "views/user/new_user";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		if (user.getId() == null) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		
		userService.save(user);

		return "redirect:/user-index";
	}

	@RequestMapping("/user/{id}/edit")
	public ModelAndView showEditUserForm(@PathVariable(name = "id") String id) {
		ModelAndView mav = new ModelAndView("views/user/edit_user");

		User user = userService.get(id);
		mav.addObject("user", user);
		mav.addObject("roles", roleService.findAll());
		return mav;
	}

	@RequestMapping("/user/{id}/delete")
	public String deleteProduct(@PathVariable(name = "id") String id) {
		userService.delete(id);

		return "redirect:/user-index";
	}
}
