create table member
(
    id          bigint not null,
    group_id    bigint,
    user_id     bigint,
    role_id     bigint,
    status_code bigint,
    text        notes,
    created_at  timestamp,
    updated_at  timestamp,

    constraint member_pk             primary key (id),
    constraint member_fk_group_id    foreign key (group_id)    references "group"(id),
    constraint member_fk_role_id     foreign key (role_id)     references group_role(id),
    constraint member_fk_status_code foreign key (status_code) references member_status(code)
);