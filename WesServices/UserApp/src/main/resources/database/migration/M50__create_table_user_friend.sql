create table user_friend
(
    id         bigint not null,
    source_id  bigint,
    target_id  bigint,
    type       integer,
    status     integer,
    created_at timestamp,
    updated_at timestamp,
    notes      text,

    constraint user_friend_pk primary key (id)
);

alter table user_friend owner to user_dev_1;