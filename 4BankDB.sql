
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema 4BankDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `4BankDB` ;

-- -----------------------------------------------------
-- Schema 4BankDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `4BankDB` DEFAULT CHARACTER SET utf8 ;
USE `4BankDB` ;

-- -----------------------------------------------------
-- Table `4BankDB`.`USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `4BankDB`.`USERS` ;

create table USERS
(
	idUSERS int auto_increment
		primary key,
	username varchar(45) not null,
	passsword varchar(45) not null,
	name varchar(45) not null,
	surname varchar(45) not null,
	address varchar(70) not null,
	email varchar(45) not null,
	NIF varchar(45) not null,
	phone_number varchar(45) not null,
	constraint username_UNIQUE
		unique (username),
	constraint email_UNIQUE
		unique (email),
	constraint NIF_UNIQUE
		unique (NIF),
	constraint phone_number_UNIQUE
		unique (phone_number)
)
;

DROP TABLE IF EXISTS `4BankDB`.`ACCOUNT` ;


create table ACCOUNT
(
	idACCOUNT int(10) auto_increment,
	idUSERS int not null,
	balance float null,
	primary key (idACCOUNT, idUSERS),
	constraint fk_ACCOUNT_1
		foreign key (idUSERS) references USERS (idUSERS)
			on update cascade on delete cascade
)
;

create index fk_ACCOUNT_1_idx
	on ACCOUNT (idUSERS)
;

DROP TABLE IF EXISTS `4BankDB`.`EMPLOYEE` ;


create table EMPLOYEE
(
	idUSERS int not null
		primary key,
	isEmployee tinyint(1) not null,
	constraint fk_EMPLOYEE_1
		foreign key (idUSERS) references USERS (idUSERS)
			on update cascade on delete cascade
)
;

create index fk_EMPLOYEE_1_idx
	on EMPLOYEE (idUSERS)
;

DROP TABLE IF EXISTS `4BankDB`.`MOVEMENTS` ;

create table MOVEMENTS
(
	idMOVEMENTS int auto_increment
		primary key,
	idUSERS int not null,
	idACCOUNT int(10) not null,
	idUSERS_receptor int null,
	concept varchar(15) not null,
	amount float default '0' not null,
	new_balance float not null,
	date date not null,
	constraint fk_MOVEMENTS_1
		foreign key (idUSERS, idACCOUNT) references ACCOUNT (idUSERS, idACCOUNT)
			on update cascade on delete cascade
)
;

create index fk_MOVEMENTS_1_idx
	on MOVEMENTS (idUSERS, idACCOUNT)
;
