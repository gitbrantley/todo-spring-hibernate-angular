package com.bw.web.todo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bw.dao.TodoDao;
import com.bw.dao.criteria.TodoCriteria;
import com.bw.hibernate.entity.Author;
import com.bw.hibernate.entity.Todo;
import com.bw.web.todo.dto.JsonResponse;
import com.bw.web.todo.security.SecurityUtils;
import com.bw.web.todo.security.TodoUser;

@Controller
@RequestMapping("/rest")
public class TodoJsonController {
	
	private static Logger logger = Logger.getLogger(TodoJsonController.class);
	@Autowired
	private TodoDao todoDao;
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	public @ResponseBody JsonResponse listTodos() {
		TodoCriteria cri = new TodoCriteria();
		TodoUser user = SecurityUtils.getUser();
		if (user != null) {
			cri.setAuthorId(user.getAuthorId());
		}
		List<Todo> todos = todoDao.list(cri);
		logger.debug("Returning list of "+todos.size()+" for user "+(user != null ? user.getUsername() : "ANON"));
		return new JsonResponse(true, todos);
	}
	
	public static class TodoList extends ArrayList<Todo> { private static final long serialVersionUID = -1416184693732853768L;}
	
	@RequestMapping(value="/todos", method=RequestMethod.PUT)
	public @ResponseBody JsonResponse updateTodos(@RequestBody TodoList list) {
		Author auth = new Author();
		auth.setId(SecurityUtils.getUser().getAuthorId());
		for (Todo todo : list) {
			todo.setAuthor(auth);
			if (todo.getId() > 0) {
				logger.debug("Saving ["+todo.getName()+"]");
				todoDao.update(todo);
			} else {
				logger.debug("Missing id for todo ["+todo.getName()+"] creating instead");
				todoDao.create(todo);
			}
				
		}
		return new JsonResponse(true, null);
	}
	
	@RequestMapping(value="/todos", method=RequestMethod.POST)
	public @ResponseBody JsonResponse createTodos(@RequestBody TodoList list) {
		Author auth = new Author();
		auth.setId(SecurityUtils.getUser().getAuthorId());
		for (Todo todo : list) {
			todo.setAuthor(auth);
			todoDao.create(todo);
		}
		return new JsonResponse(true, null);
	}
}
