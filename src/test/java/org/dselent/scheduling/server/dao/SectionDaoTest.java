package org.dselent.scheduling.server.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.Section;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class SectionDaoTest {
	@Autowired
	private SectionsDao sectionDao;
	
	@Test
	public void testSectionDao() throws SQLException{
		Section section1 = new Section();
		section1.setCrn(377777777);
		section1.setName("hw3733");
		section1.setType("cs");
		section1.setExpectedPopulation(9999);
		section1.setCourseId(1);
		section1.setCalendarId(1);
		section1.setScheduleId(1);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();
		
		insertColumnNameList.add(Section.getColumnName(Section.Columns.CRN));
		insertColumnNameList.add(Section.getColumnName(Section.Columns.NAME));
		insertColumnNameList.add(Section.getColumnName(Section.Columns.TYPE));
		insertColumnNameList.add(Section.getColumnName(Section.Columns.EXPECTED_POPULATION));
		insertColumnNameList.add(Section.getColumnName(Section.Columns.COURSE_ID));
		insertColumnNameList.add(Section.getColumnName(Section.Columns.CALENDAR_ID));
		insertColumnNameList.add(Section.getColumnName(Section.Columns.SCHEDULE_ID));
		
		keyHolderColumnNameList.add(Section.getColumnName(Section.Columns.ID));
		keyHolderColumnNameList.add(Section.getColumnName(Section.Columns.CREATED_AT));
		
		sectionDao.insert(section1, insertColumnNameList, keyHolderColumnNameList);
	}
	
}
