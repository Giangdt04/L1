USE oceantech;

INSERT INTO PRODUCT_CATALOG (ID, NAME) VALUES ('a70a0447-9a7d-4b64-9890-b5616c1f309c', 'Bakery');
INSERT INTO PRODUCT_CATALOG (ID, NAME) VALUES ('75c36466-aeb2-445f-b3f0-be8b25f30db3', 'Dairy');
INSERT INTO PRODUCT_CATALOG (ID, NAME) VALUES ('94a0e5bb-373d-4288-8832-768b4b5319cb', 'Butchery');
INSERT INTO PRODUCT_CATALOG (ID, NAME) VALUES ('99edf5d9-7dea-4e94-87a4-43f869aa3c38', 'Fruit & Vegetables');
INSERT INTO PRODUCT_CATALOG (ID, NAME) VALUES ('b678d1e6-488c-45d5-a113-0077b60aa67b', 'Household');

INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('0013b1b6-329b-4b12-bfd7-066cea338343', 'Ciabatta Rolls', 'These sandwich rolls are at once incredibly flavorful and exceedingly light.', {ts '2018-12-21'}, 4.25, 'a70a0447-9a7d-4b64-9890-b5616c1f309c');
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('0dac2a17-1af2-4a42-9449-2012d9559bc3', 'Pumpkin Pie', 'A secret blend of traditional spices and combine them with sweet and spicy pumpkin custard.', {ts '2018-12-21'}, 45.99, 'a70a0447-9a7d-4b64-9890-b5616c1f309c');
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('20d25a1b-b804-4aea-8154-3d99b90c30f7', 'Milk', 'Refreshing and delicious, milk is ready for your crunchy cereal and morning coffee.', {ts '2018-12-21'}, 24.50, '75c36466-aeb2-445f-b3f0-be8b25f30db3');
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('21ea1d67-bd16-4d80-acf3-3e7857f1c190', 'Ground Beef', 'Raised without antibiotics and full of flavor, this beef is the base of big, juicy burgers, savory meat loaf and rich Bolognese sauce.', {ts '2018-12-21'}, 100.50, '94a0e5bb-373d-4288-8832-768b4b5319cb');
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('11928edc-31da-4686-8ff8-139c9af8f19a', 'Loin Chops', 'Flown in from the sheep-rich plains of Australia, these flavorful, juicy chops are ready to be barbecue.', {ts '2018-12-21'}, 76.50, '94a0e5bb-373d-4288-8832-768b4b5319cb');
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('579718d9-5426-4dd4-a54b-b40c02b74097', 'Brocolli', 'A hearty and tasty vegetable which is rich in dozens of nutrients.', {ts '2018-12-21'}, 11.25, '99edf5d9-7dea-4e94-87a4-43f869aa3c38');
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('622e1cf8-01f9-460b-9620-cebb1d406137', 'Water melon', 'All the sweetness, crunch, and knockout juiciness of the classic summertime melon.', {ts '2018-12-21'}, 89.90, '99edf5d9-7dea-4e94-87a4-43f869aa3c38');
INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, EFFECTIVE_DATE, UNIT_PRICE, PRODUCT_CATALOG_ID) 
VALUES ('04dd8d6b-eb2a-43ad-86f5-02857e6632d8', 'Potato', 'Starchy with low moisture content, perfect for baked potatoes or french fries.', {ts '2018-12-21'}, 29.99, '99edf5d9-7dea-4e94-87a4-43f869aa3c38');

INSERT INTO ORDER_ITEM (ID, CUSTOMER_ID, PRODUCT, PRODUCT_CATALOG, ORDER_DATE, QUANTITY, UNIT_PRICE) 
VALUES ('22551188-6e15-44c1-9f07-3ad5a549ff77', 'spacehunter', 'Ciabatta Rolls', 'Bakery', {ts '2018-12-21'}, 6, 4.25);
INSERT INTO ORDER_ITEM (ID, CUSTOMER_ID, PRODUCT, PRODUCT_CATALOG, ORDER_DATE, QUANTITY, UNIT_PRICE) 
VALUES ('6a487924-c277-460f-b107-0fd233590981', 'spacehunter', 'Ground Beef', 'Butchery', {ts '2018-12-21'}, 1, 100.50);
INSERT INTO ORDER_ITEM (ID, CUSTOMER_ID, PRODUCT, PRODUCT_CATALOG, ORDER_DATE, QUANTITY, UNIT_PRICE) 
VALUES ('8e3c954e-6118-4bae-b708-e9c8b1e9c0c6', 'mythbuster', 'Loin Chops', 'Butchery', {ts '2018-12-21'}, 2, 76.50);
INSERT INTO ORDER_ITEM (ID, CUSTOMER_ID, PRODUCT, PRODUCT_CATALOG, ORDER_DATE, QUANTITY, UNIT_PRICE) 
VALUES ('146d94bc-0556-4fdf-8bcc-6f61499f87bf', 'grilldad', 'Milk', 'Dairy', {ts '2018-12-21'}, 2, 24.50);
INSERT INTO ORDER_ITEM (ID, CUSTOMER_ID, PRODUCT, PRODUCT_CATALOG, ORDER_DATE, QUANTITY, UNIT_PRICE) 
VALUES ('8444c41c-c12e-490e-a9e1-91fc76610ae3', 'grilldad', 'Ground Beef', 'Butchery', {ts '2018-12-21'}, 1, 100.50);
INSERT INTO ORDER_ITEM (ID, CUSTOMER_ID, PRODUCT, PRODUCT_CATALOG, ORDER_DATE, QUANTITY, UNIT_PRICE) 
VALUES ('55c1aced-1b1b-4bcc-82fe-7812bf29c08a', 'grilldad', 'Potato', 'Fruit & Vegetables', {ts '2018-12-21'}, 1, 29.99);

INSERT INTO EMPLOYEE (CODE, NAME, EMAIL, PHONE, AGE, PROVINCE_ID, DISTRICT_ID, COMMUNE_ID)
VALUES
('EMP001', 'Nguyễn Văn A', 'nva@example.com', '0987654321', 30, 1, 1, 1),  -- Thuộc Hà Nội, quận Ba Đình, Phường Trúc Bạch
('EMP002', 'Trần Thị B', 'ttb@example.com', '0978654321', 28, 2, 2, 2),    -- Thuộc Hồ Chí Minh, quận Tân Bình, Phường 15
('EMP003', 'Lê Văn C', 'lvc@example.com', '0967654321', 35, 3, 3, 3),   -- Thuộc Đà Nẵng, quận Hải Châu, Phường Hòa Thuận
('EMP004', 'Phạm Hồng D', 'phd@example.com', '0901234567', 40, 1, 1, 1),  -- Hà Nội, quận Ba Đình, Phường Trúc Bạch
('EMP005', 'Đỗ Thị E', 'dte@example.com', '0912234567', 25, 2, 2, 2),    -- Hồ Chí Minh, quận Tân Bình, Phường 15
('EMP006', 'Ngô Văn F', 'nvf@example.com', '0923234567', 32, 3, 3, 3),   -- Đà Nẵng, quận Hải Châu, Phường Hòa Thuận
('EMP007', 'Bùi Thị G', 'btg@example.com', '0934234567', 29, 1, 1, 1),   -- Hà Nội, quận Ba Đình, Phường Trúc Bạch
('EMP008', 'Trịnh Văn H', 'tvh@example.com', '0945234567', 38, 2, 2, 2), -- Hồ Chí Minh, quận Tân Bình, Phường 15
('EMP009', 'Hoàng Thị I', 'hti@example.com', '0956234567', 45, 3, 3, 3), -- Đà Nẵng, quận Hải Châu, Phường Hòa Thuận
('EMP010', 'Cao Văn J', 'cvj@example.com', '0967234567', 22, 1, 1, 1),   -- Hà Nội, quận Ba Đình, Phường Trúc Bạch
('EMP011', 'Lý Thị K', 'ltk@example.com', '0978234567', 33, 2, 2, 2),    -- Hồ Chí Minh, quận Tân Bình, Phường 15
('EMP012', 'Đặng Văn L', 'dvl@example.com', '0989234567', 31, 3, 3, 3),  -- Đà Nẵng, quận Hải Châu, Phường Hòa Thuận
('EMP013', 'Lưu Thị M', 'ltm@example.com', '0990234567', 27, 1, 1, 1),   -- Hà Nội, quận Ba Đình, Phường Trúc Bạch
('EMP014', 'Nguyễn Văn N', 'nvn@example.com', '0910234567', 36, 2, 2, 2),-- Hồ Chí Minh, quận Tân Bình, Phường 15
('EMP015', 'Phan Thị O', 'pto@example.com', '0921234567', 34, 3, 3, 3);  -- Đà Nẵng, quận Hải Châu, Phường Hòa Thuận

-- Fake data cho bảng PROVINCE (Tỉnh/Thành phố)
INSERT INTO PROVINCE (ID, CODE, NAME) VALUES
(1, 'HN', 'Hà Nội'),
(2, 'HCM', 'Thành phố Hồ Chí Minh'),
(3, 'DN', 'Đà Nẵng'),
(4, 'QNH', 'Quảng Ninh'),
(5, 'CT', 'Cần Thơ');

-- Fake data cho bảng DISTRICT (Huyện/Thị xã)
INSERT INTO DISTRICT (ID, PROVINCE_ID, CODE, NAME) VALUES
-- Hà Nội
(1, 1, 'DA', 'Đông Anh'),
(2, 1, 'GL', 'Gia Lâm'),
(3, 1, 'ST', 'Sơn Tây'),
(4, 1, 'SC', 'Sóc Sơn'),
-- TP Hồ Chí Minh
(5, 2, 'CC', 'Củ Chi'),
(6, 2, 'HM', 'Hóc Môn'),
(7, 2, 'BC', 'Bình Chánh'),
-- Đà Nẵng
(8, 3, 'HC', 'Hải Châu'),
(9, 3, 'ST', 'Sơn Trà'),
(10, 3, 'LC', 'Liên Chiểu'),
-- Quảng Ninh
(11, 4, 'HL', 'Hạ Long'),
(12, 4, 'CM', 'Cẩm Phả'),
-- Cần Thơ
(13, 5, 'NB', 'Ninh Kiều'),
(14, 5, 'BT', 'Bình Thủy');

-- Fake data cho bảng COMMUNE (Xã/Phường)
INSERT INTO COMMUNE (ID, DISTRICT_ID, CODE, NAME) VALUES
-- Đông Anh, Hà Nội
(1, 1, 'NA', 'Nam Hồng'),
(2, 1, 'VB', 'Vân Nội'),
-- Gia Lâm, Hà Nội
(3, 2, 'YP', 'Yên Phụ'),
(4, 2, 'DT', 'Đông Dư'),
-- Củ Chi, TP. HCM
(5, 5, 'TL', 'Tân Thạnh Đông'),
(6, 5, 'PN', 'Phú Mỹ Hưng'),
-- Hải Châu, Đà Nẵng
(7, 8, 'TT', 'Thuận Phước'),
(8, 8, 'HH', 'Hòa Thuận Tây'),
-- Hạ Long, Quảng Ninh
(9, 11, 'BA', 'Bãi Cháy'),
(10, 11, 'HA', 'Hà Khánh'),
-- Ninh Kiều, Cần Thơ
(11, 13, 'XT', 'Xuân Khánh'),
(12, 13, 'TA', 'Tân An');


INSERT INTO CERTIFICATE (CODE, NAME, BEGIN, END, STATUS, PROVINCE_ID, EMPLOYEE_ID)
VALUES
('CERT001', 'Chứng chỉ An ninh Mạng', '2023-01-01', '2026-01-01', 1, 1, 98),
('CERT002', 'Chứng chỉ Quản lý Dự án', '2022-05-15', '2025-05-15', 1, 2, 99),
('CERT003', 'Chứng chỉ Kế toán', '2021-07-01', '2024-07-01', 1, 3, 100),
('CERT004', 'Chứng chỉ Lập trình Java', '2023-03-20', '2026-03-20', 1, 1, 101),
('CERT005', 'Chứng chỉ Quản lý Tài chính', '2022-08-10', '2025-08-10', 1, 5, 102),
('CERT006', 'Chứng chỉ Thiết kế Đồ họa', '2020-09-01', '2023-09-01', 0, 3, 103),
('CERT007', 'Chứng chỉ Chuyên gia Cloud', '2021-10-25', '2024-10-25', 1, 4, 104),
('CERT008', 'Chứng chỉ Kỹ thuật AI', '2022-11-10', '2025-11-10', 1, 2, 105),
('CERT009', 'Chứng chỉ Lập trình Python', '2023-12-01', '2026-12-01', 1, 4, 106),
('CERT010', 'Chứng chỉ Phát triển Web', '2021-01-05', '2024-01-05', 1, 5, 107);

select * from employee