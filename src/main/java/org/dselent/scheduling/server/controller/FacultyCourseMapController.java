package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.FacultyCourseMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/faculty_course_map")
public interface FacultyCourseMapController
{
	@RequestMapping(method=RequestMethod.POST, value=FacultyCourseMap.REQUEST_NAME)
	public ResponseEntity<String> facultyCourseMap(@RequestBody Map<String, String> request) throws Exception;
}

	