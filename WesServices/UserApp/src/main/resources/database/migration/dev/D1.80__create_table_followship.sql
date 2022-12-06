create table followship
(
    id           bigint not null,
    follower_id  bigint,
    followed_id  bigint,
    type         integer,
    created_at   timestamp,
    updated_at   timestamp,

    constraint followship_pk             primary key (id),
    constraint followship_fk_follower_id foreign key (follower_id) references "user"(id),
    constraint followship_fk_followed_id foreign key (followed_id) references "user"(id)
);