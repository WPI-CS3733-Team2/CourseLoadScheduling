package org.dselent.scheduling.server.controller;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.requests.CreateSection;
import org.dselent.scheduling.server.requests.RemoveSection;
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
public class SectionControllerTest
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
   /* @Test
    public void testSectionController() throws Exception
    
    {
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.CRN), "565050");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.NAME), "X02");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.TYPE), "Lab");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.EXPECTED_POPULATION), "50");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.COURSE_ID), "1");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.SCHEDULE_ID), "1");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.YEAR), "2018");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.SEMESTER), "A");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.DAYS), "MWF");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.START_TIME), "0800");
    	jsonObject.put(CreateSection.getBodyName(CreateSection.BodyKey.END_TIME), "0900");
    	
    	String jsonString = jsonObject.toString();
        
    	// System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/section/create_section").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }*/
    
	@Test
	public void testSectionController() throws Exception
    
    {
    	
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(RemoveSection.getBodyName(RemoveSection.BodyKey.ID), "107");
    	
    	String jsonString = jsonObject.toString();
        
    	// System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/section/remove_section").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }
}
