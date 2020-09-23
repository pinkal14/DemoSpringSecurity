package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class TemplateController {
    
	@RequestMapping("login")
	public String getLoginView()
	{
		return "login";
	}
	
	@RequestMapping("courses")
	public String getCoursesView()
	{
		return "courses";
	}
}
