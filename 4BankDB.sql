-- MySQL Script generated by MySQL Workbench
-- Fri May  4 22:36:52 2018
-- Model: 4BankDB    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema 4bankdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `4bankdb` ;

-- -----------------------------------------------------
-- Schema 4bankdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `4bankdb` DEFAULT CHARACTER SET utf8 ;
USE `4bankdb` ;

-- -----------------------------------------------------
-- Table `4bankdb`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `4bankdb`.`USER` ;

CREATE TABLE IF NOT EXISTS `4bankdb`.`USER` (
  `idUSER` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `NIF` VARCHAR(15) NOT NULL,
  `phone_number` INT(11) NOT NULL,
  `isEmployee` TINYINT(1) NOT NULL,
  PRIMARY KEY (`idUSER`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `NIF_UNIQUE` (`NIF` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `4bankdb`.`ACCOUNT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `4bankdb`.`ACCOUNT` ;

CREATE TABLE IF NOT EXISTS `4bankdb`.`ACCOUNT` (
  `idACCOUNT` VARCHAR(24) NOT NULL,
  `idUSER` INT(11) NOT NULL,
  `balance` FLOAT NOT NULL DEFAULT '0',
  PRIMARY KEY (`idACCOUNT`),
  INDEX `fk_ACCOUNT_1_idx` (`idUSER` ASC),
  CONSTRAINT `fk_ACCOUNT_1`
    FOREIGN KEY (`idUSER`)
    REFERENCES `4bankdb`.`USER` (`idUSER`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `4bankdb`.`MOVEMENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `4bankdb`.`MOVEMENT` ;

CREATE TABLE IF NOT EXISTS `4bankdb`.`MOVEMENT` (
  `idMOVEMENT` INT(11) NOT NULL AUTO_INCREMENT,
  `idACCOUNT` VARCHAR(24) NOT NULL,
  `idACCOUNT_receptor` VARCHAR(24) NOT NULL,
  `concept` VARCHAR(45) NOT NULL,
  `amount` FLOAT NOT NULL,
  `new_balance_sender` FLOAT NOT NULL,
  `new_balance_receiver` FLOAT NOT NULL,
  `date` DATETIME NOT NULL,
  `idEmployee` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idMOVEMENT`),
  INDEX `fk_MOVEMENT_1_idx` (`idACCOUNT` ASC),
  INDEX `fk_MOVEMENT_2_idx` (`idACCOUNT_receptor` ASC),
  INDEX `fk_MOVEMENT_3_idx` (`idEmployee` ASC),
  CONSTRAINT `fk_MOVEMENT_1`
    FOREIGN KEY (`idACCOUNT`)
    REFERENCES `4bankdb`.`ACCOUNT` (`idACCOUNT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVEMENT_2`
    FOREIGN KEY (`idACCOUNT_receptor`)
    REFERENCES `4bankdb`.`ACCOUNT` (`idACCOUNT`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVEMENT_3`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `4bankdb`.`USER` (`idUSER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
