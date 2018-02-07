package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

//import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.dto.CreateRequestDto;
//import org.dselent.scheduling.server.dto.ViewRequestHistoryDto;
import org.dselent.scheduling.server.model.Request;
//import org.dselent.scheduling.server.model.User;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface RequestService
{
	/**
	 * Creates a request and adds it to the database
	 * 
	 * @param createRequestDto DTO container information for the insertions
	 * @return A list of rows affected for each insert operation
	 * @throws SQLException
	 */
	public List<Integer> createRequest(CreateRequestDto dto) throws SQLException;
	/**
	 * Searches for all requests created by the user with the given faculty ID
	 * 
	 * @param facultyId faculty ID whose requests to search for
	 * @return A list of requests returned by the query
	 * @throws SQLException
	 */
	public List<Request> viewRequestHistory(Integer facultyId) throws SQLException;
	/**
	 * Changes given request's state to given state (should be either "accepted" or "denied")
	 * 
	 * @param requestId ID of given request
	 * @param requestState ID of intended new state of given request
	 * @return A list of rows affected for each update operation
	 * @throws SQLException
	 */
	public List<Integer> changeRequestState(Integer requestId, Integer requestState) throws SQLException;
	
	/**
	 * Returns list of all requests that are yet to be resolved
	 * 
	 * @return A list of all requests that have not been resolved yet
	 * @throws SQLException
	 */
	public List<Request> viewPendingRequests() throws SQLException;
	
}
