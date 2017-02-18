DROP TABLE IF EXISTS accounts CASCADE ;
DROP TABLE IF EXISTS comments CASCADE ;
DROP TABLE IF EXISTS albums CASCADE ;

CREATE TABLE accounts
(
  "id serial" INTEGER PRIMARY KEY NOT NULL,
  login VARCHAR(45) NOT NULL,
  password VARCHAR(60) NOT NULL,
  email VARCHAR(60) NOT NULL,
  role INTEGER DEFAULT 1
);
CREATE TABLE comments
(
  id INTEGER PRIMARY KEY NOT NULL,
  author VARCHAR(50) NOT NULL,
  text VARCHAR(255) NOT NULL,
  product_id INTEGER NOT NULL
);
CREATE TABLE products
(
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  cost INTEGER NOT NULL,
  quantity INTEGER DEFAULT 0 NOT NULL,
  attack INTEGER DEFAULT 0 NOT NULL,
  defense INTEGER DEFAULT 0 NOT NULL,
  health INTEGER DEFAULT 0 NOT NULL,
  damage INTEGER DEFAULT 0 NOT NULL,
  id INTEGER DEFAULT nextval('products_id2_seq'::regclass) NOT NULL
);
CREATE UNIQUE INDEX accounts_login_uindex ON accounts (login);
CREATE UNIQUE INDEX accounts_email_uindex ON accounts (email);

INSERT INTO accounts (login, password, email, role) VALUES ('admin', 'admin', 'admin@mail.ru', 2);
INSERT INTO accounts (login, password, email, role) VALUES ('user', 'user', 'user@mail.ru', 1);

INSERT INTO products (name, description, cost, quantity, attack, defense, health, damage) VALUES ('angel', 'Hates Devils', 3000, 2, 20, 20, 200, 50);