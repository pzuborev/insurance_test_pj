create database if not exists db_insurance;
use db_insurance;

drop table if exists tokens;
drop table if exists userrolelink;
drop table if exists userroles;
drop table if exists users;

create table users (
  username varchar(255),
  password varchar(255) not null,
  constraint pk_users primary key (username)
);

create table userroles (
	id int,
	name varchar(100) not null,
	constraint pk_usergroup primary key (id)
);

create table userrolelink (
	username varchar(255),
	userroleid int,
	constraint pk_userrolelink primary key (username, userroleid)
);
alter table userrolelink add constraint fk_userrole_role foreign key (userroleid)
references userroles (id);

alter table userrolelink add constraint fk_userrole_user foreign key (username)
references users (username) on delete cascade;

create index ix_userrole_user on userrolelink(username);

create table tokens(
  username varchar(255),
  token varchar(255),
  lastused datetime not null,
  constraint pk_tokens primary key (token)
);

alter table tokens add constraint fk_token_user foreign key (username)
references users(username) on  delete cascade;

create index ix_token_user on tokens(username);

