drop database if exists patientTest;
create database if not exists patientTest;
use patientTest;

CREATE TABLE if not exists patient
(
    id            INT AUTO_INCREMENT NOT NULL,
    family        VARCHAR(44)        NOT NULL,
    given         VARCHAR(45)        NOT NULL,
    date_of_birth VARCHAR(10)        NOT NULL,
    sex           CHAR(6)            NOT NULL,
    address       VARCHAR(100)       NOT NULL,
    phone         VARCHAR(12)        NOT NULL,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;
