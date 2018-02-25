package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.DeleteCourse;
import org.dselent.scheduling.server.requests.GetCourseFaculties;
import org.dselent.scheduling.server.requests.ModifyCourse;
import org.dselent.scheduling.server.requests.SearchCourse;
import org.dselent.scheduling.server.requests.SearchUnassignedCourseSection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/course")
public interface CourseController
{
	@RequestMapping(method=RequestMethod.POST, value=CreateCourse.REQUEST_NAME)
	public ResponseEntity<String> createCourse(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=ModifyCourse.REQUEST_NAME)
	public ResponseEntity<String> modifyCourse(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=SearchCourse.REQUEST_NAME)
	public ResponseEntity<String> searchCourse(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=DeleteCourse.REQUEST_NAME)
	public ResponseEntity<String> deleteCourse(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=GetCourseFaculties.REQUEST_NAME)
	public ResponseEntity<String> getCourseFaculties(@RequestBody Map<String, String> request) throws Exception;
	

	@RequestMapping(method=RequestMethod.POST, value=SearchUnassignedCourseSection.REQUEST_NAME)
	public ResponseEntity<String> searchUnassignedCourseSection(@RequestBody Map<String, String> request) throws Exception;
}

	