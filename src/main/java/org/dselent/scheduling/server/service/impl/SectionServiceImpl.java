package org.dselent.scheduling.server.service.impl;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService
{
	@Autowired
	private SectionsDao sectionsDao;
	
    public SectionServiceImpl()
    {
    	//
    }
    
}
