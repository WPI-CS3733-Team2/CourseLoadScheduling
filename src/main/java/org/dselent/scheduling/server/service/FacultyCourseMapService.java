package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;


import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.Triple;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface FacultyCourseMapService
{
	public  List<Triple<String, String, String>> facultyCourseMap() throws SQLException;

}
