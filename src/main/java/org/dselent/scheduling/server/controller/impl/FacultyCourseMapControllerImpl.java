package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.FacultyCourseMapController;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.FacultyCourseMap;
import org.dselent.scheduling.server.requests.SearchCourseLoad;
import org.dselent.scheduling.server.service.FacultyCourseMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class FacultyCourseMapControllerImpl implements FacultyCourseMapController
{
	@Autowired
    private FacultyCourseMapService facultyCourseMapService;
	

	public ResponseEntity<String> facultyCourseMap(@RequestBody Map<String, String> request) throws Exception{
		System.out.println("controller reached");
		String response = "";
		String test = request.get(FacultyCourseMap.getBodyName(FacultyCourseMap.BodyKey.TEST));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, facultyCourseMapService.facultyCourseMap());

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}

	