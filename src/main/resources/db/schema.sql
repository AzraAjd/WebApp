CREATE TABLE `store_db`.`user` (
id int primary key auto_increment not null,
user_name varchar(32) unique,
email varchar(40),
password varchar(32),
role varchar(32)
);

CREATE TABLE `store_db`.`article` (
id int primary key auto_increment not null,
title varchar(32) unique,
summary varchar(500),
photo_link varchar(200),
price float
);
