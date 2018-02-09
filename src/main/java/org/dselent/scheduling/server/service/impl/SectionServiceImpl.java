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
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.service.SectionService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
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

		calendarDao.insert(calendar1, insertColumnNameList_cal, keyHolderColumnNameList_cal);

		List<Calendar> matchList = customDao.getMatchDateCalendar(Integer.parseInt(dto.getYear()), dto.getSemester(),
				dto.getDays(), dto.getStart_time(), dto.getEnd_time());
		Calendar madeCalendar = matchList.get(0);
		int calendarId = madeCalendar.getId();

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

	public List<Integer> remove_section(String id) throws SQLException {
		List<Integer> affectedRowsList = new ArrayList<>();

		String updateColumnName = Section.getColumnName(Section.Columns.DELETED);
		Boolean state = true;

		Section sectionToModify = sectionsDao.findById(Integer.parseInt(id));

		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Course.getColumnName(Course.Columns.ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, sectionToModify.getId(), null);
		queryTermList.add(idTerm);

		affectedRowsList.add(sectionsDao.update(updateColumnName, state, queryTermList));
		return affectedRowsList;
	}

	public List<Integer> modify_section_calendar(ModifySectionCalendarDto dto) throws SQLException {
		List<Integer> rowsAffectedList = new ArrayList<>();
		Section sectionToModify = sectionsDao.findById(Integer.parseInt(dto.getId()));
		Integer calendarToModify = sectionToModify.getCalendarId();
		
		Integer year = Integer.parseInt(dto.getYear());
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
		
//		Calendar calendar1 = new Calendar();
//
//		calendar1.setYear(Integer.parseInt(dto.getYear()));
//		calendar1.setSemester(dto.getSemester());
//		calendar1.setDays(dto.getDays());
//		calendar1.setStartTime(dto.getStart_time());
//		calendar1.setEndTime(dto.getEnd_time());
//
//		List<String> insertColumnNameList = new ArrayList<>();
//		List<String> keyHolderColumnNameList = new ArrayList<>();
//
//		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.YEAR));
//		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.SEMESTER));
//		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.DAYS));
//		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.START_TIME));
//		insertColumnNameList.add(Calendar.getColumnName(Calendar.Columns.END_TIME));
//
//		keyHolderColumnNameList.add(Section.getColumnName(Section.Columns.ID));
//		keyHolderColumnNameList.add(Section.getColumnName(Section.Columns.CREATED_AT));
//
//		calendarDao.insert(calendar1, insertColumnNameList, keyHolderColumnNameList);
//
//		List<Calendar> matchList = customDao.getMatchDateCalendar(Integer.parseInt(dto.getYear()), dto.getSemester(),
//				dto.getDays(), dto.getStart_time(), dto.getEnd_time());
//		Calendar madeCalendar = matchList.get(0);
//		int calendarId = madeCalendar.getId();
//
//		String updateColumnName = Section.getColumnName(Section.Columns.CALENDAR_ID);
//		Section sectionToModify = sectionsDao.findById(Integer.parseInt(dto.getId()));
//
//		List<QueryTerm> queryTermList = new ArrayList<>();
//		String queryColumnName = Course.getColumnName(Course.Columns.ID);
//		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, sectionToModify.getId(), null);
//		queryTermList.add(idTerm);
//
//		sectionsDao.update(updateColumnName, calendarId, queryTermList);

	}

	public void modify_section_schedule(String id, String schedule_id) throws SQLException {

		String updateColumnName = Section.getColumnName(Section.Columns.SCHEDULE_ID);
		Integer newSchedule_id = Integer.parseInt(schedule_id);

		Section sectionToModify = sectionsDao.findById(Integer.parseInt(id));

		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Course.getColumnName(Course.Columns.ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, sectionToModify.getId(), null);
		queryTermList.add(idTerm);

		sectionsDao.update(updateColumnName, newSchedule_id, queryTermList);

	}

	public void modify_section_type_name_pop(ModifySectionTypeNamePopDto dto) throws SQLException {
		Section section = new Section();
		section.setName(dto.getName());
		section.setType(dto.getType());
		section.setExpectedPopulation(Integer.parseInt(dto.getExpectedPopulation()));

		Section sectionToModify = sectionsDao.findById(Integer.parseInt(dto.getId()));

		List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Section.getColumnName(Section.Columns.ID);
		QueryTerm idTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, sectionToModify.getId(), null);
		queryTermList.add(idTerm);

		String updateColumnName;

		if (section.getName() != null) {
			updateColumnName = Course.getColumnName(Course.Columns.NAME);
			sectionsDao.update(updateColumnName, section.getName(), queryTermList);
		}
		if (section.getType() != null) {
			updateColumnName = Section.getColumnName(Section.Columns.TYPE);
			sectionsDao.update(updateColumnName, section.getType(), queryTermList);
		}
		if (section.getExpectedPopulation() != null) {
			updateColumnName = Section.getColumnName(Section.Columns.EXPECTED_POPULATION);
			sectionsDao.update(updateColumnName, section.getExpectedPopulation(), queryTermList);
		}
	}

}
