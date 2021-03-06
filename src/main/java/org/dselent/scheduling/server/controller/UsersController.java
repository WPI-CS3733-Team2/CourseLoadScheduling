package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.DeleteUser;
import org.dselent.scheduling.server.requests.DislinkFacultyWithSection;
import org.dselent.scheduling.server.requests.GetFacultyCalendars;
import org.dselent.scheduling.server.requests.LinkFacultyWithSection;
import org.dselent.scheduling.server.requests.Login;
import org.dselent.scheduling.server.requests.PasswordModification;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.UserSearch;
import org.dselent.scheduling.server.requests.ViewUserOfRoleId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
public interface UsersController
{
    
    @RequestMapping(method=RequestMethod.POST, value=Register.REQUEST_NAME)
	public ResponseEntity<String> register(@RequestBody Map<String, Object> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=DeleteUser.REQUEST_NAME)
    public ResponseEntity<String> delete(@RequestBody Map<String, Object> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Login.REQUEST_NAME)
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=PasswordModification.REQUEST_NAME)
    public ResponseEntity<String> passwordModification(@RequestBody Map<String, Object> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=UserSearch.REQUEST_NAME)
    public ResponseEntity<String> userSearch(@RequestBody Map<String, Object> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=ViewUserOfRoleId.REQUEST_NAME)
    public ResponseEntity<String> viewUserOfRoleId(@RequestBody Map<String, Integer> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=GetFacultyCalendars.REQUEST_NAME)
    public ResponseEntity<String> getfacultyCalendars(@RequestBody Map<String, Integer> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=LinkFacultyWithSection.REQUEST_NAME)
    public ResponseEntity<String> linkFacultyWithSection(@RequestBody Map<String, Object> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=DislinkFacultyWithSection.REQUEST_NAME)
    public ResponseEntity<String> dislinkFacultyWithSection(@RequestBody Map<String, Object> request) throws Exception;
}

	