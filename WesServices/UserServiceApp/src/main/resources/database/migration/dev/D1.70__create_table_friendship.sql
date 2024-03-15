create table friendship
(
    id          bigint not null,
    sender_id   bigint,
    receiver_id bigint,
    type_id     bigint,
    status_code bigint not null,
    notes       text,
    created_at  timestamp,
    updated_at  timestamp,

    constraint friendship_pk             primary key (id),
    constraint friendship_fk_sender_id   foreign key (sender_id) references "user"(id),
    constraint friendship_fk_receiver_id foreign key (receiver_id) references "user"(id),
    constraint friendship_fk_type_id     foreign key (type_id) references friendship_type(id),
    constraint friendship_fk_status      foreign key (status_code) references friendship_status(code)
);