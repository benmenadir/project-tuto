Create database RESERVATION;

USE RESERVATION;

CREATE table USER
(
ID int not null auto_increment,
FIRST_NAME varchar(20),
LAST_NAME varchar(20),
EMAIL varchar(20),
PASSWORD varchar(20),
primary key (ID),
unique key (EMAIL)
);

create table FLIGHT
(
ID int not null auto_increment,
FLIGHT_NUMBER varchar(20) not null,
OPERATING_AIRLINES varchar(20) not null,
DEPARTURE_CITY varchar(20) not null,
ARRIVAL_CITY varchar(20) not null,
DATE_OF_DEPARTURE date not null, 
ESTIMATED_DEPARTURE_TIME timestamp default current_timestamp,
primary key (ID)
);

CReate table PASSENGER
(
	ID int not null auto_increment,
    FIRST_NAME varchar(20),
	LAST_NAME varchar(20),
    MIDDLE_NAME varchar(20),
    EMAIL varchar(20),
    PHONE varchar(20),
    primary key (ID)
);

create table RESERVATION 
(
 ID int not null auto_increment,
 CHECKED_IN tinyint (1),
 NUMBER_OF_BAGS int,
 PASSENGER_ID int,
 FLIGHT_ID int,
 CREATED timestamp default current_timestamp,
 primary key (ID),
 foreign key (PASSENGER_ID) references PASSENGER(ID) ON delete cascade,
 foreign key (FLIGHT_ID) references FLIGHT(ID)
);


//
insert into flight values(1,'AA1','American Airlines','AUS','NYC', 
					STR_TO_DATE('02-05-2018','%m-%d-%Y'),'2018-02-05 03:14:07')

insert into flight values(2,'AA2','American Airlines','AUS','NYC',STR_TO_DATE('02-05-2018','%m-%d-%Y'),'2018-02-05 05:14:07')

insert into flight values(3,'AA3','American Airlines','AUS','NYC', 
					STR_TO_DATE('02-05-2018','%m-%d-%Y'),'2018-02-05 06:14:07')

insert into flight values(4,'SW1','South West','AUS','NYC', 
					STR_TO_DATE('02-05-2018','%m-%d-%Y'),'2018-02-05 07:14:07')


insert into flight values(5,'UA1','United Airlines','NYC','DAL', 
					STR_TO_DATE('02-05-2018','%m-%d-%Y'),'2018-02-05 10:14:07')

insert into flight values(6,'UA1','American Airlines','AUS','NYC', 
					STR_TO_DATE('02-05-2018','%m-%d-%Y'),'2018-02-05 10:14:07')
                    

insert into flight values(7,'SW1','South West','AUS','NYC', 
					STR_TO_DATE('02-06-2018','%m-%d-%Y'),'2018-02-06 07:14:07')


insert into flight values(8,'SW2','South West','AUS','NYC', 
					STR_TO_DATE('02-06-2018','%m-%d-%Y'),'2018-02-06 08:14:07')

insert into flight values(9,'SW3','South West','NYC','DAL', 
					STR_TO_DATE('02-06-2018','%m-%d-%Y'),'2018-02-06 10:14:07')
 
insert into flight values(10,'UA1','United Airlines','NYC','DAL', 
					STR_TO_DATE('02-06-2018','%m-%d-%Y'),'2018-02-06 10:14:07')
 

// creation security authentification 


create table ROLE
(
ID int not null auto_increment,
NAME varchar(20),
primary key(ID)

);

create table user_role
(
user_id int,
role_id int,

foreign key(user_id)
references user(id),

foreign key(role_id)
references role(id)

);

insert into ROLE values(1,'ADMIN');
insert into user_role values(3,1);

select * from user;

select * from role;



