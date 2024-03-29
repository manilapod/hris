DROP SCHEMA IF EXISTS HRIS;

CREATE SCHEMA HRIS;
USE HRIS;

SET FOREIGN_KEY_CHECKS=0;
SET GLOBAL FOREIGN_KEY_CHECKS=0;

CREATE TABLE EMPLOYEE(
    EMPLOYEE_ID INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (EMPLOYEE_ID),
    BENEFIT_ID INT NOT NULL,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    BIRTH_DATE DATETIME NOT NULL,
    JOB_ROLE VARCHAR(45) NOT NULL,
    ADDRESS VARCHAR(100) NOT NULL,
    TELEPHONE_NUMBER CHAR(100) NOT NULL,
    STATE VARCHAR(4) NOT NULL ,
    IN_TRAINING BOOLEAN NOT NULL,
    PERFORMANCE INT,
    STARTED_DATE TIMESTAMP,
    LAST_UPDATE VARCHAR(50)
);

CREATE TABLE BENEFIT(
    BENEFIT_ID INT NOT NULL AUTO_INCREMENT,
    EMPLOYEE_ID INT NOT NULL,
    FOREIGN KEY (BENEFIT_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID),
    RETIREMENT_ID INT NOT NULL ,
    INSURANCE_ID INT NOT NULL,
    LIFE_INSURANCE VARCHAR(50) NOT NULL,
    HEALTH_INSURANCE VARCHAR(50) NOT NULL,
    RETIREMENT_PLANS VARCHAR(50) NOT NULL,
    TUITION_ASSISTANCE VARCHAR(50) NOT NULL
);

CREATE TABLE RETIREMENT(
    RETIREMENT_ID INT NOT NULL AUTO_INCREMENT,
    BENEFIT_ID INT NOT NULL,
    FOREIGN KEY (RETIREMENT_ID) REFERENCES BENEFIT(BENEFIT_ID),
    IRA_AMOUNT DECIMAL(12,3),
    PTB_AMOUNT DECIMAL(12,3),
    TSA_AMOUNT DECIMAL(12,3)
);

CREATE TABLE INSURANCE(
    INSURANCE_ID INT NOT NULL AUTO_INCREMENT,
    BENEFIT_ID INT NOT NULL ,
    FOREIGN KEY (INSURANCE_ID) REFERENCES BENEFIT(BENEFIT_ID),
    LIFE_PREM DECIMAL(12,3),
    HEALTH_PREM DECIMAL(12,3)
);

CREATE TABLE TRAINING(
    TRAINING_ID INT NOT NULL AUTO_INCREMENT,
    EMPLOYEE_ID INT NOT NULL,
    FOREIGN KEY (TRAINING_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID),
    TRAINEE_NAME VARCHAR(100),
    COMPLETED VARCHAR(50)
);

CREATE TABLE PAYROLL(
    PAYROLL_ID INT NOT NULL AUTO_INCREMENT,
    EMPLOYEE_ID INT NOT NULL,
    FOREIGN KEY (PAYROLL_ID) REFERENCES EMPLOYEE(EMPLOYEE_ID),
    HOURLY_RATE DECIMAL(12,3),
    GROSS_SALARY DECIMAL(12,3),
    SALARY DECIMAL(12,3),
    YEARLY_BONUS DECIMAL(12,3),
    STATE_TAX DECIMAL(12,3),
    FEDERAL_TAX DECIMAL(12,3),
    EMPLOYMENT_TYPE VARCHAR(50),
    WEEKLY_HOURS INT,
    PAY_CYCLE VARCHAR(50),
    EXTRA_HOURS INT,
    EFFECTIVE VARCHAR(50)
);

CREATE TABLE TAX(
    TAX_ID INT NOT NULL AUTO_INCREMENT,
    PAYROLL_ID INT NOT NULL,
    FOREIGN KEY (TAX_ID) REFERENCES PAYROLL(PAYROLL_ID),
    STATE_PREM DECIMAL(12,3),
    FEDERAL_PREM DECIMAL(12,3)
);

CREATE TABLE PTE(
    PTE_ID INT NOT NULL AUTO_INCREMENT,
    TRAINING_ID INT NOT NULL,
    FOREIGN KEY (PTE_ID) REFERENCES TRAINING(TRAINING_ID),
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL,
    ZIP_CODE INT NOT NULL,
    POSITION VARCHAR(50) NOT NULL,
    DATA_APPLIED VARCHAR(50) NOT NULL,
    APP_STATUS VARCHAR(50),
    LAST_UPDATE TIMESTAMP
);
