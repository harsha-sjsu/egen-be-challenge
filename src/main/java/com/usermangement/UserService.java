package com.usermangement;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import spark.Request;
import spark.Response;

import com.google.common.base.Preconditions;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteConcern;
import com.usermangement.initialize.ApplicationContext;
import com.usermangement.model.User;
import com.usermangement.util.DBSerializeUtil;

//import com.fasterxml.jackson.core.JsonParseException;

public class UserService {

	private static final int HTTP_BAD_REQUEST = 400;

	interface Validable {
		boolean isValid();
	}

	public static void main(String[] args) {
		DBCollection databaseCollection = ApplicationContext
				.getDatabaseCollectionThroughMongoClient();
		Preconditions.checkNotNull(databaseCollection,
				"The DBCollection passed is null");
		// insert a post (using HTTP post method)
		post("/users", (request, response) -> {
			return handlePost(databaseCollection, request, response);
		});

		// get all post (using HTTP get method)
		get("/users", (request, response) -> {
			return handleGet(databaseCollection, response);
		});

		put("/users/:id", (request, response) -> {
			return handlePut(databaseCollection, request, response);

		});

	}

	public static Object handlePut(DBCollection databaseCollection,
			Request request, Response response) {
		try {
			String id = request.params(":id");
			DBCursor cursor = databaseCollection.find(new BasicDBObject("id",
					id));
			if (cursor.size() == 0) {
				response.status(404);
				return "A user does not exist";
			}
			User user = new GsonBuilder().setPrettyPrinting().create()
					.fromJson(request.body().toString(), User.class);
			if (!user.isValid()) {
				response.status(HTTP_BAD_REQUEST);
				return "Bad Request";
			}
			cursor.next();
			databaseCollection.update(new BasicDBObject("id", id),
					DBSerializeUtil.convertToDBObject(user), false, false);
			response.status(200);
			response.type("application/json");
			// return new
			// GsonBuilder().setPrettyPrinting().create().toJson(user);
			return "User is updated successfully";
		} catch (Exception e) {
			return "Exception occurred while Updating Object";
		}
	}

	public static Object handleGet(DBCollection databaseCollection,
			Response response) {
		DBCursor cursor = databaseCollection.find();
		// create new List of Users
		if (cursor.size() > 0) {
			List<User> users = new ArrayList<User>(cursor.size());
			for (int i = 0; i < cursor.size(); i++) {
				users.add(DBSerializeUtil.convertToUserObject(cursor.next()));
			}
			cursor.close();
			response.status(200);
			response.type("application/json");
			return new GsonBuilder().setPrettyPrinting().create().toJson(users);
		} else {
			cursor.close();
			response.status(204);
			return "No Users are present at this time";
		}
	}

	public static Object handlePost(DBCollection databaseCollection,
			Request request, Response response) {
		try {

			JSONObject jsonObject = new JSONObject(request.body());
			DBCursor cursor = databaseCollection.find(new BasicDBObject("id",
					jsonObject.getString("id")));

			if (cursor.size() > 0) {
				response.status(409);
				return "A user with same Id already exists";
			}
			User user = new GsonBuilder().setPrettyPrinting().create()
					.fromJson(request.body().toString(), User.class);
			if (!user.isValid()) {
				response.status(HTTP_BAD_REQUEST);
				return "Bad Request";
			}
			databaseCollection.insert(DBSerializeUtil.convertToDBObject(user),
					WriteConcern.SAFE);
			response.status(200);
			response.type("application/text");
			return "A User is successfully created";
		} catch (Exception e) {
			return "Exception occurred while inserting Object";
		}
	}
}
