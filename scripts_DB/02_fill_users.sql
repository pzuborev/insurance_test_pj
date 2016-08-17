use db_insurance;

 delete from userrolelink;
 delete from userroles;
 delete from users;

insert into users (username, password)
values ('admin', '$2a$10$XpKnb2g.7yspfaSJU8dTmePPwydf8uvuk5qMWkYelC008gOuaKxLi'); -- admin

insert into users (username, password)
values ('user', 'user');

insert into userroles (id, name)
values (1, 'ADMIN');

insert into userroles (id, name)
values (2, 'USER');

insert into userrolelink (username, userroleid)
values ('admin', 1);

insert into userrolelink (username, userroleid)
values ('user', 2);

-- tokens

-- insert into tokens (username, token, lastused)
-- values ('admin', '1', sysdate());

commit;