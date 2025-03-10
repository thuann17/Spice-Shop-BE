create database spice_shop;
use spice_shop;
create table roles (
    id int auto_increment primary key,
    role_name ENUM('CUSTOMER', 'STAFF', 'MANAGER') unique not null
);
INSERT INTO Roles (role_name) VALUES ('CUSTOMER');
INSERT INTO Roles (role_name) VALUES ('MANAGER');
INSERT INTO Roles (role_name) VALUES ('STAFF');

create table users(
	id int auto_increment primary key,
    full_name varchar(50) not null,
    email varchar(50),
    number_phone varchar(12),
    password varchar(225) not null,
    create_at timestamp default current_timestamp,
    update_at timestamp,
    role_id int,
    foreign key(role_id) references roles(id) on delete set null
);
