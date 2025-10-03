DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN NOT NULL,
    PRIMARY KEY (username)
);

INSERT INTO users VALUES
('john', '{bcrypt}$2a$12$iO.3YX.K.rg5RpilXPiTmeuS0M9C6R5e/GW9ghPDB6xxx4uVuKQlG', TRUE),
('mary', '{bcrypt}$2a$12$iO.3YX.K.rg5RpilXPiTmeuS0M9C6R5e/GW9ghPDB6xxx4uVuKQlG', TRUE),
('susan', '{bcrypt}$2a$12$iO.3YX.K.rg5RpilXPiTmeuS0M9C6R5e/GW9ghPDB6xxx4uVuKQlG', TRUE);

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