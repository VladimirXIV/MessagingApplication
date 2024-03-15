package just.education.convo_messaging_app.repository;

import org.bson.Document;
import com.mongodb.client.result.UpdateResult;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import just.education.convo_messaging_app.entity.Conversation;


public class ConversationRepository {

    private MongoTemplate mongoTemplate;

    public ConversationRepository() {
    }

    public ConversationRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public Conversation create(Conversation conversation) {

        return mongoTemplate.insert(conversation);
    };

    public Conversation retrieveById(String id) {

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, Conversation.class);
    };

    public Conversation retrieveByName(String name) {

        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Conversation.class);
    };

    public Conversation update(Conversation conversation) {

        Query query = new Query(Criteria.where("id").is(conversation.getId()));

        Document document = new Document();
        mongoTemplate.getConverter().write(conversation, document);
        Update update = Update.fromDocument(document);

        UpdateResult result = mongoTemplate.upsert(query, update, Conversation.class);
        if (result.wasAcknowledged()) {
            return mongoTemplate.findOne(query, Conversation.class);
        } else {
            return null;
        }
    };

    public Conversation deleteById(String id) {

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query, Conversation.class);
    };
}