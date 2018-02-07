package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.dselent.scheduling.server.dao.ScheduleDao;
import org.dselent.scheduling.server.dto.CreateScheduleDto;
import org.dselent.scheduling.server.dto.ViewScheduleDto;
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
	
    public ScheduleServiceImpl()
    {
    	//
    }

	@Override
	public int create(CreateScheduleDto createScheduleDto) throws SQLException {
		int rowAffected;
		
		Schedule schedule = new Schedule();
		
		schedule.setFacultyId(Integer.parseInt(createScheduleDto.getFacultyId()));
		schedule.setScheduleName(createScheduleDto.getScheduleName());
		
		List<String> scheduleInsertColumnNameList = new ArrayList<>();
		List<String> scheduleKeyHolderColumnNameList = new ArrayList<>();
		
		scheduleInsertColumnNameList.add(Schedule.getColumnName(Schedule.Columns.FACULTY_ID));
		scheduleInsertColumnNameList.add(Schedule.getColumnName(Schedule.Columns.SCHEDULE_NAME));
		
		scheduleKeyHolderColumnNameList.add(Schedule.getColumnName(Schedule.Columns.ID));
		scheduleKeyHolderColumnNameList.add(Schedule.getColumnName(Schedule.Columns.CREATED_AT));
		
		rowAffected = scheduleDao.insert(schedule, scheduleInsertColumnNameList, scheduleKeyHolderColumnNameList);
		return rowAffected;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Schedule> view(ViewScheduleDto viewScheduleDto) throws SQLException {
		List<Schedule> affectedSchedules = new ArrayList<>();
		
		Schedule schedule = new Schedule();
		
		schedule.setScheduleName(viewScheduleDto.getScheduleName());
		
		List<String> columnNames = new ArrayList<>();
		
		columnNames.addAll(Schedule.getColumnNameList());
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		
		if (schedule.getScheduleName() != null) 
		{
			String queryColumnName = Schedule.getColumnName(Schedule.Columns.SCHEDULE_NAME);
			QueryTerm queryTerm = new QueryTerm(queryColumnName,ComparisonOperator.EQUAL,schedule.getScheduleName(),null);
			queryTermList.add(queryTerm);
		}
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderBy = new Pair(Schedule.getColumnName(Schedule.Columns.ID), ColumnOrder.ASC);
		orderByList.add(orderBy);
		
		affectedSchedules = scheduleDao.select(columnNames, queryTermList, orderByList);
		return affectedSchedules;
	}
    
}
