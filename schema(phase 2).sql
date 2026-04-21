CREATE DATABASE IF NOT EXISTS employee_management;
USE employee_management;

-- Cities
CREATE TABLE cities (
  cityID INT NOT NULL AUTO_INCREMENT,
  cityName VARCHAR(50) NOT NULL,
  PRIMARY KEY (cityID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- States
CREATE TABLE states (
  stateID INT NOT NULL AUTO_INCREMENT,
  stateAbbreviation CHAR(2) NOT NULL,
  PRIMARY KEY (stateID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Addresses
CREATE TABLE addresses (
  addressID INT NOT NULL AUTO_INCREMENT,
  street VARCHAR(100) NOT NULL,
  cityID INT DEFAULT NULL,
  stateID INT DEFAULT NULL,
  zip VARCHAR(10) DEFAULT NULL,
  DOB DATE DEFAULT NULL,
  mobilePhone VARCHAR(15) DEFAULT NULL,
  emergencyContactName VARCHAR(100) DEFAULT NULL,
  emergencyContactPhone VARCHAR(15) DEFAULT NULL,
  PRIMARY KEY (addressID),
  KEY cityID (cityID),
  KEY stateID (stateID),
  CONSTRAINT addresses_ibfk_1 FOREIGN KEY (cityID) REFERENCES cities (cityID),
  CONSTRAINT addresses_ibfk_2 FOREIGN KEY (stateID) REFERENCES states (stateID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Division
CREATE TABLE division (
  divID INT NOT NULL AUTO_INCREMENT,
  divisionName VARCHAR(100) NOT NULL,
  PRIMARY KEY (divID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Job Titles
CREATE TABLE job_titles (
  job_titleID INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  PRIMARY KEY (job_titleID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Employees
CREATE TABLE employees (
  empID INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(50) NOT NULL,
  lastName VARCHAR(50) NOT NULL,
  email VARCHAR(100) DEFAULT NULL,
  hireDate DATE DEFAULT NULL,
  salary DECIMAL(10,2) DEFAULT NULL,
  SSN VARCHAR(11) DEFAULT NULL,
  addressID INT DEFAULT NULL,
  PRIMARY KEY (empID),
  KEY addressID (addressID),
  CONSTRAINT employees_ibfk_1 FOREIGN KEY (addressID) REFERENCES addresses (addressID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Users (needed for login)
CREATE TABLE users (
  userID INT NOT NULL AUTO_INCREMENT,
  empID INT NOT NULL,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL,
  PRIMARY KEY (userID),
  KEY empID (empID),
  CONSTRAINT users_ibfk_1 FOREIGN KEY (empID) REFERENCES employees (empID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Payroll
CREATE TABLE payroll (
  payrollID INT NOT NULL AUTO_INCREMENT,
  empID INT DEFAULT NULL,
  payDate DATE DEFAULT NULL,
  salaryAmount DECIMAL(10,2) DEFAULT NULL,
  PRIMARY KEY (payrollID),
  KEY empID (empID),
  CONSTRAINT payroll_ibfk_1 FOREIGN KEY (empID) REFERENCES employees (empID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Employee Division
CREATE TABLE employee_division (
  empID INT NOT NULL,
  divID INT NOT NULL,
  PRIMARY KEY (empID, divID),
  KEY divID (divID),
  CONSTRAINT employee_division_ibfk_1 FOREIGN KEY (empID) REFERENCES employees (empID),
  CONSTRAINT employee_division_ibfk_2 FOREIGN KEY (divID) REFERENCES division (divID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Employee Job Titles
CREATE TABLE employee_job_titles (
  empID INT NOT NULL,
  job_titleID INT NOT NULL,
  PRIMARY KEY (empID, job_titleID),
  KEY job_titleID (job_titleID),
  CONSTRAINT employee_job_titles_ibfk_1 FOREIGN KEY (empID) REFERENCES employees (empID),
  CONSTRAINT employee_job_titles_ibfk_2 FOREIGN KEY (job_titleID) REFERENCES job_titles (job_titleID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;