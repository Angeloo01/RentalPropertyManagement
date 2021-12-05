DROP DATABASE IF EXISTS RENTAL_MANAGEMENT;
CREATE DATABASE RENTAL_MANAGEMENT; 
USE RENTAL_MANAGEMENT;

DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
	UserID			int not null auto_increment,
	Username		varchar(25) not null,
	Password		varchar(25) not null,
    Email			varchar(25) default null,
    Type			int not null,
	primary key (UserID)
);

DROP TABLE IF EXISTS PROPERTY;
CREATE TABLE PROPERTY (
	PropertyID		int NOT NULL auto_increment,
	Type			varchar(25) default 'House',
	Bedrooms		int default 0,
    Bathrooms		int default 0,
    Quadrant		varchar(25) default 'NW',
    LandlordID		int not null,
	primary key (PropertyID),
    foreign key (LandlordID) references USERS(UserID)
);

#for every USER and PROPERTY pair, there is a row in this table
DROP TABLE IF EXISTS INBOX;
CREATE TABLE INBOX (
	UserID		int not null,
    PropertyID 	int not null,
    primary key (UserID, PropertyID),
    foreign key (UserID) references USERS(UserID),
    foreign key (PropertyID) references PROPERTY(PropertyID)
);


DROP TABLE IF EXISTS MAIL;
CREATE TABLE MAIL (
	SenderID	int not null,
    ReceiverID 	int not null,
    Message		varchar(300),
    primary key (SenderID, ReceiverID),
    foreign key (SenderID) references USERS(UserID),
    foreign key (ReceiverID) references USERS(UserID)
);

/*testing inserts
#Insert admin, test user, test landlord into user table
INSERT INTO USERS (UserID, Type, Username, Password, Email)
VALUES
(1,	0,	'admin',	'admin', null),
(2,	2,	'user',	'user', 'user@email.com'),
(3,	1,	'landlord',	'landlord', 'landlord@email.com');

INSERT INTO PROPERTY (LandlordID)
VALUES
(3);

INSERT INTO INBOX (UserID, PropertyID)
VALUES
(2, 1);
*/
    