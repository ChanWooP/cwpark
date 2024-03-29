-- 메뉴
CREATE TABLE MENU (
	  ID INT NOT NULL AUTO_INCREMENT 
	, PARENT_NUM INT 
	, DEPTH INT
	, NAME VARCHAR(100) 
	, PATH VARCHAR(100)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY(id)
) DEFAULT CHARACTER SET UTF8;

INSERT INTO menu(parent_num, depth, NAME, path) 
VALUES (NULL, 0, '메뉴', null);
COMMIT;

-- 사용자(ADMIN으로 계정 생성하기)
CREATE TABLE MEMBER (
	  ID INT NOT NULL AUTO_INCREMENT
	, NAME VARCHAR(255) NOT NULL
	, ACCOUNT VARCHAR(255) NOT NULL UNIQUE
	, PASSWORD VARCHAR(255) NOT NULL
	, AUTHORITY INT
	, LOGIN_FAIL_CNT INT
	, LAST_ACCESS_DT  DATETIME
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
) DEFAULT CHARACTER SET UTF8;

-- 점포;
CREATE TABLE Store (
	  ID INT NOT NULL AUTO_INCREMENT
	, ACCOUNT INT NOT NULL UNIQUE
	, STORE_NAME VARCHAR(255) NOT NULL
	, BIZ_NO VARCHAR(255) NOT NULL
	, BIZ_NAME VARCHAR(255) NOT NULL
	, ZIPCODE VARCHAR(255)
	, ADDRESS1 VARCHAR(255)
	, ADDRESS2 VARCHAR(255)
	, TEL VARCHAR(255)
	, OPER_TIME VARCHAR(255)
	, LIKE_CNT INT
	, ETC VARCHAR(255)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (ACCOUNT) REFERENCES MEMBER (ID)
) DEFAULT CHARACTER SET UTF8;

-- 카테고리 drop table category
CREATE TABLE category (
	  ID INT NOT NULL AUTO_INCREMENT
	, STORE_ID INT NOT NULL
	, category_name VARCHAR(255)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (STORE_ID) REFERENCES store(id)
) DEFAULT CHARACTER SET UTF8;

-- 상품 drop table Item
CREATE TABLE Item (
	  ID INT NOT NULL AUTO_INCREMENT
	, CATEGORY_ID INT NOT NULL
	, item_name VARCHAR(255)
	, item_cost INT 
	, item_image VARCHAR(255)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (CATEGORY_ID) REFERENCES category(id)
) DEFAULT CHARACTER SET UTF8;

-- 추가상품 drop table PlusItem
CREATE TABLE PlusItem (
	  ID INT NOT NULL AUTO_INCREMENT
	, ITEM_ID INT NOT NULL
	, item_name VARCHAR(255)
	, item_cost INT 
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (ITEM_ID) REFERENCES Item(id)
) DEFAULT CHARACTER SET UTF8;

-- 매출
CREATE TABLE Sale (
	  ID INT NOT NULL AUTO_INCREMENT
	, STORE_ID INT NOT NULL
	, SALE_DATE VARCHAR(10) NOT NULL
	, AMT INT
	, QTY INT 
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (STORE_ID) REFERENCES Store(id)
) DEFAULT CHARACTER SET UTF8;

-- 상품 매출
CREATE TABLE SaleItem (
	  ID INT NOT NULL AUTO_INCREMENT
	, STORE_ID INT NOT NULL
	, ITEM_ID INT NOT NULL
	, SALE_ID INT NOT NULL
	, SALE_DATE VARCHAR(10) NOT NULL
	, AMT INT
	, QTY INT 
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (STORE_ID) REFERENCES Store(id)
	, FOREIGN KEY (ITEM_ID) REFERENCES Item(id)
	, FOREIGN KEY (SALE_ID) REFERENCES Sale(id)
) DEFAULT CHARACTER SET UTF8;

-- 추가상품 매출
CREATE TABLE SalePlusItem (
	  ID INT NOT NULL AUTO_INCREMENT
	, STORE_ID INT NOT NULL
	, ITEM_ID INT NOT NULL
	, SALE_ID INT NOT NULL
	, SALE_DATE VARCHAR(10) NOT NULL
	, AMT INT
	, QTY INT 
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (STORE_ID) REFERENCES Store(id)
	, FOREIGN KEY (ITEM_ID) REFERENCES PlusItem(id)
	, FOREIGN KEY (SALE_ID) REFERENCES Sale(id)
) DEFAULT CHARACTER SET UTF8;

-- drop table storeOpen
CREATE TABLE storeOpen (
	  ID INT NOT NULL AUTO_INCREMENT
	, STORE_ID INT NOT NULL UNIQUE
	, OPEN_YN INT NOT NULL
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (STORE_ID) REFERENCES Store(id)
) DEFAULT CHARACTER SET UTF8;

CREATE TABLE review (
	  ID INT NOT NULL AUTO_INCREMENT
	, STORE_ID INT NOT NULL
	, SALE_ID INT NOT null
	, SALE_DATE VARCHAR(10)
	, STAR_CNT INT 
	, REPLY_YN VARCHAR(1)
	, IMAGE VARCHAR(255)
	, CONTENTS VARCHAR(255)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (STORE_ID) REFERENCES Store(id)
	, FOREIGN KEY (SALE_ID) REFERENCES Sale(id)
) DEFAULT CHARACTER SET UTF8;

CREATE TABLE reply (
	  ID INT NOT NULL AUTO_INCREMENT
	, REVIEW_ID INT NOT null
	, CONTENTS VARCHAR(255)
	, REG_DATE DATETIME
	, REG_ID VARCHAR(255)
	, UPD_DATE DATETIME
	, UPD_ID VARCHAR(255)
	, PRIMARY KEY (id)
	, FOREIGN KEY (REVIEW_ID) REFERENCES Review(id) 
) DEFAULT CHARACTER SET UTF8;