package org.dselent.scheduling.server.controller.impl;

import java.util.Map;

import org.dselent.scheduling.server.controller.CourseLoadController;
import org.dselent.scheduling.server.service.CourseLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class CourseLoadControllerImpl implements CourseLoadController
{
	@Autowired
    private CourseLoadService courseLoadService;

//	@Override
//	public ResponseEntity<String> delete(Map<String, Object> request) throws Exception {
//		Integer courseLoadId =(int) request.get("id");
//		courseLoadService.delete(courseLoadId);
//		return null;
//	}

	@Override
	public ResponseEntity<String> detail(Map<String, Object> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    	
}

	