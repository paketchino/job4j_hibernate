drop table c_marks cascade;

drop table cars cascade;

create table if not exists c_marks
(
    id serial primary key,
    name varchar(25)

);

create table if not exists cars
(
    id serial primary key,
    name varchar(100)

);