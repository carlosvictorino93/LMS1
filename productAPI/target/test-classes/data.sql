INSERT INTO client
(name, cpf)
VALUES('Felipe', '89247332060');

INSERT INTO client
(name, cpf)
VALUES('Fabiola', '71872633099');

INSERT INTO client
(name, cpf)
VALUES('Fernanda', '32960237048');

INSERT INTO product
(product_code, quantity, price)
VALUES('100', 100, 10);

INSERT INTO product
(product_code, quantity, price)
VALUES('200', 100, 20);

INSERT INTO product
(product_code, quantity, price)
VALUES('300', 100, 30);

INSERT INTO purchase
(purchase_date, total_purchased, id_client)
VALUES('2022-04-10 22:53:49.448', 1800, 1);

INSERT INTO purchase
(purchase_date, total_purchased, id_client)
VALUES('2022-04-10 23:16:03.086', 1800, 2);

INSERT INTO purchase
(purchase_date, total_purchased, id_client)
VALUES('2022-04-10 23:16:03.086', 2200, 1);

INSERT INTO purchase_product
(id_product, id_purchase, quantity_purchased)
VALUES(3, 1, 50);

INSERT INTO purchase_product
(id_product, id_purchase, quantity_purchased)
VALUES(1, 1, 30);

INSERT INTO purchase_product
(id_product, id_purchase, quantity_purchased)
VALUES(3, 2, 50);

INSERT INTO purchase_product
(id_product, id_purchase, quantity_purchased)
VALUES(1, 2, 30);

INSERT INTO purchase_product
(id_product, id_purchase, quantity_purchased)
VALUES(3, 3, 50);

INSERT INTO purchase_product
(id_product, id_purchase, quantity_purchased)
VALUES(1, 3, 30);

INSERT INTO purchase_product
(id_product, id_purchase, quantity_purchased)
VALUES(2, 3, 20);




