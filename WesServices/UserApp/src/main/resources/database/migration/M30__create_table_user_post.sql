create table user_post
(
    id         bigint not null,
    user_id    bigint,
    sender_id  bigint,
    message    text,
    ceated_at  timestamp,
    updated_at timestamp,

    constraint user_post_pk primary key (id)
);

alter table user_post owner to user_dev_1;