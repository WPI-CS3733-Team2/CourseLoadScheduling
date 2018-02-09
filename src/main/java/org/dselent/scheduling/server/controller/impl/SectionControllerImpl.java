package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.SectionController;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.ModifySectionCalendarDto;
import org.dselent.scheduling.server.dto.ModifySectionTypeNamePopDto;
import org.dselent.scheduling.server.dto.ViewSectionCalendarsOfCourseDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.SelectSection;
import org.dselent.scheduling.server.requests.ViewSectionCalendarsOfCourse;
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
	public ResponseEntity<String> create_section(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		//List<Object> success = new ArrayList<Object>();
		
		Integer crn =(Integer) request.get(CreateSection.getBodyName(CreateSection.BodyKey.CRN));
		String name =(String) request.get(CreateSection.getBodyName(CreateSection.BodyKey.NAME));
		String type =(String) request.get(CreateSection.getBodyName(CreateSection.BodyKey.TYPE));
		Integer expected_population =(Integer) request.get(CreateSection.getBodyName(CreateSection.BodyKey.EXPECTED_POPULATION));
		Integer course_id =(Integer) request.get(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_ID));
		Integer schedule_id =(Integer) request.get(CreateSection.getBodyName(CreateSection.BodyKey.SCHEDULE_ID));
		Integer year =(Integer) request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.YEAR));
		String semester =(String) request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.SEMESTER));
		String days =(String) request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.DAYS));
		String start_time = (String)request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.START_TIME));
		String end_time = (String)request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.END_TIME));
		
		CreateSectionDto.Builder builder = CreateSectionDto.builder();
		CreateSectionDto createSectionDto = builder.withCrn(crn)
		.withName(name)
		.withType(type)
		.withExpectedPopulation(expected_population)
		.withCourseId(course_id)
		.withScheduleId(schedule_id)
		.withYear(year)
		.withSemester(semester)
		.withDays(days)
		.withStartTime(start_time)
		.withEndTime(end_time)
		.build();
		
		//;
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, sectionService.create_section(createSectionDto));

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> remove_section(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		//List<Object> success = new ArrayList<Object>();
		
		Integer id = (Integer) request.get(RemoveSection.getBodyName(RemoveSection.BodyKey.ID));
		
		//success.add((sectionService.remove_section(id);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, sectionService.remove_section(id));

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> select_section(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		//List<Object> success = new ArrayList<Object>();
		
		Integer id = (Integer) request.get(SelectSection.getBodyName(SelectSection.BodyKey.ID));
		
		//success.add((sectionService.remove_section(id);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, sectionService.remove_section(id));

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	
	public ResponseEntity<String> modify_section_calendar(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		//List<Object> success = new ArrayList<Object>();
		
		Integer id = (Integer)request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.ID));
		Integer year = (Integer)request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.YEAR));
		String semester =(String) request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.SEMESTER));
		String days =(String) request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.DAYS));
		String start_time =(String) request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.START_TIME));
		String end_time = (String)request.get(ModifySectionCalendar.getBodyName(ModifySectionCalendar.BodyKey.END_TIME));
		
		ModifySectionCalendarDto.Builder builder = ModifySectionCalendarDto.builder();
		ModifySectionCalendarDto modifySectionCalendarDto = builder.withId(id)
		.withYear(year)
		.withSemester(semester)
		.withDays(days)
		.withStartTime(start_time)
		.withEndTime(end_time)
		.build();
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, sectionService.modify_section_calendar(modifySectionCalendarDto));

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> modify_section_schedule(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		//List<Object> success = new ArrayList<Object>();
		
		Integer id = (Integer)request.get(ModifySectionSchedule.getBodyName(ModifySectionSchedule.BodyKey.ID));
		Integer schedule_id = (Integer)request.get(ModifySectionSchedule.getBodyName(ModifySectionSchedule.BodyKey.SCHEDULE_ID));
		
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, sectionService.modify_section_schedule(id, schedule_id));

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	public ResponseEntity<String> modify_section_type_name_pop(@RequestBody Map<String, Object> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Integer id = (Integer)request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.ID));
		String type = (String)request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.TYPE));
		String name = (String)request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.NAME));
		Integer expected_population = (Integer) request.get(ModifySectionTypeNamePop.getBodyName(ModifySectionTypeNamePop.BodyKey.EXPECTED_POPULATION));
		
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
	
	@Override
	public ResponseEntity<String> view_section_calendars_of_course(Map<String, Object> request)
			throws Exception {
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		Integer course_id = (Integer) request.get(ViewSectionCalendarsOfCourse.getBodyName(ViewSectionCalendarsOfCourse.BodyKey.COURSE_ID));

		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS,
				sectionService.view_section_calendars_of_course(course_id));

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

    	
}

	