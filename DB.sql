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
-- Thêm tài khoản CUSTOMER
INSERT INTO users (username, full_name, email, number_phone, password, role_id)
VALUES ('customer01', 'Nguyen Van A', 'customer01@example.com', '0987654321', '123456',
        (SELECT id FROM roles WHERE role_name = 'CUSTOMER'));

-- Thêm tài khoản STAFF
INSERT INTO users (username, full_name, email, number_phone, password, role_id)
VALUES ('staff01', 'Tran Thi B', 'staff01@example.com', '0912345678', '123456',
        (SELECT id FROM roles WHERE role_name = 'STAFF'));

-- Thêm tài khoản MANAGER
INSERT INTO users (username, full_name, email, number_phone, password, role_id)
VALUES ('manager01', 'Le Van C', 'manager01@example.com', '0901234567', '123456',
        (SELECT id FROM roles WHERE role_name = 'MANAGER'));




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
                        position INT,
                        discount_id INT,
                        FOREIGN KEY (discount_id) REFERENCES Discounts(id) ON DELETE CASCADE
);

-- bảng chi tiết price
CREATE TABLE spice_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    spice_id INT NOT NULL,
    description TEXT,            -- Mô tả
    ingredients TEXT,            -- Thành phần
    color VARCHAR(255),          -- Màu sắc
    usage_instructions TEXT,     -- Hướng dẫn sử dụng
    storage TEXT,                -- Bảo quản
    smell VARCHAR(255),          -- Mùi vị
    expiration_period VARCHAR(255), -- Hạn sử dụng (VD: "12 tháng kể từ NSX")
    packaging_location VARCHAR(255), -- Đóng gói tại đâu
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (spice_id) REFERENCES spices(id) ON DELETE CASCADE
);
ALTER TABLE spice_details ADD COLUMN origin VARCHAR(255);
ALTER TABLE spice_details 
ADD COLUMN expiration_date DATE,
ADD COLUMN nutrition_info TEXT;



DROP TABLE spice_details;




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

--  tạo bảng dịch vụ
CREATE TABLE Service (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);
--  tạo bảng dịch vụ chi tiết
CREATE TABLE ServiceDetails (
    id INT AUTO_INCREMENT PRIMARY KEY,
    service_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (service_id) REFERENCES Service(id) ON DELETE CASCADE
);

-- thgeem dữ liệun dịch vụ
INSERT INTO Service (name, price, image) VALUES
('Bàn Tiệc Hải Sản', 2000000, 'https://spicyfoodstudio.com/wp-content/uploads/2023/03/dich-vu-chup-anh-mon-an.jpg'),
('Combo Lẩu Thái', 1500000, 'https://haithuycatering.com/image/62fc6e19b566c32ac1902c37/original.jpg'),
('Set Nướng BBQ', 1800000, 'https://ctmpalace.com/cs1/wp-content/uploads/2023/02/thuc-don-dam-cuoi-o-que-1.jpg');

INSERT INTO ServiceDetails (service_id, name, price, quantity) VALUES
(1, 'Tôm hùm nướng bơ tỏi', 500000, 2),
(1, 'Cua hoàng đế hấp', 700000, 1),
(1, 'Tôm hùm nướng bơ tỏi', 500000, 2),
(1, 'Cua hoàng đế hấp', 700000, 1),
(1, 'Cua hoàng đế hấp', 700000, 1),
(1, 'Cua hoàng đế hấp', 700000, 1),
(2, 'Bò Mỹ nhúng lẩu', 300000, 3),
(2, 'Nấm thập cẩm', 200000, 2),
(3, 'Ba chỉ heo nướng mật ong', 400000, 3),
(4, 'Tôm hùm nướng bơ tỏi', 500000, 2),
(4, 'Cua hoàng đế hấp', 700000, 1),
(5, 'Bò Mỹ nhúng lẩu', 300000, 3),
(5, 'Nấm thập cẩm', 200000, 2),
(6, 'Ba chỉ heo nướng mật ong', 400000, 3);
-----------------------------------------------------------------------------------------------------------------------

-- thêm data cho gia vịalter
INSERT INTO Spices (name, description, price, unit, quantity_available, status, position, discount_id) VALUES
('Muối biển', 'Muối biển tinh khiết, thích hợp cho mọi món ăn', 10.00, 'kg', 100, 1, 1, NULL),
('Tiêu đen', 'Tiêu đen nguyên hạt thơm nồng', 50.00, 'kg', 50, 1, 2, NULL),
('Bột nghệ', 'Bột nghệ nguyên chất, tốt cho sức khỏe', 35.00, 'kg', 70, 1, 3, NULL),
('Ớt bột', 'Ớt bột cay nồng, thích hợp cho món cay', 40.00, 'kg', 80, 1, 4, NULL),
('Quế', 'Quế khô có hương thơm đặc trưng', 55.00, 'kg', 30, 1, 5, NULL),
('Hoa hồi', 'Hoa hồi sấy khô, có mùi thơm đặc biệt', 60.00, 'kg', 40, 1, 6, NULL),
('Hành tím khô', 'Hành tím sấy khô, tiện lợi cho nấu ăn', 25.00, 'kg', 90, 1, 7, NULL);

-- thêm data cho ảnh gia vị
INSERT INTO Images (url_image, spice_id, status) VALUES
('https://doctormuoi.vn/wp-content/uploads/2020/12/cong-dung-cua-muoi-bien.jpg', 1, 1),
('https://suckhoedoisong.qltns.mediacdn.vn/Images/haiyen/2017/02/13/hat_tieu.jpg', 2, 1),
('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7t0y1xlH9Pv5ZZqTv5n1DrpEyM-3rotwI0g&s', 3, 1),
('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS728Jq0neYdmBC0HLeLPYMV-cMF2Qwh1Bhkg&s', 4, 1),
('https://media.loveitopcdn.com/6458/thumb/mua-tinh-dau-vo-que-nguyen-chat-o-dau-uy-tin-chat-luong-tai-tp-ho-chi-minh-cinnamon-essential-oil-1.png', 5, 1),
('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiilAv6QzGJqkRQYTO1VCGoZEkWox52NmSCA&s', 6, 1),
('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4QJPQjVxcijxODSeIsiy1EtjmLfCi7SMT7A&s', 7, 1);

-- thêm data cho chi tiết gia vị
INSERT INTO spice_details (spice_id, description, origin, ingredients, storage, smell, expiration_period, packaging_location) VALUES
(1, 'Muối biển sạch, giàu khoáng chất, thích hợp cho mọi món ăn.', 'Việt Nam', '100% muối biển', 'Bảo quản nơi khô ráo, thoáng mát', 'Không mùi', '24 tháng kể từ NSX', 'Việt Nam'),
(2, 'Hạt tiêu đen nguyên chất, vị cay nồng, thơm đặc trưng.', 'Việt Nam', 'Hạt tiêu đen nguyên chất', 'Bảo quản nơi khô ráo, thoáng mát', 'Thơm đặc trưng', '18 tháng kể từ NSX', 'Việt Nam'),
(3, 'Bột nghệ nguyên chất, tốt cho sức khỏe, màu vàng tự nhiên.', 'Ấn Độ', '100% củ nghệ khô', 'Bảo quản nơi thoáng mát, tránh ánh nắng trực tiếp', 'Mùi nghệ đặc trưng', '24 tháng kể từ NSX', 'Ấn Độ'),
(4, 'Ớt bột cay nồng, làm từ ớt đỏ tự nhiên, tăng vị cay cho món ăn.', 'Việt Nam', 'Ớt khô xay nhuyễn', 'Bảo quản kín, nơi khô ráo', 'Cay nồng', '12 tháng kể từ NSX', 'Việt Nam'),
(5, 'Quế khô có hương thơm đặc trưng, thích hợp làm gia vị nấu ăn.', 'Việt Nam', '100% vỏ quế', 'Bảo quản nơi khô ráo, tránh ẩm', 'Hương thơm quế', '24 tháng kể từ NSX', 'Việt Nam'),
(6, 'Hoa hồi sấy khô, có mùi thơm đặc biệt, làm gia vị và dược liệu.', 'Trung Quốc', 'Hoa hồi khô', 'Bảo quản kín, nơi thoáng mát', 'Thơm ngọt', '24 tháng kể từ NSX', 'Trung Quốc'),
(7, 'Hành tím khô sấy, giữ nguyên mùi vị, tiện lợi khi nấu ăn.', 'Việt Nam', '100% hành tím sấy khô', 'Bảo quản kín, tránh ẩm', 'Thơm nhẹ', '12 tháng kể từ NSX', 'Việt Nam');




