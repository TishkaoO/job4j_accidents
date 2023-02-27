create table if not exists accident (
 id serial primary key,
 name varchar not null,
 description text not null,
 address varchar not null,
 type_id int not null references type(id)
);