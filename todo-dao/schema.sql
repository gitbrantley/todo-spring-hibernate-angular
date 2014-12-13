CREATE TABLE author (
	author_id bigserial primary key,
	username varchar(50),
	pass varchar(32)
);

CREATE TABLE todo (
	todo_id bigserial primary key,
	author_id bigint references author(author_id),
	todo_name varchar(100)
);


CREATE TABLE todo_item (
	todo_item_id bigserial primary key,
	todo_id bigint references todo(todo_id),
	order_index integer not null,
	value varchar(1000)
	UNIQUE(todo_id, order_index)
);