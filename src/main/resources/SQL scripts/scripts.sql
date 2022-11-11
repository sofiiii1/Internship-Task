

create table if not exists employee
(
employee_id serial primary key not null,
first_name varchar(55) not null,
last_name varchar(55) not null,
department_id int not null references departments(department_id),
job_title varchar(55) not null,
gender varchar(6),
date_of_birth date not null
);

create table if not exists departments
(
department_id serial primary key not null,
department_name varchar(100) not null
);

insert into departments(department_name)
values('Human Resources');
values('IT');
values('Finance');
values('Management');
values('Marketing');

INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)
	VALUES (1,'Maria', 'Adamovich', 1, 'Recruter','female', '1990-05-06');
	VALUES (2,'Anton', 'Petrov', 2, 'Programmer', 'male','1996-12-19');
	VALUES (3,'Ivan', 'Ivanov', 4, 'Manager', 'male','1994-10-07');
	VALUES (4,'Igor', 'Novikov', 3, 'Finance manager','male','1997-03-11');
	VALUES (5,'Anna', 'Smirnova', 5, 'Marketing specialist', 'female','1999-07-05');

insert into employee (employee_id,first_name, last_name, department_id, job_title, gender, date_of_birth)
values(100,'Irina','Petrova', 4,'Head Manager','male','1991-05-27');
