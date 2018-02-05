package org.dselent.scheduling.server.controller.impl;

import org.dselent.scheduling.server.controller.CourseController;
import org.dselent.scheduling.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CourseControllerImpl implements CourseController
{
	@Autowired
    private CourseService courseService;
    	
}

	