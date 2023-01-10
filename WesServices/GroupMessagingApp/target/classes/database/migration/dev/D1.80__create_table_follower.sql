create table follower
(
    id         bigint not null,
    group_id   bigint not null,
    user_id    bigint,
    created_at timestamp,
    updated_at timestamp,

    constraint follower_pk          primary key (id),
    constraint follower_fk_group_id foreign key (group_id) references "group"(id)
);