insert into
    member_status (code, name)
values
    (nextval('member_status_code_sequence'), 'available'),
    (nextval('member_status_code_sequence'), 'busy'),
    (nextval('member_status_code_sequence'), 'away');