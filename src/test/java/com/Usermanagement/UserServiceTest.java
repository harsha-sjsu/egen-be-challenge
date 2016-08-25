package com.Usermanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import spark.Request;
import spark.Response;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.usermangement.UserService;
import com.usermangement.model.User;
import com.usermangement.util.DBSerializeUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock
	private Request request;
	@Mock
	private Response response;
	@Mock
	private DBCollection dbCollection;

	@Mock
	DBCursor c;

	private String bodyString = "{\"id\":\"d887ea79-2ff4-451c-af5e-6739099dad00\",\"firstName\":\"Dorris\",\"lastName\":\"Keeling\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"street\":\"193 Talon Valley\",\"city\":\"South Tate furt\",\"zip\":\"47069\",\"state\":\"IA\",\"country\":\"US\"},\"dateCreated\":\"2016-03-15T07:02:40.896Z\",\"company\":{\"name\":\"Denesik Group\",\"website\":\"http://jodie.org\"},\"profilePic\":\"http://lorempixel.com/640/480/people\"}";
	private String bodyString1 = "{\"id\":\"d887ea79-2ff4-451c-af5e-6739099dad00\",\"firstName\":\"Dorris\",\"lastName\":\"Keeling\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"street\":\"193 Talon Valley\",\"city\":\"South Tate furt\",\"zip\":\"47069\",\"state\":\"IA\",\"country\":\"US\"},\"dateCreated\":\"Mar 15, 2016 12:02:40 AM\",\"company\":{\"name\":\"Denesik Group\",\"website\":\"http://jodie.org\"},\"profilePic\":\"http://lorempixel.com/640/480/people\"}";

	private DBObject value;

	@Before
	public void init() {
		when(request.body()).thenReturn(bodyString);
		when(dbCollection.find(any(DBObject.class))).thenReturn(c);
		when(c.size()).thenReturn(1);

		User user = new GsonBuilder().setPrettyPrinting().create()
				.fromJson(bodyString1, User.class);

		value = DBSerializeUtil.convertToDBObject(user);

		when(c.next()).thenReturn(value);

	}

	@Test
	public void testHandlePostForExistingUser() {

		String output = (String) UserService.handlePost(dbCollection, request,
				response);
		assertEquals("A user with same Id already exists", output);

	}

	@Test
	public void testHandlePostForNewUser() {

		when(c.size()).thenReturn(0);

		String output = (String) UserService.handlePost(dbCollection, request,
				response);
		assertEquals("A User is successfully created", output);

	}

	@Test
	public void testHandlePostForException() {

		when(c.size()).thenThrow(new RuntimeException());

		String output = (String) UserService.handlePost(dbCollection, request,
				response);
		assertEquals("Exception occurred while inserting Object", output);

	}

	@Test
	public void testHandlePostForInvalidUser() {

		String invalidBodyString = "{\"id\":\"\",\"firstName\":\"Dorris\",\"lastName\":\"Keeling\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"street\":\"193 Talon Valley\",\"city\":\"South Tate furt\",\"zip\":\"47069\",\"state\":\"IA\",\"country\":\"US\"},\"dateCreated\":\"2016-03-15T07:02:40.896Z\",\"company\":{\"name\":\"Denesik Group\",\"website\":\"http://jodie.org\"},\"profilePic\":\"http://lorempixel.com/640/480/people\"}";

		when(request.body()).thenReturn(invalidBodyString);
		when(c.size()).thenReturn(0);

		String output = (String) UserService.handlePost(dbCollection, request,
				response);
		assertEquals("Bad Request", output);

	}

	@Test
	public void testHandleGetWithEmptyData() {

		when(dbCollection.find()).thenReturn(c);
		when(c.size()).thenReturn(0);

		String output = (String) UserService.handleGet(dbCollection, response);
		assertEquals("No Users are present at this time", output);

	}

	@Test
	public void testHandleGetWithUsersData() {

		when(dbCollection.find()).thenReturn(c);
		when(c.size()).thenReturn(1);

		when(c.next()).thenReturn(value);

		String output = (String) UserService.handleGet(dbCollection, response);
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(output);
		new JSONObject(bodyString);
		new JsonParser().parse(bodyString).getAsJsonObject();
		assertEquals(bodyString1, jsonArray.get(0).toString());

	}

	@Test
	public void testHandlePutWithNoUser() {

		when(request.params(any(String.class))).thenReturn("");
		when(dbCollection.find(any(DBObject.class))).thenReturn(c);
		when(c.size()).thenReturn(0);

		String output = (String) UserService.handlePut(dbCollection, request,
				response);
		assertEquals("A user does not exist", output);

	}

	@Test
	public void testHandlePutForException() {
		when(request.params(any(String.class))).thenReturn("");
		when(dbCollection.find(any(DBObject.class))).thenReturn(c);
		when(c.size()).thenThrow(new RuntimeException());

		String output = (String) UserService.handlePut(dbCollection, request,
				response);
		assertEquals("Exception occurred while Updating Object", output);

	}

	@Test
	public void testHandlePutForInvalidUser() {

		String invalidBodyString = "{\"id\":\"\",\"firstName\":\"Dorris\",\"lastName\":\"Keeling\",\"email\":\"Darby_Leffler68@gmail.com\",\"address\":{\"street\":\"193 Talon Valley\",\"city\":\"South Tate furt\",\"zip\":\"47069\",\"state\":\"IA\",\"country\":\"US\"},\"dateCreated\":\"2016-03-15T07:02:40.896Z\",\"company\":{\"name\":\"Denesik Group\",\"website\":\"http://jodie.org\"},\"profilePic\":\"http://lorempixel.com/640/480/people\"}";

		when(request.body()).thenReturn(invalidBodyString);
		when(c.size()).thenReturn(1);

		String output = (String) UserService.handlePut(dbCollection, request,
				response);
		assertEquals("Bad Request", output);

	}

	@Test
	public void testHandlePutForExistingUser() {

		when(c.size()).thenReturn(1);

		String output = (String) UserService.handlePut(dbCollection, request,
				response);
		assertEquals("User is updated successfully", output);

	}

}
