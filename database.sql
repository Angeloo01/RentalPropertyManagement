DROP DATABASE IF EXISTS RENTAL_MANAGEMENT;
CREATE DATABASE RENTAL_MANAGEMENT; 
USE RENTAL_MANAGEMENT;

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
	Username		varchar(25) not null,
	Password		varchar(25) not null,
    Email			varchar(25) default null,
    Type			int not null,
	primary key (Username)
);

DROP TABLE IF EXISTS PROPERTY;
CREATE TABLE PROPERTY (
	PropertyID		int NOT NULL auto_increment,
	Type			varchar(25) not null default 'House',
	Bedrooms		int not null default 0,
    Bathrooms		int not null default 0,
    Quadrant		varchar(25) not null default 'NW',
    Landlord		varchar(25) not null,
    Status 			varchar(25) not null default 'registered',# registered, active, rented, cancelled, suspended
	primary key (PropertyID),
    foreign key (Landlord) references USERS(Username)
);

#for every USER and PROPERTY pair, there is a row in this table
DROP TABLE IF EXISTS INBOX;
CREATE TABLE INBOX (
	Username		varchar(25) not null,
    PropertyID 	int not null,
    primary key (Username, PropertyID),
    foreign key (Username) references USERS(Username),
    foreign key (PropertyID) references PROPERTY(PropertyID)
);


DROP TABLE IF EXISTS MAIL;
CREATE TABLE MAIL (
	MailID	int not null auto_increment,
	Sender	varchar(25) not null,
    Receiver 	varchar(25) not null,
    Message		varchar(10000),
    primary key (MailID),
    foreign key (Sender) references USERS(Username),
    foreign key (Receiver) references USERS(Username)
);

DROP TABLE IF EXISTS INT_VARIABLES;
CREATE TABLE INT_VARIABLES (
	Name	varchar(25) not null,
    Value	int not null,
    primary key (Name)
);

INSERT INTO INT_VARIABLES(Name, Value)
VALUES
('FeeAmount', 0),
('FeePeriod', 0);

/*testing inserts
#Insert admin, test user, test landlord into user table
INSERT INTO USERS (Type, Username, Password, Email)
VALUES
(0,	'admin',	'admin', null),
(2,	'user',	'user', 'user@email.com'),
(1,	'landlord',	'landlord', 'landlord@email.com');

INSERT INTO PROPERTY (Landlord)
VALUES
('landlord');

INSERT INTO INBOX (Username, PropertyID)
VALUES
('user', 1);
*/
    