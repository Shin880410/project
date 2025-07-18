-- 刪除已存在的表格（先刪有FK依賴的表，再刪主表）
drop table if exists order_details;
drop table if exists orders;
drop table if exists items;
drop table if exists categories;
drop table if exists users;
drop table if exists roles;
-- 建立 roles (權限)
create table if not exists roles (
    role_id int auto_increment primary key,			 -- 權限編號
    name varchar(20) not null unique    			 -- 權限名稱
);

-- 建立 categories（商品分類）
create table if not exists categories (
    category_id int auto_increment primary key,		-- 分類編號
    name varchar(50) not null						-- 分類名稱
);

-- 建立 items（商品資料）
create table if not exists items (
    item_id int auto_increment primary key,			-- 商品編號
    name varchar(100) not null,						-- 商品名稱
    description text,								-- 商品描述
    price int not null,								-- 商品價格
    category_id int not null,						-- 商品分類
    is_available tinyint(1) default 1,				-- 是否上架
    create_time datetime default current_timestamp,	-- 上架時間
    is_deleted tinyint(1) default 0,				-- 軟刪除
    constraint fk_item_category foreign key (category_id) references categories(category_id)
);

-- 建立 users (使用者資料)
create table if not exists users (
	user_id int auto_increment primary key,         -- 會員編號
    name varchar(50) not null,                      -- 姓名
    phone varchar(30) not null unique,              -- 電話
	password varchar(128) not null,                 -- 密碼
	role_id int not null default 1,                 -- 權限
	last_login_time datetime,                       -- 最後登入時間
    update_time datetime,                           -- 資料異動時間
    create_time datetime default current_timestamp, -- 建立時間
    is_deleted tinyint(1) default 0,                -- 軟刪除
    constraint fk_users_role foreign key (role_id) references roles(role_id)
);

-- 建立 orders（訂單資料）
create table if not exists orders (
    order_id int auto_increment primary key,        -- 訂單編號
    user_id int not null,                           -- 下單會員
    total_amount int not null,                      -- 訂單總金額
    remark varchar(255),                            -- 備註
    pickup_type varchar(20),                        -- 取餐方式
    payment_type varchar(20),                       -- 付款方式
    pay_status varchar(20),                         -- 付款狀態
    order_status varchar(20),                       -- 訂單狀態
    create_time datetime default current_timestamp, -- 建立時間
    update_time datetime,                           -- 資料異動時間
    is_deleted tinyint(1) default 0,                -- 軟刪除
    constraint fk_order_user foreign key (user_id) references users(user_id)
); 

-- 建立 order_details（訂單明細）
create table if not exists order_details (
    order_detail_id int auto_increment primary key, -- 明細編號
    order_id int not null,                          -- 訂單編號
    item_id int not null,                           -- 商品編號
    options varchar(255),                           -- 客製化選項
    quantity int not null,                          -- 數量
    unit_price int not null,                        -- 單價
    subtotal int not null,                          -- 小計
    constraint fk_detail_order foreign key (order_id) references orders(order_id),
    constraint fk_detail_item foreign key (item_id) references items(item_id)
);


-- 建立範例資料
insert into roles (name) values 
('user'), ('admin'), ('super admin');

insert into categories (name) values 
('飲料'), ('早午餐'), ('甜點');

insert into items (name, description, price, category_id) values 
('熱美式', '大杯', 60, 1),
('歐姆蛋早午餐', '全套餐', 200, 2),
('蜂蜜鬆餅', '搭配鮮奶油', 120, 3);

insert into users (name, phone, password, role_id) values 
('Shin', '0912000111', '12345678', 1),
('Shikken', '0922555777', '00000000', 2);

insert into orders (user_id, total_amount, remark, order_status) values 
(1, 260, '請加熱', '未完成');

insert into order_details (order_id, item_id, options, quantity, unit_price, subtotal) values
(1, 1, '去冰', 2, 60, 120),
(1, 2, '蛋全熟', 1, 200, 200);

