package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.RequestDao;
import org.dselent.scheduling.server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService
{
	@Autowired
	private RequestDao requestDao;
	
    public RequestServiceImpl()
    {
    	//
    }
    
}
