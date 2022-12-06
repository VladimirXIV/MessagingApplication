insert into
    "user" (id, first_name, middle_name, last_name, username, mobile, email, registered_at, last_login, intro, profile_info)
values
    (nextval('user_id_sequence'), 'James', 'Edward', 'Franco', 'Harry Osborn', '+7 800 2000 600', 'jamesef@gmail.com', '2015-09-01T16:34:02', '2015-09-01T16:34:02', 'Life is concise, so put worries apart', 'An American actor and filmmaker'),
    (nextval('user_id_sequence'), 'Tobias', 'Vincent', 'Maguire', 'Peter Parker', '+7 800 2000 700', 'tobiasvm@yahoo.com', '2015-09-01T16:34:02', '2015-09-01T16:34:02', 'Where happy life settles, age doesn''t matter', 'An American actor and film producer'),
    (nextval('user_id_sequence'), 'Kirsten', 'Caroline', 'Dunst', 'Mary Jane', '+7 800 2000 800', 'kirstencd@inbox.com', '2015-09-01T16:34:02', '2015-09-01T16:34:02', 'Help others in need; life is about you being there for people', 'An American actress'),
    (nextval('user_id_sequence'), 'Alfred', 'Esteban', 'Molina', 'Otto Gunther Octavius', '+7 800 4000 100', 'alfredem@gmail.com', '2015-09-01T16:34:02', '2015-09-01T16:34:02', 'Love should never be a secret', 'An English-American actor known for his work on the stage and screen'),
    (nextval('user_id_sequence'), 'William', ' James', 'Dafoe', 'Norman Osborn', '+7 800 3000 900', 'williamjd@yahoo.com', '2015-09-01T16:34:02', '2015-09-01T16:34:02', 'Back To Formula!', 'An American actor');