create table followship
(
    id         bigint not null,
    group_id   bigint not null,
    user_id    bigint,
    created_at timestamp,
    updated_at timestamp,

    constraint followship_pk          primary key (id),
    constraint followship_fk_group_id foreign key (group_id) references "group"(id)
);