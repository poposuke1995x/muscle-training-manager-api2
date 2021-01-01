-- !Ups
insert into users (name, firebase_uid)
values ('system', '164T8tZhiAZ27o5F6z6lVFyi4NJ3');

insert into lift_types (name, user_id, default_rep, default_weight, default_set_count, share_flag)
values ('ベンチプレス', 1, 45, 10, 3, true),
       ('デッドリフト', 1, 80, 10, 2, true),
       ('スクワット', 1, 70, 8, 3, true);

insert into categories (name)
values ('胸'),
       ('背中'),
       ('肩'),
       ('脚'),
       ('腹筋・体幹'),
       ('有酸素'),
       ('その他');

insert into training_menu (name, user_id, share_flag)
values ('サンプルメニュー', 1, true);

insert into body_parts (name, category_id)
values
    ('大胸筋 全体', 1),
    ('大胸筋 上部', 1),
    ('大胸筋 中部', 1),
    ('大胸筋 下部', 1),
    ('広背筋 全体', 2),
    ('広背筋 上部', 2),
    ('広背筋 中部', 2),
    ('広背筋 下部', 2),
    ('三角筋 前部', 3),
    ('三角筋 中部', 3),
    ('三角筋 後部', 3),
    ('大腿四頭筋 全体', 4),
    ('大腿四頭筋 表', 4),
    ('大腿四頭筋 裏', 4),
    ('ハムストリング', 4),
    ('ヒラメ筋', 4),
    ('僧帽筋', 2),
    ('大円筋', 2),
    ('腹筋', 5),
    ('腹斜筋', 5);

insert into lift_actions (lift_type_id, training_menu_id)
values (1, 1);

insert into targets (lift_action_id, body_part_id, is_main)
values (1, 1, true);