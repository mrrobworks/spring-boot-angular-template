CREATE TABLE app_user(
	id VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE app_role(
	id VARCHAR(50) NOT NULL,
	description VARCHAR(250),
	PRIMARY KEY (id)
);

CREATE TABLE app_user_role(
	user_id VARCHAR(100) NOT NULL,
	role_id VARCHAR(50) NOT NULL,
	FOREIGN KEY(user_id) REFERENCES app_user(id),
	FOREIGN KEY(role_id) REFERENCES app_role(id)
);

CREATE TABLE person(
	id INTEGER NOT NULL,
	firstname VARCHAR(25),
	lastname VARCHAR(25),
	PRIMARY KEY (id)
);

INSERT INTO app_role VALUES('ROLE_ADMIN', 'Administrator');
INSERT INTO app_role VALUES('ROLE_USER', 'Normal User');

INSERT INTO person VALUES(1, 'firstname1', 'lastname1');
INSERT INTO person VALUES(2, 'firstname2', 'lastname2');