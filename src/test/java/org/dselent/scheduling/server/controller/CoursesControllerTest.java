package org.dselent.scheduling.server.controller;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.requests.CreateCourse;
import org.dselent.scheduling.server.requests.DeleteCourse;
import org.dselent.scheduling.server.requests.GetCourseFaculties;
import org.dselent.scheduling.server.requests.ModifyCourse;
import org.dselent.scheduling.server.requests.SearchCourse;
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
public class CoursesControllerTest
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
    public void testCreateCourseController() throws Exception
    {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(CreateCourse.getBodyName(CreateCourse.BodyKey.NAME), "Testing");
    	jsonObject.put(CreateCourse.getBodyName(CreateCourse.BodyKey.NUMBER), "1234");
    	jsonObject.put(CreateCourse.getBodyName(CreateCourse.BodyKey.FREQUENCY), "4");
    	String jsonString = jsonObject.toString();
        
    	// System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/course/create_course").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }
    
    //@Test
    public void testModifyCourseController() throws Exception
    {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(ModifyCourse.getBodyName(ModifyCourse.BodyKey.NAME), "Testing");
    	jsonObject.put(ModifyCourse.getBodyName(ModifyCourse.BodyKey.NUMBER), "2222");
    	jsonObject.put(ModifyCourse.getBodyName(ModifyCourse.BodyKey.FREQUENCY), "4");
    	jsonObject.put(ModifyCourse.getBodyName(ModifyCourse.BodyKey.ID), "92");
    	String jsonString = jsonObject.toString();
        
    	//System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/course/modify_course").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }
    
    //@Test
    public void testSearchCourseController() throws Exception
    {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(SearchCourse.getBodyName(SearchCourse.BodyKey.FREQUENCY), "1");
    	String jsonString = jsonObject.toString();
        
    	//System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/course/search_course").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }
    
    //@Test
    public void testDeleteCourseController() throws Exception
    {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(DeleteCourse.getBodyName(DeleteCourse.BodyKey.ID), "94");
    	String jsonString = jsonObject.toString();
        
    	// System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/course/delete_course").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }
    
    @Test
    public void testGetCourseFaculties() throws Exception
    {
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put(GetCourseFaculties.getBodyName(GetCourseFaculties.BodyKey.COURSE_ID), "1");
    	String jsonString = jsonObject.toString();
        
    	//System.out.println(jsonString);
    	
        this.mockMvc.perform(post("/course/get_course_faculties").content(jsonString)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .characterEncoding("utf-8"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
        //.andExpect(content().contentType("application/json"));
        
    }
}
