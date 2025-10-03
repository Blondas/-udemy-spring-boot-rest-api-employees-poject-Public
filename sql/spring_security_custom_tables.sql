DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS system_users;

CREATE TABLE system_users
(
    user_id VARCHAR(50)  NOT NULL,
    password VARCHAR(500) NOT NULL,
    active  BOOLEAN NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO system_users VALUES
('john', '{bcrypt}$2a$12$iO.3YX.K.rg5RpilXPiTmeuS0M9C6R5e/GW9ghPDB6xxx4uVuKQlG', TRUE),
('mary', '{bcrypt}$2a$12$iO.3YX.K.rg5RpilXPiTmeuS0M9C6R5e/GW9ghPDB6xxx4uVuKQlG', TRUE),
('susan', '{bcrypt}$2a$12$iO.3YX.K.rg5RpilXPiTmeuS0M9C6R5e/GW9ghPDB6xxx4uVuKQlG', TRUE);

CREATE TABLE `roles`
(
    user_id VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES `system_users` (user_id)
);

INSERT INTO roles (user_id, role)
VALUES
('john', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_EMPLOYEE'),
('mary', 'ROLE_MANAGER'),
('susan', 'ROLE_EMPLOYEE'),
('susan', 'ROLE_MANAGER'),
('susan', 'ROLE_ADMIN');