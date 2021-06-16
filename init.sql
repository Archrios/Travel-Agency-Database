DROP TABLE Scheduled_Event;
DROP TABLE Vacation_Event;
DROP TABLE Vacation_Schedule;
DROP TABLE Vacation_Transportation_Company;
DROP TABLE Vacation_Destination;
DROP TABLE Vacation_Hotel;
DROP TABLE Vacation_Tourist_Attraction;
DROP TABLE Cruise;
DROP TABLE Airline;
DROP TABLE Transportation_Company;
DROP TABLE Review;
DROP TABLE Tourist_Attraction;
DROP TABLE Destination;
DROP TABLE Hotel;
DROP TABLE Event;
DROP TABLE Booking;
DROP TABLE Manages;
DROP TABLE Vacation_Plan;
DROP TABLE Customer_Account;
DROP TABLE Employee_Account;
DROP TABLE Employee_Login;
DROP TABLE Customer_Login;


CREATE TABLE Customer_Login(
	Email CHAR(30) PRIMARY KEY,
	Password CHAR(30)
);

CREATE TABLE Customer_Account (
Customer_ID INTEGER PRIMARY KEY,
First_Name VARCHAR(100),
Last_Name VARCHAR(100),
Account_Tier INTEGER DEFAULT 0,
Email CHAR(30) NOT NULL UNIQUE,
FOREIGN KEY (Email) REFERENCES Customer_Login(Email) ON DELETE CASCADE
);

CREATE TABLE Employee_Login(
	Email CHAR(30) PRIMARY KEY,
	Password CHAR(30)
);

CREATE TABLE Employee_Account (
Employee_ID INTEGER PRIMARY KEY,
First_Name VARCHAR(100),
Last_Name VARCHAR(100),
Email CHAR(30),
FOREIGN KEY (Email) REFERENCES Employee_Login(Email) ON DELETE NO ACTION
);


CREATE TABLE Manages (
	Employee_ID INTEGER,
FOREIGN KEY (Employee_ID) REFERENCES Employee_Account (Employee_ID) ON DELETE CASCADE,
	Customer_ID INTEGER,
FOREIGN KEY (Customer_ID) REFERENCES Customer_Account (Customer_ID) ON DELETE CASCADE,
	PRIMARY KEY (Employee_ID, Customer_ID)
);

CREATE TABLE Vacation_Plan (
	Plan_ID INTEGER PRIMARY KEY,
	Start_Date DATE,
	End_Date DATE,
	Price REAL
);

CREATE TABLE Booking (
	Booking_ID INTEGER PRIMARY KEY,
	Number_of_Adults INTEGER,
	Number_of_Children INTEGER,
	Customer_ID INTEGER,
FOREIGN KEY (Customer_ID) REFERENCES Customer_Account(Customer_ID) ON DELETE CASCADE, 
	Employee_ID INTEGER,
FOREIGN KEY (Employee_ID) REFERENCES Employee_Account(Employee_ID) ON DELETE CASCADE, 
	Plan_ID INTEGER,
FOREIGN KEY (Plan_ID) REFERENCES Vacation_Plan(Plan_ID) ON DELETE CASCADE
);

CREATE TABLE Tourist_Attraction(
	Attraction_ID INTEGER PRIMARY KEY,
	Attraction_Name VARCHAR(100),
	Location VARCHAR (100)
);

CREATE TABLE Transportation_Company(
	Company_ID INTEGER PRIMARY KEY,
	Company_Name VARCHAR(100),
	Rating INTEGER
);

CREATE TABLE Cruise(
Company_ID INTEGER PRIMARY KEY,
FOREIGN KEY (Company_ID) REFERENCES Transportation_Company(Company_ID) ON DELETE CASCADE,
  	Features VARCHAR(100),
    	Cruise_Model VARCHAR(100)
);


CREATE TABLE Airline(
	Company_ID INTEGER PRIMARY KEY,
FOREIGN KEY (Company_ID) REFERENCES Transportation_Company(Company_ID) ON DELETE CASCADE,
	Plane_Model VARCHAR(100)
);

CREATE TABLE Destination(
	Destination_ID INTEGER PRIMARY KEY,
	Country VARCHAR(100),
	City VARCHAR(100)
);

CREATE TABLE Hotel(
	Hotel_ID INTEGER PRIMARY KEY,
	Company_Name VARCHAR(100),
	Rating INTEGER
);



CREATE TABLE Vacation_Transportation_Company (
	Plan_ID INTEGER,
FOREIGN KEY (Plan_ID) REFERENCES Vacation_Plan(Plan_ID) ON DELETE CASCADE,
	Company_ID INTEGER,
FOREIGN KEY (Company_ID) REFERENCES Transportation_Company(Company_ID) ON DELETE CASCADE,
	PRIMARY KEY(Plan_ID, Company_ID)
);

CREATE TABLE Vacation_Destination (
	Plan_ID INTEGER,
FOREIGN KEY (Plan_ID) REFERENCES Vacation_Plan(Plan_ID) ON DELETE CASCADE,
	Destination_ID INTEGER,
FOREIGN KEY (Destination_ID) REFERENCES Destination(Destination_ID) ON DELETE CASCADE,
	PRIMARY KEY(Plan_ID, Destination_ID)
);

CREATE TABLE Vacation_Hotel (
Plan_ID INTEGER,
FOREIGN KEY (Plan_ID) REFERENCES Vacation_Plan(Plan_ID) ON DELETE CASCADE,
	Hotel_ID INTEGER,
FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID) ON DELETE CASCADE,
	Start_Date DATE,
	End_Date DATE,
	PRIMARY KEY(Plan_ID, Hotel_ID)
);

CREATE TABLE Vacation_Tourist_Attraction(
	Plan_ID INTEGER,
FOREIGN KEY (Plan_ID) REFERENCES Vacation_Plan(Plan_ID) ON DELETE CASCADE,
	Attraction_ID INTEGER,
FOREIGN KEY (Attraction_ID) REFERENCES Tourist_Attraction(Attraction_ID) ON DELETE CASCADE,
	PRIMARY KEY(Plan_ID, Attraction_ID)
);

CREATE TABLE Vacation_Schedule(
	Plan_ID INTEGER,
FOREIGN KEY (Plan_ID) REFERENCES Vacation_Plan(Plan_ID) ON DELETE CASCADE,
	Schedule_ID INTEGER,
	PRIMARY KEY(Plan_ID, Schedule_ID)
);

CREATE TABLE Event(
	Event_ID INTEGER PRIMARY KEY,
	Description VARCHAR(100), 
	Event_date DATE
);

CREATE TABLE Scheduled_Event(
	Plan_ID INTEGER,
	Schedule_ID INTEGER,
FOREIGN KEY (Plan_ID, Schedule_ID) REFERENCES Vacation_Schedule(Plan_ID, Schedule_ID) ON DELETE CASCADE, 
	Event_ID INTEGER,
FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID) ON DELETE CASCADE,
	PRIMARY KEY (Plan_ID, Schedule_ID, Event_ID)
);

CREATE TABLE Vacation_Event(
	Plan_ID INTEGER,
	Schedule_ID INTEGER,
FOREIGN KEY (Plan_ID, Schedule_ID) REFERENCES Vacation_Schedule(Plan_ID, Schedule_ID) ON DELETE CASCADE,
	Event_ID INTEGER,
FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID) ON DELETE CASCADE,
	PRIMARY KEY (Plan_ID, Schedule_ID, Event_ID)
);

CREATE TABLE Review(
	Review_ID INTEGER PRIMARY KEY,
	Date_Posted DATE,
	Rating REAL,
	Description VARCHAR(100),
	Reviewer_Customer_ID INTEGER,
FOREIGN KEY (Reviewer_Customer_ID) REFERENCES Customer_Account(Customer_ID) ON DELETE CASCADE,
	Plan_ID INTEGER,
FOREIGN KEY (Plan_ID) REFERENCES Vacation_Plan(Plan_ID) ON DELETE CASCADE
);

INSERT INTO Employee_Login(Email, Password)
VALUES
("a1@adsa.com", "Password"),
("b2@adsa.com", "12345"),
("c3@123.com", "asldkj"),
("d4@321.com", "hunter2"),
("e5@gmail.com", "zq123");

INSERT INTO Employee_Account(Employee_ID, First_Name, Last_Name, Email) 
VALUES
(19001, "Evie", "Mikhaila", "a1@adsa.com"),
(19002, "Carlisa", "Faye", "b2@adsa.com"),
(19003, "Gwenevere", "Brennan", "c3@123.com"),
(19004, "Arvel", "Shelly", "d4@321.com"),
(19005, "Bud", "Emalee", "e5@gmail.com");

INSERT INTO Customer_Login(Email, Password) 
VALUES
("asd@asd.com", "Password"),
("asd@adsa.com", "12345"),
("123@123.com", "asldkj"),
("321@321.com", "hunter2"),
("hello@gmail.com", "zq123");

INSERT INTO Customer_Account(Customer_ID, First_Name, Last_Name, Account_Tier, Email) 
VALUES
(18001, "Arian", "Bridges", 0, "asd@asd.com"),
(18002, "Ralphy", "David", 0, "asd@adsa.com"),
(18003, "Franklin", "Rosas", 1, "123@123.com"),
(18004, "Ishaan", "Tyler", 2, "321@321.com"),
(18005, "Katie", "Valenzuela", 2, "hello@gmail.com");

INSERT INTO Manages(Employee_ID, Customer_ID) 
VALUES
(19001, 18001),
(19002, 18002),
(19003, 18003),
(19004, 18004),
(19005, 18005);

INSERT INTO Transportation_Company(Company_ID,  Company_Name,  Rating)
VALUES
(11001, "Company1", 3),
(11002, "Company2", 2),
(11003, "Company3", 4),
(11004, "Company4", 1),
(11005, "Company5", 1),
(11006, "Company6", 3),
(11007, "Company7", 2),
(11008, "Company8", 4),
(11009, "Company9", 1),
(11010, "Company10", 1);


INSERT INTO Hotel(Hotel_ID, Company_name, Rating) 
VALUES
(13001, "Hotel1", 3),
(13002, "Hotel2", 2),
(13003, "Hotel3", 4),
(13004, "Hotel4", 1),
(13005, "Hotel5", 1);

INSERT INTO Destination(Destination_ID, Country, City) 
VALUES
(12001, "Destination_1", "City_1"),
(12002, "Destination_2", "City_2"),
(12003, "Destination_3", "City_3"),
(12004, "Destination_4", "City_4"),
(12005, "Destination_5", "City_5");

INSERT INTO Airline(Company_ID, Plane_Model) 
VALUES
(11006, "Plane_Model_1"),
(11007, "Plane_Model_2"),
(11008, "Plane_Model_3"),
(11009, "Plane_Model_4"),
(11010, "Plane_Model_5");

INSERT INTO Cruise(Company_ID,  Features,  Cruise_Model)
VALUES
(11001, "Pool" ,"Cruise_Model_1"),
(11002, "Casino" ,"Cruise_Model_2"),
(11003, "Buffet" ,"Cruise_Model_3"),
(11004, "Nice view", "Cruise_Model_4"),
(11005, "Two pools", "Cruise_Model_5");



INSERT INTO Tourist_Attraction(Attraction_ID, Attraction_Name, Location)
VALUES
(14001, "UBC", "Vancouver"),
(14002, "SomeWherElse", "SomeRandomAddr"),
(14003, "ChezCakeShop", "ChezCakeCity"),
(14004, "Hi", "Hello"),
(14005, "SFU", "AlsoVancouver");

INSERT INTO Vacation_Plan(Plan_ID, Start_Date, End_Date, Price) 
VALUES
(10001, "2021-07-01", "2021-07-08", 800),
(10002, "2021-08-11", "2021-08-18", 2000),
(10003, "2021-11-03", "2021-11-10", 1499.99),
(10004, "2021-07-24", "2021-08-05", 1599.99),
(10005, "2022-01-05", "2022-01-19", 1000);

INSERT INTO Booking(Booking_ID, Number_of_Adults, Number_of_Children, Customer_ID, Employee_ID, Plan_ID)
VALUES
(20001, 1, 0, 18001, 19001, 10001),
(20002, 2, 2, 18002, 19002, 10002),
(20003, 7, 0, 18003, 19003, 10003),
(20004, 2, 5, 18004, 19004, 10004),
(20005, 2, 0, 18005, 19005, 10005),
(20006, 1, 0, 18002, 19001, 10001),
(20007, 1, 0, 18003, 19001, 10001),
(20008, 1, 0, 18004, 19001, 10001),
(20009, 1, 0, 18005, 19001, 10001);



INSERT INTO Vacation_Transportation_Company (Plan_ID, Company_ID) 
VALUES
(10001, 11001),
(10002, 11002),
(10003, 11003),
(10004, 11004),
(10005, 11005);


INSERT INTO Vacation_Destination(Plan_ID, Destination_ID) 
VALUES
(10001, 12001),
(10002, 12002),
(10003, 12003),
(10004, 12004),
(10005, 12005);

INSERT INTO Vacation_Hotel(Plan_ID, Hotel_ID, Start_Date, End_Date) 
VALUES
(10001, 13001, "2021-07-01", "2021-07-08"),
(10002, 13002, "2021-08-11", "2021-08-18"),
(10003, 13003, "2021-11-03", "2021-11-10"),
(10004, 13004, "2021-07-24", "2021-08-05"),
(10005, 13005, "2022-01-05", "2022-01-19");

INSERT INTO Vacation_Tourist_Attraction(Plan_ID, Attraction_ID) 
VALUES
(10001, 14001),
(10002, 14002),
(10003, 14003),
(10004, 14004),
(10005, 14005);

INSERT INTO Vacation_Schedule(Plan_ID, Schedule_ID) 
VALUES
(10001, 15001),
(10002, 15002),
(10003, 15003),
(10004, 15004),
(10005, 15005);

INSERT INTO Event(Event_ID, Description , Event_date) 
VALUES
(16001, "RaNdOm Str", "2021-07-01"),
(16002, "15002", "2021-08-11"),
(16003, "Wow", "2021-11-03"),
(16004, "Such", "2021-07-24"),
(16005, "Trip", "2022-01-05"),
(16006, "Wine Tasting", "2021-06-16"),
(16007, "Beer Tasting", "2021-06-17"),
(16008, "Sake Tasting", "2021-06-18"),
(16009, "Soju Tasting", "2021-06-19");


INSERT INTO Vacation_Event(Plan_ID, Schedule_ID, Event_ID) 
VALUES
(10001, 15001, 16001),
(10001, 15001, 16007),
(10001, 15001, 16008),
(10002, 15002, 16002),
(10002, 15002, 16008),
(10002, 15002, 16006),
(10003, 15003, 16003),
(10004, 15004, 16004),
(10005, 15005, 16005),
(10005, 15005, 16006),
(10005, 15005, 16008),
(10005, 15005, 16009);

INSERT INTO Review (Review_ID, Date_Posted, Rating, Description, Reviewer_Customer_ID, Plan_ID) 
VALUES
(17001, "2021-07-01", 3, "Apple Pie", 18001, 10001),
(17006, "2021-07-02", 4, "Apple Pie good", 18001, 10001),
(17002, "2021-08-11", 2, "15002", 18002, 10002),
(17003, "2021-11-03", 5, "Good", 18003, 10003),
(17004, "2021-07-24", 1, "Not Good", 18004, 10004),
(17005, "2022-01-05", 4, ":)", 18005, 10005);


