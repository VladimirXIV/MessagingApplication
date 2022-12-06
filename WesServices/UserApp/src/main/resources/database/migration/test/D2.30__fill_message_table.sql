insert into
    message (id, sender_id, receiver_id, text, created_at, updated_at)
values
    (nextval('message_id_sequence'), 102, 100, 'Hello fellas!', '2015-09-01T16:34:02', '2015-09-01T16:34:02'),
    (nextval('message_id_sequence'), 104, 100, 'Great acting. Amazing soundtrack that was performed by Danny Elfman, especially the opening score really fits the film''s opening scene.', '2015-09-01T16:34:02', '2015-09-01T16:34:02'),
    (nextval('message_id_sequence'), 102, 104, 'Lady in red is dancing with me. I have never seen you looking so lovely as you did tonight...', '2015-09-01T16:34:02', '2015-09-01T16:34:02');