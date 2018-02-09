-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema easyshop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema easyshop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `easyshop` DEFAULT CHARACTER SET utf8 ;
USE `easyshop` ;

-- -----------------------------------------------------
-- Table `easyshop`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easyshop`.`questions` (
  `SECURITY_QUES_ID` INT(11) NOT NULL,
  `SECURITY_QUES_DESCRIPTION` VARCHAR(45) NOT NULL,
  `SECURITY_QUES_STATUS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SECURITY_QUES_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `easyshop`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easyshop`.`customer` (
  `CUST_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CUST_FIRST_NAME` VARCHAR(45) NOT NULL,
  `CUST_LAST_NAME` VARCHAR(45) NOT NULL,
  `CUST_EMAILID` VARCHAR(45) NOT NULL,
  `CUST_PHONE_NUMBER` VARCHAR(20) NOT NULL,
  `CUST_PASSWORD` VARCHAR(255) NOT NULL,
  `ADDRESS1` VARCHAR(45) NULL DEFAULT NULL,
  `ADDRESS2` VARCHAR(45) NULL DEFAULT NULL,
  `CITY` VARCHAR(45) NOT NULL,
  `STATE` VARCHAR(45) NOT NULL,
  `ZIPCODE` INT(11) NOT NULL,
  `SECURITY_QUES_ID` INT(11) NOT NULL,
  `SECURITY_QUES_ANS` VARCHAR(45) NOT NULL,
  `ACTIVE_STATUS` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`CUST_ID`),
  INDEX `SECURITY_QUES_ID_idx` (`SECURITY_QUES_ID` ASC),
  CONSTRAINT `SECURITY_QUES_ID`
    FOREIGN KEY (`SECURITY_QUES_ID`)
    REFERENCES `easyshop`.`questions` (`SECURITY_QUES_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8
COMMENT = '		';


-- -----------------------------------------------------
-- Table `easyshop`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easyshop`.`address` (
  `ADDRESS_ID` INT(11) NOT NULL,
  `CUST_ID2` INT(11) NOT NULL,
  `ADDRESS_ADDRESS1` VARCHAR(45) NULL DEFAULT NULL,
  `ADDRESS_ADDRESS2` VARCHAR(45) NULL DEFAULT NULL,
  `ADDRESS_CITY` VARCHAR(45) NOT NULL,
  `ADDRESS_STATE` VARCHAR(45) NOT NULL,
  `ADDRESS_ZIPCODE` INT(11) NOT NULL,
  `ADDRESS_PHONE_NUMBER` INT(11) NOT NULL,
  PRIMARY KEY (`ADDRESS_ID`),
  UNIQUE INDEX `BILLING_ID_UNIQUE` (`ADDRESS_ID` ASC),
  INDEX `CUST_ID2_idx` (`CUST_ID2` ASC),
  CONSTRAINT `CUST_ID2`
    FOREIGN KEY (`CUST_ID2`)
    REFERENCES `easyshop`.`customer` (`CUST_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `easyshop`.`card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easyshop`.`card` (
  `CARD_ID` INT(11) NOT NULL,
  `CUST_ID3` INT(11) NOT NULL,
  `CARD_NUMBER` INT(11) NOT NULL,
  `CARD_CVV` INT(11) NOT NULL,
  PRIMARY KEY (`CARD_ID`),
  UNIQUE INDEX `CARD_ID_UNIQUE` (`CARD_ID` ASC),
  INDEX `CUST_ID3_idx` (`CUST_ID3` ASC),
  CONSTRAINT `CUST_ID3`
    FOREIGN KEY (`CUST_ID3`)
    REFERENCES `easyshop`.`customer` (`CUST_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `easyshop`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `easyshop`.`item` (
  `ITEM_ID` BIGINT(10) NOT NULL,
  `ITEM_NAME` VARCHAR(45) NOT NULL,
  `ITEM_DESCRIPTION` MEDIUMTEXT NOT NULL,
  `ITEM_PRICE` FLOAT NOT NULL,
  `ITEM_QUANTITY` INT(11) NOT NULL,
  PRIMARY KEY (`ITEM_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


ALTER TABLE `easyshop`.`customer` ADD ACTIVE_STATUS BIT;

ALTER TABLE `easyshop`.`billingaddress` CHANGE BILLING_PHONE_NUMBER BILLING_PHONE_NUMBER VARCHAR(15) ;

ALTER TABLE `easyshop`.`shipmentaddress` CHANGE CUST_ID1 CUST_ID int;

ALTER TABLE `easyshop`.`billingaddress` CHANGE CUST_ID2 CUST_ID int;

ALTER TABLE `easyshop`.`shipmentaddress` ADD SHIPMENT_ZIPCODE int;

alter table `easyshop`.`shipmentaddress` drop primary key;

alter table `easyshop`.`billingaddress` drop primary key;

alter table `easyshop`.`shipmentaddress` change SHIPMENT_ID SHIPMENT_ID INT PRIMARY KEY AUTO_INCREMENT;

alter table `easyshop`.`billingaddress` change BILLING_ID BILLING_ID INT PRIMARY KEY AUTO_INCREMENT;

alter table `easyshop`.`customer` ADD AUTH_TOKEN varchar(45);

ALTER TABLE `easyshop`.`card` CHANGE CUST_ID3 CUST_ID int;

ALTER TABLE `easyshop`.`card` ADD CARD_EXP_MON int;

ALTER TABLE `easyshop`.`card` ADD CARD_EXP_YR int;

ALTER TABLE `easyshop`.`card` CHANGE CARD_NUMBER CARD_NUMBER varchar(25);

alter table `easyshop`.`card` change CARD_ID CARD_ID INT  AUTO_INCREMENT;

create table address(
 ADDRESS_ID INT PRIMARY KEY AUTO_INCREMENT,
 CUST_ID INT,
 ADDRESS1 VARCHAR(45),
 ADDRESS2 VARCHAR(45),
 CITY VARCHAR(45),
 STATE VARCHAR(45),
 ZIPCODE INT,
 PHONE_NUMBER VARCHAR(45),
 COUNTRY VARCHAR(30),
 CONSTRAINT ADDRESS_1
 FOREIGN KEY (CUST_ID) REFERENCES customer(CUST_ID));


CREATE TABLE IF NOT EXISTS `easyshop`.`item` (
  `ITEM_ID` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ITEM_NAME` VARCHAR(45) NOT NULL,
  `ITEM_DESCRIPTION` VARCHAR(100) NOT NULL,
  `ITEM_PRICE` FLOAT NOT NULL,
  `ITEM_IMAGE` VARCHAR(100),
  `ITEM_QUANTITY` INT(11) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `easyshop`.`cart` (
  `CART_ITEM_ID` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ITEM_ID` INT(11) NOT NULL,
  `CUST_ID` INT(11) NOT NULL,
   CONSTRAINT CART_1
   FOREIGN KEY (CUST_ID) REFERENCES customer(CUST_ID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `easyshop`.`order_hdr` (
  `ORDER_HDR_ID` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ORDER_ID` INT(11) NOT NULL,
  `CUST_ID` INT(11) NOT NULL,
  `ORDER_ITEM_COUNT` INT NOT NULL,
  `ORDER_TOTAL` FLOAT NOT NULL,
  `ORDER_STATUS` VARCHAR(20) NOT NULL,
  `ORDER_ADDRESS_ID` int NOT NULL,
  `ORDER_BILLING_ADDR_ID` int NOT NULL,
  `ORDER_CREATED_DATE` VARCHAR(20) NOT NULL,
  `ORDER_UPDATED_DATE` VARCHAR(20) NOT NULL,
   CONSTRAINT ORDER_HDR_1
   FOREIGN KEY (CUST_ID) REFERENCES customer(CUST_ID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `easyshop`.`order_dtl` (
  `ORDER_DTL_ID` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ORDER_ID` INT(11) NOT NULL,
  `ORDER_ITEM_ID` INT NOT NULL,
  `ORDER_ITEM_QUANTITY` INT NOT NULL,
  `ORDER_ITEM_PRICE` FLOAT NOT NULL,
  `ORDER_ITEM_STATUS` VARCHAR(20) NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `easyshop`.`subs_order_hdr` (
  `SUBS_ORDER_HDR_ID` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `SUBS_ORDER_ID` INT(11) NOT NULL,
  `CUST_ID` INT(11) NOT NULL,
  `SUBS_ORDER_ITEM_COUNT` INT NOT NULL,
  `SUBS_ORDER_TOTAL` FLOAT NOT NULL,
  `SUBS_ORDER_STATUS` VARCHAR(20) NOT NULL,
  `SUBS_ORDER_ADDRESS_ID` int NOT NULL,
  `SUBS_ORDER_CREATED_DATE` VARCHAR(20) NOT NULL,
  `SUBS_ORDER_UPDATED_DATE` VARCHAR(20) NOT NULL,
   CONSTRAINT SUBS_ORDER_HDR_1
   FOREIGN KEY (CUST_ID) REFERENCES customer(CUST_ID))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `easyshop`.`subs_order_dtl` (
  `SUBS_ORDER_DTL_ID` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `SUBS_ORDER_ID` INT(11) NOT NULL,
  `SUBS_ORDER_ITEM_ID` INT NOT NULL,
  `SUBS_ORDER_ITEM_QUANTITY` INT NOT NULL,
  `SUBS_ORDER_ITEM_PRICE` FLOAT NOT NULL,
  `SUBS_ORDER_ITEM_STATUS` VARCHAR(20) NULL,
  `SUBS_ORDER_ITEM_NAME` VARCHAR(20) NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `easyshop`.`subs_next_due_date` (
  `SUBS_ORDER_ID` BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `SUBSCRIPTION_TYPE` INT NOT NULL,
  `NEXT_DUE_DATE` DATE NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `easyshop`.`cust_messages` (
  `MESSAGE_ID` INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `CUST_ID` INT NOT NULL,
  `MESSAGE_CONTENT` VARCHAR(500) NOT NULL,
  `IS_READ` bit NOT NULL,
  `MESSAGE_TIME` DATE NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `easyshop`.`shipment` (
  `SHIPMENT_ID` INT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `TRACKING_NO` VARCHAR(200) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `easyshop`.`order_dtl` add ORDER_SHIPMENT_ID INT;