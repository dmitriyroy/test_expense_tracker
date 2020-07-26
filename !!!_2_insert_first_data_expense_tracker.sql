-- INSERT INTO user_role (role_id,role_name,role_description) VALUES (0 /*not nullable*/,'s' /*not nullable*/,'s');
INSERT INTO user_role (role_id,role_name,role_description) VALUES (1,'ROLE_ADMIN','Role of the main Administrator');
INSERT INTO user_role (role_id,role_name,role_description) VALUES (2,'ROLE_USER','User role');

-- INSERT INTO "user" (user_id,role_id,username,first_name,second_name,encrypted_password,registration_dttm) VALUES (0 /*not nullable*/,0 /*not nullable*/,'s' /*not nullable*/,'s','s','s',{ts '2020-07-23 23:06:59.640'});
INSERT INTO "user" (role_id,username,first_name,second_name,encrypted_password,registration_dttm) VALUES (1,'admin','Administrator',null,'$2a$10$rf5X8zx45q1c5hDQ3Ejq9.ATwwfj/680wQtyJ3Xl27tpZqxJJI2AK',now());

