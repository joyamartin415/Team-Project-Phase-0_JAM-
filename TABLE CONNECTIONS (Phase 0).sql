-- Creating Tables: addresses, cities, states

create table if not exists states (
	stateID INT primary key, 
	abbreviation CHAR(2)
);

create table if not exists cities (
	cityID INT AUTO_INCREMENT primary key,
	city_name VARCHAR(25)
);

create table if not exists addresses (
	addressID INT AUTO_INCREMENT primary key,
	street VARCHAR(50),
	cityID INT,
	stateID INT,
	zip VARCHAR(50)
);

-- --------------------------------
-- Establish Primary keys 
alter table division
add primary key (ID);

alter table job_titles
add primary key (job_title_id);

alter table payroll
add primary key (payID);

-- FK Connections for addresses

-- address --> cities
alter table addresses
add constraint fk_city
foreign key (cityID)
references cities(cityID);

-- address --> states
alter table addresses
add constraint fk_state
foreign key (stateID)
references states(stateID);

-- FK Connections for employees

-- employee_division --> employees
alter  table  employee_division
add constraint fk_emp_div
foreign key (empid)
references employees(empid);

-- employee_division --> division
alter table employee_division
add constraint fk_division
foreign key (div_ID)
references division(ID);

-- employee_job_titles --> employees
alter  table employee_job_titles
add constraint fk_emp_title
foreign key (empid)
references  employees(empid);

-- employee_job_titles --> job_titles
alter  table employee_job_titles
add constraint fk_job_title
foreign key (job_title_id)
references  job_titles(job_title_id);

-- payroll --> employees
alter table payroll
add constraint fk_payroll_employee
foreign key (empid)
references employees(empid);

-- employees --> addresses
alter table  employees
add constraint fk_employee_address
foreign key (addressID)
references addresses(addressID);

-- ------------------------------

-- Adds missing coloumns to the employees table
ALTER TABLE employees
ADD DOB DATE,
ADD mobilePhone VARCHAR(15),
ADD emergencyContactName VARCHAR(50),
ADD emergencyContactPhone VARCHAR(15);