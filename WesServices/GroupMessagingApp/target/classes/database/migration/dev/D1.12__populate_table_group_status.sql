insert into
    group_status (code, name)
values
    (nextval('group_status_code_sequence'), 'active'),
    (nextval('group_status_code_sequence'), 'freeze'),
    (nextval('group_status_code_sequence'), 'archive');