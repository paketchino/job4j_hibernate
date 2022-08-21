create table if not exists candidates (
    id serial PRIMARY KEY,
    name TEXT,
    experience timestamp default current_timestamp,
    salary int
)
