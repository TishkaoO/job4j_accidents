create table accident_type_rule if not exists accident_type_rule(
id serial primary key,
accident_id references accindent(id),
accident_type_id references accident_type(id),
rule_id references rule(id)
);