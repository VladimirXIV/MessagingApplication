create table post
(
    id         bigint not null,
    user_id    bigint,
    sender_id  bigint,
    message    text,
    created_at timestamp,
    updated_at timestamp,

    constraint post_pk primary key (id),
    constraint post_fk_user_id foreign key (user_id) references "user"(id)
);