
db = db.getSiblingDB('security_db');
db.createUser({
    user: 'security_dev',
    pwd: 'security_dev',
    roles: [
      { role: "dbOwner", db: "security_db" },
      { role: "dbOwner", db: "db_two" },
      "readWrite"]
});
