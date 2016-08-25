# egen-be-challenge

I have develeoped a restful web service for User Management application using Spark Java(http://sparkjava.com/) and MongoDB.

Assumptions made for this project
1. User has the following properties
{
	"id": "1630215c-2608-44b9-aad4-9d56d8aafd4c",
	"firstName": "Dorris",
	"lastName": "Keeling",
	"email": "Darby_Leffler68@gmail.com",
	"address": {
		"street": "193 Talon Valley",
		"city": "South Tate furt",
		"zip": "47069",
		"state": "IA",
		"country": "US"
	},
	"dateCreated": "2016-03-15T07:02:40.896Z",
	"company": {
		"name": "Denesik Group",
		"website": "http://jodie.org"
	},
	"profilePic": "http://lorempixel.com/640/480/people"
}

2. The User's ID uniquely identifies a user.

3. Input is valid while using the rest service.

To Run the application:
-------------------------

Right click on the UserService.java and select run as Java Application.


Accessing the REST User Service
=================================

http://localhost:4567/users
 

A HTTP GET request to URL http://localhost:4567/users
returns all users data in Json format. The Json format returned:

[
  {
    "id": "bd0c0c64-de5f-47ee-8164-0b8b9f662b48",
    "firstName": "Surendra",
    "lastName": "Yadav",
    "email": "Darby_Leffler68@gmail.com",
    "address": {
      "street": "193 Talon Valley",
      "city": "South Tate furt",
      "zip": "47069",
      "state": "IA",
      "country": "US"
    },
    "dateCreated": "Mar 15, 2016 12:02:40 AM",
    "company": {
      "name": "Denesik Group",
      "website": "http://jodie.org"
    },
    "profilePic": "http://lorempixel.com/640/480/people"
  },
  {
    "id": "bd0c0c64-de5f-47ee-8164-0b8b9f662b49",
    "firstName": "Harsha",
    "lastName": "Yadav",
    "email": "Darby_Leffler68@gmail.com",
    "address": {
      "street": "193 Talon Valley",
      "city": "South Tate furt",
      "zip": "47069",
      "state": "IA",
      "country": "US"
    },
    "dateCreated": "Mar 15, 2016 12:02:40 AM",
    "company": {
      "name": "Denesik Group",
      "website": "http://jodie.org"
    },
    "profilePic": "http://lorempixel.com/640/480/people"
  }
]

A HTTP POST request to URL http://localhost:4567/users
with the data:

{
	"id": "bd0c0c64-de5f-47ee-8164-0b8b9f662b32",
	"firstName": "Kishore",
	"lastName": "yadav",
	"email": "Darby_Leffler68@gmail.com",
	"address": {
		"street": "193 Talon Valley",
		"city": "South Tate furt",
		"zip": "47069",
		"state": "AP",
		"country": "India"
	},
	"dateCreated": "2016-03-15T07:02:40.896Z",
	"company": {
		"name": "Denesik Group",
		"website": "http://jodie.org"
	},
	"profilePic": "http://lorempixel.com/640/480/people"
}

adds a user whose name is Kishore yadav 


A HTTP PUT request to URL http://localhost:4567/users/bd0c0c64-de5f-47ee-8164-0b8b9f662b32
with the data:

{
	"id": "bd0c0c64-de5f-47ee-8164-0b8b9f662b32",
	"firstName": "first name with middle name",
	"lastName": "last name",
	"email": "Darby_Leffler68@gmail.com",
	"address": {
		"street": "193 Talon Valley",
		"city": "South Tate furt",
		"zip": "47069",
		"state": "AP",
		"country": "India"
	},
	"dateCreated": "2016-03-15T07:02:40.896Z",
	"company": {
		"name": "Denesik Group",
		"website": "http://jodie.org"
	},
	"profilePic": "http://lorempixel.com/640/480/people"
}

updates the user whose id is bd0c0c64-de5f-47ee-8164-0b8b9f662b32

Languages, tools, libaries and frameworks used in the project.

1. Spark Framework (spark-core)
2. Mongo DB
3. Java 8
4. Maven
5. JUnit
6. lombok
7. Gson
8. commons-lang3
9. Guava
10. mongo-java-driver
11. Postman

I have performed Unit testing for this application. The test cases performed for this application are given below.
1. testHandleGetWithEmptyData()
2. testHandleGetWithUsersData()
3. testHandlePostForException()
4. testHandlePostForExistingUser()
5. testHandlePostForInvalidUser()
6. testHandlePostForNewUser()
7. testHandlePutForException()
8. testHandlePutForExistingUser()
9. testHandlePutForInvalidUser()
10. testHandlePutWithNoUser()

Tutorials and documentation followed for this project.
1. http://sparkjava.com/documentation.html
2. https://sparktutorials.github.io/2015/04/02/setting-up-a-spark-project-with-maven.html
3. https://sparktutorials.github.io/2015/04/03/spark-lombok-jackson-reduce-boilerplate.html
4. https://sparktutorials.github.io/2015/07/30/spark-testing-unit.html
5. https://docs.mongodb.com/ecosystem/drivers/java/
6. https://docs.mongodb.com/getting-started/java/


 









