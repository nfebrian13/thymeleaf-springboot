package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.model.Role;
import com.springboot.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping("/role-index")
	public String viewHomePage(Model model) {
		List<Role> listroles = roleService.findAll();
		model.addAttribute("listRoles", listroles);

		return "views/role/role_index";
	}

	@RequestMapping("/role")
	public String showNewRoleForm(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);

		return "views/role/new_role";
	}

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String saveRole(@ModelAttribute("role") Role role) {
		roleService.save(role);

		return "redirect:/role-index";
	}

	@RequestMapping("/role/{id}/edit")
	public ModelAndView showEditRoleForm(@PathVariable(name = "id") String id) {
		ModelAndView mav = new ModelAndView("views/role/edit_role");

		Role role = roleService.get(id);
		mav.addObject("role", role);

		return mav;
	}

	@RequestMapping("/role/{id}/delete")
	public String deleteRole(@PathVariable(name = "id") String id) {
		roleService.delete(id);

		return "redirect:/role-index";
	}
}
