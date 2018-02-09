package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.Register;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
public interface UsersController
{
    
    @RequestMapping(method=RequestMethod.POST, value=Register.REQUEST_NAME)
	public ResponseEntity<String> register(@RequestBody Map<String, Object> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value="delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, Object> request) throws Exception;
    
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;
    public ResponseEntity<String> passwordModification(@RequestBody Map<String, String> request) throws Exception;
    public ResponseEntity<String> userSearch(@RequestBody Map<String, String> request) throws Exception;
}

	