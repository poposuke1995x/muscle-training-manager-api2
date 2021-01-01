create table if not exists training_menu
(
    id          serial primary key,
    name        varchar(50) not null,
    description varchar(200),
    user_id     integer references users (id) on delete cascade,
    share_flag  boolean default false
);



