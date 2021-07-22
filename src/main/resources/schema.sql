
create table users(
    id int primary key AUTO_INCREMENT,
    username varchar(50) unique not null,
    password varchar(150) not null,
    disabled boolean default false
);

create table roles (
    id int primary key AUTO_INCREMENT,
    name varchar(50)
);

create table user_role (
    user_role_id int primary key AUTO_INCREMENT,
    user_id int references users(id),
    role_id int references roles(id)
);

create table superheroes(
    id int primary key AUTO_INCREMENT,
    name varchar(50) unique not null,
    real_name varchar(50) not null,
    occupation varchar(50),
    gender varchar(50),
    universe varchar(50)
);

