package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dao.CalendarDao;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.ModifySectionCalendarDto;
import org.dselent.scheduling.server.dto.ModifySectionTypeNamePopDto;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.service.SectionService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService
{
	@Autowired
	private SectionsDao sectionsDao;
	private CalendarDao calendarDao;
	
    public SectionServiceImpl()
    {
    	//
    	
    }
	public int create_section(CreateSectionDto dto) throws SQLException
	{
		int rowAffected;
		
		Calendar calendar1 = new Calendar();
		
		calendar1.setYear(Integer.parseInt(dto.getYear()));
		calendar1.setSemester(dto.getSemester());
		calendar1.setDays(dto.getDays());
		calendar1.setStartTime(dto.getStart_time());
		calendar1.setEndTime(dto.getEnd_time());
		
		List<String> insertColumnNameList_cal = new ArrayList<>();
		List<String> keyHolderColumnNameList_cal = new ArrayList<>();
		
		insertColumnNameList_cal.add(Calendar.getColumnName(Calendar.Columns.YEAR));
		insertColumnNameList_cal.add(Calendar.getColumnName(Calendar.Columns.SEMESTER));
		insertColumnNameList_cal.add(Calendar.getColumnName(Calendar.Columns.DAYS));
		insertColumnNameList_cal.add(Calendar.getColumnName(Calendar.Columns.START_TIME));
		insertColumnNameList_cal.add(Calendar.getColumnName(Calendar.Columns.END_TIME));
		
		keyHolderColumnNameList_cal.add(Section.getColumnName(Section.Columns.ID));
		keyHolderColumnNameList_cal.add(Section.getColumnName(Section.Columns.CREATED_AT));
		
		int calendarId = calendarDao.insert(calendar1, insertColumnNameList_cal, keyHolderColumnNameList_cal);
	
    	Section section1 = new Section();
		section1.setCrn(Integer.parseInt(dto.getCrn()));
		section1.setName(dto.getName());
		section1.setType(dto.getType());
		section1.setExpectedPopulation(Integer.parseInt(dto.getExpectedPopulation()));
		section1.setCourseId(Integer.parseInt(dto.getCourseId()));
		section1.setCalendarId(calendarId);
		section1.setScheduleId(Integer.parseInt(dto.getScheduleId()));
		
		List<String> insertColumnNameList_sec = new ArrayList<>();
		List<String> keyHolderColumnNameList_sec = new ArrayList<>();
		
		insertColumnNameList_sec.add(Section.getColumnName(Section.Columns.CRN));
		insertColumnNameList_sec.add(Section.getColumnName(Section.Columns.NAME));
		insertColumnNameList_sec.add(Section.getColumnName(Section.Columns.TYPE));
		insertColumnNameList_sec.add(Section.getColumnName(Section.Columns.EXPECTED_POPULATION));
		insertColumnNameList_sec.add(Section.getColumnName(Section.Columns.COURSE_ID));
		insertColumnNameList_sec.add(Section.getColumnName(Section.Columns.CALENDAR_ID));
		insertColumnNameList_sec.add(Section.getColumnName(Section.Columns.SCHEDULE_ID));
		
		keyHolderColumnNameList_sec.add(Section.getColumnName(Section.Columns.ID));
		keyHolderColumnNameList_sec.add(Section.getColumnName(Section.Columns.CREATED_AT));
		
		rowAffected = sectionsDao.insert(section1, insertColumnNameList_sec, keyHolderColumnNameList_sec);
		return rowAffected;
    }
	
	public void remove_section(String id) throws SQLException
	{
		   	
    	String updateColumnName = Section.getColumnName(Section.Columns.DELETED);
    	Integer oldSection = Integer.parseInt(id);
    	Boolean state = true;
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm deleteSection = new QueryTerm();
    	deleteSection.setColumnName(updateColumnName);
    	deleteSection.setComparisonOperator(ComparisonOperator.EQUAL);
    	deleteSection.setValue(oldSection);
    	updateQueryTermList.add(deleteSection);
    	
    	sectionsDao.update(updateColumnName, state, updateQueryTermList);
	}
	
	public void modify_section_calendar(ModifySectionCalendarDto dto) throws SQLException
	{
		Calendar calendar1 = new Calendar();
		
		calendar1.setYear(Integer.parseInt(dto.getYear()));
		calendar1.setSemester(dto.getSemester());
		calendar1.setDays(dto.getDays());
		calendar1.setStartTime(dto.getStart_time());
		calendar1.setEndTime(dto.getEnd_time());
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.YEAR));
		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.SEMESTER));
		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.DAYS));
		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.START_TIME));
		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.END_TIME));
		
		keyHolderColumnNameList.add(Section.getColumnName(Section.Columns.ID));
		keyHolderColumnNameList.add(Section.getColumnName(Section.Columns.CREATED_AT));
		
		int calendarId = calendarDao.insert(calendar1, insertColumnNameList, keyHolderColumnNameList);
		
		
		String updateColumnName = Section.getColumnName(Section.Columns.CALENDAR_ID);
		Integer oldSection = Integer.parseInt(dto.getId());
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateSection = new QueryTerm();
    	updateSection.setColumnName(updateColumnName);
    	updateSection.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateSection.setValue(oldSection);
    	updateQueryTermList.add(updateSection);
    	
    	sectionsDao.update(updateColumnName, calendarId, updateQueryTermList);
		
    }
	
	public void modify_section_schedule(String id, String schedule_id) throws SQLException
	{
		   	
    	String updateColumnName = Section.getColumnName(Section.Columns.SCHEDULE_ID);
    	Integer oldSection = Integer.parseInt(id);
    	Integer newSchedule = Integer.parseInt(schedule_id);
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateSection = new QueryTerm();
    	updateSection.setColumnName(updateColumnName);
    	updateSection.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateSection.setValue(oldSection);
    	updateQueryTermList.add(updateSection);
    	
    	sectionsDao.update(updateColumnName, newSchedule, updateQueryTermList);
	}
	
	public void modify_section_type_name_pop(ModifySectionTypeNamePopDto dto) throws SQLException
	{
		String updateType = Section.getColumnName(Section.Columns.TYPE);
		String updateName = Section.getColumnName(Section.Columns.TYPE);
		String updatePop = Section.getColumnName(Section.Columns.TYPE);
		
    	Integer oldSection = Integer.parseInt(dto.getId());
    	String newType = dto.getType();
    	String newName = dto.getName();
    	Integer newPop = Integer.parseInt(dto.getExpectedPopulation());
    	List<QueryTerm> updateQueryTermList1 = new ArrayList<>();
    	List<QueryTerm> updateQueryTermList2 = new ArrayList<>();
    	List<QueryTerm> updateQueryTermList3 = new ArrayList<>();
    	
    	QueryTerm updateSectionByType = new QueryTerm();
    	updateSectionByType.setColumnName(updateType);
    	updateSectionByType.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateSectionByType.setValue(oldSection);
    	updateQueryTermList1.add(updateSectionByType);
    	
    	QueryTerm updateSectionByName = new QueryTerm();
    	updateSectionByName.setColumnName(updateName);
    	updateSectionByName.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateSectionByName.setValue(oldSection);
    	updateQueryTermList2.add(updateSectionByName);
    	
    	QueryTerm updateSectionByPop = new QueryTerm();
    	updateSectionByName.setColumnName(updatePop);
    	updateSectionByName.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateSectionByName.setValue(oldSection);
    	updateQueryTermList3.add(updateSectionByPop);
    	
    	sectionsDao.update(updateType, newType, updateQueryTermList1);
    	sectionsDao.update(updateName, newName, updateQueryTermList2);
    	sectionsDao.update(updatePop, newPop, updateQueryTermList3);
	}
    
}
