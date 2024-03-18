CREATE TABLE "order_table" (
	id bigserial NOT NULL,
	customer_id bigint,
	status int NOT NULL,
	notes varchar(100) NOT NULL,
	created_at timestamp NOT NULL default now(),
	updated_at timestamp NOT NULL default now(),

	CONSTRAINT fk_order_customer FOREIGN KEY ("customer_id") REFERENCES customer(id),
	CONSTRAINT order_pkey PRIMARY KEY (id)
);


CREATE TABLE orderItem (
	id bigserial NOT NULL,
	order_id bigint NOT NULL,
	product_id bigint NOT NULL,
	amount integer NOT NULL,

	CONSTRAINT fk_orderitem_order FOREIGN KEY ("order_id") REFERENCES "order_table"(id),
	CONSTRAINT orderitem_pkey PRIMARY KEY (id)
);


INSERT INTO "order_table" (id, customer_id, status, notes) VALUES (1, 1, 1, 'Favor não colocar sal');
INSERT INTO "order_table" (id, customer_id, status, notes) VALUES (2, 2, 0, 'Entregar no orfanato de gothan city');

ALTER SEQUENCE order_table_id_seq RESTART WITH 3;

INSERT INTO orderitem (id, order_id, product_id, amount) VALUES (1, 1, 2, 2 );

INSERT INTO orderitem (id, order_id, product_id, amount) VALUES (2, 2, 1, 500);
INSERT INTO orderitem (id, order_id, product_id, amount) VALUES (3, 2, 2, 500);
INSERT INTO orderitem (id, order_id, product_id, amount) VALUES (4, 2, 3, 500);
INSERT INTO orderitem (id, order_id, product_id, amount) VALUES (5, 2, 4, 500);

ALTER SEQUENCE orderitem_id_seq RESTART WITH 6;