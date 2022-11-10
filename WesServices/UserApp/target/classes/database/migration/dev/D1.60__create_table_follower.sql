create table follower
(
    id         bigint not null,
    source_id  bigint,
    target_id  bigint,
    type       integer,
    created_at timestamp,
    updated_at timestamp,

    constraint user_follower_pk primary key (id)
);