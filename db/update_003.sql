drop table persons cascade;

drop table addresses cascade;


create table if not exists persons
(
    id serial primary key,
    name varchar (50) not null
);

create table if not exists addresses
(
    id serial primary key,
    street varchar(60) not null,
    number varchar (10) not null,
    persons_id int not null references persons(id)
);
