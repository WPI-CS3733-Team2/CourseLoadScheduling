package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.CourseLoadDao;
import org.dselent.scheduling.server.service.CourseLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLoadServiceImpl implements CourseLoadService
{
	@Autowired
	private CourseLoadDao courseLoadDao;
	
    public CourseLoadServiceImpl()
    {
    	//
    }
    
}
