--
--    Copyright 2010-2022 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       https://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--
-- https://github.com/mybatis/jpetstore-6/blob/master/src/main/resources/database/jpetstore-hsqldb-schema.sql

CREATE TABLE supplier
(
    suppid SERIAL PRIMARY KEY,
    name   VARCHAR(80),
    status VARCHAR(2) NOT NULL,
    addr1  VARCHAR(80),
    addr2  VARCHAR(80),
    city   VARCHAR(80),
    state  VARCHAR(80),
    zip    VARCHAR(5),
    phone  VARCHAR(80)
);

CREATE TABLE signon
(
    username VARCHAR(25) PRIMARY KEY,
    password VARCHAR(128) NOT NULL
);

CREATE TABLE account
(
    userid    VARCHAR(80) PRIMARY KEY,
    email     VARCHAR(80) NOT NULL,
    firstname VARCHAR(80) NOT NULL,
    lastname  VARCHAR(80) NOT NULL,
    status    VARCHAR(2),
    addr1     VARCHAR(80) NOT NULL,
    addr2     VARCHAR(40),
    city      VARCHAR(80) NOT NULL,
    state     VARCHAR(80) NOT NULL,
    zip       VARCHAR(20) NOT NULL,
    country   VARCHAR(20) NOT NULL,
    phone     VARCHAR(80) NOT NULL
);

CREATE TABLE profile
(
    userid      VARCHAR(80) PRIMARY KEY,
    langpref    VARCHAR(80) NOT NULL,
    favcategory VARCHAR(30),
    mylistopt   INTEGER,
    banneropt   INTEGER,
    FOREIGN KEY (userid) REFERENCES account (userid)
);

CREATE TABLE bannerdata
(
    favcategory VARCHAR(80) PRIMARY KEY,
    bannername  VARCHAR(255)
);

CREATE TABLE orders
(
    orderid         SERIAL PRIMARY KEY,
    userid          VARCHAR(80)                 NOT NULL,
    orderdate       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    shipaddr1       VARCHAR(80)                 NOT NULL,
    shipaddr2       VARCHAR(80),
    shipcity        VARCHAR(80)                 NOT NULL,
    shipstate       VARCHAR(80)                 NOT NULL,
    shipzip         VARCHAR(20)                 NOT NULL,
    shipcountry     VARCHAR(20)                 NOT NULL,
    billaddr1       VARCHAR(80)                 NOT NULL,
    billaddr2       VARCHAR(80),
    billcity        VARCHAR(80)                 NOT NULL,
    billstate       VARCHAR(80)                 NOT NULL,
    billzip         VARCHAR(20)                 NOT NULL,
    billcountry     VARCHAR(20)                 NOT NULL,
    courier         VARCHAR(80)                 NOT NULL,
    totalprice      NUMERIC(10, 2)              NOT NULL,
    billtofirstname VARCHAR(80)                 NOT NULL,
    billtolastname  VARCHAR(80)                 NOT NULL,
    shiptofirstname VARCHAR(80)                 NOT NULL,
    shiptolastname  VARCHAR(80)                 NOT NULL,
    creditcard      VARCHAR(80)                 NOT NULL,
    exprdate        VARCHAR(7)                  NOT NULL,
    cardtype        VARCHAR(80)                 NOT NULL,
    locale          VARCHAR(80)                 NOT NULL,
    FOREIGN KEY (userid) REFERENCES account (userid)
);

CREATE TABLE orderstatus
(
    orderid   INTEGER                     NOT NULL,
    linenum   INTEGER                     NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status    VARCHAR(2)                  NOT NULL,
    PRIMARY KEY (orderid, linenum),
    FOREIGN KEY (orderid) REFERENCES orders (orderid)
);

CREATE TABLE lineitem
(
    orderid   INTEGER        NOT NULL,
    linenum   INTEGER        NOT NULL,
    itemid    VARCHAR(10)    NOT NULL,
    quantity  INTEGER        NOT NULL,
    unitprice NUMERIC(10, 2) NOT NULL,
    PRIMARY KEY (orderid, linenum),
    FOREIGN KEY (orderid) REFERENCES orders (orderid)
);

CREATE TABLE category
(
    catid VARCHAR(10) PRIMARY KEY,
    name  VARCHAR(80),
    descn VARCHAR(255)
);

CREATE TABLE product
(
    productid VARCHAR(10) PRIMARY KEY,
    category  VARCHAR(10) NOT NULL,
    name      VARCHAR(80),
    descn     VARCHAR(255),
    FOREIGN KEY (category) REFERENCES category (catid)
);

CREATE INDEX productCat ON product (category);
CREATE INDEX productName ON product (LOWER(name));

CREATE TABLE item
(
    itemid    VARCHAR(10) PRIMARY KEY,
    productid VARCHAR(10) NOT NULL,
    listprice NUMERIC(10, 2),
    unitcost  NUMERIC(10, 2),
    supplier  INTEGER,
    status    VARCHAR(2),
    attr1     VARCHAR(80),
    attr2     VARCHAR(80),
    attr3     VARCHAR(80),
    attr4     VARCHAR(80),
    attr5     VARCHAR(80),
    FOREIGN KEY (productid) REFERENCES product (productid),
    FOREIGN KEY (supplier) REFERENCES supplier (suppid)
);

CREATE INDEX itemProd ON item (productid);

CREATE TABLE inventory
(
    itemid VARCHAR(10) PRIMARY KEY,
    qty    INTEGER NOT NULL,
    FOREIGN KEY (itemid) REFERENCES item (itemid)
);

CREATE TABLE system_date
(
    date_time TIMESTAMP WITH TIME ZONE
);