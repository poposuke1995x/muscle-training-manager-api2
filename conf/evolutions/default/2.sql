-- !Ups
create table if not exists lift_types
(
    id                serial primary key,
    name              varchar(50) not null,
    reference_url     varchar(200),
    description       varchar(200),
    user_id           integer references users (id) on delete cascade,
    default_rep       integer              default 0,
    default_weight    integer              default 0,
    default_set_count integer              default 0,
    share_flag        boolean     not null default false
);

-- !Downs
drop table lift_types;

