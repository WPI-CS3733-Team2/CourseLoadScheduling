package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.ModifyCourse;
import org.dselent.scheduling.server.requests.SearchCourse;
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

}

	