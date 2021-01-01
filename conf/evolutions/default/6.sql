-- !Ups
create table if not exists lift_actions
(
    id               serial primary key,
    lift_type_id     integer references lift_types (id) on delete cascade,
    training_menu_id integer references training_menu (id) on delete cascade,
    light_rep        integer default 0,
    light_weight     integer default 0,
    light_set_count  integer default 0,
    heavy_rep        integer default 0,
    heavy_weight     integer default 0,
    heavy_set_count  integer default 0
);

alter table lift_actions
    add constraint unique_lift_action unique (lift_type_id, training_menu_id);

-- !Downs
drop table lift_actions;
