create table if not exists accident_rule (
 id serial primary key,
 rule_id int references rule(id),
 accident_id int references accident(id)
);