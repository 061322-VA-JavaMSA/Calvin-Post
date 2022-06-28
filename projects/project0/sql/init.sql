/*
 * Init tables
 */
drop table if exists transactions, payments, offers, items, users cascade;

-- users
create table users(
id serial primary key,
level integer default 1,
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
status varchar(6) default 'unpaid',
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
insert into users (level, username, password, first_name, last_name) values (2, 'employee', 'password', 'Employee', 'Smith');
insert into users (level, username, password, first_name, last_name) values (3, 'manager', 'password', 'Manager', 'Jones');

-- items
insert into items (created_on, status, name, description) values ('2022-01-12', 'new', 'Watch the Birdie', 'Beam Radiation of Other Bone using Photons 1 - 10 MeV');
insert into items (created_on, status, name, description) values ('2021-09-26', 'new', 'Evilspeak', 'Replacement of Right Tibia with Nonaut Sub, Perc Approach');
insert into items (created_on, status, name, description) values ('2021-07-13', 'available', 'Execution Squad', 'Removal of Drainage Device from Upper Bone, Open Approach');
insert into items (created_on, status, name, description) values ('2022-05-05', 'available', 'Bill Bailey: Qualmpeddler', 'Restriction of Urethra with Intraluminal Device, Endo');
insert into items (created_on, status, name, description) values ('2022-04-27', 'new', 'Wall, The (Die Wand)', 'Revision of Synth Sub in R Wrist Jt, Open Approach');
insert into items (created_on, status, name, description) values ('2022-02-27', 'available', 'Ali Baba Goes to Town', 'Replacement of Nasal Septum with Nonaut Sub, Perc Approach');
insert into items (created_on, status, name, description) values ('2021-09-16', 'new', 'Let''s Get Lost', 'Drainage of Aortic Body with Drainage Device, Open Approach');
insert into items (created_on, status, name, description) values ('2022-05-11', 'available', 'Clean and Sober', 'Excision of Left Orbit, Perc Endo Approach, Diagn');
insert into items (created_on, status, name, description) values ('2021-10-13', 'available', 'JFK: The Smoking Gun', 'Release Penis, Percutaneous Approach');
insert into items (created_on, status, name, description) values ('2021-09-23', 'new', 'Chaos', 'Restrict of L Up Lobe Bronc with Extralum Dev, Perc Approach');

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

-- owned items
insert into items (name, description, balance, user_id) values ('Summer Stock', 'Liver lacerat, mod-open', 29.69, 3);
insert into items (name, description, balance, user_id) values ('Road to Guantanamo, The', 'Bipol I currnt mixed-mod', 87.09, 9);
insert into items (name, description, balance, user_id) values ('Troll', 'Injury iliac artery', 57.82, 6);
insert into items (name, description, balance, user_id) values ('Parade, The (Parada)', 'Syphilitic keratitis', 37.63, 9);
insert into items (name, description, balance, user_id) values ('Adventures of Hajji Baba, The', 'Benign neo urinary NEC', 70.71, 2);
insert into items (name, description, balance, user_id) values ('You''re Gonna Miss Me', '1st deg burn arm-mult', 28.83, 7);
insert into items (name, description, balance, user_id) values ('Gift, The', 'Del w 2 deg lacerat-del', 54.54, 4);
insert into items (name, description, balance, user_id) values ('Cave of Forgotten Dreams', 'Unsp lym unsp xtrndl org', 27.12, 4);
insert into items (name, description, balance, user_id) values ('How I Unleashed World War II', 'Alchl gstritis w hmrhg', 55.11, 6);
insert into items (name, description, balance, user_id) values ('Get on the Bus', 'Poisoning-antitussives', 95.89, 6);
/*
-- payments
insert into payments (created_on, amount, item_id) values ('2022-06-05', 48.58, 5);
insert into payments (created_on, amount, item_id) values ('2022-04-28', 27.2, 9);
insert into payments (created_on, amount, item_id) values ('2021-08-10', 82.49, 5);
insert into payments (created_on, amount, item_id) values ('2021-09-25', 70.79, 3);
insert into payments (created_on, amount, item_id) values ('2021-08-20', 63.54, 3);
insert into payments (created_on, amount, item_id) values ('2021-12-14', 81.32, 10);
insert into payments (created_on, amount, item_id) values ('2022-01-15', 63.16, 3);
insert into payments (created_on, amount, item_id) values ('2021-10-22', 24.96, 4);
insert into payments (created_on, amount, item_id) values ('2022-01-28', 77.27, 8);
insert into payments (created_on, amount, item_id) values ('2021-12-01', 45.31, 7);
*/