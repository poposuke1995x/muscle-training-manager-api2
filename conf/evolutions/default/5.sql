-- !Ups
create table if not exists body_parts
(
    id          serial primary key,
    name        varchar(50) not null,
    category_id integer references categories (id) on delete cascade
);

-- !Downs
drop table body_parts;


