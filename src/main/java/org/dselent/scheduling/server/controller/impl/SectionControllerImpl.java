package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SectionController;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.ModifySectionCalendarDto;
import org.dselent.scheduling.server.dto.ModifySectionTypeNamePopDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.ModifySectionCalendar;
import org.dselent.scheduling.server.requests.ModifySectionSchedule;
import org.dselent.scheduling.server.requests.ModifySectionTypeNamePop;
import org.dselent.scheduling.server.requests.RemoveSection;
import org.dselent.scheduling.server.service.SectionService;
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
public class SectionControllerImpl implements SectionController
{
	@Autowired
    private SectionService sectionService;
	
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */
	public ResponseEntity<String> create_section(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String crn = request.get(CreateSection.getBodyName(CreateSection.BodyKey.CRN));
		String name = request.get(CreateSection.getBodyName(CreateSection.BodyKey.NAME));
		String type = request.get(CreateSection.getBodyName(CreateSection.BodyKey.TYPE));
		String expected_population = request.get(CreateSection.getBodyName(CreateSection.BodyKey.EXPECTED_POPULATION));
		String course_id = request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_ID));
		String calendar_id = request.get(CreateSection.getBodyName(CreateSection.BodyKey.CALENDAR_ID));
		String schedule_id = request.get(CreateSection.getBodyName(CreateSection.BodyKey.SCHEDULE_ID));
		
		CreateSectionDto.Builder builder = CreateSectionDto.builder();
		CreateSectionDto createSectionDto = builder.withCrn(crn)
		.withName(name)
		.withType(type)
		.withExpectedPopulation(expected_population)
		.withCourseId(course_id)
		.withCalendarId(calendar_id)
		.withScheduleId(schedule_id)
		.build();
		
		sectionService.create_section(createSectionDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> remove_section(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String id = request.get(RemoveSection.getBodyName(RemoveSection.BodyKey.ID));
		
		sectionService.remove_section(id);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> modify_section_calendar(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String id = request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.ID));
		String year = request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.YEAR));
		String semester = request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.SEMESTER));
		String days = request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.DAYS));
		String start_time = request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.START_TIME));
		String end_time = request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.END_TIME));
		
		ModifySectionCalendarDto.Builder builder = ModifySectionCalendarDto.builder();
		ModifySectionCalendarDto modifySectionCalendarDto = builder.withId(id)
		.withYear(year)
		.withSemester(semester)
		.withDays(days)
		.withStartTime(start_time)
		.withEndTime(end_time)
		.build();
		
		sectionService.modify_section_calendar(modifySectionCalendarDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> modify_section_schedule(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String id = request.get(ModifySectionSchedule.getBodyName(ModifySectionSchedule.BodyKey.ID));
		String schedule_id = request.get(ModifySectionSchedule.getBodyName(ModifySectionSchedule.BodyKey.SCHEDULE_ID));
		
		sectionService.modify_section_schedule(id, schedule_id);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> modify_section_type_name_pop(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String id = request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.ID));
		String type = request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.TYPE));
		String name = request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.NAME));
		String expected_population = request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.EXPECTED_POPULATION));
		
		ModifySectionTypeNamePopDto.Builder builder = ModifySectionTypeNamePopDto.builder();
		ModifySectionTypeNamePopDto modifySectionTypeNamePopDto = builder.withId(id)
		.withType(type)
		.withName(name)
		.withExpectedPopulation(expected_population)
		.build();
		
		sectionService.modify_section_type_name_pop(modifySectionTypeNamePopDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
    	
}

	