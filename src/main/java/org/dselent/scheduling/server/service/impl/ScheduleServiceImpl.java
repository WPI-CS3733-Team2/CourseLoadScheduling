package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.ScheduleDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.service.ScheduleService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService
{
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private CustomDao customDao;
	
    public ScheduleServiceImpl()
    {
    	//
    }

	@Override
	public Schedule create(Integer facultyId, String scheduleName) throws SQLException {
		
		Schedule schedule = new Schedule();
		
		schedule.setFacultyId(facultyId);
		schedule.setScheduleName(scheduleName);
		
		List<String> scheduleInsertColumnNameList = new ArrayList<>();
		List<String> scheduleKeyHolderColumnNameList = new ArrayList<>();
		
		scheduleInsertColumnNameList.add(Schedule.getColumnName(Schedule.Columns.FACULTY_ID));
		scheduleInsertColumnNameList.add(Schedule.getColumnName(Schedule.Columns.SCHEDULE_NAME));
		
		scheduleKeyHolderColumnNameList.add(Schedule.getColumnName(Schedule.Columns.ID));
		scheduleKeyHolderColumnNameList.add(Schedule.getColumnName(Schedule.Columns.CREATED_AT));
		
		// I don't think I need the return value right now
		scheduleDao.insert(schedule, scheduleInsertColumnNameList, scheduleKeyHolderColumnNameList);
		
		return schedule;
	}
	

	@Override
	public List<Schedule> view(Integer scheduleId) throws SQLException {
		List<Schedule> affectedSchedules = new ArrayList<>();
		
		Schedule schedule = new Schedule();
		
		schedule.setId(scheduleId);
		
		List<String> columnNames = new ArrayList<>();
		
		columnNames.addAll(Schedule.getColumnNameList());
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		
		if (schedule.getId() != null) 
		{
			String queryColumnName = Schedule.getColumnName(Schedule.Columns.ID);
			QueryTerm queryTerm = new QueryTerm(queryColumnName,ComparisonOperator.EQUAL,schedule.getId(),null);
			queryTermList.add(queryTerm);
		}
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderBy = new Pair<String, ColumnOrder>(Schedule.getColumnName(Schedule.Columns.ID), ColumnOrder.ASC);
		orderByList.add(orderBy);
		
		affectedSchedules = scheduleDao.select(columnNames, queryTermList, orderByList);
		return affectedSchedules;
	}

	@Override
	public Schedule findWithFacultyId(Integer facultyId) throws SQLException {
		return scheduleDao.findByFacultyId(facultyId);
	}
    
	@Override
	public List<Schedule> search(String searchBy, String searchTerm) throws SQLException{
		List<Schedule> scheduleList = new ArrayList();
		if (searchBy.contentEquals("faculty")) {
			List<Faculty> facultyList = customDao.getFacultyIDFromUserSearch(searchTerm);
			for (Faculty faculty : facultyList) {
				scheduleList.add(scheduleDao.findByFacultyId(faculty.getId()));
			}
		}
		else if (searchBy.contentEquals("course")) {
			scheduleList = customDao.getScheduleFromCourseSearch(searchTerm);
		}
		else if (searchBy.contentEquals("semester")) {
			scheduleList = customDao.getScheduleFromCalendarSearch(searchTerm);
		}
		else if (searchBy.contentEquals("name")) {
			scheduleList = customDao.getScheduleFromName(searchTerm);
		}
		else { //all will be returned
			scheduleList = scheduleDao.getAll();
		}
		return scheduleList;
	}
}
