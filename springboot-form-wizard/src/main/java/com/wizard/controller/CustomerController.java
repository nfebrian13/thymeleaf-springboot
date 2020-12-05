package com.wizard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.wizard.entity.Customer;
import com.wizard.validation.CustomerValidator;

@Controller
@RequestMapping("wizard")
@SessionAttributes("customer")
public class CustomerController {

	@Autowired
	CustomerValidator validator;

	@RequestMapping("/")
	public String indexAwal() {
		return "/wizard/form-1";
	}

	@RequestMapping(value = "form-1")
	public String index(Model model) {
		model.addAttribute("customer", new Customer());
		return "wizard1";
	}

	@RequestMapping(value = "form-2", method = RequestMethod.POST)
	public String wizard2(Model model, @ModelAttribute("customer") Customer customer, BindingResult result) {

		validator.validatePage1(customer, result);
		if (result.hasErrors()) {
			return "wizard1";
		}
		model.addAttribute("customer", customer);
		return "wizard2";
	}

	@RequestMapping(value = "form-3", method = RequestMethod.POST)
	public String wizard3(Model model, @ModelAttribute("customer") Customer customer, BindingResult result) {
		validator.validatePage2(customer, result);
		if (result.hasErrors()) {
			return "wizard2";
		}
		model.addAttribute("customer", customer);
		return "wizard3";
	}

	@RequestMapping(value = "finish", method = RequestMethod.POST)
	public String finish(Model model, @ModelAttribute("customer") Customer customer, SessionStatus sessionStatus,
			BindingResult result) {
		validator.validatePage3(customer, result);
		if (result.hasErrors()) {
			return "wizard3";
		}
		model.addAttribute("customer", customer);
		sessionStatus.setComplete();
		return "finish";
	}
}
