drop database if exists patient;
create database patient;
use patient;

CREATE TABLE patient
(
    id            INT AUTO_INCREMENT NOT NULL,
    family        VARCHAR(44)        NOT NULL,
    given         VARCHAR(45)        NOT NULL,
    date_of_birth VARCHAR(10)        NOT NULL,
    sex           VARCHAR(10)         NOT NULL,
    address       VARCHAR(100)       NOT NULL,
    phone         VARCHAR(12)        NOT NULL,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;


