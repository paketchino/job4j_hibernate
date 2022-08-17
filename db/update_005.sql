create table if not exists category
(
    id serial primary key,
    name varchar (50) not null
);

create table if not exists tasks
(
    id serial primary key,
    name varchar(50) not null
);