package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.dao.UserFacultyAssociationDao;
import org.dselent.scheduling.server.dto.CreateCourseDto;
import org.dselent.scheduling.server.dto.ModifyCourseDto;
import org.dselent.scheduling.server.dto.SearchCourseDto;
import org.dselent.scheduling.server.httpReturnObject.CalendarInfo;
import org.dselent.scheduling.server.httpReturnObject.CourseInfo;
import org.dselent.scheduling.server.httpReturnObject.SectionInfo;
import org.dselent.scheduling.server.httpReturnObject.UserFaculty;
import org.dselent.scheduling.server.httpReturnObject.UserWithScheduleInfo;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Course;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.UserFacultyAssociation;
import org.dselent.scheduling.server.service.CourseService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.LogicalOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CoursesDao coursesDao;
	
	@Autowired
	private CustomDao customDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private UserFacultyAssociationDao userFacultyAssociationDao;
	
    public CourseServiceImpl()
    {
    	//
    }

    @Transactional
    @Override
	public Course createCourse(CreateCourseDto dto) throws SQLException
	{
    	
		Course course = new Course();
		course.setName(dto.getName());
		course.setNumber(dto.getNumber());
		course.setFrequency(Integer.parseInt(dto.getFrequency()));										//
    	
    	List<String> courseInsertColumnNameList = new ArrayList<>();
    	List<String> courseKeyHolderColumnNameList = new ArrayList<>();
    	
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NAME));		//
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.NUMBER));
    	courseInsertColumnNameList.add(Course.getColumnName(Course.Columns.FREQUENCY));
    	
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.ID));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.CREATED_AT));
    	courseKeyHolderColumnNameList.add(Course.getColumnName(Course.Columns.UPDATED_AT));
		
    	coursesDao.insert(course, courseInsertColumnNameList, courseKeyHolderColumnNameList);
		return course;
	}
    
    @Transactional
    @Override
	public Integer modifyCourse(ModifyCourseDto dto) throws SQLException
	{
    	Integer successful;
    	
    	Course course = new Course();
		course.setName(dto.getName());
		course.setNumber(dto.getNumber());
		course.setFrequency(Integer.parseInt(dto.getFrequency()));
		
        Course courseToModify = coursesDao.findById(Integer.parseInt(dto.getId()));	
		
        List<QueryTerm> queryTermList = new ArrayList<>();
		String queryColumnName = Course.getColumnName(Course.Columns.ID);
		QueryTerm idTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, courseToModify.getId(), null);
		queryTermList.add(idTerm);
		
		List<String> updateColumnName = new ArrayList<>();
		updateColumnName.add(Course.getColumnName(Course.Columns.NAME));
		updateColumnName.add(Course.getColumnName(Course.Columns.NUMBER));
		updateColumnName.add(Course.getColumnName(Course.Columns.FREQUENCY));
		
		List<Object> updateValues = new ArrayList<>();
		updateValues.add(course.getName());
		updateValues.add(course.getNumber());
		updateValues.add(course.getFrequency());
		
		successful = coursesDao.updateColumns(updateColumnName, updateValues, queryTermList);
		return successful;
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	@Override
	public List<Course> searchCourse(SearchCourseDto dto) throws SQLException {
		
		String name = dto.getName();
		String number = dto.getNumber();
		String freqString = dto.getFrequency();										
    	
		List<String> courseColumnNameList = new ArrayList<>();
    	
		courseColumnNameList.addAll(Course.getColumnNameList());
		
    	List<QueryTerm> queryTermList = new ArrayList<>();
    	boolean firstTerm = true;
    	
    	if (name != null) {
			String queryColumnName = Course.getColumnName(Course.Columns.NAME);
			QueryTerm queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, name, LogicalOperator.AND);
			if(firstTerm) {
				queryTerm.setLogicalOperator(null);
				firstTerm = false;
			}
			queryTermList.add(queryTerm);
		}
    	if (number != null) {
			String queryColumnName = Course.getColumnName(Course.Columns.NUMBER);
			QueryTerm queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, number, LogicalOperator.AND);
			if(firstTerm) {
				queryTerm.setLogicalOperator(null);
				firstTerm = false;
			}
			queryTermList.add(queryTerm);
		}
    	if (freqString != null) {
    		int frequency = Integer.parseInt(freqString);
			String queryColumnName = Course.getColumnName(Course.Columns.FREQUENCY);
			QueryTerm queryTerm = new QueryTerm (queryColumnName, ComparisonOperator.EQUAL, frequency, LogicalOperator.AND);
			if(firstTerm) {
				queryTerm.setLogicalOperator(null);
				firstTerm = false;
			}
			queryTermList.add(queryTerm);
		}
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderBy = new Pair(Course.getColumnName(Course.Columns.ID), ColumnOrder.ASC);
    	orderByList.add(orderBy);
    	
    	List<Course> selectedCourses = new ArrayList<Course>();
    	selectedCourses = coursesDao.select(courseColumnNameList, queryTermList, orderByList);
    			
		return selectedCourses;
    }

	@Override
	public int deleteCourse(String id) throws SQLException {
		int success;
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		
		String queryColumnName = Course.getColumnName(Course.Columns.ID);
		QueryTerm queryTerm = new QueryTerm(queryColumnName, ComparisonOperator.EQUAL, Integer.parseInt(id), null);
		queryTermList.add(queryTerm);
		
		success = coursesDao.delete(queryTermList);
		return success;
	}

	@Override
	public List<UserFaculty> getCourseFaculties(String courseId) throws SQLException {
		List<UserFaculty> result = new ArrayList<>();
		List<Faculty> faculties = customDao.getFacultiesTeachingACourse(Integer.parseInt(courseId));
		for(Faculty faculty: faculties) {
			int facultyId = faculty.getId();
			UserFacultyAssociation ufa = userFacultyAssociationDao.findByFacultyId(facultyId);
			int userId = ufa.getUserId();
			User user = usersDao.findById(userId);
			result.add(new UserFaculty(userId, user.getWpiId(), user.getUserName(), 
					user.getFirstName(), user.getLastName(), user.getEmail(), user.getAccountState(), user.getDeleted(), facultyId, (int)faculty.getRank()));
		}
		return result;
	}
	
	@Override
	public List<CourseInfo> getUnassignedCourseSection(String searchTerm) throws SQLException{
		List<CourseInfo> returnCourseList = new ArrayList<CourseInfo>();
		List<Course> knownCoursesList = new ArrayList<Course>();
		List<Section> sectionList = customDao.getSectionsFromCourseSearch(searchTerm);
		
		for (Section section : sectionList) {
			if(section.getScheduleId() == 0) {
				List<Calendar> calendarList = customDao.getCalendarsOfSection(section.getId());
				Calendar calendar = calendarList.get(0);
				
				CalendarInfo returnCalendar = new CalendarInfo(calendar.getId(),
						calendar.getYear(), calendar.getSemester(), calendar.getDays(),
						calendar.getStartTime(), calendar.getEndTime());
				
				SectionInfo returnSection = new SectionInfo(section.getId(), section.getName(), section.getType(),
						section.getExpectedPopulation(), returnCalendar, section.getCrn(),
						section.getCourseId(), section.getCalendarId(), section.getScheduleId());

				Course course = customDao.getCoursesOfSection(section.getId()).get(0);
				
				if (!knownCoursesList.contains(course)) {
					knownCoursesList.add(course);
					List<SectionInfo> returnSectionList = new ArrayList<SectionInfo>();
					returnSectionList.add(returnSection);
					CourseInfo returnCourse = new CourseInfo(course.getId(), course.getName(), course.getNumber(), course.getFrequency(), returnSectionList);
					returnCourseList.add(returnCourse);
				}
				else {
					for (CourseInfo courseInfo : returnCourseList) {
						if (course.getName().contentEquals(courseInfo.getCourseName())) {
							courseInfo.getSectionsOfCourse().add(returnSection);
						}
					}
				}
			}
		}	
		return returnCourseList;
	}
    
}
