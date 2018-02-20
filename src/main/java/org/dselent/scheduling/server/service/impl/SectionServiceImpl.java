package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.dao.CalendarDao;
import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.ModifySectionCalendarDto;
import org.dselent.scheduling.server.dto.ModifySectionTypeNamePopDto;
import org.dselent.scheduling.server.httpReturnObject.UserInfo;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UsersRolesLink;
import org.dselent.scheduling.server.service.SectionService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.LogicalOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService {
	@Autowired
	private SectionsDao sectionsDao;
	@Autowired
	private CalendarDao calendarDao;
	@Autowired
	private CustomDao customDao;

	public SectionServiceImpl() {
		//

	}

	public int create_section(CreateSectionDto dto) throws SQLException {
		int rowAffected;

		Calendar calendar1 = new Calendar();

		calendar1.setYear(Integer.valueOf(dto.getYear()));
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

		calendarDao.insert(calendar1, insertColumnNameList_cal, keyHolderColumnNameList_cal);

		List<Calendar> matchList = customDao.getMatchDateCalendar(Integer.valueOf(dto.getYear()), dto.getSemester(),
				dto.getDays(), dto.getStart_time(), dto.getEnd_time());
		Calendar madeCalendar = matchList.get(0);
		int calendarId = madeCalendar.getId();

		Section section1 = new Section();
		section1.setCrn(Integer.valueOf(dto.getCrn()));
		section1.setName(dto.getName());
		section1.setType(dto.getType());
		section1.setExpectedPopulation(Integer.valueOf(dto.getExpectedPopulation()));
		section1.setCourseId(Integer.valueOf(dto.getCourseId()));
		section1.setCalendarId(calendarId);
		section1.setScheduleId(Integer.valueOf(dto.getScheduleId()));

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

	public List<Integer> remove_section(Integer id) throws SQLException {
		List<Integer> affectedRowsList = new ArrayList<>();

		String updateColumnName = Section.getColumnName(Section.Columns.DELETED);
		Boolean state = true;


		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Course.getColumnName(Course.Columns.ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		affectedRowsList.add(sectionsDao.update(updateColumnName, state, queryTermList));
		return affectedRowsList;
	}
	
	public Section select_section(Integer id) throws SQLException {
		
		List<String> columnNamesList = new ArrayList<>();
		columnNamesList.addAll(Section.getColumnNameList());
		List<QueryTerm> queryTermList = new ArrayList<>();
		
		String queryColumnName1 = Section.getColumnName(Section.Columns.ID);
		QueryTerm queryTerm1 = new QueryTerm (queryColumnName1, ComparisonOperator.EQUAL, id, LogicalOperator.OR);
		queryTerm1.setLogicalOperator(null);
		queryTermList.add(queryTerm1);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> orderBy1 = new Pair<String, ColumnOrder>(Section.getColumnName(Section.Columns.ID), ColumnOrder.ASC);
		orderByList.add(orderBy1);
		
		List<Section> selectedSection = sectionsDao.select(columnNamesList, queryTermList, orderByList);
		System.out.println("Selected Section: "+selectedSection);

		return selectedSection.get(0);
	}


	public List<Integer> modify_section_calendar(ModifySectionCalendarDto dto) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		Section sectionToModify = sectionsDao.findById(dto.getId());
		Integer calendarToModify = sectionToModify.getCalendarId();
		
		Integer year = dto.getYear();
		String semester = dto.getSemester();
		String days = dto.getDays();
		String startingTime = dto.getStart_time();
		String endingTime = dto.getEnd_time();

		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Calendar.getColumnName(Calendar.Columns.ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL,calendarToModify, null);
		queryTermList.add(idTerm);
		
		List<String> updateColumnName = new ArrayList<>();
		updateColumnName.add(Calendar.getColumnName(Calendar.Columns.YEAR));
		updateColumnName.add(Calendar.getColumnName(Calendar.Columns.SEMESTER));
		updateColumnName.add(Calendar.getColumnName(Calendar.Columns.DAYS));
		updateColumnName.add(Calendar.getColumnName(Calendar.Columns.START_TIME));
		updateColumnName.add(Calendar.getColumnName(Calendar.Columns.END_TIME));
		
		List<Object> updateValue = new ArrayList<>();
		updateValue.add(year);
		updateValue.add(semester);
		updateValue.add(days);
		updateValue.add(startingTime);
		updateValue.add(endingTime);
		

		rowsAffectedList.add(calendarDao.updateColumns(updateColumnName, updateValue, queryTermList));
		return rowsAffectedList;
		

	}

	public List<Integer> modify_section_schedule(Integer id, Integer schedule_id) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		String updateColumnName = Section.getColumnName(Section.Columns.SCHEDULE_ID);
		Integer newSchedule_id = schedule_id;


		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Course.getColumnName(Course.Columns.ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		rowsAffectedList.add(sectionsDao.update(updateColumnName, newSchedule_id, queryTermList));
		return rowsAffectedList;

	}

	public List<Integer> modify_section_type_name_pop(ModifySectionTypeNamePopDto dto) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
//		Section section = new Section();
//		section.setName(dto.getName());
//		section.setType(dto.getType());
//		section.setExpectedPopulation(Integer.parseInt(dto.getExpectedPopulation()));


		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Section.getColumnName(Section.Columns.ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, dto.getId(), null);
		queryTermList.add(idTerm);

		List<String> updateColumnName = new ArrayList<>();
		List<Object> updateValue = new ArrayList<>();

		if (dto.getName() != null) {
			updateColumnName.add(Course.getColumnName(Course.Columns.NAME));
			updateValue.add(dto.getName());
			//rowsAffectedList.add(sectionsDao.update(updateColumnName, section.getName(), queryTermList));
		}
		if (dto.getType() != null) {
			updateColumnName.add(Section.getColumnName(Section.Columns.TYPE));
			updateValue.add(dto.getType());
		}
		if (dto.getExpectedPopulation() != null) {
			updateColumnName.add(Section.getColumnName(Section.Columns.EXPECTED_POPULATION));
			updateValue.add(dto.getExpectedPopulation());
		}
		sectionsDao.updateColumns(updateColumnName, updateValue, queryTermList);
		return rowsAffectedList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Calendar> view_section_calendars_of_course(String courseId) throws SQLException {
    	
		List<String> sectionColumnNameList = new ArrayList<>();
    	
    	sectionColumnNameList.addAll(Section.getColumnNameList());
    	
    	List<QueryTerm> queryTermList = new ArrayList<>();
    	
    	String queryColumnName = Section.getColumnName(Section.Columns.COURSE_ID);
		QueryTerm queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, Integer.parseInt(courseId), null);
		queryTermList.add(queryTerm);
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderBy = new Pair<>(Section.getColumnName(Section.Columns.CALENDAR_ID), ColumnOrder.ASC);
    	orderByList.add(orderBy);
    	
    	List<Section> calendars = new ArrayList<>();
    	calendars = sectionsDao.select(sectionColumnNameList, queryTermList, orderByList);
		
		List<String> calendarColumnNameList = new ArrayList<>();
    	
    	calendarColumnNameList.addAll(Calendar.getColumnNameList());
    	
    	queryTermList = new ArrayList<>();
    	
    	for(int i = 0; i < calendars.size(); i++) {
			queryColumnName = Calendar.getColumnName(Calendar.Columns.ID);
			queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, calendars.get(i).getCalendarId(), LogicalOperator.OR);
			if(i == 0) {
				queryTerm.setLogicalOperator(null);
			}
			queryTermList.add(queryTerm);
    	}
    	
    	orderByList = new ArrayList<>();
    	orderBy = new Pair(Calendar.getColumnName(Calendar.Columns.ID), ColumnOrder.ASC);
    	orderByList.add(orderBy);
    
    	List<Calendar> selectedCalendars = new ArrayList<>();
    	selectedCalendars = calendarDao.select(calendarColumnNameList, queryTermList, orderByList);
    			
		return selectedCalendars;
	}

	@Override
	public List<Integer> dislinkAll(Integer scheduleId) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		String updateColumnName = Section.getColumnName(Section.Columns.SCHEDULE_ID);

		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Section.getColumnName(Section.Columns.SCHEDULE_ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, scheduleId, null);
		queryTermList.add(idTerm);

		rowsAffectedList.add(sectionsDao.update(updateColumnName, null, queryTermList));
		return rowsAffectedList;
	}

}
