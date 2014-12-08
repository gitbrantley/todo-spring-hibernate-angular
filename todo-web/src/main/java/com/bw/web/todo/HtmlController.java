package com.bw.web.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HtmlController {
	
	@RequestMapping("/todo-angular.html")
	public ModelAndView todoAngular() {
		return new ModelAndView("todos-angular");
	}
}
