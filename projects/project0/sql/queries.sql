/* user queries */

-- insert user
insert into users (username, password, first_name, last_name, role) values ('myuser', 'mypass', initcap('calvin'), initcap('post'), 'USER') returning id;

-- select by username
select * from users where username = 'myuser';

-- select all users alphabetically
select * from users order by first_name asc, last_name asc;

-- select all users with EMPLOYEE role
select * from users where role = 'EMPLOYEE';

-- delete user with id 8
delete from users where id = 8;

/* item queries */

-- change status from 'new' to 'available' for items that are more than a week old
update items set status = 'available' where status = 'new' and created_on < current_date - 7;

-- select all items without 'owned' status
select * from items where status <> 'owned';

-- select all from items and associated rows from users
select * from items left join users on user_id = users.id order by status desc, name asc;

-- insert new item
insert into items (name, description) values ('Super Smash Bros.', 'An awesome game') returning id;

-- delete item id 9
delete from items where id = 9;

-- update item id 5
update items set name = 'Splatoon', description = 'Taking paintball to a whole new level.' where id = 5;

-- update item after offer accepted
update items set user_id = 4, balance = 40.58, status = 'owned' where id = 6;

-- select info for items owned by user id 4
select id, name, description, balance from items where user_id = 4 order by name asc;

-- update item balance when payment is received
update items set balance = 30.00 where id = 6;

/* offer queries */

-- select fields from offers and users for user id 8
select * from (
select created_on, status, amount, item_id, user_id, username 
from offers 
join users on user_id = id) o 
where item_id = 8;

-- select joined rows for all offers from offers/users/items
select date, status, amount, item_id, name, o.user_id, username 
from offers o 
join users u on u.id = o.user_id 
join items i on i.id = item_id;

-- transaction reject all other offers for an item when one is accepted
begin;
update offers set status = 'rejected' 
where item_id = 8 and user_id <> 5;
update offers set status = 'accepted' 
where item_id = 8 and user_id = 5; 
commit;

-- select all offers and associated rows for specific user
select date, status, amount, item_id, name, o.user_id, username 
from offers o 
join users u on u.id = o.user_id 
join items i on i.id = item_id 
having user_id = 5;

-- create new offer
insert into offers (amount, item_id, user_id) values (25.0, 2, 3);

-- reject single offer
update offers set status = 'rejected' where user_id = 3 and item_id = 5;

/* payment queries */

-- create new payment
insert into payments (date_due, amount_due, item_id) values (current_date + 7, 10.00, 6);

-- update payment when received
update payments set status = 'partial', amount_received = 7.00 where id = 6;

-- select payments for item id 6 that are NOT paid
select * from payments where item_id = 6 and status <> 'paid' order by date_due asc;

-- select all payments and associated rows from users/items
select * from payments p 
join (
select i.id item_id, name item_name, description, i.status item_status,
balance, user_id, username, first_name, last_name 
from items i 
join users u on user_id = u.id) r 
on r.item_id = p.item_id 
order by p.status desc, date_due asc;

select o.created_on, o.status, amount, item_id, name, o.user_id, username from offers o join users u on u.id = o.user_id join items i on i.id = item_id order by o.created_on desc;