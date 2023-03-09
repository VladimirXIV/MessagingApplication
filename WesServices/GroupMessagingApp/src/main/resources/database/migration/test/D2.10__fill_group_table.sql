insert into
    "group" (id, status_code, type_id, created_by, updated_by, created_at, updated_at, title, meta_title, description)
values
    (nextval('group_id_sequence'), 500, 800, 102, 102, '2010-01-10T02:10:00', '2010-01-10T02:10:00', 'American muscle cars',          'The most amazing "muscle cars community" ever.',                             'This group is all about muscle cars. Feel free to post pics of your own muscle cars. For many of us, classic muscle cars hold a special place in our hearts. If you love classic muscle cars as we do then you are in the right place!'),
    (nextval('group_id_sequence'), 500, 800, 104, 110, '2011-02-11T04:15:00', '2011-02-11T04:15:00', 'Aircraft for sale',             'Here you can post details of the aircraft you sell or you are looking for.', 'This group to showcase your aircraft or helicopter that you are trying to sell. Avionics and aircraft parts are not accepted.'),
    (nextval('group_id_sequence'), 500, 800, 106, 112, '2012-03-12T06:20:00', '2012-03-12T06:20:00', 'Aviation jobs & careers',       'All the best for your dream flying job!',                                    'The purpose of this group is to share information about job opportunities, chat about aviation related subjects and to meet other people from all over the world in the aviation business. Anyone can start a topic or share photos, but please be polite.'),
    (nextval('group_id_sequence'), 510, 800, 108, 106, '2013-04-13T08:25:00', '2013-04-13T08:25:00', 'Crossfit friends community',    'Get all your crossfit news!',                                                'This group is for passing along information to all members, including scheduling updates and gym news. Feel free to pass along info that our other members will love too, but good vibes only!'),
    (nextval('group_id_sequence'), 500, 800, 108, 110, '2014-05-14T10:30:00', '2014-05-14T10:30:00', 'Ship lovers',                   'We just love ships!',                                                        'A place for everyone who loves the sea or works in the sea. Join and share your photos and your stories!'),
    (nextval('group_id_sequence'), 500, 800, 110, 110, '2015-06-15T12:35:00', '2015-06-15T12:35:00', 'Cats of the world',             'This is a place to share our love of cats of all kinds!',                    'Welcome to cat for love group! Cute cats, cute kittens!'),
    (nextval('group_id_sequence'), 520, 800, 112, 110, '2016-07-16T14:40:00', '2016-07-16T14:40:00', 'I love my dogs',                'We love dogs!',                                                              'A fun and friendly page to share pictures and stories of your fur babies. We are all here to share our experiences with our furry friends.'),
    (nextval('group_id_sequence'), 500, 800, 112, 110, '2017-08-17T16:45:00', '2017-08-17T16:45:00', 'Geography knowledge',           'Place that we all can learn about geography of the world!',                  'This group is ideally for geographers and historians who are deeply interested in teaching others.'),
    (nextval('group_id_sequence'), 500, 800, 114, 110, '2018-09-18T18:50:00', '2018-09-18T18:50:00', 'Classical culture and history', 'For lovers of history and civilizations.',                                   'This group has been set up to encourage discussion of the classical world. It focuses upon the classical period, mainly Athenian, early and late Roman republican period and early Roman imperial period.'),
    (nextval('group_id_sequence'), 500, 800, 116, 110, '2019-10-19T20:00:00', '2019-10-19T20:00:00', 'German history and culture',    'Amazing group to explore Germany past and present!',                         'A group for people who are fascinated and intrigued by the wondrous and impressive story of the german people of one of the greatest nations on Earth... Germany! We also cover Austria, Switzerland, and Liechtenstein because so much is shared. From the germanic tribes to today.');