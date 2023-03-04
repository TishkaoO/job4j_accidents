insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$hoVpxMGjvQ0ve8rjL1RESOKhwPjOyVwFEp5Cx3GM0sulaPe2FZSZi',
(select id from authorities where authority = 'ROLE_ADMIN'));