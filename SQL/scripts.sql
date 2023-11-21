CREATE DATABASE IF NOT EXISTS infuse;

CREATE TABLE IF NOT EXISTS orders(
	id bigint(20) NOT NULL AUTO_INCREMENT,
	control_code varchar(255),
	custumer_code bigint(20),
	order_date datetime,
	order_value double,
	product_name varchar(255),
	product_value double,
	quantity int(11),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS client(
	id bigint(20) NOT NULL AUTO_INCREMENT,
	client_name varchar(255),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS message(
	id bigint(20) NOT NULL AUTO_INCREMENT,
	error_date datetime,
	msg varchar(255),
	PRIMARY KEY (id)
);

INSERT INTO client (client_name) VALUES ('Client 1');
INSERT INTO client (client_name) VALUES ('Client 2');
INSERT INTO client (client_name) VALUES ('Client 3');
INSERT INTO client (client_name) VALUES ('Client 4');
INSERT INTO client (client_name) VALUES ('Client 5');
INSERT INTO client (client_name) VALUES ('Client 6');
INSERT INTO client (client_name) VALUES ('Client 7');
INSERT INTO client (client_name) VALUES ('Client 8');
INSERT INTO client (client_name) VALUES ('Client 9');
INSERT INTO client (client_name) VALUES ('Client 10');