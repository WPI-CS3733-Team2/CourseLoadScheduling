package org.dselent.scheduling.server.controller;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.requests.DeleteUser;
import org.dselent.scheduling.server.requests.GetFacultyCalendars;
import org.dselent.scheduling.server.requests.LinkFacultyWithSection;
import org.dselent.scheduling.server.requests.Login;
import org.dselent.scheduling.server.requests.PasswordModification;
import org.dselent.scheduling.server.requests.Register;
import org.dselent.scheduling.server.requests.UserSearch;
import org.dselent.scheduling.server.requests.ViewUserOfRoleId;
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
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
public class UsersControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// initializes controllers and dependencies
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	//@Test
	public void testRegister() throws Exception {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Register.getBodyName(Register.BodyKey.WPI_ID), "1234568");
		jsonObject.put(Register.getBodyName(Register.BodyKey.USER_NAME), "dselenttt2368");
		jsonObject.put(Register.getBodyName(Register.BodyKey.FIRST_NAME), "Doug2368");
		jsonObject.put(Register.getBodyName(Register.BodyKey.LAST_NAME), "Selent2368");
		jsonObject.put(Register.getBodyName(Register.BodyKey.PASSWORD), "password1368");
		jsonObject.put(Register.getBodyName(Register.BodyKey.EMAIL), "dselenttt2368@wpi.edu");
		jsonObject.put(Register.getBodyName(Register.BodyKey.RANK), 1);
		jsonObject.put(Register.getBodyName(Register.BodyKey.ROLE_ID), 1);
		String jsonString = jsonObject.toString();

		System.out.println(jsonString);

		this.mockMvc.perform(post("/user/register").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
		// .andExpect(content().contentType("application/json"));



		// .andExpect(content().contentType("application/json"));

	}

	//@Test
	public void TestSearch() throws Exception {
		JSONObject jsonObject = new JSONObject();
		// jsonObject.put(UserSearch.getBodyName(UserSearch.BodyKey.EMAIL))
		 jsonObject.put(UserSearch.getBodyName(UserSearch.BodyKey.WPI_ID),
		 "?????????x");
		// jsonObject.put(UserSearch.getBodyName(UserSearch.BodyKey.USER_NAME),
		// "user1x");
		// jsonObject.put(UserSearch.getBodyName(UserSearch.BodyKey.FIRST_NAME), null);
		// jsonObject.put(UserSearch.getBodyName(UserSearch.BodyKey.LAST_NAME), null);
		// jsonObject.put(UserSearch.getBodyName(UserSearch.BodyKey.EMAIL),
		// "cew@wpi.edu");
		String jsonString = jsonObject.toString();

		System.out.println(jsonString);

		this.mockMvc.perform(post("/user/search_user").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}

	//@Test
	public void TestLogin() throws Exception {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put(Login.getBodyName(Login.BodyKey.USER_NAME), "dselenttt2");
		jsonObject.put(Login.getBodyName(Login.BodyKey.PASSWORD), "2a$10$oZHCntfOaVZFVXyXVDp/1.XB5DzbQ7mhSJW9S4duTda06bQf2PHni");
		String jsonString = jsonObject.toString();

		System.out.println(jsonString);

		this.mockMvc.perform(post("/user/login").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
		// .andExpect(content().contentType("application/json"));
	}
	
	//@Test
	public void ChangePassword() throws Exception{
		
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put(PasswordModification.getBodyName(PasswordModification.BodyKey.
		  ID), 1);
		  jsonObject.put(PasswordModification.getBodyName(PasswordModification.BodyKey.
		  OLD_PASSWORD), "11111111x");
		  jsonObject.put(PasswordModification.getBodyName(PasswordModification.BodyKey.
		  NEW_PASSWORD), "josephbeck"); 
		  String jsonString = jsonObject.toString();
		  
		  System.out.println(jsonString);
		  
		  this.mockMvc.perform(post("/user/change_password").content(jsonString)
		  .contentType(MediaType.APPLICATION_JSON_VALUE) .characterEncoding("utf-8"))
		  .andDo(MockMvcResultHandlers.print()) .andExpect(status().isOk());
		  //.andExpect(content().contentType("application/json")); 
	}
	
	//@Test
	public void DeleteUser() throws Exception{
		
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put(DeleteUser.getBodyName(DeleteUser.BodyKey.ID), 17);
		  String jsonString = jsonObject.toString();
		  
		  System.out.println(jsonString);
		  
		  this.mockMvc.perform(post("/user/delete").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE)
					.characterEncoding("utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
		  //.andExpect(content().contentType("application/json")); 
	}
	
	//@Test
	public void TestViewUserOfRoleId() throws Exception{
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put(ViewUserOfRoleId.getBodyName(ViewUserOfRoleId.BodyKey.ROLE_ID), 1);
		  String jsonString = jsonObject.toString();
		  
		  System.out.println(jsonString);
		  
		  this.mockMvc.perform(post("/user/view_user_of_role_id").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE)
					.characterEncoding("utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	//@Test
	public void TestGetFacultyCalendars() throws Exception{
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put(GetFacultyCalendars.getBodyName(GetFacultyCalendars.BodyKey.FACULTY_ID), 13);
		  String jsonString = jsonObject.toString();
		  
		  System.out.println(jsonString);
		  
		  this.mockMvc.perform(post("/user/get_faculty_calendars").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE)
					.characterEncoding("utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
	
	//@Test
	public void TestLinkFacultyWithSection() throws Exception{
		  JSONObject jsonObject = new JSONObject();
		  jsonObject.put(LinkFacultyWithSection.getBodyName(LinkFacultyWithSection.BodyKey.FACULTY_ID), 18);
		  jsonObject.put(LinkFacultyWithSection.getBodyName(LinkFacultyWithSection.BodyKey.SECTION_ID), 10);
		  String jsonString = jsonObject.toString();
		  
		  System.out.println(jsonString);
		  
		  this.mockMvc.perform(post("/user/link_faculty_with_section").content(jsonString).contentType(MediaType.APPLICATION_JSON_VALUE)
					.characterEncoding("utf-8")).andDo(MockMvcResultHandlers.print()).andExpect(status().isOk());
	}
}
