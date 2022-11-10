create table message
(
    id         bigint not null,
    source_id  bigint,
    target_id  bigint,
    message    text,
    created_at timestamp,
    updated_at timestamp,

    constraint user_message_pk primary key (id)
);