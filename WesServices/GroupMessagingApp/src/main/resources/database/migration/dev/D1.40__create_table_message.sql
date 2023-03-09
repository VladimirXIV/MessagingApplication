create table message
(
    id         bigint not null,
    group_id   bigint not null,
    user_id    bigint,
    text       text,
    created_at timestamp,
    updated_at timestamp,

    constraint message_pk          primary key (id),
    constraint message_fk_group_id foreign key (group_id) references "group"(id)
);