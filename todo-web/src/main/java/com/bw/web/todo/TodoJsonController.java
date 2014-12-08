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
import com.bw.hibernate.entity.Todo;
import com.bw.web.todo.dto.JsonResponse;

@Controller
public class TodoJsonController {
	
	private static Logger logger = Logger.getLogger(TodoJsonController.class);
	@Autowired
	private TodoDao todoDao;
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	public @ResponseBody JsonResponse listTodos() {
		List<Todo> todos = todoDao.list(new TodoCriteria());
		logger.debug("Returning list of "+todos.size());
		return new JsonResponse(true, todos);
	}
	
	public static class TodoList extends ArrayList<Todo> { private static final long serialVersionUID = -1416184693732853768L;}
	
	@RequestMapping(value="/todos", method=RequestMethod.PUT)
	public @ResponseBody JsonResponse updateTodos(@RequestBody TodoList list) {
		for (Todo todo : list) {
			if (todo.getId() > 0) {
				logger.debug("saving: "+todo.getName());
				todoDao.update(todo);
			} else {
				todoDao.create(todo);
			}
				
		}
		return new JsonResponse(true, null);
	}
}
