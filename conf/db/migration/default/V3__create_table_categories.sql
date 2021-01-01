drop type if exists category;
create type category as enum ('胸', '背中', '肩', '脚', '腹筋・体幹', '有酸素', 'その他');

create table if not exists categories
(
    id   serial primary key,
    name category not null
);

