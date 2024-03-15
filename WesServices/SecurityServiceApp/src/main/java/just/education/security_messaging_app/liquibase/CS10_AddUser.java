package just.education.security_messaging_app.liquibase;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.ext.mongodb.database.MongoConnection;
import liquibase.resource.ResourceAccessor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Collections;
import java.util.UUID;

public class CS10_AddUser implements CustomTaskChange {

    @Override
    public void execute(Database database) throws CustomChangeException {

        MongoConnection mongoConnection = (MongoConnection) database.getConnection();
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase();

        // find ADMIN role
        MongoCollection<Document> roleCollection = mongoDatabase.getCollection("role");
        BasicDBObject roleSearchQuery = new BasicDBObject("name", "ADMIN");

        Document role = roleCollection.find(roleSearchQuery).first();
        ObjectId roleId = (ObjectId) role.get("_id");

        // new user with ADMIN role
        MongoCollection<Document> users = mongoDatabase.getCollection("user");
        Document newUser = new Document()
                .append("_id", UUID.randomUUID())
                .append("email", "franz_schmidt_bayern_1965@gmail.com")
                .append("username", "franz_schmidt")
                .append("password", "fsb1965")
                .append("roles", Collections.singletonList(roleId));

        InsertOneResult result = users.insertOne(newUser);

        role.toString();
    }

    @Override
    public String getConfirmationMessage() {
        // Not used
        return null;
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }
}
