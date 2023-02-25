create table if not exists accident (
 id serial primary key,
 name varchar not null,
 description text not null,
 address varchar not null,
 accident_type_id int references accident_type(id)
);