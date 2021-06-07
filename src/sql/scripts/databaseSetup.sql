CREATE TABLE Customer_Login(
	Email CHAR(30) PRIMARY KEY,
	Password CHAR(30)
);

CREATE TABLE Customer_Account (
    Customer_ID INTEGER PRIMARY KEY,
    First_Name CHAR(20),
    Last_Name CHAR(20),
    Account_Tier INTEGER DEFAULT 0,
    Email CHAR(30) NOT NULL UNIQUE,
    FOREIGN KEY(Email) REFERENCES Customer_Login(Email) ON DELETE CASCADE
);