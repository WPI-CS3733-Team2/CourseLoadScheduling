package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.CreateSectionDto;
import org.dselent.scheduling.server.dto.ModifySectionCalendarDto;
import org.dselent.scheduling.server.dto.ModifySectionTypeNamePopDto;
import org.dselent.scheduling.server.model.Calendar;
import org.dselent.scheduling.server.model.Section;
import org.springframework.stereotype.Service;

/**
 * Service layer to specify all business logic. Calls the dao layer when data retrieval is needed.
 * Interfaces specify the behavior and the implementation provide the specific implementations.
 * 
 * @author dselent
 *
 */
@Service
public interface SectionService
{

	public int create_section(CreateSectionDto createSectionDto) throws SQLException;
	public List<Integer> remove_section(Integer id) throws SQLException;
	public Section select_section(Integer id) throws SQLException;
	public List<Integer> modify_section_calendar(ModifySectionCalendarDto modifySectionCalendarDto) throws SQLException;
	public List<Integer> modify_section_schedule(Integer id, Integer schedule_id) throws SQLException;
	public List<Integer> modify_section_type_name_pop(ModifySectionTypeNamePopDto modifySectionTypeNamePopDto) throws SQLException;
	public List<Calendar> view_section_calendars_of_course(Integer id) throws SQLException;
	public List<Integer> dislinkAll(Integer scheduleId) throws SQLException;
	
}
