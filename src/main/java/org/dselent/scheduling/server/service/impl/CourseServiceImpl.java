package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CoursesDao coursesDao;
	
    public CourseServiceImpl()
    {
    	//
    }
    
}
