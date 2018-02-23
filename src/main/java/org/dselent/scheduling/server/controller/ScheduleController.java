package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateSchedule;
import org.dselent.scheduling.server.requests.SearchSchedule;
import org.dselent.scheduling.server.requests.ViewSchedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/schedule")
public interface ScheduleController
{
	@RequestMapping(method=RequestMethod.POST, value=CreateSchedule.REQUEST_NAME)
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ViewSchedule.REQUEST_NAME)
	public ResponseEntity<String> view(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=SearchSchedule.REQUEST_NAME)
	public ResponseEntity<String> search(@RequestBody Map<String, String> request) throws Exception;
}

	