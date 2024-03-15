create table "group"
(
    id          bigint not null,
    title       varchar(1000),
    meta_title  varchar(1000),
    description varchar(1000),
    type_id     bigint not null,
    status_code bigint not null,
    created_by  bigint,
    updated_by  bigint,
    created_at  timestamp,
    updated_at  timestamp,

    constraint group_pk             primary key (id),
    constraint group_fk_type_id     foreign key (type_id)     references group_type (id),
    constraint group_fk_status_code foreign key (status_code) references group_status (code)
);