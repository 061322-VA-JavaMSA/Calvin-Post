--drop tables if exist
drop table if exists sales_rep, customers, staff, offices;

--office
create table offices(
id serial primary key,
street varchar(30) not null,
city varchar(20) not null,
state varchar(2) not null,
zip varchar(5) not null
);

--staff
create table staff(
id serial primary key,
f_name varchar(20) not null,
l_name varchar(20) not null,
office_id integer references offices(id),
dob date not null
);

-- customer
create table customers(
id serial primary key,
f_name varchar(20),
l_name varchar(20)
);

--junction staff/customer
create table sales_rep(
rep_id integer references staff(id),
cus_id integer references customers(id),
primary key (rep_id, cus_id)
);