create database projectdb;

use projectdb;

create table document (
id BIGINT not null,
name varchar(100) not null,
data BLOB not null,
primary key(id)
)



