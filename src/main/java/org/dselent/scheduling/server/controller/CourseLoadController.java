package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.Register;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/course_load")
public interface CourseLoadController
{
	@RequestMapping(method=RequestMethod.POST, value="view")
    public ResponseEntity<String> delete(@RequestBody Map<String, Object> request) throws Exception;
}

	