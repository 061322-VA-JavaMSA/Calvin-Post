/*
 * Init tables
 */
drop table if exists payments, offers, items, users cascade;

-- users
create table users(
id serial primary key,
role varchar(8) default 'USER',
username varchar(12) unique not null check(length(username) > 2),
password varchar(30) not null,
first_name varchar(30) not null,
last_name varchar(30) not null
);

--items
create table items(
id serial primary key,
created_on date default current_date,
status varchar(9) default 'new',
name varchar(30),
description text,
balance numeric,
user_id integer references users(id)
);

-- offers
create table offers(
created_on date default current_date,
status varchar(8) default 'pending',
amount numeric not null,
item_id integer references items(id),
user_id integer references users(id),
primary key (item_id, user_id)
);

-- payments
create table payments(
id serial primary key,
date_due date,
amount_due numeric,
amount_received numeric default 0.0,
status varchar(7) default 'unpaid',
item_id integer references items(id)
);

/*
 * MOCK DATA
 */

-- users
insert into users (username, password, first_name, last_name) values ('bdurbyn0', 'ueffg8xzrFxG', 'Bibby', 'Durbyn');
insert into users (username, password, first_name, last_name) values ('ifrayne1', '8S8w7dHc', 'Ivonne', 'Frayne');
insert into users (username, password, first_name, last_name) values ('oalfonso2', 'vlrfRMgtk1z4', 'Oralie', 'Alfonso');
insert into users (username, password, first_name, last_name) values ('geglin3', 'o53tky', 'Guinevere', 'Eglin');
insert into users (username, password, first_name, last_name) values ('nkilbee4', 'MaEQ5Y8', 'Ninetta', 'Kilbee');
insert into users (username, password, first_name, last_name) values ('ubuttner5', 'pC3ZAYrkr', 'Ula', 'Buttner');
insert into users (username, password, first_name, last_name) values ('mwhyborn6', 'ZrE77c1', 'Marchelle', 'Whyborn');
insert into users (username, password, first_name, last_name) values ('fsewart7', 'bl7riJHH', 'Feliza', 'Sewart');
insert into users (username, password, first_name, last_name) values ('ctolan8', 'pOenFz', 'Clark', 'Tolan');
insert into users (username, password, first_name, last_name) values ('obrockman9', 'gJFuSOT', 'Olenka', 'Brockman');

-- create employee manager accounts
insert into users (role, username, password, first_name, last_name) values ('EMPLOYEE', 'employee', 'password', 'Employee', 'Smith');
insert into users (role, username, password, first_name, last_name) values ('MANAGER', 'manager', 'password', 'Manager', 'Jones');

-- items
insert into items (created_on, status, name, description) values ('2022-05-04', 'available', 'Super Mario Bros.', 'Capra ibex');
insert into items (created_on, status, name, description) values ('2021-12-30', 'new', 'Super Mario Bros. 2', 'Spermophilus tridecemlineatus');
insert into items (created_on, status, name, description) values ('2021-12-05', 'available', 'Super Mario Bros. 3', 'Pelecanus conspicillatus');
insert into items (created_on, status, name, description) values ('2022-05-22', 'new', 'Super Mario World', 'Vulpes vulpes');
insert into items (created_on, status, name, description) values ('2022-01-27', 'available', 'Super Mario 64', 'Capreolus capreolus');
insert into items (created_on, status, name, description) values ('2022-03-02', 'available', 'Super Mario Sunshine', 'Geochelone elegans');
insert into items (created_on, status, name, description) values ('2022-05-14', 'new', 'New Super Mario Bros.', 'Zalophus californicus');
insert into items (created_on, status, name, description) values ('2021-08-08', 'available', 'Super Mario Galaxy', 'Paradoxurus hermaphroditus');
insert into items (created_on, status, name, description) values ('2021-11-29', 'available', 'Super Mario Galaxy 2', 'Cyrtodactylus louisiadensis');
insert into items (created_on, status, name, description) values ('2021-09-08', 'new', 'Super Mario 3D Land', 'Psittacula krameri');
insert into items (created_on, status, name, description) values ('2021-08-19', 'new', 'Super Mario 3D World', 'Bubulcus ibis');
insert into items (created_on, status, name, description) values ('2022-01-07', 'available', 'Super Mario Odyssey', 'Camelus dromedarius');

-- offers
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-05', 'pending', 25.64, 3, 2);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-07', 'pending', 22.64, 8, 4);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-02', 'pending', 93.71, 4, 2);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-06', 'pending', 34.95, 3, 6);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-16', 'pending', 75.97, 1, 7);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-21', 'pending', 96.73, 5, 1);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-11', 'pending', 67.38, 7, 1);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-11', 'pending', 75.64, 2, 7);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-06', 'pending', 97.78, 8, 5);
insert into offers (created_on, status, amount, item_id, user_id) values ('2022-06-08', 'pending', 30.82, 5, 3);