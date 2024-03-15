package just.education.convo_messaging_app.repository;

import org.bson.Document;
import com.mongodb.client.result.UpdateResult;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import just.education.convo_messaging_app.entity.ConvoMessage;


public class ConvoMessageRepository {

    private MongoTemplate mongoTemplate;


    public ConvoMessageRepository() {
    }

    public ConvoMessageRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    public ConvoMessage create(ConvoMessage convoMessage) {

        return mongoTemplate.insert(convoMessage);
    };

    public ConvoMessage retrieveById(String id) {

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, ConvoMessage.class);
    };

    public ConvoMessage update(ConvoMessage convoMessage) {

        Query query = new Query(Criteria.where("id").is(convoMessage.getId()));
        
        Document document = new Document();
        mongoTemplate.getConverter().write(convoMessage, document);
        Update update = Update.fromDocument(document);

        UpdateResult result = mongoTemplate.upsert(query, update, ConvoMessage.class);
        if (result.wasAcknowledged()) {
            return mongoTemplate.findOne(query, ConvoMessage.class);
        } else {
            return null;
        }
    };

    public ConvoMessage deleteById(String id) {

        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query, ConvoMessage.class);
    };
}