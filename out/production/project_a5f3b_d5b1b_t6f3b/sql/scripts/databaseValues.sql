INSERT INTO Customer_Login(Email, Password)
VALUES
    ('asd@asd.com', 'Password'),
    ('asd@adsa.com', '12345'),
    ('123@123.com', 'asldkj'),
    ('321@321.com', 'hunter2'),
    ('hello@gmail.com', 'zq123');

INSERT INTO Customer_Account(Customer_ID, First_Name, Last_Name, Account_Tier, Email)
VALUES
    (18001, 'Arian', 'Bridges', 0, 'asd@asd.com'),
    (18002, 'Ralphy', 'David', 0, 'asd@adsa.com'),
    (18003, 'Franklin', 'Rosas', 1, '123@123.com'),
    (18004, 'Ishaan', 'Tyler', 2, '321@321.com'),
    (18005, 'Katie', 'Valenzuela', 2, 'hello@gmail.com');