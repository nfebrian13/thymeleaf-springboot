package com.springboot.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.springboot.entity.Person;
import com.springboot.validation.PersonValidator;

@Controller
@RequestMapping("person")
@SessionAttributes("person")
public class PersonController {

	@Autowired
	PersonValidator validator;

	@RequestMapping(value = "form")
	public String index(Model model) {
		model.addAttribute("person", new Person());
		return "person/form-1";
	}
	
	@RequestMapping(value = "success")
	public String indexSucces(Model model) {
		return "person/finish";
	}

	@RequestMapping(value = "form", method = RequestMethod.POST)
	public String submitForm(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("person") Person person, BindingResult result, SessionStatus status,
			@RequestParam("_page") int currentPage, Model model) {

		Map<Integer, String> pageForms = new HashMap<Integer, String>();
		pageForms.put(0, "person/form-1");
		pageForms.put(1, "person/form-2");

		if (userClickedCancel(request)) {
			status.setComplete();
			return "redirect:/person/form-1";
		} else if (userIsFinished(request)) {
			validator.validate(person, result);
			if (result.hasErrors()) {
				return pageForms.get(currentPage);
			} else {
				person.setRegistrationComplete(true);
				return "redirect:/person/success";
			}
		} else {
			int targetPage = getTargetPage(request, "_target", currentPage);
			if (userClickedPrevious(currentPage, targetPage)) {
				return pageForms.get(targetPage);
			} else {
				switch (currentPage) {
				case 0:
					validator.validate(person, result);
					break;
				}

				if (result.hasErrors()) {
					return pageForms.get(currentPage);
				} else {
					return pageForms.get(targetPage);
				}
			}
		}
	}

	public static int getTargetPage(ServletRequest request, String paramPrefix, int currentPage) {
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if (paramName.startsWith(paramPrefix)) {
				for (int i = 0; i < WebUtils.SUBMIT_IMAGE_SUFFIXES.length; i++) {
					String suffix = WebUtils.SUBMIT_IMAGE_SUFFIXES[i];
					if (paramName.endsWith(suffix)) {
						paramName = paramName.substring(0, paramName.length() - suffix.length());
					}
				}
				return Integer.parseInt(paramName.substring(paramPrefix.length()));
			}
		}
		return currentPage;
	}

	private boolean userClickedPrevious(int currentPage, int targetPage) {
		return targetPage < currentPage;
	}

	private boolean userIsFinished(HttpServletRequest request) {
		return request.getParameter("_finish") != null;
	}

	private boolean userClickedCancel(HttpServletRequest request) {
		return request.getParameter("_cancel") != null;
	}

	private boolean registrationHasBeenCompleted(HttpSession session) {
		Person person = getRegistrationFromSession(session);
		return person != null && person.isRegistrationComplete();
	}

	private Person getRegistrationFromSession(HttpSession session) {
		return (Person) session.getAttribute("person");
	}

}
