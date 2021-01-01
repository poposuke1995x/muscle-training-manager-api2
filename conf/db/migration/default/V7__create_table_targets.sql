create table if not exists targets
(
    id             serial primary key,
    lift_action_id integer references lift_actions (id) on delete cascade,
    body_part_id   integer references body_parts (id) on delete cascade,
    is_main        boolean default true
);


