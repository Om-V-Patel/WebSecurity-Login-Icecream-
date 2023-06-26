package com.om.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;




@Controller

public class AppController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	ContactRepository contRepo;


	@GetMapping("")
	public String viewHomePage() {
		return "index.html";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";

	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}


	@GetMapping("/home")
	public String showpage() {
		return "successor";
	}
	
	@RequestMapping("/AboutUs")
	public String showAboutpage() {
		return "AboutUs";	
	}
	
	@GetMapping("/Contact")
	public String showcontactForm(Model model) {
		model.addAttribute("contact", new Contact());
		return "Contact";
	}
	
	@PostMapping("/insertdata")
	public String adddata(Contact contact) {
			System.out.print(contact);
		contRepo.save(contact);

		return "register_success";
	}

	

}

