create database HospitalManagementSystem;

use HospitalManagementSystem;

create table Patient (
    patientId int primary key auto_increment,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    dateOfBirth date not null,
    gender varchar(10) not null,
    contactNumber bigint not null,
    address varchar(255) not null
) auto_increment = 1001;

create table Doctor (
    doctorId int primary key auto_increment,
    firstName varchar(255) not null,
    lastName varchar(255) not null,
    specialization varchar(255) not null,
    contactNumber bigint not null
) auto_increment = 2001;

create table Appointment (
    appointmentId int primary key auto_increment,
    patientId int not null,
    doctorId int,
    appointmentDate date not null,
    description text not null,
    constraint patientId_fk 
    foreign key (patientId) references Patient(patientId) on delete cascade on update cascade,
    constraint doctorId_fk 
    foreign key (doctorId) references Doctor(doctorId) on delete set null on update cascade
) auto_increment = 3001;


insert into Patient values
(1001, 'Eleanor', 'West', '1990-03-15', 'female', 1234567890, '124 Rosewood Ave, Boston'),
(1002, 'James', 'Hart', '1985-07-22', 'male', 2345678901, '78 River Lane, Seattle'),
(1003, 'Clara', 'Mills', '1994-11-08', 'female', 3456789012, '23 Beacon St, Denver'),
(1004, 'Dorian', 'Black', '1978-01-05', 'male', 4567890123, '512 Hudson Rd, Chicago'),
(1005, 'Isla', 'Wren', '1992-05-30', 'female', 5678901234, '77 Maple Dr, San Diego'),
(1006, 'Leo', 'Finch', '2009-06-21', 'male', 6789012345, '35 Pine Hill, Portland'),  
(1007, 'Mila', 'Hale', '2015-02-11', 'female', 7890123456, '99 Aurora St, Miami');    


insert into Doctor values
(2001, 'Drake', 'Monroe', 'cardiologist', 9876543210),
(2002, 'Vivian', 'Stone', 'dermatologist', 8765432109),
(2003, 'Rowan', 'Thorne', 'pediatrician', 7654321098),
(2004, 'Harper', 'Vale', 'neurologist', 6543210987),
(2005, 'Asher', 'Gray', 'psychiatrist', 5432109876); 


insert into Appointment values
(3001, 1001, 2001, '2025-07-02', 'Routine heart check-up'),
(3002, 1002, 2002, '2025-07-04', 'Skin rash consultation'),
(3003, 1003, 2003, '2025-07-06', 'Child vaccination follow-up'),
(3004, 1004, 2004, '2025-07-08', 'Migraine and neural exam'),
(3005, 1005, 2001, '2025-07-10', 'Follow-up on ECG test'),
(3006, 1001, 2004, '2025-07-12', 'Neurological assessment for dizziness'),
(3007, 1006, 2003, '2025-07-13', 'Annual pediatric wellness check');


select * from Patient;

select * from Doctor;

select * from Appointment;


