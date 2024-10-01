package com.systex.homework4.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.systex.homework4.model.Person;
import com.systex.homework4.model.PersonRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	PersonRepository personRepository;
	
	@GetMapping("/")
	private String redirectToLogin() {
        return "login";
    }
	
	@GetMapping("lottery/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/lottery/loginConfirm")
	private String login(HttpServletRequest request, HttpSession session) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		List<Person> allPerson = (List<Person>)this.personRepository.findAll();
		for (Person person : allPerson) {
			if (person.getUserName().equals(userName)) {
				if (person.getPassword().equals(password)) {
					session.setAttribute("authUserName", userName);
					return "lottery/lottery";
				}
			}
		}
		session.setAttribute("userName", userName);
		session.setAttribute("password", password);
		session.setAttribute("loginError", "帳號或密碼有誤!!");
		return "redirect:/";
	}
	
	@GetMapping("/register")
	private ModelAndView register() {
		return new ModelAndView("register", "command", new Person());
	}
	
	@PostMapping("/addPerson")
	private String addPerson(@ModelAttribute Person person,
			HttpServletRequest request, HttpSession session) {
		String confirmPassword = request.getParameter("confirmPassword").toString();
		LinkedList<String> errorMsgs = new LinkedList<>();
		session.setAttribute("errors", errorMsgs);
		if (person == null) {
			errorMsgs.add("帳號或密碼不可空白!!");
			return "redirect:/register";
		}
		session.setAttribute("registerUserName", person.getUserName());
		session.setAttribute("registerPassword", person.getPassword());
		session.setAttribute("confirmPassword", confirmPassword);
		if (person.getUserName().isEmpty() || person.getPassword().isEmpty()) {
			errorMsgs.add("帳號或密碼不可空白!!");
		}
		if (!person.getPassword().equals(confirmPassword)) {
			errorMsgs.add("確認密碼有誤!!");
		}
		if (errorMsgs.size() != 0) {
			return "redirect:/register";
		}
		this.personRepository.save(person);
		session.setAttribute("successMessage", "註冊成功!!");
		return "redirect:/"; 
	}
}
