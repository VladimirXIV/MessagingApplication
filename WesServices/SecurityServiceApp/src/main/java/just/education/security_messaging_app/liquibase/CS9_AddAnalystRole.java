package just.education.security_messaging_app.liquibase;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.ext.mongodb.database.MongoConnection;
import liquibase.resource.ResourceAccessor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CS9_AddAnalystRole implements CustomTaskChange {

    @Override
    public void execute(Database database) throws CustomChangeException {

        MongoConnection mongoConnection = (MongoConnection) database.getConnection();
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase();

        // permission collection
        MongoCollection<Document> permissionCollection = mongoDatabase.getCollection("permission");
        BasicDBObject permissionSearchQuery = new BasicDBObject("name", new BasicDBObject("$in", Collections.singletonList("read")));

        Document permission = permissionCollection.find(permissionSearchQuery).first();
        ObjectId permissionId = (ObjectId) permission.get("_id");

        MongoCollection<Document> roles = mongoDatabase.getCollection("role");

        // new role ANALYST with permissions
        Document adminRole = new Document()
                .append("name", "ANALYST")
                .append("permissions", Collections.singletonList(permissionId));

        roles.insertOne(adminRole);
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
