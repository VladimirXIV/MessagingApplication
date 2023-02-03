insert into
    member (id, group_id, user_id, role_id, status_code, notes, created_at, updated_at)
values
    (nextval('member_id_sequence', 100, 102, 300, 700, 'some notes', '2015-09-01T16:34:02', '2015-09-01T16:34:02')