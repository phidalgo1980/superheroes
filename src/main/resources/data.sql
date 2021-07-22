insert into roles (name) values('ROLE_ADMIN');
insert into roles (name) values('ROLE_USER');

insert into users (username, password) values('admin', '$2a$10$qxn8MgUR3xqvEs6VGIQWjuCxf2mkY1uILR.d7lEk6BVSIYnrHuu3q');
insert into users (username, password) values('user', '$2a$10$qEitvQrrMkdZPuTXq/AUmu.PXcRGY1/.fjXb568M9NFrp1QUuxUFq');

insert into user_role (user_id, role_id) values(1,1);
insert into user_role (user_id, role_id) values(2,2);

insert into superheroes (name, real_name, occupation, gender, universe) values ('Superman', 'Clark Kent', 'Journalist', 'Male', 'DC Comics');
insert into superheroes (name, real_name, occupation, gender, universe) values ('Batman', 'Bruce Wayne', 'Businessman', 'Male', 'DC Comics');
insert into superheroes (name, real_name, occupation, gender, universe) values ('Wonder Woman', 'Diana Prince', 'Secretary', 'Female', 'DC Comics');
insert into superheroes (name, real_name, occupation, gender, universe) values ('Spiderman', 'Peter Parker', 'Photographer', 'Male', 'Marvel');
insert into superheroes (name, real_name, occupation, gender, universe) values ('Hulk', 'Bruce Banner', 'Doctor', 'Male', 'Marvel');
insert into superheroes (name, real_name, occupation, gender, universe) values ('Black Widow', 'Natasha Romanoff', 'Spy', 'Female', 'Marvel');


