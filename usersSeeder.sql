USE pharmhands_db;

SET foreign_key_checks = 0;

DELETE FROM patient_info;
DELETE FROM users;

ALTER TABLE users AUTO_INCREMENT = 1;
ALTER TABLE patient_info AUTO_INCREMENT = 1;



insert into users (email, full_name, is_deleted, password, phone_number, role_id, username) values ('qhallowes0@house.gov', 'Quincey Hallowes', 0, '1234qwerty', '8989924970', 1, 'qhallowes0');
insert into users (email, full_name, is_deleted, password, phone_number, role_id, username) values ('mcheltnam1@indiegogo.com', 'Myrta Cheltnam', 0, '1234qwerty', '2265394388', 2, 'mcheltnam1');
insert into users (email, full_name, is_deleted, password, phone_number, role_id, username) values ('tyellep2@dagondesign.com', 'Tildie Yellep', 0, '1234qwerty', '7586631835', 2, 'tyellep2');
insert into users (email, full_name, is_deleted, password, phone_number, role_id, username) values ('ftoupe3@google.com.hk', 'Felicia Toupe', 0, '1234qwerty', '6286791029', 1, 'ftoupe3');
insert into users (email, full_name, is_deleted, password, phone_number, role_id, username) values ('zdieton4@biglobe.ne.jp', 'Zeb Dieton', 0, '1234qwerty', '6699228867', 3, 'zdieton4');


insert into patient_info (address, city, dob, sex, state, user_id, zip) values ('41537 Valley Edge Junction', 'Santa Barbara', '2021-03-12', 'M', 'CA', 1, '20337');
insert into patient_info (address, city, dob, sex, state, user_id, zip) values ('42 Leroy Plaza', 'Arlington', '2020-08-25', 'M', 'VA', 2, '32688');
insert into patient_info (address, city, dob, sex, state, user_id, zip) values ('37568 Kennedy Terrace', 'San Jose', '2020-06-13', 'M', 'CA', 3, '40640');
insert into patient_info (address, city, dob, sex, state, user_id, zip) values ('23 Anhalt Point', 'Orlando', '2020-10-21', 'F', 'FL', 4, '43174');
insert into patient_info (address, city, dob, sex, state, user_id, zip) values ('5080 Acker Plaza', 'Jacksonville', '2020-06-16', 'M', 'FL', 5, '84849');


