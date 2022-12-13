create table post
(
    id          bigint not null,
    sender_id   bigint,
    receiver_id bigint,
    text        text,
    created_at  timestamp,
    updated_at  timestamp,

    constraint post_pk             primary key (id),
    constraint post_fk_sender_id   foreign key (sender_id) references "user"(id),
    constraint post_fk_receiver_id foreign key (receiver_id) references "user"(id)
);