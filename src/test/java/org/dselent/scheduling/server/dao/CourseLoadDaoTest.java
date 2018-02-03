package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.impl.CustomDaoImpl;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.CourseLoad;
import org.dselent.scheduling.server.model.CourseLoadAssociation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration

public class CourseLoadDaoTest
{
	
	@Autowired
	private CourseLoadDao courseLoadDao;
	
	@Test
	public void testCourseLoadDao() throws SQLException
	{
		// Insert
		
		CourseLoad courseLoad1 = new CourseLoad();
		courseLoad1.setType("REGULAR1");
		courseLoad1.setAmount(5);
		courseLoad1.setDeleted(false);
		
		List<String> insertColumnNameList = new ArrayList<>();
		List<String> keyHolderColumnNameList = new ArrayList<>();

		
		insertColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.TYPE));
		insertColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.AMOUNT));
		insertColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.DELETED));
		
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.ID));
		keyHolderColumnNameList.add(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT));
		
		courseLoadDao.insert(courseLoad1, insertColumnNameList, keyHolderColumnNameList);
	}
	

}
