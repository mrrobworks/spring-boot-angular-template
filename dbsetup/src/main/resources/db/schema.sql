CREATE TABLE person(
	id INTEGER NOT NULL,
	firstname CHAR(25),
	lastname VARCHAR(25),
	PRIMARY KEY (id)
);

CREATE TABLE template_user(
	id VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE template_role(
	id VARCHAR(50) NOT NULL,
	description VARCHAR(250),
	PRIMARY KEY (id)
);

CREATE TABLE template_user_role(
	user_id VARCHAR(100) NOT NULL,
	role_id VARCHAR(50) NOT NULL,
	FOREIGN KEY(user_id) REFERENCES template_user(id),
	FOREIGN KEY(role_id) REFERENCES template_role(id)
);

INSERT INTO person VALUES(1, 'firstname1', 'lastname1');

INSERT INTO person VALUES(2, 'firstname2', 'lastname2');

INSERT INTO template_user VALUES('123456789012345678901');

INSERT INTO template_role VALUES('ROLE_SUPERUSER', 'Super-User');

INSERT INTO template_user_role VALUES('123456789012345678901', 'ROLE_SUPERUSER');