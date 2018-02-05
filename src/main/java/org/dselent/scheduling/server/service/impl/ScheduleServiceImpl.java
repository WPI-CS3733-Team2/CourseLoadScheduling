package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.ScheduleDao;
import org.dselent.scheduling.server.service.ScheduleService;
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
    
}
