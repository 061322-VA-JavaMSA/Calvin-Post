drop table if exists reimbursements, users, reimb_type, reimb_status, roles;

create table roles(
id serial primary key,
role varchar(10) not null
);

create table reimb_status(
id serial primary key,
status varchar(10) not null
);

create table reimb_type(
id serial primary key,
type varchar(20) not null
);

create table users(
id serial primary key,
username varchar(50) unique not null,
password varchar(50) not null,
first_name varchar(100) not null,
last_name varchar(100)not null,
email varchar(150) unique not null,
role_id integer references roles(id) not null
);

create table reimbursements(
id serial primary key,
amount NUMERIC(19,2) not null,
submitted timestamp default current_timestamp not null,
resolved timestamp,
description varchar(250),
receipt bytea,
author_id integer references users(id) not null,
resolver_id integer references users(id),
status_id integer references reimb_status(id) not null,
type_id integer references reimb_type(id) not null
);

INSERT INTO roles (role) VALUES ('EMPLOYEE');
INSERT INTO roles (role) VALUES ('MANAGER');

INSERT INTO reimb_status (status) VALUES ('PENDING');
INSERT INTO reimb_status (status) VALUES ('APPROVED');
INSERT INTO reimb_status (status) VALUES ('DENIED');

INSERT INTO reimb_type (type) VALUES ('LODGING');
INSERT INTO reimb_type (type) VALUES ('TRAVEL');
INSERT INTO reimb_type (type) VALUES ('FOOD');
INSERT INTO reimb_type (type) VALUES ('OTHER');

insert into users (username, password, first_name, last_name, email, role_id) values ('nschruurs0', 'AzUpOX', 'Nananne', 'Schruurs', 'nschruurs0@bluehost.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('seschelle1', 'On895S', 'Salim', 'Eschelle', 'seschelle1@ovh.net', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('ndanshin2', 'lsOY0qWSkQ', 'Nikolia', 'Danshin', 'ndanshin2@usnews.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('tkingzett3', '9BDmKHVys0', 'Tybi', 'Kingzett', 'tkingzett3@opensource.org', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('solivier4', 'gQpW06IehVd', 'Sherman', 'Olivier', 'solivier4@omniture.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('ewhatford5', 'us6bY7', 'Emili', 'Whatford', 'ewhatford5@linkedin.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('kbrundle6', 'k5vvbjLtTNK', 'Kale', 'Brundle', 'kbrundle6@china.com.cn', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('rcordingly7', 'gSCJMp', 'Rosalinde', 'Cordingly', 'rcordingly7@list-manage.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('mchapelhow8', 'm1mNKg', 'Mace', 'Chapelhow', 'mchapelhow8@blogspot.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('rrubin9', '5RCQGl1ckwi', 'Ruddie', 'Rubin', 'rrubin9@cbslocal.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('kjorcka', 'hBctMJ2', 'Kristina', 'Jorck', 'kjorcka@feedburner.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('lpestellb', 'H8leUW', 'Louella', 'Pestell', 'lpestellb@php.net', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('wadiec', '75bBuFUxime', 'Woody', 'Adie', 'wadiec@technorati.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('clorkinsd', '3aiWCe0CB', 'Caren', 'Lorkins', 'clorkinsd@google.cn', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('cgappere', 'OGiBBLw795', 'Corly', 'Gapper', 'cgappere@google.co.jp', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('sdyzartf', 'qOiMEB8dD82O', 'Sioux', 'Dyzart', 'sdyzartf@freewebs.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('lbrashg', 'fl7QHSmC', 'Laurie', 'Brash', 'lbrashg@mapy.cz', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('cpreh', 'ZygaPzl8z2pm', 'Carlye', 'Pre', 'cpreh@vinaora.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('rphlippii', 'ViOuZK9qIU', 'Revkah', 'Phlippi', 'rphlippii@nydailynews.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('ncowlamj', 'AcdQGU3mN', 'Norah', 'Cowlam', 'ncowlamj@mit.edu', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('tmussingtonk', 'LsAsivXz7A9', 'Tatiana', 'Mussington', 'tmussingtonk@pinterest.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('pilettl', 'Dz1BtMFRXEDz', 'Pansie', 'Ilett', 'pilettl@shutterfly.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('fgreedingm', 'GaPzboHG', 'Francklyn', 'Greeding', 'fgreedingm@princeton.edu', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('amattiaton', 'wIqVHzad', 'Amory', 'Mattiato', 'amattiaton@flickr.com', 1);
insert into users (username, password, first_name, last_name, email, role_id) values ('cwhyberdo', 'gFxg3uVWgEf9', 'Cchaddie', 'Whyberd', 'cwhyberdo@harvard.edu', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('rscamerdinep', 'NYdYrxp', 'Rowen', 'Scamerdine', 'rscamerdinep@fastcompany.com', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('claityq', 'tkQ9HwgA', 'Camile', 'Laity', 'claityq@bloglovin.com', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('aterbeckr', 'iVOpCgzWw', 'Augustina', 'Terbeck', 'aterbeckr@wp.com', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('llightbourns', 'rzEoPy4gOwFo', 'Leshia', 'Lightbourn', 'llightbourns@wordpress.com', 2);
insert into users (username, password, first_name, last_name, email, role_id) values ('tsimonott', 'PKvbskiQ2Mgl', 'Tallie', 'Simonot', 'tsimonott@addthis.com', 2);

insert into reimbursements (amount, description, author_id, status_id, type_id) values (10.3, 'Repair Paraganglion Extremity, Percutaneous Approach', 1, 1, 3);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (71.62, 'Release Right Large Intestine, Percutaneous Approach', 2, 1, 3);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (106.91, 'Supplement Stomach, Pylorus with Nonaut Sub, Open Approach', 4, 1, 2);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (608.25, 'Drainage of Left Hepatic Duct, Open Approach', 7, 1, 2);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (894.35, 'Replace R Parietal Bone w Synth Sub, Perc Endo', 4, 2, 1);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (711.7, 'Repair Left Hepatic Duct, Percutaneous Approach', 3, 2, 2);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (168.74, 'Drainage of R Wrist Bursa/Lig with Drain Dev, Perc Approach', 8, 3, 2);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (255.0, 'Occlusion L Up Lobe Bronc w Intralum Dev, Open', 10, 3, 1);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (218.36, 'Removal of Int Fix from R Acromioclav Jt, Open Approach', 12, 1, 4);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (328.74, 'Revision of Drainage Device in R Low Extrem, Open Approach', 2, 1, 1);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (120.35, 'Fluoroscopy of Pelvis using High Osmolar Contrast', 5, 1, 3);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (849.53, 'Bypass L Atrium to R Pulm Art w Autol Art, Perc Endo', 9, 2, 3);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (698.27, 'Repair Right Mandible, Percutaneous Endoscopic Approach', 9, 2, 1);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (439.07, 'Monitoring of Gastrointestinal Motility, Endo', 11, 3, 1);
insert into reimbursements (amount, description, author_id, status_id, type_id) values (82.14, 'Supplement Left Renal Vein with Synth Sub, Perc Approach', 12, 3, 1);