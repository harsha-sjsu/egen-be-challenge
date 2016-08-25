package com.usermangement.util;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.usermangement.model.Address;
import com.usermangement.model.Company;
import com.usermangement.model.User;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBSerializeUtil {

	public static BasicDBObject convertToDBObject(User user)
	{
		BasicDBObject addressObject = new BasicDBObject();
		addressObject.append("street", user.getAddress().getStreet());
		addressObject.append("city", user.getAddress().getCity());
		addressObject.append("zip", user.getAddress().getZip());
		addressObject.append("state", user.getAddress().getState());
		addressObject.append("country", user.getAddress().getCountry());
		
		
		BasicDBObject companyObject = new BasicDBObject();
		companyObject.append("name", user.getCompany().getName());
		companyObject.append("website", user.getCompany().getWebsite());
		
		
		BasicDBObject dbObject = new BasicDBObject("id", user.getId());
		dbObject.append("firstName", user.getFirstName());
		dbObject.append("lastName", user.getLastName());
		dbObject.append("email", user.getEmail());
		dbObject.append("address", addressObject);
		dbObject.append("dateCreated", user.getDateCreated());
		dbObject.append("company", companyObject);
		dbObject.append("profilePic", user.getProfilePic());
		
		return dbObject;
	}
	public static User convertToUserObject(DBObject dbObject)
	{
		BasicDBObject addressObject = (BasicDBObject)dbObject.get("address");
		BasicDBObject companyObject = (BasicDBObject)dbObject.get("company");
		Address address = new Address(addressObject.getString("street"),addressObject.getString("city"),addressObject.getString("zip"),addressObject.getString("state"),addressObject.getString("country"));
		Company company = new Company(companyObject.getString("name"),companyObject.getString("website"));
		User user = new User((String) dbObject.get("id"),(String) dbObject.get("firstName"),(String) dbObject.get("lastName"),(String) dbObject.get("email"),address,(Date) dbObject.get("dateCreated"),company,(String) dbObject.get("profilePic"));
		return user;
	}
}
