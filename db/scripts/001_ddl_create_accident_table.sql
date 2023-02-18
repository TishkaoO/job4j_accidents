create table accident if not exists accident(
  id serial primary key,
  name varchar not null,
  description text no null,
  address varchar not null,
);