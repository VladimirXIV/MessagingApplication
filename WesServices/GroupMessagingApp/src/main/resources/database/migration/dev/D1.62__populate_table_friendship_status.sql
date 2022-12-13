insert into
    friendship_status (code, name)
values
    (nextval('friendship_status_code_sequence'), 'pending'),
    (nextval('friendship_status_code_sequence'), 'rejected'),
    (nextval('friendship_status_code_sequence'), 'approved');