package com.bw.web.sandbox;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	//private static Logger logger = Logger.getLogger(MainController.class);
	
	@RequestMapping("/main.html")
	public ModelAndView hello() {
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		model.put("message", "Welcome!");
		
		return new ModelAndView("main", model);
	}
	
	
	
}
