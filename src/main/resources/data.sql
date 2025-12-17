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
-- https://github.com/mybatis/jpetstore-6/blob/master/src/main/resources/database/jpetstore-hsqldb-dataload.sql

INSERT INTO signon (username, password)
VALUES ('j2ee', '{noop}j2ee')
ON CONFLICT (username) DO UPDATE SET password = EXCLUDED.password;
INSERT INTO signon (username, password)
VALUES ('ACID', '{noop}ACID')
ON CONFLICT (username) DO UPDATE SET password = EXCLUDED.password;

INSERT INTO account (userid, email, firstname, lastname, status, addr1, addr2, city, state, zip,
                     country, phone)
VALUES ('j2ee', 'yourname@yourdomain.com', 'ABC', 'XYX', 'OK', '901 San Antonio Road',
        'MS UCUP02-206', 'Palo Alto',
        'CA', '94303', 'USA', '555-555-5555')
ON CONFLICT (userid) DO UPDATE SET email = EXCLUDED.email, firstname = EXCLUDED.firstname,
    lastname                             = EXCLUDED.lastname,
    status                               = EXCLUDED.status, addr1 = EXCLUDED.addr1,
    addr2                                = EXCLUDED.addr2, city = EXCLUDED.city,
    state                                = EXCLUDED.state, zip = EXCLUDED.zip,
    country                              = EXCLUDED.country, phone = EXCLUDED.phone;
INSERT INTO account (userid, email, firstname, lastname, status, addr1, addr2, city, state, zip,
                     country, phone)
VALUES ('ACID', 'acid@yourdomain.com', 'ABC', 'XYX', 'OK', '901 San Antonio Road', 'MS UCUP02-206',
        'Palo Alto', 'CA',
        '94303', 'USA', '555-555-5555')
ON CONFLICT (userid) DO UPDATE SET email = EXCLUDED.email, firstname = EXCLUDED.firstname,
    lastname                             = EXCLUDED.lastname,
    status                               = EXCLUDED.status, addr1 = EXCLUDED.addr1,
    addr2                                = EXCLUDED.addr2, city = EXCLUDED.city,
    state                                = EXCLUDED.state, zip = EXCLUDED.zip,
    country                              = EXCLUDED.country, phone = EXCLUDED.phone;

INSERT INTO profile (userid, langpref, favcategory, mylistopt, banneropt)
VALUES ('j2ee', 'english', 'DOGS', 1, 1)
ON CONFLICT (userid) DO UPDATE SET langpref = EXCLUDED.langpref, favcategory = EXCLUDED.favcategory,
    mylistopt                               = EXCLUDED.mylistopt, banneropt = EXCLUDED.banneropt;
INSERT INTO profile (userid, langpref, favcategory, mylistopt, banneropt)
VALUES ('ACID', 'english', 'CATS', 1, 1)
ON CONFLICT (userid) DO UPDATE SET langpref = EXCLUDED.langpref, favcategory = EXCLUDED.favcategory,
    mylistopt                               = EXCLUDED.mylistopt, banneropt = EXCLUDED.banneropt;

INSERT INTO bannerdata (favcategory, bannername)
VALUES ('FISH', '<image src="/images/banner_fish.gif">')
ON CONFLICT (favcategory) DO UPDATE SET bannername = EXCLUDED.bannername;
INSERT INTO bannerdata (favcategory, bannername)
VALUES ('CATS', '<image src="/images/banner_cats.gif">')
ON CONFLICT (favcategory) DO UPDATE SET bannername = EXCLUDED.bannername;
INSERT INTO bannerdata (favcategory, bannername)
VALUES ('DOGS', '<image src="/images/banner_dogs.gif">')
ON CONFLICT (favcategory) DO UPDATE SET bannername = EXCLUDED.bannername;
INSERT INTO bannerdata (favcategory, bannername)
VALUES ('REPTILES', '<image src="/images/banner_reptiles.gif">')
ON CONFLICT (favcategory) DO UPDATE SET bannername = EXCLUDED.bannername;
INSERT INTO bannerdata (favcategory, bannername)
VALUES ('BIRDS', '<image src="/images/banner_birds.gif">')
ON CONFLICT (favcategory) DO UPDATE SET bannername = EXCLUDED.bannername;

INSERT INTO category (catid, name, descn)
VALUES ('FISH', 'Fish',
        '<image src="/images/fish_icon.gif"><font size="5" color="blue"> Fish</font>')
ON CONFLICT (catid) DO UPDATE SET name = EXCLUDED.name, descn = EXCLUDED.descn;
INSERT INTO category (catid, name, descn)
VALUES ('DOGS', 'Dogs',
        '<image src="/images/dogs_icon.gif"><font size="5" color="blue"> Dogs</font>')
ON CONFLICT (catid) DO UPDATE SET name = EXCLUDED.name, descn = EXCLUDED.descn;
INSERT INTO category (catid, name, descn)
VALUES ('REPTILES', 'Reptiles',
        '<image src="/images/reptiles_icon.gif"><font size="5" color="blue"> Reptiles</font>')
ON CONFLICT (catid) DO UPDATE SET name = EXCLUDED.name, descn = EXCLUDED.descn;
INSERT INTO category (catid, name, descn)
VALUES ('CATS', 'Cats',
        '<image src="/images/cats_icon.gif"><font size="5" color="blue"> Cats</font>')
ON CONFLICT (catid) DO UPDATE SET name = EXCLUDED.name, descn = EXCLUDED.descn;
INSERT INTO category (catid, name, descn)
VALUES ('BIRDS', 'Birds',
        '<image src="/images/birds_icon.gif"><font size="5" color="blue"> Birds</font>')
ON CONFLICT (catid) DO UPDATE SET name = EXCLUDED.name, descn = EXCLUDED.descn;

INSERT INTO product (productid, category, name, descn)
VALUES ('FI-SW-01', 'FISH', 'Angelfish',
        '<image src="/images/fish1.gif">Salt Water fish from Australia')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('FI-SW-02', 'FISH', 'Tiger Shark',
        '<image src="/images/fish4.gif">Salt Water fish from Australia')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('FI-FW-01', 'FISH', 'Koi', '<image src="/images/fish3.gif">Fresh Water fish from Japan')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('FI-FW-02', 'FISH', 'Goldfish',
        '<image src="/images/fish2.gif">Fresh Water fish from China')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('K9-BD-01', 'DOGS', 'Bulldog', '<image src="/images/dog2.gif">Friendly dog from England')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('K9-PO-02', 'DOGS', 'Poodle', '<image src="/images/dog6.gif">Cute dog from France')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('K9-DL-01', 'DOGS', 'Dalmation',
        '<image src="/images/dog5.gif">Great dog for a Fire Station')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('K9-RT-01', 'DOGS', 'Golden Retriever', '<image src="/images/dog1.gif">Great family dog')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('K9-RT-02', 'DOGS', 'Labrador Retriever', '<image src="/images/dog5.gif">Great hunting dog')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('K9-CW-01', 'DOGS', 'Chihuahua', '<image src="/images/dog4.gif">Great companion dog')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('RP-SN-01', 'REPTILES', 'Rattlesnake',
        '<image src="/images/snake1.gif">Doubles as a watch dog')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('RP-LI-02', 'REPTILES', 'Iguana', '<image src="/images/lizard1.gif">Friendly green friend')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('FL-DSH-01', 'CATS', 'Manx',
        '<image src="/images/cat2.gif">Great for reducing mouse populations')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('FL-DLH-02', 'CATS', 'Persian',
        '<image src="/images/cat1.gif">Friendly house cat, doubles as a princess')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('AV-CB-01', 'BIRDS', 'Amazon Parrot',
        '<image src="/images/bird2.gif">Great companion for up to 75 years')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;
INSERT INTO product (productid, category, name, descn)
VALUES ('AV-SB-02', 'BIRDS', 'Finch', '<image src="/images/bird1.gif">Great stress reliever')
ON CONFLICT (productid) DO UPDATE SET category = EXCLUDED.category, name = EXCLUDED.name,
    descn                                      = EXCLUDED.descn;

INSERT INTO supplier (suppid, name, status, addr1, addr2, city, state, zip, phone)
VALUES (1, 'XYZ Pets', 'AC', '600 Avon Way', '', 'Los Angeles', 'CA', '94024', '212-947-0797')
ON CONFLICT (suppid) DO UPDATE SET name = EXCLUDED.name, status = EXCLUDED.status,
    addr1                               = EXCLUDED.addr1,
    addr2                               = EXCLUDED.addr2, city = EXCLUDED.city,
    state                               = EXCLUDED.state, zip = EXCLUDED.zip,
    phone                               = EXCLUDED.phone;
INSERT INTO supplier (suppid, name, status, addr1, addr2, city, state, zip, phone)
VALUES (2, 'ABC Pets', 'AC', '700 Abalone Way', '', 'San Francisco ', 'CA', '94024', '415-947-0797')
ON CONFLICT (suppid) DO UPDATE SET name = EXCLUDED.name, status = EXCLUDED.status,
    addr1                               = EXCLUDED.addr1,
    addr2                               = EXCLUDED.addr2, city = EXCLUDED.city,
    state                               = EXCLUDED.state, zip = EXCLUDED.zip,
    phone                               = EXCLUDED.phone;

INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-1', 'FI-SW-01', 16.50, 10.00, 1, 'P', 'Large')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-2', 'FI-SW-01', 16.50, 10.00, 1, 'P', 'Small')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-3', 'FI-SW-02', 18.50, 12.00, 1, 'P', 'Toothless')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-4', 'FI-FW-01', 18.50, 12.00, 1, 'P', 'Spotted')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-5', 'FI-FW-01', 18.50, 12.00, 1, 'P', 'Spotless')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-6', 'K9-BD-01', 18.50, 12.00, 1, 'P', 'Male Adult')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-7', 'K9-BD-01', 18.50, 12.00, 1, 'P', 'Female Puppy')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-8', 'K9-PO-02', 18.50, 12.00, 1, 'P', 'Male Puppy')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-9', 'K9-DL-01', 18.50, 12.00, 1, 'P', 'Spotless Male Puppy')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-10', 'K9-DL-01', 18.50, 12.00, 1, 'P', 'Spotted Adult Female')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-11', 'RP-SN-01', 18.50, 12.00, 1, 'P', 'Venomless')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-12', 'RP-SN-01', 18.50, 12.00, 1, 'P', 'Rattleless')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-13', 'RP-LI-02', 18.50, 12.00, 1, 'P', 'Green Adult')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-14', 'FL-DSH-01', 58.50, 12.00, 1, 'P', 'Tailless')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-15', 'FL-DSH-01', 23.50, 12.00, 1, 'P', 'With tail')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-16', 'FL-DLH-02', 93.50, 12.00, 1, 'P', 'Adult Female')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-17', 'FL-DLH-02', 93.50, 12.00, 1, 'P', 'Adult Male')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-18', 'AV-CB-01', 193.50, 92.00, 1, 'P', 'Adult Male')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-19', 'AV-SB-02', 15.50, 2.00, 1, 'P', 'Adult Male')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-20', 'FI-FW-02', 5.50, 2.00, 1, 'P', 'Adult Male')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-21', 'FI-FW-02', 5.29, 1.00, 1, 'P', 'Adult Female')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-22', 'K9-RT-02', 135.50, 100.00, 1, 'P', 'Adult Male')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-23', 'K9-RT-02', 145.49, 100.00, 1, 'P', 'Adult Female')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-24', 'K9-RT-02', 255.50, 92.00, 1, 'P', 'Adult Male')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-25', 'K9-RT-02', 325.29, 90.00, 1, 'P', 'Adult Female')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-26', 'K9-CW-01', 125.50, 92.00, 1, 'P', 'Adult Male')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-27', 'K9-CW-01', 155.29, 90.00, 1, 'P', 'Adult Female')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;
INSERT INTO item (itemid, productid, listprice, unitcost, supplier, status, attr1)
VALUES ('EST-28', 'K9-RT-01', 155.29, 90.00, 1, 'P', 'Adult Female')
ON CONFLICT (itemid) DO UPDATE SET productid = EXCLUDED.productid, listprice = EXCLUDED.listprice,
    unitcost                                 = EXCLUDED.unitcost, supplier = EXCLUDED.supplier,
    status                                   = EXCLUDED.status, attr1 = EXCLUDED.attr1;

INSERT INTO inventory (itemid, qty)
VALUES ('EST-1', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-2', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-3', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-4', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-5', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-6', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-7', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-8', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-9', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-10', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-11', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-12', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-13', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-14', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-15', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-16', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-17', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-18', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-19', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-20', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-21', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-22', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-23', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-24', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-25', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-26', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-27', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
INSERT INTO inventory (itemid, qty)
VALUES ('EST-28', 10000)
ON CONFLICT (itemid) DO UPDATE SET qty = EXCLUDED.qty;
