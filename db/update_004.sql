create table if not exists authors
(
    id serial primary key,
    name varchar(50) not null

    );

create table if not exists books
(
    id serial primary key,
    name varchar(50) not null,
    authors_id int not null references authors(id)
    );