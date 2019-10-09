DROP DATABASE IF EXISTS `mybatis_learning`;
CREATE DATABASE `mybatis_learning`;
USE `mybatis_learning`;

create table user(
	id int(11) not null auto_increment,
	username varchar(32) not null comment '用户名称',
	birthday datetime default null comment '生日',
	sex char(1) default null comment '性别',
	address varchar(256) default null comment '地址',
	primary key(id)
) engine=INNODB default charset=utf8;
