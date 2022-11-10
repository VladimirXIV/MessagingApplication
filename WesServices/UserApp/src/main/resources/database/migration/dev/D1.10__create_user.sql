create user user_dev_1 with password 'user_dev_1';

grant connect on database "users" to user_dev_1;
grant all privileges on all tables in schema user_schema to user_dev_1;
grant all privileges on all sequences in schema public to user_dev_1;