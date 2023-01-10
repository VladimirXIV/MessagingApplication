insert into
    group_role (id, name)
values
    (nextval('group_role_id_sequence'), 'administrator'),
    (nextval('group_role_id_sequence'), 'moderator'),
    (nextval('group_role_id_sequence'), 'guest');