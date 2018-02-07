package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.RequestController;
//import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.dto.CreateRequestDto;
//import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.CreateRequest;
import org.dselent.scheduling.server.requests.ViewRequestHistory;
import org.dselent.scheduling.server.requests.ChangeRequestState;
import org.dselent.scheduling.server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;

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
	
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
		    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String facultyId = request.get(CreateRequest.getParameterName(CreateRequest.ParameterKey.FACULTY_ID));
		String requestType = request.get(CreateRequest.getBodyName(CreateRequest.BodyKey.REQUEST_TYPE));
		//String requestState = request.get(CreateRequest.getBodyName(CreateRequest.BodyKey.REQUEST_STATE));
		String courseId = request.get(CreateRequest.getBodyName(CreateRequest.BodyKey.COURSE_ID));
		String sectionId = request.get(CreateRequest.getBodyName(CreateRequest.BodyKey.SECTION_ID));
		String data = request.get(CreateRequest.getBodyName(CreateRequest.BodyKey.DATA));
		
		CreateRequestDto.Builder builder = CreateRequestDto.builder();
		CreateRequestDto createRequestDto = builder.withFacultyId(facultyId)
		.withRequestType(requestType)
		//.withRequestState(requestState)
		.withCourseId(courseId)
		.withSectionId(sectionId)
		.withData(data)
		.build();
		
		requestService.createRequest(createRequestDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> history(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
				    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
				
		String facultyId = request.get(ViewRequestHistory.getParameterName(ViewRequestHistory.ParameterKey.FACULTY_ID));
		
		//Since there's only one data value, this may not need a DTO.
		//Maybe change later.
		/*CreateRequestDto.Builder builder = CreateRequestDto.builder();
		CreateRequestDto viewRequestHistoryDto = builder.withFacultyId(facultyId)
		.build();*/
				
		requestService.viewRequestHistory(Integer.parseInt(facultyId));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> changeState(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
						    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
						
		String requestId = request.get(ChangeRequestState.getBodyName(ChangeRequestState.BodyKey.REQUEST_ID));
		String requestState = request.get(ChangeRequestState.getBodyName(ChangeRequestState.BodyKey.REQUEST_STATE));
				
		//Since there's only one data value, this may not need a DTO.
		//Maybe change later.
		/*CreateRequestDto.Builder builder = CreateRequestDto.builder();
		CreateRequestDto viewRequestHistoryDto = builder.withFacultyId(facultyId)
		.build();*/
						
		requestService.changeRequestState(Integer.parseInt(requestId),Integer.parseInt(requestState));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> viewPending(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
								    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}

	