CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users VALUES
('john', '{noop}password', TRUE),
('mary', '{noop}password', TRUE),
('susan', '{noop}password', TRUE);

CREATE TABLE `authorities`
(
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    UNIQUE (username, authority),
    FOREIGN KEY (username) REFERENCES `users` (username)
);

INSERT INTO authorities (username, authority)
VALUES
('john', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_MANAGER'),
('susan', 'ROLE_EMPLOYEE'),
('susan', 'ROLE_MANAGER'),
('susan', 'ROLE_ADMIN');