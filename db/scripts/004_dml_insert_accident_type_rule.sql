insert into accident_type_rule (accident_id, accident_type_id, rule_id)
values (1, 2, 3);

insert into accident_type_rule (accident_id, accident_type_id, rule_id)
values (2, 1, 2);

insert into accident_type_rule (accident_id, accident_type_id, rule_id)
values (3, 3, 1);

insert into accident_type_rule (accident_id, accident_type_id, rule_id)
values (:accident_id, :accident_type_id, :rule_id);