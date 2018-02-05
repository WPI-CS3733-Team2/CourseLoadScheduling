package org.dselent.scheduling.server.controller.impl;

import org.dselent.scheduling.server.controller.RequestController;
import org.dselent.scheduling.server.service.RequestService;
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
public class RequestControllerImpl implements RequestController
{
	@Autowired
    private RequestService requestService;
    	
}

	