-- employee_management.cities definition

CREATE TABLE `cities` (
  `cityID` int NOT NULL AUTO_INCREMENT,
  `cityName` varchar(50) NOT NULL,
  PRIMARY KEY (`cityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.division definition

CREATE TABLE `division` (
  `divID` int NOT NULL AUTO_INCREMENT,
  `divisionName` varchar(100) NOT NULL,
  PRIMARY KEY (`divID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.job_titles definition

CREATE TABLE `job_titles` (
  `job_titleID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`job_titleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.states definition

CREATE TABLE `states` (
  `stateID` int NOT NULL AUTO_INCREMENT,
  `stateAbbreviation` char(2) NOT NULL,
  PRIMARY KEY (`stateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.addresses definition

CREATE TABLE `addresses` (
  `addressID` int NOT NULL AUTO_INCREMENT,
  `street` varchar(100) NOT NULL,
  `cityID` int DEFAULT NULL,
  `stateID` int DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `mobilePhone` varchar(15) DEFAULT NULL,
  `emergencyContactName` varchar(100) DEFAULT NULL,
  `emergencyContactPhone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`addressID`),
  KEY `cityID` (`cityID`),
  KEY `stateID` (`stateID`),
  CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`cityID`) REFERENCES `cities` (`cityID`),
  CONSTRAINT `addresses_ibfk_2` FOREIGN KEY (`stateID`) REFERENCES `states` (`stateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.employees definition

CREATE TABLE `employees` (
  `empID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  `addressID` int DEFAULT NULL,
  PRIMARY KEY (`empID`),
  KEY `addressID` (`addressID`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`addressID`) REFERENCES `addresses` (`addressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.payroll definition

CREATE TABLE `payroll` (
  `payrollID` int NOT NULL AUTO_INCREMENT,
  `empID` int DEFAULT NULL,
  `payDate` date DEFAULT NULL,
  `salaryAmount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`payrollID`),
  KEY `empID` (`empID`),
  CONSTRAINT `payroll_ibfk_1` FOREIGN KEY (`empID`) REFERENCES `employees` (`empID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.employee_division definition

CREATE TABLE `employee_division` (
  `empID` int NOT NULL,
  `divID` int NOT NULL,
  PRIMARY KEY (`empID`,`divID`),
  KEY `divID` (`divID`),
  CONSTRAINT `employee_division_ibfk_1` FOREIGN KEY (`empID`) REFERENCES `employees` (`empID`),
  CONSTRAINT `employee_division_ibfk_2` FOREIGN KEY (`divID`) REFERENCES `division` (`divID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- employee_management.employee_job_titles definition

CREATE TABLE `employee_job_titles` (
  `empID` int NOT NULL,
  `job_titleID` int NOT NULL,
  PRIMARY KEY (`empID`,`job_titleID`),
  KEY `job_titleID` (`job_titleID`),
  CONSTRAINT `employee_job_titles_ibfk_1` FOREIGN KEY (`empID`) REFERENCES `employees` (`empID`),
  CONSTRAINT `employee_job_titles_ibfk_2` FOREIGN KEY (`job_titleID`) REFERENCES `job_titles` (`job_titleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;