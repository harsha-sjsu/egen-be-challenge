package com.usermangement.initialize;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationContext {
	public static DBCollection getDatabaseCollectionThroughMongoClient()
	{
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient();
			DB database = mongoClient.getDB("UserManagementDatabase");
			DBCollection collection = database.getCollection("Users");
			return collection;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
