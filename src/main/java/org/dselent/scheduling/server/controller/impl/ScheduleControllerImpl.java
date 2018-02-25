package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.ScheduleController;
import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.httpReturnObject.UserWithScheduleInfo;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.requests.CreateSchedule;
import org.dselent.scheduling.server.requests.ScheduleSpecifics;
import org.dselent.scheduling.server.requests.SearchSchedule;
import org.dselent.scheduling.server.requests.ViewSchedule;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class ScheduleControllerImpl implements ScheduleController
{
	@Autowired
    private ScheduleService scheduleService;
	
	@Autowired
	private SectionsDao sectionsDao;
	
	@Autowired
	private FacultyDao facultyDao;
	
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception{
		System.out.println("controller reached");
		    	
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String facultyId = request.get(CreateSchedule.getBodyName(CreateSchedule.BodyKey.FACULTY_ID));
		String scheduleName = request.get(CreateSchedule.getBodyName(CreateSchedule.BodyKey.SCHEDULE_NAME));
		String sectionIdsCSV = request.get(CreateSchedule.getBodyName(CreateSchedule.BodyKey.SECTION_IDS));
		Schedule createdSchedule = scheduleService.create(Integer.parseInt(facultyId), scheduleName);
		
		String [] idArray = sectionIdsCSV.replace("[", "").replace("]", "").replace(" ", "").split(",");
		List<String> sectionIds = Arrays.asList(idArray);
		
		for(String id: sectionIds) {
			Integer numId = Integer.parseInt(id);
			
			List<QueryTerm> queryTermList = new ArrayList<>();
			String queryColumnName = Section.getColumnName(Section.Columns.ID);
			QueryTerm IdTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, numId, null);
			queryTermList.add(IdTerm);

			String updateColumnName = Section.getColumnName(Section.Columns.SCHEDULE_ID);
			sectionsDao.update(updateColumnName, createdSchedule.getId(), queryTermList);
			
		}
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Faculty.getColumnName(Faculty.Columns.ID);
		QueryTerm IdTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, Integer.parseInt(facultyId), null);
		queryTermList.add(IdTerm);
		String updateColumnName = Faculty.getColumnName(Faculty.Columns.ASSIGNED);
		facultyDao.update(updateColumnName, true, queryTermList);
		
		UserWithScheduleInfo user = scheduleService.specifics(createdSchedule.getId());
		success.add(user);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> view(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
								    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String scheduleIdString = request.get(ViewSchedule.getParameterName(ViewSchedule.ParameterKey.SCHEDULE_ID));
		Integer scheduleId = Integer.parseInt(scheduleIdString);
		
		List<Schedule> scheduleList = scheduleService.view(scheduleId);
		success.add(scheduleList);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> search(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
								    	
		// add any objects that need to be returned to the success list
		String response = "";
		
		String searchBy = request.get(SearchSchedule.getParameterName(SearchSchedule.ParameterKey.SEARCH_BY));
		String searchTerm = request.get(SearchSchedule.getParameterName(SearchSchedule.ParameterKey.SEARCH_TERM));
		
		List<Schedule> scheduleList = scheduleService.search(searchBy, searchTerm);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, scheduleList);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<String> specifics(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
								    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Integer scheduleId = Integer.parseInt(request.get(ScheduleSpecifics.getParameterName(ScheduleSpecifics.ParameterKey.ID)));
		
		UserWithScheduleInfo user = scheduleService.specifics(scheduleId);
		success.add(user);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
    	
}

	