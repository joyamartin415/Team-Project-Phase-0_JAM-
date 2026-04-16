-- Cities
insert into cities (cityName) values
('Atlanta'),
('Marietta'),
('Snellville'),
('Sandy Springs');

-- States
insert into states (stateID, abbrev) values
(1, 'AL'), (2, 'AK'), (3, 'AZ'), (4, 'AR'), (5, 'CA'),
(6, 'CO'), (7, 'CT'), (8, 'DE'), (9, 'FL'), (10, 'GA'),
(11, 'HI'), (12, 'ID'), (13, 'IL'), (14, 'IN'), (15, 'IA'),
(16, 'KS'), (17, 'KY'), (18, 'LA'), (19, 'ME'), (20, 'MD'),
(21, 'MA'), (22, 'MI'), (23, 'MN'), (24, 'MS'), (25, 'MO'),
(26, 'MT'), (27, 'NE'), (28, 'NV'), (29, 'NH'), (30, 'NJ'),
(31, 'NM'), (32, 'NY'), (33, 'NC'), (34, 'ND'), (35, 'OH'),
(36, 'OK'), (37, 'OR'), (38, 'PA'), (39, 'RI'), (40, 'SC'),
(41, 'SD'), (42, 'TN'), (43, 'TX'), (44, 'UT'), (45, 'VT'),
(46, 'VA'), (47, 'WA'), (48, 'WV'), (49, 'WI'), (50, 'WY');

-- Addresses 
insert into addresses (street, cityID, stateID, zip) values
('123 Main Rd', 1, 10, '30082'),
('199 Backroad Ln', 2, 10, '30027'),
('76 Mountain View', 1, 10, '30887'),
('722 Roam Rd', 3, 10, '33379'),
('88 Baytes Cir', 1, 10, '30992'),
('29 Mighty Ln', 2, 10, '30773'),
('6 Valley View', 1, 10, '33391'),
('857 Maestro Rd', 3, 10, '89896'),
('639 Yancey Cir', 2, 10, '32695'),
('796 Journey Ave', 3, 10, '30097');

-- Division 
insert into division (ID, Name, city, addressLine1, addressLine2, state, country, postalCode) values
(1, 'HR', 'Atlanta', '199 Smithen Rd', NULL, 'GA', 'USA', '30182'),
(2, 'Engineering', 'Marietta', '203 Mirror Ave', NULL, 'GA', 'USA', '30784'),
(3, 'Analysis', 'Snellville', '723 Chancy Circle', NULL, 'GA', 'USA', '30221');

-- Job Titles
insert into job_titles (job_title_id, job_title) values
(1, 'Manager'),
(2, 'Developer'),
(3, 'Analyst');

-- Employees
insert into employees (empid, Fname, Lname, email, HireDate, Salary, SSN, addressID) values
(101, 'Perry', 'Plat', 'pplat@gmail.com', '2022-01-10', 60000, '111-11-1111', 1),
(102, 'Marinette', 'Dupain-Cheng', 'marcheng@yahoo.com', '2021-03-15', 75000, '222-22-2222', 2),
(103, 'Altha', 'Weyward', 'aweyward@gmail.com', '2020-07-20', 50000, '333-33-3333', 3),
(104, 'Diana', 'Ross', 'dianarss@gmail.com', '2023-02-01', 68000, '444-44-4444', 4),
(105, 'Ford', 'Perfect', 'perfectford@gmail.com', '2019-11-11', 82000, '555-55-5555', 5),
(106, 'Baymax', 'Hero', 'hero6bay@gmail.com', '2022-06-25', 54000, '666-66-6666', 6),
(107, 'Lilo', 'Ohana', 'lilohana@gmail.com', '2021-09-30', 71000, '777-77-7777', 7),
(108, 'Adrien', 'Agreste', 'aadreamy@hotmail.com', '2020-12-12', 63000, '888-88-8888', 8),
(109, 'Buttercup', 'Utonium', 'bcuputo@yahoo.com', '2023-04-18', 59000, '999-99-9999', 9),
(110, 'Steven', 'DeMayo', 'stevendm@gmail.com', '2022-08-05', 77000, '101-10-1010', 10);

-- Employee Division
insert into employee_division (empid, div_ID) values
(101, 1), (102, 1), (103, 2), (104, 2), (105, 1),
(106, 2), (107, 1), (108, 2), (109, 1), (110, 2);

-- Employee Job Title
insert into employee_job_titles (empid, job_title_id) values
(101, 1), (102, 2), (103, 3), (104, 1), (105, 2),
(106, 3), (107, 1), (108, 2), (109, 3), (110, 1);

-- Payroll
insert into payroll (payID, pay_date, earnings, fed_tax, fed_med, fed_SS, state_tax, retire_401k, health_care, empid) values
(1, '2024-01-01', 5000, 500, 200, 300, 150, 200, 100, 101),
(2, '2024-01-01', 6000, 600, 250, 350, 200, 250, 120, 102),
(3, '2024-01-01', 4200, 420, 180, 250, 130, 180, 90, 103),
(4, '2024-01-01', 5500, 550, 220, 300, 160, 210, 110, 104),
(5, '2024-01-01', 7000, 700, 300, 400, 250, 300, 150, 105);


