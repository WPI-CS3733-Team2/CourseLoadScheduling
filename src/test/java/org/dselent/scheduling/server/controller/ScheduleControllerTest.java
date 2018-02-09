package org.dselent.scheduling.server.controller;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.CreateSchedule;
import org.dselent.scheduling.server.requests.ModifyCourse;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class ScheduleControllerTest
{
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup()
	{
		// initializes controllers and dependencies
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    //@Test
    public void testCreateScheduleController() throws Exception
    {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(CreateSchedule.getBodyName(CreateSchedule.BodyKey.FACULTY_ID), 13);
    	jsonObject.put(CreateSchedule.getBodyName(CreateSchedule.BodyKey.SCHEDULE_NAME), "Test");
    	String jsonString = jsonObject.toString();
        
    	// System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/schedule/create_schedule").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }
}
