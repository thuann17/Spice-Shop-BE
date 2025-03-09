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
                      username varchar(30) not null unique,
                      full_name varchar(50) ,
                      email varchar(50),
                      number_phone varchar(12),
                      password varchar(225) not null,
                      create_at timestamp default current_timestamp,
                      update_at timestamp,
                      role_id int,
                      foreign key(role_id) references roles(id) on delete set null
);

-- Menu trong bàn tiệc
CREATE TABLE CateringMenus (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               name VARCHAR(100) NOT NULL,
                               price DECIMAL(10,2) NOT NULL
);
--
CREATE TABLE CateringMenuItems (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   dish_name VARCHAR(100) NOT NULL,
                                   price DECIMAL(10,2) NOT NULL,
                                   description varchar(225),
                                   menu_id INT,
                                   FOREIGN KEY (menu_id) REFERENCES CateringMenus(id) ON DELETE CASCADE
);
-- Giảm giá
CREATE TABLE Discounts (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           discount_value DECIMAL(10,2) NOT NULL,
                           start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           end_date TIMESTAMP
);

-- Gia vị
CREATE TABLE Spices (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description TEXT,
                        price DECIMAL(10,2) NOT NULL,
                        unit VARCHAR(50) NOT NULL,
                        quantity_available INT DEFAULT 0,
                        status BIT,
                        discount_id INT,
                        FOREIGN KEY (discount_id) REFERENCES Discounts(id) ON DELETE CASCADE
);
-- Hình ảnh
CREATE TABLE Images(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       url_image VARCHAR(225),
                       create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       status BIT,
                       spice_id INT,
                       FOREIGN KEY (spice_id) REFERENCES Spices(id) ON DELETE CASCADE
);
-- Kho hàng
CREATE TABLE Inventory (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           quantity INT NOT NULL,
                           purchase_price DECIMAL(10,2) NOT NULL,
                           purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           spice_id INT,
                           FOREIGN KEY (spice_id) REFERENCES Spices(id) ON DELETE CASCADE
);

