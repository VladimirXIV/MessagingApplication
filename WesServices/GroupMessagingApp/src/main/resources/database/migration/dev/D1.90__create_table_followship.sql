create table followship
(
    id           bigint not null,
    follower_id  bigint,
    followed_id  bigint,
    type_id      bigint,
    notes        text,
    created_at   timestamp,
    updated_at   timestamp,

    constraint followship_pk             primary key (id),
    constraint followship_fk_sender_id   foreign key (follower_id) references "user"(id),
    constraint followship_fk_receiver_id foreign key (followed_id) references "user"(id),
    constraint followship_fk_type_id     foreign key (type_id) references followship_type(id)
);