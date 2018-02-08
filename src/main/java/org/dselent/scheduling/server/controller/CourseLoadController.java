package org.dselent.scheduling.server.controller;

import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.dselent.scheduling.server.requests.SearchCourseLoad;

@RequestMapping("/course_load")
public interface CourseLoadController
{
//	@RequestMapping(method=RequestMethod.POST, value="delete")
//    public ResponseEntity<String> delete(@RequestBody Map<String, Object> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value = SearchCourseLoad.REQUEST_NAME)
	public ResponseEntity<String> search(@RequestBody Map<String, Object> request) throws Exception;
	
}

	