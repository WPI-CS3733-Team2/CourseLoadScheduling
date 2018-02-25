package org.dselent.scheduling.server.controller;

import java.util.Map;

//import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.CreateRequest;
import org.dselent.scheduling.server.requests.ViewFacultiesInfo;
import org.dselent.scheduling.server.requests.ViewRequestHistory;
import org.dselent.scheduling.server.requests.ChangeRequestState;
import org.dselent.scheduling.server.requests.ViewPendingRequests;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/request")
public interface RequestController
{
	@RequestMapping(method=RequestMethod.POST, value=CreateRequest.REQUEST_NAME)
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ViewRequestHistory.REQUEST_NAME)
	public ResponseEntity<String> history(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ChangeRequestState.REQUEST_NAME)
	public ResponseEntity<String> changeState(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=ViewPendingRequests.REQUEST_NAME)
	public ResponseEntity<String> viewPending(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=ViewFacultiesInfo.REQUEST_NAME)
	public ResponseEntity<String> getFacultisInfo(@RequestBody Map<String, String> request) throws Exception;
}

	