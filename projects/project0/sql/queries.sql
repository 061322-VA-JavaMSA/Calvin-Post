/* transaction
begin;
subtract payment amount from user account
subtract payment amount from item balance
commit;
*/

-- Auto-generated SQL script #202206231406
DELETE FROM public.users
	WHERE id=2 returning exists(select from users where id=2);

-- Accept offer and reject all others on item/user ids
begin;
update offers 
set status = 'rejected'
where item_id = 3 and user_id <> 1;
update offers 
set status = 'accepted'
where item_id = 3 and user_id = 1;
commit;

-- select from offers join with users/items
select 
	date, 
	status, 
	amount, 
	item_id, 
	name, 
	o.user_id, 
	username 
from offers o 
join users u on u.id = o.user_id 
join items i on i.id = item_id;

-- select offers for given user
select * from (select
	date,
	status,
	amount,
	item_id,
	name,
	offers.user_id
from offers
join items on id = item_id)
as o
where user_id = 2;

-- select from offers for given item
select * from (select
	date,
	status,
	amount,
	offers.user_id,
	username,
	item_id
from offers
join users on id = user_id)
as o
where item_id = 3;

--select offers for given item


-- add employee level user
insert into users (level, username, password, first_name, last_name) values (2, 'cpost', 'pass', 'Calvin', 'Post');

-- add status column to items
alter table items add status varchar(9) default 'new';

-- add created_at column to items
alter table items add created_at date default current_date;

-- select available items
select * from items where status <> 'sold' order by status desc, name asc;