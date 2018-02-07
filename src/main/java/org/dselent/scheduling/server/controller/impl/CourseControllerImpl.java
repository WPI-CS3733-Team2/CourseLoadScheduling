package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.CourseController;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.DeleteCourseDto;
import org.dselent.scheduling.server.dto.ModifyCourseDto;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.dto.SearchCourseDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.DeleteCourse;
import org.dselent.scheduling.server.requests.ModifyCourse;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.SearchCourse;
import org.dselent.scheduling.server.service.CourseService;
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
public class CourseControllerImpl implements CourseController
{
	@Autowired
    private CourseService courseService;
	
	@Override
	public ResponseEntity<String> createCourse(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String name = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NAME));
		String number = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER));
		String frequency = request.get(CreateCourse.getBodyName(CreateCourse.BodyKey.FREQUENCY));
		
		CreateCourseDto.Builder builder = CreateCourseDto.builder();
		CreateCourseDto createCourseDto = builder.withName(name)
		.withNumber(number)
		.withFrequency(frequency)
		.build();
		
		courseService.createCourse(createCourseDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<String> modifyCourse(@RequestBody Map<String, String> request) throws Exception {
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		String name = request.get(ModifyCourse.getBodyName(ModifyCourse.BodyKey.NAME));
		String number = request.get(ModifyCourse.getBodyName(ModifyCourse.BodyKey.NUMBER));
		String frequency = request.get(ModifyCourse.getBodyName(ModifyCourse.BodyKey.FREQUENCY));
		String id = request.get(ModifyCourse.getBodyName(ModifyCourse.BodyKey.ID));
		
		ModifyCourseDto.Builder builder = ModifyCourseDto.builder();
		ModifyCourseDto modifyCourseDto = builder.withName(name)
				.withNumber(number)
				.withFrequency(frequency)
				.withId(id)
				.build();

		courseService.modifyCourse(modifyCourseDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> searchCourse(@RequestBody Map<String, String> request) throws Exception {
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";

		String name = request.get(SearchCourse.getBodyName(SearchCourse.BodyKey.NAME));
		String number = request.get(SearchCourse.getBodyName(SearchCourse.BodyKey.NUMBER));
		String frequency = request.get(SearchCourse.getBodyName(SearchCourse.BodyKey.FREQUENCY));
		String id = request.get(SearchCourse.getBodyName(SearchCourse.BodyKey.ID));

		SearchCourseDto.Builder builder = SearchCourseDto.builder();
		SearchCourseDto searchCourseDto = builder.withName(name)
				.withNumber(number)
				.withFrequency(frequency)
				.withId(id)
				.build();

		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, courseService.searchCourse(searchCourseDto));

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteCourse(Map<String, String> request) throws Exception {
		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String number = request.get(DeleteCourse.getBodyName(DeleteCourse.BodyKey.NUMBER));
		String name = request.get(DeleteCourse.getBodyName(DeleteCourse.BodyKey.NAME));
		
		DeleteCourseDto.Builder builder = DeleteCourseDto.builder();
		DeleteCourseDto deleteCourseDto = builder.withName(name)
		.withNumber(number)
		.build();
		
		courseService.deleteCourse(deleteCourseDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
    	
}

	