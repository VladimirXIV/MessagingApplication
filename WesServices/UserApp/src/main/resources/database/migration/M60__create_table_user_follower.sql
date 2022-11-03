create table user_follower
(
    id         bigint not null,
    source_id  bigint,
    target_id  bigint,
    type       integer,
    created_at timestamp,
    updated_at timestamp,

    constraint user_follower_pk primary key (id)
);

alter table user_follower owner to user_dev_1;