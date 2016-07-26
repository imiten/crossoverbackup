insert into users (username, password, enabled) values ('user', 'userpass', true);
insert into users (username, password, enabled) values ('admin', 'adminpass', true);

insert into authorities (username, authority) values ('user', 'ROLE_USER');
insert into authorities (username, authority) values ('admin', 'ROLE_ADMIN');
