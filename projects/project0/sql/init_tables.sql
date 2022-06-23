drop table if exists payments, offers, items, users;

create table users(
id serial primary key,
level integer default 1,
username varchar(12) unique not null check(length(username) > 2),
password varchar(30) not null,
first_name varchar(30) not null,
last_name varchar(30) not null
/*
street varchar(30) not null,
state char(2),
zip numeric(5,0) check(zip between 0 and 99999)
*/
);

create table items(
id serial primary key,
name varchar(30),
description text,
owner_id integer references users(id)
);

create table offers(
id serial primary key,
status varchar(8) default 'pending',
item_id integer references items(id),
amount numeric not null,
user_id integer references users(id)
);

create table payments(
id serial primary key,
date date default current_date,
amount numeric not null,
item_id integer references items(id),
owner_id integer references users(id)
);

/*
create table owned_items(
user_id int references users(id),
status varchar(20) default 'unpaid',
price numeric,
amount_paid numeric
);
*/