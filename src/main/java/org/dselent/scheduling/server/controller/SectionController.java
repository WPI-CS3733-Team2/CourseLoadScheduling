package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.ModifySectionCalendar;
import org.dselent.scheduling.server.requests.ModifySectionSchedule;
import org.dselent.scheduling.server.requests.ModifySectionTypeNamePop;
import org.dselent.scheduling.server.requests.RemoveSection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/section")
public interface SectionController
{
	@RequestMapping(method=RequestMethod.POST, value=CreateSection.REQUEST_NAME)
	public ResponseEntity<String> create_section(@RequestBody Map<String, Object> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=RemoveSection.REQUEST_NAME)
	public ResponseEntity<String> remove_section(@RequestBody Map<String,Object> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ModifySectionCalendar.REQUEST_NAME)
	public ResponseEntity<String> modify_section_calendar(@RequestBody Map<String, Object> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ModifySectionSchedule.REQUEST_NAME)
	public ResponseEntity<String> modify_section_schedule(@RequestBody Map<String, Object> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ModifySectionTypeNamePop.REQUEST_NAME)
	public ResponseEntity<String> modify_section_type_name_pop(@RequestBody Map<String, Object> request) throws Exception;
}

	