package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.ScheduleController;
import org.dselent.scheduling.server.dto.ViewScheduleDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateSchedule;
import org.dselent.scheduling.server.requests.ViewSchedule;
import org.dselent.scheduling.server.service.ScheduleService;
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
public class ScheduleControllerImpl implements ScheduleController
{
	@Autowired
    private ScheduleService scheduleService;
	
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
		    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String facultyId = request.get(CreateSchedule.getBodyName(CreateSchedule.BodyKey.FACULTY_ID));
		String scheduleName = request.get(CreateSchedule.getBodyName(CreateSchedule.BodyKey.SCHEDULE_NAME));
		
//		CreateScheduleDto.Builder builder = CreateScheduleDto.builder();
//		CreateScheduleDto createScheduleDto = builder.withFacultyId(facultyId)
//		.withScheduleName(scheduleName)
//		.build();
		
		scheduleService.create(Integer.parseInt(facultyId), scheduleName);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> view(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
								    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String scheduleId = request.get(ViewSchedule.getParameterName(ViewSchedule.ParameterKey.SCHEDULE_NAME));
		
		ViewScheduleDto.Builder builder = ViewScheduleDto.builder();
		ViewScheduleDto viewScheduleDto = builder.withScheduleId(scheduleId)
		.build();
		
		scheduleService.view(viewScheduleDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
    	
}

	