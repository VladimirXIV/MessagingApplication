db = db.getSiblingDB('security_db_main');
db.createUser({
    user: 'security_dev_main_user',
    pwd: 'security_dev_main_password',
    roles: [{ role: "dbOwner", db: "security_db_main"}]
});
db.createCollection("dummy_table", { capped : true, size : 5242880, max : 5000 });
db.dummy_table.insertOne({text: "dummy database record"});

db = db.getSiblingDB('security_db_one');
db.createUser({
    user: 'security_dev_01_user',
    pwd: 'security_dev_01_password',
    roles: [{ role: "dbOwner", db: "security_db_one"}]
});
db.createCollection("dummy_table", { capped : true, size : 5242880, max : 5000 });
db.dummy_table.insertOne({text: "dummy database record"});


db = db.getSiblingDB('security_db_two')
db.createUser({
    user: 'security_dev_02_user',
    pwd: 'security_dev_02_password',
    roles: [{ role: "dbOwner", db: "security_db_two"}]
});
db.createCollection("dummy_table", { capped : true, size : 5242880, max : 5000 });
db.dummy_table.insertOne({text: "dummy database record"});