create table "group"
(
    id          bigint not null,
    title       varchar(500),
    meta_title  varchar(500),
    summary     varchar(500),
    status_code bigint not null,
    info        varchar(500),
    created_by  bigint,
    updated_by  bigint,
    created_at  timestamp,
    updated_at  timestamp,

    constraint group_pk             primary key (id),
    constraint group_fk_status_code foreign key (status_code) references group_status(code)
);