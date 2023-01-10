create table post
(
    id         bigint not null,
    group_id   bigint not null,
    user_id    varchar(500),
    text       varchar(500),
    created_at timestamp,
    updated_at timestamp,

    constraint post_pk           primary key (id),
    constraint group_fk_group_id foreign key (group_id) references "group"(id)
);