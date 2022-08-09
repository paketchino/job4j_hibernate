create table if not exists c_marks
(
    id serial primary key,
    name varchar(25)

);

create table if not exists cars
(
    id serial primary key,
    name varchar(100),
    c_marks_id int references c_marks(id)
);