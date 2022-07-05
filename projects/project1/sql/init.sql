drop table if exists reimbursements, users, reimb_type, reimb_status, roles;

create table roles(
id serial primary key,
role varchar(10)
);

create table reimb_status(
id serial primary key,
status varchar(10)
);

create table reimb_type(
id serial primary key,
type varchar(20)
);

create table users(
id serial primary key,
username varchar(50) unique,
password varchar(50),
first_name varchar(100),
last_name varchar(100),
email varchar(150) unique,
role integer references roles(id)
);

create table reimbursements(
id serial primary key,
amount numeric,
submitted timestamp,
resolved timestamp,
description varchar(250),
receipt bytea,
author integer references users(id),
resolver integer references users(id),
status_id integer references reimb_status(id),
type_id integer references reimb_type(id)
);

insert into users (username, password, first_name, last_name, email) values ('nschruurs0', 'AzUpOX', 'Nananne', 'Schruurs', 'nschruurs0@bluehost.com');
insert into users (username, password, first_name, last_name, email) values ('seschelle1', 'On895S', 'Salim', 'Eschelle', 'seschelle1@ovh.net');
insert into users (username, password, first_name, last_name, email) values ('ndanshin2', 'lsOY0qWSkQ', 'Nikolia', 'Danshin', 'ndanshin2@usnews.com');
insert into users (username, password, first_name, last_name, email) values ('tkingzett3', '9BDmKHVys0', 'Tybi', 'Kingzett', 'tkingzett3@opensource.org');
insert into users (username, password, first_name, last_name, email) values ('solivier4', 'gQpW06IehVd', 'Sherman', 'Olivier', 'solivier4@omniture.com');
insert into users (username, password, first_name, last_name, email) values ('ewhatford5', 'us6bY7', 'Emili', 'Whatford', 'ewhatford5@linkedin.com');
insert into users (username, password, first_name, last_name, email) values ('kbrundle6', 'k5vvbjLtTNK', 'Kale', 'Brundle', 'kbrundle6@china.com.cn');
insert into users (username, password, first_name, last_name, email) values ('rcordingly7', 'gSCJMp', 'Rosalinde', 'Cordingly', 'rcordingly7@list-manage.com');
insert into users (username, password, first_name, last_name, email) values ('mchapelhow8', 'm1mNKg', 'Mace', 'Chapelhow', 'mchapelhow8@blogspot.com');
insert into users (username, password, first_name, last_name, email) values ('rrubin9', '5RCQGl1ckwi', 'Ruddie', 'Rubin', 'rrubin9@cbslocal.com');
insert into users (username, password, first_name, last_name, email) values ('kjorcka', 'hBctMJ2', 'Kristina', 'Jorck', 'kjorcka@feedburner.com');
insert into users (username, password, first_name, last_name, email) values ('lpestellb', 'H8leUW', 'Louella', 'Pestell', 'lpestellb@php.net');
insert into users (username, password, first_name, last_name, email) values ('wadiec', '75bBuFUxime', 'Woody', 'Adie', 'wadiec@technorati.com');
insert into users (username, password, first_name, last_name, email) values ('clorkinsd', '3aiWCe0CB', 'Caren', 'Lorkins', 'clorkinsd@google.cn');
insert into users (username, password, first_name, last_name, email) values ('cgappere', 'OGiBBLw795', 'Corly', 'Gapper', 'cgappere@google.co.jp');
insert into users (username, password, first_name, last_name, email) values ('sdyzartf', 'qOiMEB8dD82O', 'Sioux', 'Dyzart', 'sdyzartf@freewebs.com');
insert into users (username, password, first_name, last_name, email) values ('lbrashg', 'fl7QHSmC', 'Laurie', 'Brash', 'lbrashg@mapy.cz');
insert into users (username, password, first_name, last_name, email) values ('cpreh', 'ZygaPzl8z2pm', 'Carlye', 'Pre', 'cpreh@vinaora.com');
insert into users (username, password, first_name, last_name, email) values ('rphlippii', 'ViOuZK9qIU', 'Revkah', 'Phlippi', 'rphlippii@nydailynews.com');
insert into users (username, password, first_name, last_name, email) values ('ncowlamj', 'AcdQGU3mN', 'Norah', 'Cowlam', 'ncowlamj@mit.edu');
insert into users (username, password, first_name, last_name, email) values ('tmussingtonk', 'LsAsivXz7A9', 'Tatiana', 'Mussington', 'tmussingtonk@pinterest.com');
insert into users (username, password, first_name, last_name, email) values ('pilettl', 'Dz1BtMFRXEDz', 'Pansie', 'Ilett', 'pilettl@shutterfly.com');
insert into users (username, password, first_name, last_name, email) values ('fgreedingm', 'GaPzboHG', 'Francklyn', 'Greeding', 'fgreedingm@princeton.edu');
insert into users (username, password, first_name, last_name, email) values ('amattiaton', 'wIqVHzad', 'Amory', 'Mattiato', 'amattiaton@flickr.com');
insert into users (username, password, first_name, last_name, email) values ('cwhyberdo', 'gFxg3uVWgEf9', 'Cchaddie', 'Whyberd', 'cwhyberdo@harvard.edu');
insert into users (username, password, first_name, last_name, email) values ('rscamerdinep', 'NYdYrxp', 'Rowen', 'Scamerdine', 'rscamerdinep@fastcompany.com');
insert into users (username, password, first_name, last_name, email) values ('claityq', 'tkQ9HwgA', 'Camile', 'Laity', 'claityq@bloglovin.com');
insert into users (username, password, first_name, last_name, email) values ('aterbeckr', 'iVOpCgzWw', 'Augustina', 'Terbeck', 'aterbeckr@wp.com');
insert into users (username, password, first_name, last_name, email) values ('llightbourns', 'rzEoPy4gOwFo', 'Leshia', 'Lightbourn', 'llightbourns@wordpress.com');
insert into users (username, password, first_name, last_name, email) values ('tsimonott', 'PKvbskiQ2Mgl', 'Tallie', 'Simonot', 'tsimonott@addthis.com');