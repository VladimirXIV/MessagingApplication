package just.education.security_messaging_app.liquibase;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import liquibase.database.Database;
import liquibase.exception.SetupException;
import liquibase.ext.mongodb.database.MongoConnection;
import liquibase.resource.ResourceAccessor;
import liquibase.exception.ValidationErrors;
import liquibase.change.custom.CustomTaskChange;
import liquibase.exception.CustomChangeException;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CS7_AddAdminRole implements CustomTaskChange {

    @Override
    public void execute(Database database) throws CustomChangeException {

        MongoConnection mongoConnection = (MongoConnection) database.getConnection();
        MongoDatabase mongoDatabase = mongoConnection.getMongoDatabase();

        // permission collection
        MongoCollection<Document> permissionCollection = mongoDatabase.getCollection("permission");
        BasicDBObject permissionSearchQuery = new BasicDBObject("name", new BasicDBObject("$in", Arrays.asList("read", "write", "update", "delete")));

        List<Document> permissions = new ArrayList<>();
        permissionCollection.find(permissionSearchQuery).forEach(permissions::add);

        // ids
        List<ObjectId> permissionIds = permissions
                .stream()
                .map(document -> (ObjectId) document.get("_id"))
                .collect(Collectors.toList());

        MongoCollection<Document> roles = mongoDatabase.getCollection("role");

        // new role ADMIN with permissions
        Document adminRole = new Document()
                .append("name", "ADMIN")
                .append("permissions", permissionIds);

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
