CREATE DATABASE OCEANTECH;

USE OCEANTECH;

CREATE TABLE PRODUCT_CATALOG
(
    ID          VARCHAR(255) NOT NULL,
    NAME        VARCHAR(255),
    DESCRIPTION VARCHAR(4000),
    PRIMARY KEY (ID)
);

CREATE TABLE PRODUCT
(
    ID                 VARCHAR(255) NOT NULL,
    NAME               VARCHAR(255),
    DESCRIPTION        VARCHAR(4000),
    EFFECTIVE_DATE     DATE         NOT NULL,
    EXPIRATION_DATE    DATE,
    UNIT_PRICE         DECIMAL(19, 2),
    PRODUCT_CATALOG_ID VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

ALTER TABLE PRODUCT
    ADD CONSTRAINT PRODUCT_CATALOG_ID_FK_01
        FOREIGN KEY (PRODUCT_CATALOG_ID)
            REFERENCES PRODUCT_CATALOG (ID);

CREATE TABLE ORDER_ITEM
(
    ID              VARCHAR(255) NOT NULL,
    CUSTOMER_ID     VARCHAR(255) NOT NULL,
    PRODUCT         VARCHAR(255) NOT NULL,
    PRODUCT_CATALOG VARCHAR(255) NOT NULL,
    ORDER_DATE      DATE         NOT NULL,
    QUANTITY        INT,
    UNIT_PRICE      DECIMAL(19, 2),
    PRIMARY KEY (ID)
);

CREATE TABLE EMPLOYEE
(
    ID    INT UNSIGNED AUTO_INCREMENT NOT NULL,
    CODE  VARCHAR(255)                NOT NULL,
    NAME  VARCHAR(255)                NOT NULL,
    EMAIL VARCHAR(255),
    PHONE VARCHAR(20),
    AGE   INT UNSIGNED,
    PRIMARY KEY (ID)
);

CREATE TABLE PROVINCE
(
    ID   INT UNSIGNED AUTO_INCREMENT NOT NULL,
    CODE VARCHAR(225)                NOT NULL,
    NAME NVARCHAR(500)               NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE DISTRICT
(
    ID          INT UNSIGNED AUTO_INCREMENT NOT NULL,
    PROVINCE_ID INT UNSIGNED                NOT NULL,
    CODE        VARCHAR(225)                NOT NULL,
    NAME        NVARCHAR(225)               NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE COMMUNE
(
    ID          INT UNSIGNED AUTO_INCREMENT NOT NULL,
    DISTRICT_ID INT UNSIGNED                NOT NULL,
    CODE        VARCHAR(225)                NOT NULL,
    NAME        NVARCHAR(225)               NOT NULL,
    PRIMARY KEY (ID)
);

ALTER TABLE DISTRICT
    ADD CONSTRAINT PROVINCE_ID_FK_01
        FOREIGN KEY (PROVINCE_ID)
            REFERENCES PROVINCE (ID)
            ON DELETE CASCADE;

ALTER TABLE COMMUNE
    ADD CONSTRAINT DISTRICT_ID_FK_01
        FOREIGN KEY (DISTRICT_ID)
            REFERENCES DISTRICT (ID)
            ON DELETE CASCADE;

USE OCEANTECH;

ALTER TABLE EMPLOYEE
    ADD PROVINCE_ID INT UNSIGNED NOT NULL,
    ADD DISTRICT_ID INT UNSIGNED NOT NULL,
    ADD COMMUNE_ID INT UNSIGNED NOT NULL;


ALTER TABLE EMPLOYEE
    ADD CONSTRAINT EMPLOYEE_ID_FK_01
        FOREIGN KEY (PROVINCE_ID)
            REFERENCES PROVINCE(ID)
            ON DELETE CASCADE,
    ADD CONSTRAINT EMPLOYEE_ID_FK_02
        FOREIGN KEY (DISTRICT_ID)
            REFERENCES DISTRICT(ID)
            ON DELETE CASCADE,
    ADD CONSTRAINT EMPLOYEE_ID_FK_03
        FOREIGN KEY (COMMUNE_ID)
            REFERENCES COMMUNE(ID)
            ON DELETE CASCADE;

CREATE TABLE CERTIFICATE
(
    ID     INT UNSIGNED AUTO_INCREMENT NOT NULL,
    CODE   VARCHAR(225)                NOT NULL,
    NAME   VARCHAR(225)                NOT NULL,
    BEGIN  DATE                        NOT NULL,
    END    DATE                        NOT NULL,
    STATUS BIT                         NOT NULL,
    PRIMARY KEY (ID)
);

ALTER TABLE CERTIFICATE
    ADD PROVINCE_ID INT UNSIGNED NOT NULL,
    ADD EMPLOYEE_ID INT UNSIGNED NOT NULL;

ALTER TABLE CERTIFICATE
    ADD CONSTRAINT CERTIFICATE_ID_FK_01
        FOREIGN KEY (PROVINCE_ID)
            REFERENCES PROVINCE(ID)
            ON DELETE CASCADE,
    ADD CONSTRAINT CERTIFICATE_ID_FK_02
        FOREIGN KEY (EMPLOYEE_ID)
            REFERENCES EMPLOYEE(ID)
            ON DELETE CASCADE;


select *
from employee
where CODE = 'EMP001';


SELECT *
FROM COMMUNE;
SELECT *
FROM DISTRICT;

USE OCEANTECH;
SELECT *
FROM PROVINCE P INNER JOIN DISTRICT D on P.ID = D.PROVINCE_ID WHERE PROVINCE_ID = 2;
SELECT *
FROM CERTIFICATE;



SHOW CREATE TABLE DISTRICT;
